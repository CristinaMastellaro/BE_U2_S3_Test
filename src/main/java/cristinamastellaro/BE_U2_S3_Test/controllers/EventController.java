package cristinamastellaro.BE_U2_S3_Test.controllers;

import cristinamastellaro.BE_U2_S3_Test.entities.Event;
import cristinamastellaro.BE_U2_S3_Test.entities.Person;
import cristinamastellaro.BE_U2_S3_Test.payloads.EventDTO;
import cristinamastellaro.BE_U2_S3_Test.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eServ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ORGANIZER')")
    public Event saveEvent(@RequestBody EventDTO eventPayload) {
        return eServ.saveEvent(eventPayload);
    }

    @PutMapping("/{idEvent}")
    public Event modifyEvent(@AuthenticationPrincipal Person currentAuthenticatedUser, @PathVariable UUID idEvent, @RequestBody EventDTO eventPayload) {
        return eServ.modifyEvent(currentAuthenticatedUser.getId(), idEvent, eventPayload);
    }
    
    @DeleteMapping("/{idEvent}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@AuthenticationPrincipal Person currentAuthenticatedUser, @PathVariable UUID idEvent) {
        eServ.deleteEvent(currentAuthenticatedUser.getId(), idEvent);
    }
}
