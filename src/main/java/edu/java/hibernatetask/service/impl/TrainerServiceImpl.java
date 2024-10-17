package edu.java.hibernatetask.service.impl;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.Training;
import edu.java.hibernatetask.repository.TrainerRepository;
import edu.java.hibernatetask.service.TrainerService;
import edu.java.hibernatetask.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static edu.java.hibernatetask.utility.PasswordGenerator.*;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;
    private UserService userService;

    public TrainerServiceImpl(TrainerRepository trainerRepository, UserService userService) {
        this.trainerRepository = trainerRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Trainer> save(Trainer trainer) {
        trainer.getUser().setUserName(createValidUserName(trainer));
        trainer.getUser().setPassword(generatePassword());
        return trainerRepository.save(trainer);
    }

    @Override
    public Optional<Trainer> usernameAndPasswordMatching(String userName, String password) {
        Optional<Trainer> trainer = getTrainerByUserName(userName);
        if(trainer.isPresent()){
            if(password.equals(trainer.get().getUser().getPassword())){
                return trainer;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Trainer> getTrainerByUserName(String userName) {
        return trainerRepository.getTrainerByUserName(userName);
    }

    @Override
    public void changePassword(Trainer trainer) {

    }

    @Override
    public Optional<Trainer> update(Trainer trainer) {
        return Optional.empty();
    }

    @Override
    public boolean changeStatus(Trainer trainer) {
        return false;
    }

    @Override
    public List<Training> getTrainings(String trainerUsername, Date fromDate, Date toDate, String traineeName) {
        return null;
    }

    @Override
    public List<Trainer> getNotAssignedOnTraineeTrainersByTraineeUsername(String traineeUsername) {
        return null;
    }

    private String createValidUserName(Trainer trainer) {

        String userName = trainer.getUser().getFirstName() + "." + trainer.getUser().getLastName();

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
