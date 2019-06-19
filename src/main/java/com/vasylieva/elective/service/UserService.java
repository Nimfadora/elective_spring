package com.vasylieva.elective.service;

import com.vasylieva.elective.model.User;
import com.vasylieva.elective.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
@Transactional
public class UserService implements UserDetailsService {

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

    public com.vasylieva.elective.model.UserDetails getUser(Principal principal) {
        return (com.vasylieva.elective.model.UserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        return new com.vasylieva.elective.model.UserDetails(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }
}
