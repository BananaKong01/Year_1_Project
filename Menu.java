import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu implements ActionListener {
    
    JFrame window = new JFrame("Snow Problem");
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout);

    String[] levels = {"Level 1", "Level 2", "Level 3"};
    JComboBox<String> levelBox = new JComboBox<>(levels);

    public Menu() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);

        //Add buttons to menu panel
        JPanel menuPanel = new JPanel();
        JButton playButton = new JButton("Play");
        menuPanel.add(playButton);
        menuPanel.add(levelBox);

        //Add menu panel to layout
        mainPanel.add(menuPanel, "Menu");

        //Switch to game when button is clicked
        playButton.addActionListener(this);

        //Add layout to window
        window.add(mainPanel);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get index of array
        int selectedIndex = levelBox.getSelectedIndex();
        //Add one to index to find level number
        int selectedLevel = levelBox.getSelectedIndex() + 1;

        // Game Panel
        GameWindow g = new GameWindow(selectedLevel, this);

        // Add panel to layout
        mainPanel.add(g.getPanel(), "Game");

        //Add layout to window
        cardLayout.show(mainPanel, "Game");
    };

    public void changeToMenu() {
        cardLayout.show(mainPanel, "Menu");
    }

    public void showLeaderboard() {
        Leaderboard l = new Leaderboard(this);      
        mainPanel.add(l.getPanel(), "Leaderboard");
        cardLayout.show(mainPanel, "Leaderboard");

        l.updateLeaderboard(3);
        System.out.println("WINNER");
    }

    public void changeToLeaderboard() {
        cardLayout.show(mainPanel, "Leaderboard");
    }
}