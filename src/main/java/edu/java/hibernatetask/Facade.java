package edu.java.hibernatetask;

import edu.java.hibernatetask.config.SpringConfig;
import edu.java.hibernatetask.facade.Menu;
import edu.java.hibernatetask.facade.MenuItem;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Facade {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        MenuItem menuItem;

        Scanner scanner = new Scanner(System.in);
        List<MenuItem> menuItems = new ArrayList<>();
        Menu menu = new Menu(scanner, menuItems);

        menuItem = context.getBean("createTraineeItem", MenuItem.class);
        menuItems.add(menuItem);
        menuItem = context.getBean("createTrainerItem", MenuItem.class);
        menuItems.add(menuItem);

        menu.run();
    }
}