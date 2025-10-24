package cristinamastellaro.BE_U2_S3_Test.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("The email " + email + " is already used");
    }
}
