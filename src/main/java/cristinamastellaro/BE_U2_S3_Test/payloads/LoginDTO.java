package cristinamastellaro.BE_U2_S3_Test.payloads;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "The email must not be blank")
        String email,
        @NotBlank(message = "The password must not be blank")
        String password
) {
}
