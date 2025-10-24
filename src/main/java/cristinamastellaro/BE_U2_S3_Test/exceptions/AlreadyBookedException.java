package cristinamastellaro.BE_U2_S3_Test.exceptions;

public class AlreadyBookedException extends RuntimeException {
    public AlreadyBookedException(String title) {
        super("You alread reserved a seat for the event " + title);
    }
}
