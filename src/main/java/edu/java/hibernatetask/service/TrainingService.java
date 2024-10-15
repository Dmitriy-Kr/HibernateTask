package edu.java.hibernatetask.service;

import edu.java.hibernatetask.entity.Training;

import java.util.Optional;

public interface TrainingService {
    Optional<Training> save(Training training);
}
