package shittynetcode.messages;

import shittynetcode.ShittyOutputStream;

import java.io.IOException;

public abstract class Message_out {
    private short id;

    protected Message_out(short id ) {
        this.id = id;
    }

    public void sendMessage(ShittyOutputStream stream){
        try {
            //dataOutputStream.writeShort(id);
            stream.writeShort(id);
            onSendMessage(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void onSendMessage(ShittyOutputStream stream) throws IOException{

    }
}
