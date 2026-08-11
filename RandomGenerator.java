import java.util.Random;

/**
 * Generates random lowercase words for the player to type.
 * Words are randomly assembled rather than drawn from a dictionary,
 * so difficulty scales purely with length.
 */
public class RandomGenerator
{
    public static String generateWord(int wordLength)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < wordLength; i++)
        {
            int index = random.nextInt(alphabet.length());
            sb.append(alphabet.charAt(index));
        }
        return sb.toString();
    }
}