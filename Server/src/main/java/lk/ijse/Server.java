package lk.ijse;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static final int port = 3000;
    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dataInputStream;

    static List<ClientHandler> clientList = new ArrayList<>();
    public static void main(String[] args) {
            try {
                serverSocket = new ServerSocket(port);
                System.out.println("Server Accepted!\nWaiting for client...");


                while (true){
                    socket = serverSocket.accept();

                    dataInputStream = new DataInputStream(socket.getInputStream());
                    System.out.println("Employee "+dataInputStream.readUTF()+" Accepted!");

                    ClientHandler clientHandler = new ClientHandler(socket,dataInputStream.readUTF(),clientList);
                    clientList.add(clientHandler);
                    clientHandler.start();

                }
            } catch (IOException e) {
                throw new RuntimeException(e);

            }

    }
}