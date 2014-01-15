import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;

public class Loot{
	public int xcoord;
	public int ycoord;
	Image Loot;
	public Random generator = new Random();
	public boolean visible = false;

	public Loot() {
		ImageIcon i = new ImageIcon(this.getClass().getResource("Loot.png"));
		Loot = i.getImage();
		xcoord = 500;
		ycoord = 500;
	}

	public int getX() {
		return xcoord;
	}

	public int getY() {
		return ycoord;
	}

	public Image getImage() {
		return Loot;
	}

	public boolean checkCollisions(Rectangle knightRect, boolean k) {
		Rectangle lootRect = new Rectangle(this.getX(), this.getY(),
				48 - 12, 48);
		if ((lootRect.intersects(knightRect)) && (k == false)) {
			int ytemp1 = -1 * (generator.nextInt(100) + 100);
			int xtemp1 = -1 * (generator.nextInt(100) + 100);
			int ytemp2 = (generator.nextInt(412) + 300);
			int xtemp2 = (generator.nextInt(412) + 300);
			int option = generator.nextInt(4) + 1;
			switch (option) {
			case 1:
				this.xcoord = xtemp1;
				this.ycoord = ytemp1;
				break;
			case 2:
				this.xcoord = xtemp1;
				this.ycoord = ytemp2;
				break;
			case 3:
				this.xcoord = xtemp2;
				this.ycoord = ytemp1;
				break;
			case 4:
				this.xcoord = xtemp2;
				this.ycoord = ytemp2;
				break;
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean checkknightCollisions(Rectangle knightRect) {
		Rectangle skeletonRect = new Rectangle(this.getX(), this.getY(), 48, 48);
		if ((skeletonRect.intersects(knightRect))) {
			return true;
		} else {
			return false;
		}
	}

	public void makeVisible() {
		visible = true;
	}
}
