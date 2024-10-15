package edu.java.hibernatetask.service.impl;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.repository.TraineeRepository;
import edu.java.hibernatetask.service.TraineeService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TraineeServiceImpl implements TraineeService {

    private TraineeRepository traineeRepository;

    public TraineeServiceImpl(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Override
    public Optional<Trainee> save(Trainee trainee) {
        return Optional.empty();
    }

    @Override
    public Optional<Trainee> usernameAndPasswordMatching(String userName, String password) {
        return Optional.empty();
    }

    @Override
    public Optional<Trainee> getTraineeByUserName(String userName) {
        return Optional.empty();
    }

    @Override
    public void changePassword(Trainee trainee) {

    }

    @Override
    public Optional<Trainee> update(Trainee trainer) {
        return Optional.empty();
    }

    @Override
    public boolean changeStatus(Trainee trainee) {
        return false;
    }

    @Override
    public void deleteByUsername(String Username) {

    }

    @Override
    public List<Training> getTrainings(String traineeUsername, Date fromDate, Date toDate, String trainerName, TrainingType trainingType) {
        return null;
    }

    @Override
    public Optional<Trainee> updateTrainersList(Trainee trainee, List<Trainer> trainersList) {
        return Optional.empty();
    }
}
