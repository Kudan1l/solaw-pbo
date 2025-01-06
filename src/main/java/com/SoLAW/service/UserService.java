package com.SoLAW.service;

import com.SoLAW.model.User;
import com.SoLAW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.SoLAW.PasswordUtil.PasswordUtil; // Perbaiki jika ada kesalahan import

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void signup(String email, String password, String username) {
        if (emailExists(email)) {
            throw new IllegalArgumentException("Email sudah terdaftar");
        }

        // Membuat pengguna baru
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(PasswordUtil.hashPassword(password)); // Hash password

        userRepository.save(newUser);
    }

    public boolean authenticate(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return PasswordUtil.verifyPassword(password, user.getPassword()); // Verifikasi password
        }
        return false;
    }
}

