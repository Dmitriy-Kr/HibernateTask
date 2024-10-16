package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.repository.DBException;
import edu.java.hibernatetask.repository.TrainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TrainerRepositoryImpl implements TrainerRepository {
    @PersistenceContext(name = "hibernate")
    private EntityManager entityManager;

    private static Logger logger = LoggerFactory.getLogger(TrainerRepositoryImpl.class);

    @Override
    public Optional<Trainer> save(Trainer trainer) {
        try {

            entityManager.persist(trainer);

            return Optional.of(trainer);

        } catch (Exception e) {
            logger.error("Error save Trainer in the database", e);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trainer> getTrainerByUserName(String userName) {
        Query query = entityManager.createQuery("SELECT t FROM Trainer as t WHERE t.user.userName = :userName", Trainer.class);
        query.setParameter("userName", userName);
        Trainer trainer = null;
        try {
            trainer = (Trainer) query.getSingleResult();
        } catch (NoResultException e){
            logger.error("No such Trainer present in the database with userName {}", userName);
        }
        return trainer != null ? Optional.of(trainer) : Optional.empty();
    }

    @Override
    public Optional<Trainer> changePassword(Trainer trainer) {
        Trainer trainerFromDB = entityManager.find(Trainer.class, trainer.getId());
        if (trainerFromDB != null) {
            trainerFromDB.getUser().setPassword(trainer.getUser().getPassword());
            trainer = entityManager.merge(trainerFromDB);
        }

        return Optional.ofNullable(trainer);
    }

    @Override
    public Optional<Trainer> update(Trainer trainer) {
        Trainer trainerFromDB = entityManager.find(Trainer.class, trainer.getId());
        if (trainerFromDB != null) {

            trainerFromDB.getUser().setFirstName(trainer.getUser().getFirstName());
            trainerFromDB.getUser().setLastName(trainer.getUser().getLastName());
            trainerFromDB.getUser().setUserName(trainer.getUser().getUserName());

            trainerFromDB.setSpecialization(trainer.getSpecialization());

            trainer = entityManager.merge(trainerFromDB);
        }else{
            return Optional.empty();
        }

        return Optional.ofNullable(trainer);
    }

    @Override
    public boolean changeStatus(Trainer trainer) throws DBException {
        Trainer trainerFromDB = entityManager.find(Trainer.class, trainer.getId());
        if (trainerFromDB != null) {

            trainerFromDB.getUser().setIsActive(!trainerFromDB.getUser().isActive());

            trainer = entityManager.merge(trainerFromDB);

        } else {
            logger.error("No such Trainer present in the database with id {}", trainer.getId());
            throw new DBException("No such Trainer present in the database with id " + trainer.getId());
        }

        return trainer.getUser().isActive();
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
