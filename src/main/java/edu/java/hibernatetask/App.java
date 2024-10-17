package edu.java.hibernatetask;

import edu.java.hibernatetask.config.SpringConfig;
import edu.java.hibernatetask.entity.Trainee;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.entity.User;
import edu.java.hibernatetask.service.TraineeService;
import edu.java.hibernatetask.service.TrainerService;
import edu.java.hibernatetask.service.impl.TrainerServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        TrainerService trainerService = context.getBean("trainerServiceImpl", TrainerService.class);

        System.out.println( "Hello World!" );
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
        System.out.println("Find trainee " + (traineeService.getTraineeByUserName("Mari.Doyle").get().getUser().getPassword()));

        System.out.println("---------------------------------------------------------------------------");
        Optional<Trainee> optionalTrainee = traineeService.usernameAndPasswordMatching("Mari.Doyle", "1753799703");
        System.out.println("Match trainee userName and password  " + (optionalTrainee.isPresent() ? optionalTrainee.get().getUser().getPassword() : "No matching!!!"));

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Find trainer " + (trainerService.getTrainerByUserName("Coleman.Yates").get().getUser().getPassword()));

        System.out.println("---------------------------------------------------------------------------");
        Optional<Trainer> optionalTrainer = trainerService.usernameAndPasswordMatching("Coleman.Yates", "4415125129");
        System.out.println("Match trainer userName and password  " + (optionalTrainer.isPresent() ? optionalTrainer.get().getUser().getPassword() : "No matching!!!"));

//        entityManager.close();
//        entityManagerFactory.close();
    }
}
