import javax.swing.*; 

public class GamePiece extends JButton {
    protected String path;
    protected int x; // (0, 0) top left 
    protected int y;

    public GamePiece(String path, int x, int y) {
        super(new ImageIcon(path));
        this.x =  x;
        this.y = y;
    }

    public void movementCheck() {

    }
}