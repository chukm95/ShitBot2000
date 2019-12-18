package shittynetcode.messages;

import shittynetcode.ShittyOutputStream;

import java.io.IOException;

public class Msg_Out_SensorData extends Message_out {

    private int distance;
    private boolean lineLeft;
    private boolean lineMid;
    private boolean lineRight;
    private int moterLeft;
    private int moterRight;


    public Msg_Out_SensorData(int distance, boolean lineLeft,boolean lineMid, boolean lineRight,int moterLeft, int moterRight) {
        super(Short.parseShort("1"));
        this.distance = distance;
        this.lineLeft = lineLeft;
        this.lineMid = lineMid;
        this.lineRight = lineRight;
        this.moterLeft = moterLeft;
        this.moterRight = moterRight;
    }

    @Override
    protected void onSendMessage(ShittyOutputStream stream) throws IOException {

        stream.writeInt(distance);
        stream.writeBoolean(lineLeft);
        stream.writeBoolean(lineMid);
        stream.writeBoolean(lineRight);
        stream.writeInt(moterLeft);
        stream.writeInt(moterRight);

//        System.out.println("distance: " + distance);
//        System.out.println("lineleft: " + lineLeft);
//        System.out.println("linemid: " + lineMid);
//        System.out.println("lineright: " + lineRight);
//        System.out.println("moterleft: " + moterLeft);
//        System.out.println("moterright: " + moterRight);

    }
}
