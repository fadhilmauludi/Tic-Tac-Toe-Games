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
- 🔐 **Secure Login:** User authentication verified against a MySQL database.
- 🤖 **Smart AI Opponent:** A dynamic computer player that actively tries to win and block player moves.
- 📊 **Stat Tracking:** Automatically records Wins, Losses, Draws, and Total Score.
- 🏆 **Global Leaderboard:** View the Top 5 highest-scoring players directly from the database using a `JTable`.
- 🖥️ **Interactive GUI:** Clean and intuitive graphical interface using Java Swing.

### 🎯 Score System
| Match Result | Points Awarded |
|:---:|:---:|
| **Win** | `+10 Points` |
| **Draw** | `+3 Points` |
| **Lose** | `0 Points` |

---

## 🛠️ System Architecture

### Program Flow
```text
Main.java 
 └── LoginFrame 
      └── (authenticate) 
           └── MainMenuFrame
                ├── GameFrame (Play Tic-Tac-Toe)
                ├── StatisticsFrame (View personal stats)
                └── TopScorersFrame (View Top 5 leaderboard)
