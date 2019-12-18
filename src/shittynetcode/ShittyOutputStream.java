package shittynetcode;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class ShittyOutputStream {

    private final int   SIZE_SHORT  = 2,
                        SIZE_INT    = 4,
                        SIZE_LONG   = 8;


    private OutputStream outputStream;

    public ShittyOutputStream(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void writeBoolean(boolean value) throws IOException {
        byte[] data = new byte[1];
        data[0] = value? (byte)1 : (byte)0;
        outputStream.write(data);
    }

    public void writeShort(short value) throws IOException{
        byte[] data = new byte[SIZE_SHORT];
        for(int i = 0; i < SIZE_SHORT;  i++)
            data[i] = (byte)(value >>> (SIZE_SHORT - i -1) * 8);
        outputStream.write(data);
    }

    public void writeInt(int value) throws IOException{
        byte[] data = new byte[SIZE_INT];
        for(int i = 0; i < SIZE_INT;  i++)
            data[i] = (byte)(value >>> (SIZE_INT - i - 1) * 8);
        outputStream.write(data);
    }

    public void writeLong(long value) throws IOException
    {
        byte[] data = new byte[SIZE_LONG];
        for(int i = 0; i < SIZE_LONG;  i++)
            data[i] = (byte)(value >>> (SIZE_LONG - i -1) * 8);
        outputStream.write(data);
    }

    public void writeString(String value) throws IOException{
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        writeInt(data.length);
        outputStream.write(data);
    }

    public void flush() throws IOException {
        outputStream.flush();
    }

    public void close() throws IOException {
        outputStream.close();
    }

}
