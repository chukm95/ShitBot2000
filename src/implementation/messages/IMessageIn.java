package implementation.messages;

public interface IMessageIn {
    public enum MessageType{
        TIMEOUT,
        CONNECT,
        DISCONNECT,
        SENSOR_DATA_REQUEST
    }

    MessageType getType();
}
