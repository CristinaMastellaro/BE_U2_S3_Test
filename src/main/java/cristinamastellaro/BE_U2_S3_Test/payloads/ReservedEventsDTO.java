package cristinamastellaro.BE_U2_S3_Test.payloads;

import cristinamastellaro.BE_U2_S3_Test.entities.Event;

import java.util.List;

public record ReservedEventsDTO(List<Event> events) {
}
