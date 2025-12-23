package com.eirzarog.studentmanagementsystem.service;

import com.eirzarog.studentmanagementsystem.entity.User;
import com.eirzarog.studentmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService   {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add("ROLE_USER");
        return userRepository.save(user);
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }


    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
