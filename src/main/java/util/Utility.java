package util;

import domain.SinglyLinkedList;
import domain.Student;

import java.text.DecimalFormat;
import java.util.Random;

public class Utility {

    private static final Random random;
    private static SinglyLinkedList studentList;

    //constructor estático, inicializador estático
    static {
        // semilla para el random
        long seed = System.currentTimeMillis();
        random = new Random(seed);
        studentList = new SinglyLinkedList();
    }

    public static SinglyLinkedList getStudentList() {
        return studentList;
    }

    public static void setStudentList(SinglyLinkedList studentList) {
        Utility.studentList = studentList;
    }

    // ------------------------------------------------------------- Métodos:
    public static int random(int bound){
      // return(int) Math.floor(Math.random()*bound); //Forma 1
        return 1+random.nextInt(bound);

    }

    public static void fill(int[] a) {

    for (int i = 0; i < a.length; i++){
        a[i] = random(99);

    }

    }

    public static String format(long n) {

        return new DecimalFormat("###,###,###.##").format(n); //Establecer un formato para n

    }

    public static String format(double n) {

        return new DecimalFormat("###,###,###.##").format(n); //Establecer un formato para n

    }

    public static String $format(double n) {

        return new DecimalFormat("$###,###,###.##").format(n); //Establecer un formato para n

    }

    public static int min(int x, int y) {

        return x<y ? x : y;

    }

    public static int max(int x, int y) {

        return x>y ? x : y;

    }

    public static String show(int[] a) {

        String result ="";
        for (int item : a){
            if (item == 0) break; //si es cero es porque no hay más elementos
            result+=item + " ";

        }//End for

     return result;
    }


    public static int compare(Object a, Object b) {

        switch (instanceOf(a,b)){
            case "Integer":
                Integer int1 = (Integer)a;
                Integer int2 = (Integer)b;
                return int1 < int2 ? -1 :int1 > int2 ? 1 : 0; // ? --> Si es cierto haga... /// : --> Pero si no es cierto entonces haga... Puntos son como el else, ? es como el if

            case "String":
                String str1 = (String)a;
                String str2 = (String)b;
                return str1.compareTo(str2) < 0 ? -1 : str1.compareTo(str2) > 0 ? 1 : 0;

            case "Character":
                Character ch1 = (Character) a; Character ch2 = (Character) b;
                return ch1.compareTo(ch2) < 0 ? -1 : ch1.compareTo(ch2) > 0 ? 1 : 0;

            case "Student":
                Student st1 = (Student) a;
                Student st2 = (Student) b;
                return st1.getId().compareTo(st2.getId()) < 0 ? -1 : st1.getId().compareTo(st2.getId()) > 0 ? 1 : 0;

        }//End switch

        return 2; //Cuando es un caso Unknown
    }

    public static String instanceOf(Object a, Object b) {

        if (a instanceof Integer && b instanceof Integer) return "Integer";
        if (a instanceof String && b instanceof String) return "String";
        if (a instanceof Character && b instanceof Character) return "Character";
        if (a instanceof Student && b instanceof Student) return "Student";

        return "Unknown";

    }
}
