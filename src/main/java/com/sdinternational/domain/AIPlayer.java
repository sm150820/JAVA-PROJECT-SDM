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
                if (!company.getEmployees().isEmpty()) {
                    Employee e = company.getEmployees().get(0);
                    if (!project.getTeam().contains(e)) {
                        project.addEmployee(e);
                    }
                }
            }

            case MEDIUM -> {
                int half = company.getEmployees().size() / 2;

                for (int i = 0; i < half; i++) {
                    Employee e = company.getEmployees().get(i);

                    if (!project.getTeam().contains(e)) {
                        project.addEmployee(e);
                    }
                }
            }

            case HARD -> {
                for (Employee e : company.getEmployees()) {
                    if (!project.getTeam().contains(e)) {
                        project.addEmployee(e);
                    }
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