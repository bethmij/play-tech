package lk.ijse;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.List;

public class ClientHandler extends Thread {
    Socket socket;
    //String clientName;
    List<ClientHandler> clientList;
    //DataOutputStream dataOutputStream;
    //DataInputStream dataInputStream;
    BufferedReader reader;
    PrintWriter writer;


    public ClientHandler(Socket socket, List<ClientHandler> clientList ) throws IOException {
        this.clientList = clientList;
        this.socket = socket;
        //this.clientName = name;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream() , true);
    }

    @Override
    public void run() {
        try {
            //dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //dataInputStream = new DataInputStream(socket.getInputStream());
            String message;
            while ((message=reader.readLine()) != null){
               // message = dataInputStream.readUTF();

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
