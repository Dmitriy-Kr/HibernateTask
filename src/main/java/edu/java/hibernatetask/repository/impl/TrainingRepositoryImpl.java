package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.repository.DBException;
import edu.java.hibernatetask.repository.TrainingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class TrainingRepositoryImpl implements TrainingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static Logger logger = LoggerFactory.getLogger(TrainingRepositoryImpl.class);

    @Override
    public Optional<Training> create(Training training) throws DBException {
        try {

            entityManager.persist(training);

            return Optional.of(training);

        } catch (Exception e) {
            logger.error("Error saving Training in the database", e);
            throw new DBException("Error saving Training in the database ");
        }
    }
}
