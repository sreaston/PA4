public class BSTNode {

	public int value, size;
	public BSTNode left, right, parent;

	public BSTNode(int val) {
		// a node is always inserted as a leaf
		value = val;
		size = 1;
		left = null;
		right = null;
		parent = null;
	}

	public String toString() {
		if (parent == null)
			return "<" + value + ", null>";
		else {
			return "<" + value + ", " + parent.value + ">";
		}
	}
}
