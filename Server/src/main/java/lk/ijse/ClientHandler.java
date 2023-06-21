package lk.ijse;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    Socket socket;
    String clientName;
    List<ClientHandler> clientList;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    BufferedReader reader;
    PrintWriter writer;


    public ClientHandler(Socket socket, String name , List<ClientHandler> clientList ) throws IOException {
        this.socket = socket;
        this.clientName = name;
        this.clientList = clientList;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream() , true);
    }

    @Override
    public void run() {
        try {
            //dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //dataInputStream = new DataInputStream(socket.getInputStream());

            while (true){
                String message = dataInputStream.readUTF();

                if(message.equalsIgnoreCase("exit")){
                    break;
                }

                for (ClientHandler clientHandler : clientList) {
                    clientHandler.writer.println(message);
                    System.out.println(message);
                   /* clientHandler.dataOutputStream.writeUTF(message);
                    dataOutputStream.flush();*/
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
