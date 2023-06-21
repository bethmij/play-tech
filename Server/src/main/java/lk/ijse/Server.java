package lk.ijse;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int port = 3000;
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dataInputStream;
    public static void main(String[] args) {
            try {
                serverSocket = new ServerSocket(port);
                System.out.printf("Server Accepted!");


                while (true){
                    socket = serverSocket.accept();


                    dataInputStream = new DataInputStream(socket.getInputStream());
                    System.out.println("Employee "+dataInputStream.readUTF()+" Accepted!");


                }
            } catch (IOException e) {
                throw new RuntimeException(e);

            }

    }
}