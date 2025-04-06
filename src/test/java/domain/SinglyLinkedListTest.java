package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SinglyLinkedListTest {



    @Test
    void test1(){

    SinglyLinkedList list = new SinglyLinkedList();
    list.add(new Student("1", "María",20,"Cartago"));
    list.add(new Student("2", "Carlos",22,"San José"));
    list.add(new Student("3", "Laura",20,"Paraíso"));
    list.add(new Student("4", "Paula",18,"Turrialba"));
    list.add(new Student("5", "Carlos",21,"Limón"));
    list.add(new Student("6", "Fabiana",19,"Paraíso"));
    list.add(new Student("7", "María",23,"Guanacaste"));
    list.add(new Student("8", "Carlos",25,"San Carlos"));
    list.add(new Student("9", "Laura",20,"Turrialba"));
    list.add(new Student("10", "Pedro",24,"Heredia"));

    System.out.println(list); //Muestra la lista de estudiantes

        try {
            System.out.println("---------------------------");
            System.out.println("¿Existe Pedro, Id=20? " + list.contains(new Student("20")));
            System.out.println("¿Existe Paula, Id=4? " + list.contains(new Student("4")));
            System.out.println("¿Existe Carlos, Id=5? " + list.contains(new Student("5")));
            System.out.println("¿Existe Carlos, Id=8? " + list.contains(new Student("8")));
            System.out.println("---------------------------");

            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------------------------");

            //Muestra los elementos almacenados en cada nodo
            for (int i = 1; i < list.size(); i++) {
                Node node = list.getNode(i);
                if (node != null) {
                    System.out.println("El elemento en la posición " + i + " es: " + node.data);
                } else {
                    System.out.println("No hay nodo en la posición " + i);
                }//End if
            }//End for

            System.out.println("-----------------------------------------------------------------------------------------------------------");


            System.out.println();

            System.out.println("----------------------------------------------------------------------------");

            Student CarlosID8 = new Student("8"); //Usamos el segundo constructor de Student
            Student CarlosID100 = new Student("100");


            System.out.println("El estudiante Carlos con Id=8 se encuentra en la posicion: " + list.indexOf(CarlosID8));
            System.out.println("El estudiante Carlos con Id=100 se encuentra en la posicion: " + list.indexOf(CarlosID100));

            System.out.println("----------------------------------------------------------------------------");
            System.out.println();

            System.out.println("Ordenamiento de la lista por nombre:\n");
            list.sort();
            System.out.println(list);

            System.out.println("----------------------------------------------------------------------------");

            System.out.println("Remover estudiantes con ID: 1, 3, 5:\n");

            Student id1 = new Student("1");
            Student id3 = new Student("3");
            Student id5 = new Student("5");

            list.remove(id1);
            list.remove(id3);
            list.remove(id5);

            System.out.println(list);

            System.out.println("------------------------------------------------------");

            System.out.println("Buscamos cuántos Carlos tenemos en la lista: " +countNames(list, "Carlos"));

            System.out.println("------------------------------------------------------");

            System.out.println();

            System.out.println("¿En la lista existe una estudiante con el nombre Karla? " + findNames(list, "Karla"));
            System.out.println("¿En la lista existe una estudiante con el nombre Fabiana? " + findNames(list, "Fabiana"));

        } catch (ListException e) {
            throw new RuntimeException(e);
        }


    }//End TEST1

    private int countNames(SinglyLinkedList list, String name){

        Node aux = list.first;
        int counter = 1;


        while(aux.next != null){

            Student currentStudent = (Student) aux.data;

            if(util.Utility.compare(currentStudent.getName(), name) == 0) //Compara el nombre del estudiante en el nodo actual con name ingresado
                counter++;

            aux = aux.next;

        }//End while

        return counter;

    }//End countNames

    private boolean findNames(SinglyLinkedList list, String name){

        Node aux = list.first;

        while(aux.next != null){ //Recorre la lista en busca de coincidencias

            Student currentStudent = (Student) aux.data;

            if(util.Utility.compare(currentStudent.getName(), name) == 0) //Compara el nombre del estudiante en el nodo actual con name ingresado
                return true;

            aux = aux.next;

        }//End while

        return false; //Si no encuentra coincidencias retorna false

    }//End findNames

    //@Test
    void test2(){

        SinglyLinkedList list = new SinglyLinkedList();
        list.addFirst(20);
        list.addFirst(10);
        list.addFirst(30);
        list.addFirst(50);
        list.addFirst(40);
        list.add(70);
        list.add(5);
        System.out.println(list);

        try {
            System.out.println("List size: " + list.size());
            System.out.println("Removed first item "+list.removeFirst());
            System.out.println("List size: " + list.size());
            System.out.println(list);
            System.out.println("Removed first item "+list.removeFirst());
            System.out.println("List size: " + list.size());
            System.out.println(list);

          /*
            for (int i = 0; i < 5; i++)//Para eliminar todo
                list.removeFirst();
           */

            for (int i = 0; i < 50; i++) {
                list.add(util.Utility.random(50));
            }//End for de agregar elementos

            System.out.println(list);
            System.out.println("List size: " + list.size());

            for (int i = 0; i < 10; i++){
                int value = util.Utility.random(50);
                System.out.println(
                        list.contains(value)
                        ? "The element [" + value + "] exists in the list" +
                                " ---- Index: " + list.indexOf(value)
                                : "The element ["+ value + "] does not exist in the list"
                );

                //Probamos remove
               if(list.contains(value)){
                   list.remove(value);
                   System.out.println("The element ["+value+"] has been deleted");
                }//End if
            }//End for


            //Muestra la lista final
            System.out.println(list);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }

}