package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.User;
import edu.java.hibernatetask.repository.UserRepository;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

//    @PersistenceContext(name = "gym")
    private EntityManager entityManager;

//    @PersistenceUnit(name = "gym")
    private EntityManagerFactory entityManagerFactory;

    {
        entityManagerFactory = Persistence.createEntityManagerFactory("gym");
        entityManager = entityManagerFactory.createEntityManager();
    }

//    public UserRepositoryImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    private static Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    public Optional<User> getUserByUserName(String userName) {

        System.out.println("emf = "+ entityManagerFactory + "  ...   em = " + entityManager);

        Query query = entityManager.createQuery("SELECT u FROM User as u WHERE u.userName = :userName", User.class);
        query.setParameter("userName", userName);
        User user = null;
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e){
            logger.error("No such User present in the database with userName {}", userName);
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }
}
