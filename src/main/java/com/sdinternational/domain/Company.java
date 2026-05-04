package com.sdinternational.domain;

import java.util.ArrayList;
import java.util.List;

public class Company {

    private String name;
    private double cash;
    private List<Employee> employees;
    private List<Project> projects;

    public Company(String name, double cash) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid company name.");
        }
        if (cash < 0) {
            throw new IllegalArgumentException("Cash cannot be negative.");
        }

        this.name = name;
        this.cash = cash;
        this.employees = new ArrayList<>();
        this.projects = new ArrayList<>();
    }

    public void hire(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        employees.add(employee);
    }

    public void startProject(Project project) {
        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }
        projects.add(project);
    }

    // PAY SALARIES EACH TURN
    public void paySalaries() {
        double total = 0;

        for (Employee e : employees) {
            total += e.getSalary();
        }

        cash -= total;
    }

    public boolean isBankrupt() {
        return cash <= 0;
    }

    public String getName() {
        return name;
    }

    public double getCash() {
        return cash;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Project> getProjects() {
        return projects;
    }
}