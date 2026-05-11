package com.sdinternational.ui;

import com.sdinternational.domain.Company;
import com.sdinternational.domain.Employee;
import com.sdinternational.domain.Project;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);

    public void showTurnHeader(int turn) {
        System.out.println("\n====================================");
        System.out.println("   SD INTERNATIONAL BUSINESS GAME");
        System.out.println("====================================");
        System.out.println("TURN " + turn);
    }

    public void showMainMenu() {
        System.out.println("\nChoose an action:");
        System.out.println("1. Show company status");
        System.out.println("2. Start a project");
        System.out.println("3. Assign employee to project");
        System.out.println("4. Advance turn");
        System.out.println("5. Save game");
        System.out.println("6. Exit game");
    }

    public int readMenuChoice() {
        System.out.print("Enter choice: ");

        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public void showCompanyStatus(Company company) {

        System.out.println("\nCompany: " + company.getName());
        System.out.println("Cash: " + company.getCash());

        System.out.println("\nEmployees:");
        for (int i = 0; i < company.getEmployees().size(); i++) {
            Employee e = company.getEmployees().get(i);
            System.out.println((i + 1) + ". " + e.getName() + " (Skill: " + e.getSkill() + ")");
        }

        System.out.println("\nProjects:");
        for (int i = 0; i < company.getProjects().size(); i++) {
            Project p = company.getProjects().get(i);
            System.out.println((i + 1) + ". " + p.getName()
                    + " | " + p.getStatus()
                    + " | " + p.getProgress() + "/" + p.getRequiredWork());
        }
    }

    public int chooseProject(List<Project> projects) {

        if (projects.isEmpty()) return -1;

        System.out.println("\nChoose a project:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i).getName());
        }

        System.out.print("Enter number: ");

        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > projects.size()) return -1;

        return choice - 1;
    }

    public int chooseEmployee(List<Employee> employees) {

        if (employees.isEmpty()) return -1;

        System.out.println("\nChoose an employee:");
        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". " + employees.get(i).getName());
        }

        System.out.print("Enter number: ");

        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > employees.size()) return -1;

        return choice - 1;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
