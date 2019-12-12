package implementation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class NetworkComponent implements IComponent {
    //server port
    private final int PORT = 12345;
    //server socket
    private ServerSocket serverSocket;
    //socket
    private Socket socket;
    //input stream
    private DataInputStream inputStream;
    //output stream
    private DataOutputStream outputStream;

    //constructor only tries to initialize the port
    private NetworkComponent(){
        try {
            //create a new socket
            serverSocket = new ServerSocket(PORT);
            //set the socket timeout to 1 second
            serverSocket.setSoTimeout(1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //we listen for a incoming connection and return if we find a connection
    public boolean ListenForConnection(){
        //temporary socket
        Socket socket = null;
        try {
            //try and listen for a connection
            socket = serverSocket.accept();
            //if it doesnt throw a timeout exception
            this.socket = socket;
            //setup the streams
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (SocketTimeoutException e){
            //listen timeout return false
            return false;
        } catch (IOException e) {
            //unknown exception return false
            e.printStackTrace();
            //return false
            return false;
        }
        //if we walk trough everything succesfull return true
        return true;
    }

    @Override
    public void update(double deltaTime) {
        try {
            //while we have recieved messages
            while(inputStream.available() > 0){
                //read a message id
                short messageID = inputStream.readShort();
                //check what message we just recieved
                switch (messageID){
                    case 0: //ping message
                        break;
                    case 1: //connection message
                        break;
                    case 2: //disconnect message
                        break;
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public componentType getType() {
        return null;
    }

}
