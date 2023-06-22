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

import static lk.ijse.controller.Client2LoginFormController.userName;


public class Client2ChatFormController extends Thread implements Initializable {

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
    DataOutputStream dataOutputStream;

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
               System.out.println("3");
               String cmd = token[0];

               System.out.println("cmd : "+cmd);

               StringBuilder fullMsg = new StringBuilder();
               for (int i = 1; i <token.length ; i++) {
                   fullMsg.append(token[i]);
               }
               System.out.println("4");

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
                   imageView.setFitHeight(100);
                   imageView.setFitWidth(100);

                   HBox hBox = new HBox(10);
                   hBox.setAlignment(Pos.BOTTOM_RIGHT);

                   if (!cmd.equalsIgnoreCase(userName + ":")){
                       System.out.println(fullMsg);
                       //HBox.setAlignment(Pos.TOP_LEFT);
                       //HBox.setPadding(new Insets(5,10,5,5));

                       VBox.setAlignment(Pos.BOTTOM_LEFT);
                       hBox.setAlignment(Pos.CENTER_LEFT);


                       Text text1 = new Text(" "+cmd+" :");
                       text1.setStyle("-fx-font-size: 20px");
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
                       text1.setStyle("-fx-font-size: 20px");
                       /*TextFlow textFlow = new TextFlow(text1,imageView);
                       textFlow.setStyle("-fx-color:rgb(239,242,255);"
                               + "-fx-background-color: rgb(182,182,182);" +
                               "-fx-background-radius: 10px");
                       textFlow.setPadding(new Insets(5, 0, 5, 5));*/
                       hBox.getChildren().add(text1);
                   }

                    Platform.runLater(() -> VBox.getChildren().addAll(hBox));
               }else {
                   TextFlow tempText = new TextFlow();

                   if (!cmd.equalsIgnoreCase(userName + ":")) {
                       Text name = new Text(cmd + "");
                       name.getStyleClass().add("name");
                       tempText.getChildren().add(name);
                   }

                   tempText.getChildren().add(text);
                   tempText.setMaxWidth(120);

                   TextFlow textFlow = new TextFlow(tempText);
                   textFlow.setStyle("-fx-background-color:#ff6b81;" + "-fx-background-radius: 20px;" + "-fx-font-size: 17px;");
                   textFlow.setPadding(new Insets(5, 10, 5, 10));

                   HBox hBox = new HBox(10);
                   hBox.setPadding(new Insets(5));

                   if (!cmd.equalsIgnoreCase(userName + ":")) {
                       System.out.println("cmd : "+cmd);
                       VBox.setAlignment(Pos.TOP_LEFT);
                       hBox.setAlignment(Pos.CENTER_LEFT);
                       hBox.getChildren().add(textFlow);
                   } else {
                       System.out.println("cmd : "+cmd);
                       Text text1 = new Text(fullMsg + " :Me");
                       TextFlow textFlow1 = new TextFlow(text1);
                       hBox.setAlignment(Pos.BOTTOM_RIGHT);
                       hBox.getChildren().add(textFlow1);
                       textFlow1.setStyle("-fx-background-color:#7bed9f;" + "-fx-background-radius: 20px;" + "-fx-font-size: 17px;");
                       textFlow1.setPadding(new Insets(5, 10, 5, 10));
                   }
                   Platform.runLater(() -> VBox.getChildren().addAll(hBox));
               }

                   System.out.println(fullMsg);

                   if (cmd.equalsIgnoreCase(userName + ":")) {
                       continue;
                   } else if (fullMsg.toString().equalsIgnoreCase("bye")) {
                       break;
                   }
               }
                   reader.readLine();
                   writer.close();
                   socket.close();


       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public void connectSocket(){
        try {
            socket = new Socket("localhost",3000);

            /*dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(userName);
            dataOutputStream.flush();*/

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
       writer.println(userName+" :"+message);

       HBox hBox = new HBox();
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
       VBox.getChildren().add(hBox);
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
            this.file = fileChooser.showOpenDialog(stage);
            writer.println(userName + " " + "imag" + file.getPath());
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
}
