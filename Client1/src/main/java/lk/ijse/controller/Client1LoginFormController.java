package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Client1LoginFormController {
    public AnchorPane pane;

    public void btnStartOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage)pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/client1ChatForm.fxml"))));
            stage.setTitle("Chat Room");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
