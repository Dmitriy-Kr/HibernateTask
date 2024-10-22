package edu.java.hibernatetask.repository;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.entity.TrainingType;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TraineeRepository {
    Optional<Trainee> create(Trainee trainee);
    Optional<Trainee> update(Trainee trainer);
    void delete(Trainee trainee) throws DBException;
    Optional<Trainee> getTraineeByUserName(String userName) throws DBException;
    Optional<Trainee> changePassword(Trainee trainee);
    boolean changeStatus(Trainee trainee) throws DBException;
    List<Training> getTrainings(String traineeUsername, Date fromDate, Date toDate, String trainerName, TrainingType trainingType) throws DBException;
    Optional<Trainee> updateTrainersList(Trainee trainee, List<Trainer> trainersList) throws DBException;
}
