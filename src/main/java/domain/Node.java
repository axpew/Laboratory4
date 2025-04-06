package domain;

public class Node {

    public Object data;
    public Node next; //Apuntador
    public Node prev; //apuntador al nodo anterior

    public Node(Object data) {
        this.data = data;
        this.next = null; //Puntero al siguiente nodo es nulo por default
    }//End constructor


    //Constructor 2
    public Node() {
        this.prev = this.next = null;
    }

}//END NODE
