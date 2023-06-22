package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.controller.util.OpenView;

import java.io.IOException;

public class Client2LoginFormController {
    public TextField txtName;
    static String userName;
    public AnchorPane pane;

    public void btnStartOnAction(ActionEvent actionEvent) {
        OpenView.openView("Client2ChatForm",pane);
    }
}
