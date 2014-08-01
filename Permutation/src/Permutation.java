import java.util.ArrayList;
import java.util.LinkedList;

public class Permutation<E> {

	private LinkedList<E> llElements = new LinkedList<E>();

	private ArrayList<LinkedList<E>> listExistingAlignment = new ArrayList<LinkedList<E>>();

	public void ffdfd(LinkedList<E> remainingElement,
			ArrayList<LinkedList<E>> listExistingAlignment) {
		E header = cutHeader(remainingElement);
		if (header == null) {
			return;
		}

		if (listExistingAlignment.size() == 0) {
			LinkedList<E> oneAlignment = new LinkedList<E>();
			oneAlignment.add(header);
			listExistingAlignment.add(oneAlignment);
			return;
		}

		int existedAlignmentCount = listExistingAlignment.size();
		for (int i = 0; i < existedAlignmentCount; i++) {
			LinkedList<E> oneAlignment = listExistingAlignment.get(i);
			int currentAligmentSize = oneAlignment.size();

			int spawnAlignmentCount = currentAligmentSize + 1;

			LinkedList<E>[] dd = new LinkedList<E>[];

		}
		for (LinkedList<E> oneAlignment : listExistingAlignment) {
			int size = oneAlignment.size();

		}
	}

	private void spawnList(E header, LinkedList<E> orgnAlignment,
			ArrayList<LinkedList<E>> newExistingAlignment) {
		
	}

	private E cutHeader(LinkedList<E> beChanged) {
		if (beChanged.isEmpty() == false) {
			return beChanged.removeFirst();
		}
		return null;
	}

	public static void main(String[] args) {

	}
}
