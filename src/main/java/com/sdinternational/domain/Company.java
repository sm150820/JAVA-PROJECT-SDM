package com.sdinternational.domain;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;
    private double cash;
    private int reputation;

    private List<Employee> employees;
    private List<Project> projects;

    public Company(String name, double cash) {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Invalid name");

        this.name = name;
        this.cash = cash;
        this.reputation = 0;

        this.employees = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public void hire(Employee e) {
        employees.add(e);
    }

    public void startProject(Project p) {
        projects.add(p);
    }

    // MAIN TURN LOGIC
    public void nextTurn() {
        workOnProjects();
        paySalaries();
        collectRevenue();

        // passive income
        cash += 2000;
    }

    private void workOnProjects() {
        for (Project p : projects) {
            p.workOneTurn();
        }
    }

    // PAY ONLY ACTIVE PROJECTS
    private void paySalaries() {
        double total = 0;

        for (Project p : projects) {
            if (p.getStatus() == ProjectStatus.IN_PROGRESS) {
                for (Employee e : p.getTeam()) {
                    total += e.getSalary();
                }
            }
        }

        cash -= total;
    }

    // REWARD SYSTEM
    private void collectRevenue() {
        for (Project p : projects) {
            if (p.isFinished() && !p.isPaid()) {
                cash += p.getReward();
                reputation += 10;
                p.markAsPaid();
            }
        }
    }

    // EVENTS SYSTEM 
    public void reduceCash(double amount) {
        cash -= amount;
    }

    public boolean hasFinishedStrategicProject() {
        for (Project p : projects) {
            if (p.isStrategic() && p.isFinished()) return true;
        }
        return false;
    }

    public boolean isBankrupt() {
        return cash <= 0;
    }

    // FINAL VALUE
    public double calculateValue() {

        int finishedProjects = 0;

        for (Project p : projects) {
            if (p.isFinished()) {
                 finishedProjects++;
            }
        }

         // VALUE FORMULA
        return cash + (reputation * 1000) + (finishedProjects * 10000);
    }


    public String getName() { return name; }
    public double getCash() { return cash; }
    public int getReputation() { return reputation; }
    public List<Employee> getEmployees() { return employees; }
    public List<Project> getProjects() { return projects; }
}