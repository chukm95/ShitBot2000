package implementation.messages;

public class Msg_In_Connect implements IMessageIn {
    @Override
    public MessageType getType() {
        return MessageType.CONNECT;
    }
}
