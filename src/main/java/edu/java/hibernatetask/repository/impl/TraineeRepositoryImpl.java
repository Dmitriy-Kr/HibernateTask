package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.repository.TraineeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TraineeRepositoryImpl implements TraineeRepository {

    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;

    {
        entityManagerFactory = Persistence.createEntityManagerFactory("gym");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Trainee> save(Trainee trainee) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(trainee);

            entityManager.getTransaction().commit();

            return Optional.of(trainee);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trainee> usernameAndPasswordMatching(String userName, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<Trainee> getTraineeByUserName(String userName) {
        return Optional.empty();
    }

    @Override
    public void changePassword(Trainee trainee) {

    }

    @Override
    public Optional<Trainee> update(Trainee trainer) {
        return Optional.empty();
    }

    @Override
    public boolean changeStatus(Trainee trainee) {
        return false;
    }

    @Override
    public void deleteByUsername(String Username) {

    }

    @Override
    public List<Training> getTrainings(String traineeUsername, Date fromDate, Date toDate, String trainerName, TrainingType trainingType) {
        return null;
    }

    @Override
    public Optional<Trainee> updateTrainersList(Trainee trainee, List<Trainer> trainersList) {
        return Optional.empty();
    }
}
