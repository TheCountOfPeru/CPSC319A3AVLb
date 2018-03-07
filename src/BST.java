import java.io.PrintWriter;

/*
 * Class methods adapted from CPSC 319 lecture notes unless otherwise noted
 * An AVL binary search tree class
 * http://www.sanfoundry.com/java-program-implement-avl-tree/
 */
public class BST {
	private Node root;
	
	public BST() {
		root = null;
	}
	public Node getroot(){
		return root;
	}
	/*
	 * Method to return the height of a node
	 */
	private int height(Node t) {
		return t == null ? -1 : t.getHeight();
	}
	/*
	 * Method to return max of left/right of node 
	 */
    private int max(int lhs, int rhs)
    {
        return lhs > rhs ? lhs : rhs;
    }
    public void insert(char opcd, int snum, String lnam, String dep, String prog, int yr) {
    	root = insert(opcd, snum, lnam, dep, prog, yr, root);
    }
	public Node insert(char opcd, int snum, String lnam, String dep, String prog, int yr, Node t) {
        if (t == null)
            t = new Node(opcd, snum, lnam, dep, prog, yr, null, null);
        else if (lnam.compareTo(t.getLastname()) < 0)
        {
            t.setLeft(insert(opcd, snum, lnam, dep, prog, yr, t.getLeft()));
            if( height( t.getLeft() ) - height( t.getRight() ) == 2 ) {	//Check if tree is out of balance, if yes adjust accordingly
            	if( lnam.compareTo(t.getLeft().getLastname()) < 0 )		//Check if a left or right rotation is needed
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
            } 
        }
        else if( lnam.compareTo(t.getLastname()) > 0)
        {
            t.setRight(insert(opcd, snum, lnam, dep, prog, yr, t.getRight()));
            if( height( t.getRight() ) - height( t.getLeft() ) == 2 ) {	//Check if tree is out of balance, if yes adjust accordingly
            	if( lnam.compareTo(t.getRight().getLastname()) > 0) 	//Check if a left or right rotation is needed
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
            }
        }
        else
          ;  // Duplicate; do nothing
        t.setHeight(max( height( t.getLeft() ), height( t.getRight() ) ) + 1);
        return t;		
	}
    /* Rotate binary tree node with left child */     
    private Node rotateWithLeftChild(Node k2)
    {
    	Node k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);
        k2.setHeight(max( height( k2.getLeft() ), height( k2.getRight() ) ) + 1);
        k1.setHeight(max( height( k1.getLeft() ), k2.getHeight() ) + 1);
        return k1;
    }
    /* Rotate binary tree node with right child */
    private Node rotateWithRightChild(Node k1)
    {
    	Node k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);
        k1.setHeight(max( height( k1.getLeft() ), height( k1.getRight() ) ) + 1);
        k2.setHeight(max( height( k2.getRight() ), k1.getHeight() ) + 1);
        return k2;
    }
    /*
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child 
     */
    private Node doubleWithLeftChild(Node k3)
    {
        k3.setLeft(rotateWithRightChild( k3.getLeft() ));
        return rotateWithLeftChild( k3 );
    }
    /*
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child 
     */      
    private Node doubleWithRightChild(Node k1)
    {
        k1.setRight(rotateWithLeftChild( k1.getRight() ));
        return rotateWithRightChild( k1 );
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
