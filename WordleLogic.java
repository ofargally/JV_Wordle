import java.awt.Color;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;
import java.lang.String;

public class WordleLogic {

    //Toggle DEBUG MODE On/Off
    public static final boolean DEBUG_MODE = false;

    private static final String FILENAME = "englishWords5.txt";
    //Number of words in the words.txt file
    private static final int WORDS_IN_FILE = 5758; 

    //Use for generating random numbers!
    private static final Random rand = new Random();


    public static final int MAX_ATTEMPTS = 6; //max number of attempts
    public static final int WORD_LENGTH = 5; //WORD_LENTGH-letter word 
    // as is 5 like wordle, could be changed

    private static final char EMPTY_CHAR = WordleView.EMPTY_CHAR; //use to delete char


    //************       Color Values       ************

    //Green (right letter in the right place)
    private static final Color CORRECT_COLOR = new Color(53, 209, 42);
    //Yellow (right letter in the wrong place)
    private static final Color WRONG_PLACE_COLOR = new Color(235, 216, 52);
    //Dark Gray (letter doesn't exist in the word)
    private static final Color WRONG_COLOR = Color.DARK_GRAY;
    //Light Gray (default keyboard key color, letter hasn't been checked yet)
    private static final Color NOT_CHECKED_COLOR = new Color(160, 163, 168);

    private static final Color DEFAULT_BGCOLOR = Color.BLACK;

    //***************************************************

    //************      Class variables     ************

    //Add them as necessary (I have some but less than 5)
    public static int row_increment = 0;
    public static int column_increment = 0;
    public static String playerWord = "";
    public static String secret = "";
    public static String[] arrayOfWords = new String[WORDS_IN_FILE];
    public static char[] secretWordCopy;
    public static int[] indexFill = new int[5];
    //***************************************************



    //************      Class methods     ************

    //This function gets called ONCE when the game is very first launched
    //before the player has the opportunity to do anything.
    //Returns the chosen mystery word the user needs to guess
    public static String init() throws FileNotFoundException {
        File wordFile = new File("englishWords5.txt");
        Scanner readerObject = new Scanner(wordFile);
        int chosenWordNumber = rand.nextInt(WORDS_IN_FILE);
        for (int i = 0; i < arrayOfWords.length; i++) {
            arrayOfWords[i] = readerObject.nextLine();
        }
        String wordChosen = arrayOfWords[chosenWordNumber];
        wordChosen = wordChosen.toLowerCase();
        System.out.println(wordChosen);
        if (DEBUG_MODE) {
            return "banal";
        }
        return wordChosen;
    }

    //This function gets called everytime the player types a valid letter
    //on the keyboard or clicks one of the letter keys on the 
    //graphical keyboard interface.  
    //The key pressed is passed in as a char uppercase for letter
    public static void inputLetter(char key) {

        //Some placeholder debugging code...
        System.out.println("Letter pressed!: " + key);
        if (column_increment <= 4 && row_increment <= 5) {
            WordleView.setCellLetter(row_increment, column_increment, key);
            playerWord += key;
            column_increment += 1;
            System.out.println(column_increment);
            System.out.println(row_increment);
            System.out.println("Word After Addition: " + playerWord); 
        }
    }

    //This function gets called everytime the user inputs 'Backspace'
    //pressing the physical or virtual keyboard.
    // call on Backspace input
    public static void deleteLetter() {
        if (DEBUG_MODE) {
            System.out.println("in deleteLetter()");

        }
        if (column_increment <= 5 && column_increment > 0) {
            WordleView.setCellLetter(row_increment, column_increment - 1, EMPTY_CHAR);
            playerWord = playerWord.substring(0, column_increment - 1);
            System.out.println("Word After Subtraction: " + playerWord);
            column_increment -= 1;
        }
    }


