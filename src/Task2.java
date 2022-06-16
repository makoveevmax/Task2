import java.util.*;

public class Task2 {
    private int[][] blockHeights;
    private int width;
    private int length;
    private final RulesForRiver rulesForRiver;

    public Task2(int[][] blockHeights) {
        this.blockHeights = blockHeights;
        this.width = blockHeights.length;
        this.length = blockHeights[0].length;
        this.rulesForRiver = new RulesForRiver(blockHeights);
    }

    public int[][] waterHeightsByBlocks() {
        int[][] waterHeights = new int[width][length];
        int[][] originalBlockHeights;

        originalBlockHeights = deepCopy(blockHeights);

        method(waterHeights);
        while (!isMapsEquals(originalBlockHeights, blockHeights)) {
            originalBlockHeights = deepCopy(blockHeights);
            method(waterHeights);
        }
        return waterHeights;
    }

    private void method(int[][] waterHeights) {
        int newHeight;
        for (int x = 1; x < width - 1; ++x) {
            for (int y = 1; y < length - 1; ++y) {
                int currentHeight = blockHeights[x][y];
                List<Integer> listOfAroundBlockHeights = getAroundBlockHeights(new Coordinate(x, y));

                int firstMin = listOfAroundBlockHeights.get(0);
                int secondMin;
                if (listOfAroundBlockHeights.size() > 1) {
                    secondMin = listOfAroundBlockHeights.get(1);
                } else {
                    secondMin = firstMin;
                }

                boolean isHole = currentHeight == 0 || firstMin == 0;
                boolean isFlatland = currentHeight == firstMin;
                boolean isPeak = currentHeight > firstMin;
                if (isHole) {
                    waterHeights[x][y] += 0;
                } else {
                    if (isFlatland) {
                        boolean isRiver = rulesForRiver.isBelongToRiver(new Coordinate(x, y), Directions.NONE);
                        if (isRiver) {
                            waterHeights[x][y] += 0;
                        } else {
                            newHeight = secondMin - currentHeight + blockHeights[x][y];
                            waterHeights[x][y] = newHeight;
                            blockHeights[x][y] = newHeight;
                        }
                    } else {
                        if (isPeak) {
                            waterHeights[x][y] += 0;
                        } else {
                            newHeight = firstMin - currentHeight + blockHeights[x][y];
                            waterHeights[x][y] = newHeight;
                            blockHeights[x][y] = newHeight;
                        }
                    }
                }
                rulesForRiver.resetCounter();
            }
        }
    }

    private List<Integer> getAroundBlockHeights(Coordinate coordinate) {
        /*
                    n = north
                    s = south
                    w = west
                    e = east
        */
        int x = coordinate.x();
        int y = coordinate.y();

        int north = blockHeights[x - 1][y];
        int east = blockHeights[x][y + 1];
        int south = blockHeights[x + 1][y];
        int west = blockHeights[x][y - 1];

        List<Integer> aroundBlockHeights = new ArrayList<>(Arrays.asList(north, east, south, west));
        List<Integer> list = new ArrayList<>();
        Set<Integer> uniqueValues = new HashSet<>();
        for (Integer aroundBlockHeight : aroundBlockHeights) {
            if (uniqueValues.add(aroundBlockHeight)) {
                list.add(aroundBlockHeight);
            }
        }
        list.sort(null);
        return list;
    }

    private int[][] deepCopy(int[][] array) {
        int[][] newArray = new int[array.length][array[0].length];

        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, newArray[i], 0, array[i].length);
        }
        return newArray;
    }

    private boolean isMapsEquals(int[][] firstMap, int[][] secondMap) {
        if (firstMap.length != secondMap.length) {
            return false;
        }

        for (int i = 0; i < firstMap.length; ++i) {
            for (int k = 0; k < firstMap[i].length; ++k) {
                if (firstMap[i][k] != secondMap[i][k]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setBlockHeights(int[][] blockHeights) {
        this.blockHeights = blockHeights;
        this.width = blockHeights.length;
        this.length = blockHeights[0].length;
        this.rulesForRiver.setHeightOfBlock(blockHeights);
    }

    public static void main(String[] args) {
        TestCases.testCases();
    }
    public record Coordinate(int x, int y) {
    }
}
