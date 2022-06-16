public class TestCases {
    public static void testCases() {

        int[][] blockHeights = { {2, 2, 2, 2},
                                 {4, 1, 0, 4},
                                 {5, 6, 7, 2} };
        Task2 task2 = new Task2(blockHeights);
        int[][] retainingWater = task2.waterHeightsByBlocks();
        printArray(retainingWater);


        blockHeights = new int[][] { {2, 2, 2, 2},
                                     {2, 2, 2, 2},
                                     {2, 2, 2, 2} };
        task2.setBlockHeights(blockHeights);
        retainingWater = task2.waterHeightsByBlocks();
        printArray(retainingWater);

        blockHeights = new int[][] { {1, 3, 4, 5},
                                     {4, 1, 1, 4},
                                     {5, 6, 7, 2} };
        task2.setBlockHeights(blockHeights);
        retainingWater = task2.waterHeightsByBlocks();
        printArray(retainingWater);


        blockHeights = new int[][] { {1, 2, 1, 4, 4, 1},
                                     {2, 1, 4, 1, 1, 4},
                                     {2, 1, 3, 1, 1, 4},
                                     {1, 2, 1, 4, 4, 1},
                                     {1, 1, 1, 1, 1, 1}
        };
        task2.setBlockHeights(blockHeights);
        retainingWater = task2.waterHeightsByBlocks();
        printArray(retainingWater);
    }

    public static void printArray(int[][] array) {
        StringBuilder output = new StringBuilder();
        for (int[] ints : array) {
            for (int y = 0; y < array[0].length; ++y) {
                output.append(ints[y]).append(", ");
            }
            output.append("\n");
        }
        output.deleteCharAt(output.length()-3);
        System.out.println(output);
    }
}
