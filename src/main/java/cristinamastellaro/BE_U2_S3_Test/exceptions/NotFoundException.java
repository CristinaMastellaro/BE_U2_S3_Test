package cristinamastellaro.BE_U2_S3_Test.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("The id " + id + " has not been found");
    }
}
