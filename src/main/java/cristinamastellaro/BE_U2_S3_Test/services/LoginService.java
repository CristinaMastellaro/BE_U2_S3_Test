package cristinamastellaro.BE_U2_S3_Test.services;

import cristinamastellaro.BE_U2_S3_Test.entities.Person;
import cristinamastellaro.BE_U2_S3_Test.exceptions.UnauthorizedException;
import cristinamastellaro.BE_U2_S3_Test.payloads.LoginDTO;
import cristinamastellaro.BE_U2_S3_Test.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PersonService pServ;
    @Autowired
    private PasswordEncoder bCrypt;

    public String checkInfoAndGenerateToken(LoginDTO loginPayload) {
        // Esiste email?
        System.out.println(loginPayload);
        Person user = pServ.findPersonByEmail(loginPayload.email());

        // Password corretta?
        if (bCrypt.matches(loginPayload.password(), user.getPassword())) {
            // Ottieni token
            return jwtTools.createToken(user.getId());
        } else {
            throw new UnauthorizedException("Wrong credentials");
        }
    }
}
