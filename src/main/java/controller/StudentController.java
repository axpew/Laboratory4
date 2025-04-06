package controller;

import domain.Node;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.Utility;

public class StudentController {


    @FXML
    private Text txtMessage;
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn nameTableColumn;
    @FXML
    private TableColumn addressTableColumn;
    @FXML
    private TableColumn ageTableColumn;
    @FXML
    private Pane buttonPane;
    @FXML
    private TableView studentTableColumn;

    private SinglyLinkedList studentList;
    @FXML
    private Pane mainPain;


    @FXML
    public void initialize(){

        //Cargamos la lista general
        this.studentList = Utility.getStudentList();
        SinglyLinkedList studentList = Utility.getStudentList();
/*
        //Se añaden los estudiantes de la lista inicial
        studentList.add(new Student("1", "María",20,"Cartago"));
        studentList.add(new Student("2", "Carlos",22,"San José"));
        studentList.add(new Student("3", "Laura",20,"Paraíso"));
        studentList.add(new Student("4", "Paula",18,"Turrialba"));
        studentList.add(new Student("5", "Carlos",21,"Limón"));
        studentList.add(new Student("6", "Fabiana",19,"Paraíso"));
        studentList.add(new Student("7", "María",23,"Guanacaste"));
        studentList.add(new Student("8", "Carlos",25,"San Carlos"));
        studentList.add(new Student("9", "Laura",20,"Turrialba"));
        studentList.add(new Student("10", "Pedro",24,"Heredia"));
*/
        //Asignar la lista al TableView
        studentTableColumn.setItems(convertToObservableList(studentList));


        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));




    }

    public ObservableList<Student> convertToObservableList(SinglyLinkedList list){

        ObservableList<Student> students = FXCollections.observableArrayList();

        Node aux = studentList.first;


        while (aux != null){ //Recorre la lista

            Object data = aux.data;
            Student current =  (Student)data;

            students.add(current); //Agrega el estudiante a la ObservableList

            aux = aux.next; //Avanzamos al siguiente nodo
        }//End while

        return students;
    }


    @Deprecated
    public void removeByIndex(ActionEvent actionEvent) {
    }

    @Deprecated
    public void contains(ActionEvent actionEvent) {
    }

    @Deprecated
    public void size(ActionEvent actionEvent) {
    }

    @Deprecated
    public void clear(ActionEvent actionEvent) {
    }

    @Deprecated
    public void addByIndex(ActionEvent actionEvent) {
    }

    @Deprecated
    public void fill(ActionEvent actionEvent) {
    }

    @Deprecated
    public void addByValue(ActionEvent actionEvent) {
    }

    @Deprecated
    public void removeByValue(ActionEvent actionEvent) {
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {

        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "addStudent-view.fxml", mainPain);

        //Después de agregar el objeto debemos actualizar la lista general
        Utility.setStudentList(this.studentList); //Para setear la lista general
    }

    @FXML
    public void addFirstOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void removeOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void addSortedOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void getFirstOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void containsOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void getLastOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void sizeOnAction(ActionEvent actionEvent) {
    }
}
