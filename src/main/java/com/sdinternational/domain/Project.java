package com.sdinternational.domain;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private String name;
    private int requiredWork;
    private int progress;
    private List<Employee> team;
    private ProjectStatus status;

    public Project(String name, int requiredWork) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Invalid project name.");
        }
        if (requiredWork <= 0) {
            throw new IllegalArgumentException("Required work must be positive.");
        }

        this.name = name;
        this.requiredWork = requiredWork;
        this.progress = 0;
        this.team = new ArrayList<>();
        this.status = ProjectStatus.PLANNED;
    }

    public void addEmployee(Employee employee) {
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }

        if (status != ProjectStatus.PLANNED) {
            throw new IllegalStateException("Cannot add employees after project started.");
        }

        if (team.contains(employee)) {
            throw new IllegalStateException("Employee already assigned.");
        }

        team.add(employee);
    }

    public void start() {

        if (team.isEmpty()) {
            throw new IllegalStateException("Cannot start project without employees.");
        }

        if (status != ProjectStatus.PLANNED) {
            throw new IllegalStateException("Project already started.");
        }

        status = ProjectStatus.IN_PROGRESS;
    }

    public void workOneTurn() {

        if (status != ProjectStatus.IN_PROGRESS) {
            return;
        }

        for (Employee e : team) {
            progress += e.work();
        }

        if (progress >= requiredWork) {
            progress = requiredWork;
            status = ProjectStatus.FINISHED;
        }
    }

    public boolean isFinished() {
        return status == ProjectStatus.FINISHED;
    }

    public String getName() {
        return name;
    }

    public int getRequiredWork() {
        return requiredWork;
    }

    public int getProgress() {
        return progress;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public List<Employee> getTeam() {
        return team;
    }
}
