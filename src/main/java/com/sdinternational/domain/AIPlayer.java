package com.sdinternational.domain;

public class AIPlayer {

    private Company company;
    private GameDifficulty difficulty;

    public AIPlayer(Company company, GameDifficulty difficulty) {
        this.company = company;
        this.difficulty = difficulty;
    }

    public void makeDecision() {

        if (company.getProjects().isEmpty()) return;

        Project project = company.getProjects().get(0);

        switch (difficulty) {

            case EASY -> {
                // only assign 1 employee
                if (!company.getEmployees().isEmpty()) {
                    project.addEmployee(company.getEmployees().get(0));
                }
            }

            case MEDIUM -> {
                // assign half employees
                int half = company.getEmployees().size() / 2;

                for (int i = 0; i < half; i++) {
                    project.addEmployee(company.getEmployees().get(i));
                }
            }

            case HARD -> {
                // assign all employees + aggressive start
                for (Employee e : company.getEmployees()) {
                    project.addEmployee(e);
                }
            }
        }

        try {
            project.start();
        } catch (Exception ignored) {}
    }

    public Company getCompany() {
        return company;
    }
}