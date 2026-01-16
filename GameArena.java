import java.util.*;
import java.io.*;

public class GameArena {

    static Scanner scn = new Scanner(System.in);

    //Methods for Memory Match
    public static void arrayPopulate(String [][]emojiArr, int shapesCount){
        int rowLength = emojiArr.length, colLength = emojiArr[0].length;
        char randChar = ' ';
        char usedChars[] = new char[(shapesCount / 2)];
        int usedCharSaveAt = 0;
        int randIndex1 = -1, randIndex2 = -1, randIndex3 = -1, randIndex4 = -1;
        int usedIndexes[] = new int[(shapesCount) * 2];
        int indexCounter = 0;
        boolean cont = false;

        for (int i = 0; i < (shapesCount / 2); i++) {
            while (true) {
                cont = false;
                // Unicode range of emojis used: 9984 <----> 10175
                randChar = (char) (9984 + (int) (Math.random() * 192));

                for (int j = 0; j < usedCharSaveAt; j++) {
                    if (randChar == usedChars[j]) {
                        cont = true;
                        break;
                    }
                }

                if (cont) continue;

                usedChars[usedCharSaveAt++] = randChar;
                break;
            }

            while (true) {
                cont = false;

                randIndex1 = (int) (Math.random() * rowLength); // row
                randIndex2 = (int) (Math.random() * colLength); // col
                randIndex3 = (int) (Math.random() * rowLength); // row
                randIndex4 = (int) (Math.random() * colLength); // col

                if (randIndex1 == randIndex3 && randIndex2 == randIndex4) {
                    continue;
                }

                for (int k = 0; k < indexCounter; k += 2) {
                    int usedRowIndex = usedIndexes[k];
                    int usedColIndex = usedIndexes[(k + 1)];

                    if ((usedRowIndex == randIndex1 && usedColIndex == randIndex2) || (usedRowIndex == randIndex3 && usedColIndex == randIndex4)) {
                        cont = true;
                        break;
                    }
                }

                if (cont) continue;

                usedIndexes[indexCounter++] = randIndex1; // row 1 [row]
                usedIndexes[indexCounter++] = randIndex2; // col 1 [col]
                usedIndexes[indexCounter++] = randIndex3; // row 2 [row]
                usedIndexes[indexCounter++] = randIndex4; // col 2 [col]

                emojiArr[randIndex1][randIndex2] = (randChar + "");
                emojiArr[randIndex3][randIndex4] = (randChar + "");

                break;
            }
        }
    }

