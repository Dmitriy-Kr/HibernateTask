package edu.java.hibernatetask.repository;

import edu.java.hibernatetask.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserByUserName(String userName);
}
