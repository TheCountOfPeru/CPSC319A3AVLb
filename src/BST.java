import java.io.PrintWriter;

/*
 * Class methods adapted from CPSC 319 lecture notes
 * A binary search tree class
 */
public class BST {
	private Node root;
	
	public BST() {
		root = null;
	}
	public Node getroot(){
		return root;
	}
	public void insert(char opcd, int snum, String lnam, String dep, String prog, int yr) {
		Node current = root, parent = null;
		while(current != null) {
			parent = current;
			if(lnam.compareTo(current.getLastname()) > 0)
				current = current.getRight();
			else
				current = current.getLeft();
		}
		if(root == null)
			root = new Node(opcd, snum, lnam, dep, prog, yr, null, null, parent);
		else if(lnam.compareTo(parent.getLastname())>0)
			parent.setRight(new Node(opcd, snum, lnam, dep, prog, yr, null, null, parent));
		else
			parent.setLeft(new Node(opcd, snum, lnam, dep, prog, yr, null, null, parent));
	}
	public void depthfirst(Node current, PrintWriter pw) {
		if(current != null) {
			depthfirst(current.getLeft(), pw);
			current.visit(pw);
			depthfirst(current.getRight(), pw);
		}
	}
	public void breadthfirst(PrintWriter pw) {
		Node p = root;
		myqueue queue = new myqueue();
		if (p != null) {
			queue.enqueue(p);
			while (!queue.isEmpty()) {
				p =  (Node) queue.dequeue();
				p.visit(pw);
				if (p.getLeft() != null)
					queue.enqueue(p.getLeft());
				if (p.getRight() != null)
					queue.enqueue(p.getRight());
			}
		}
		
	}
	public void deletebymerge(String el) {
		System.out.println("Seaching for key: [" + el + "] for deletion.");
		Node tmp, node, p = root, prev = null;
		while(p != null && !p.getLastname().equals(el)) {//find the node p with element el
			prev = p;
			if(p.getLastname().compareTo(el) < 0)
				p = p.getRight();
			else
				p = p.getLeft();
		}
		node = p;
		if(p != null && p.getLastname().equals(el)) {
			if(node.getRight() == null)			//If node has no right child attach the left child 
				node = node.getLeft(); 			//to the parent
			else if(node.getLeft() == null)		//If node has no left child attach the right child
				node = node.getRight();			//to the parent
			else {								//be ready for merging subtrees
				tmp = node.getLeft();			//1. move left
				while(tmp.getRight() != null)	//2. and then right as far as possible
					tmp = tmp.getRight();		
				tmp.setRight(node.getRight());	//3. Establish the link between the rightmost
				node = node.getLeft();			//node of the left subtree and the right subtree
			}
			if(p == root)
				root = node;
			else if(prev.getLeft() == p)
				prev.setLeft(node);
			else
				prev.setRight(node);
		}
		else if(root != null)
			System.out.println("key: [" + el + "] is not in the tree");
		else
			System.out.println("The tree is empty.");	
	}
}
