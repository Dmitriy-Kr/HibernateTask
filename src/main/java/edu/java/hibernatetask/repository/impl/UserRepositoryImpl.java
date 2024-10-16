package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.User;
import edu.java.hibernatetask.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> getUserByUserName(String userName) {
        Query query = entityManager.createQuery("SELECT u FROM User as u WHERE e.userName = :userName", User.class);
        query.setParameter("userName", userName);
        User user = (User) query.getSingleResult();
        return user != null ? Optional.of(user) : Optional.empty();
    }
}
