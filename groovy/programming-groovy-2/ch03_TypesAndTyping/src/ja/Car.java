package ja;

public class Car implements Cloneable {
    int year;
    Engine engine;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Car cloned = (Car) super.clone();
        cloned.engine = (Engine) engine.clone();
        return cloned;
    }
}
