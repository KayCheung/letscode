/**
 * Created by nuc on 2016/1/15.
 */
public class a {
    public static void main(String[] args) {
        triangle(10);
    }

    private static void triangle(int height) {
        int seed = 1;
        int[] curRow = new int[]{seed};
        for (int i = 0; i < height; i++) {
            display(curRow);
            curRow = generateNextRow(curRow);
        }
    }

    private static int[] generateNextRow(int[] curRow) {
        int[] nextRow = new int[curRow.length + 1];
        nextRow[0] = curRow[0];
        nextRow[nextRow.length - 1] = curRow[curRow.length - 1];

        for (int i = 1; i < nextRow.length - 1; i++) {
            nextRow[i] = curRow[i - 1] + curRow[i];
        }
        return nextRow;
    }

    private static void display(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1) {
                System.out.println(array[i]);
            } else {
                System.out.print(array[i]);
                System.out.print("  ");
            }
        }
    }
}
