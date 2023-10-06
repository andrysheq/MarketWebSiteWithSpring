package com.taco.cloud.security;

import com.taco.cloud.models.User;
//import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone);
    }

//    public User toUser(){
//        return new User(username, password,
//                fullname, street, city, state, zip, phone);
//    }
}
