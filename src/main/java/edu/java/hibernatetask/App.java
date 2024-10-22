package edu.java.hibernatetask;

import edu.java.hibernatetask.config.SpringConfig;
import edu.java.hibernatetask.entity.*;
import edu.java.hibernatetask.service.*;
import edu.java.hibernatetask.service.impl.TrainingServiceImpl;
import edu.java.hibernatetask.utility.PasswordGenerator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        TrainerService trainerService = context.getBean("trainerServiceImpl", TrainerService.class);
//        UserService userService = context.getBean("userServiceImpl", UserService.class);
//
//        System.out.println(userService.getUserByUserName("Coleman.Yates"));

        System.out.println("Hello World!");
        Trainer trainer = new Trainer();
        trainer.setSpecialization(new TrainingType(2L, "fitness"));
        User user = new User();
        user.setFirstName("Fred");
        user.setLastName("Mercury");
        user.setIsActive(true);
        trainer.setUser(user);

        try {
            trainerService.save(trainer);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("trainer id = " + trainer.getId());
        System.out.println("trainer user id = " + trainer.getUser().getId());

        System.out.println("---------------------------------------------------------------------------");

        TraineeService traineeService = context.getBean("traineeServiceImpl", TraineeService.class);

        Trainee trainee = new Trainee();
        trainee.setAddress("Black street 28/36");
        trainee.setDateOfBirth(Date.valueOf(LocalDate.parse("2000-12-06")));

        user = new User();
        user.setFirstName("Fred");
        user.setLastName("Mercury");
        user.setIsActive(true);
        trainee.setUser(user);

        traineeService.save(trainee);

        System.out.println("trainee id = " + trainee.getId());
        System.out.println("trainee user id = " + trainee.getUser().getId());

        System.out.println("---------------------------------------------------------------------------");
        try {
            System.out.println("Find trainee " + (traineeService.getTraineeByUserName("Mari.Doyle").get().getUser().getPassword()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------------------------------------------------");
        Optional<Trainee> optionalTrainee = null;
        try {
            optionalTrainee = traineeService.usernameAndPasswordMatching("Mari.Doyle", "1753799703");
        } catch (ServiceException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Match trainee userName and password  " + (optionalTrainee.isPresent() ? optionalTrainee.get().getUser().getPassword() : "No matching!!!"));

        System.out.println("---------------------------------------------------------------------------");
        try {
            System.out.println("Find trainer " + (trainerService.getTrainerByUserName("Coleman.Yates").get().getUser().getPassword()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------------------------------------------------");
        Optional<Trainer> optionalTrainer = null;
        try {
            optionalTrainer = trainerService.usernameAndPasswordMatching("Coleman.Yates", "4415125129");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println("Match trainer userName and password  " + (optionalTrainer.isPresent() ? optionalTrainer.get().getUser().getPassword() : "No matching!!!"));

        System.out.println("----------------------------------Trainee Change password-----------------------------------------");

        try {
            trainee = traineeService.getTraineeByUserName("Mari.Doyle").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainee);
        String password = PasswordGenerator.generatePassword();
        System.out.println("New password for " + trainee.getUser().getUserName() + "   " + password);
        trainee.getUser().setPassword(password);
        System.out.println(traineeService.changePassword(trainee));

        System.out.println("----------------------------------Trainer Change password-----------------------------------------");

        try {
            trainer = trainerService.getTrainerByUserName("Coleman.Yates").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainer);
        password = PasswordGenerator.generatePassword();
        System.out.println("New password for " + trainer.getUser().getUserName() + "   " + password);
        trainer.getUser().setPassword(password);
        System.out.println(trainerService.changePassword(trainer));

        System.out.println("----------------------------------Trainer Change password-----------------------------------------");

        try {
            trainer = trainerService.getTrainerByUserName("Coleman.Yates").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainer);
        password = PasswordGenerator.generatePassword();
        System.out.println("New password for " + trainer.getUser().getUserName() + "   " + password);
        trainer.getUser().setPassword(password);
        System.out.println(trainerService.changePassword(trainer));

        System.out.println("----------------------------------Trainer update-----------------------------------------\n");

        try {
            trainer = trainerService.getTrainerByUserName("Coleman.Yates").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainer);

        trainer.getUser().setFirstName("Dasha");
        trainer.getUser().setLastName("Flower");
        trainer.getUser().setUserName("Bird");
        trainer.getSpecialization().setTrainingType("resistance");

        try {
            System.out.println(trainerService.update(trainer));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------- Trainee update -----------------------------------------\n");

        try {
            trainee = traineeService.getTraineeByUserName("Mari.Doyle").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainee);

        trainee.getUser().setFirstName("Irina");
        trainee.getUser().setLastName("Ortega");
        trainee.getUser().setUserName("Cow");

        trainee.setDateOfBirth(Date.valueOf(LocalDate.parse("2005-10-16")));
        trainee.setAddress("Serova st, 256, ap 45");

        System.out.println(traineeService.update(trainee));

        System.out.println("---------------------------------- Trainee change status  -----------------------------------------\n");

        try {
            trainee = traineeService.getTraineeByUserName("Dave.Batista").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainee);

        try {
            System.out.println(traineeService.changeStatus(trainee));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            trainee = traineeService.getTraineeByUserName("Dave.Batista").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainee);

        System.out.println("---------------------------------- Trainer change status  -----------------------------------------\n");

        try {
            trainer = trainerService.getTrainerByUserName("Kathleen.Carr").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainer);

        try {
            System.out.println(trainerService.changeStatus(trainer));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            trainer = trainerService.getTrainerByUserName("Kathleen.Carr").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainer);


        System.out.println("---------------------------------- Delete Trainee  -----------------------------------------\n");

        try {
            trainee = traineeService.getTraineeByUserName("Dave.Batista").get();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        System.out.println(trainee);

        try {
            traineeService.deleteByUsername("Dave.Batista");
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            trainee = traineeService.getTraineeByUserName("Dave.Batista").get();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("---------------------------------- Get Trainee Trainings List  -----------------------------------------\n");

        try {
            System.out.println(traineeService.getTrainings("Shannon.Velazquez",
                    Date.valueOf(LocalDate.parse("2024-10-21")),
                    Date.valueOf(LocalDate.parse("2024-11-21")),
                    "Ward",
                    new TrainingType(1L, "yoga")));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------- Get Trainer Trainings List  -----------------------------------------\n");

        try {
            System.out.println(trainerService.getTrainings("Ward.Mejia",
                    Date.valueOf(LocalDate.parse("2024-09-29")),
                    Date.valueOf(LocalDate.parse("2024-11-22")),
                    "Shannon"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------------- Add Training  -----------------------------------------\n");

        TrainingService trainingService = context.getBean("trainingServiceImpl", TrainingService.class);

        Training training = new Training();
        training.setTrainingDay(Date.valueOf(LocalDate.parse("2024-10-25")));
        training.setTrainingName("dynamic stretching");
        training.setTrainingDuration(45);
        training.setTrainingType(new TrainingType("stretching"));

        user = new User();
        user.setUserName("Igor.Gura");
        trainee = new Trainee();
        trainee.setUser(user);
        training.setTrainee(trainee);

        user = new User();
        user.setUserName("Frazier.Richards");
        trainer = new Trainer();
        trainer.setUser(user);
        training.setTrainer(trainer);

        try {
            System.out.println(trainingService.create(training));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("--------------- Get trainers list that not assigned on trainee by trainee's username----------\n");

        try {
            System.out.println(trainerService.getNotAssignedOnTraineeTrainersByTraineeUsername("Shannon.Velazquez"));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("--------------- Update Tranee's trainers list -------------\n");

        List<Trainer> trainerList = new ArrayList<>();

        user = new User();
        user.setUserName("Ward.Mejia");
        trainer = new Trainer();
        trainer.setUser(user);

        trainerList.add(trainer);

        try {
            System.out.println(traineeService.getTraineeByUserName("Igor.Gura").get().getTrainers());

            System.out.println(traineeService.updateTrainersList("Igor.Gura", trainerList).get().getTrainers());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

//        entityManager.close();
//        entityManagerFactory.close();
    }
}
