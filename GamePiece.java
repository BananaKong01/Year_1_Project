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

    public void movement(GamePiece[] array, GamePiece first, GamePiece second) {
        // Check GamePieces are valid (only swaps with blank tiles)
        if (first == null || second == null) {
            return; 
        }
        if (second instanceof Tree || second instanceof Snowball || second instanceof SnowmanHead) {
            return;
        }

        // Find all x,y coords
        int firstX = first.returnX();
        int firstY = first.returnY();
        int secondX = second.returnX();
        int secondY = second.returnY();

        // Find direction of movement
        int dx = secondX - firstX;
        int dy = secondY - firstY;

        // Check tiles are next to one another
        if (!((dx == 0 && Math.abs(dy) == 1) || (dy == 0 && Math.abs(dx) == 1))) {
            return;
        }

        // Swap coords
        first.setX(secondX);
        first.setY(secondY);
        second.setX(firstX);
        second.setY(firstY);

        // Find location in array
        int firstIndex = -1;
        int secondIndex = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == first) {
                firstIndex = i;
            }
            if (array[i] == second) {
                secondIndex = i;
            }
        }

        // Check not out of array
        if (firstIndex == -1 || secondIndex == -1) {
            return;
        }

        // Swap locations in array
        GamePiece temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
        
        // Find next tile to be swapped
        GamePiece nextSwap = null;
        for (int i = 0; i < array.length; i++) {
            if (array[i].returnX() == first.returnX() + dx && array[i].returnY() == first.returnY() + dy) {
                nextSwap = array[i];
                break;
            }
        }

        if (nextSwap != null) {
            movement(array, first, nextSwap);
        }
    }

}