import javax.swing.*;
import java.awt.*;

/**
 * Renders the game scene (sky, ground, sun, stick-figure runner)
 * and hosts the on-screen controls needed to play: the word to
 * type, the input field, and a status readout (score, lives,
 * position, word length).
 */
public class GamePanel extends JPanel
{
    private int playerX = 20;

    private JLabel wordLabel;
    private JTextField inputField;
    private JLabel infoLabel;

    public GamePanel()
    {
        setLayout(null); // free positioning so we can still paint the background scene

        wordLabel = new JLabel("", SwingConstants.CENTER);
        wordLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        wordLabel.setBounds(300, 20, 300, 40);
        add(wordLabel);

        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 20));
        inputField.setBounds(320, 70, 260, 32);
        add(inputField);

        infoLabel = new JLabel("");
        infoLabel.setVerticalAlignment(SwingConstants.TOP);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoLabel.setBounds(10, 10, 260, 100);
        add(infoLabel);
    }

    // Lets the controller attach an ActionListener (fires on Enter)
    // and read/clear the typed text.
    public JTextField getInputField()
    {
        return inputField;
    }

    public void setWord(String word)
    {
        wordLabel.setText(word);
    }

    public void setInfo(String text)
    {
        infoLabel.setText("<html>" + text.replace("\n", "<br>") + "</html>");
    }

    // Moves the stick figure by 'distance' pixels, clamped to stay on screen.
    public void movePlayer(int distance)
    {
        playerX += distance;
        if(playerX > getWidth() - 40)
        {
            playerX = getWidth() - 40;
        }
        if(playerX < 20)
        {
            playerX = 20;
        }
        repaint();
    }

    public void resetPlayer()
    {
        playerX = 20;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        // Sky
        g.setColor(new Color(135,206,235));
        g.fillRect(0,0,getWidth(),getHeight());
        // Ground
        g.setColor(Color.GREEN);
        g.fillRect(0,300,getWidth(),100);
        // Sun
        g.setColor(Color.YELLOW);
        g.fillOval(700,30,80,80);
        // Character (Stick Figure)
        g.setColor(Color.BLACK);
        g.fillOval(playerX,180,30,30);
        g.drawLine(playerX+15,210,playerX+15,260);
        g.drawLine(playerX+15,220,playerX,240);
        g.drawLine(playerX+15,220,playerX+30,240);
        g.drawLine(playerX+15,260,playerX,290);
        g.drawLine(playerX+15,260,playerX+30,290);
    }
}