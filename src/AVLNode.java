

public class AVLNode<AnyType extends Comparable<AnyType>> implements Comparable<AVLNode<AnyType>> {

    AVLNode( AnyType theElement )
    {
        this( theElement, null, null );
    }

    AVLNode( AnyType theElement, AVLNode<AnyType> lt, AVLNode<AnyType> rt )
    {
        element  = theElement;
        left     = lt;
        right    = rt;
        height   = 0;
    }

    AnyType           element;      // The data in the node
    AVLNode<AnyType>  left;         // Left child
    AVLNode<AnyType>  right;        // Right child
    int               height;       // Height

    
    

	public AnyType getElement() {
		return element;
	}

	public void setElement(AnyType element) {
		this.element = element;
	}

	public AVLNode<AnyType> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<AnyType> left) {
		this.left = left;
	}

	public AVLNode<AnyType> getRight() {
		return right;
	}

	public void setRight(AVLNode<AnyType> right) {
		this.right = right;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int compareTo(AVLNode comparable) {
		return this.element.compareTo((AnyType) comparable.element);
	}

	@Override
	public String toString() {
	    if (this.getLeft() != null && this.getRight() != null) {
	        return "Level " + height + ": " + element + " || left " + this.getLeft().element + " | right " + this.getRight().element;
	    } else if (this.getLeft() != null && this.getRight() == null) {
	        return "Level " + height + ": " + element + " || left " + this.getLeft().element;
	    } else if (this.getLeft() == null && this.getRight() != null) {
	        return "Level " + height + ": " + element + " || right " + this.getRight().element;
	    }

	    return "Level " + height + ": " + element;      
	}

}