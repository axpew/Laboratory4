package domain;

public class Student implements Person{

    private String id;
    private String name;
    private int age;
    private String address;

    public Student(String id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public Student(String id) {
        this.id = id;
    }

    //Métodos de la clase
    private double studyHours(){
        return age/2;
    }

    @Override
    public boolean equals(Person other) { // retorna true si los objetos tienen el mismo id

        Student student = (Student) other;
        return student.id.equals(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Student {" + "id=" + id + ", name=" + name + ", age=" + age + ", study hours="+studyHours()+", address="+address+'}';
    }
}
