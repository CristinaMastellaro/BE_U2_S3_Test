package cristinamastellaro.BE_U2_S3_Test.payloads;

public record UserPayload(
        String name,
        String surname,
//        @NotBlank(message = "")
//        @Email
        String email,
//        @NotBlank
//        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$")
        String password
) {
}
