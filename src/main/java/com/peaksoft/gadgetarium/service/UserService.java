package com.peaksoft.gadgetarium.service;


import com.peaksoft.gadgetarium.config.security.jwt.JwtUtil;
import com.peaksoft.gadgetarium.mapper.LoginMapper;
import com.peaksoft.gadgetarium.mapper.UserMapper;
import com.peaksoft.gadgetarium.model.dto.LoginRequest;
import com.peaksoft.gadgetarium.model.dto.LoginResponse;
import com.peaksoft.gadgetarium.model.dto.RegistrationRequest;
import com.peaksoft.gadgetarium.model.dto.RegistrationResponse;
import com.peaksoft.gadgetarium.model.entities.Role;
import com.peaksoft.gadgetarium.model.entities.User;
import com.peaksoft.gadgetarium.repository.RoleRepository;
import com.peaksoft.gadgetarium.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final LoginMapper loginMapper;
    private final JwtUtil jwtUtil;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager manager;

    public RegistrationResponse registration(RegistrationRequest request) {
        User user = userMapper.mapToEntity(request);
        System.out.println(request.getPassword());
        System.out.println(user.getPassword());
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.error("someone has the same email, please change your email");
            throw new RuntimeException("Email is not unique!");
        }
        if (!user.getEmail().contains("@")) {
            log.error("this isn't email, because your email doesn't have symbol '@'");
            throw new RuntimeException("Email doesn't have symbol '@'");
        }

        if (user.getPassword().length() < 6) {
            log.error("the password is short, make at least 6 letters");
            throw new RuntimeException("Password is short");
        }

        if (!user.getPassword().contains("0") &&
                !user.getPassword().contains("1") &&
                !user.getPassword().contains("2") &&
                !user.getPassword().contains("3") &&
                !user.getPassword().contains("4") &&
                !user.getPassword().contains("5") &&
                !user.getPassword().contains("6") &&
                !user.getPassword().contains("7") &&
                !user.getPassword().contains("8") &&
                !user.getPassword().contains("9")) {
            log.error("your password must have at least 1 digit");
            throw new RuntimeException("password without digit");
        }
        boolean pass = false;
        for (char ch = 65; ch < 91; ch++) {
            if (user.getPassword().contains("" + ch)) {
                pass = true;
                break;
            }
        }
        if (!pass) {
            for (char ch = 97; ch < 123; ch++) {
                if (user.getPassword().contains("" + ch)) {
                    pass = true;
                    break;
                }
            }
            if (!pass) {
                log.error("your password must have at least 1 letter");
                throw new RuntimeException("password without letter");
            }
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        List<Role> roles = new ArrayList<>();
        if (userRepository.findAll().isEmpty()) {
            Role role = roleRepository.findByName("ADMIN");
            if (role == null) {
                role = new Role("ADMIN");
            }
            roles.add(role);
        } else {
            Role role = roleRepository.findByName("USER");
            if (role == null) {
                role = new Role("USER");
            }
            roles.add(role);
        }
        user.setRoles(roles);

//        List<Role> roles = new ArrayList<>();
//        Role role = new Role("USER");
//        roles.add(role);
//        user.setRoles(roles);

        userRepository.save(user);
        return userMapper.registration(user);
    }

    public LoginResponse login(LoginRequest request) {

        System.out.println("userservice before login before user");

        System.out.println("userservice before nanager");
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        System.out.println("userservice before login after manager");

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException(" Not found with " + request.getEmail()));


        System.out.println("userservice before login before jwt");
        String jwt = jwtUtil.generateToken((UserDetails) user);
        System.out.println("userservice after login");
        return loginMapper.mapToResponse(jwt,user);
    }

    public RegistrationResponse findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not found"));
        return  userMapper.registration(user);
    }

    public List<RegistrationResponse>getAllUser(){
        return userRepository.findAll().stream().map(userMapper::registration).toList();
    }

    public void deleted(Long id){
        userRepository.findById(id);
    }

    public RegistrationResponse profile(Principal principal) {
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow( ()->new RuntimeException(" Not found with " + principal.getName()));
        return userMapper.registration(user);
    }

    public RegistrationResponse update(Long userId, RegistrationRequest request) {
        User oldUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(" User not found by id: " + userId));
        oldUser.setFirstName(request.getFirstName());
        oldUser.setLastName(request.getLastName());
        oldUser.setTelNumber(request.getTelNumber());
        oldUser.setEmail(request.getEmail());
        oldUser.setPassword(request.getPassword());
        userRepository.save(oldUser);
        return userMapper.registration(oldUser);
    }

}
