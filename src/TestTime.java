import java.util.Random;

public class TestTime {

	/**
	 * Sieve of Eratosthenes method for generating primes
	 */
	private static int getLargestBoundedPrime(int n) {

		boolean prime[] = new boolean[n + 1];
		for (int i = 0; i < n; i++)
			prime[i] = true;

		for (int p = 2; p <= Math.sqrt(n); p++) {
			if (prime[p]) {
				// Update all multiples of p
				for (int i = p * 2; i <= n; i += p)
					prime[i] = false;
			}
		}

		// Get Largest Prime <= n
		for (int i = n; i >= 2; i--) {
			if (prime[i])
				return i;
		}
		return 2;
	}

	public static void compareHashingAndBST() throws Exception {
		System.out.println("****************** Time Test Dictionary ******************\n");

		int U = 1000000;
		int CHAINING_TABLE_SIZE = getLargestBoundedPrime(U / 10);
		int INITIAL_PROBING_SIZE = 128;

		Random rand = new Random(System.currentTimeMillis());

		HashingWithChaining hChain = new HashingWithChaining(CHAINING_TABLE_SIZE);
		HashingWithProbing hProbing = new HashingWithProbing(INITIAL_PROBING_SIZE);
		BST bst = new BST();

		long totalChainIns = 0, totalProbingIns = 0, totalBSTIns = 0;
		long totalChainSearch = 0, totalProbingSearch = 0, totalBSTSearch = 0;
		long totalChainDel = 0, totalProbingDel = 0, totalBSTDel = 0;

		int generatedNum[] = new int[U];

		int numFailedInsertions = 0;
		for (int i = 0; i < U; i++) {
			int val = rand.nextInt(U);
			generatedNum[i] = val;
			long startTime = System.currentTimeMillis();
			boolean inserted = hChain.insert(val);
			if (!inserted)
				numFailedInsertions++;
			totalChainIns += System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			if (bst.insert(val) != null && !inserted)
				throw new Exception("Something wrong with insertion!");
			totalBSTIns += System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			if (hProbing.insert(val) >= 0 && !inserted)
				throw new Exception("Something wrong with insertion!");
			totalProbingIns += System.currentTimeMillis() - startTime;
		}

		int numFailedSearches = 0;
		for (int i = 0; i < U; i++) {
			int val = rand.nextDouble() > 0.5 ? rand.nextInt(U) : generatedNum[rand.nextInt(U)];

			long startTime = System.currentTimeMillis();
			boolean found = hChain.search(val);
			if (!found)
				numFailedSearches++;
			totalChainSearch += System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			if (null != bst.search(val) && !found)
				throw new Exception("Something wrong with search!");
			totalBSTSearch += System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			if (hProbing.search(val) >= 0 && !found)
				throw new Exception("Something wrong with search!");
			totalProbingSearch += System.currentTimeMillis() - startTime;
		}

		int numFailedDeletions = 0;
		for (int i = 0; i < U; i++) {
			int val = rand.nextDouble() > 0.5 ? rand.nextInt(U) : generatedNum[rand.nextInt(U)];

			long startTime = System.currentTimeMillis();
			boolean deleted = hChain.remove(val);
			if (!deleted)
				numFailedDeletions++;
			totalChainDel += System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			if (bst.remove(val) && !deleted)
				throw new Exception("Something wrong with deletion!");
			totalBSTDel += System.currentTimeMillis() - startTime;

			startTime = System.currentTimeMillis();
			if (hProbing.remove(val) >= 0 && !deleted)
				throw new Exception("Something wrong with deletion!");
			totalProbingDel += System.currentTimeMillis() - startTime;
		}

		System.out.println("*** Hashing With Chaining ***\n");
		hChain.printStatistics();
		System.out.println("Total time over " + U + " insertion attempts (" + numFailedInsertions + " failed): "
				+ totalChainIns + " (may vary with each execution)");
		System.out.println("Total time over " + U + " search attempts (" + numFailedSearches + " failed): "
				+ totalChainSearch + " (may vary with each execution)");
		System.out.println("Total time over " + U + " deletion attempts (" + numFailedDeletions + " failed): "
				+ totalChainDel + " (may vary with each execution)");

		System.out.println("\n*** Hashing With Probing ***\n");
		System.out.println(
				"Size of Probing table = " + hProbing.size() + ". Number of Tombstones = " + hProbing._garbage);
		System.out.println("Total time over " + U + " insertion attempts (" + numFailedInsertions + " failed): "
				+ totalProbingIns + " (may vary with each execution)");
		System.out.println("Total time over " + U + " search attempts (" + numFailedSearches + " failed): "
				+ totalProbingSearch + " (may vary with each execution)");
		System.out.println("Total time over " + U + " deletion attempts (" + numFailedDeletions + " failed): "
				+ totalProbingDel + " (may vary with each execution)");

		System.out.println("\n*** BST ***\n");
		System.out.println("Height of BST = " + bst.getHeight());
		System.out.println("Total time over " + U + " insertion attempts (" + numFailedInsertions + " failed): "
				+ totalBSTIns + " (may vary with each execution)");
		System.out.println("Total time over " + U + " search attempts (" + numFailedSearches + " failed): "
				+ totalBSTSearch + " (may vary with each execution)");
		System.out.println("Total time over " + U + " deletion attempts (" + numFailedDeletions + " failed): "
				+ totalBSTDel + " (may vary with each execution)");
	}

	public static void main(String[] args) throws Exception {
		compareHashingAndBST();
	}
}
