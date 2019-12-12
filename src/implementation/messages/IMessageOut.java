package implementation.messages;

import java.io.DataOutputStream;
import java.io.IOException;

public interface IMessageOut {
    short getID();
    void send(DataOutputStream dos) throws IOException;
}
