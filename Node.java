public class Node<T extends Comparable<T>> {
     T data; //variables
     Node<T> left;
     Node<T> right;
    public Node(T value){ //overloaded constuctor
        this.data = value;
        this.left = null;
        this.right = null;
    }
    public Node(){ //default
        this.data = null;
        this.left = null;
        this.right = null;
    }
    public void insert(T value){ //my insert function for binary is in node to help insert 
        Payload o = new Payload((Payload) value);
        if (data.compareTo(value) < 0){
            if(right == null){
                right = new Node<T>(value);
            }else{
                right.insert(value);
            }
        }
        else if(data.compareTo(value) > 0){ //compart to value is called in payload
            if(left == null){
                left = new Node<T>(value);
            }
            else{
                left.insert(value);
            }
        }
    }
    public T getData(){ return data;} //my accessors 
    public Node<T>getLeft(){return left;}
    public Node<T> getRight() {return right;}
        
}
