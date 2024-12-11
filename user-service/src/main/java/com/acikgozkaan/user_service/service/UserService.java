package com.acikgozkaan.user_service.service;

import com.acikgozkaan.user_service.core.exception.UserNotFoundException;
import com.acikgozkaan.user_service.dto.request.UserCreateRequest;
import com.acikgozkaan.user_service.dto.request.UserUpdateRequest;
import com.acikgozkaan.user_service.entity.User;
import com.acikgozkaan.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserCreateRequest userCreateRequest) {
        User userToCreate = User.builder()
                .name(userCreateRequest.getName())
                .surname(userCreateRequest.getSurname())
                .email(userCreateRequest.getEmail())
                .build();

        return userRepository.save(userToCreate);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id: "+id)
        );
    }

    public User updateUser(String id, UserUpdateRequest userUpdateRequest) {
        User updateToUser = this.getUserById(id);

        updateToUser.setName(userUpdateRequest.getName());
        updateToUser.setSurname(userUpdateRequest.getSurname());
        updateToUser.setEmail(userUpdateRequest.getEmail());

        return userRepository.save(updateToUser);
    }

    public User deleteUser(String id) {
        User userToDelete = this.getUserById(id);
        userRepository.delete(userToDelete);
        return userToDelete;
    }

}
