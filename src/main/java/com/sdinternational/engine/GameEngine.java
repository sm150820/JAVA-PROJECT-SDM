package com.sdinternational.engine;

import com.sdinternational.domain.*;
import com.sdinternational.events.BonusEvent;
import com.sdinternational.events.MarketCrashEvent;
import com.sdinternational.ui.ConsoleUI;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

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
        ui.showMessage("        TECHCORP DUEL");
        ui.showMessage("====================================");

        ui.showMessage("🎯 GOAL: Complete ALL projects before your competitors!");
        ui.showMessage("💡 Manage money carefully and finish faster than AI.\n");

        while (running) {

            ui.showTurnHeader(turn);

            ui.showCompanyStatus(player);
            ui.showCompanyStatus(ai1.getCompany());
            ui.showCompanyStatus(ai2.getCompany());

            playerDecision();

            aiPhase();

            processTurn();

            // ✅ SHOW FULL UPDATED STATE
            ui.showMessage("\n📊 Updated status after this turn:");
            ui.showCompanyStatus(player);
            ui.showCompanyStatus(ai1.getCompany());
            ui.showCompanyStatus(ai2.getCompany());

            triggerEvent();

            checkGameEnd();

            turn++;
        }
    }

    // ================= PLAYER =================
    private void playerDecision() {

        ui.showPlayerMenu();
        int choice = ui.readMenuChoice();

        switch (choice) {
            case 1 -> assignEmployee();
            case 2 -> startProject();
            case 3 -> ui.showMessage("Skipping turn...");
            default -> ui.showMessage("Invalid option.");
        }
    }

    // ================= AI =================
    private void aiPhase() {
        ai1.makeDecision();
        ai2.makeDecision();
    }

    // ================= PROCESS =================
    private void processTurn() {

        ui.showLoading();

        player.nextTurn();
        ai1.getCompany().nextTurn();
        ai2.getCompany().nextTurn();

        ui.showMessage("✅ Work completed for this turn.");
    }

    // ================= ASSIGN =================
    private void assignEmployee() {

        int projectIndex = ui.chooseProject(player.getProjects());

        if (projectIndex == -1) {
            ui.showMessage("Cancelled.");
            return;
        }

        Project project = player.getProjects().get(projectIndex);

        List<Integer> indexes = ui.chooseEmployees(player.getEmployees());

        if (indexes.isEmpty()) {
            ui.showMessage("No employees selected.");
            return;
        }

        for (int index : indexes) {

            if (index < 0 || index >= player.getEmployees().size()) continue;

            Employee employee = player.getEmployees().get(index);

            try {
                project.addEmployee(employee);
                ui.showMessage("✅ Added: " + employee.getName());
            } catch (Exception e) {
                ui.showMessage("⚠ " + e.getMessage());
            }
        }
    }

    // ================= START =================
    private void startProject() {

        int p = ui.chooseProject(player.getProjects());

        if (p == -1) {
            ui.showMessage("Cancelled.");
            return;
        }

        try {
            player.getProjects().get(p).start();
            ui.showMessage("🚀 Project started.");
        } catch (Exception e) {
            ui.showMessage("⚠ " + e.getMessage());
        }
    }

    // ================= EVENTS =================
    private void triggerEvent() {

        int chance = random.nextInt(100);

        if (chance < 30) {

            if (random.nextBoolean()) {

                new MarketCrashEvent().apply(player);
                new MarketCrashEvent().apply(ai1.getCompany());
                new MarketCrashEvent().apply(ai2.getCompany());

                ui.showMessage("⚠ MARKET CRASH! (-5000)");

            } else {

                new BonusEvent().apply(player);
                new BonusEvent().apply(ai1.getCompany());
                new BonusEvent().apply(ai2.getCompany());

                ui.showMessage("💰 BONUS! (+3000)");
            }
        }
    }

    // ================= GAME END =================
    private void checkGameEnd() {

        Company c1 = ai1.getCompany();
        Company c2 = ai2.getCompany();

        // ✅ PLAYER WINS (ALL PROJECTS)
        if (allProjectsFinished(player)) {

            ui.showMessage("\n✅ All your projects are completed.");
            ui.showMessage("\n🏆 YOU WIN! All projects completed!");

            running = false;
            return;
        }

        // ✅ AI WINS
        if (allProjectsFinished(c1) || allProjectsFinished(c2)) {

            ui.showMessage("\n❌ YOU LOST! An AI completed all projects first.");

            running = false;
            return;
        }

        // ✅ CONTINUE GAME
        if (turn < maxTurns && player.getCash() > -10000) {
            return;
        }

        // ✅ FINAL RANKING
        ui.showMessage("\n====================================");
        ui.showMessage("           GAME OVER");
        ui.showMessage("====================================");

        double pv = player.calculateValue();
        double v1 = c1.calculateValue();
        double v2 = c2.calculateValue();

        List<String> ranking = new ArrayList<>();
        ranking.add("YOU: " + pv);
        ranking.add("AI1: " + v1);
        ranking.add("AI2: " + v2);

        ranking.sort((a, b) -> {
            double va = Double.parseDouble(a.split(": ")[1]);
            double vb = Double.parseDouble(b.split(": ")[1]);
            return Double.compare(vb, va);
        });

        ui.showMessage("\n===== FINAL RANKING =====");

        for (int i = 0; i < ranking.size(); i++) {

            String medal = (i == 0) ? "🥇"
                    : (i == 1) ? "🥈"
                    : "🥉";

            ui.showMessage(medal + " " + ranking.get(i));
        }

        // ✅ FINAL RESULT
        if (ranking.get(0).startsWith("YOU")) {

            if (player.getCash() <= 0) {
                ui.showMessage("\n💀 YOU WIN... BUT WITH DEBT!");
            } else {
                ui.showMessage("\n🎉 CONGRATULATIONS! YOU WIN!");
            }

        } else {

            if (player.getCash() <= -10000) {
                ui.showMessage("\n💸 YOU COLLAPSED FINANCIALLY!");
            } else {
                ui.showMessage("\n❌ YOU LOST! AI dominated the market.");
            }
        }

        running = false;
    }

    // ✅ CHECK ALL PROJECTS
    private boolean allProjectsFinished(Company company) {

        for (Project p : company.getProjects()) {
            if (!p.isFinished()) {
                return false;
            }
        }

        return true;
    }
}