package shittynetcode;

import implementation.IComponent;
import shittynetcode.messages.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ShittyNetServer implements IComponent {
    private static ShittyNetServer instance;

    public static ShittyNetServer getInstance(){
        if (instance == null){
            instance = new ShittyNetServer();
        }
        return instance;
    }

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream dataInputStream;
    private ShittyOutputStream stream;
    private boolean isConnected;
    private int lastpingRecieved;
    private int lastPingSended;

    private ArrayList<Message_in> messagesIn;

    public boolean isConnected() {
        return isConnected;
    }

    private ShittyNetServer() {
        try {
            serverSocket = new ServerSocket(12345);
            serverSocket.setSoTimeout(1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        messagesIn = new ArrayList<>();
    }

    public void listenForConnection(){
        if(!isConnected) {
            try {
                socket = serverSocket.accept();
                System.out.println("Connection accepted");
                dataInputStream = new DataInputStream(socket.getInputStream());
                stream = new ShittyOutputStream(socket.getOutputStream());
                isConnected = true;
            } catch (SocketTimeoutException e) {
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(double deltaTime){
        if (isConnected){
            //send ping
            sendPing(deltaTime);
            recieveMessages();
            checkTimeout(deltaTime);
        }
    }

    private void sendPing(double deltaTime){
        lastPingSended += (int)(deltaTime * 1000.0);
        try {
            if(lastPingSended > 100) {
                stream.writeShort((short) 0);
                stream.flush();
                lastPingSended = 0;
            }
        } catch (IOException e) {
        }
    }

    private void recieveMessages(){
        try {
            if (dataInputStream.available() > 0){
                short msgId = dataInputStream.readShort();

                switch (msgId){
                    case 0://ping
                        lastpingRecieved = 0;
                        break;
                    case 1://sensor data request
                        System.out.println("Sensordata requested");
                        Msg_In_SensorDataRequest msg = new Msg_In_SensorDataRequest();
                        msg.readMessage(dataInputStream);
                        messagesIn.add(msg);
                        break;
                    case 2: //forward
                        System.out.println("Forward");
                        messagesIn.add(new Msg_in_forward());
                        break;
                    case 3 : //left
                        System.out.println("left");
                        messagesIn.add(new Msg_In_left());
                        break;
                    case 4: //right
                        System.out.println("right");
                        messagesIn.add(new Msg_in_right());
                        break;
                    case 5: //180
                        System.out.println("Oneeighty");
                        messagesIn.add(new Msg_In_180());
                        break;
                    case 6: // infine eight
                        System.out.println("Infinite Eight");
                        messagesIn.add(new Msg_In_InfinitEight());
                        break;
                    default:
                        System.out.println("unkown message");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message_out msg){
        msg.sendMessage(stream);
        try {
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkTimeout(double deltatime){
        lastpingRecieved += (int)(deltatime * 1000);
        if (lastpingRecieved > 5000){
            isConnected = false;

            try {
                dataInputStream.close();
                stream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            dataInputStream = null;
            stream = null;
            socket = null;
            messagesIn.add(new Msg_In_Timeout());
            lastpingRecieved = 0;
            lastPingSended = 0;

        }
    }

    public Message_in[] getMessagesIn() {
        Message_in[] msgs = messagesIn.toArray(new Message_in[messagesIn.size()]);
        messagesIn.clear();
        return msgs;
    }

    @Override
    public componentType getType() {
        return componentType.NETWORK;
    }
}