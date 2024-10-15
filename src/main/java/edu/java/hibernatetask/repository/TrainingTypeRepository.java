package edu.java.hibernatetask.repository;

import edu.java.hibernatetask.entity.TrainingType;

import java.util.Optional;

public interface TrainingTypeRepository {
    Optional<TrainingType> save(TrainingType trainingType);
}
