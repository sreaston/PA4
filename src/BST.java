public class BST {

	public BSTNode root;

	public BST() {
		root = null;
	}

	public BST(int A[]) {
		root = null;
		for (int a : A)
			insert(a);
	}

	public BSTNode search(int key) {
		BSTNode tmp = root;
		while (tmp != null) {
			if (tmp.value == key)
				return tmp;
			else if (tmp.value > key)
				tmp = tmp.left;
			else
				tmp = tmp.right;
		}
		return null;
	}

	public BSTNode insert(int key) { // complete this method
		if (size() == 0) {
			root = new BSTNode(key);
			return root;
		}
		BSTNode tmp = root;
		BSTNode parent = null;
		while (tmp != null) {
			if (tmp.value == key) {
				return null;
			} else if (tmp.value < key){
				parent = tmp;
				tmp = tmp.right;
			} else {
				parent = tmp;
				tmp = tmp.left;
			}
		}
		BSTNode newNode = new BSTNode(key);
		newNode.parent = parent;
		if (parent.value > key) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		while (parent != null) {
			parent.size++;
			parent = parent.parent;
		}
		return newNode;
	}

	public boolean remove(int key) { // complete this method
		BSTNode nodeToDelete = search(key);
		if (nodeToDelete == null) {
			return false;
		} else if (nodeToDelete.right != null && nodeToDelete.left != null) {
			BSTNode maxNode = findMax(nodeToDelete.left);
			nodeToDelete.value = maxNode.value;
			nodeToDelete = maxNode;
		}
		BSTNode parent = nodeToDelete.parent;
		while (parent != null) {
			parent.size--;
			parent = parent.parent;
		}
		if (nodeToDelete.left == null && nodeToDelete.right == null) {
			removeLeaf(nodeToDelete);
		} else {
			removeNodeWithOneChild(nodeToDelete);
		}
		return true;
	}


	private void removeLeaf(BSTNode leaf) { // complete this method
		if (leaf == root) {
			root = null;
		} else {
			BSTNode parent = leaf.parent;
			if (parent.left == leaf) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			leaf.parent = null;
		}

	}

	private void removeNodeWithOneChild(BSTNode node) { // complete this method
		BSTNode child;
		if (node.left != null) {
			child = node.left;
			node.left = null;
		} else {
			child = node.right;
			node.right = null;
		}
		if (node == root) {
			root = child;
			child.parent = null;
			// In C++, you would delete the node here if necessary
		} else {
			if (node == node.parent.left) {
				node.parent.left = child;
			} else {
				node.parent.right = child;
			}
			child.parent = node.parent;
			node.parent = null;
			// In C++, you would delete the node here if necessary
		}
	}


	public BSTNode lca(int x, int y) { // complete this method
			BSTNode nodeX = search(x);
			BSTNode nodeY = search(y);

			if (nodeX == null || nodeY == null) {
				return null; // LCA is not defined if x or y is not in the tree
			}

			if (x == y) {
				return nodeX; // LCA of the same node is the node itself
			}

			int min = Math.min(x, y);
			int max = Math.max(x, y);
			BSTNode tmp = root;

			while (tmp != null) {
				if (tmp.value < min) {
					tmp = tmp.right;
				} else if (tmp.value > max) {
					tmp = tmp.left;
				} else {
					return tmp; // LCA found
				}
			}

			return null; // LCA not found
		}


	public int rank(int key) { // complete this method
		int rank = 0;
		BSTNode tmp = root;

		while (tmp != null) {
			if (tmp.value <= key) {
				rank++; // Increase rank by one
				if (tmp.left != null) {
					rank += tmp.left.size; // Increase rank by the size of the left subtree
				}
				tmp = tmp.right;
			} else {
				tmp = tmp.left;
			}
		}

		return rank;
	}

	private static BSTNode findMin(BSTNode node) {
		if (null == node)
			return null;
		while (node.left != null)
			node = node.left;
		return node;
	}

	private static BSTNode findMax(BSTNode node) {
		if (null == node)
			return null;
		while (node.right != null)
			node = node.right;
		return node;
	}

	private static int getHeight(BSTNode node) {
		if (node == null)
			return 0;
		else
			return 1 + Math.max(getHeight(node.left), getHeight(node.right));
	}

	private void print(BSTNode node) {
		if (null != node) {
			System.out.print(node.toString() + " ");
			print(node.left);
			print(node.right);
		}
	}

	public int getHeight() {
		return getHeight(root);
	}

	public void print() {
		print(root);
	}

	public int size() {
		return root == null ? 0 : root.size;
	}
}
