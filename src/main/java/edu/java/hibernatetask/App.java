package edu.java.hibernatetask;

import edu.java.hibernatetask.config.SpringConfig;
import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.entity.User;
import edu.java.hibernatetask.service.ServiceException;
import edu.java.hibernatetask.service.TraineeService;
import edu.java.hibernatetask.service.TrainerService;
import edu.java.hibernatetask.service.UserService;
import edu.java.hibernatetask.utility.PasswordGenerator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.time.LocalDate;
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

        trainerService.save(trainer);

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
        System.out.println("Find trainer " + (trainerService.getTrainerByUserName("Coleman.Yates").get().getUser().getPassword()));

        System.out.println("---------------------------------------------------------------------------");
        Optional<Trainer> optionalTrainer = trainerService.usernameAndPasswordMatching("Coleman.Yates", "4415125129");
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

        trainer = trainerService.getTrainerByUserName("Coleman.Yates").get();
        System.out.println(trainer);
        password = PasswordGenerator.generatePassword();
        System.out.println("New password for " + trainer.getUser().getUserName() + "   " + password);
        trainer.getUser().setPassword(password);
        System.out.println(trainerService.changePassword(trainer));

        System.out.println("----------------------------------Trainer Change password-----------------------------------------");

        trainer = trainerService.getTrainerByUserName("Coleman.Yates").get();
        System.out.println(trainer);
        password = PasswordGenerator.generatePassword();
        System.out.println("New password for " + trainer.getUser().getUserName() + "   " + password);
        trainer.getUser().setPassword(password);
        System.out.println(trainerService.changePassword(trainer));

        System.out.println("----------------------------------Trainer update-----------------------------------------\n");

        trainer = trainerService.getTrainerByUserName("Coleman.Yates").get();
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

        trainer = trainerService.getTrainerByUserName("Kathleen.Carr").get();
        System.out.println(trainer);

        try {
            System.out.println(trainerService.changeStatus(trainer));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        trainer = trainerService.getTrainerByUserName("Kathleen.Carr").get();
        System.out.println(trainer);

        System.out.println("---------------------------------- Remove Trainee  -----------------------------------------\n");

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
            e.printStackTrace();
        }


//        entityManager.close();
//        entityManagerFactory.close();
    }
}
