package com.example.fullstackbackend.controller;

import com.example.fullstackbackend.exception.UserNotFoundException;
import com.example.fullstackbackend.model.Users;
import com.example.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    Users newUser (@RequestBody Users newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/getAll")
    List<Users>  getAllUsers() {
        return  userRepository.findAll();
    }

    @GetMapping("/{id}")
    Users getUserById(@PathVariable Long id) {
        return  userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/{id}")
    Users updateUser(@RequestBody Users newUser,@PathVariable Long id) {
        return  userRepository.findById(id).map(user -> {
            user.setName(newUser.getName());
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    String deleteUser (@PathVariable Long id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return  "User with id: " + id + " has been deleted successfully";
    }
}
