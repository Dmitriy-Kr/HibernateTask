package edu.java.hibernatetask.repository;

import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TrainerRepository {
        Optional<Trainer> save(Trainer trainer);
        Optional<Trainer> getTrainerByUserName(String userName);
        Optional<Trainer> changePassword(Trainer trainer);
        Optional<Trainer> update(Trainer trainer);
        boolean changeStatus(Trainer trainer) throws DBException;
        List<Training> getTrainings(String trainerUsername, Date fromDate, Date toDate, String traineeName);
        List<Trainer> getNotAssignedOnTraineeTrainersByTraineeUsername(String traineeUsername);
}
