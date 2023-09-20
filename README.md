🎮 **Wordle Game** 🧠

This project is a Java implementation of the Wordle game. The game involves guessing a secret word 🤫 within a limited number of attempts. Each time a player makes a guess, feedback is provided about the correctness of the letters and their positions.

**📁 Files:**

**WordleLogic.Java:** 🧩 Contains the main logic of the game, including word initialization, input handling, and game checking mechanisms.
**WordleView.Java:** 🎨 Handles the graphical user interface (GUI) and visual elements of the game, such as drawing the game board, keyboard, and game over messages.

**✨ Features:**

**Word List:** 📜 The game uses a word list from the file englishWords5.txt to select a random word for the player to guess.
**Color Feedback:** 🌈 The game provides color-coded feedback for each letter guessed:
Green: ✅ Correct letter in the correct position.
Yellow: ⚠️ Correct letter but in the wrong position.
Dark Gray: ❌ Letter doesn't exist in the word.
Light Gray: ⭕ Letter hasn't been checked yet.
**Keyboard Interface:** ⌨️ Players can use both the physical keyboard and a graphical keyboard interface to input their guesses.

**🕹️ How to Play:**

Launch the game by running the main method in WordleLogic.Java.
The game window will open, displaying the game board and the graphical keyboard interface.
Start guessing the secret word by typing or clicking on the letters.
After selecting the desired letters, press 'Enter' to submit your guess.
Use 'Backspace' to delete a letter from your current guess.
The game provides color-coded feedback for each letter in your guess.
Continue guessing until you either guess the word correctly or run out of attempts.

**📝 Development Notes:**

-->The word list is sourced from englishWords5.txt, which contains 5758 five-letter English words.

-->The game uses a fixed word length of 5 letters, but this can be changed by modifying the WORD_LENGTH constant.

-->The maximum number of attempts is set to 6, but this can be adjusted using the MAX_ATTEMPTS constant.

-->The game provides a debug mode, which, when enabled, sets the secret word to "banal" for testing purposes.

**🚀 Future Enhancements:**

Introduce difficulty levels by varying the word length and number of attempts.
Add sound effects 🎵 for correct guesses, incorrect guesses, and game over scenarios.
Implement a leaderboard 🏆 to track and display high scores.
