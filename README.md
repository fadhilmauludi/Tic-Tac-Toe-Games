# Simple Tic-Tac-Toe Game with Java Swing, Login, and Statistics

## Student Information
- **Name:** Fadhil Mauludi
- **Student ID:** 5026251221
- **Class:** ES234211 – Programming Fundamental

---

## Project Description
This project is a simple Tic-Tac-Toe game built using Java Swing GUI.
The player competes against the computer (AI). The application includes login with database verification, game statistics tracking, and Top 5 scorer display.

---

## Features
- ✅ Login using MySQL database
- ✅ Play Tic-Tac-Toe against computer (Java Swing GUI)
- ✅ Smart computer AI (tries to win, blocks player)
- ✅ Record wins, losses, draws, and score
- ✅ Display personal statistics
- ✅ Display Top 5 scorers using JTable

## Score System
| Result | Score |
|--------|-------|
| Win    | +10   |
| Draw   | +3    |
| Lose   | +0    |

---

## Database
- **DBMS:** MySQL
- **Database name:** game_project
- **Table:** players (id, username, password, wins, losses, draws, score)

---

## How to Run

### Step 1: Setup Database
1. Open MySQL (XAMPP / MySQL Workbench)
2. Run the file `schema.sql` in phpMyAdmin or MySQL client

### Step 2: Add JDBC Driver
1. Download MySQL Connector/J from https://dev.mysql.com/downloads/connector/j/
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
- Run `Main.java` directly from your IDE

### Step 5: Login
- Default credentials: `student1` / `12345`

---

## Class Explanation

| Class | Responsibility |
|-------|---------------|
| `Main` | Entry point, opens LoginFrame |
| `DatabaseManager` | Handles JDBC MySQL connection |
| `Player` | Model class storing player data |
| `PlayerService` | Login, update statistics, get Top 5 |
| `GameLogic` | Move validation, win/draw detection, computer AI |
| `LoginFrame` | Swing login window |
| `MainMenuFrame` | Swing main menu window |
| `GameFrame` | Swing game window (3x3 board) |
| `StatisticsFrame` | Swing window for personal statistics |
| `TopScorersFrame` | Swing window with JTable for Top 5 |

---

## Program Flow

```
Main.java → LoginFrame → (authenticate) → MainMenuFrame
                                              ├── GameFrame (play Tic-Tac-Toe)
                                              ├── StatisticsFrame (view personal stats)
                                              └── TopScorersFrame (view Top 5 leaderboard)
```

---

### Tampilan Aplikasi Tic-Tac-Toe

* **Menu Login:** ![Halaman Login](Tic%20Tac%20Toe%20Games/img/Screenshot%20(397).png)
* **Notifikasi Login Berhasil:** ![Login Sukses](Tic%20Tac%20Toe%20Games/img/Screenshot%20(398).png)
* **Halaman Awal Game:** ![Home Page Game](Tic%20Tac%20Toe%20Games/img/Screenshot%20(403).png)
* **In Game:** ![In Game Activity](Tic%20Tac%20Toe%20Games/img/Screenshot%20(399).png)
* **Hasil Game:** ![Hasil Game](Tic%20Tac%20Toe%20Games/img/Screenshot%20(400).png)
* **Statistik Game Saya:** ![Your Stats](Tic%20Tac%20Toe%20Games/img/Screenshot%20(401).png)
* **Statistik Top 5 Pemain:** ![Top 5 Player](Tic%20Tac%20Toe%20Games/img/Screenshot%20(401).png)

## Video Link
- YouTube: [ISI LINK VIDEO KAMU]

## GitHub Link
- GitHub: [ISI LINK REPO KAMU]
