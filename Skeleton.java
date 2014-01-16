import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.Random;

public class Skeleton {
	
	//variables
	public int xcoord;
	public int ycoord;
	Image Skeleton;
	public Random generator = new Random();
	public boolean visible = false;
	
	//constructor
	public Skeleton() {
		//loads the image
		ImageIcon i = new ImageIcon(this.getClass().getResource("Skeleton.png"));
		Skeleton = i.getImage();
		
		//sets initial x and y coordinates
		xcoord = 500;
		ycoord = 500;
	}
	
	//moves the skeleten towards the knight(knights x and y coordinates are in the parameters)
	public void move(int X, int Y) {
		if (X > xcoord) {
			xcoord++;
		}
		if (X < xcoord) {
			xcoord--;
		}
		if (Y > ycoord) {
			ycoord++;
		}
		if (Y < ycoord) {
			ycoord--;
		}
	}
	
	//returns x coordinate
	public int getX() {
		return xcoord;
	}
	
	//returns y coordinate
	public int getY() {
		return ycoord;
	}
	
	//returns the image
	public Image getImage() {
		return Skeleton;
	}
	
	//checks collisions
	public boolean checkCollisions(Rectangle knightRect, boolean k){
		//makes a rectangle around the skeleton
		Rectangle skeletonRect = new Rectangle(this.getX(), this.getY(), 48-12, 48);
		//if it intersects with the knight and it is attacking, the skeleton moves out of the map
		if ((skeletonRect.intersects(knightRect)) && (k == false)) {
			//different temporary options for every corner of the map
			int ytemp1 = -1 * (generator.nextInt(100) + 100);
			int xtemp1 = -1 * (generator.nextInt(100) + 100);
			int ytemp2 = (generator.nextInt(412) + 300);
			int xtemp2 = (generator.nextInt(412) + 300);
			int option = generator.nextInt(4) + 1;
			//switch chooses between the top left, top right, bottom left or bottom right corner
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
		}else{
			return false;
		}
	}
	
	//just returns true or false based on where the knight is and if it is intersecting(smaller version of previous method)
	public boolean checkknightCollisions(Rectangle knightRect){
		Rectangle skeletonRect = new Rectangle(this.getX(), this.getY(), 48, 48);
		if ((skeletonRect.intersects(knightRect))){
			return true;
		}else{
			return false;
		}
	}
	
	//method to make the skeleton visible
	public void makeVisible(){
		visible = true;
	}
}
