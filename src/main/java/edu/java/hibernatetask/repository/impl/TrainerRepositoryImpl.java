package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.repository.TrainerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainerRepositoryImpl implements TrainerRepository {
//    @PersistenceContext
    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    {
        entityManagerFactory = Persistence.createEntityManagerFactory("gym");
        entityManager = entityManagerFactory.createEntityManager();
    }

    private static Logger logger = LoggerFactory.getLogger(TrainerRepositoryImpl.class);

    @Override
    public Optional<Trainer> save(Trainer trainer) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(trainer);

            entityManager.getTransaction().commit();

            return Optional.of(trainer);

        } catch (Exception e) {
            logger.error("Error save Trainer in the database", e);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trainer> usernameAndPasswordMatching(String userName, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<Trainer> getTrainerByUserName(String userName) {
        return Optional.empty();
    }

    @Override
    public void changePassword(Trainer trainer) {

    }

    @Override
    public Optional<Trainer> update(Trainer trainer) {
        return Optional.empty();
    }

    @Override
    public boolean changeStatus(Trainer trainer) {
        return false;
    }

    @Override
    public List<Training> getTrainings(String trainerUsername, Date fromDate, Date toDate, String traineeName) {
        return null;
    }

    @Override
    public List<Trainer> getNotAssignedOnTraineeTrainersByTraineeUsername(String traineeUsername) {
        return null;
    }
}
