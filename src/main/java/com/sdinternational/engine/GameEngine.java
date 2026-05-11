package com.sdinternational.engine;

import com.sdinternational.domain.*;
import com.sdinternational.events.MarketCrashEvent;
import com.sdinternational.events.BonusEvent;
import com.sdinternational.ui.ConsoleUI;

import java.util.Random;

public class GameEngine {

    private Company company;
    private ConsoleUI ui;
    private boolean running;
    private int turn;
    private Random random;
    private int score;

    public GameEngine(Company company, ConsoleUI ui) {
        this.company = company;
        this.ui = ui;
        this.running = true;
        this.turn = 1;
        this.random = new Random();
        this.score = 0;
    }

    public void start() {

        while (running) {

            ui.showTurnHeader(turn);
            ui.showCompanyStatus(company);
            ui.showMainMenu();

            int choice = ui.readMenuChoice();

            handleChoice(choice);
        }

        ui.showMessage("Final Score: " + score);
    }

    private void handleChoice(int choice) {

        switch (choice) {

            case 1:
                ui.showCompanyStatus(company);
                break;

            case 2:
                startOneProject();
                break;

            case 3:
                assignEmployeeToProject();
                break;

            case 4:
                advanceTurn();
                break;

            case 5:
                saveGame();
                break;

            case 6:
                running = false;
                break;

            default:
                ui.showMessage("Invalid option.");
        }
    }

    private void startOneProject() {

        int index = ui.chooseProject(company.getProjects());

        if (index == -1) {
            ui.showMessage("Invalid selection.");
            return;
        }

        Project project = company.getProjects().get(index);

        try {
            project.start();
            ui.showMessage("Project started.");
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
        }
    }

    private void assignEmployeeToProject() {

        int projectIndex = ui.chooseProject(company.getProjects());
        if (projectIndex == -1) {
            ui.showMessage("Invalid project.");
            return;
        }

        int employeeIndex = ui.chooseEmployee(company.getEmployees());
        if (employeeIndex == -1) {
            ui.showMessage("Invalid employee.");
            return;
        }

        Project project = company.getProjects().get(projectIndex);
        Employee employee = company.getEmployees().get(employeeIndex);

        try {
            project.addEmployee(employee);
            ui.showMessage("Employee assigned.");
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
        }
    }

    private void advanceTurn() {

        company.nextTurn();

        ui.showMessage("Turn executed (projects + salaries)");

        int eventChance = random.nextInt(5);

        if (eventChance == 0) {
            new MarketCrashEvent().apply(company);
        } else if (eventChance == 1) {
            new BonusEvent().apply(company);
        }

        score += 10;
        turn++;

        if (company.isBankrupt()) {
            ui.showMessage("Bankrupt! Game Over.");
            running = false;
            return;
        }

        if (allProjectsFinished()) {
            ui.showMessage("All projects finished! YOU WIN!");
            running = false;
        }
    }

    private boolean allProjectsFinished() {

        if (company.getProjects().isEmpty()) return false;

        for (Project p : company.getProjects()) {
            if (!p.isFinished()) return false;
        }

        return true;
    }

    private void saveGame() {

        try {
            java.io.FileWriter writer = new java.io.FileWriter("save.txt");

            writer.write("Cash: " + company.getCash() + "\n");
            writer.write("Projects: " + company.getProjects().size() + "\n");

            writer.close();

            ui.showMessage("Game saved!");

        } catch (Exception e) {
            ui.showMessage("Error saving game.");
        }
    }
}
