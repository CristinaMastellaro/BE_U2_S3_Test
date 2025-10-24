package cristinamastellaro.BE_U2_S3_Test.exceptions;

public class NotBookedException extends RuntimeException {
    public NotBookedException(String title) {
        super("You haven't reserved a seat for the event " + title);
    }
}
