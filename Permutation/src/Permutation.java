import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {

	public static void afdf(byte[] allElement) {
		int length = allElement.length;

		List<byte[]> currentContainer = new ArrayList<byte[]>();
		byte[] aArray = new byte[length];
		aArray[0] = allElement[0];
		currentContainer.add(aArray);

		List<byte[]> newSpawnedContainer = new ArrayList<byte[]>();

		for (int i = 1; i < allElement.length; i++) {
			for (byte[] oneAlignedArray : currentContainer) {
				AlignmentInfo aInfo = new AlignmentInfo(oneAlignedArray, i);
				List<byte[]> aContainer = aInfo.spawnAlignment(allElement[i]);
				newSpawnedContainer.addAll(aContainer);
			}
			currentContainer.clear();
			currentContainer.addAll(newSpawnedContainer);
			newSpawnedContainer.clear();
		}

		System.out.println("result:");
		for (byte[] bArray : currentContainer) {
			System.out.println(Arrays.toString(bArray));
		}
	}

	public static void main(String[] args) {
		afdf(new byte[] { 1, 2, 3,4,6,7,8,9 });
	}
}
