package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.repository.TrainingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class TrainingRepositoryImpl implements TrainingRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Training> save(Training training) {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(training);

            entityManager.getTransaction().commit();

            return Optional.of(training);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
