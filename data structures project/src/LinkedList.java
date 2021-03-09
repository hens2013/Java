
public class LinkedList {

	private Node first;
	private Node last;

	public Node getLast() {
		return last;
	}

	public LinkedList() {
		first = last = null;
	}

	public void add(int value) {
		if (first == null)
			first = last = new Node(value);
		else {
			last.setNext(new Node(value));
			last = last.getNext();
		}
	}

	@Override
	public String toString() {
		Node temp=first;
		StringBuilder sb=new StringBuilder();
		while (temp != last) {
			sb.append(temp.getData()+" ");
			temp=temp.getNext();
		}
		sb.append(last.getData());
		return sb.toString() ;
	}

}
