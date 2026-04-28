public class Snowball extends GamePiece {
    // Variable that defines large or small snowball
    private String size; 
    private boolean stack = false;

    // Constructor Method
    public Snowball(String path, int x, int y, String size) {
        this.size = size;
        super(path, x, y);
    }

    public boolean getStack() {
        if (stack == true) {
            return true;
        }
        else {
            return false;
        }
    }
    
   @Override
   public void movement(GamePiece[] array, GamePiece first, GamePiece second) {
        // Check if need to stack
        if (getStack() == false) {
            if (checkAdjacency(first, second) && first instanceof Snowball s1 && second instanceof Snowball s2) { // Ensure first and second can be seen as Snowball class to read size
                if (s1.size.equals("S") && s2.size.equals("L")) {
                    s2.updateImage("snowman_stack.png");
                    s2.stack = true;
                    // Remove s1 from array, and replace with a new GamePiece
                    for (int i = 0; i < array.length; i++) {
                        if (array[i] == s1) {
                            int a = s1.getX();
                            int b = s1.getY();
                            array[i] = new GamePiece ("hole.png", a, b);
                        }
                    }
                    return;
                }
            }
        }
        else {
            return;
        }
        super.movement(array, first, second);
   }

   
}