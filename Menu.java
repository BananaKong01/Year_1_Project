import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu implements ActionListener {
    
    // Add CardLayout (holds multiple different layouts)
    JFrame window = new JFrame("Snow Problem");
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);

    // Hold selection of each level in drop-down box
    String[] levels = {"Level 1", "Level 2", "Level 3","Level 4","Level 5","Level 6","Level 7","Level 8","Level 9","Level 10"
    ,"Level 11", "Level 12", "Level 13","Level 14","Level 15","Level 16","Level 17","Level 18","Level 19","Level 20"
    ,"Level 21", "Level 22", "Level 23","Level 24","Level 25","Level 26","Level 27","Level 28","Level 29","Level 30"
    ,"Level 31", "Level 32", "Level 33","Level 34","Level 35","Level 36","Level 37","Level 38","Level 39","Level 40"
    ,"Level 41", "Level 42", "Level 43","Level 44","Level 45","Level 46","Level 47","Level 48","Level 49","Level 50"
    ,"Level 51", "Level 52", "Level 53","Level 54","Level 55","Level 56","Level 57","Level 58","Level 59","Level 60"
    ,"Level 61", "Level 62", "Level 63","Level 64","Level 65","Level 66","Level 67","Level 68","Level 69","Level 70"
    ,"Level 71", "Level 72", "Level 73","Level 74","Level 75","Level 76","Level 77","Level 78","Level 79","Level 80"
    };
    JComboBox<String> levelBox = new JComboBox<>(levels);

    public Menu() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);

        // Add buttons to menu panel
        JPanel menuPanel = new JPanel();
        JButton playButton = new JButton("Play");
        menuPanel.add(playButton);
        menuPanel.add(levelBox);

        // Add menu panel to layout
        mainPanel.add(menuPanel, "Menu");

        // Switch to game when button is clicked
        playButton.addActionListener(this);

        // Add layout to window
        window.add(mainPanel);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get index of array
        int selectedIndex = levelBox.getSelectedIndex();
        // Add one to index to find level number
        int selectedLevel = levelBox.getSelectedIndex() + 1;

        // Game Panel
        GameWindow g = new GameWindow(selectedLevel, this);

        // Add panel to layout
        mainPanel.add(g.getPanel(), "Game");

        // Add layout to window
        cardLayout.show(mainPanel, "Game");
    };

    // Display leaderboard, and update accordingly
    public void showLeaderboard(int score, int level) {
        Leaderboard l = new Leaderboard(this);      
        mainPanel.add(l.getPanel(), "Leaderboard");
        cardLayout.show(mainPanel, "Leaderboard");

        l.updateLeaderboard(score, level);
    }

    // Methods to change card layout
    public void changeToLeaderboard() {
        cardLayout.show(mainPanel, "Leaderboard");
    }  
    
    public void changeToMenu() {
        cardLayout.show(mainPanel, "Menu");
    }
}