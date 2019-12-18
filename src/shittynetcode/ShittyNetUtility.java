package shittynetcode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ShittyNetUtility {

    public static void writeBoolean(boolean value, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(value?1:0);
    }

    public static boolean readBoolean(DataInputStream dataInputStream) throws IOException {
        return (dataInputStream.readByte() == 1? true : false);
    }

    public static void writeString(String value, DataOutputStream dataOutputStream) throws IOException {
        if (value.isEmpty()){
            return;
        }
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
    }

    public static String readString(DataInputStream dataInputStream) throws IOException {
        byte[] data = new byte[dataInputStream.readInt()];
        for (int i = 0; i < data.length; i++){
            data[i] = dataInputStream.readByte();
        }
        return new String(data, StandardCharsets.UTF_8);
    }
}
