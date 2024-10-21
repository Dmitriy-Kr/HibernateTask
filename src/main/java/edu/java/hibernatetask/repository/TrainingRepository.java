package edu.java.hibernatetask.repository;

import edu.java.hibernatetask.entity.Training;

import java.util.Optional;

public interface TrainingRepository {
    Optional<Training> create(Training training) throws DBException;
}
