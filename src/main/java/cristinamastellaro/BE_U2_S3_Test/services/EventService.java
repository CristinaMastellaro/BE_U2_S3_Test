package cristinamastellaro.BE_U2_S3_Test.services;

import cristinamastellaro.BE_U2_S3_Test.entities.Event;
import cristinamastellaro.BE_U2_S3_Test.entities.Person;
import cristinamastellaro.BE_U2_S3_Test.payloads.EventDTO;
import cristinamastellaro.BE_U2_S3_Test.repositories.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
@Slf4j
public class EventService {
    @Autowired
    private EventRepository eRepo;
    @Autowired
    private PersonService pServ;

    public Event saveEvent(EventDTO newEventInfo) {
        // Controlliamo che l'organizzatore dell'evento esista
        Person creator = pServ.findPersonById(newEventInfo.creatorId());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(newEventInfo.date(), formatter);
        Event newEvent = new Event(newEventInfo.title(), newEventInfo.description(), newEventInfo.place(), date, newEventInfo.maxNumPeople(), creator);

        eRepo.save(newEvent);

        log.info("The event " + newEvent.getTitle() + " has been correctly saved!");

        return newEvent;
    }
}
