package Implementation;

public class DeltaTime {
    //currenttime
    private long currentTime;
    //previous time
    private long previousTime;
    //deltaTime
    private double deltaTime;

    public DeltaTime(){
        currentTime = System.nanoTime();
        previousTime = currentTime;
        deltaTime = 0;
    }

    public void Update(){
        currentTime = System.nanoTime();
        deltaTime = ((double)(currentTime - previousTime) / 1000000000.0);
        previousTime = currentTime;
    }

    public double getDeltaTime(){
        return deltaTime;
    }
}
