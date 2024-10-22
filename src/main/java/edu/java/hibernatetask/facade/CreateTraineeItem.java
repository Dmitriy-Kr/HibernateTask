package edu.java.hibernatetask.facade;

import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.User;
import edu.java.hibernatetask.service.TraineeService;
import edu.java.hibernatetask.utility.Validation;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

@Component
public class CreateTraineeItem implements MenuItem {
    private TraineeService traineeService;
    private Scanner scanner;

    public CreateTraineeItem(TraineeService traineeService, Scanner scanner) {
        this.traineeService = traineeService;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "Create trainee profile";
    }

    @Override
    public void run() {
        Trainee trainee = new Trainee();
        User user = new User();


        System.out.print("Enter first name: ");
        String line = scanner.nextLine();

        while (!Validation.validateName(line)){
            System.out.print("Not valid first name. Enter again: ");
            line = scanner.nextLine();
        };
        user.setFirstName(line);

        System.out.print("Enter last name: ");
        line = scanner.nextLine();

        while (!Validation.validateName(line)){
            System.out.print("Not valid last name. Enter again: ");
            line = scanner.nextLine();
        };
        user.setLastName(line);

        System.out.print("Enter address: ");
        line = scanner.nextLine();
        trainee.setAddress(line);

        System.out.print("Enter date of birth (yyyy-mm-dd): ");
        line = scanner.nextLine();

        while (!Validation.validateDate(line)){
            System.out.print("Not valid date. Enter again: ");
            line = scanner.nextLine();
        };
        trainee.setDateOfBirth(Date.valueOf(LocalDate.parse(line)));

        user.setIsActive(true);
        trainee.setUser(user);

        traineeService.save(trainee);

        System.out.println("\n Trainee created: ");
        System.out.println("- trainee id = " + trainee.getId());
        System.out.println("- trainee username = " + trainee.getUser().getUserName());
        System.out.println("- trainee password = " + trainee.getUser().getPassword());
        System.out.println();
    }
}
