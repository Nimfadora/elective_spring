package com.vasylieva.elective.service;

import com.vasylieva.elective.model.User;
import com.vasylieva.elective.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUserNameAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
