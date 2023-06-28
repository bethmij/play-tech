package lk.ijse;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    Socket socket;
    List<ClientHandler> clientList;
    BufferedReader reader;
    PrintWriter writer;


    public ClientHandler(Socket socket, List<ClientHandler> clientList ) throws IOException {
        this.clientList = clientList;
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream() , true);
    }

    @Override
    public void run() {
        try {

            String message;
            while ((message=reader.readLine()) != null){

                if(message.equalsIgnoreCase("exit")){
                    break;
                }

                for (ClientHandler clientHandler : clientList) {
                    clientHandler.writer.println(message);;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);

        }finally {
            try {
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
    }
}
