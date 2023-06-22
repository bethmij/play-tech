package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
//import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.controller.util.OpenView;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import static lk.ijse.controller.Client1LoginFormController.userName;
import static lk.ijse.controller.Client1LoginFormController.users;

public class Client1ChatFormController extends Thread implements Initializable {

    public JFXButton btnEmoji;
    public JFXButton btnCamera;
    public JFXButton btnSend;
    public TextField txt;
    //public HBox HBox;
    public VBox VBox;
    public Label lblName;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    FileChooser fileChooser;
    File filePath;

    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblName.setText(userName);
        connectSocket();
    }

    @Override
    public void run() {

       try {
           while (true){

               String message = reader.readLine();
               System.out.println("message "+message);
               //char character = ':';

               String[] parts = message.split("\\Q" + "::" + "\\E");
               String beforeCharacter = parts[0];
               //System.out.println("beforeCharacter "+beforeCharacter);

               String afterCharacter = parts[1];
               //System.out.println("afterCharacter "+afterCharacter);

               /*String[] token = message.split("");
               String cmd = token[0];


               StringBuilder fullMsg = new StringBuilder();
               for (int i = 1; i <token.length ; i++) {
                   fullMsg.append(token[i]);
               }

               String[] msgToAr = message.split("");
               String string = "";
               for (int i = 0; i <msgToAr.length-1; i++) {
                   string += msgToAr[i+1]+" ";
               }


               Text text = new Text(string);
               String firstChar = "";

               if(string.length()>3){
                   firstChar = string.substring(0,3);
               }*/
              // System.out.println("afterCharacter "+afterCharacter+" "+afterCharacter.startsWith("img")  );
               if(afterCharacter.startsWith("img")){
                   //for image
                   String[] part = message.split("\\Q" + "img" + "\\E");
                   String path = part[1];
                   //System.out.println("path "+path);

                   //string = string.substring(3, string.length()-1);

                   //File file = new File(path);
                   //Image image = new Image(file.toURI().toString());
                   Image image = new Image(path, 100, 100, true, true);

                   ImageView imageView = new ImageView(image);
                   imageView.setFitHeight(120);
                   imageView.setFitWidth(120);

                   HBox hBox = new HBox(10);
                   hBox.setAlignment(Pos.BOTTOM_RIGHT);

                   if (!lblName.getText().equals(beforeCharacter)){
                       //System.out.println("not "+lblName.getText());
                       //HBox.setAlignment(Pos.TOP_LEFT);
                       //HBox.setPadding(new Insets(5,10,5,5));

                       VBox.setAlignment(Pos.BOTTOM_LEFT);
                       hBox.setAlignment(Pos.CENTER_LEFT);


                       Text text1 = new Text(" "+beforeCharacter+" : ");
                       text1.setStyle("-fx-font-size: 15px");
                       /*//TextFlow textFlow = new TextFlow(text1);
                       text1.setStyle("-fx-color:rgb(239,242,255);"
                               + "-fx-background-color: #ff6b81;" +
                               "-fx-background-radius: 20px");*/
                      /* TextFlow textFlow = new TextFlow(text1,imageView);
                       textFlow.setStyle("-fx-color:rgb(239,242,255);"
                               + "-fx-background-color: rgb(182,182,182);" +
                               "-fx-background-radius: 10px");
                       textFlow.setPadding(new Insets(5, 0, 5, 5));*/
                       hBox.getChildren().add(text1);
                       hBox.getChildren().add(imageView);

                   }else {
                       /*HBox.setAlignment(Pos.BOTTOM_RIGHT);
                       HBox.getChildren().add(imageView);
                       Text text1 = new Text(": Me ");
                       HBox.getChildren().add(text1);*/
                       hBox.setAlignment(Pos.BOTTOM_RIGHT);
                       hBox.getChildren().add(imageView);
                       Text text1 = new Text(" : Me");
                       text1.setStyle("-fx-font-size: 15px");
                       /*TextFlow textFlow = new TextFlow(text1);
                       textFlow.setStyle("-fx-color:rgb(239,242,255);"
                               + "-fx-background-color: rgb(15,125,242);" +
                               "-fx-background-radius: 20px");*/
                       /*TextFlow textFlow = new TextFlow(text1,imageView);
                       textFlow.setStyle("-fx-color:rgb(239,242,255);"
                               + "-fx-background-color: rgb(182,182,182);" +
                               "-fx-background-radius: 10px");
                       textFlow.setPadding(new Insets(5, 0, 5, 5));*/
                       hBox.getChildren().add(text1);
                   }

                    Platform.runLater(() -> VBox.getChildren().addAll(hBox));
               }else {
                   /*text.setFill(Color.WHITE);
                   text.getStyleClass().add("message");*/
                   TextFlow tempText = new TextFlow();

                   HBox hBox = new HBox(10);
                   if (!lblName.getText().equals(beforeCharacter)) {
                       System.out.println("not " + lblName.getText());
                       Text name = new Text(beforeCharacter + " : ");
                       //name.getStyleClass().add("name");
                       tempText.getChildren().add(name);

                       Text msg = new Text(afterCharacter);
                       tempText.getChildren().add(msg);
                       tempText.setMaxWidth(200);

                  /* TextFlow textFlow = new TextFlow(tempText);
                   textFlow.setStyle("-fx-background-color:#ff6b81;" + "-fx-background-radius: 20px;" + "-fx-font-size: 17px;");
                   textFlow.setPadding(new Insets(5, 10, 5, 10));*/

                       // tempText = new TextFlow(tempText);
                       tempText.setStyle("-fx-background-color:#ff6b81;" + "-fx-background-radius: 20px;" + "-fx-font-size: 17px;");
                       tempText.setPadding(new Insets(5, 10, 5, 10));


                       hBox.setPadding(new Insets(5));

                        /*tempText.getStyleClass().add("tempFlowFlipped");
                        textFlow.getStyleClass().add("textFlowFlipped");*/
                       VBox.setAlignment(Pos.TOP_LEFT);
                       hBox.setAlignment(Pos.CENTER_LEFT);
                       hBox.getChildren().add(tempText);

                   } else {/*
                       text.setFill(Color.WHITE);
                       tempText.getStyleClass().add("tempFlow");
                       textFlow.getStyleClass().add("textFlow");*/
                       Text text1 = new Text(afterCharacter + " : Me");
                       TextFlow textFlow1 = new TextFlow(text1);
                       hBox.setAlignment(Pos.BOTTOM_RIGHT);
                       hBox.getChildren().add(textFlow1);
                       textFlow1.setStyle("-fx-background-color:#7bed9f;" + "-fx-background-radius: 20px;" + "-fx-font-size: 17px;");
                       textFlow1.setPadding(new Insets(5, 10, 5, 10));
                   }
                   //hBox.getStyleClass().add("hBox");
                   Platform.runLater(() -> VBox.getChildren().addAll(hBox));
               }

                 //  System.out.println(fullMsg);

                   if (!lblName.getText().equals(beforeCharacter)) {
                       continue;
                   } else if (afterCharacter.equalsIgnoreCase("bye")) {
                       break;
                   }
               }
                   //reader.readLine();
                   //writer.close();
                   //socket.close();


       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public void connectSocket(){
        try {
            socket = new Socket("localhost",3000);
            System.out.println(userName);
            System.out.println(users);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(userName);
            dataOutputStream.flush();

            System.out.println(userName+" Connected"); // name

            reader = new BufferedReader( new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(),true);
            this.start(); //****

        } catch (IOException e) {
            System.out.println(e);;
        }
    }

    public void btnSendOnAction(ActionEvent actionEvent) {

       String message = txt.getText();
       writer.println(lblName.getText()+"::"+message);

      /* HBox hBox = new HBox();
       hBox.setAlignment(Pos.CENTER_RIGHT);
       hBox.setPadding(new Insets(5,5,5,10));

       Text text = new Text("Me : "+message);
       text.setStyle("-fx-font-size: 15px");
       TextFlow textFlow = new TextFlow(text);
       textFlow.setStyle("-fx-color:rgb(239,242,255);"
               + "-fx-background-color: rgb(15,125,242);" +
               "-fx-background-radius: 20px");
       textFlow.setPadding(new Insets(5,10,5,10));
       text.setFill(Color.color(0.934, 0.945, 0.996));
       hBox.getChildren().add(textFlow);
       VBox.getChildren().add(hBox);*/
       writer.flush();
       txt.setText("");
       if(message.equalsIgnoreCase("bye")){
           System.exit(0);
       }

    }

    public void btnCameraOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image");
            this.filePath = fileChooser.showOpenDialog(stage);
            writer.println(lblName.getText()+ "::" + "img" + filePath.getPath());
            writer.flush();
        }catch (NullPointerException e){
            System.out.println(e);
            System.out.println("Image not Selected!");
        }
    }

    public void btnEmojiOnAction(ActionEvent actionEvent) {
    }

    public void VBoxOnAction(MouseEvent mouseEvent) {
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        OpenView.openView("client1LoginForm");
    }

    @FXML
    void crazyOnAction(MouseEvent event) {

    }

    @FXML
    void cuteOnAction(MouseEvent event) {

    }

    @FXML
    void ghostOnAction(MouseEvent event) {

    }

    @FXML
    void heartOnAction(MouseEvent event) {

    }

    @FXML
    void holoOnAction(MouseEvent event) {

    }

    @FXML
    void kissOnAction(MouseEvent event) {

    }

    @FXML
    void laughOnAction(MouseEvent event) {

    }

    @FXML
    void lovelyOnAction(MouseEvent event) {

    }

    @FXML
    void sadOnAction(MouseEvent event) {

    }

    @FXML
    void shockOnAction(MouseEvent event) {

    }

    @FXML
    void smileOnAction(MouseEvent event) {

    }

    @FXML
    void sunGlassOnAction(MouseEvent event) {

    }

    @FXML
    void winkOnAction(MouseEvent event) {

    }
}
