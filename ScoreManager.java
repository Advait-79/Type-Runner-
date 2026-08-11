/**
 * Calculates and tracks the player's running score,
 * rewarding faster typing on longer words.
 */
public class ScoreManager
{
    private int score;
    private int highestScore;

    public ScoreManager()
    {
        score = 0;
        highestScore = 0;
    }

    /**
     * Awards points proportional to typing speed (characters/second)
     * and updates the running total and personal best.
     */
    public int calculateScore(int wordLength, double time)
    {
        double speed = wordLength / time;
        int points = (int)(speed * 100);
        score += points;
        if(score > highestScore)
        {
            highestScore = score;
        }
        return score;
    }

    public int getScore()
    {
        return score;
    }

    public int getHighestScore()
    {
        return highestScore;
    }

    public void reset()
    {
        score = 0;
    }
}