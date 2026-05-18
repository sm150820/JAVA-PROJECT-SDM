package com.sdinternational.ui;

import com.sdinternational.domain.Company;
import com.sdinternational.domain.Employee;
import com.sdinternational.domain.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);

    // COLORS
    private final String RESET = "\u001B[0m";
    private final String GREEN = "\u001B[32m";
    private final String RED = "\u001B[31m";
    private final String YELLOW = "\u001B[33m";
    private final String CYAN = "\u001B[36m";
    private final String PURPLE = "\u001B[35m";

    // ================= HEADER =================
    public void showTurnHeader(int turn) {
        System.out.println("\n" + CYAN + "====================================");
        System.out.println("         TECHCORP DUEL");
        System.out.println("====================================" + RESET);
        System.out.println("TURN: " + PURPLE + turn + RESET);
    }

    // ================= MENU =================
    public void showPlayerMenu() {
        System.out.println("\n" + CYAN + "------ ACTION MENU ------" + RESET);
        System.out.println("1. Assign employee(s)");
        System.out.println("2. Start project");
        System.out.println("3. Skip turn");
        System.out.println("--------------------------");
    }

    // ================= DIFFICULTY =================
    public int chooseDifficulty() {

        System.out.println(CYAN +"\n===== SELECT DIFFICULTY =====" + RESET);
        System.out.println("1. EASY" + RESET);
        System.out.println("2. MEDIUM" + RESET);
        System.out.println("3. HARD" + RESET);

        System.out.print("Enter choice: ");

        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return 2;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    // ================= INPUT =================
    public int readMenuChoice() {
        System.out.print("Your action → ");

        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    // ================= COMPANY VIEW =================
    public void showCompanyStatus(Company company) {

        System.out.println(PURPLE + "\n== " + company.getName() + " ==" + RESET);

        // CASH COLOR
        String cashColor;
        if (company.getCash() > 20000) cashColor = GREEN;
        else if (company.getCash() < 10000) cashColor = RED;
        else cashColor = YELLOW;

        System.out.println("Cash: " + cashColor + company.getCash() + RESET);
        System.out.println("Reputation: " + company.getReputation());

        // EMPLOYEES
        System.out.println("\nEmployees:");
        for (int i = 0; i < company.getEmployees().size(); i++) {
            Employee e = company.getEmployees().get(i);
            System.out.println((i + 1) + ". " + e.getName()
                    + " (Skill: " + e.getSkill()
                    + ", Salary: " + e.getSalary() + ")");
        }

        // PROJECTS
        System.out.println("\nProjects:");
        for (int i = 0; i < company.getProjects().size(); i++) {

            Project p = company.getProjects().get(i);

            int percent = (int)((double)p.getProgress() / p.getRequiredWork() * 100);
            String bar = buildProgressBar(percent);

            System.out.println((i + 1) + ". " + p.getName()
                    + " | " + p.getStatus()
                    + " | " + bar + " " + percent + "%");

            if (!p.getTeam().isEmpty()) {
                System.out.print("   Team: ");
                for (Employee e : p.getTeam()) {
                    System.out.print(e.getName() + " ");
                }
                System.out.println();
            }
        }
    }

    // ================= MULTI SELECT =================
    public List<Integer> chooseEmployees(List<Employee> employees) {

        List<Integer> selected = new ArrayList<>();

        if (employees.isEmpty()) return selected;

        System.out.println("\nSelect employees (example: 1,2,3):");

        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". "
                    + employees.get(i).getName()
                    + " (Skill: " + employees.get(i).getSkill() + ")");
        }

        System.out.print("→ ");

        String input = scanner.nextLine();
        String[] parts = input.split(",");

        for (String part : parts) {
            try {
                int index = Integer.parseInt(part.trim()) - 1;

                if (index >= 0 && index < employees.size()) {
                    selected.add(index);
                }
            } catch (Exception ignored) {}
        }

        return selected;
    }

    public int chooseProject(List<Project> projects) {

        if (projects.isEmpty()) return -1;

        System.out.println("\nSelect a project:");
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i).getName());
        }

        System.out.print("→ ");

        if (!scanner.hasNextInt()) {
            scanner.nextLine();
            return -1;
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > projects.size())
            return -1;

        return choice - 1;
    }

    // ================= LOADING =================
    public void showLoading() {

        System.out.print("\n Processing Turn");

        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(250);
                System.out.print(".");
            }
        } catch (InterruptedException ignored) {}

        System.out.println("\n");
    }

    // ================= PROGRESS BAR =================
    private String buildProgressBar(int percent) {

        int total = 20;
        int filled = (percent * total) / 100;

        StringBuilder bar = new StringBuilder("[");

        for (int i = 0; i < total; i++) {
            if (i < filled) bar.append("█");
            else bar.append("░");
        }

        bar.append("]");

        return bar.toString();
    }

    // ================= GENERIC MESSAGE =================
    public void showMessage(String message) {
        System.out.println(message);
    }
}
