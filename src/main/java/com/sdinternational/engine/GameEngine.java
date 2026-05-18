package com.sdinternational.engine;

import com.sdinternational.domain.*;
import com.sdinternational.events.BonusEvent;
import com.sdinternational.events.MarketCrashEvent;
import com.sdinternational.ui.ConsoleUI;

import java.util.Random;

public class GameEngine {

    private Company player;
    private AIPlayer ai1;
    private AIPlayer ai2;

    private ConsoleUI ui;

    private int turn = 1;
    private int maxTurns = 12;
    private boolean running = true;

    private Random random = new Random();

    public GameEngine(Company player, AIPlayer ai1, AIPlayer ai2, ConsoleUI ui) {
        this.player = player;
        this.ai1 = ai1;
        this.ai2 = ai2;
        this.ui = ui;
    }

    public void start() {

        ui.showMessage("====================================");
        ui.showMessage("   TECHCORP DUEL - 3 COMPANIES");
        ui.showMessage("====================================");

        // ✅ MAIN GAME LOOP
        while (running) {

            showState();

            playerPhase();

            aiPhase();

            processTurn();

            triggerEvent();

            checkGameEnd();

            turn++;
        }

        showFinalMessage();
    }

    // ✅ SHOW STATE (CLEAN SEPARATION)
    private void showState() {
        ui.showTurnHeader(turn);

        ui.showCompanyStatus(player);
        ui.showCompanyStatus(ai1.getCompany());
        ui.showCompanyStatus(ai2.getCompany());

        // Warning system
        if (player.getCash() < 15000 && player.getCash() > 0) {
            ui.showMessage("⚠ WARNING: Low cash!");
        }
    }

    // ✅ PLAYER DECISION PHASE
    private void playerPhase() {
        ui.showPlayerMenu();

        int choice = ui.readMenuChoice();

        switch (choice) {
            case 1 -> assignEmployee();
            case 2 -> startProject();
            case 3 -> ui.showMessage("Skipping turn...");
            default -> ui.showMessage("Invalid choice.");
        }
    }

    // ✅ AI DECISION PHASE
    private void aiPhase() {
        ai1.makeDecision();
        ai2.makeDecision();
    }

    // ✅ CORE TURN LOGIC
    private void processTurn() {

        ui.showMessage("\n--- Processing Turn ---");

        // Work + salaries + revenue
        player.nextTurn();
        ai1.getCompany().nextTurn();
        ai2.getCompany().nextTurn();

        ui.showMessage("Projects progressed, salaries paid, income received.");
    }

    // ✅ EVENT SYSTEM
    private void triggerEvent() {

        int chance = random.nextInt(100);

        if (chance < 30) {

            int type = random.nextInt(2);

            if (type == 0) {
                new MarketCrashEvent().apply(player);
                new MarketCrashEvent().apply(ai1.getCompany());
                new MarketCrashEvent().apply(ai2.getCompany());

                ui.showMessage("⚠ MARKET CRASH! All companies lose 5000");
            } else {
                new BonusEvent().apply(player);
                new BonusEvent().apply(ai1.getCompany());
                new BonusEvent().apply(ai2.getCompany());

                ui.showMessage("💰 BONUS! All companies gain 3000");
            }
        }
    }

    // ✅ PLAYER ACTIONS
    private void assignEmployee() {

        int p = ui.chooseProject(player.getProjects());
        int e = ui.chooseEmployee(player.getEmployees());

        if (p == -1 || e == -1) {
            ui.showMessage("Assignment cancelled.");
            return;
        }

        try {
            Project project = player.getProjects().get(p);
            Employee employee = player.getEmployees().get(e);

            project.addEmployee(employee);

            ui.showMessage("Employee assigned.");
        } catch (Exception ex) {
            ui.showMessage(ex.getMessage());
        }
    }

    private void startProject() {

        int p = ui.chooseProject(player.getProjects());

        if (p == -1) {
            ui.showMessage("Action cancelled.");
            return;
        }

        try {
            player.getProjects().get(p).start();
            ui.showMessage("Project started.");
        } catch (Exception ex) {
            ui.showMessage(ex.getMessage());
        }
    }

    // ✅ GAME END CONDITIONS
    private void checkGameEnd() {

        Company c1 = ai1.getCompany();
        Company c2 = ai2.getCompany();

        // ✅ Strategic project win
        if (player.hasFinishedStrategicProject()) {
            ui.showMessage("\n✅ YOU WIN (Strategic project completed)");
            running = false;
            return;
        }

        if (c1.hasFinishedStrategicProject() || c2.hasFinishedStrategicProject()) {
            ui.showMessage("\n❌ YOU LOST (AI completed strategic project)");
            running = false;
            return;
        }

        // ✅ Bankruptcy
        if (player.isBankrupt()) {
            ui.showMessage("\n❌ YOU LOST (Bankrupt)");
            running = false;
            return;
        }

        // ✅ End by turns
        if (turn >= maxTurns) {

            double pv = player.calculateValue();
            double v1 = c1.calculateValue();
            double v2 = c2.calculateValue();

            ui.showMessage("\n=== FINAL RESULTS ===");
            ui.showMessage("Your value: " + pv);
            ui.showMessage("AI1 value: " + v1);
            ui.showMessage("AI2 value: " + v2);

            if (pv > v1 && pv > v2) {
                ui.showMessage("✅ YOU WIN!");
            } else {
                ui.showMessage("❌ YOU LOST!");
            }

            running = false;
        }
    }

    // ✅ FINAL MESSAGE
    private void showFinalMessage() {
        ui.showMessage("\n====================================");
        ui.showMessage("           GAME OVER");
        ui.showMessage("====================================");
    }
}
