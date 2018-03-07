/*
 * Adapted from Data Structures and Algorithms 2nd Edition, Drozdek p.153
 * A queue class using a linked list
 */
public class myqueue {
	private mylinkedlist list = new mylinkedlist();
	
	public myqueue() {
		
	}
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public Object dequeue() {
		return list.deleteFromHead();
	}
	public void enqueue(Node el) {
		list.addToTail(el);
	}
}
