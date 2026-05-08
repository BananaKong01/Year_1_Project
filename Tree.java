// Class only used for name comparisons between subclasses of GamePiece
public class Tree extends GamePiece {
    private String path;
    private int x;
    private int y;

    // Constructor Method
    public Tree(String path, int x, int y) {
        super(path, x, y);
    }
}