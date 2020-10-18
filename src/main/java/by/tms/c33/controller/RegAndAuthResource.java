package by.tms.c33.controller;

import by.tms.c33.entity.User;
import by.tms.c33.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;


@RestController
@RequestMapping(path = "/home")
public class RegAndAuthResource {

    @Autowired
    private UserRepository userRepository;

    @PostMapping (path = "/reg")
    public ResponseEntity<User> reg(@RequestBody User user){
        user.setToken(generateNewToken());
        User saved = userRepository.save(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PostMapping(path = "/auth")
    public ResponseEntity<User> auth(@RequestBody User user, @RequestHeader("token") String token){
        Optional<User> authed = userRepository.findByLoginAndPassword(user.getLogin(), user.getPassword());
        return ResponseEntity.of(authed);
    }


    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    public static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
