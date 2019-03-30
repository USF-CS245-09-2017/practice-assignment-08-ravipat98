// ravi patel
// assignment 8 cs 245
public class BST<T extends Comparable> {

    private Node<T> root;

    public boolean find(Comparable value) {
        return find(root, (T) value);
    }

    private boolean find(Node<T> node, T value) {
        if (node == null) {
            return false;
        }

        if (node.getData().compareTo(value) == 0) {
            return true;
        } else if (node.getData().compareTo(value) < 0) {
            return find(node.getRight(), value);
        } else {
            return find(node.getLeft(), value);
        }
    }

    public void insert(Comparable value) 
    {
        root = insert(root, (T) value);
    }

    private Node<T> insert(Node<T> node, T value) 
    {
        if (node == null) 
        {
            Node<T> n = new Node<>(value);
            return n;
        } 
        else if (value.compareTo(node.getData()) > 0) 
        {
            node.setRight(insert(node.getRight(), value));
        } 
        else 
        {
            node.setLeft(insert(node.getLeft(), value));
        }
        return node;
    }

    public void print() 
    {
        print(root);
        System.out.println();
    }

    private void print(Node<T> node) 
    {
        if (node != null) {
            print(node.getLeft());
            System.out.print(node.getData() + " ");
            print(node.getRight());
        }
    }

    public Node<T> delete(Comparable value) 
    {
        return delete(root, value);
    }

    private Node<T> delete(Node<T> node, Comparable value) 
    {
        if (node == null) {
            return null;
        }

        // delete the leaf
        Node<T> rightChild = node.getRight();
        if (rightChild != null && rightChild.getData().compareTo(value) == 0 && isLeaf(rightChild)) 
        {
            node.setRight(null);
            return rightChild;
        }
        Node<T> leftChild = node.getLeft();
        if (leftChild != null && leftChild.getData().compareTo(value) == 0 && isLeaf(leftChild)) 
        {
            node.setLeft(null);
            return leftChild;
        }

        if (node.getData().compareTo(value) == 0) 
        {
            return deleteNode(node);
        } 
        else if (node.getData().compareTo(value) < 0) 
        {
            return delete(node.getRight(), value);
        } 
        else 
        {
            return delete(node.getLeft(), value);
        }
    }

    private boolean isLeaf(Node<T> node) 
    {
        return node.getLeft() == null && node.getRight() == null;
    }

    private Node<T> deleteNode(Node<T> node) 
    {
        if (node.getLeft() == null) 
        {
            Node<T> deletedNode = node.getRight();
            node.setData(node.getRight().getData());
            node.setRight(null);
            return deletedNode;
        } else if (node.getRight() == null) 
        {
            Node<T> deletedNode = node.getLeft();
            node.setData(node.getLeft().getData());
            node.setLeft(null);
            return deletedNode;
        } else 
        {
            if (node.getRight().getLeft() == null) 
            {
                node.setData(node.getRight().getData());
                node.setRight(node.getRight().getRight());
                return node;
            } else 
            {
                node = removeSmallest(node.getRight());
                return node;
            }
        }
    }

    private Node<T> removeSmallest(Node<T> node) 
    {
        if (node.getLeft().getLeft() == null) 
        {
            Node<T> smallest = node.getLeft();
            node.setLeft(node.getLeft().getRight());
            return smallest;
        } else {
            return removeSmallest(node.getLeft());
        }
    }
}