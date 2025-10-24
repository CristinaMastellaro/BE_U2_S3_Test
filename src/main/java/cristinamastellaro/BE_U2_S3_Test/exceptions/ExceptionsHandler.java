package cristinamastellaro.BE_U2_S3_Test.exceptions;

import cristinamastellaro.BE_U2_S3_Test.payloads.GeneralErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GeneralErrorDTO handleEmailAlreadyUsedExc(EmailAlreadyUsedException ex) {
        return new GeneralErrorDTO(ex.getMessage(), LocalDateTime.now());
    }
}
