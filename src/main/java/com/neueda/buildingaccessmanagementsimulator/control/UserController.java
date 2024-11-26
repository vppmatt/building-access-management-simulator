package com.neueda.buildingaccessmanagementsimulator.control;

import com.neueda.buildingaccessmanagementsimulator.data.UserRepository;
import com.neueda.buildingaccessmanagementsimulator.exceptions.UserNotFoundException;
import com.neueda.buildingaccessmanagementsimulator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Integer id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
