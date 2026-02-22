package com.example.movieapp.service;

import com.example.movieapp.dto.dtorequest.UserRequest;
import com.example.movieapp.dto.dtoresponse.UserResponse;
import com.example.movieapp.exception.ConflictException;
import com.example.movieapp.exception.ResourceNotFoundException;
import com.example.movieapp.model.Users;
import com.example.movieapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // butun istifadeceiler
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ID ile
    public UserResponse getUserById(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return mapToResponse(user);
    }

    // Username ile
    public UserResponse getUserByUsername(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return mapToResponse(user);
    }

    // yaratmag yeni istifadeci
    public UserResponse createUser(UserRequest request) {
        // Username ve email kontrol
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ConflictException("Username already exists: " + request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already exists: " + request.getEmail());
        }

        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setCreatedAt(LocalDateTime.now());

        Users savedUser = userRepository.save(user);
        return mapToResponse(savedUser);
    }

    //  guncelle
    public UserResponse updateUser(Long id, UserRequest request) {
        Users existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Username ve email yoxlama
        if (!existingUser.getUsername().equals(request.getUsername()) &&
                userRepository.existsByUsername(request.getUsername())) {
            throw new ConflictException("Username already exists: " + request.getUsername());
        }
        if (!existingUser.getEmail().equals(request.getEmail()) &&
                userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already exists: " + request.getEmail());
        }

        existingUser.setUsername(request.getUsername());
        existingUser.setPassword(request.getPassword());
        existingUser.setEmail(request.getEmail());

        Users updatedUser = userRepository.save(existingUser);
        return mapToResponse(updatedUser);
    }

    //  sil
    public void deleteUser(Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }

    // Dto maplama
    private UserResponse mapToResponse(Users user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}