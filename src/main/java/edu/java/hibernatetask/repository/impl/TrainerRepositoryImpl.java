package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.repository.TrainerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TrainerRepositoryImpl implements TrainerRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Trainer> save(Trainer trainer) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(trainer);

            entityManager.getTransaction().commit();

            return Optional.of(trainer);

        } catch (Exception e) {
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
