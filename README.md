# 🎮 Java Swing Tic-Tac-Toe & Player Statistics

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![GUI](https://img.shields.io/badge/GUI-Java_Swing-blue?style=for-the-badge)

> **Academic Project — ES234211 Programming Fundamental (E)** > 👤 **Author:** Fadhil Mauludi | 💳 **Student ID:** 5026251221

---

## 📖 Project Overview
A desktop-based Tic-Tac-Toe game application built with **Java Swing GUI**. This project goes beyond a simple game by integrating a **MySQL database** to handle user authentication (login), track individual player statistics, and display a global Top 5 leaderboard. Players compete against a smart computer AI designed to actively block and win.

---

## ✨ Key Features
- ✅ Login using MySQL database
- ✅ Play Tic-Tac-Toe against computer (Java Swing GUI)
- ✅ Smart computer AI (tries to win, blocks player)
- ✅ Record wins, losses, draws, and score
- ✅ Display personal statistics
- ✅ Display Top 5 scorers using JTable

### 🎯 Score System
| Match Result | Points Awarded |
|:---:|:---:|
| **Win** | `+10 Points` |
| **Draw** | `+3 Points` |
| **Lose** | `0 Points` |

---

## 🗄️ Database
- **DBMS:** MySQL
- **Database name:** `game_project`
- **Table:** `players` (id, username, password, wins, losses, draws, score)

---

## 🚀 How to Run

### Step 1: Setup Database
1. Open MySQL (XAMPP / MySQL Workbench).
2. Run the file `schema.sql` in phpMyAdmin or MySQL client.

### Step 2: Add JDBC Driver
1. Download MySQL Connector/J from [MySQL Downloads](https://dev.mysql.com/downloads/connector/j/).
2. Add the `.jar` file to your project classpath:
   - **IntelliJ IDEA:** File > Project Structure > Libraries > Add JAR
   - **VS Code:** Java: Configure Classpath > Add the JAR to Referenced Libraries
   - **Command Line:** Use `-cp` flag when compiling and running

### Step 3: Configure Database Connection
Open `DatabaseManager.java` and adjust:
```java
private static final String URL = "jdbc:mysql://localhost:3306/game_project";
private static final String USER = "root";
private static final String PASSWORD = ""; // your MySQL password
```

### Step 4: Compile and Run
**Option A — Command Line:**
```bash
javac -cp ".;path/to/mysql-connector-j-x.x.x.jar" *.java
java -cp ".;path/to/mysql-connector-j-x.x.x.jar" Main
```

**Option B — IDE:**
- Run `Main.java` directly from your IDE.

### Step 5: Login
- Default credentials: `student1` / `12345`

---

## 🧩 Class Explanation

| Class | Responsibility |
|---|---|
| `Main` | Entry point, opens LoginFrame |
| `DatabaseManager` | Handles JDBC MySQL connection |
| `Player` | Model class storing player data |
| `PlayerService` | Login, update statistics, get Top 5 |
| `GameLogic` | Move validation, win/draw detection, computer AI |
| `LoginFrame` | Swing login window |
| `MainMenuFrame`| Swing main menu window |
| `GameFrame` | Swing game window (3x3 board) |
| `StatisticsFrame`| Swing window for personal statistics |
| `TopScorersFrame`| Swing window with JTable for Top 5 |

---

## 🔄 Program Flow
```text
Main.java → LoginFrame → (authenticate) → MainMenuFrame
                                              ├── GameFrame (play Tic-Tac-Toe)
                                              ├── StatisticsFrame (view personal stats)
                                              └── TopScorersFrame (view Top 5 leaderboard)
```

---

## 📸 Tampilan Aplikasi Tic-Tac-Toe

<table>
  <tr>
    <td align="center"><b>Menu Login</b><br><img src="Tic%20Tac%20Toe%20Games/img/Screenshot%20(397).png" width="350" alt="Login"></td>
    <td align="center"><b>Notifikasi Login Berhasil</b><br><img src="Tic%20Tac%20Toe%20Games/img/Screenshot%20(398).png" width="350" alt="Login Success"></td>
  </tr>
  <tr>
    <td align="center"><b>Halaman Awal Game</b><br><img src="Tic%20Tac%20Toe%20Games/img/Screenshot%20(407).png" width="350" alt="Home"></td>
    <td align="center"><b>In Game</b><br><img src="Tic%20Tac%20Toe%20Games/img/Screenshot%20(399).png" width="350" alt="Gameplay"></td>
  </tr>
  <tr>
    <td align="center"><b>Hasil Game</b><br><img src="Tic%20Tac%20Toe%20Games/img/Screenshot%20(400).png" width="350" alt="Result"></td>
    <td align="center"><b>Statistik Game Saya</b><br><img src="Tic%20Tac%20Toe%20Games/img/Screenshot%20(401).png" width="350" alt="Stats"></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><b>Statistik Top 5 Pemain</b><br><img src="Tic%20Tac%20Toe%20Games/img/Screenshot%20(402).png" width="500" alt="Leaderboard"></td>
  </tr>
</table>

---

## 🎥 YouTube Video Demo
[![Demo Tic-Tac-Toe](https://img.youtube.com/vi/mZ7Fj1UAOcY/maxresdefault.jpg)](https://youtu.be/mZ7Fj1UAOcY)

## 🔗 GitHub Link
- **GitHub:** [Github Repositories](https://github.com/fadhilmauludi/Tic-Tac-Toe-Games.git)