    public static void printGrid(String arr[][]) {
        System.out.println("\n====MEMORY GRID====");

        for (int i = 0; i < (arr.length); i++) {
            System.out.print("-------");
        }
        System.out.println();

        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr[j].length; i++) {
                System.out.printf(" [ %2s ]", arr[j][i]);
            }
            System.out.println();
        }

        for (int i = 1; i <= (arr.length); i++) {
            System.out.print("-------");
        }
        System.out.println();
    }

    // Methods for Word Scramble
    public static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getRandomWord(String fileName){
        String randomWord = "";
        int wordCount = 0;
        int randWordIndex = -1;

        try{
            Scanner readwords = new Scanner(new FileInputStream(fileName));
            while (readwords.hasNext()){
                readwords.next();
                wordCount++;
            }
            randWordIndex = (int) (Math.random() * wordCount);
            readwords.close();

            Scanner read = new Scanner(new FileInputStream(fileName));

            for (int i = 0; i < randWordIndex; i++){
                read.next();
            }

            randomWord = read.next();
            read.close();
        } catch (Exception e){
            System.out.println("Some Exception Occured....");
            e.printStackTrace();
        }
        return randomWord;
    }

    public static String getScrambleWord(String orgWord){
        char temp = ' ';
        int wordLength = orgWord.length();
        int rand = -1;
        char scrambledChars[] = new char[wordLength];

        for (int i = 0; i < scrambledChars.length; i++){
            scrambledChars[i] = orgWord.charAt(i);
        }

        for (int i = 0; i < scrambledChars.length; i++){
            rand = (int) (Math.random()*scrambledChars.length);
            temp = scrambledChars[i];
            scrambledChars[i] = scrambledChars[rand];
            scrambledChars[rand] = temp;
        }
        
        String scrambledWord = new String(scrambledChars);
        if (scrambledWord.equals(orgWord)) scrambledWord = getScrambleWord(orgWord);
        return scrambledWord;
    }

    public static String getFileName(int n){
        String fileName = "";
        int choice = 0;

        if (n == 4) choice = (int) (Math.random()*3) + 1;
        else choice = n;

        switch (choice) {
            case 1:
                fileName = "data/country.txt";
                break;
        
            case 2:
                fileName = "data/fruit.txt";
                break;

            case 3:
                fileName = "data/city.txt";
                break;
        }
        return fileName;
    }

    // Methods for TicTacToe

    public static char getOtherSymbol(char a){
        return a == 'X' ? 'O' : 'X';
    }

    public static char getSymbol(){
        String inp = " ";
        char symbol = ' ';

        do {
            inp = scn.next().toUpperCase();
            if (inp.length() != 1){
                System.out.print("Wrong Input\nEnter only 1 Character (X/O): ");
                continue;
            }
            symbol = inp.charAt(0);
            if (symbol != 'O' && symbol != 'X'){
                System.out.print("Wrong Input\nEnter Correct Symbol (X/O)...\nEnter Again: ");
                continue;
            }
            break;
        } while (true);

        return symbol;
    }

    public static String getName(){
        scn.nextLine();
        return scn.nextLine();
    }

    public static void displayInfo(String playerOneName, String playerTwoName, char playerOneSymbol, char playerTwoSymbol){
        System.out.printf("=========================\n" + "       PLAYER INFO       \n" + "=========================\n" + "Player 1 : %-10s (%c)\n" + "Player 2 : %-10s (%c)\n" + "=========================\n", playerOneName, playerOneSymbol, playerTwoName, playerTwoSymbol);
    }

    public static int toss (String playerOneName, String playerTwoName){
        int choice = -1;
        int result = (int) (Math.random()*2);
        do {
            try {
                System.out.printf(
                "%n========== TOSS ==========%n" +
                "%s is tossing the coin...%n" +
                "%s, make your choice:%n" +
                "  [0] Heads%n" +
                "  [1] Tails%n" +
                "===========================%n> ",
                playerOneName, playerTwoName);

                choice = scn.nextInt();
                if (choice < 0 || choice > 1){
                    System.out.println("Input Must be in Range...");
                    sleep(1);
                    continue;
                }
                if (choice == result){
                    System.out.printf("%n*** '%s' WINS THE TOSS! ***%n", playerTwoName);
                    sleep(1);
                    return 2;
                } else {
                    System.out.printf("%n*** '%s' LOSES THE TOSS - '%s' WINS! ***%n", playerTwoName, playerOneName);
                    sleep(1);
                    return 1;
                }
            
            } catch (InputMismatchException ie){
                System.out.print("Wrong choice... Enter 0 or 1 only..");
                sleep(1);
                scn.nextLine();
                continue;
            }
        } while (true);
    }

    public static void displayGrid(char arr[][]){
        System.out.println("---------------");
        
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[0].length; j++){
                System.out.printf("| %s |", arr[i][j]);
            }
            System.out.println("\n---------------");
        }
    }

    public static void playOptimalMove (char computerSymbol, char[][] grid, int []movePlayed){
        char temp = ' ';
        char playerSymbol = getOtherSymbol(computerSymbol);

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] != 'O' && grid[i][j] != 'X'){
                    temp = grid[i][j];
                    grid[i][j] = computerSymbol;

                    if (winChecker(grid, i, j)){
                        grid[i][j] = temp;
                        movePlayed[0] = i;
                        movePlayed[1] = j;
                        return;
                    } else {
                        grid[i][j] = temp;
                    }
                }
            }
        }

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] != 'O' && grid[i][j] != 'X'){
                    temp = grid[i][j];
                    grid[i][j] = playerSymbol;

                    if (winChecker(grid, i, j)){
                        grid[i][j] = temp;
                        movePlayed[0] = i;
                        movePlayed[1] = j;
                        return;
                    } else {
                        grid[i][j] = temp;
                    }
                }
            }
        }

        if (grid[1][1] != 'X' && grid[1][1] != 'O'){
            movePlayed[0] = 1;
            movePlayed[1] = 1;
            return;
        }

        if (grid[0][0] != 'X' && grid[0][0] != 'O'){
            movePlayed[0] = 0;
            movePlayed[1] = 0;
            return;
        }

        if (grid[0][grid.length - 1] != 'X' && grid[0][grid.length - 1] != 'O'){
            movePlayed[0] = 0;
            movePlayed[1] = grid.length - 1;
            return;
        }

        if (grid[grid.length - 1][0] != 'X' && grid[grid.length - 1][0] != 'O'){
            movePlayed[0] = grid.length - 1;
            movePlayed[1] = 0;
            return;
        }

        if (grid[grid.length - 1][grid.length - 1] != 'X' && grid[grid.length - 1][grid.length - 1] != 'O'){
            movePlayed[0] = grid.length - 1;
            movePlayed[1] = grid.length - 1;
            return;
        }

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] != 'X' && grid[i][j] != 'O'){
                    movePlayed[0] = i;
                    movePlayed[1] = j;
                    return;
            }
        }
    }
    }

    public static boolean playerTurn (String playerName, char playerSymbol, char arr[][]){
        int toOpen = 0, toOpeni = -1, toOpenj = -1, gridSize = 3;
        int movePlayed[] = new int[2];

        do {
            try {
                System.out.printf("*** Player '%s' Turn ***%n", playerName);
                
                if (playerName.equals("Computer")){
                    sleep(1);
                    do {
                        playOptimalMove(playerSymbol, arr, movePlayed);
                        toOpeni = movePlayed[0];
                        toOpenj = movePlayed[1];
                        toOpen = toOpeni * 3 + toOpenj + 1;
                        break;
                    } while (true);
                } else {
                    System.out.print("Enter Tile to Play: ");
                    toOpen = scn.nextInt();

                    if (toOpen < 1 || toOpen > 9){
                        System.out.println("Input must be in Range...");
                        continue;
                    }
                }

                toOpen--;
                toOpeni = toOpen / gridSize;
                toOpenj = toOpen % gridSize;

                if (arr[toOpeni][toOpenj] == 'O' || arr[toOpeni][toOpenj] == 'X'){
                    System.out.println("Already Played...");
                    continue;
                }

            arr[toOpeni][toOpenj] = playerSymbol;
            displayGrid(arr);

            if (winChecker(arr, toOpeni, toOpenj)){
                System.out.printf(
                    "%n*** CONGRATULATIONS %s ***%n" +
                    ">>> YOU ARE THE WINNER! <<<%n%n",
                    playerName
                );

                return true;
            } else{
                return false;
            }
            
            } catch (InputMismatchException ie){
                System.out.println("Wrong Input Type... Input Again!");
                scn.nextLine();
            }
        } while (true);
    }

    public static boolean winChecker(char arr[][], int lastIndexOpenedi, int lastIndexOpenedj){
        int equalityCount = 0;
        char playerSymbol = arr[lastIndexOpenedi][lastIndexOpenedj];

        for (int i = 0; i < arr[0].length; i++){
            if (arr[lastIndexOpenedi][i] == playerSymbol) equalityCount++;
        }
        if (equalityCount == 3) return true;

        equalityCount = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i][lastIndexOpenedj] == playerSymbol) equalityCount++;
        }
        if (equalityCount == 3) return true;

        equalityCount = 0;
        for (int i = 0; i < arr.length; i++){
            if (playerSymbol == arr[i][i]) equalityCount++;
        }
        if (equalityCount == 3) return true;

        equalityCount = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i][arr.length - i - 1] == playerSymbol) equalityCount++;
        }
        if (equalityCount == 3) return true;
        return false;
    }

    public static void playGame(boolean isComputer){
        int gridSize = 3, counter = 1, tossWinner = 0;
        char[][] grid = new char[gridSize][gridSize];
        char playerOneSymbol = ' ', playerTwoSymbol = ' ';
        boolean winStatus = false, playAgain = false;
        String playerOneName = "", playerTwoName = "", tossWinnerName = "";

        if (isComputer){
            System.out.print("Enter Player Name: ");
            playerTwoName = getName();

            System.out.print("Choose Player Symbol (X/O): ");
            playerTwoSymbol = getSymbol();
            playerOneName = "Computer";
            playerOneSymbol = getOtherSymbol(playerTwoSymbol);
        } else {
            System.out.print("Enter Player 1 Name: ");
            playerOneName = getName();

            System.out.print("Choose Player 1 Symbol (X/O): ");
            playerOneSymbol = getSymbol();

            System.out.print("Enter Player 2 Name: ");
            playerTwoName = getName();
            playerTwoSymbol = getOtherSymbol(playerOneSymbol);
        }    
        
        do {
            counter = 1;

            for (int i = 0; i < gridSize; i++){
                for (int j = 0; j < gridSize; j++){
                    grid[i][j] = (char) ('0' + counter++);
                }
            }

            displayInfo(playerOneName, playerTwoName, playerOneSymbol, playerTwoSymbol);
            sleep(1);
        
            tossWinner = toss(playerOneName, playerTwoName);
            if (tossWinner == 1){
                tossWinnerName = playerOneName;
            } else tossWinnerName = playerTwoName;

            System.out.printf("%n*** '%s's' Turn ***%n", tossWinnerName);
            displayGrid(grid);
            counter = 0;

            if (tossWinner == 1){
                while (counter < 9) {
                    winStatus = playerTurn(playerOneName, playerOneSymbol, grid);
                    counter++;
                    if (winStatus) break;
                    else if (counter == 9){
                        System.out.println("\n====================");
                        System.out.println("     GAME DRAW!");
                        System.out.println("====================\n");
                        break;
                    }
                    winStatus = playerTurn(playerTwoName, playerTwoSymbol, grid);
                    counter++;
                    if (winStatus) break;
                }
            } else {
                while (counter < 9 ) {
                    winStatus = playerTurn(playerTwoName, playerTwoSymbol, grid);
                    counter++;
                    if (winStatus) break;
                    else if (counter == 9){
                        System.out.println("\n====================");
                        System.out.println("     GAME DRAW!");
                        System.out.println("====================\n");

                        break;
                    }
                    winStatus = playerTurn(playerOneName, playerOneSymbol, grid);
                    counter++;
                    if (winStatus) break;
                }
            }
            
            System.out.printf(
                "%n>>> PLAY AGAIN? <<<%n%n" +
                "Y  -> Play Again%n" +
                "Any key -> Main Menu%n%n" +
                "> "
            );



            do {
                try {
                    char choice = scn.next().toUpperCase().charAt(0);
                    if (choice == 'Y'){
                        playAgain = true;
                        break;
                    } else{
                        playAgain = false;
                        break;
                    }
                } catch (Exception e){
                    playAgain = false;
                    break;
                }
            } while (true);
            if (!playAgain) break;
        } while (true);
    }

    public static void Hangman(){
        int randWordIndex = 0;
        int wordCount = 0;
        String word = "";
        String name = "";
        String userInpLtr;
        int lettersNum = word.length();
        int counter = 0;
        int score = 0;
        char inpLtr;
        int guessedTimes = 0;
        String guessedLetters = "";
        String displayedWord = "";
        String actualWord = word;
        boolean flag = false;
        int loopFlag = 1;
        boolean alreadyGuessed = false;

        String hangman[] = {"      -------\n" +
                        "    |/      |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "____|____ ",

                    "      -------\n" +
                        "    |/      |\n" +
                        "    |      (_)\n" +
                        "    |\n" +
                        "    |\n" +
                        "    |\n" +
                        "____|____ ",
                    
                    "      -------\n" +
                        "    |/      |\n" +
                        "    |      (_)\n" +
                        "    |       |\n" +
                        "    |       |\n" +
                        "    |\n" +
                        "____|____ ",

                        "      -------\n" +
                        "    |/      |\n" +
                        "    |      (_)\n" +
                        "    |      \\|\n" +
                        "    |       |\n" +
                        "    |\n" +
                        "____|____ ",

                        "      -------\n" +
                        "    |/      |\n" +
                        "    |      (_)\n" +
                        "    |      \\|/\n" +
                        "    |       |\n" +
                        "    |\n" +
                        "____|____ ",

                        "      -------\n" +
                        "    |/      |\n" +
                        "    |      (_)\n" +
                        "    |      \\|/\n" +
                        "    |       |\n" +
                        "    |      /\n" +
                        "____|____ ",

                        "      -------\n" +
                        "    |/      |\n" +
                        "    |      (_)\n" +
                        "    |      \\|/\n" +
                        "    |       |\n" +
                        "    |      / \\\n" +
                        "____|____ "
                    };
                        

        System.out.println("\n===================================");
        System.out.println("           HANGMAN GAME  ");
        System.out.println("===================================\n");

        System.out.print("Enter your Name: ");
        name = getName();

        while (loopFlag != 0){
            wordCount = 0;
            try {
                Scanner readlines = new Scanner(new FileInputStream("data/input.txt"));
                while (readlines.hasNextLine()){
                    readlines.nextLine();
                    wordCount++;
                }
                randWordIndex = (int) (Math.random() * wordCount);
                readlines.close();

                FileInputStream fis = new FileInputStream("data/input.txt");
                Scanner read = new Scanner(fis);

                for (int i = 0; i < randWordIndex; i++){
                    read.next();
                }

                word = read.next();
            } catch (IOException ioe){
                ioe.printStackTrace();
            }

            guessedTimes = 0;
            displayedWord = "";
            guessedLetters = "";
            actualWord = word;
            lettersNum = word.length();
            counter = 0;

            System.out.println("\n-----------------------------------");
            System.out.printf("Welcome to the game %s\n", name);
            System.out.println("-----------------------------------");

            System.out.println("\n-----------------------------------");
            System.out.println("   The secret word contains " + lettersNum + " letters");
            System.out.println("   You have 6 incorrect attempts remaining...");
            System.out.println("-----------------------------------\n");
           
            for (int i = 1; i <= word.length(); i++){
                displayedWord += "_";
            }

        while (counter < 6){
            System.out.printf("%s%n%n", hangman[counter]);
            System.out.print("Mystery Word: ");
            System.out.printf("%s%n", displayedWord);
            System.out.print("Letters Guessed: ");
            System.out.print("[");

            for (int j = 0; j < guessedTimes; j++){
                System.out.printf("'%c' ", guessedLetters.charAt(j));
            }

            System.out.println("]");
            System.out.println("Guesses Remaining: " + (6 - counter));
            System.out.print("Guess a Letter (A-Z): ");

            do {
                userInpLtr = scn.next().toUpperCase();
                if (userInpLtr.length() != 1 || !Character.isAlphabetic(userInpLtr.charAt(0))){
                    System.out.print("Enter Single Letter (A-Z) Only!\nInput Letter: ");
                    continue;
                }
            } while (userInpLtr.length() != 1 || !Character.isAlphabetic(userInpLtr.charAt(0)));
                
            inpLtr = userInpLtr.charAt(0);
            
            for (int k = 0; k < guessedLetters.length(); k++){
                if (inpLtr == guessedLetters.charAt(k)){
                    alreadyGuessed = true;
                    break;
                }
            }
            if (!alreadyGuessed){
                guessedLetters += inpLtr;
                guessedTimes++;
            }
            

            for (int i = 0; i < actualWord.length(); i++){
                if (actualWord.charAt(i) == inpLtr && actualWord.charAt(i) != displayedWord.charAt(i)){
                    displayedWord = displayedWord.substring(0, i) + inpLtr + displayedWord.substring(i+1);
                    System.out.println("Letter Found!!");
                    if (displayedWord.equals(actualWord)){
                        counter = 6;
                    }
                    flag = true;
                }
            }

            if (!flag && !alreadyGuessed){
                counter++;
                System.out.println("Wrong Attempt!");
                
            } else if (!flag && alreadyGuessed) {
                System.out.println("You Have Already Guessed This Letter!\n");
            }

            flag = false;
            alreadyGuessed = false;
        }

        if (actualWord.equals(displayedWord)) {
            System.out.println("Congratulations! You Won The Game");
            score+=10;
        } else {
            System.out.printf("%s%n%nYou Lost The Game%nThe Correct Word Is: %s%n", hangman[6], actualWord);
        }

        System.out.print("Press 1 to Play Again\nPress 0 to Quit\nInput: ");
        do {
            try {
                loopFlag = scn.nextInt();
                scn.nextLine();
            if (loopFlag != 1 && loopFlag != 0){
                System.out.print("Input Must be in Range!\nEnter 1 to Play Again\n" + "Enter 0 to Quit\n" + "Input: ");
                continue;
            }
            break;
            } catch (InputMismatchException ie){
                System.out.print("Wrong Input Type!\nEnter 1 to Play Again\nEnter 0 to Quit\nInput Again: ");
                scn.nextLine();
            }
        } while (true);
        
    }
        System.out.print("\nThank you for playing the Game\n");

        try {
            File record = new File("data/record.txt");
            FileOutputStream fos = new FileOutputStream("data/record.txt", true);
            PrintWriter wrt = new PrintWriter(fos);
            wrt.printf("Name: %s Score: %d%n", name, score);
            wrt.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Your Score is: " + score);
    }

    public static void MemoryMatch(){
        int gridSize = 0;

        System.out.println("\n================================");
        System.out.println("     MEMORY MATCH GAME");
        System.out.println("================================\n");

        do {
            System.out.print("Enter the Grid Size: ");

            try {
                gridSize = scn.nextInt();
                if ((gridSize % 2 != 0) || gridSize == 0 || gridSize < 0) {
                    System.out.println("ODD, ZERO or NEGATIVE Grid Size Not Allowed!!");
                    continue;
                } else if ((gridSize*gridSize)/2 > 192){
                    System.out.println("Grid Size Too Big!\n");
                    continue;
                }
                
                break;
            } catch (InputMismatchException wrongInp) {
                System.out.println("You Entered Wrong Input Type\nInput Again!!!\n");
                scn.nextLine();
            }
        } while (true);

        int shapesCount = gridSize * gridSize;
        String displayArr[][] = new String[gridSize][gridSize];
        int displayArrayFiller = 1;

        for (int i = 0; i < displayArr.length; i++) {
            for (int j = 0; j < displayArr[i].length; j++) {
                displayArr[i][j] = (displayArrayFiller++) + "";
            }
        }

        printGrid(displayArr);

        String emojiArr[][] = new String[displayArr.length][displayArr[0].length];
        arrayPopulate(emojiArr, shapesCount);
        String openedNums[] = new String[shapesCount];
        int storeIndex = 0, counter = 0, correctMatches = 0, wrongMatches = 0;
        int totalPairs = (gridSize * gridSize) / 2;
        int toMatch = -1, toFlip = -1;
        int toFlipi = -1, toFlipj = -1;
        int toMatchi = -1, toMatchj = -1;
        boolean cont= false;

        do {
            cont = false;
            counter = 0;

            try {
                System.out.print("Select a Number to Flip: ");
                toFlip = scn.nextInt();

                if (toFlip > (shapesCount) || toFlip <= 0) {
                    System.out.println("Input Must be in Range...\n");
                    printGrid(displayArr);
                    continue;
                }
            } catch (InputMismatchException wrngInp) {
                System.out.println("Please enter an Integer Only...\n");
                printGrid(displayArr);
                scn.nextLine();
                continue;
            }

            while (counter < storeIndex) {
                if ((Integer.toString(toFlip)).equals(openedNums[counter++])) {
                    System.out.println("This Number is Already Opened!\n---Input Again---");
                    printGrid(displayArr);
                    cont = true;
                    break;
                }
            }

            if (cont) continue;

            if (toFlip % gridSize != 0) {
                toFlipj = (toFlip % gridSize) - 1;
                toFlipi = (toFlip / gridSize);
            } else if (toFlip % gridSize == 0) {
                toFlipj = gridSize - 1;
                toFlipi = (toFlip / gridSize) - 1;
            }

            displayArr[toFlipi][toFlipj] = emojiArr[toFlipi][toFlipj];
            printGrid(displayArr);

            try {
                System.out.print("Select the Number to flip and Match: ");
                toMatch = scn.nextInt();

                if (toMatch > shapesCount || toMatch <= 0) {
                    System.out.println("Input Must be in Range...");
                    displayArr[toFlipi][toFlipj] = toFlip + "";
                    printGrid(displayArr);
                    continue;
                }
            } catch (InputMismatchException wrngInp) {
                System.out.println("Please enter an Integer Only.");
                displayArr[toFlipi][toFlipj] = toFlip + "";
                printGrid(displayArr);
                scn.nextLine();
                continue;
            }

            counter = 0;

            do {
                if ((Integer.toString(toMatch)).equals(openedNums[counter++]) || toFlip == toMatch) {
                    System.out.println("This Number is Already Opened!\n!--Input Again--!");
                    cont = true;
                    break;
                }
            } while (counter < storeIndex);

            if (cont) {
                displayArr[toFlipi][toFlipj] = toFlip + "";
                printGrid(displayArr);
                continue;
            }

            if (toMatch % gridSize != 0) {
                toMatchj = (toMatch % gridSize) - 1;
                toMatchi = (toMatch / gridSize);
            } else if (toMatch % gridSize == 0) {
                toMatchj = gridSize - 1;
                toMatchi = (toMatch / gridSize) - 1;
            }

            displayArr[toMatchi][toMatchj] = emojiArr[toMatchi][toMatchj];
            printGrid(displayArr);

            if (displayArr[toFlipi][toFlipj].equals(displayArr[toMatchi][toMatchj])) {
                System.out.println("Well Done! Pair Matched...");
                correctMatches++;
                openedNums[storeIndex++] = (toFlip) + "";
                openedNums[storeIndex++] = (toMatch) + "";
                continue;
            } else {
                System.out.println("Sorry, Pair didnt match...");
                sleep(1);
                wrongMatches++;
                displayArr[toFlipi][toFlipj] = toFlip + "";
                displayArr[toMatchi][toMatchj] = toMatch + "";
                printGrid(displayArr);
            }

        } while (totalPairs != correctMatches);

        int finalScore = (((correctMatches * 10) - (wrongMatches * 3)) * 100) / (totalPairs * 10);
        if (finalScore < 0) finalScore = 0;

        System.out.printf(
                "\n==========GAME OVER==========\nCorrect Pairs: %d%nWrong Attempts: %d%nFinal Score: %d / 100\n=============================\n",
                correctMatches, wrongMatches, finalScore);
    }

    public static void WordScramble(){
        int choice = -1, difficulty = -1, timeToGuess = -1, wins = 0, rounds = 5;
        long timeBeforeGuess = -1, timeAfterGuess = -1;
        String fileName = "", orgWord = "", scrambledWord = "", guess = "";

        System.out.println("\n====WELCOME TO WORD SCRAMBLE GAME====");

        do{
            try{
                System.out.printf("How do you want to play?%n1. Country%n2. Fruit%n3. City%n4. Mixed Words%nInput your Choice: ");
                choice = scn.nextInt();
                System.out.printf("\nDifficulty Level?%n1: Hard%n2: Medium%n3: Easy%nEnter your Level: ");
                difficulty = scn.nextInt();

                if (choice < 1 || choice > 4 || difficulty < 1 || difficulty > 3) System.out.println("Some of the Input is Wrong...\n");
                else break;
            } catch (InputMismatchException e){
                System.out.println("Wrong Input Type... Enter an Integer Only!\n");
                scn.nextLine();
            }
        } while (true);
        
        timeToGuess = difficulty * 3;
        fileName = getFileName(choice);

        System.out.printf("\nGame is about to begin...%n====TIME TO GUESS: %d Seconds====%n", timeToGuess);
        sleep(1);

        for (int i = 0; i < rounds; i++){
            orgWord = getRandomWord(fileName);
            scrambledWord = getScrambleWord(orgWord);
            
            System.out.printf("\n======================\n  WORD SCRAMBLE GAME\n     Round: %d / %d\n======================\nScrambled Word: %s%nEnter your Guess: ", (i+1), rounds, scrambledWord);
            timeBeforeGuess = System.currentTimeMillis();
            guess = scn.next().toUpperCase();
            scn.nextLine();
            timeAfterGuess = System.currentTimeMillis();
            
            if ((timeAfterGuess - timeBeforeGuess) > (timeToGuess*1000)){
                System.out.printf("You didnt Guess within required Time.\nYou must Guess in %d Seconds....", timeToGuess);
                sleep(1);
                continue;
            }

            if (guess.equals(orgWord)){
                System.out.printf("Correct Guess! +1 Points%n");
                wins++;
            } else{
                System.out.printf("Wrong Guess! The correct Word is: %s%n", orgWord);
            }

            if (choice == 4) fileName = getFileName(choice);
            sleep(1);
        }
        System.out.printf("\n-------------------\nWins: %d out of %d.\nThanks for playing!\n-------------------\n", wins, rounds);
    }

    public static void TicTacToe(){
        int gameChoice = 0;

        do {
            System.out.println("\n********** TIC TAC TOE **********\n");
            System.out.println(" [1] Multiplayer  (P1 vs P2)");
            System.out.println(" [2] Single Player (You vs Bot)");
            System.out.println(" [3] Any Other Key to Exit");
            System.out.print("\n>> ");

            try {
                gameChoice = scn.nextInt();
                if (gameChoice < 1 || gameChoice > 2){
                    System.out.println("Exiting...");
                    break;
                }
                else if (gameChoice == 1) playGame(false);
                else if (gameChoice == 2) playGame(true);
            } catch (InputMismatchException ie){
                break;
            } 
        } while (true);
        System.out.println("Thanks for Playing the game!");
    }
    public static void main(String[] args) {
        int choice = -1;

        do{
            try {
                System.out.println("=== GAME ARENA ===");
                System.out.printf("   [1] Hangman%n   [2] Memory Match%n   [3] Word Scramble%n   [4] Tic Tac Toe%n   [0] Exit%n%nInput >> ");

                choice = scn.nextInt();
                if (choice < 0 || choice > 4){
                    System.out.println("Input out of range!");
                    continue;
                }

                if (choice == 0) break;

                switch (choice){
                    case 1:
                        Hangman();
                        break;
                    
                    case 2:
                        MemoryMatch();
                        break;

                    case 3:
                        WordScramble();
                        break;

                    case 4:
                        TicTacToe();
                        break;
                }

            } catch (InputMismatchException ime){
                System.out.println("Wrong Input Type");
                scn.nextLine();
            }
        } while (true);
        System.out.println("=== Thanks for Playing the Game! ===");
    }
}
