package controller;

import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.time.LocalDateTime;

public class RegisterController {
    @FXML
    private Text txtMessage;
    @FXML
    private TextArea taShowMessages;
    @FXML
    private Pane mainPain;
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn creditsTableColumn;
    @FXML
    private TableColumn studentIDTableColumn;
    @FXML
    private TableColumn studentNameTableColumn;
    @FXML
    private TableColumn courseIDTableColumn;
    @FXML
    private TableColumn courseNameTableColumn;
    @FXML
    private Pane buttonPane;
    @FXML
    private TableColumn DateTableColumn;
    @FXML
    private TableView studentTableColumn;

    private DoublyLinkedList registerList;
    private static DoublyLinkedList registerListGlobal = new DoublyLinkedList();
    private int currentNodeIndex = 1;

    // Métodos para acceder a la lista global de registros
    public static DoublyLinkedList getRegisterList() {
        return registerListGlobal;
    }

    public static void setRegisterList(DoublyLinkedList list) {
        registerListGlobal = list;
    }

    // Clase interna para mostrar registros con información de estudiante y curso
    public class RegistrationDisplay {
        private int id;
        private LocalDateTime date;
        private String studentId;
        private String studentName;
        private String courseId;
        private String courseName;
        private int credits;

        public RegistrationDisplay(int id, LocalDateTime date, String studentId, String studentName, String courseId, String courseName, int credits) {
            this.id = id;
            this.date = date;
            this.studentId = studentId;
            this.studentName = studentName;
            this.courseId = courseId;
            this.courseName = courseName;
            this.credits = credits;
        }

        // Getters necesarios para PropertyValueFactory
        public int getId() { return id; }
        public LocalDateTime getDate() { return date; }
        public String getStudentId() { return studentId; }
        public String getStudentName() { return studentName; }
        public String getCourseId() { return courseId; }
        public String getCourseName() { return courseName; }
        public int getCredits() { return credits; }
    }

    @FXML
    public void initialize() {
        // Configuramos el TextArea
        taShowMessages.setEditable(false);
        taShowMessages.setWrapText(true);

        registerList = registerListGlobal;

        // Configuramos las columnas de la tabla
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        DateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        studentIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        courseIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        creditsTableColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        // Cargamos los datos en la tabla
        updateTableView();

        // Mostramos un mensaje inicial
        try {
            taShowMessages.setText("Lista de registros cargada con éxito. Total de registros: " + registerList.size());
        } catch (ListException e) {
            taShowMessages.setText("Error al cargar la lista de registros: " + e.getMessage());
        }
    }

    // Método para actualizar la tabla
    private void updateTableView() {
        studentTableColumn.setItems(convertToObservableList(registerList));
    }

    // Convierte la lista enlazada a ObservableList para el TableView
    private ObservableList<RegistrationDisplay> convertToObservableList(DoublyLinkedList list) {
        ObservableList<RegistrationDisplay> registrations = FXCollections.observableArrayList();

        try {
            if (!list.isEmpty()) {
                Node aux = list.getNode(1);
                int i = 1;
                while (aux != null) {
                    Register register = (Register) aux.data;

                    // Buscar el estudiante correspondiente
                    Student student = findStudent(register.getStudentId());
                    String studentName = student != null ? student.getName() : "Desconocido";

                    // Buscar el curso correspondiente
                    Course course = findCourse(register.getCourseId());
                    String courseName = course != null ? course.getName() : "Desconocido";
                    int credits = course != null ? course.getCredits() : 0;

                    RegistrationDisplay display = new RegistrationDisplay(
                            register.getId(),
                            register.getRegisterDate(),
                            register.getStudentId(),
                            studentName,
                            register.getCourseId(),
                            courseName,
                            credits
                    );

                    registrations.add(display);
                    i++;
                    try {
                        aux = list.getNode(i);
                    } catch (ListException e) {
                        aux = null;
                    }
                }
            }
        } catch (ListException e) {
            taShowMessages.setText("Error al convertir la lista a ObservableList: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al convertir la lista a ObservableList: " + e.getMessage());
        }

        return registrations;
    }

