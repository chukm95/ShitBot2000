package implementation.messages;

public class Msg_In_TimeOut implements IMessageIn {
    @Override
    public MessageType getType() {
        return MessageType.TIMEOUT;
    }
}
