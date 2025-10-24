package cristinamastellaro.BE_U2_S3_Test.payloads;

import java.util.UUID;

public record EventDTO(
        String title,
        String description,
        String place,
        String date,
        int maxNumPeople,
        UUID creatorId
) {
}