    // Método para encontrar un estudiante por ID
    private Student findStudent(String studentId) {
        SinglyLinkedList studentList = util.Utility.getStudentList();
        try {
            Node aux = studentList.first;
            while (aux != null) {
                Student student = (Student) aux.data;
                if (student.getId().equals(studentId)) {
                    return student;
                }
                aux = aux.next;
            }
        } catch (Exception e) {
            // Ignorar errores
        }
        return null;
    }

    // Método para encontrar un curso por ID
    private Course findCourse(String courseId) {
        DoublyLinkedList courseList = CourseController.getCourseList();
        try {
            if (!courseList.isEmpty()) {
                for (int i = 1; i <= courseList.size(); i++) {
                    Course course = (Course) courseList.getNode(i).data;
                    if (course.getId().equals(courseId)) {
                        return course;
                    }
                }
            }
        } catch (ListException e) {
            // Ignorar errores
        }
        return null;
    }

    @FXML
    public void getPrevious(ActionEvent actionEvent) {
        try {
            if (!registerList.isEmpty() && currentNodeIndex > 1) {
                currentNodeIndex--;
                Register register = (Register) registerList.getNode(currentNodeIndex).data;
                taShowMessages.setText("Registro anterior:\n" + register.toString());
            } else {
                taShowMessages.setText("No hay registro anterior disponible.");
            }
        } catch (ListException e) {
            taShowMessages.setText("Error al obtener el registro anterior: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al obtener el registro anterior: " + e.getMessage());
        }
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
        try {
            registerList.clear();
            updateTableView();
            taShowMessages.setText("Lista de registros limpiada exitosamente.");
            registerListGlobal = registerList; // Actualizamos la lista global
        } catch (Exception e) {
            taShowMessages.setText("Error al limpiar la lista: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al limpiar la lista: " + e.getMessage());
        }
    }

