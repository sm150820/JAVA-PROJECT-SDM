package com.sdinternational.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private int requiredWork;
    private int progress;

    private double reward;
    private boolean strategic;
    private boolean paid;

    private List<Employee> team;
    private ProjectStatus status;

    public Project(String name, int requiredWork, double reward, boolean strategic) {

        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Invalid project name");

        if (requiredWork <= 0)
            throw new IllegalArgumentException("Work must be positive");

        this.name = name;
        this.requiredWork = requiredWork;
        this.reward = reward;
        this.strategic = strategic;

        this.progress = 0;
        this.paid = false;

        this.team = new ArrayList<>();
        this.status = ProjectStatus.PLANNED;
    }

    public void addEmployee(Employee employee) {

        if (employee == null)
            throw new IllegalArgumentException("Employee null");

        if (team.contains(employee)) {
            throw new IllegalStateException("Employee already assigned.");
        }

        team.add(employee);
    }

    public void start() {
        if (team.isEmpty())
            throw new IllegalStateException("Assign employees first!");

        status = ProjectStatus.IN_PROGRESS;
    }

    public void workOneTurn() {

        if (status != ProjectStatus.IN_PROGRESS) return;

        for (Employee e : team) {
            progress += e.work();
        }

        if (progress >= requiredWork) {
            progress = requiredWork;
            status = ProjectStatus.FINISHED;
        }
    }

    public boolean isFinished() {
        return progress >= requiredWork;
    }

    public boolean isStrategic() {
        return strategic;
    }

    public double getReward() {
        return reward;
    }

    public boolean isPaid() {
        return paid;
    }

    public void markAsPaid() {
        paid = true;
    }

    public String getName() { return name; }
    public int getRequiredWork() { return requiredWork; }
    public int getProgress() { return progress; }
    public ProjectStatus getStatus() { return status; }
    public List<Employee> getTeam() { return team; }
}
