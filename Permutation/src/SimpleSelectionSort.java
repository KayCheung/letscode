import java.util.Arrays;

public class SimpleSelectionSort {
	public static int[] simpleSelectionSort(int[] array, int startIndex,
			int endIndex) {
		int[] result = new int[endIndex - startIndex + 1];

		int currentResultIndex = 0;
		for (int i = startIndex; i <= endIndex; i++) {
			for (int k = i + 1; k <= endIndex; k++) {
				if (array[i] > array[k]) {
					int tmp = array[i];
					array[i] = array[k];
					array[k] = tmp;
				}
			}
			result[currentResultIndex] = array[i];
			currentResultIndex++;

		}
		return result;
	}

	public static void main(String[] args) {
		int[] array = { 3, 5, 2, 9, 8, 0, 3, 5, 8, 4, 7 };
		int[] result = simpleSelectionSort(array, 3, array.length - 1);
		System.out.println(Arrays.toString(result));
	}
}
