package edu.java.hibernatetask.facade;

import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.entity.User;
import edu.java.hibernatetask.service.ServiceException;
import edu.java.hibernatetask.service.TrainerService;
import edu.java.hibernatetask.service.TrainingTypeService;
import edu.java.hibernatetask.utility.Validation;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
public class CreateTrainerItem implements MenuItem{
    private TrainerService trainerService;
    private TrainingTypeService trainingTypeService;
    private Scanner scanner;

    public CreateTrainerItem(TrainerService trainerService, TrainingTypeService trainingTypeService, Scanner scanner) {
        this.trainerService = trainerService;
        this.trainingTypeService = trainingTypeService;
        this.scanner = scanner;
    }

    @Override
    public String getName() {
        return "Create trainer profile";
    }

    @Override
    public void run() {
        Trainer trainer = new Trainer();
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

        int choice = -1;
        System.out.println("Specializations: ");
        int counter = 1;
        for(TrainingType trainingType : trainingTypeService.getAll()){
            System.out.println(counter++ + "  " + trainingType.getTrainingType());
        }
        System.out.println("Choose specialization from list above: ");
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
            scanner.nextLine();
        }

        while(choice < 1 || choice > trainingTypeService.getAll().size()){
            System.out.println("Choose specialization from list above: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            }
        }
        trainer.setSpecialization(trainingTypeService.getAll().get(--choice));

        user.setIsActive(true);
        trainer.setUser(user);

        try {
            trainerService.save(trainer);

            System.out.println("\n Trainer created: ");
            System.out.println("- trainer id = " + trainer.getId());
            System.out.println("- trainer username = " + trainer.getUser().getUserName());
            System.out.println("- trainer password = " + trainer.getUser().getPassword());
            System.out.println();

        } catch (ServiceException e) {
            System.out.println("\nError!!! Trainer not created.");
        }


    }
}
