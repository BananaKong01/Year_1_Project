import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameWindow implements ActionListener {
    // UI variables
    private GamePiece[] tiles = new GamePiece[20];
    private JPanel panel;

    // Object swap selection
    private int clickedIndex = -1;
    private boolean firstSelection = true;
    private String object;

    public GameWindow() {
        JFrame window = new JFrame();
        panel = new JPanel();
        GridLayout layout = new GridLayout(4,5);
        
        // First set all objects to hole
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new GamePiece("hole.png", 0, 0); // find formula for coords
        }
        // Replace holes based on game board (currently only level 1)
        tiles[1] = new Snowball("snowball_small.png", 0, 0); 
        tiles[15] = new SnowmanHead("head_blue.png", 0 ,0);
        tiles[19] = new Snowball("snowball_large.png", 0, 0);
        
        panel.setLayout(layout);
        for (int i = 0; i < tiles.length; i++) {
            panel.add(tiles[i]);
            tiles[i].addActionListener(this); 
        }

        window.setSize(475, 400);
        window.setContentPane(panel);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        GamePiece clicked = (GamePiece) e.getSource(); // find object clicked

        int swapIndex = -1;

        // Find indexes of object clicked based on whether it was selected first or second
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] == clicked) {
                if (firstSelection == true) {
                    if (clicked.getClass() == Snowball.class) {
                        object = "sb";
                        clickedIndex = i;
                        firstSelection = false;
                    }
                    else {
                        break;
                    }
                }
                else {
                    swapIndex = i;
                    firstSelection = true;
                }
            }
        }

        // Swap objects
        if (object.equals("sb") == true) {
            GamePiece temp = tiles[clickedIndex];
            tiles[clickedIndex] = tiles[swapIndex];
            tiles[swapIndex] = temp;
        }

        // Rebuild panel
        panel.removeAll();
        for (int i = 0; i < tiles.length; i++) {
            panel.add(tiles[i]);
        }
        panel.revalidate();
        panel.repaint();
    }
}