//Created by: Ritesh Virlley 
//Created on 3/29/21
//RXV200008
public class BinTree<T extends Comparable<T>> {
    Node<T> root;

    public BinTree() { //default construcot 
    this.root = null;
    }

    public BinTree(T value) { //overloaded constructor
        super();
        Node<T> node = new Node<T>(value);
        this.root = node;
    }

    public void add(T value) { //This is my insert it calls a function in node that helps insert into the binary tree
        if (root == null) {
            root = new Node<T>(value);
        } else {
            root.insert(value);
        }
    }

    public Node<T> search(Node<T> node, T data) { //this is my search function
        
        if (node == null) {
            return null;
        }
            if (node.data.compareTo(data) == 0) {
                return node;
            } else if (node.data.compareTo(data) < 0) {
                return search(node.right, data);
            } else {
                return search(node.left, data);
            }
            
    }

    private T successor(Node root) { //this finds the minimum value
        T min = (T) root.right.getData();
        while (root.left != null) {
            min = (T) root.left.data;
            root = root.left;
        }
        return min;
    }

    private T predecessor(Node root) { //this finds the max
        T max = (T) root.left.getData();
        while (root.right != null) {
            max = (T) root.right.data;
            root = root.right;
        }
        return max;
    }

    public Node delete(Node root, T data) { //this function deletes the node
        if (root == null) {
            return root;
        }
        if (root.data.compareTo(data) > 0) {
            root.left = delete(root.left, data);
        } else if (root.data.compareTo(data) < 0) {
            root.right = delete(root.right, data);
        } else {
            if (root.right == null && root.left == null) {
                root = null;
            } else if (root.right != null) {
                root.data = successor(root);
                root.right = delete(root.right, (T) root.data);

            } else {
                root.data = predecessor(root);
                root.left = delete(root.left, (T) root.data);
            }
        }
        return root;

    }
}
