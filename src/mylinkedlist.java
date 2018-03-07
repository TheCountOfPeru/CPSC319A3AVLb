/*
 * Class methods adapted from CPSC 319 lecture notes
 * A linked list class 
 */
public class mylinkedlist {
	private nodell head;
	private nodell tail;
	public mylinkedlist() {
		setHead(tail = null);
	}
	public nodell getHead() {
		return head;
	}
	public void setHead(nodell head) {
		this.head = head;
	}
	public nodell getTail() {
		return tail;
	}
	public void setTail(nodell tail) {
		this.tail = tail;
	}
	public boolean isEmpty() {
		return getHead() == null;
	}
	public Node deleteFromHead() { // delete the head and return its info;
		Node el = getHead().getBSTnd();
		if (getHead() == tail) // if only one node on the list;
			setHead(tail = null);
		else 
			setHead(getHead().getNext());
		return el;
	}
	public void addToTail(Node el) {
		if (!isEmpty()) {
			tail.setNext(new nodell(el));
			tail = tail.getNext();
		}
		else 
			setHead(tail = new nodell(el));
	}
	
}
