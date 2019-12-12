package implementation.messages;

public class TimeOut implements IMessageIn {
    @Override
    public MessageType getType() {
        return MessageType.TIMEOUT;
    }
}
