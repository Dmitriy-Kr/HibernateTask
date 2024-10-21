package edu.java.hibernatetask.service;

import edu.java.hibernatetask.entity.TrainingType;

import java.util.List;
import java.util.Optional;

public interface TrainingTypeService {
    Optional<TrainingType> getByName(String trainingType) throws ServiceException;
    Optional<TrainingType> getById(Long id);
    List<TrainingType> getAll();
}
