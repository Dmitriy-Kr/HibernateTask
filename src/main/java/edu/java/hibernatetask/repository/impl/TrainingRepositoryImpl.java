package edu.java.hibernatetask.repository.impl;

import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.repository.TrainingRepository;
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
