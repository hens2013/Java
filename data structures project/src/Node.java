
public class Node {
	private int value;
	private Node next;
	
	public Node(int value) {
		this.value = value;
	}
	
	public int getData() {
		return value;
	}
	public Node getNext() {
		return next;
	}
	public void  setNext(Node next) {
		this.next = next;

	}
 
}
