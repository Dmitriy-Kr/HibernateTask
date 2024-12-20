package edu.java.hibernatetask.service;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.entity.TrainingType;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TraineeService {
    Optional<Trainee> save(Trainee trainee);
    Optional<Trainee> usernameAndPasswordMatching(String userName, String password) throws ServiceException;
    Optional<Trainee> getTraineeByUserName(String userName) throws ServiceException;
    Optional<Trainee> changePassword(Trainee trainee);
    Optional<Trainee> update(Trainee trainer);
    boolean changeStatus(Trainee trainee) throws ServiceException;
    void deleteByUsername(String Username) throws ServiceException;
    List<Training> getTrainings(String traineeUsername, Date fromDate, Date toDate, String trainerName, TrainingType trainingType) throws ServiceException;
    Optional<Trainee> updateTrainersList(String traineeUsername, List<Trainer> trainersList) throws ServiceException;
}
