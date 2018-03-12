import java.io.PrintWriter;

/*
 * Class methods from CPSC 319 lecture notes and AVL methods taken from
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
    /*
     * Insertion method called only in the main method
     */
    public void insert(char opcd, int snum, String lnam, String dep, String prog, int yr) {
    	root = insert(opcd, snum, lnam, dep, prog, yr, root);
    }
    /*
     * Insertion method with Node parameter. Only called in the above insertion method. 
     * AVL algorithm implemented here as well.
     */
	public Node insert(char opcd, int snum, String lnam, String dep, String prog, int yr, Node t) {
        if (t == null)													//If the root is null set the new node as the root.
            t = new Node(opcd, snum, lnam, dep, prog, yr, null, null);
        else if (lnam.compareTo(t.getLastname()) < 0)					//If the new node is less than the current node t (LEFT SIDE)
        {
            t.setLeft(insert(opcd, snum, lnam, dep, prog, yr, t.getLeft()));//Recursively find where to insert the new node
            if( height( t.getLeft() ) - height( t.getRight() ) == 2 ) {	//Check if tree is out of balance, if yes adjust accordingly
            	if( lnam.compareTo(t.getLeft().getLastname()) < 0 )		//If the new node is on the outside subtree of the pivot
                    t = rotateWithLeftChild( t );						//then execute a RIGHT ROTATION
                else													//If the new node is on the inside subtree of the pivot
                    t = doubleWithLeftChild( t );						//then execute a LEFT RIGHT ROTATION
            } 
        }
        else if( lnam.compareTo(t.getLastname()) > 0)				//If the new node is greater than the current node t (RIGHT SIDE)
        {
            t.setRight(insert(opcd, snum, lnam, dep, prog, yr, t.getRight()));//Recursively find where to insert the new node
            if( height( t.getRight() ) - height( t.getLeft() ) == 2 ) {	//Check if tree is out of balance, if yes adjust accordingly
            	if( lnam.compareTo(t.getRight().getLastname()) > 0) 	//If the new node is on the outside subtree of the pivot
                    t = rotateWithRightChild( t );						//then execute a LEFT ROTATION
                else													//If the new node is on the inside subtree of the pivot
                    t = doubleWithRightChild( t );						//Execute a RIGHT LEFT ROTATION
            }
        }
        else
          ;  // Duplicate; do nothing
        t.setHeight(max( height( t.getLeft() ), height( t.getRight() ) ) + 1);
        return t;		
	}
    /*
     *  Rotate binary tree node with left child, otherwise known as a RIGHT ROTATION
     */     
    private Node rotateWithLeftChild(Node k2)
    {
    	Node k1 = k2.getLeft();			//k2 is the pivot node so k1 will be the
        k2.setLeft(k1.getRight());		//Son node. Then set the pivot's left child to the right child of the son
        k1.setRight(k2);				//Then set son nodes right child to the pivot
        k2.setHeight(max( height( k2.getLeft() ), height( k2.getRight() ) ) + 1);//adjust the height values of the rotated nodes.
        k1.setHeight(max( height( k1.getLeft() ), k2.getHeight() ) + 1);
        return k1;
    }
    /*
     *  Rotate binary tree node with right child, otherwise known as a LEFT ROTATION
     */
    private Node rotateWithRightChild(Node k1)
    {
    	Node k2 = k1.getRight();		//k2 is the pivot node so k1 will be the
        k1.setRight(k2.getLeft());		//Son node. Then set the pivot's right child to the left child of the son
        k2.setLeft(k1);					//Then set son nodes left child to the pivot
        k1.setHeight(max( height( k1.getLeft() ), height( k1.getRight() ) ) + 1);//adjust the height values of the rotated nodes.
        k2.setHeight(max( height( k2.getRight() ), k1.getHeight() ) + 1);
        return k2;
    }
    /*
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child 
     * Otherwise known as a LEFT RIGHT ROTATION
     */
    private Node doubleWithLeftChild(Node k3)//k3 is the pivot node
    {
        k3.setLeft(rotateWithRightChild( k3.getLeft() ));	//Do a LEFT ROTATION of the son 
        return rotateWithLeftChild( k3 );					//Do a RIGHT ROTATION at the pivot
    }
    /*
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child 
     * Otherwise known as a RIGHT LEFT ROTATION
     */      
    private Node doubleWithRightChild(Node k1)//k1 is pivot node 
    {
        k1.setRight(rotateWithLeftChild( k1.getRight() ));	//Do a RIGHT ROTATION of the son 
        return rotateWithRightChild( k1 );					//Do a LEFT ROTATION at the pivot
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
