
/**
 * @authors Hen Shiryon & Bar Zada
 */
public class BinarySearchTree {

	Data data;
	BinarySearchTree left;
	BinarySearchTree right;

	public BinarySearchTree(String word, int line) {
		this.data = new Data(word, line);
		this.left = null;
		this.right = null;
	}
}
