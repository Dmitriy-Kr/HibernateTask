package edu.java.hibernatetask.facade;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.service.ServiceException;
import edu.java.hibernatetask.service.TraineeService;
import edu.java.hibernatetask.service.TrainerService;

import java.util.Optional;
import java.util.Scanner;

public class AuthenticationItem implements MenuItem {
    private TraineeService traineeService;
    private TrainerService trainerService;
    private Scanner scanner;

    public AuthenticationItem(TraineeService traineeService, TrainerService trainerService, Scanner scanner) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "Authentication";
    }

    @Override
    public void run() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            Optional<Trainee> trainee = traineeService.usernameAndPasswordMatching(username, password);
            Optional<Trainer> trainer = trainerService.usernameAndPasswordMatching(username, password);

            if (trainee.isPresent()) {
                System.out.println("\n Logged as trainee: ");
                System.out.println("- trainee id = " + trainee.get().getId());
                System.out.println("- trainee username = " + trainee.get().getUser().getUserName());
                System.out.println("- trainee first name = " + trainee.get().getUser().getFirstName());
                System.out.println("- trainee last name = " + trainee.get().getUser().getLastName());
                System.out.println();
            } else {
                if(trainer.isPresent()){
                    System.out.println("\n Logged as trainee: ");
                    System.out.println("- trainee id = " + trainee.get().getId());
                    System.out.println("- trainee username = " + trainee.get().getUser().getUserName());
                    System.out.println("- trainee first name = " + trainee.get().getUser().getFirstName());
                    System.out.println("- trainee last name = " + trainee.get().getUser().getLastName());
                    System.out.println();
                }
            }
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
    }
}
