
# TechCorp Duel – Turn-Based Strategy Game

## Project Description

**TechCorp Duel** is a turn-based business strategy game developed in Java.  
The player manages a technology company and competes against two AI-controlled companies.

The goal is no longer just to survive or earn money — it is to **strategically manage resources and complete all projects faster than your competitors**.

The project was extended into a **web application using Spring Boot** and deployed publicly using **Render**, making it accessible through HTTP endpoints.

---

## Game Concept

You are the CEO of **TechCorp**.

You compete against:
- NeoSoft AI  
- CyberDynamics  

Each company:
- hires employees  
- works on projects  
- spends money  
- earns rewards  

Each turn represents a business cycle where you:
- Assign employees
- Start projects
- Manage resources

---

## MAIN OBJECTIVE

> **Win by completing ALL your projects before any AI company**

---

### You WIN if:
- You finish **all your projects first**

---

### You LOSE if:
- Any AI completes all their projects first  
- Your company collapses financially (cash ≤ -10000)  
- At the final turn (Turn 12), your company has lower value  

---

## How the Game Works

Each turn:

1. Player decision  
2. AI decision  
3. Work is processed  
4. Salaries are paid  
5. Revenue is collected  
6. Random event may happen  
7. Game checks for win/lose  

---

## Player Actions

Each turn, you can:

### 1️. Assign Employees
- Assign **one or multiple employees** to a project
- Example input: 1, 2, 3

### 2️. Start Project
- Projects only progress when **started**
- Projects need a team first

### 3️. Skip Turn
- Useful for saving money or waiting

---

## Game Systems

### Employees
- Have **skill** (affects progress)
- Have **salary** (cost per turn)
- Can be assigned to projects

### Projects

Projects have:
- Required work
- Progress
- Reward (money + reputation)

Progress is shown visually:

[██████░░░░░░░░░░░░] 30%

█ = completed work  
░ = remaining work  
- Can be **strategic** (important for winning)

### Economy

- Salaries paid only when projects are active
- Passive income each turn
- Finished projects give rewards
- Going negative is allowed… until:

> Bankruptcy threshold = **-10000**


### AI System

- AI assigns employees automatically
- Difficulty levels:
  - 🟢 EASY → slow  
  - 🟡 MEDIUM → balanced  
  - 🔴 HARD → aggressive  


### Random Events

Occur randomly:

- **Market Crash** → lose money  
- **Bonus** → gain money  

---

## Strategy Tips

- Do NOT start projects too early → salary cost 
- Assign multiple employees to finish faster 
- Manage cash carefully  
- Focus on finishing projects quickly  
- Speed is often better than saving money  

---

## Project Structure

com.sdinternational
domain     → Core logic (Company, Project, Employee)
engine     → Game flow and turn logic (GameEngine)
ui         → Console interaction
events     → Random events system
api        → Web layer (REST controllers)
Main.java  → Entry point for console gameplay

---

## Architecture

The system is divided into **4 layers**:

- **domain** → Contains core game rules and business logic  
- **engine** → Controls the game loop and turn execution  
- **ui** → Handles user interaction through the console  
- **api** → Exposes the application as a web service using Spring Boot  

---

## 🌐 Web Application Programming Interface (API)

The game is exposed as a **REST API**

### Endpoints:
The application is deployed using **Render and Docker**.

- `/` → API homepage (API status)
https://java-project-sdm.onrender.com
- `/health` → server status (Health check)
https://java-project-sdm.onrender.com/health
- `/game/state` → game info (basic game info)
https://java-project-sdm.onrender.com/game/state

---

## Deployment

### Mode 1 — Console Game (Play the Game)

#### Compile:
```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.sdinternational.Main"
```
---

### Mode 2 — Web API (Server Mode)
Expose system via HTTP
## Run Locally

```bash
mvn spring-boot:run
```
---

## Learning Outcomes

This project demonstrates:

- Extending an existing codebase  
- Designing a complete system  
- Applying OOP principles  
- Managing complexity  
- Modeling real-world processes  

---

