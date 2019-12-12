package implementation;

import implementation.messages.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Stack;

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
    //ping time
    private double pingTime;
    //are we connected
    private boolean isConnected;
    //the queue we put messages in
    private Stack<IMessageIn> messageStack;

    //constructor only tries to initialize the port
    private NetworkComponent(){
        try {
            //create a new socket
            serverSocket = new ServerSocket(PORT);
            //set the socket timeout to 1 second
            serverSocket.setSoTimeout(1000);
            //set is connected to false
            isConnected = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //init messageQueue
        messageStack = new Stack<>();
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
            //set is connected to true
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
        //check if we are connected
        if(!isConnected)
            //if not exit this method
            return;

        //add deltatime to pingtime
        pingTime += deltaTime;

        try {
            //while we have recieved messages
            while(inputStream.available() > 0){
                //read a message id
                short messageID = inputStream.readShort();
                //check what message we just recieved
                switch (messageID){
                    case 0: //ping message
                        pingTime = 0;
                        break;
                    case 1: //connection message
                        messageStack.push(new Msg_In_Connect());
                        break;
                    case 2: //disconnect message
                        messageStack.push(new Msg_In_Disconnect());
                        break;
                    case 3: //sensor data requested
                        messageStack.push(new Msg_In_SensorDataRequest());
                    default:
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //check if pingtime since last ping is bigger then 2 seconds
        if(pingTime > 5)
            //if it is more than 2 seconds timeout
            timeOut();
    }

    public void sendMessage(IMessageOut message){
        //check if we are connected
        if(isConnected){
            //if we are connected send the message
            try {
                message.send(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public IMessageIn pollMessage(){
        return messageStack.pop();
    }

    private void timeOut(){
        try {
            //close streams
            inputStream.close();
            outputStream.close();
            //close socket
            socket.close();
            //set everything to null
            inputStream = null;
            outputStream = null;
            socket = null;
            //set is connected to false
            isConnected = false;
            //add timeout message
            messageStack.add(new TimeOut());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public componentType getType() {
        return componentType.NETWORK;
    }

}
