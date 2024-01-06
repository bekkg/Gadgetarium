package com.peaksoft.gadgetarium.mapper;

import com.peaksoft.gadgetarium.model.dto.LoginResponse;
import com.peaksoft.gadgetarium.model.entities.Role;
import com.peaksoft.gadgetarium.model.entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginMapper {
    public LoginResponse mapToResponse(String token, User user){
        List<String> roles = new ArrayList<>();
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
        }
        return LoginResponse
                .builder()
                .token(token)
                .name(roles)
                .build();
    }

}
