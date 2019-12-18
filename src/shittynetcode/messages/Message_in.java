package shittynetcode.messages;

import java.io.DataInputStream;

public abstract class Message_in  {
    public enum MessageTypes{
        SENSORDATAREQUEST,
        TIMEOUT,
        FORWARD,
        LEFT,
        RIGHT,
        OneEighty,
        INFINETEIGHT
    }

    protected MessageTypes messageType;

    public MessageTypes getMessageType(){
        return messageType;
    }

    public void readMessage(DataInputStream dataInputStream){

    }
}
