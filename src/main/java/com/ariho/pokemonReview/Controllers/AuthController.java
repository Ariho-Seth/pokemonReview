package com.ariho.pokemonReview.Controllers;

import com.ariho.pokemonReview.DTO.UserDTO;
import com.ariho.pokemonReview.Model.Role;
import com.ariho.pokemonReview.Model.UserEntity;
import com.ariho.pokemonReview.Repository.RoleRepository;
import com.ariho.pokemonReview.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        if(userRepository.existsByUsername(userDTO.getUsername())){
            return new ResponseEntity<>("UserName already taken!",HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role role = roleRepository.findByUsername("USER").get();
        userEntity.setRoles(Collections.singletonList(role));

        userRepository.save(userEntity);
        return new ResponseEntity<>("User registered Successfully!", HttpStatus.CREATED);

    }


}
