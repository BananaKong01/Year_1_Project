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

    public int returnX() {
        return x;
    }

    public int returnY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void movementCheck() {

    }
}