    //This function gets called everytime the player inputs 'Enter'
    //pressing the physical or virtual keyboard.  
    public static void checkLetters() {
        if (DEBUG_MODE) {
            System.out.println("in checkLetters()");
            System.out.println(secret);
        }
        if (playerWord.equalsIgnoreCase(secret)) {
            for (int i = 0; i < playerWord.length(); i++) {
                WordleView.setCellColor(row_increment, i, CORRECT_COLOR);
                WordleView.setKeyboardColor(playerWord.charAt(i), CORRECT_COLOR);
            }
            WordleView.gameOver(true);
        } else {
            if (checkIfCorrect()) {
                column_increment = 0;
                playerWord = playerWord.toLowerCase();
                secretWordCopy = secret.toCharArray();
                System.out.println("right before correctchars: " + playerWord);
                setCorrectChars();
                setWrongPosChars();
                setWrongChars();
                row_increment += 1;
                playerWord = "";
                if (row_increment == 6)
                    WordleView.gameOver(false);
            }
        }
    }

    public static boolean checkRepeatedLetter(char playerLetter) {
        Color charColor = WordleView.getKeyboardColor(playerLetter);
        if (!charColor.equals(CORRECT_COLOR)) {
            return true;
        }
        return false;
    }

    //This function checks if the player input is both of correct length (5 letter) and of correct definition.
    //If either of these conditions fail, the current row wiggles indicating to the player that the word should be changed
    //for the game to continue.
    public static boolean checkIfCorrect() {
        for (int i = 0; i < arrayOfWords.length; i++) {
            if (playerWord.length() == WORD_LENGTH && playerWord.equalsIgnoreCase(arrayOfWords[i])) {
                return true;
            }
        }
        WordleView.wiggleRow(row_increment);
        return false;
    }


    public static void setCorrectChars() {
        for (int i = 0; i < secret.length(); i++) {
            if (playerWord.charAt(i) == secret.charAt(i)) {
                System.out.println(playerWord.charAt(i));
                WordleView.setCellColor(row_increment, i, CORRECT_COLOR);
                WordleView.setKeyboardColor(playerWord.charAt(i), CORRECT_COLOR);
                secretWordCopy[i] = '\0';
                indexFill[i] = 1;
            }
        }
        System.out.println(Arrays.toString(indexFill));
    }

    public static void setWrongPosChars() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (playerWord.charAt(j) == secretWordCopy[i] && indexFill[j] == 0) {
                    WordleView.setCellColor(row_increment, j, WRONG_PLACE_COLOR);
                    secretWordCopy[i] = '\0';
                    indexFill[j] = 1;
                    if (checkRepeatedLetter(playerWord.charAt(j))) {
                        WordleView.setKeyboardColor(playerWord.charAt(j), WRONG_PLACE_COLOR);
                    }
                } else if (playerWord.charAt(j) == secretWordCopy[j] && indexFill[i] == 1) {
                    WordleView.setCellColor(row_increment, j, CORRECT_COLOR);
                }
            }

        }
        //System.out.println(secretWordCopy);
        System.out.println(Arrays.toString(indexFill));
        secretWordCopy = new char[0];
    }

    public static void setWrongChars() {
        for (int i = 0; i < secret.length(); i++) {
            char chr = playerWord.charAt(i);
            Color color = WordleView.getKeyboardColor(chr);
            if (!color.equals(CORRECT_COLOR) && !color.equals(WRONG_PLACE_COLOR)) {
                WordleView.setCellColor(row_increment, i, WRONG_COLOR);
                WordleView.setKeyboardColor(chr, WRONG_COLOR);
            }
        }
        for (int i = 0; i < indexFill.length; i++) {
            if (indexFill[i] == 0) {
                WordleView.setCellColor(row_increment, i, WRONG_COLOR);
            }
        }
        System.out.println(Arrays.toString(indexFill));
        System.out.println("New Array");
        indexFill = new int[5];
    }

    //Initializes and launches the game logic and its GUI window
    public static void main(String[] args) throws FileNotFoundException {
        //Calls to intialize the game logic and pick the secret word
        secret = WordleLogic.init();
        //Creates the game window
        WordleView.create(secret);
    }
}