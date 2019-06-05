package com.vasylieva.elective.repository;

import com.vasylieva.elective.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

}
