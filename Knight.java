import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Knight {

	// variables
	public boolean notAttacking = true;
	public boolean knightgoingLeft = false;
	public int changeinX;
	public int changeinY;
	public int xcoord;
	public int ycoord;
	public long prevTime = 0;
	public boolean attackState = false;
	public boolean previousState = false;
	public boolean refresh = false;

	// initializes the images we will be using
	Image knight;
	Image knightAttack;
	Image knightMirror;
	Image knightAttackMirror;

	// constructor
	public Knight() {
		// loads all the different images knight will need ie. mirror and mirror
		// attack
		ImageIcon knightTemp = new ImageIcon(this.getClass().getResource(
				"Knight.png"));
		ImageIcon knightAttackTemp = new ImageIcon(this.getClass().getResource(
				"Knight Attack.png"));
		ImageIcon knightLeftTemp = new ImageIcon(this.getClass().getResource(
				"Knight Left.png"));
		ImageIcon knightAttackMirrorTemp = new ImageIcon(this.getClass()
				.getResource("Knight Attack Left.png"));
		knight = knightTemp.getImage();
		knightAttack = knightAttackTemp.getImage();
		knightMirror = knightLeftTemp.getImage();
		knightAttackMirror = knightAttackMirrorTemp.getImage();

		// sets the initial x coordinates
		xcoord = 232;
		ycoord = 232;
	}

	// moves the knight by changing the coordinates
	public void move() {

		xcoord += changeinX;
		ycoord += changeinY;
	}

	// returns the xcoord for boards use
	public int getX() {
		return xcoord;
	}

	// returns the ycoord for boards use
	public int getY() {
		return ycoord;
	}

	public void check() {
		if (attackState == true) {
			if ((System.currentTimeMillis() - prevTime) > 500) {
				notAttacking = true;
			} else {
				notAttacking = false;
			}
		}else{
			notAttacking = true;
		}
	}

	// returns the image based on what direction the knight is going
	public Image getImage() {
		// when it is going right it returns the normal pictures
		if (knightgoingLeft == false) {
			if (notAttacking) {
				return knight;
			} else {
				return knightAttack;
			}
			// mirror pictures if going left
		} else {
			if (notAttacking) {
				return knightMirror;
			} else {
				return knightAttackMirror;
			}
		}
	}

	// handles a key press
	public void keyPressed(KeyEvent e) {
		
		// gets what key is pressed
		int key = e.getKeyCode();

		// x key if for attack
		if ((key == KeyEvent.VK_X)){
			attackState = true;
			if(previousState == false){
			prevTime = System.currentTimeMillis();
			previousState = true;
			}
			notAttacking = false;
		}

		// only goes left if it would result in the knight being inbounds
		if ((key == KeyEvent.VK_LEFT) && (xcoord > 0)) {
			// changes the direction the knight is going
			knightgoingLeft = true;
			changeinX = -4;
		} else if (xcoord <= 0) {
			changeinX = 0;
			xcoord = -1;
		}

		// only goes right if it would result in the knight being inbounds
		if ((key == KeyEvent.VK_RIGHT) && (xcoord < 464)) {
			changeinX = 4;
			// changes the direction the knight is going
			knightgoingLeft = false;
		} else if (xcoord >= 464) {
			changeinX = 0;
			xcoord = 463;
		}

		// only goes up if it would result in the knight being inbounds
		if (key == KeyEvent.VK_UP && ycoord > 0) {
			changeinY = -4;
		} else if (ycoord <= 0) {
			changeinY = 0;
			ycoord = -1;
		}

		// only goes down if it would result in the knight being inbounds
		if ((key == KeyEvent.VK_DOWN) && (ycoord < 436)) {
			changeinY = 4;
		} else if (ycoord >= 436) {
			changeinY = 0;
			ycoord = 435;
		}

	}

	// handles a keyRelease
	// sets the increment that the knight needs to move to 0
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		if ((key == KeyEvent.VK_X)) {
			//does not let the knight attack
			attackState = false;
			notAttacking = true;
			previousState = false;
		}
		//dx and dy to 0 for all directions
		if (key == KeyEvent.VK_LEFT) {
			changeinX = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			changeinX = 0;
		}

		if (key == KeyEvent.VK_UP) {
			changeinY = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			changeinY = 0;
		}
		//space refreshes the game or starts the game when pressed and released
		if (key == KeyEvent.VK_SPACE){
			refresh = true;
		}
	}
}
