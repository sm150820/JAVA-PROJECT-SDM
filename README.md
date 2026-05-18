# TechCorp Duel – Turn-Based Strategy Game

## Project Description

**TechCorp Duel** is a turn-based business strategy game developed in Java.  
The player manages a technology company and competes against two AI-controlled companies.

The objective is to:
- Build the strongest company
- Complete strategic projects before competitors
- Manage employees, money, and decisions over time

This project demonstrates:
- Object-Oriented Programming (OOP)
- Layered architecture (Engine / UI / Domain)
- Turn-based game mechanics
- Decision-making systems

---

## Game Concept

You are the CEO of **TechCorp**.  
Your competitors (**NeoSoft AI** and **CyberDynamics**) are racing to dominate the tech market.

Each turn represents a business cycle where:
- You assign employees to projects
- You decide when to start projects
- Your company earns money or loses money based on decisions
- AI competitors act automatically

---

## Objectives

You can win in three ways:

- Complete the **strategic project** before competitors  
- Have the **highest company value** after the final turn  
- Survive economically while others fail (bankruptcy)

You lose if:
- You go bankrupt  
- An AI finishes the strategic project first  
- Your company is weaker at the end of the game  

---

## How to Play

### Step 1 – Start the Game
Run the application and follow the console prompts.

---

### Step 2 – Manage Your Company

Each turn, you can:

1. **Assign employees**
   - Choose which project they should work on

2. **Start a project**
   - Projects only progress when started
   - Employees must be assigned first

3. **Skip Turn**
   - Take no action

---

### Step 3 – Understand the System

#### Employees
- Each employee has:
  - Skill (affects progress)
  - Salary (cost per turn)

#### Projects
- Require work to complete
- Show progress with visual bars:

[██████░░░░░░░░░░░░] 30%

█ = completed work  
░ = remaining work  
- Can be **strategic** (important for winning)

#### Economy
- Salaries are only paid when projects are active
- Passive income is generated each turn
- Finished projects generate rewards

---

### 🔹 Step 4 – Compete Against AI

- **AI Companies act automatically**
- Difficulty levels affect their behavior:
- EASY → slow decisions
- MEDIUM → balanced strategy
- HARD → aggressive and optimized

---

### 🔹 Step 5 – Random Events

Every few turns, events may occur:

- **Market Crash** → lose money  
- **Bonus** → gain money  

These make the game dynamic and unpredictable.

---

## Project Structure
com.sdinternational
│
├── domain     → Core game logic (Company, Project, Employee)
├── engine     → Game flow controller (GameEngine)
├── ui         → Console interaction with the player
├── events     → Random event system (Bonus, Market Crash)
└── Main.java  → Entry point of the application
---

## Architecture

The project follows a layered design:

- **Engine (Control Layer)** → manages turns and game flow
- **UI (Interaction Layer)** → handles player input/output
- **Domain (Business Logic)** → contains core game rules

This ensures:
- Separation of concerns
- Clean structure
- Easy extensibility

---

## Features

+ Turn-based gameplay  
+ Multiple competing companies (Player vs AI)  
+ Employee management system  
+ Project lifecycle system  
+ Economic system (costs, rewards)  
+ Random events  
+ Difficulty levels  
+ Visual progress bars  

---

## Learning Outcomes

This project helps practice:
- Object-Oriented Design
- System architecture modeling
- Game logic structuring
- Extending existing codebases

---

## How to Run

1. Clone the repository
2. Open in VS Code or IntelliJ
3. Run:

```bash
mvn compile
mvn exec:java