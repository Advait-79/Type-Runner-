/**
 * Tracks how far the runner has progressed.
 * Movement speed scales with typing speed, so faster
 * (and longer) correct answers advance the character further.
 */
public class Character
{
    private int position;

    public Character()
    {
        position = 0;
    }

    /**
     * Advances the character based on typing speed (characters/second).
     * A minimum of 1 unit is guaranteed so slow answers still count as progress.
     */
    public void move(int wordLength, double time)
    {
        double speed = wordLength / time;
        int distance = (int)(speed * 5);
        if(distance < 1)
        {
            distance = 1;
        }
        position += distance;
    }

    public int getPosition()
    {
        return position;
    }

    public void reset()
    {
        position = 0;
    }
}