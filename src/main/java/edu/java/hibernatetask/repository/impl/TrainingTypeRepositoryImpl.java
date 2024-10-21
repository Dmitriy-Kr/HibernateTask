package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.repository.DBException;
import edu.java.hibernatetask.repository.TrainingTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TrainingTypeRepositoryImpl implements TrainingTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;
    private static Logger logger = LoggerFactory.getLogger(TrainerRepositoryImpl.class);

    @Override
    public Optional<TrainingType> getByName(String trainingTypeName) throws DBException {
        Query query = entityManager.createQuery("SELECT t FROM TrainingType as t WHERE t.trainingType = :trainingTypeName", TrainingType.class);
        query.setParameter("trainingTypeName", trainingTypeName);
        TrainingType trainingType = null;
        try {
            trainingType = (TrainingType) query.getSingleResult();
        } catch (NoResultException e) {
            logger.error("No such Training type present in the database with name {}", trainingTypeName);
            throw new DBException("No such Training type present in the database with name " + trainingTypeName, e);
        }
        return trainingType != null ? Optional.of(trainingType) : Optional.empty();
    }

    @Override
    public Optional<TrainingType> getById(Long id) {
        TrainingType trainingType = entityManager.find(TrainingType.class, id);
        return trainingType != null ? Optional.of(trainingType) : Optional.empty();
    }

    @Override
    public List<TrainingType> getAll() {
        Query jpqlQuery = entityManager.createQuery("SELECT * FROM TrainingType as t");
        List<TrainingType> trainingTypes = jpqlQuery.getResultList();
        return trainingTypes;
    }
}
