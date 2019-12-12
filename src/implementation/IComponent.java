package implementation;

public interface IComponent {

    public enum componentType{
        ULTRASOON,
        LINEFOLLOWERS,
        MOTORS,
        NETWORK
    }

    void update(double deltaTime);

    componentType getType();

}
