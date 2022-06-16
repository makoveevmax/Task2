public class RulesForRiver {
    private int[][] heightOfBlock;
    private static int stepsCount;
    private int maxCount;

    public RulesForRiver(int[][] heightOfBlock) {
        this.heightOfBlock = heightOfBlock;
        this.maxCount = heightOfBlock.length * heightOfBlock[0].length;
    }

    public boolean isBelongToRiver(Task2.Coordinate coordinate, Directions prev) {
        int x = coordinate.x();
        int y = coordinate.y();

        ++stepsCount;
        if (stepsCount > maxCount) {
            return false;
        }

        if (x == 0 || y == 0 || x == heightOfBlock.length-1 || y == heightOfBlock[0].length-1) {
            return true;
        }

        int n = heightOfBlock[x-1][y];
        int e = heightOfBlock[x][y+1];
        int s = heightOfBlock[x+1][y];
        int w = heightOfBlock[x][y-1];
        int currentHeight = heightOfBlock[x][y];

        if (n <= currentHeight && !prev.equals(Directions.NORTH) &&
                isBelongToRiver(new Task2.Coordinate(x - 1, y), Directions.SOUTH)) {
            return true;
        }

        if (e <= currentHeight && !prev.equals(Directions.EAST) &&
                isBelongToRiver(new Task2.Coordinate(x, y + 1), Directions.WEST)) {
            return true;
        }

        if (s <= currentHeight && !prev.equals(Directions.SOUTH) &&
                isBelongToRiver(new Task2.Coordinate(x + 1, y), Directions.NORTH)) {
            return true;
        }

        if (w <= currentHeight && !prev.equals(Directions.WEST)) {
            return isBelongToRiver(new Task2.Coordinate(x, y - 1), Directions.EAST);
        }
        return false;
    }

    public void setHeightOfBlock(int[][] heightOfBlock) {
        this.heightOfBlock = heightOfBlock;
        this.maxCount = heightOfBlock.length * heightOfBlock[0].length;
    }
    public void resetCounter() {
        stepsCount = 0;
    }
}
