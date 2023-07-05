package lk.ijse.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BackgroundController {
    Client1ChatFormController client1 = new Client1ChatFormController();

    public void blueOnAction(MouseEvent mouseEvent) {
        Image image = new Image("D:\\IJSE\\Working Projects\\Chat Application\\Client1\\src\\main\\resources\\Assets\\background\\pexels-brakou-abdelghani-1723637.jpg");
        client1.setImage(image);
    }

    public void whiteOnAction(MouseEvent mouseEvent) {
    }

    public void roseOnAction(MouseEvent mouseEvent) {
    }

    public void blackOnAction(MouseEvent mouseEvent) {
    }

    public void skyOnAction(MouseEvent mouseEvent) {
    }

    public void flowerOnAction(MouseEvent mouseEvent) {
    }

    public static Image setImage(Image image){
        return image;
    }
}
