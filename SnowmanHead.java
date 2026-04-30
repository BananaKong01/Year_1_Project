public class SnowmanHead extends GamePiece {
    String colour;
    // Constructor Method
    public SnowmanHead(String path, int x, int y, String colour) {
        this.colour = colour;
        super(path, x, y);
    }

    // Movement Method
    /* Can only move onto stack next to it*/
    @Override
    public void movement(GamePiece[] array, GamePiece first, GamePiece second) {
        if (checkAdjacency(first, second) && first instanceof SnowmanHead s1 && second instanceof Snowball s2) {
            if (s2.getStack() == true) {
                if (colour.equals("blue")) {
                    s2.updateImage("snowman_blue.png");
                } else if (colour.equals("red")) {
                    s2.updateImage("snowman_red.png");
                } else if (colour.equals("yellow")) {
                    s2.updateImage("snowman_yellow.png");
                }
                // Remove s1 from array, and replace with a new GamePiece
                for (int i = 0; i < array.length; i++) {
                    if (array[i] == s1) {
                        int a = s1.getX();
                        int b = s1.getY();
                        array[i] = new GamePiece ("hole.png", a, b);
                    }
                }
            }
        }
   }
}
