import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class HashingWithChaining {

	public ArrayList<LinkedList<Integer>> hashTable;
	public int TABLE_SIZE;

	@SuppressWarnings("unchecked")
	public HashingWithChaining(int tableSize) {
		TABLE_SIZE = tableSize;
		hashTable = new ArrayList<>();
		for (int i = 0; i < TABLE_SIZE; i++)
			hashTable.add(new LinkedList<Integer>());
	}

	private int hash(int val) {
		return (37 * val + 61) % TABLE_SIZE;
	}


	public boolean search(int key) {
		int keyHash = hash(key);
		LinkedList<Integer> bucket = hashTable.get(keyHash);
		Iterator<Integer> iterator = bucket.iterator();
		while (iterator.hasNext()) {
			if (iterator.next() == key) {
				return true; // Key found
			}
		}
		return false; // Key not found
	}

	public boolean insert(int key) {
		int keyHash = hash(key);
		LinkedList<Integer> bucket = hashTable.get(keyHash);
		if (!bucket.contains(key)) {
			bucket.addLast(key);
			return true;
		}
		return false;
	}

	public boolean remove(int key) {
		int keyHash = hash(key);
		LinkedList<Integer> bucket = hashTable.get(keyHash);
		Iterator<Integer> iterator = bucket.iterator();
		while (iterator.hasNext()) {
			if (iterator.next() == key) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}


	public void printStatistics() {
		int maxSize = hashTable.get(0).size();
		int minSize = maxSize, total = maxSize;
		for (int i = 1; i < TABLE_SIZE; i++) {
			int size = hashTable.get(i).size();
			if (size > maxSize)
				maxSize = size;
			else if (size < minSize)
				minSize = size;
			total += size;
		}
		System.out.printf(
				"Max length of a chain = %d%n" + "Min length of a chain = %d%n" + "Avg length of chains = %d%n",
				maxSize, minSize, total / TABLE_SIZE);
	}
}
