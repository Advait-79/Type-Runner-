import java.util.Scanner;

/**
 * Console entry point for Type Run.
 * Drives the core game loop: show a word, time the input,
 * score it, and update the player's health and position.
 *
 * For the graphical version, run GameController instead.
 */
public class Game
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        Player player = new Player();
        Character character = new Character();
        ScoreManager scoreManager = new ScoreManager();
        GameTimer timer = new GameTimer();
        int wordLength = 3;
        int correctWords = 0;
        System.out.println("==========================================");
        System.out.println("           WELCOME TO TYPE RUN");
        System.out.println("==========================================");
        System.out.println("Rules:");
        System.out.println("1. Type the displayed word correctly.");
        System.out.println("2. Faster typing = Higher Score.");
        System.out.println("3. Faster typing = Character moves farther.");
        System.out.println("4. You have only 3 lives.");
        System.out.println("5. Every 5 correct words increases");
        System.out.println("   the word length by 1.");
        System.out.println("==========================================");
        while(player.isAlive())
        {
            String randomWord =
            RandomGenerator.generateWord(wordLength);
            System.out.println("\n--------------------------------------");
            System.out.println("Word : " + randomWord);
            timer.startTimer();
            String input = sc.nextLine();
            timer.stopTimer();
            double time = timer.getTime();
            if(input.equals(randomWord))
            {
                correctWords++;
                int score =
                scoreManager.calculateScore(
                wordLength,
                time);
                character.move(wordLength,time);
                System.out.println("\nCorrect!");
                System.out.printf("Time Taken : %.2f seconds\n",time);
                System.out.println("Score : "
                +scoreManager.getScore());
                System.out.println("Highest Score : "
                +scoreManager.getHighestScore());
                System.out.println("Character Position : "
                +character.getPosition());
                System.out.println("Lives Left : "
                +player.getHealth());
                if(correctWords%5==0)
                {
                    wordLength++;
                    System.out.println("\n******** LEVEL UP ********");
                    System.out.println(
                    "Word Length Increased To : "
                    +wordLength);
                }
            }
            else
            {
                player.loseLife();
                System.out.println("\nWrong!");
                System.out.println(
                "Correct Word : "+randomWord);
                System.out.println(
                "Lives Left : "
                +player.getHealth());
            }
        }
        System.out.println("\n================================");
        System.out.println("         GAME OVER");
        System.out.println("================================");
        System.out.println("Final Score : "
        +scoreManager.getScore());
        System.out.println("Highest Score : "
        +scoreManager.getHighestScore());
        System.out.println("Final Position : "
        +character.getPosition());
        sc.close();
    }
}