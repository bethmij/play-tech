package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Client1LoginFormController {
    public AnchorPane pane;
    public TextField txtName;
    public JFXButton btnStart;
    static String userName;

    public static List<String> users = new ArrayList<>();

    public void btnStartOnAction(ActionEvent actionEvent) {
        userName = txtName.getText();

        if(users.contains(txtName.getText())){
            System.out.println("already added");
        }else {
            users.add(userName);
            try {
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/client1ChatForm.fxml"))));
                stage.setTitle("Chat Room");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }



    }
}
