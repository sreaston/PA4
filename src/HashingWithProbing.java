import java.util.ArrayList;

public class HashingWithProbing {

	public ArrayList<Integer> hashTable;
	public int _size;
	public int _garbage;

	public static final int EMPTY = -1;
	public static final int TOMBSTONE = -9;

	private int hash(int val) {
		return (37 * val + 61) % capacity();
	}

	public HashingWithProbing(int initSize) {
		init(initSize);
	}

	private void init(int capacity) { // complete this method
		_size = 0;
		_garbage = 0;

		hashTable = new ArrayList<>(capacity);

		for (int i = 0; i < capacity; i++) {
			hashTable.add(EMPTY);
		}
	}

	private void resize(int newTableSize) { // complete this method
		ArrayList<Integer> keys = new ArrayList<Integer>();

        int i = 0;
        while (i < hashTable.size()) {
            if (hashTable.get(i) == TOMBSTONE) {
                return;
            } else if (hashTable.get(i) == EMPTY) {
                return;
            } else {
                keys.add(hashTable.get(i));
            }
            i++;
        }
		init(newTableSize);
		while (i < keys.size()) {
			hashTable.add(keys.get(i));
			i++;
		}
    }

	public int search(int key) { // complete this method
		int keyHash = hash(key);

		int i = 0;
		while (i < capacity()) {
			if (hashTable.get(keyHash) == key) {
				return keyHash;
			} else if (hashTable.get(keyHash) == EMPTY) {
				return -1;
			}
			keyHash++;
			if (keyHash == capacity()) {
				keyHash = 0;
			}
			i++;
		}
	return keyHash;
	}

	public int insert(int key) { // complete this method
		if ((_size + _garbage) == capacity()) {
			resize(2 * _size);
		}
		int keyHash = hash(key);
		int i = 0;
		while (i < capacity()) {
			if (hashTable.get(keyHash) == key) {
				return -1;
			} else if (hashTable.get(keyHash)== EMPTY) {
				break;
			}
			keyHash++;
			if (keyHash == capacity()) {
				keyHash = 0;
			}
		}
		hashTable.set(keyHash, key);
		_size++;
		return keyHash;
	}


	public int remove(int key) { // complete this method
		int index = search(key);
		if (key < 0) {
			return -1;
		}
		_garbage++;
		_size--;
		if (_garbage * 2 == capacity()) {
			resize(2 * _size);
		}
		return index;
	}
	public int capacity() {
		return hashTable.size();
	}

	public int size() {
		return _size;
	}
}
