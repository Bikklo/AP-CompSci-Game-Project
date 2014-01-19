import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;

public class Loot {
	public int xcoord;
	public int ycoord;
	Image Loot;
	public Random generator = new Random();
	public int value;

	// constructor that creates a new loot pile
	public Loot() {
		ImageIcon l = new ImageIcon(this.getClass().getResource("Loot.png"));
		Loot = l.getImage();
		randomizePositionandValue();
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
		return Loot;
	}

	// generates the position of the loot and its value
	public void randomizePositionandValue() {
		xcoord = generator.nextInt(450) + 1;
		ycoord = generator.nextInt(450) + 1;
		value = generator.nextInt(10) + 1;
	}

	// checks for knight collisions and adds to score and moves if true
	public boolean checkknightCollisions(Rectangle knightRect) {
		Rectangle skeletonRect = new Rectangle(this.getX(), this.getY(), 48, 48);
		if ((skeletonRect.intersects(knightRect))) {
			randomizePositionandValue();
			return true;
		} else {
			return false;
		}
	}
}
