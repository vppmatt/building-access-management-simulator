package com.neueda.buildingaccessmanagementsimulator.control;

import com.neueda.buildingaccessmanagementsimulator.data.UserRepository;
import com.neueda.buildingaccessmanagementsimulator.exceptions.InvalidDataException;
import com.neueda.buildingaccessmanagementsimulator.exceptions.UserNotFoundException;
import com.neueda.buildingaccessmanagementsimulator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/user", "/api2/user"})
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

    @PostMapping
    public User create(@RequestBody User user) throws InvalidDataException {
        if (user.getFirstname().trim().length() < 3) {
            throw new InvalidDataException("Firstname must be at least 3 characters long");
        }
        if (user.getSurname().trim().length() < 3) {
            throw new InvalidDataException("Surname must be at least 3 characters long");
        }
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") Integer id, @RequestBody User user) throws InvalidDataException {
        if (user.getFirstname().trim().length() < 3) {
            throw new InvalidDataException("Firstname must be at least 3 characters long");
        }
        if (user.getSurname().trim().length() < 3) {
            throw new InvalidDataException("Surname must be at least 3 characters long");
        }
        user.setId(id);
        return userRepository.save(user);
    }
}
