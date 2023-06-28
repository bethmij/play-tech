package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Client2LoginFormController {
    public AnchorPane pane;
    public TextField txtName;
    public JFXButton btnStart;
    static String userName;

    public static List<String> users = new ArrayList<>();
    public JFXButton btnImage;
    public static Image image;
    public static List list= new ArrayList();
    public static HashMap<String, Image> userLIst = new HashMap<>();
    public Circle circle;

    public void btnStartOnAction(ActionEvent actionEvent) {
        userName = txtName.getText();

        if(users.contains(txtName.getText())){
            System.out.println("already added");
        }else {
            users.add(userName);
            userLIst.put(txtName.getText(),image);
            try {
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/client2ChatForm.fxml"))));
                stage.setTitle("Chat Room");
                stage.show();
                txtName.setText("");
                circle.setFill(null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void btnImageOnAction(ActionEvent actionEvent) {
        Window window = ((Node) (actionEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        actionEvent.consume();
        try {
            InputStream in = new FileInputStream(file);
            image = new Image(in);
            circle.setFill(new ImagePattern(image));

        } catch (FileNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
