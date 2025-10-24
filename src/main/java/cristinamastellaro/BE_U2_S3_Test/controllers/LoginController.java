package cristinamastellaro.BE_U2_S3_Test.controllers;

import cristinamastellaro.BE_U2_S3_Test.entities.Person;
import cristinamastellaro.BE_U2_S3_Test.payloads.PersonPayload;
import cristinamastellaro.BE_U2_S3_Test.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private PersonService uServ;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Person savePerson(@RequestBody PersonPayload newUser) {
        return uServ.savePerson(newUser);
    }
}
