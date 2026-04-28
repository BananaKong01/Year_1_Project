public class Snowball extends GamePiece {
    // Variable that defines large or small snowball
    private String size; 

    // Constructor Method
    public Snowball(String path, int x, int y, String size) {
        this.size = size;
        super(path, x, y);
    }

    // Movement Method
    /* Following Rules: 
    - Large onto small only
    - Only movement vertical and horizontal, not diagonal
    - No movement after it is stacked
    */

   /* ---- WARNING ---- 
   - does not check adjacency first
   - potential solution involves moving adjacency logic to a separate method and calling before stacking
   */

   @Override
   public void movement(GamePiece[] array, GamePiece first, GamePiece second) {
        // Check if need to stack
        if (checkAdjacency(first, second) && first instanceof Snowball s1 && second instanceof Snowball s2) { // Ensure first and second can be seen as Snowball class to read size
            if (s1.size.equals("S") && s2.size.equals("L")) {
                s2.updateImage("snowman_stack.png");
                // Remove s1 from array, and replace with a new GamePiece
                return;
            }
        }
        super.movement(array, first, second);
   }

   
}