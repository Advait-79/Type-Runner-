import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI equivalent of Game.java's main loop. A Scanner-based
 * while-loop can't be used with Swing without freezing the window,
 * so this instead listens for the Enter key on the input field and
 * runs one "turn" of the game each time it fires.
 */
public class GameController
{
    private GameWindow window;
    private GamePanel panel;

    private Player player;
    private Character character;
    private ScoreManager scoreManager;
    private GameTimer timer;

    private int wordLength = 3;
    private int correctWords = 0;
    private String currentWord;

    public GameController()
    {
        window = new GameWindow();
        panel = window.getPanel();

        player = new Player();
        character = new Character();
        scoreManager = new ScoreManager();
        timer = new GameTimer();

        panel.getInputField().addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                checkAnswer();
            }
        });

        nextWord();
        updateInfo("Type the word above and press Enter.");
    }

    // Shows a new word and (re)starts the timer, same role as the
    // "randomWord" + "timer.startTimer()" lines in Game.java.
    private void nextWord()
    {
        currentWord = RandomGenerator.generateWord(wordLength);
        panel.setWord(currentWord);
        panel.getInputField().setText("");
        panel.getInputField().requestFocusInWindow();
        timer.startTimer();
    }

    // Fires when the player presses Enter. Mirrors the if/else block
    // inside the while-loop of Game.java.
    private void checkAnswer()
    {
        timer.stopTimer();
        String input = panel.getInputField().getText();
        double time = timer.getTime();

        if(input.equals(currentWord))
        {
            correctWords++;
            scoreManager.calculateScore(wordLength, time);

            int oldPosition = character.getPosition();
            character.move(wordLength, time);
            int distanceMoved = character.getPosition() - oldPosition;
            panel.movePlayer(distanceMoved);

            String message = String.format(
                "Correct! (%.2fs)", time);

            if(correctWords % 5 == 0)
            {
                wordLength++;
                message += "\nLEVEL UP! Word length: " + wordLength;
            }
            updateInfo(message);
        }
        else
        {
            player.loseLife();
            updateInfo("Wrong! The word was: " + currentWord);
        }

        if(player.isAlive())
        {
            nextWord();
        }
        else
        {
            gameOver();
        }
    }

    // Refreshes the status readout (score, lives, position, etc.)
    // and appends the last-turn message on top.
    private void updateInfo(String lastTurnMessage)
    {
        String info = lastTurnMessage
            + "\nScore: " + scoreManager.getScore()
            + "\nHighest Score: " + scoreManager.getHighestScore()
            + "\nLives: " + player.getHealth()
            + "\nPosition: " + character.getPosition()
            + "\nWord Length: " + wordLength;
        panel.setInfo(info);
    }

    private void gameOver()
    {
        panel.getInputField().setEnabled(false);
        panel.setWord("GAME OVER");
        JOptionPane.showMessageDialog(window,
            "Game Over!\n"
            + "Final Score: " + scoreManager.getScore() + "\n"
            + "Highest Score: " + scoreManager.getHighestScore() + "\n"
            + "Final Position: " + character.getPosition());
    }

    public static void main(String[] args)
    {
        // Swing components must be created/updated on the Event
        // Dispatch Thread, so we kick things off with invokeLater.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new GameController();
            }
        });
    }
}