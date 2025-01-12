package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/Users")
@RestController
@CrossOrigin
public class UsersController {
    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id) {
        Users u = usersRepository.findById(id).orElse(null);
        if (u == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Users>> getAll() {
        return new ResponseEntity<>(usersRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Users> addUser(@RequestBody Users u) {
        Users newUser = usersRepository.save(u);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<Users> put(@PathVariable Long id, @RequestBody Users u) {
        Users u2 = usersRepository.findById(id).orElse(null);
        if (u2 == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        u2.setName(u.getName());
        u2.setPassword(u.getPassword());
        u2.setEmail(u.getEmail());
        usersRepository.save(u2);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Users u = usersRepository.findById(id).orElse(null);
        if (u == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        usersRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/LogIn")
    public ResponseEntity LogIn(@RequestBody Users users) {
        Long id = nameFound(users);
        if (id == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);//404
        }
        if (usersRepository.findById(id).get().getPassword().equals(users.getPassword())) {
            return new ResponseEntity(HttpStatus.OK);//200
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);//400
    }

    public Long nameFound(@RequestBody Users users) {
        List<Users> users1 = usersRepository.findAll();
        for (int i = 0; i < users1.size(); i++) {
            if (users1.get(i).getName().equals(users.getName())) {
                return users1.get(i).getId();
            }
        }
        return null;
    }
}
