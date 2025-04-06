package domain;

public class SinglyLinkedList implements List {

    public Node first; //Apuntador al inicio de la lista

    //Constructor
    public SinglyLinkedList(){
        this.first = null;

    }//End constructor



    @Override
    public int size() throws ListException {

        int counter = 0; //Contador de nodos

        if(isEmpty()){
            throw new ListException("Singly Linked List is empty");
        }else{

            Node aux = first;
            while(aux != null){
                counter++;
                aux = aux.next; //Mueve aux al nodo siguiente

            }//End while
        }//End if

        return counter;
    }

    @Override
    public void clear() {
        this.first = null;
    }

    @Override
    public boolean isEmpty() {
        return first == null; //Solo está vacío si first es nulo
    }

    @Override
    public boolean contains(Object element) throws ListException {

        if(isEmpty())
            throw new ListException("Singly Linked List is empty");

        Node aux = first;
        while(aux != null){

            if(util.Utility.compare(aux.data, element)==0) return true; //Ya lo encontró
            aux = aux.next; //muevo aux al nodo siguiente
            }//End while

        return false; //Signigica que no encontró el elemento
    }

    @Override
    public void add(Object element) {

        Node newNode = new Node(element);
        if(isEmpty())
            first = newNode; //Se coloca newNode como el primero

        else{
            Node aux = first;
            while(aux.next != null){
                aux = aux.next; //Mueve aux al nodo siguiente
            }//End while

            //Se sale del while cuando auxiliar está en el último nodo
            aux.next = newNode; //Hace que el siguiente nodo sea el newNode

        }//End if

    }//End add()

    @Override
    public void addFirst(Object element) {

        Node newNode = new Node(element);
        if(isEmpty())
            first = newNode; //Se coloca newNode como el primero
        else
            newNode.next = first; //newNode apunta al que era first antes de actualizarlo
        first = newNode;

    }

    @Override
    public void addLast(Object element) {
        add(element);

    }

    @Override
    public void addInSortedList(Object element) {

        Node newNode = new Node(element);
        Node aux = first; //aux para recorrer la lista
        Node postNode; //Almacena el valor del nodo siguiente al que se agrega


        if (isEmpty()) {
            first = newNode; //Si la lista está vacía newNode será el primero
        } else {

            //Se compara el primer nodo con el nuevo para saber si newNode va de primero en la lista
            if (util.Utility.compare(first.data, newNode.data) > 0) {

                newNode.next = first;
                first = newNode;

            } else {

                //aux recorre la lista mientras no haya llegado al final y mientras el siguiente nodo sea menor al newNode

                while (aux.next != null && util.Utility.compare(aux.next.data, newNode.data) < 0) {
                    aux = aux.next;

                }//End while

                //Cuando salga del while guarda aux.next en postNode para no perder el nodo al desconectarlo
                //Agrega newNode después de aux y conecta newNode con postNode

                postNode = aux.next;
                aux.next = newNode;
                newNode.next = postNode;

            }//End if2
        }//End if1

    }

    @Override
    public void remove(Object element) throws ListException {

        if(isEmpty())
            throw new ListException("Singly Linked List is empty");

        //Caso 1: El elemento a suprimir es el primero de la lista
        if(util.Utility.compare(first.data, element) == 0)
            first = first.next;

        //Caso 2: El elemento puede estar en el medio o al final
        else{

            Node prev = first; //Nodo anterior
            Node aux = first.next; //Nodo siguiente
            while(aux != null && !(util.Utility.compare(aux.data, element) == 0)){ //Mientras lo que hay en aux no sea lo que busco
                prev = aux;
                aux = aux.next;
            }
            //Se sale del while cuando alcanza nulo
            //O cuando encuentra el elemento
            if(aux != null && util.Utility.compare(aux.data, element) == 0){
                //Debo desenlazar el nodo
                prev.next = aux.next; //Se salta el nodo

            }//End if
        }//End if
    }

    @Override
    public Object removeFirst() throws ListException {


        if(isEmpty())
            throw new ListException("Singly Linked List is empty");


            Object value = first.data;
            first = first.next; //Movemos el apuntador al nodo siguiente
            return value;
    }

    @Override
    public Object removeLast() throws ListException {

        if (isEmpty())
            throw new ListException("Singly Linked List is empty");

        //Si hay solo un nodo en la lista se elimina
        if (first.next == null) {
            Object data = first.data;
            first = null;
            return data;
        }//End if


        Node aux = first;
        Node prev = null;


        while (aux.next != null) { //Recorre toda la lista
            prev = aux;
            aux = aux.next;
        }//End while

        //aux es el último y prev el anterior a ese
        prev.next = null; //Desconecta el último nodo
        return aux.data;

    }

