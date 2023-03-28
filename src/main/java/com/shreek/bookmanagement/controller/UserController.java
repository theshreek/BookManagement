package com.shreek.bookmanagement.controller;

import com.shreek.bookmanagement.dto.ResponseDTO;
import com.shreek.bookmanagement.model.User;
import com.shreek.bookmanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newuser = new User();
        newuser.setEmail(user.getEmail());
        newuser.setPassword(user.getPassword());
        User add = userRepo.save(newuser);

        return new ResponseEntity<>(add, HttpStatus.CREATED);

    }

    @GetMapping
    public List<User> getAllUser(){

        return userRepo.findAll();
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable("id") int id){
        return Optional.ofNullable(userRepo.findById(id).orElseThrow(() ->
                new Error("User Id Not found")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserById(@RequestBody User user, @PathVariable("id") int id){
        User olduser = userRepo.findById(id).orElseThrow(()->
        new Error("User not found by Id"+id));
        olduser.setEmail(user.getEmail());
        olduser.setPassword(user.getPassword());
        User save = userRepo.save(olduser);
        return new ResponseEntity<>(save, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteUserById(@PathVariable("id") int id){
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) userRepo.deleteById(id);
        else throw new Error("User not found by id"+id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("User successfully deleted");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
