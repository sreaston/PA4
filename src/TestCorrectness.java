import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TestCorrectness {

	final static int insertionArray[] = {11, 12, 15, 17, 12, 19, 4, 5, 11, 19, 20, 32, 77, 65, 66, 88, 99, 10, 8, 19,
			15, 66, 11, 19, 0, 3, 2, 55, 67, 78, 39};
	final static int numInsert = insertionArray.length;

	final static int searchArray[] = {29, 3, 19, 27, 12, 34, 4, 5, 19, 20, 32, 45, 37, 25, 99, 25, 8, 24, 12, 16};
	final static int numSearch = searchArray.length;

	final static int deleteArray[] = {11, 16, 12, 15, 5, 17, 19, 4, 5, 20, 32, 17, 19, 39, 99, 10, 8, 19, 15, 21, 0, 55, 3, 78, 66};
	final static int numDelete = deleteArray.length;

	final static int[] cleanUp = {77, 65, 2, 88, 39, 67};
	final static int numCleanUp = cleanUp.length;

	final static int CHAINING_TABLE_SIZE = 7;
	static final int INITIAL_PROBING_SIZE = 2;

	private static void printList(final List<Integer> list) {
		if (list.size() == 0) {
			System.out.print("[]");
			return;
		}
		Iterator<Integer> it = list.iterator();
		System.out.print("[");
		for (int i = 0; i < list.size() - 1; i++)
			System.out.print(it.next() + ", ");
		System.out.print(it.next() + "]");
	}

	public static HashingWithChaining testChaining() throws Exception {

		System.out.println("****************** Hashing with Chaining ******************\n");
		HashingWithChaining hChain = new HashingWithChaining(CHAINING_TABLE_SIZE);

		System.out.println("Inserting the following numbers: " + Arrays.toString(insertionArray));

		for (int i = 0; i < numInsert; i++)
			hChain.insert(insertionArray[i]);

		System.out.println("\n*** Hash Table Structure (after insertion) ***");
		int size = 0;
		for (int i = 0; i < CHAINING_TABLE_SIZE; i++) {
			System.out.print("Slot " + i + ": ");
			printList(hChain.hashTable.get(i));
			System.out.println();
			size += hChain.hashTable.get(i).size();
		}
		System.out.println("\nSize of hash table: " + size);

		System.out.println("\n*** Searching Hash Table ***");
		LinkedList<Integer> foundList = new LinkedList<Integer>();
		LinkedList<Integer> notFoundList = new LinkedList<Integer>();

		for (int i = 0; i < numSearch; i++) {
			int val = searchArray[i];
			if (hChain.search(val))
				foundList.add(val);
			else
				notFoundList.add(val);
		}
		System.out.print("Found: ");
		printList(foundList);
		System.out.print("\nDid not find: ");
		printList(notFoundList);
		System.out.println();

		System.out.print("\n*** Deleting Hash Table ***");

		LinkedList<Integer> deleteList = new LinkedList<Integer>();
		notFoundList = new LinkedList<Integer>();
		System.out.println();
		for (int i = 0; i < numDelete; i++) {
			int val = deleteArray[i];
			if (hChain.remove(val))
				deleteList.add(val);
			else
				notFoundList.add(val);
		}
		System.out.print("Deleted: ");
		printList(deleteList);
		System.out.println();
		System.out.print("Did not find: ");
		printList(notFoundList);
		System.out.println();

		System.out.println("\n*** Hash Table Structure (after deletion) ***");
		size = 0;
		for (int i = 0; i < CHAINING_TABLE_SIZE; i++) {
			System.out.print("Slot " + i + ": ");
			printList(hChain.hashTable.get(i));
			System.out.println();
			size += hChain.hashTable.get(i).size();
		}
		System.out.println("\nSize of hash table: " + size);
		return hChain;
	}

	public static HashingWithProbing testProbing() throws Exception {

		System.out.println("\n****************** Hashing with Probing ******************\n");
		HashingWithProbing hProbing = new HashingWithProbing(INITIAL_PROBING_SIZE);

		System.out.println("Inserting the following numbers: " + Arrays.toString(insertionArray));

		for (int i = 0; i < numInsert; i++) {
			hProbing.insert(insertionArray[i]);
		}

		System.out.println("\n*** Hash Table Structure (after insertion) ***");
		System.out.println(hProbing.hashTable);
		System.out.println("\nSize of hash table: " + hProbing._size);

		System.out.println("\n*** Searching Hash Table ***");
		List<Integer> foundList = new LinkedList<Integer>();
		List<Integer> notFoundList = new LinkedList<Integer>();

		for (int i = 0; i < numSearch; i++) {
			int val = searchArray[i];
			if (hProbing.search(val) >= 0)
				foundList.add(val);
			else
				notFoundList.add(val);
		}
		System.out.print("Found: ");
		printList(foundList);
		System.out.print("\nDid not find: ");
		printList(notFoundList);

		System.out.print("\n\n*** Deleting Hash Table ***");

		List<Integer> deleteList = new LinkedList<Integer>();
		notFoundList = new LinkedList<Integer>();
		System.out.println();
		for (int i = 0; i < numDelete; i++) {
			int val = deleteArray[i];
			if (hProbing.remove(val) >= 0)
				deleteList.add(val);
			else
				notFoundList.add(val);
		}
		System.out.print("Deleted: ");
		printList(deleteList);
		System.out.print("\nDid not find: ");
		printList(notFoundList);

		System.out.println("\n\n*** Hash Table Structure (after deletion) ***");
		System.out.println(hProbing.hashTable);
		System.out.println("\nSize of hash table: " + hProbing._size);
		return hProbing;
	}

	private static BST testBST() {
		System.out.println("\n****************** BST ******************\n");

		BST bst = new BST();

		System.out.println("Inserting the following numbers: " + Arrays.toString(insertionArray));

		for (int i = 0; i < numInsert; i++) {
			bst.insert(insertionArray[i]);
		}

		System.out.println("\n*** BST Structure (after insertion) ***");
		bst.print();
		System.out.println("\n\nSize of BST: " + bst.size());

		System.out.println("\n*** Searching BST ***");
		LinkedList<Integer> foundList = new LinkedList<Integer>();
		LinkedList<Integer> notFoundList = new LinkedList<Integer>();

		for (int i = 0; i < numSearch; i++) {
			int val = searchArray[i];
			if (bst.search(val) != null)
				foundList.add(val);
			else
				notFoundList.add(val);
		}
		System.out.print("Found: ");
		printList(foundList);
		System.out.print("\nDid not find: ");
		printList(notFoundList);

		System.out.print("\n\n*** Deleting BST ***");

		LinkedList<Integer> deleteList = new LinkedList<Integer>();
		notFoundList = new LinkedList<Integer>();
		System.out.println();
		for (int i = 0; i < numDelete; i++) {
			int val = deleteArray[i];
			if (bst.remove(val))
				deleteList.add(val);
			else
				notFoundList.add(val);
		}
		System.out.print("Deleted: ");
		printList(deleteList);
		System.out.print("\nDid not find: ");
		printList(notFoundList);

		System.out.println("\n\n*** BST Structure (after deletion) ***");
		bst.print();
		System.out.println("\n\nSize of BST: " + bst.size());
		return bst;
	}

	private static void testBSTApplications() throws Exception {
		System.out.println("****************** BST Applications ******************\n");
		int testArray[] = {50, 25, 80, 15, 40, 60, 90, 10, 20, 35, 45, 55, 65, 85, 95};
		System.out.println("Numbers in BST: " + Arrays.toString(testArray));
		System.out.println();
		BST bst = new BST(testArray);
		int LCA1[] = {20, 35, 95, 90, 40, 23, 82};
		int LCA2[] = {45, 45, 25, 65, 40, 85, 35};
		for (int i = 0; i < LCA1.length; i++) {
			BSTNode lca = bst.lca(LCA1[i], LCA2[i]);
			System.out.println("LCA of " + LCA1[i] + " and " + LCA2[i] + " is " + (lca == null ? "undefined" : lca.value));
		}
		System.out.println();
		int RANK[] = {10, 54, 18, 42, 37, 34, 9, 95, 57, 44, 67, 88, 62, 47, 100};
		for (int key : RANK)
			System.out.println("Rank of " + key + " is " + bst.rank(key));
	}

	private static void cleanTest(HashingWithChaining chaining, HashingWithProbing probing, BST bst) {
		System.out.println("\n****************** Clean up ******************");
		for (int i : cleanUp) {
			chaining.remove(i);
			probing.remove(i);
			bst.remove(i);
		}
		int size = 0;
		for (int i = 0; i < CHAINING_TABLE_SIZE; i++)
			size += chaining.hashTable.get(i).size();

		System.out.println("\nSize of chaining: " + size);
		System.out.println("Size of probing: " + probing._size);
		System.out.println("Size of BST: " + bst.size());
	}

	public static void main(String[] args) throws Exception {
		HashingWithChaining chaining = testChaining();
		HashingWithProbing probing = testProbing();
		BST bst = testBST();
		cleanTest(chaining, probing, bst);
		System.out.println();
		testBSTApplications();
	}
}
