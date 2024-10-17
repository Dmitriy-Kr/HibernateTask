package edu.java.hibernatetask;

import edu.java.hibernatetask.config.SpringConfig;
import edu.java.hibernatetask.entity.Trainer;
import edu.java.hibernatetask.entity.TrainingType;
import edu.java.hibernatetask.entity.User;
import edu.java.hibernatetask.service.TrainerService;
import edu.java.hibernatetask.service.impl.TrainerServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

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

//        entityManager.close();
//        entityManagerFactory.close();
    }
}
