package com.sdinternational;

import com.sdinternational.domain.*;
import com.sdinternational.engine.GameEngine;
import com.sdinternational.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        Company company = new Company("SD International", 50000);

        Employee dev = new Developer("Anna", 8, 7000);
        Employee tester = new Tester("Piotr", 6, 6000);
        Employee manager = new Manager("Ewa", 7, 9000);

        company.hire(dev);
        company.hire(tester);
        company.hire(manager);

        Project p1 = new Project("Business App", 30);
        Project p2 = new Project("Website", 20);

        p1.addEmployee(dev);
        p1.addEmployee(tester);

        p2.addEmployee(manager);

        company.startProject(p1);
        company.startProject(p2);

        ConsoleUI ui = new ConsoleUI();
        GameEngine engine = new GameEngine(company, ui);

        engine.start();
    }
}
