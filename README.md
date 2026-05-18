
# 🎮 TechCorp Duel – Turn-Based Strategy Game

## 📌 Project Description

**TechCorp Duel** is a turn-based business strategy game developed in Java.  
The player manages a technology company and competes against two AI-controlled companies.

The goal is no longer just to survive or earn money — it is to **strategically manage resources and complete all projects faster than your competitors**.

This project demonstrates:

- Object-Oriented Programming (OOP)
- Layered architecture (Engine / UI / Domain)
- Turn-based strategy design
- Decision-making systems over time
- System extension and improvement

---

## 🧠 Game Concept

You are the CEO of **TechCorp**.

You compete against:
- 🤖 NeoSoft AI  
- 🤖 CyberDynamics  

Each company:
- hires employees  
- works on projects  
- spends money  
- earns rewards  

Every turn represents a business cycle.

---

## 🎯 MAIN OBJECTIVE (UPDATED)

> ✅ **Win by completing ALL your projects before any AI company**

---

### ✅ You WIN if:
- You finish **all your projects first**

---

### ❌ You LOSE if:
- Any AI completes all their projects first  
- Your company collapses financially (cash ≤ -10000)  
- At the final turn (Turn 12), your company has lower value  

---

## 🕹️ How the Game Works

Each turn:

1. Player decision  
2. AI decision  
3. Work is processed  
4. Salaries are paid  
5. Revenue is collected  
6. Random event may happen  
7. Game checks for win/lose  

---

## ⚙️ Player Actions

Each turn, you can:

### 1️⃣ Assign Employees
- Assign **one or multiple employees** to a project
- Example input: 1, 2, 3

### 2️⃣ Start Project
- Projects only progress when **started**
- Projects need a team first

### 3️⃣ Skip Turn
- Useful for saving money or waiting

---

## 🧩 Game Systems

### 👨‍💻 Employees
- Have **skill** (affects progress)
- Have **salary** (cost per turn)
- Can be assigned to projects

---

### 📊 Projects

Projects have:
- Required work
- Progress
- Reward (money + reputation)

Progress is shown visually:

[██████░░░░░░░░░░░░] 30%

█ = completed work  
░ = remaining work  
- Can be **strategic** (important for winning)

---

### 💰 Economy

- Salaries paid only when projects are active
- Passive income each turn
- Finished projects give rewards
- Going negative is allowed… until:

> ❌ Bankruptcy threshold = **-10000**

---

### 🤖 AI System

- AI assigns employees automatically
- Difficulty levels:
  - 🟢 EASY → slow  
  - 🟡 MEDIUM → balanced  
  - 🔴 HARD → aggressive  

---

### 🎲 Random Events

Occur randomly:

- ⚠ **Market Crash** → lose money  
- 💰 **Bonus** → gain money  

---

## 🧠 Strategy Tips

- Do NOT start projects too early → salary cost ⚠  
- Assign multiple employees to finish faster ✅  
- Manage cash carefully  
- Focus on finishing projects quickly  
- Speed is often better than saving money  

---

## 🗂️ Project Structure
com.sdinternational
- domain     → Core logic (Company, Project, Employee)
- engine     → Game flow (GameEngine)
- ui         → Console interaction
- events     → Random events
- Main.java  → Entry point

---

## 🏗️ Architecture

The system is divided into 3 layers:

- **Engine (Control Layer)**  
  → Controls turns and game logic  

- **UI (Interaction Layer)**  
  → Handles user input/output  

- **Domain (Business Layer)**  
  → Core game rules and logic  

✅ This ensures clean, modular, extensible code.

---

## 🚀 Features (Final Version)

✔ Turn-based gameplay  
✔ 3 competing companies  
✔ Multi-employee assignment  
✔ Project lifecycle system  
✔ Economy system with risk  
✔ Random events system  
✔ Difficulty levels  
✔ Visual progress bars  
✔ Colored UI + panels  
✔ Animated turn transitions  
✔ Ranking system  
✔ Clear win/loss conditions  

---

## 📚 Learning Outcomes

This project demonstrates:

- Extending an existing codebase  
- Designing a complete system  
- Applying OOP principles  
- Managing complexity  
- Modeling real-world processes  

---

## ▶️ How to Run (IMPORTANT)

### ✅ Compile:
```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.sdinternational.Main"
