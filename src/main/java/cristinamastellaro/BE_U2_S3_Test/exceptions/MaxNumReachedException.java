package cristinamastellaro.BE_U2_S3_Test.exceptions;

public class MaxNumReachedException extends RuntimeException {
    public MaxNumReachedException(String title) {
        super("The event " + title + " has already reached the maximum number of participants");
    }
}
