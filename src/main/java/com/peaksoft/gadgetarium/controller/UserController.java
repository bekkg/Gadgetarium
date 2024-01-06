package com.peaksoft.gadgetarium.controller;

import com.peaksoft.gadgetarium.model.dto.LoginRequest;
import com.peaksoft.gadgetarium.model.dto.LoginResponse;
import com.peaksoft.gadgetarium.model.dto.RegistrationRequest;
import com.peaksoft.gadgetarium.model.dto.RegistrationResponse;
import com.peaksoft.gadgetarium.repository.UserRepository;
import com.peaksoft.gadgetarium.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Tag(name = "This is UserController")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @Operation(description = "This method registered new User")
    @PostMapping("/sign-up")
    public ResponseEntity<RegistrationResponse> registration(@RequestBody RegistrationRequest request) {
        RegistrationResponse response = userService.registration(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/sign-in")
    public LoginResponse login(@RequestBody LoginRequest request) {
        System.out.println("controller user login");
        return userService.login(request);
    }

    @GetMapping("/find-by/{id}")
    public RegistrationResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/get-allUser")
    public List<RegistrationResponse> getAllUser(){
       return userService.getAllUser();
    }

    @DeleteMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id){
        userService.deleted(id);
        return " Success deleted! ";
    }
    @GetMapping("/profile")
    public RegistrationResponse profile(Principal principal) {
        return userService.profile(principal);
    }

    @PutMapping("/update/{id}")
    public RegistrationResponse update(@PathVariable("id") Long id, @RequestBody RegistrationRequest request) {
        return userService.update(id, request);
    }
}
