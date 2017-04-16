import java.util.LinkedList;
import java.util.Queue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AVLTree<AnyType extends Comparable<? super AnyType>>
{

	private AVLNode<?> root;

	ObservableList<Student> students = FXCollections.observableArrayList();



	public AVLNode<?> getRoot() {
		return root;
	}

	
	public AVLTree( )
	{
		root = null;
	}

		public void insert( AnyType x )
	{
		root = insert( x, root );
	}

		public void remove( AnyType x )
	{
		root = remove( x, root );
	}


		private AVLNode remove( AnyType x, AVLNode t )
	{
		if( t == null )
			return t;   // Item not found; do nothing

		int compareResult = x.compareTo( (AnyType) t.element );

		if( compareResult < 0 )
			t.left = remove( x, t.left );
		else if( compareResult > 0 )
			t.right = remove( x, t.right );
		else if( t.left != null && t.right != null ) // Two children
		{
			t.element = findMin( t.right ).element;
			t.right = remove( (AnyType) t.element, t.right );
		}
		else
			t = ( t.left != null ) ? t.left : t.right;
		return balance( t );
	}

		public AnyType findMin( ) throws Exception
	{
		if( isEmpty( ) )
			throw new Exception( );
		return (AnyType) findMin( root ).element;
	}

		public AnyType findMax( ) throws Exception
	{
		if( isEmpty( ) )
			throw new Exception( );
		return (AnyType) findMax( root ).element;
	}

		public boolean contains( AnyType x )
	{
		return contains( x, root );
	}

		public void makeEmpty( )
	{
		root = null;
	}

		public boolean isEmpty( )
	{
		return root == null;
	}

		public String printTreeAscending( )
	{
		//		if( isEmpty() )
		//			return( "Empty tree\n" );
		//		else
		return(printTree( root ));
	}

	private static final int AI = 1;

	private AVLNode balance( AVLNode t )
	{
		if( t == null )
			return t;

		if( height( t.left ) - height( t.right ) > AI )
			if( height( t.left.left ) >= height( t.left.right ) )
				t = rotateWithLeftChild( t );
			else
				t = doubleWithLeftChild( t );
		else
			if( height( t.right ) - height( t.left ) > AI )
				if( height( t.right.right ) >= height( t.right.left ) )
					t = rotateWithRightChild( t );
				else
					t = doubleWithRightChild( t );

		t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
		return t;
	}

	public void checkBalance( )
	{
		checkBalance( root );
	}

	private int checkBalance( AVLNode t )
	{
		if( t == null )
			return -1;

		if( t != null )
		{
			int hl = checkBalance( t.left );
			int hr = checkBalance( t.right );
			if( Math.abs( height( t.left ) - height( t.right ) ) > 1 ||
					height( t.left ) != hl || height( t.right ) != hr )
				System.out.println( "OOPS!!" );
		}

		return height( t );
	}


	private AVLNode insert( AnyType x, AVLNode t )
	{
		if( t == null )
			return new AVLNode( x, null, null );

		int compareResult = x.compareTo( (AnyType) t.element );

		if( compareResult < 0 )
			t.left = insert( x, t.left );
		else if( compareResult > 0 )
			t.right = insert( x, t.right );
		else
			;  // Duplicate; do nothing
		return balance( t );
	}

	private AVLNode findMin( AVLNode t )
	{
		if( t == null )
			return t;

		while( t.left != null )
			t = t.left;
		return t;
	}

	private AVLNode findMax( AVLNode t )
	{
		if( t == null )
			return t;

		while( t.right != null )
			t = t.right;
		return t;
	}

	private boolean contains( AnyType x, AVLNode t )
	{
		while( t != null )
		{
			int compareResult = x.compareTo( (AnyType) t.element );

			if( compareResult < 0 )
				t = t.left;
			else if( compareResult > 0 )
				t = t.right;
			else
				return true;    // Match
		}

		return false;   // No match
	}

	private String printTree( AVLNode t )
	{
		String h="";
		if( t != null )
		{
			h = h + printTree( t.left );
			h = h + t.element+"\n";
			h += printTree( t.right );
		}
		return h;
	}


	private int height( AVLNode t )
	{
		return t == null ? -1 : t.height;
	}


	private AVLNode rotateWithLeftChild( AVLNode k2 )
	{
		AVLNode k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = Math.max( height( k1.left ), k2.height ) + 1;
		return k1;
	}


	private AVLNode rotateWithRightChild( AVLNode k1 )
	{
		AVLNode k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
		k2.height = Math.max( height( k2.right ), k1.height ) + 1;
		return k2;
	}

	private AVLNode doubleWithLeftChild( AVLNode k3 )
	{
		k3.left = rotateWithRightChild( k3.left );
		return rotateWithLeftChild( k3 );
	}

	@SuppressWarnings("unchecked")
	private AVLNode doubleWithRightChild( AVLNode k1 )
	{
		k1.right = rotateWithLeftChild( k1.right );
		return rotateWithRightChild( k1 );
	}


	public String PrintTree() {
		root.setHeight(0);
		String s = "";

		Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();
			s=s+node+"\n";
			int level = node.height;
			AVLNode left = node.getLeft();
			AVLNode right = node.getRight();
			if (left != null) {
				left.height = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.height = level + 1;
				queue.add(right);
			}
		}
		return s;
	}

	public void printLeftToRight(AVLNode node) {
		if(node != null) {
			if(node.left != null) {
				printLeftToRight(node.left);
			}
			students.add((Student) node.element);
			if(node.right != null) {
				printLeftToRight(node.right);
			}
		}
	}

	public AVLNode search(AnyType x) {
		try {
			if (x == root.getElement()) { //basis step
				return root;
			}
			if (x.compareTo((AnyType) root.getElement()) < 0) {
				root = root.getLeft();
				return search(x);//recursive call
			} else {
				root = root.getRight();
				return search(x);//recursive call
			}
		} catch (NullPointerException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return printTreeAscending();
	}


}