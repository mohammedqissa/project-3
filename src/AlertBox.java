import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.sql.Date;
import java.util.LinkedList;
import java.util.Queue;

import javafx.geometry.*;

public class AlertBox {

	static TextField nameInput;
	static TextField idInput;
	static TextField pDateInput;
	static TextField addressInput;
	static TextField eDateInput;
	static TextField classIdInput;
    static ChoiceBox<String> choiceBox1 = new ChoiceBox<>();
    public static Student st ;
    static VBox layout ;



    public static void display(String title, String id) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        
        if(School.isContain(Integer.parseInt(id))){
        
        Queue<AVLNode> queue = new LinkedList<AVLNode>();
		queue.add(Driver.tree.getRoot());
		while (!queue.isEmpty()) {
			AVLNode node = queue.poll();

			Student s = (Student) node.element;

			if(s.getId() == Integer.parseInt(id)){
				st = s;
				break;
			}
				

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
        
        
        nameInput = new TextField();
		nameInput.setText(st.getName());
		nameInput.setMaxWidth(90);

		idInput = new TextField();
		idInput.setText(st.getId()+"");
		idInput.setMaxWidth(55);

		pDateInput = new TextField();
		pDateInput.setText(st.getBirthDate().toString());
		
		eDateInput = new TextField();
		eDateInput.setText(st.getEnrollmentDate().toString());
		
		classIdInput = new TextField();
		classIdInput.setText(st.getClassId()+"");
		classIdInput.setMaxWidth(70);

		
		addressInput = new TextField();
		addressInput.setText(st.getAddress());
		addressInput.setMaxWidth(90);
		
        
		//getItems returns the ObservableList object which you can add items to
        choiceBox1.getItems().add("Graduated");
        choiceBox1.getItems().add("UnGraduated");

        //Set a default value
        choiceBox1.setValue(st.getStatus());
        choiceBox1.setMinWidth(100);
		

        
        Button closeButton = new Button("Close this window");
        closeButton.setOnAction(e -> window.close());
        
        Button saveButton = new Button("save this change");
        saveButton.setOnAction(e -> {
        	
        	st.setAddress(addressInput.getText());
        	st.setClassId(Integer.parseInt(classIdInput.getText()));
        	try {
				st.setId(Integer.parseInt(idInput.getText()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	st.graduated(choiceBox1.getValue().equals("Graduated"));
        	st.setName(nameInput.getText());
        	
    		String[] a = pDateInput.getText().split("[-/_]");
    		String[] b = eDateInput.getText().split("[-/_]");
    		
    		st.setBirthDate(new Date(Integer.parseInt(a[0])-1900,Integer.parseInt(a[1])-1,Integer.parseInt(a[2])));
    		st.setEnrollmentDate(new Date(Integer.parseInt(b[0])-1900,Integer.parseInt(b[1])-1,Integer.parseInt(b[2])));
        	
        	
        	
        });
        
        HBox hBox2 = new HBox();
		hBox2.setPadding(new Insets(10,10,10,10));
		hBox2.setSpacing(10);
		hBox2.getChildren().addAll(saveButton,closeButton);
        
        
        HBox hBox = new HBox();
		hBox.setPadding(new Insets(10,10,10,10));
		hBox.setSpacing(10);
		hBox.getChildren().addAll(nameInput,idInput,pDateInput,addressInput,classIdInput,eDateInput ,choiceBox1);

        layout = new VBox(10);
        layout.getChildren().addAll( new Label("Student has been found!"),hBox,hBox2);
        layout.setAlignment(Pos.CENTER);
        }
        else{
        	
        	Label l1 = new Label("Student Not Found!");
        	layout = new VBox();
        	layout.getChildren().add(l1);
        	layout.setMinSize(400, 100);
        	layout.setAlignment(Pos.CENTER);
        	
        	
        }

		//Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }



}