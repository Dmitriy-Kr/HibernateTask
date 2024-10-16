package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.*;
import edu.java.hibernatetask.repository.DBException;
import edu.java.hibernatetask.repository.TraineeRepository;

import javax.persistence.*;
import javax.transaction.Transactional;

import edu.java.hibernatetask.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TraineeRepositoryImpl implements TraineeRepository {
    @PersistenceContext(name = "hibernate")
    private EntityManager entityManager;

    private static Logger logger = LoggerFactory.getLogger(TraineeRepositoryImpl.class);

    @Override
    public Optional<Trainee> create(Trainee trainee) {
        try {

            entityManager.persist(trainee);

            return Optional.of(trainee);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trainee> getTraineeByUserName(String userName) throws DBException {
        Query query = entityManager.createQuery("SELECT t FROM Trainee as t WHERE t.user.userName = :userName", Trainee.class);
        query.setParameter("userName", userName);
        Trainee trainee = null;
        try {
            trainee = (Trainee) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error("No such Trainee present in the database with userName {}", userName);
            throw new DBException("No such Trainee present in the database with userName " + userName, e);
        }
        return trainee != null ? Optional.of(trainee) : Optional.empty();
    }

    @Override
    public Optional<Trainee> changePassword(Trainee trainee) {
        Trainee traineeFromDB = entityManager.find(Trainee.class, trainee.getId());
        if (traineeFromDB != null) {
            traineeFromDB.getUser().setPassword(trainee.getUser().getPassword());
            trainee = entityManager.merge(traineeFromDB);
        }

        return Optional.ofNullable(trainee);
    }

    @Override
    public Optional<Trainee> update(Trainee trainee) {
        Trainee traineeFromDB = entityManager.find(Trainee.class, trainee.getId());
        if (traineeFromDB != null) {

            traineeFromDB.getUser().setFirstName(trainee.getUser().getFirstName());
            traineeFromDB.getUser().setLastName(trainee.getUser().getLastName());
            traineeFromDB.getUser().setUserName(trainee.getUser().getUserName());

            traineeFromDB.setDateOfBirth(trainee.getDateOfBirth());
            traineeFromDB.setAddress(trainee.getAddress());

            trainee = entityManager.merge(traineeFromDB);
        } else{
            return Optional.empty();
        }

        return Optional.ofNullable(trainee);
    }

    @Override
    public boolean changeStatus(Trainee trainee) throws DBException {
        Trainee traineeFromDB = entityManager.find(Trainee.class, trainee.getId());
        if (traineeFromDB != null) {

            traineeFromDB.getUser().setIsActive(!traineeFromDB.getUser().isActive());

            trainee = entityManager.merge(traineeFromDB);

        } else {
            logger.error("No such Trainee present in the database with id {}", trainee.getId());
            throw new DBException("No such Trainee present in the database with id " + trainee.getId());
        }

        return trainee.getUser().isActive();
    }

    @Override
    public void delete(Trainee trainee) throws DBException {

        Trainee traineeFromDB = entityManager.find(Trainee.class, trainee.getId());

        if(traineeFromDB == null){
            logger.error("Fail to delete, no trainee with userName {} in DB ", trainee.getUser().getUserName());
            throw new DBException("Fail to delete, no trainee with userName {} in DB " + trainee.getUser().getUserName());
        }

        try{
            entityManager.remove(traineeFromDB );
        } catch (Exception e){
            logger.error("No such entity Trainee managed by entityManager, id {}", trainee.getId());
            throw new DBException("No such entity Trainee managed by entityManager, id " + trainee.getId(), e);
        }
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
