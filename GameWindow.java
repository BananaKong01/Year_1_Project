import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameWindow implements ActionListener {
    // UI variables
    private GamePiece[] tiles = new GamePiece[20];
    private JPanel panel;

    JLabel label = new JLabel("Value will appear here");

    // Object swap selection
    private int clickedIndex = -1;
    private boolean firstSelection = true;
    private String object;

    public GameWindow() {
        JFrame window = new JFrame();
        panel = new JPanel();
        GridLayout layout = new GridLayout(4,5);
        
        int n = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                tiles[n] = new GamePiece("hole.png", i, j);
                n++;
            }
        }
        
        // Replace holes based on game board (currently only level 1)
        tiles[1] = new Snowball("snowball_small.png", 0, 1, "S"); 
        tiles[15] = new SnowmanHead("head_blue.png", 3,0);
        tiles[19] = new Snowball("snowball_large.png", 3, 4, "L");
        
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
        
        label.setText("Value: " + clicked.x + clicked.y); // debugging tool
        
        int swapIndex = -1;

        // Find indexes of object clicked based on whether it was selected first or second
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] == clicked) {
                if (firstSelection == true) {
                    if (clicked.getClass() == Snowball.class || clicked.getClass() == SnowmanHead.class) {
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

        /*GamePiece temp = tiles[clickedIndex];
        int tempX = tiles[clickedIndex].returnX();
        int tempY = tiles[clickedIndex].returnY();
        tiles[clickedIndex].setX(tiles[swapIndex].returnX());
        tiles[clickedIndex].setY(tiles[swapIndex].returnY());
        tiles[clickedIndex] = tiles[swapIndex];
        tiles[swapIndex].setX(tempX);
        tiles[swapIndex].setY(tempY);
        tiles[swapIndex] = temp;
        */
        tiles[clickedIndex].movement(tiles, tiles[clickedIndex], tiles[swapIndex]);


        // Rebuild panel
        panel.removeAll();
        for (int i = 0; i < tiles.length; i++) {
            panel.add(tiles[i]);
        }

        clicked.add(label); // for debugging, see coords

        panel.revalidate();
        panel.repaint();
        

    }
}