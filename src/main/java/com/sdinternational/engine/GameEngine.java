package com.sdinternational.engine;

import com.sdinternational.domain.Company;
import com.sdinternational.domain.Employee;
import com.sdinternational.domain.Project;
import com.sdinternational.domain.ProjectStatus;
import com.sdinternational.ui.ConsoleUI;

public class GameEngine {

    private Company company;
    private ConsoleUI ui;
    private boolean running;
    private int turn;

    public GameEngine(Company company, ConsoleUI ui) {
        this.company = company;
        this.ui = ui;
        this.running = true;
        this.turn = 1;
    }

    public void start() {
        while (running) {

            ui.showTurnHeader(turn);
            ui.showCompanyStatus(company);
            ui.showMainMenu();

            int choice = ui.readMenuChoice();

            handleChoice(choice);

            if (running) {
                advanceTurn();
                turn++;
            }
        }
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
                workOnOneProject();
                break;

            case 4:
                assignEmployeeToProject();
                break;

            case 5:
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

        if (project.getStatus() == ProjectStatus.PLANNED) {
            project.start();
            ui.showMessage("Project started.");
        } else {
            ui.showMessage("Project cannot be started.");
        }
    }

    private void workOnOneProject() {
        int index = ui.chooseProject(company.getProjects());

        if (index == -1) {
            ui.showMessage("Invalid selection.");
            return;
        }

        Project project = company.getProjects().get(index);

        project.workOneTurn();
        ui.showMessage("Worked on project.");
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

        project.addEmployee(employee);
        ui.showMessage("Employee assigned to project.");
    }

    private void advanceTurn() {

        // Pay salaries
        company.paySalaries();
        ui.showMessage("Salaries paid!");

        // Check lose condition
        if (company.isBankrupt()) {
            ui.showMessage("You are bankrupt! Game Over.");
            running = false;
            return;
        }

        // Check win condition
        if (allProjectsFinished()) {
            ui.showMessage("All projects finished! Congratulations, you win!");
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
}