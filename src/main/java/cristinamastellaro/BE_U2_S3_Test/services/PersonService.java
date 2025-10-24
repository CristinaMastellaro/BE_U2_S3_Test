package cristinamastellaro.BE_U2_S3_Test.services;

import cristinamastellaro.BE_U2_S3_Test.entities.Event;
import cristinamastellaro.BE_U2_S3_Test.entities.Person;
import cristinamastellaro.BE_U2_S3_Test.entities.Role;
import cristinamastellaro.BE_U2_S3_Test.exceptions.*;
import cristinamastellaro.BE_U2_S3_Test.payloads.PersonPayload;
import cristinamastellaro.BE_U2_S3_Test.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PersonService {
    @Autowired
    private PersonRepository uRepo;
    @Autowired
    private PasswordEncoder bCrypt;

    public Person findPersonById(UUID id) {
        return uRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Person savePerson(PersonPayload userInfo) {
        if (uRepo.existsByEmail(userInfo.email())) throw new EmailAlreadyUsedException(userInfo.email());

        Person newUser = new Person(userInfo.name(), userInfo.surname(), userInfo.email(), bCrypt.encode(userInfo.password()), Role.SIMPLEUSER);
        uRepo.save(newUser);
        log.info("The user " + newUser.getName() + " has been registered!");
        return newUser;
    }

    public Person findPersonByEmail(String email) {
        Person found = uRepo.findByEmail(email);
        if (found == null) throw new NotFoundException(email);
        return found;
    }

    public void partecipateToEvent(Person currentAuthenticatedUser, Event event) {
        // Assicuriamoci che evento esista, che possibilmente non sia già passato e che il numero massimo di persone non sia raggiunto
        if (event.getDate().isBefore(LocalDate.now())) throw new EventFinishedException(event.getTitle());
        if (event.getMaxNumPeople() <= event.getPeopleThatWillPartecipate().size())
            throw new MaxNumReachedException(event.getTitle());
        // Assicuriamoci anche che la persona non si sia già prenotata all'evento
        Person personThatWantsToPartecipate = findPersonById(currentAuthenticatedUser.getId());
        if (personThatWantsToPartecipate.getEventsToPartecipate().contains(event))
            throw new AlreadyBookedException(event.getTitle());

        // Prenota l'evento
        List<Event> currentEventsAlreadyBookedByPerson = personThatWantsToPartecipate.getEventsToPartecipate();
        currentEventsAlreadyBookedByPerson.add(event);
        currentAuthenticatedUser.setEventsToPartecipate(currentEventsAlreadyBookedByPerson);
        uRepo.save(personThatWantsToPartecipate);

        log.info(currentAuthenticatedUser.getName() + " will partecipate to the event " + event.getTitle());
    }

    public void deleteParticipation(UUID idPerson, Event event) {
        // Assicuriamoci che la persona sia iscritta a quell'evento
        Person person = findPersonById(idPerson);
        if (!person.getEventsToPartecipate().contains(event)) throw new NotBookedException(event.getTitle());

        List<Event> bookedEvents = person.getEventsToPartecipate();
        bookedEvents.remove(event);
        person.setEventsToPartecipate(bookedEvents);
        uRepo.save(person);
        log.info("Successfully cancelled the booking for the event " + event.getTitle());
    }

    public Person changeRole(UUID idPersonToChange, Role role) {
        // Controlliamo che id sia di una persona esistente
        Person personToChangeStatus = findPersonById(idPersonToChange);

        personToChangeStatus.setRole(role);

        uRepo.save(personToChangeStatus);

        log.info("Successfully change the status!");

        return personToChangeStatus;
    }

    public List<Event> checkReservedEvents(UUID id) {
        Person person = findPersonById(id);
        System.out.println(person.getEventsToPartecipate());
        return person.getEventsToPartecipate();
    }
}
