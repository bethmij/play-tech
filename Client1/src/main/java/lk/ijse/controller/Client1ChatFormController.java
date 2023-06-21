package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.bytedeco.flycapture.FlyCapture2.H264Option;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.controller.Client1LoginFormController.userName;

public class Client1ChatFormController extends Thread implements Initializable {

    public JFXButton btnEmoji;
    public JFXButton btnCamera;
    public JFXButton btnSend;
    public TextField txt;
    //public HBox HBox;
    public VBox VBox;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    FileChooser fileChooser;
    File file;

    DataInputStream dataInputStream;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectSocket();
        /*try {
            socket = new Socket("localhost",3000);
            System.out.println("Connected"); // name
            reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
            this.start(); //****



        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void run() {
       try {
           while (true){
               String message = reader.readLine();
               String[] token = message.split("");
               String cmd = token[0];

               StringBuilder fullMsg = new StringBuilder();
               for (int i = 1; i <token.length ; i++) {
                   fullMsg.append(token[i]);
               }

               String[] msgToAr = message.split("");
               String string = "";
               for (int i = 0; i <msgToAr.length-1; i++) {
                   string += msgToAr[i+1]+"";
               }


               Text text = new Text(string);
               String firstChar = "";
               if(string.length()>3){
                   firstChar = string.substring(0,3);
               }

               if(firstChar.equalsIgnoreCase("img")){
                   //for image
                   System.out.println(fullMsg);
                   string = string.substring(3, string.length()-1);





                   File file = new File(string);
                   Image image = new Image(file.toURI().toString());

                   ImageView imageView = new ImageView(image);
                   imageView.setFitHeight(200);
                   imageView.setFitWidth(300);

                   HBox HBox = new HBox();
                   HBox.setAlignment(Pos.BOTTOM_RIGHT);

                   if (!cmd.equalsIgnoreCase(userName)){
                       System.out.println(fullMsg);
                       //HBox.setAlignment(Pos.TOP_LEFT);
                       //HBox.setPadding(new Insets(5,10,5,5));

                       VBox.setAlignment(Pos.TOP_LEFT);
                       HBox.setAlignment(Pos.CENTER_LEFT);


                       Text text1 = new Text(""+cmd+" :");
                       text1.setStyle("-fx-font-size: 20px");
                       TextFlow textFlow = new TextFlow(text1,imageView);
                       textFlow.setStyle("-fx-color:rgb(239,242,255);"
                               + "-fx-background-color: rgb(182,182,182);" +
                               "-fx-background-radius: 10px");
                       textFlow.setPadding(new Insets(5, 0, 5, 5));
                       HBox.getChildren().add(textFlow);
                       VBox.getChildren().add(HBox);

                   }else {
                       HBox.setAlignment(Pos.BOTTOM_RIGHT);
                       HBox.getChildren().add(imageView);
                       Text text1 = new Text(": Me ");
                       HBox.getChildren().add(text1);
                   }

                    Platform.runLater(() -> VBox.getChildren().addAll(HBox));
               }else {

               }

           }

       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public void connectSocket(){
        try {
            socket = new Socket("localhost",3000);
            System.out.println("Connected"); // name
            reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
            this.start(); //****

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSendOnAction(ActionEvent actionEvent) {
       /* String message = txt.getText();
        Text text = new Text(message);
        HBox.getChildren().add(text);*/

    }

    public void btnCameraOnAction(ActionEvent actionEvent) {

        Stage stage = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        this.file = fileChooser.showOpenDialog(stage);
        writer.println(userName+" "+"imag"+file.getPath());
    }

    public void btnEmojiOnAction(ActionEvent actionEvent) {
    }

    public void VBoxOnAction(MouseEvent mouseEvent) {
    }


}
