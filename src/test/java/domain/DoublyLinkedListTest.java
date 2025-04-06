package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void testContains() {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4));
        list.add(new Course("IF-4001", "Sistemas Operativos", 4));
        list.add(new Course("IF-2000", "Programación 1", 4));
        list.add(new Course("IF-3000", "Programación 2", 4));
        list.add(new Course("IF-5000", "Arquitectura", 3));
        list.add(new Course("IF-5100", "Redes", 4));
        list.add(new Course("IF-5100", "Bases de Datos", 4));
        list.add(new Course("IF-4101", "Lenguajes app Comerciales", 4));
        list.add(new Course("IF-3100", "Sistemas de Información", 3));

        try {
            // Utilizar el método contains(object) para buscar cursos
            System.out.println("Pruebas con método contains(object):");
            System.out.println("¿Existe Informática Aplicada, Id=IF-6201? " +
                    list.contains(new Course("IF-6201", "Informática Aplicada", 0)));
            System.out.println("¿Existe Algoritmos y Estructuras de Datos, Id=IF-3001? " +
                    list.contains(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4)));
            System.out.println("¿Existe Sistemas Operativos, Id=IF-4001? " +
                    list.contains(new Course("IF-4001", "Sistemas Operativos", 4)));
            System.out.println("¿Existe Análisis y Diseño de Sistemas, Id=IF-6100? " +
                    list.contains(new Course("IF-6100", "Análisis y Diseño de Sistemas", 0)));
        } catch (ListException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    void testGetNode() {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4));
        list.add(new Course("IF-4001", "Sistemas Operativos", 4));
        list.add(new Course("IF-2000", "Programación 1", 4));
        list.add(new Course("IF-3000", "Programación 2", 4));
        list.add(new Course("IF-5000", "Arquitectura", 3));
        list.add(new Course("IF-5100", "Redes", 4));
        list.add(new Course("IF-5100", "Bases de Datos", 4));
        list.add(new Course("IF-4101", "Lenguajes app Comerciales", 4));
        list.add(new Course("IF-3100", "Sistemas de Información", 3));

        try {
            // Utilizar el método getNode(i) para recorrer la lista
            System.out.println("\nRecorrido de la lista con getNode(i):");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println("El elemento en la posicion " + i + " es: " + list.getNode(i).data);
            }
        } catch (ListException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    void testIndexOf() {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4));
        list.add(new Course("IF-4001", "Sistemas Operativos", 4));
        list.add(new Course("IF-2000", "Programación 1", 4));
        list.add(new Course("IF-3000", "Programación 2", 4));
        list.add(new Course("IF-5000", "Arquitectura", 3));
        list.add(new Course("IF-5100", "Redes", 4));
        list.add(new Course("IF-5100", "Bases de Datos", 4));
        list.add(new Course("IF-4101", "Lenguajes app Comerciales", 4));
        list.add(new Course("IF-3100", "Sistemas de Información", 3));

        try {
            // Utilizar el método indexOf(object) para determinar la posición
            System.out.println("\nPruebas con método indexOf(object):");
            System.out.println("El curso Algoritmos y Estructuras de Datos Id=IF-3001 se encuentra en la posición: " +
                    list.indexOf(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4)));
            System.out.println("El curso Análisis y Diseño de Sistemas con Id=IF-6100 se encuentra en la posición: " +
                    list.indexOf(new Course("IF-6100", "Análisis y Diseño de Sistemas", 0)));
        } catch (ListException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    void testSort() {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4));
        list.add(new Course("IF-4001", "Sistemas Operativos", 4));
        list.add(new Course("IF-2000", "Programación 1", 4));
        list.add(new Course("IF-3000", "Programación 2", 4));
        list.add(new Course("IF-5000", "Arquitectura", 3));
        list.add(new Course("IF-5100", "Redes", 4));
        list.add(new Course("IF-5100", "Bases de Datos", 4));
        list.add(new Course("IF-4101", "Lenguajes app Comerciales", 4));
        list.add(new Course("IF-3100", "Sistemas de Información", 3));

        try {
            System.out.println("\nLista antes de ordenar:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(list.getNode(i).data);
            }

            // Ordene la lista de cursos por nombre
            list.sort();

            System.out.println("\nLista ordenada por nombre:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(list.getNode(i).data);
            }
        } catch (ListException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    void testRemove() {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4));
        list.add(new Course("IF-4001", "Sistemas Operativos", 4));
        list.add(new Course("IF-2000", "Programación 1", 4));
        list.add(new Course("IF-3000", "Programación 2", 4));
        list.add(new Course("IF-5000", "Arquitectura", 3));
        list.add(new Course("IF-5100", "Redes", 4));
        list.add(new Course("IF-5100", "Bases de Datos", 4));
        list.add(new Course("IF-4101", "Lenguajes app Comerciales", 4));
        list.add(new Course("IF-3100", "Sistemas de Información", 3));

        try {
            System.out.println("\nLista antes de eliminar cursos:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(list.getNode(i).data);
            }

            // Suprima los cursos con Id= IF-5000, IF-5100
            System.out.println("\nSuprimiendo cursos con Id= IF-5000, IF-5100");
            list.remove(new Course("IF-5000", "Arquitectura", 3));
            list.remove(new Course("IF-5100", "Redes", 4));
            list.remove(new Course("IF-5100", "Bases de Datos", 4));

            System.out.println("\nLista después de suprimir cursos:");
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(list.getNode(i).data);
            }
        } catch (ListException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Test
    void test2() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.addFirst(20);
        list.addFirst(10);
        list.addFirst(30);
        list.addFirst(50);
        list.addFirst(40);
        list.add(70);
        list.add(5);
        System.out.println(list);
        try {
            System.out.println("List size: "+list.size());
            System.out.println("Removed first item: "+list.removeFirst());
            System.out.println("List size: "+list.size());
            System.out.println(list);
            System.out.println("Removed first item: "+list.removeFirst());
            System.out.println("List size: "+list.size());
            /*for (int i = 0; i < 6 ; i++) {
                list.removeFirst();
            }*/
            for (int i = 0; i < 50; i++) {
                list.add(util.Utility.random(50));
            }
            System.out.println(list);

            for (int i = 0; i <10 ; i++) {
                int value = util.Utility.random(50);
                System.out.println(
                        list.contains(value)
                                ? "The element ["+value+"] exists in the list. " +
                                "Index: "+list.indexOf(value)
                                :"The element ["+value+" does not exist in the list"
                );

                //probamos remove
                if(list.contains(value)){
                    list.remove(value);
                    System.out.println("The element ["+value+"] has been deleted");
                }
            }

            System.out.println(list);
        } catch (ListException e) {
            throw new RuntimeException(e);
        }
    }
}//END TEST