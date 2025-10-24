package cristinamastellaro.BE_U2_S3_Test.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorDTO(
        String message,
        LocalDateTime timeStamp,
        List<String> errors
) {
}
