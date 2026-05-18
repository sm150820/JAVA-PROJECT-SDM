package com.sdinternational;

import com.sdinternational.domain.*;
import com.sdinternational.engine.GameEngine;
import com.sdinternational.ui.ConsoleUI;

public class Main {

    public static void main(String[] args) {

        // PLAYER
        Company player = new Company("TechCorp", 50000);

        player.hire(new Developer("Anna", 8, 7000));
        player.hire(new Tester("Piotr", 6, 6000));
        player.hire(new Manager("Ewa", 7, 9000));
        player.hire(new Developer("Lucas", 5, 5000));

        // PROJECTS
        player.startProject(new Project("AI Platform", 50, 30000, true));
        player.startProject(new Project("Mobile App", 30, 15000, false));

        // AI 1
        Company ai1Company = new Company("NeoSoft AI", 50000);
        ai1Company.hire(new Developer("BotDev1", 7, 6500));
        ai1Company.hire(new Tester("BotTester1", 5, 5500));

        ai1Company.startProject(new Project("Competing AI", 50, 30000, true));
        ai1Company.startProject(new Project("Cloud System", 30, 15000, false));

        // AI 2
        Company ai2Company = new Company("CyberDynamics", 50000);
        ai2Company.hire(new Developer("BotDev2", 6, 6000));
        ai2Company.hire(new Tester("BotTester2", 4, 5000));

        ai2Company.startProject(new Project("NextGen AI", 50, 30000, true));
        ai2Company.startProject(new Project("Web Platform", 30, 15000, false));

        AIPlayer ai1 = new AIPlayer(ai1Company, GameDifficulty.MEDIUM);
        AIPlayer ai2 = new AIPlayer(ai2Company, GameDifficulty.HARD);

        ConsoleUI ui = new ConsoleUI();

        GameEngine engine = new GameEngine(player, ai1, ai2, ui);

        engine.start();
    }
}