package edu.java.hibernatetask.repository;

import edu.java.hibernatetask.entity.TrainingType;

import java.util.List;
import java.util.Optional;

public interface TrainingTypeRepository {
    Optional<TrainingType> getByName(String trainingType) throws DBException;
    Optional<TrainingType> getById(Long id);
    List<TrainingType> getAll();
}
