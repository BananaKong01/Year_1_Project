import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Level {
    public Level(GamePiece[] array, int level) {
        int n = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                array[n] = new GamePiece("hole.png", i, j);
                n++;
            }
        }

        File myObj = new File("levels.txt");
        String data = null;

        try (Scanner myReader = new Scanner(myObj)) {
            int fileLine = 1;
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
                if (fileLine == level) {
                    break;
                } 
                fileLine++;
            }

            for (int i = 0; i < 20; i++) {
                if (data != null) {
                    char current = data.charAt(i);
                    int count = 0;
                    if (current == 'S') { //small snowball
                        for (int a = 0; a < 4; a++) {
                            for (int b = 0; b < 5; b++) {
                                if (count == i) {
                                    array[i] = new Snowball("snowball_small.png",a ,b , "S");
                                }
                                count++;
                            }
                        }
                    } else if (current == 'L') { //large snowball
                        for (int a = 0; a < 4; a++) {
                            for (int b = 0; b < 5; b++) {
                                if (count == i) {
                                    array[i] = new Snowball("snowball_large.png",a ,b , "L");
                                } 
                                count++;
                            }
                        }
                    } else if (current == 'B') { //snowman head
                        for (int a = 0; a < 4; a++) {
                            for (int b = 0; b < 5; b++) {
                                if (count == i) {
                                    array[i] = new SnowmanHead("head_blue.png",a ,b, "blue");
                                }
                                count++;
                            }
                        }
                    } else if (current == 'R') { //snowman head
                        for (int a = 0; a < 4; a++) {
                            for (int b = 0; b < 5; b++) {
                                if (count == i) {
                                    array[i] = new SnowmanHead("head_red.png",a ,b, "red");
                                }
                                count++;
                            }
                        }
                    } else if (current == 'Y') { //snowman head
                        for (int a = 0; a < 4; a++) {
                            for (int b = 0; b < 5; b++) {
                                if (count == i) {
                                    array[i] = new SnowmanHead("head_yellow.png",a ,b, "yellow");
                                }
                                count++;
                            }
                        }
                    } else if (current == 'T') { //tree
                        for (int a = 0; a < 4; a++) {
                            for (int b = 0; b < 5; b++) {
                                if (count == i) {
                                    array[i] = new Tree("tree.png",a ,b);
                                }
                                count++;
                            }
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}