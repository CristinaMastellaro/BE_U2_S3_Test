package cristinamastellaro.BE_U2_S3_Test.services;

import cristinamastellaro.BE_U2_S3_Test.entities.Person;
import cristinamastellaro.BE_U2_S3_Test.entities.Role;
import cristinamastellaro.BE_U2_S3_Test.exceptions.EmailAlreadyUsedException;
import cristinamastellaro.BE_U2_S3_Test.exceptions.NotFoundException;
import cristinamastellaro.BE_U2_S3_Test.payloads.PersonPayload;
import cristinamastellaro.BE_U2_S3_Test.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
