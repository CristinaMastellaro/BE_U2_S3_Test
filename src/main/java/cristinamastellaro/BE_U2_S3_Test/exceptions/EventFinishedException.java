package cristinamastellaro.BE_U2_S3_Test.exceptions;

public class EventFinishedException extends RuntimeException {
    public EventFinishedException(String name) {
        super("The event " + name + " has already took place");
    }
}
