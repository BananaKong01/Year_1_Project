public class Snowball extends GamePiece {
    // Variable that defines large or small snowball
    private String path;
    private int x;
    private int y;

    // Constructor Method
    public Snowball(String path, int x, int y) {
        super(path, x, y);
    }

    // Movement Method
    /* Following Rules: 
    - Large onto small only
    - Only movement vertical and horizontal, not diagonal
    - No movement after it is stacked
    */
}