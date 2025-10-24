package cristinamastellaro.BE_U2_S3_Test.payloads;

import jakarta.validation.constraints.NotBlank;

public record EventDTO(
        @NotBlank(message = "The title must not be blank")
        String title,
        String description,
        @NotBlank(message = "The description must not be blank")
        String place,
        @NotBlank(message = "The date must not be blank")
        String date,
        @NotBlank(message = "The maxNumPeople must not be blank")
        int maxNumPeople
) {
}
