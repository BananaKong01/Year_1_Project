import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Leaderboard implements ActionListener {
    private JPanel panel;
    private Menu menu;
    private JButton restartButton;
    JTextArea textArea = new JTextArea(5, 20);

    public Leaderboard(Menu m) {
        menu = m;
        File myObj = new File("leaderboard.txt");
        String data = null;

        textArea.setEditable(false);

        panel = new JPanel();
        panel.add(textArea);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(this);
        panel.add(restartButton);

        textArea.setText("");
        try (Scanner myReader = new Scanner(myObj)) {
            int fileLine = 1;
            while (myReader.hasNextLine() && fileLine != 6) {
                data = myReader.nextLine();
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

    public void updateLeaderboard(int score) {
        String[] topFive = new String[5];
        File file = new File("leaderboard.txt");

        try (Scanner myReader = new Scanner(file)) {
            for (int i = 0; i < topFive.length; i++) {
                topFive[i] = myReader.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
            
        for (int i = 0; i < topFive.length; i++) {
            int comparison = Integer.parseInt(topFive[i]); 
            if (comparison > score) {
                topFive[i] = String.valueOf(score);
                break;
            }
        }


        textArea.setText("");
        try (FileWriter myWriter = new FileWriter(file)) {
            for (int i = 0; i < topFive.length; i++) {
                myWriter.write(topFive[i]);
                myWriter.write("\n");
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        String data = null;

        try (Scanner myReader = new Scanner(file)) {
            int fileLine = 1;
            while (myReader.hasNextLine() && fileLine != 6) {
                data = myReader.nextLine();
                textArea.append(data + "\n");
                fileLine++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menu.changeToMenu();
    }

    
}