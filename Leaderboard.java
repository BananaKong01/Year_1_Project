import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ServiceConfigurationError;

public class Leaderboard implements ActionListener {
    // Swing variables
    private JPanel panel;
    private Menu menu;
    private JButton restartButton;
    JTextArea textArea = new JTextArea(5, 20);

    public Leaderboard(Menu m) {
        menu = m;
        File file = new File("leaderboard.txt");
        String data = null;

        // Add UI elements
        textArea.setEditable(false);
        panel = new JPanel();
        panel.add(textArea);
        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        panel.add(restartButton);

        // Display current leaders on creation
        textArea.setText("");
        try (Scanner scanner = new Scanner(file)) {
            int fileLine = 1;
            while (scanner.hasNextLine() && fileLine != 6) {
                data = scanner.nextLine();
                textArea.append(data + "\n");
                fileLine++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    // Check if score is lower than any current ones, and replace accordingly
    public void updateLeaderboard(int score, int level) {
        String[] topFive = new String[5];
        File file = new File("leaderboard.txt");

        // Get current scores
        try {
            topFive[0] = Files.readAllLines(Paths.get("leaderboard.txt")).get(((level-1)*5));
            topFive[1] = Files.readAllLines(Paths.get("leaderboard.txt")).get(((level-1)*5)+1);
            topFive[2] = Files.readAllLines(Paths.get("leaderboard.txt")).get(((level-1)*5)+2);
            topFive[3] = Files.readAllLines(Paths.get("leaderboard.txt")).get(((level-1)*5)+3);
            topFive[4] = Files.readAllLines(Paths.get("leaderboard.txt")).get(((level-1)*5)+4);
        } catch (IOException e) {
            e.printStackTrace();
        }
            
        // If a lower score is found, it is replaced
        for (int i = 0; i < topFive.length; i++) {
            int comparison = Integer.parseInt(topFive[i]); 
            if (comparison > score) {
                topFive[i] = String.valueOf(score);
                break;
            }
        }

        // Update text file
        textArea.setText("");
        try (FileWriter scanner = new FileWriter(file)) {
            for (int i = 0; i < topFive.length; i++) {
                scanner.write(topFive[i]);
                scanner.write("\n");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String data = null;

        // Display leaders after updates
        try (Scanner scanner = new Scanner(file)) {
            int fileLine = 1;
            while (scanner.hasNextLine() && fileLine != 6) {
                data = scanner.nextLine();
                textArea.append(data + "\n");
                fileLine++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Restart button logic
    @Override
    public void actionPerformed(ActionEvent e) {
        menu.changeToMenu();
    }

    
}