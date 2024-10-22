package edu.java.hibernatetask.facade;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<MenuItem> menuItems;
    private Scanner scanner;

    public Menu(Scanner scanner, List<MenuItem> menuItems) {
        this.scanner = scanner;
        this.menuItems = menuItems;
    }

    private void printMenu() {
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(i + 1 + ". " + menuItems.get(i).getName());
        }
        System.out.println(menuItems.size() + 1 + ". Quit");
    }

    private int getChoice() {
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return --choice;
        }
        scanner.nextLine();
        return -1;
    }

    public void run() {
        int choice;
        System.out.println("Hello." + System.lineSeparator());
        while (true) {
            printMenu();
            System.out.print("Choose menu item (enter number): ");
            choice = getChoice();
            if (choice < 0 || choice > menuItems.size()) {
                System.out.println("Choose menu item");
                continue;
            }
            if (choice == menuItems.size()) {
                break;
            }
            menuItems.get(choice).run();
        }
    }
}
