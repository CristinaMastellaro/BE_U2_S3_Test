package cristinamastellaro.BE_U2_S3_Test.controllers;

import cristinamastellaro.BE_U2_S3_Test.entities.Event;
import cristinamastellaro.BE_U2_S3_Test.entities.Person;
import cristinamastellaro.BE_U2_S3_Test.entities.Role;
import cristinamastellaro.BE_U2_S3_Test.services.EventService;
import cristinamastellaro.BE_U2_S3_Test.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/me")
public class PersonController {
    @Autowired
    private PersonService pServ;
    @Autowired
    private EventService eServ;

    @PatchMapping("/partecipate/{idEvent}")
    public void partecipateToEvent(@AuthenticationPrincipal Person currentAuthenticatedUser, @PathVariable UUID idEvent) {
        // IntelliJ si è lamentato per aver trovate una circolarità... Questo è un modo per risolverlo
        Event event = eServ.findEventById(idEvent);
        pServ.partecipateToEvent(currentAuthenticatedUser, event);
    }

    @DeleteMapping("/partecipate/{idEvent}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelReservation(@AuthenticationPrincipal Person currentAuthenticatedUser, @PathVariable UUID idEvent) {
        Event event = eServ.findEventById(idEvent);
        pServ.deleteParticipation(currentAuthenticatedUser.getId(), event);
    }

    @PatchMapping("/{idUser}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Person changeRole(@PathVariable UUID idUser, @RequestParam Role newRole) {
        return pServ.changeRole(idUser, newRole);
    }

    @GetMapping("/reservations")
    public List<Event> getMyReservations(@AuthenticationPrincipal Person currentAuthUser) {
        return pServ.checkReservedEvents(currentAuthUser.getId());
    }
}
