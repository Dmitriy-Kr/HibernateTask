package edu.java.hibernatetask.repository;

import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TrainerRepository {
        Optional<Trainer> create(Trainer trainer) throws DBException;
        Optional<Trainer> getTrainerByUserName(String userName) throws DBException;
        Optional<Trainer> changePassword(Trainer trainer);
        Optional<Trainer> update(Trainer trainer);
        boolean changeStatus(Trainer trainer) throws DBException;
        List<Training> getTrainings(String trainerUsername, Date fromDate, Date toDate, String traineeName) throws DBException;
        List<Trainer> getAll() throws DBException;
        List<Trainer> getNotAssignedOnTraineeTrainersByTraineeUsername(String traineeUsername);
}
