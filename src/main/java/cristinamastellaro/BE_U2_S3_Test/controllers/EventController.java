package cristinamastellaro.BE_U2_S3_Test.controllers;

import cristinamastellaro.BE_U2_S3_Test.entities.Event;
import cristinamastellaro.BE_U2_S3_Test.payloads.EventDTO;
import cristinamastellaro.BE_U2_S3_Test.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eServ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Event saveEvent(@RequestBody EventDTO eventPayload) {
        return eServ.saveEvent(eventPayload);
    }
}
