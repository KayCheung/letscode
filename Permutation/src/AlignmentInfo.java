import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlignmentInfo {
	public byte[] orgnArray;
	public int usedCount;

	public AlignmentInfo(byte[] orgnArray, int usedCount) {
		this.orgnArray = orgnArray;
		this.usedCount = usedCount;
	}

	public List<byte[]> spawnAlignment(byte newElement) {
		System.out.println("newElement=" + newElement + ", orgnArray="
				+ Arrays.toString(orgnArray));
		int usedCount = this.usedCount;
		int orgnLength = orgnArray.length;

		List<byte[]> listContainer = new ArrayList<byte[]>(usedCount + 1);
		// put newElement at index i
		for (int i = 0; i < usedCount; i++) {
			AlignmentInfo aInfo = new AlignmentInfo(new byte[orgnLength], 0);
			listContainer.add(aInfo.orgnArray);

			// elements before i
			System.arraycopy(orgnArray, 0, aInfo.orgnArray, 0, i);
			// elements at i
			aInfo.orgnArray[i] = newElement;
			// elements after i
			System.arraycopy(orgnArray, i, aInfo.orgnArray, i + 1, usedCount
					- i);
			aInfo.usedCount++;
		}

		listContainer.add(orgnArray);
		orgnArray[usedCount] = newElement;
		usedCount++;

		for (byte[] aArray : listContainer) {
			System.out.println(Arrays.toString(aArray));
		}
		return listContainer;
	}

	public static void main(String[] args) {
		byte[] aaa = new byte[] { 1, 2, 3, 0, 0 };
		AlignmentInfo aInfo = new AlignmentInfo(aaa, 3);
		aInfo.spawnAlignment((byte) 4);
	}
}
