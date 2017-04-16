import java.io.File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Driver extends Application{

	static AVLTree<Student> tree = new AVLTree<Student>();
	static File file = new File("Student.data");

	Stage window;
	TableView<Student> table;
	ChoiceBox<String> choiceBox1 = new ChoiceBox<>();
	ComboBox<String> combo = new ComboBox<>();



	TextField nameInput, idInput, pDateInput,addressInput,eDateInput,classIdInput,id2;


	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception, Exception  {


		ArrayList<String> array1 = new ArrayList<String>();
		ArrayList<String[]> array2 = new ArrayList<String[]>();

		Scanner scan = new Scanner(file);

		while(scan.hasNextLine())
			array1.add(scan.nextLine());
		

		for (int i = 0; i < array1.size(); i++) {
			array2.add(array1.get(i).split(","));
		}


		for (int i = 0; i < array2.size(); i++) {
			String[] a = array2.get(i)[2].split("-");
			String[] b = array2.get(i)[5].split("-");


			Date da = new Date(Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(a[2]));
			Date db = new Date(Integer.parseInt(b[0]),Integer.parseInt(b[1]),Integer.parseInt(b[2]));

			boolean x =(array2.get(i)[6].equals("true"));

			tree.insert(new Student(Integer.parseInt(array2.get(i)[0]),array2.get(i)[1],da,array2.get(i)[3],Integer.parseInt(array2.get(i)[4]),db,x));

		}

		launch(args);





	}



	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		window = primaryStage;
		window.setTitle("School");


		TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Student, String> idColumn = new TableColumn<>("ID");
		idColumn.setMinWidth(30);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

		TableColumn<Student, Date> pDateColumn = new TableColumn<>("Birth Date");
		pDateColumn.setMinWidth(100);
		pDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate"));

		TableColumn<Student, String> addressColumn = new TableColumn<>("Address");
		addressColumn.setMinWidth(100);
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

		TableColumn<Student, String> classIDColumn = new TableColumn<>("Class ID");
		classIDColumn.setMinWidth(70);
		classIDColumn.setCellValueFactory(new PropertyValueFactory<>("classId"));

		TableColumn<Student, Date> eDateColumn = new TableColumn<>("Enrollment Date");
		eDateColumn.setMinWidth(130);
		eDateColumn.setCellValueFactory(new PropertyValueFactory<>("enrollmentDate"));

		TableColumn<Student, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setMinWidth(130);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));



		nameInput = new TextField();
		nameInput.setPromptText("Name");
		nameInput.setMaxWidth(90);

		idInput = new TextField();
		idInput.setPromptText("ID");
		idInput.setMaxWidth(55);

		pDateInput = new TextField();
		pDateInput.setPromptText("Birth date d/m/y");

		eDateInput = new TextField();
		eDateInput.setPromptText("Enrollment d/m/y");

		classIdInput = new TextField();
		classIdInput.setPromptText("Class ID");
		classIdInput.setMaxWidth(70);


		addressInput = new TextField();
		addressInput.setPromptText("Addresss");
		addressInput.setMaxWidth(90);



		//getItems returns the ObservableList object which you can add items to
		choiceBox1.getItems().add("Graduated");
		choiceBox1.getItems().add("UnGraduated");

		//Set a default value
		choiceBox1.setValue("UnGraduated");
		choiceBox1.setMinWidth(100);



		//getItems returns the ObservableList object which you can add items to
		combo.getItems().add("All Students");
		combo.getItems().add("All Students by Class");
		combo.getItems().add("All Graduated Students");
		combo.getItems().add("UnderGraduated by Class");


		//Set a default value
		combo.setValue("All Students");
		combo.setMinWidth(100);




		Label show = new Label("Show");

		id2 = new TextField();
		id2.setPromptText("ID");
		id2.setMaxWidth(55);





		//Button
		Button addButton = new Button("Add");
		try {
			addButton.setOnAction(e -> addButtonClicked());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Button saveButton = new Button("Save");
		saveButton.setOnAction(e -> saveButtonClicked());


		Button findButton = new Button("Find");
		findButton.setOnAction(e -> findButtonClicked());

		Button deleteButton = new Button("Delete");
		deleteButton.setOnAction(e -> deleteButtonClicked());

		Button delete2Button = new Button("Delete Graduates");
		delete2Button.setOnAction(e -> d2ButtonClicked());

		Button exitButton = new Button("Exit");
		exitButton.setOnAction(e -> exitButtonClicked());

		HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(nameInput,idInput,pDateInput,addressInput,classIdInput,eDateInput ,choiceBox1,addButton);

		HBox hBox3 = new HBox();
		hBox3.setPadding(new Insets(10,10,10,10));
		hBox3.setSpacing(10);
		hBox3.getChildren().addAll(findButton,id2,deleteButton);
		hBox3.setAlignment(Pos.CENTER);

		HBox hBox4 = new HBox();
		hBox4.setPadding(new Insets(10,10,10,10));
		hBox4.setSpacing(10);
		hBox4.getChildren().addAll(saveButton,exitButton);
		hBox4.setAlignment(Pos.CENTER);


		HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10,10,10,10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(show,combo);
		hBox2.setAlignment(Pos.CENTER);



		table = new TableView<>();
		table.setItems(printAllStudents(tree));
		table.getColumns().addAll(nameColumn,idColumn,pDateColumn,addressColumn,classIDColumn,eDateColumn,statusColumn);



		combo.setOnAction( e -> tableRefresh());


		VBox vBox = new VBox();
		vBox.getChildren().addAll(table,hBox2, hBox,hBox3,new Label("     "),delete2Button,new Label("    "),hBox4);
		vBox.setAlignment(Pos.CENTER);

		Scene scene = new Scene(vBox);

		window.setScene(scene);
		window.show();

	}

	private void tableRefresh() {
		// TODO Auto-generated method stub
		String x = combo.getValue();
		if(x.equals("All Students by Class")){
			table.getItems().clear();
			table.setItems(printAllStudentsByClass(tree));

		}
		else if(x.equals("All Graduated Students")){
			table.getItems().clear();
			table.setItems(printGraduated(tree));
		}
		else if(x.equals("UnderGraduated by Class")){
			table.getItems().clear();
			table.setItems(printUnderGraduate(tree));
		}
		else{
			table.getItems().clear();
			table.setItems(printAllStudents(tree));
		}
	}



	private void exitButtonClicked() {
		// TODO Auto-generated method stub
		System.exit(0);
	}



	private void d2ButtonClicked() {
		// TODO Auto-generated method stub
		deleteGraduate(tree);
		tableRefresh();

	}



	private void deleteButtonClicked() {
		// TODO Auto-generated method stub
		deleteStudent(Integer.parseInt(id2.getText()));
		id2.clear();
		tableRefresh();		
	}



	private void findButtonClicked() {
		// TODO Auto-generated method stub
		AlertBox.display("edit student",id2.getText());
		id2.clear();
		tableRefresh();
	}



	private void saveButtonClicked()  {

		String a = "";

		Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(tree.getRoot());
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();

			Student s = (Student) node.element;

			a+=s.toSave()+"\n";

			int level = node.height;
			AVLNode left = node.getLeft();
			AVLNode right = node.getRight();
			if (left != null) {
				left.height = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.height = level + 1;
				queue.add(right);
			}
		}

		PrintWriter p = null;
		try {
			p = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.print(a);
		p.close();




	}



	private void addButtonClicked()  {



		boolean d = choiceBox1.getValue() == "Graduare";
		String[] a = pDateInput.getText().split("[-/_]");
		String[] b = eDateInput.getText().split("[-/_]");
		
		idInput.setStyle("-fx-text-inner-color: black;");



		Student student = null;
		try {
			student = new Student(Integer.parseInt(idInput.getText()),nameInput.getText(),new Date(Integer.parseInt(a[2])-1900,Integer.parseInt(a[1])-1,Integer.parseInt(a[0])),addressInput.getText(),Integer.parseInt(classIdInput.getText()),new Date(Integer.parseInt(b[2])-1900,Integer.parseInt(b[1])-1,Integer.parseInt(b[0])),d);
			
			tree.insert(student);

			nameInput.clear();
			nameInput.clear();
			classIdInput.clear();
			addressInput.clear();
			eDateInput.clear();
			pDateInput.clear();
			idInput.clear();

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			classIdInput.clear();
			eDateInput.clear();
			pDateInput.clear();
			idInput.clear();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			idInput.clear();
//			idInput.setStyle("-fx-text-inner-color: red;");
			eDateInput.clear();
			pDateInput.clear();
			idInput.clear();

		}





		//        table.getItems().clear();
		//		table.setItems(printAllStudents(tree));
		tableRefresh();





	}



	private static ObservableList<Student> printByclass(AVLTree t,int c){

		AVLTree<Student> tree2 = new AVLTree<Student>();	

		Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(t.getRoot());
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();

			Student s = (Student) node.element;

			if(s.getClassId() == c)
				tree2.insert(s);

			int level = node.height;
			AVLNode left = node.getLeft();
			AVLNode right = node.getRight();
			if (left != null) {
				left.height = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.height = level + 1;
				queue.add(right);
			}
		}


		//		return tree2.printTreeAscending();
		tree2.printLeftToRight(tree2.getRoot());
		return tree2.students;


	}

	private static ObservableList<Student> printAllStudents(AVLTree t){


		//		return tree2.printTreeAscending();
		t.printLeftToRight(t.getRoot());
		return t.students;


	}

	private static ObservableList<Student> printGraduated(AVLTree t){

		AVLTree<Student> tree2 = new AVLTree<Student>();		

		Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(t.getRoot());
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();

			Student s = (Student) node.element;

			if(s.isGraduated())
				tree2.insert(s);

			int level = node.height;
			AVLNode left = node.getLeft();
			AVLNode right = node.getRight();
			if (left != null) {
				left.height = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.height = level + 1;
				queue.add(right);
			}
		}


		//		return tree2.printTreeAscending();
		tree2.printLeftToRight(tree2.getRoot());
		return tree2.students;


	}

	private static ObservableList<Student> printUndergraduatedByClass(AVLTree t,int c){

		AVLTree<Student> tree2 = new AVLTree<Student>();		

		Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(t.getRoot());
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();

			Student s = (Student) node.element;

			if(s.getClassId() == c && !s.isGraduated())
				tree2.insert(s);

			int level = node.height;
			AVLNode left = node.getLeft();
			AVLNode right = node.getRight();
			if (left != null) {
				left.height = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.height = level + 1;
				queue.add(right);
			}
		}


		//		return tree2.printTreeAscending();
		tree2.printLeftToRight(tree2.getRoot());
		return tree2.students;


	}

	private static ObservableList<Student> printAllStudentsByClass(AVLTree t){

		ObservableList<Student> students = FXCollections.observableArrayList();

		for (int i = 0; i < School.classes.size(); i++) {
			//			students.add(printUndergraduatedByClass(t,School.classes.get(i)));
			for (int j = 0; j < printByclass(t,School.classes.get(i)).size(); j++) {
				students.add(printByclass(t,School.classes.get(i)).get(j));
			}

		}
		return students;

	}
	private static ObservableList<Student> printUnderGraduate(AVLTree t){

		ObservableList<Student> students = FXCollections.observableArrayList();

		for (int i = 0; i < School.classes.size(); i++) {
			//			students.add(printUndergraduatedByClass(t,School.classes.get(i)));
			for (int j = 0; j < printUndergraduatedByClass(t,School.classes.get(i)).size(); j++) {
				students.add(printUndergraduatedByClass(t,School.classes.get(i)).get(j));
			}

		}
		return students;

	}

	private static void deleteGraduate(AVLTree t){



		Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(t.getRoot());
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();

			Student s = (Student) node.element;

			if(s.isGraduated())
				t.remove(s);

			int level = node.height;
			AVLNode left = node.getLeft();
			AVLNode right = node.getRight();
			if (left != null) {
				left.height = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.height = level + 1;
				queue.add(right);
			}
		}


	}

	private static void deleteStudentByTree(AVLTree t,int a){



		Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(t.getRoot());
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();

			Student s = (Student) node.element;

			if(s.getId() == a)
				t.remove(s);

			int level = node.height;
			AVLNode left = node.getLeft();
			AVLNode right = node.getRight();
			if (left != null) {
				left.height = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.height = level + 1;
				queue.add(right);
			}
		}


	}

	private static void deleteStudent(int a){
		deleteStudentByTree(tree,a);
	}

	private static Student find(int a){


		Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(tree.getRoot());
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();

			Student s = (Student) node.element;

			if(s.getId() == a)
				return s;


			int level = node.height;
			AVLNode left = node.getLeft();
			AVLNode right = node.getRight();
			if (left != null) {
				left.height = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.height = level + 1;
				queue.add(right);
			}
		}
		return null;





	}

	private static void save() throws FileNotFoundException{

		String a = "";		

		Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(tree.getRoot());
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();

			Student s = (Student) node.element;
			a = a + s.toSave() + "\n";


			int level = node.height;
			AVLNode left = node.getLeft();
			AVLNode right = node.getRight();
			if (left != null) {
				left.height = level + 1;
				queue.add(left);
			}
			if (right != null) {
				right.height = level + 1;
				queue.add(right);
			}
		}

		PrintWriter p = new PrintWriter(file);
		p.print(a);
		p.close();



	}


}
