/**
 * Measures how long the player takes to type each word.
 * Uses System.nanoTime() for sub-millisecond precision.
 */
public class GameTimer
{
    private long start;
    private long end;

    public void startTimer()
    {
        start = System.nanoTime();
    }

    public void stopTimer()
    {
        end = System.nanoTime();
    }

    /** Returns the elapsed time between start and stop, in seconds. */
    public double getTime()
    {
        return (end - start) / 1_000_000_000.0;
    }
}