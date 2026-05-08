import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameWindow implements ActionListener {
    // UI variables
    private GamePiece[] tiles = new GamePiece[20];
    private JPanel panel;
    private Menu menu;

    // Object swap selection
    private int clickedIndex = -1;
    private boolean firstSelection = true;
    private String object;

    // Move count variables
    private int moveCount = 0;
    private JLabel moveDisplay;

    // Level variables
    private int levelNumber;

    // Constructor
    public GameWindow(int levelSelect, Menu menu) {
        // Assign swing variables
        this.menu = menu;
        JFrame window = new JFrame();
        panel = new JPanel();
        GridLayout layout = new GridLayout(4,5);
        panel.setLayout(layout);
        
        // Assign level
        Level level = new Level(tiles, levelSelect);
        levelNumber = levelSelect;

        // Iterate through array to add tiles
        for (int i = 0; i < tiles.length; i++) {
            panel.add(tiles[i]);
            tiles[i].addActionListener(this); 
        }

        // Display move count on UI
        moveDisplay = new JLabel(String.valueOf(moveCount));
        tiles[0].add(moveDisplay);
    }

    // Return panel
    public JPanel getPanel() {
        return panel;
    }

    // Determining what objects should be swapped
    @Override
    public void actionPerformed(ActionEvent e) {
        // Find object clicked
        GamePiece clicked = (GamePiece) e.getSource();
        
        int swapIndex = -1;

        // Find indexes of object clicked based on whether it was selected first or second, and if it is valid to move
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

        // Call movement
        tiles[clickedIndex].movement(tiles, tiles[clickedIndex], tiles[swapIndex]);

        // Increase move count
        moveCount++;
        System.out.println(moveCount);
        
        // Check game is not lost
        for (int i = 0; i < tiles.length; i++) {
            String status = tiles[i].checkLoss();
            if (status.equals("L")) {
                loss();
            }
        }

        // Check whether win has occcured
        boolean win = true;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] instanceof Snowball s) {
                if (s.getSnowman() == false) {
                    win = false;
                }
            }
        }
        if (win == true) {
            win();
        }

        // Rebuild panel
        panel.removeAll();
        for (int i = 0; i < tiles.length; i++) {
            panel.add(tiles[i]);
        }

        moveDisplay.setText(String.valueOf(moveCount));
        tiles[0].add(moveDisplay);

        panel.revalidate();
        panel.repaint();
        

    }

    // Methods to call different UI displays
    public void loss() {
        menu.changeToMenu();
    }

    public void win() {
        menu.showLeaderboard(moveCount, levelNumber);
    }
}