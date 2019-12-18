package shittynetcode.messages;

public class Msg_In_Timeout extends Message_in {
    public Msg_In_Timeout() {
        messageType = MessageTypes.TIMEOUT;
    }
}
