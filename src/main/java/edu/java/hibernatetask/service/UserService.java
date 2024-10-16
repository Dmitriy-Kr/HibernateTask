package edu.java.hibernatetask.service;

import edu.java.hibernatetask.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUserName(String userName);
}
