package cristinamastellaro.BE_U2_S3_Test.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PersonPayload(
        String name,
        String surname,
        @NotBlank(message = "The email cannot be blank")
        @Email
        String email,
        @NotBlank(message = "The password cannot be blank")
        @Size(min = 4, message = "The password must have at least 4 characters")
        String password
) {
}
