import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;

public class Life {
        public int xcoord;
        public int ycoord;
        Image Life;
        public Random generator = new Random();
        public int value = 1;
        // constructor that creates a new loot pile
        public Life() {
                ImageIcon l = new ImageIcon(this.getClass().getResource("Life.png"));
                Life = l.getImage();
                randomizePosition();
        }
        // returns the x coordinate
        public int getX() {
                return xcoord;
        }
        // returns the y coordinate
        public int getY() {
                return ycoord;
        }
        // returns the loot sprite
        public Image getImage() {
                return Life;
        }
        // generates the position of the loot and its value
        public void randomizePosition() {
                xcoord = generator.nextInt(450)+1;
                ycoord = generator.nextInt(450)+1;
        }
        // checks for knight collisions and adds to score and moves if true
        public boolean checkknightCollisions(Rectangle knightRect) {
                Rectangle LifeRect = new Rectangle(this.getX(), this.getY(), 48, 48);
                if ((LifeRect.intersects(knightRect))) {
                        randomizePosition();
                        return true;
                }else {
                        return false;
                }
        }
}
