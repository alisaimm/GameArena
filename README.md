# 🎮 GameArena

GameArena is a Java console-based mini game hub developed as a **first semester Java project**.  
The project is available both as **source code** and as a **standalone Windows executable (EXE)**.

## 📌 Features
- Menu-driven console interface
- Multiple games accessible from a single platform:
  - Hangman
  - Memory Match
  - Word Scramble
  - Tic Tac Toe
- File-based game data and record management
- Input validation with exception handling
- Persistent records using text files
- Standalone Windows executable (no Java installation required)

## 🧠 Concepts Used
- Java Classes and Methods
- Loops and Conditional Statements
- Arrays and Strings
- File Handling (`Scanner`, `PrintWriter`)
- Exception Handling

## 📂 Project Structure
GameArena/
 ├── GameArena.java
 ├── data/
 │   ├── city.txt
 │   ├── country.txt
 │   ├── fruit.txt
 │   ├── input.txt
 │   └── record.txt
 ├── .gitignore
 └── README.md

## ▶️ Run Using Source Code
1. Clone the repository  
   git clone https://github.com/alisaimm/GameArena.git
2. Open the project in any Java IDE
3. Ensure the data folder is in the same directory
4. Compile and run  
   javac GameArena.java  
   java GameArena

## 🪟 Run Using Windows EXE
1. Go to the **Releases** section of this repository
2. Download `GameArena-Windows-x64.zip`
3. Extract the ZIP file
4. Run `GameArena.exe`

No Java installation is required for the EXE version.

## 📝 Notes
- The EXE version was built using `jlink`
- Do not delete or rename the `data` folder
- The application is designed for console/terminal use
- Compiled `.class` files are ignored using `.gitignore`

## 🚀 Future Improvements
- Refactor code into multiple classes
- Add a graphical user interface (JavaFX or Swing)
- Improve record and score management
- Add more games to the platform

## 👤 Author
Ali Saim Salehzadeh
First Semester Java Project

## 📜 License
This project is created for educational purposes only.