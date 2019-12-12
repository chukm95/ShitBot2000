package implementation.messages;

public class Msg_In_Disconnect implements IMessageIn {
    @Override
    public MessageType getType() {
        return MessageType.DISCONNECT;
    }
}
