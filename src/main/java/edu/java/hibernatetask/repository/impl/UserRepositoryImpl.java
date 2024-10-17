package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.User;
import edu.java.hibernatetask.repository.UserRepository;
import javax.persistence.*;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(name = "hibernate")
    private EntityManager entityManager;

    private static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    public Optional<User> getUserByUserName(String userName) {

        Query query = entityManager.createQuery("SELECT u FROM User as u WHERE u.userName = :userName", User.class);
        query.setParameter("userName", userName);
        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e){
            logger.error("No such User present in the database with userName {}", userName);
        } finally {
            entityManager.close();
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }
}