    @FXML
    public void removeOnAction(ActionEvent actionEvent) {
        try {
            RegistrationDisplay selectedDisplay = (RegistrationDisplay) studentTableColumn.getSelectionModel().getSelectedItem();
            if (selectedDisplay != null) {
                Register selectedRegister = new Register(selectedDisplay.getId());
                registerList.remove(selectedRegister);
                updateTableView();
                taShowMessages.setText("Registro eliminado exitosamente:\nID: " + selectedDisplay.getId() +
                        "\nEstudiante: " + selectedDisplay.getStudentName() +
                        "\nCurso: " + selectedDisplay.getCourseName());
                registerListGlobal = registerList; // Actualizamos la lista global
            } else {
                taShowMessages.setText("No se ha seleccionado ningún registro para eliminar.");
                util.FXUtility.showErrorAlert("Error", "No se ha seleccionado ningún registro");
            }
        } catch (ListException e) {
            taShowMessages.setText("Error al eliminar el registro: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al eliminar el registro: " + e.getMessage());
        }
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "addRegister-view.fxml", mainPain);
        registerListGlobal = registerList; // Actualizamos la lista global
    }

    @FXML
    public void sortByID(ActionEvent actionEvent) {
        try {
            // Creamos una nueva lista para almacenar los elementos ordenados
            DoublyLinkedList sortedList = new DoublyLinkedList();

            // Copiamos los elementos a la nueva lista
            for (int i = 1; i <= registerList.size(); i++) {
                sortedList.add(registerList.getNode(i).data);
            }

            // Ordenamos la nueva lista
            sortedList.sort();

            // Actualizamos la lista original
            registerList = sortedList;
            registerListGlobal = registerList;

            // Actualizamos la vista de la tabla
            updateTableView();
            taShowMessages.setText("Lista de registros ordenada por ID.");
        } catch (ListException e) {
            taShowMessages.setText("Error al ordenar la lista: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al ordenar la lista: " + e.getMessage());
        }
    }

    @FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
        try {
            Register removedRegister = (Register) registerList.removeFirst();
            updateTableView();
            taShowMessages.setText("Primer registro eliminado:\n" + removedRegister.toString());
            registerListGlobal = registerList; // Actualizamos la lista global
        } catch (ListException e) {
            taShowMessages.setText("Error al eliminar el primer registro: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al eliminar el primer registro: " + e.getMessage());
        }
    }

    @FXML
    public void containsOnAction(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Verificar Registro");
            dialog.setHeaderText("Ingrese el ID del registro");
            dialog.setContentText("ID:");

            dialog.showAndWait().ifPresent(id -> {
                try {
                    int registerId = Integer.parseInt(id);
                    Register register = new Register(registerId);
                    boolean contains = registerList.contains(register);
                    if (contains) {
                        taShowMessages.setText("El registro con ID " + id + " existe en la lista.");
                    } else {
                        taShowMessages.setText("El registro con ID " + id + " no existe en la lista.");
                    }
                } catch (NumberFormatException e) {
                    taShowMessages.setText("Por favor, ingrese un ID válido (número entero).");
                    util.FXUtility.showErrorAlert("Error", "Por favor, ingrese un ID válido (número entero)");
                } catch (ListException e) {
                    taShowMessages.setText("Error al verificar si el registro existe: " + e.getMessage());
                    util.FXUtility.showErrorAlert("Error", "Error al verificar si el registro existe: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            taShowMessages.setText("Error: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error: " + e.getMessage());
        }
    }

    @FXML
    public void getNext(ActionEvent actionEvent) {
        try {
            int size = registerList.size();
            if (!registerList.isEmpty() && currentNodeIndex < size) {
                currentNodeIndex++;
                Register register = (Register) registerList.getNode(currentNodeIndex).data;
                taShowMessages.setText("Siguiente registro:\n" + register.toString());
            } else {
                taShowMessages.setText("No hay siguiente registro disponible.");
            }
        } catch (ListException e) {
            taShowMessages.setText("Error al obtener el siguiente registro: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al obtener el siguiente registro: " + e.getMessage());
        }
    }

    @FXML
    public void sortByStudent(ActionEvent actionEvent) {
        try {
            // Esta función debería ordenar por ID de estudiante
            // Como no tenemos un método de ordenación específico para esto, lo implementamos

            // Implementación básica de bubble sort para ordenar por ID de estudiante
            int n = 0;
            try {
                n = registerList.size();
            } catch (ListException e) {
                taShowMessages.setText("Error al obtener el tamaño de la lista: " + e.getMessage());
                util.FXUtility.showErrorAlert("Error", "Error al obtener el tamaño de la lista: " + e.getMessage());
                return;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n - i; j++) {
                    try {
                        Register reg1 = (Register) registerList.getNode(j).data;
                        Register reg2 = (Register) registerList.getNode(j + 1).data;

                        if (reg1.getStudentId().compareTo(reg2.getStudentId()) > 0) {
                            // Intercambiamos los registros
                            Object temp = registerList.getNode(j).data;
                            registerList.getNode(j).data = registerList.getNode(j + 1).data;
                            registerList.getNode(j + 1).data = temp;
                        }
                    } catch (ListException e) {
                        // Ignorar
                    }
                }
            }

            // Actualizamos la lista global
            registerListGlobal = registerList;

            // Actualizamos la vista de la tabla
            updateTableView();
            taShowMessages.setText("Lista de registros ordenada por ID de estudiante.");
        } catch (Exception e) {
            taShowMessages.setText("Error al ordenar la lista: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al ordenar la lista: " + e.getMessage());
        }
    }

    @FXML
    public void sizeOnAction(ActionEvent actionEvent) {
        try {
            int size = registerList.size();
            taShowMessages.setText("Tamaño de la lista de registros: " + size);
        } catch (ListException e) {
            taShowMessages.setText("Error al obtener el tamaño de la lista: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al obtener el tamaño de la lista: " + e.getMessage());
        }
    }
}