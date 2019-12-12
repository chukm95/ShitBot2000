package util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class StringHelper {
    public static void sendString(String value, DataOutputStream dos) throws IOException {
        //check if the string doesnt equal null
        if(value == null)
            return;
        //check if string isnt empty
        if(value.length() < 1)
            return;
        //get the string in bytes
        byte[] data = value.getBytes(StandardCharsets.UTF_8);
        //send the size in bytes
        dos.writeInt(data.length);
        //then send every byte in data
        dos.write(data);
    }

    public static String recieveString(DataInputStream dis) throws IOException {
        //read the size
        int size = dis.readInt();
        //create byte array
        byte[] data = new byte[size];
        //read data
        for(int i = 0; i < size; i++){
            data[i] = dis.readByte();
        }
        //return string made of recieved bytes
        return new String(data, StandardCharsets.UTF_8);
    }
}
