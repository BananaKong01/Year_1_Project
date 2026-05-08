import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameWindow implements ActionListener {
    // UI variables
    private GamePiece[] tiles = new GamePiece[20];
    private JPanel panel;
    private Menu menu;
    //private JTextArea moveDisplay;

    // Object swap selection
    private int clickedIndex = -1;
    private boolean firstSelection = true;
    private String object;

    private int moveCount = 0;
    private JLabel moveDisplay;

    private int levelNumber;

    public GameWindow(int levelSelect, Menu menu) {
        this.menu = menu;
        JFrame window = new JFrame();
        panel = new JPanel();
        GridLayout layout = new GridLayout(4,5);
        panel.setLayout(layout);
        
        Level level = new Level(tiles, levelSelect);
        levelNumber = levelSelect;

        //panel.setLayout(layout);
        for (int i = 0; i < tiles.length; i++) {
            panel.add(tiles[i]);
            tiles[i].addActionListener(this); 
        }

        moveDisplay = new JLabel(String.valueOf(moveCount));
        tiles[0].add(moveDisplay);

        /*window.setSize(475, 400);
        window.setContentPane(panel);
        window.setVisible(true);*/
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GamePiece clicked = (GamePiece) e.getSource(); // find object clicked
        
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

        tiles[clickedIndex].movement(tiles, tiles[clickedIndex], tiles[swapIndex]);

        moveCount++;
        System.out.println(moveCount);
        
        for (int i = 0; i < tiles.length; i++) {
            String status = tiles[i].checkLoss();
            if (status.equals("L")) {
                loss();
            }
        }

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

    public void loss() {
        menu.changeToMenu();
    }

    public void win() {
        menu.showLeaderboard(moveCount, levelNumber);
    }
}