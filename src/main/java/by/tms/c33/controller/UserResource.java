package by.tms.c33.controller;

import by.tms.c33.entity.User;
import by.tms.c33.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/user")
public class UserResource {

    @Autowired
    private UserRepository userRepository;

    @GetMapping (path = "/getAll")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping(path = "/getByLogin")
    public ResponseEntity<User> getByLogin(@RequestParam String login){
        Optional<User> byLogin = userRepository.findByLogin(login);
        return ResponseEntity.of(byLogin);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<User> save(@RequestBody User user){
        User saved = userRepository.save(user);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

}
