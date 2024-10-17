package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.*;
import edu.java.hibernatetask.repository.TraineeRepository;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class TraineeRepositoryImpl implements TraineeRepository {

    private EntityManager entityManager;

    private EntityManagerFactory entityManagerFactory;
    private static Logger logger = LoggerFactory.getLogger(TraineeRepositoryImpl.class);

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
    public Optional<Trainee> getTraineeByUserName(String userName) {
        Query query = entityManager.createQuery("SELECT t FROM Trainee as t WHERE t.user.userName = :userName", Trainee.class);
        query.setParameter("userName", userName);
        Trainee trainee = null;
        try {
            trainee = (Trainee) query.getSingleResult();
        } catch (NoResultException e){
            logger.error("No such Trainee present in the database with userName {}", userName);
        }
        return trainee != null ? Optional.of(trainee) : Optional.empty();
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
