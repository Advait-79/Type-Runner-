/**
 * Tracks the player's remaining lives.
 * The game ends once health reaches zero.
 */
public class Player
{
    private int health;

    public Player()
    {
        health = 3;
    }

    public void loseLife()
    {
        health--;
    }

    public boolean isAlive()
    {
        return health > 0;
    }

    public int getHealth()
    {
        return health;
    }

    public void reset()
    {
        health = 3;
    }
}