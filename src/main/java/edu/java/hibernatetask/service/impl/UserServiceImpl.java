package edu.java.hibernatetask.service.impl;

import edu.java.hibernatetask.entity.User;
import edu.java.hibernatetask.repository.UserRepository;
import edu.java.hibernatetask.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByUserName(String userName) {
        return userRepository.getUserByUserName(userName);
    }
}
