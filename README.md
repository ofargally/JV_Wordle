# JV_Wordle
**Wordle Logic Game**
This project is a Java implementation of the Wordle game. The game involves guessing a secret word within a limited number of attempts. Each time a player makes a guess, feedback is provided about the correctness of the letters and their positions.

**Files**
WordleLogic.Java: Contains the main logic of the game, including word initialization, input handling, and game checking mechanisms.
WordleView.Java: Handles the graphical user interface (GUI) and visual elements of the game, such as drawing the game board, keyboard, and game over messages.

**Features**

**Debug Mode:** Toggle the debug mode on/off using the DEBUG_MODE constant in WordleLogic.Java.
**Word List:** The game uses a word list from the file englishWords5.txt to select a random word for the player to guess.

**Color Feedback:** The game provides color-coded feedback for each letter guessed:
Green: Correct letter in the correct position.
Yellow: Correct letter but in the wrong position.
Dark Gray: Letter doesn't exist in the word.
Light Gray: Letter hasn't been checked yet.

**Keyboard Interface:** Players can use both the physical keyboard and a graphical keyboard interface to input their guesses.
**Game Over:** The game ends either when the player guesses the word correctly or when they exhaust their attempts.

**How to Play**

Launch the game by running the main method in WordleLogic.Java.
The game window will open, displaying the game board and the graphical keyboard interface.
Start guessing the secret word by typing or clicking on the letters.
After selecting the desired letters, press 'Enter' to submit your guess.
Use 'Backspace' to delete a letter from your current guess.
The game provides color-coded feedback for each letter in your guess.
Continue guessing until you either guess the word correctly or run out of attempts.

**Future Enhancements**
Introduce difficulty levels by varying the word length and number of attempts.
Add sound effects for correct guesses, incorrect guesses, and game over scenarios.
Implement a leaderboard to track and display high scores.
