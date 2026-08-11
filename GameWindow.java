import javax.swing.*;

/**
 * Top-level application window. Hosts the GamePanel,
 * which handles all rendering and gameplay input.
 */
public class GameWindow extends JFrame
{
    private GamePanel panel;
    public GameWindow()
    {
        setTitle("Type Run");
        setSize(900,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GamePanel();
        add(panel);
        setVisible(true);
    }
    public GamePanel getPanel()
    {
        return panel;
    }
}