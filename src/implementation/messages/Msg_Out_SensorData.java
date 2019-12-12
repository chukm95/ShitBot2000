package implementation.messages;

import java.io.DataOutputStream;
import java.io.IOException;

public class Msg_Out_SensorData implements IMessageOut{

    private int distance;
    private int motorSpeedLeft;
    private int motorSpeedRight;
    private boolean lineSensorLeft;
    private boolean lineSensorMiddle;
    private boolean lineSensorRight;

    public Msg_Out_SensorData(int distance, int motorSpeedLeft, int motorSpeedRight, boolean lineSensorLeft, boolean lineSensorMiddle, boolean lineSensorRight){
        this.distance = distance;
        this.motorSpeedLeft = motorSpeedLeft;
        this.motorSpeedRight = motorSpeedRight;
        this.lineSensorLeft = lineSensorLeft;
        this.lineSensorMiddle = lineSensorMiddle;
        this.lineSensorRight = lineSensorRight;
    }

    @Override
    public short getID() {
        return 0;
    }

    @Override
    public void send(DataOutputStream dos) throws IOException {
        dos.writeInt(distance);
        dos.writeInt(motorSpeedLeft);
        dos.writeInt(motorSpeedRight);
        dos.writeBoolean(lineSensorLeft);
        dos.writeBoolean(lineSensorMiddle);
        dos.writeBoolean(lineSensorRight);
    }
}
