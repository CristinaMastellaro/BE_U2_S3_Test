package cristinamastellaro.BE_U2_S3_Test.services;

import cristinamastellaro.BE_U2_S3_Test.entities.Event;
import cristinamastellaro.BE_U2_S3_Test.entities.Person;
import cristinamastellaro.BE_U2_S3_Test.entities.Role;
import cristinamastellaro.BE_U2_S3_Test.exceptions.NotFoundException;
import cristinamastellaro.BE_U2_S3_Test.exceptions.UnauthorizedException;
import cristinamastellaro.BE_U2_S3_Test.payloads.EventDTO;
import cristinamastellaro.BE_U2_S3_Test.repositories.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

@Service
@Slf4j
public class EventService {
    @Autowired
    private EventRepository eRepo;
    @Autowired
    private PersonService pServ;

    public Event findEventById(UUID id) {
        return eRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Event saveEvent(EventDTO newEventInfo) {
        // Controlliamo che l'organizzatore dell'evento esista e sia un organizer o admin
        Person creator = pServ.findPersonById(newEventInfo.creatorId());
        if (creator.getRole().equals(Role.SIMPLEUSER))
            throw new UnauthorizedException("The user is not an organizer or admin and can't create new events!");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(newEventInfo.date(), formatter);
        Event newEvent = new Event(newEventInfo.title(), newEventInfo.description(), newEventInfo.place(), date, newEventInfo.maxNumPeople(), creator);

        eRepo.save(newEvent);

        log.info("The event " + newEvent.getTitle() + " has been correctly saved!");

        return newEvent;
    }

    public Event modifyEvent(UUID idOrganizer, UUID idEvent, EventDTO newInfo) {
        // Vediamo se l'evento e l'organizer esistono
        Event eventToModify = findEventById(idEvent);
        Person organizer = pServ.findPersonById(idOrganizer);

        // Assicuriamoci che chi è autenticato sia effettvamente l'organizzatore del viaggio da modificare
        if (eventToModify.getCreator() != organizer)
            throw new UnauthorizedException("Can't update an event that you haven't created");

        // Ora posso modificare l'evento
        if (eventToModify.getCreator().getId() != newInfo.creatorId())
            eventToModify.setCreator(pServ.findPersonById(newInfo.creatorId()));
        ;
        eventToModify.setTitle(newInfo.title());
        eventToModify.setDescription(newInfo.description());
        eventToModify.setPlace(newInfo.place());
        eventToModify.setMaxNumPeople(newInfo.maxNumPeople());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(newInfo.date(), formatter);
        eventToModify.setDate(date);

        eRepo.save(eventToModify);
        log.info("Event modified!");
        return eventToModify;
    }

    public void deleteEvent(UUID idOrganizer, UUID idEvent) {
        Event eventToDelete = findEventById(idEvent);

        // Assicuriamoci che chi è autenticato sia effettivamente l'organizzatore del viaggio da eliminare
        Person organizer = pServ.findPersonById(idOrganizer);
        if (eventToDelete.getCreator() != organizer)
            throw new UnauthorizedException("Can't delete an event that you haven't created");

        eRepo.delete(eventToDelete);
        log.info("Event cancelled!");
    }
}
