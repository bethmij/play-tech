package lk.ijse;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    Socket socket;
    String clientName;
    List<ClientHandler> clientList;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;


    public ClientHandler(Socket socket, String name , List<ClientHandler> clientList ){
        this.socket = socket;
        this.clientName = name;
        this.clientList = clientList;
    }

    @Override
    public void run() {
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());

            while (true){
                String message = dataInputStream.readUTF();

                if(message.equalsIgnoreCase("exit")){
                    break;
                }

                for (ClientHandler clientHandler : clientList) {
                    clientHandler.dataOutputStream.writeUTF(message);
                    dataOutputStream.flush();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
