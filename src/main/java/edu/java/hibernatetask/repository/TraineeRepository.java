package edu.java.hibernatetask.repository;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.entity.TrainingType;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TraineeRepository {
    Optional<Trainee> save(Trainee trainee);
    Optional<Trainee> usernameAndPasswordMatching(String userName, String password);
    Optional<Trainee> getTraineeByUserName(String userName);
    void changePassword(Trainee trainee);
    Optional<Trainee> update(Trainee trainer);
    boolean changeStatus(Trainee trainee);
    void deleteByUsername(String Username);
    List<Training> getTrainings(String traineeUsername, Date fromDate, Date toDate, String trainerName, TrainingType trainingType);
    Optional<Trainee> updateTrainersList(Trainee trainee, List<Trainer> trainersList);
}
