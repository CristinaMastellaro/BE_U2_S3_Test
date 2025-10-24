package cristinamastellaro.BE_U2_S3_Test.payloads;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateEventDTO(
        @NotBlank(message = "The title can't be blank")
        String title,
        String description,
        @NotBlank(message = "The place can't be blank")
        String place,
        @NotBlank(message = "The date can't be blank")
        String date,
        @NotBlank(message = "The maxNumPeople can't be blank")
        int maxNumPeople,
        @NotBlank(message = "The creatorId can't be blank")
        UUID creatorId) {
}
