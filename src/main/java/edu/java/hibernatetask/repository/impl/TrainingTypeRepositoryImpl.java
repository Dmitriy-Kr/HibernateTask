package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.repository.TrainingRepository;
import edu.java.hibernatetask.repository.TrainingTypeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class TrainingTypeRepositoryImpl implements TrainingTypeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<TrainingType> save(TrainingType trainingType) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(trainingType);

            entityManager.getTransaction().commit();

            return Optional.of(trainingType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
