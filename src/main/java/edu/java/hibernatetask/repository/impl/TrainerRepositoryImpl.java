package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
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
