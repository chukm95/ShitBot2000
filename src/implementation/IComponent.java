package implementation;

public interface IComponent {

    public enum componentType{
        ULTRASOON,
        LINEFOLLOWERS,
        MOTORS
    }

    void update(double deltaTime);

    componentType getType();

}
