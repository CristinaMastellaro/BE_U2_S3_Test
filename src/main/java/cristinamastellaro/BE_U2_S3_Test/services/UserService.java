package cristinamastellaro.BE_U2_S3_Test.services;

import cristinamastellaro.BE_U2_S3_Test.entities.Role;
import cristinamastellaro.BE_U2_S3_Test.entities.User;
import cristinamastellaro.BE_U2_S3_Test.exceptions.EmailAlreadyUsedException;
import cristinamastellaro.BE_U2_S3_Test.payloads.UserPayload;
import cristinamastellaro.BE_U2_S3_Test.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository uRepo;
    @Autowired
    private PasswordEncoder bCrypt;

    public User saveUser(UserPayload userInfo) {
        if (uRepo.existsByEmail(userInfo.email())) throw new EmailAlreadyUsedException(userInfo.email());

        User newUser = new User(userInfo.name(), userInfo.surname(), userInfo.email(), bCrypt.encode(userInfo.password()), Role.USER);
        uRepo.save(newUser);
        log.info("The user " + newUser.getName() + " has been registered!");
        return newUser;
    }
}
