package edu.java.hibernatetask.service.impl;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.repository.DBException;
import edu.java.hibernatetask.repository.TraineeRepository;
import edu.java.hibernatetask.service.ServiceException;
import edu.java.hibernatetask.service.TraineeService;
import edu.java.hibernatetask.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static edu.java.hibernatetask.utility.PasswordGenerator.generatePassword;

@Service
public class TraineeServiceImpl implements TraineeService {

    private TraineeRepository traineeRepository;
    private UserService userService;

    public TraineeServiceImpl(TraineeRepository traineeRepository, UserService userService) {
        this.traineeRepository = traineeRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Trainee> save(Trainee trainee) {
        trainee.getUser().setUserName(createValidUserName(trainee));
        trainee.getUser().setPassword(generatePassword());
        return traineeRepository.save(trainee);
    }

    @Override
    public Optional<Trainee> usernameAndPasswordMatching(String userName, String password) {
        Optional<Trainee> trainee = getTraineeByUserName(userName);
        if(trainee.isPresent()){
            if(password.equals(trainee.get().getUser().getPassword())){
                return trainee;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trainee> getTraineeByUserName(String userName) {
        return traineeRepository.getTraineeByUserName(userName);
    }

    @Override
    public  Optional<Trainee> changePassword(Trainee trainee) {
        return traineeRepository.changePassword(trainee);
    }

    @Override
    public Optional<Trainee> update(Trainee trainee) {
        return traineeRepository.update(trainee);
    }

    @Override
    public boolean changeStatus(Trainee trainee) throws ServiceException {
        try {
            return traineeRepository.changeStatus(trainee);
        } catch (DBException e) {
            throw new ServiceException("Fail to change status", e);
        }
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

    private String createValidUserName(Trainee trainee) {

        String userName = trainee.getUser().getFirstName() + "." + trainee.getUser().getLastName();

        if (userService.getUserByUserName(userName).isEmpty()) {
            return userName;
        }

        for (long i = 0; i < Long.MAX_VALUE; i++) {
            StringBuilder newUserName = new StringBuilder(userName + i);
            if (userService.getUserByUserName(newUserName.toString()).isEmpty()) {
                return newUserName.toString();
            }
        }

        return userName;
    }
}