    @Override
    public void sort() throws ListException {
        if(isEmpty())
            throw new ListException("Singly Linked List is empty");

        int n = 0;
        try {
            n = size();
        } catch (ListException e) {
            throw new ListException("Error al obtener tamaño: " + e.getMessage());
        }

        boolean swapped;

        for(int i = 0; i < n - 1; i++) {
            swapped = false;
            Node current = first;

            for(int j = 0; j < n - i - 1; j++) {
                Node nextNode = current.next;

                // Verificamos si los nodos contienen estudiantes para ordenar por nombre
                if(current.data instanceof Student && nextNode.data instanceof Student) {
                    Student currentStudent = (Student)current.data;
                    Student nextStudent = (Student)nextNode.data;

                    // Ordenamos por nombre (orden alfabético)
                    if(currentStudent.getName().compareTo(nextStudent.getName()) > 0) {
                        // Intercambiamos los datos de los nodos
                        Object temp = current.data;
                        current.data = nextNode.data;
                        nextNode.data = temp;
                        swapped = true;
                    }
                } else {
                    // Para otros tipos de objetos usamos el método compare de Utility
                    if(util.Utility.compare(current.data, nextNode.data) > 0) {
                        // Intercambiamos los datos de los nodos
                        Object temp = current.data;
                        current.data = nextNode.data;
                        nextNode.data = temp;
                        swapped = true;
                    }
                }

                current = current.next;
            }

            // Si no hubo intercambios en esta pasada, la lista ya está ordenada
            if(!swapped)
                break;
        }
    }

    @Override
    public int indexOf(Object element) throws ListException {

        if(isEmpty())
            throw new ListException("Singly Linked List is empty");

        Node aux = first;
        int index = 1; //El primer índice de la lista es 1
        while(aux != null){

            if(util.Utility.compare(aux.data, element) == 0) return index;
            index++;
            aux = aux.next;

        }//End while

        return -1; //Significa que el elemnto no existe en la lista
    }

    @Override
    public Object getFirst() throws ListException {
        if(isEmpty())
            throw new ListException("Singly Linked List is empty");
        return first.data;
    }

    @Override
    public Object getLast() throws ListException {

        if(isEmpty())
            throw new ListException("Singly Linked List is Empty");


        Node aux = first;

        while(aux.next != null){ //Recorre la lista
            aux = aux.next;
        }

        //Y cuando llega al último retorna la data
        return aux.data;

    }

    @Override
    public Object getPrev(Object element) throws ListException {

        if(isEmpty())
            throw new ListException("Singly Linked List is Empty");

        if(util.Utility.compare(first.data, element) == 0)
            return null;


        Node aux = first; //Para recorrer la lista

        while(aux.next!=null){

            if(util.Utility.compare(aux.next.data, element) == 0) //Si la data del nodo siguiente es igual al que se busca
                return aux.data;

            aux = aux.next;
        }//End while

        return null;

    }

    @Override
    public Object getNext(Object element) throws ListException {

        if(isEmpty())
            throw new ListException("Singly Linked List is Empty");

        if(util.Utility.compare(first.data, element) == 0)
            return null;

        Node aux = first; //Para recorrer la lista


        while(aux.next != null){

            if(util.Utility.compare(aux.data, element) == 0) //Si la data de aux es la que busca entonces retorna el siguiente nodo
                return aux.next.data;

            aux = aux.next; //Sigue recorriendo

        }//End while

         return null; //Si element es el último nodo entonces retorna false

    }

    @Override
    public Node getNode(int index) throws ListException {

        if(isEmpty())
            throw new ListException("Singly Linked List is Empty");

        Node aux = first;
        int i = 1; //Index para posiciones de cada nodo

        while(aux.next != null){

            if(util.Utility.compare(i, index)==0) {//Si index coincide con la posición entonces retorna el nodo
                return aux;
            }//End if

            i++;
            aux = aux.next; //Sigue recorriendo la lista
        }//End while

        return null;

    }

    @Override
    public String toString() {

        if(isEmpty()) return "Singly Linked List is empty";
        String result = "Singly Linked List Content\n";
        Node aux = first; //aux para moverme por la lista y no perder el puntero al inicio

        while(aux != null){
            result += aux.data+"\n";
            aux = aux.next; //lo muevo al siguiente nodo
        }
        return result;
    }//End toString



}//END CLASS
