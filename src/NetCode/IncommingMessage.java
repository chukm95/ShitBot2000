package NetCode;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public interface IncommingMessage {
    short getID();
    void read(DataInputStream dis);
    void write(DataOutputStream dos);
}
