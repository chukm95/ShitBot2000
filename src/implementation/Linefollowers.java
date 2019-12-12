package implementation;

import hardware.linefollower;

public class Linefollowers implements IComponent {

    private int leftPin;
    private int midPin;
    private int rightPin;
    private linefollower linefollowerLeft;
    private linefollower linefollowermid;
    private linefollower linefollowerright;
    private boolean detectLineLeft;
    private boolean detectLineMid;
    private boolean detectLineRight;

    public Linefollowers(int leftPin, int midPin, int rightPin) {
        this.leftPin = leftPin;
        this.midPin = midPin;
        this.rightPin = rightPin;

        linefollowerLeft = new linefollower(leftPin);
        linefollowermid = new linefollower(midPin);
        linefollowerright = new linefollower(rightPin);
    }

    public boolean isDetectLineLeft() {
        return detectLineLeft;
    }

    public boolean isDetectLineMid() {
        return detectLineMid;
    }

    public boolean isDetectLineRight() {
        return detectLineRight;
    }

    @Override
    public void update(double deltaTime) {
        detectLineLeft = linefollowerLeft.detectLine();
        detectLineMid = linefollowermid.detectLine();
        detectLineRight = linefollowerright.detectLine();
    }

    @Override
    public componentType getType() {
        return componentType.LINEFOLLOWERS;
    }
}
