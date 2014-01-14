import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Knight {
	public boolean notAttacking = true;
	public boolean left = false;
	public int dx;
	public int dy;
	public int xcoord;
	public int ycoord;
	Image knight;
	Image knightAttack;
	Image knightMirror;
	Image knightAttackMirror;

	public Knight() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("Knight.png"));
		ImageIcon iii = new ImageIcon(this.getClass().getResource(
				"Knight Attack.png"));
		ImageIcon i = new ImageIcon(this.getClass().getResource(
				"Knight Left.png"));
		ImageIcon iiii = new ImageIcon(this.getClass().getResource(
				"Knight Attack Left.png"));
		knight = ii.getImage();
		knightAttack = iii.getImage();
		knightMirror = i.getImage();
		knightAttackMirror = iiii.getImage();
		xcoord = 48;
		ycoord = 48;
	}

	public void move() {
		xcoord += dx;
		ycoord += dy;
	}

	public int getX() {
		return xcoord;
	}

	public int getY() {
		return ycoord;
	}

	public Image getImage() {
		if (left == false) {
			if (notAttacking) {
				return knight;
			} else {
				return knightAttack;
			}
		} else {
			if (notAttacking) {
				return knightMirror;
			} else {
				return knightAttackMirror;
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if ((key == KeyEvent.VK_X)) {
			notAttacking = false;
		}
		if ((key == KeyEvent.VK_LEFT) && (xcoord > 0)) {
			left = true;
			dx = -4;
		} else if (xcoord <= 0) {
			dx = 0;
			xcoord = -1;
		}
		if ((key == KeyEvent.VK_RIGHT) && (xcoord < 464)) {
			dx = 4;
			left = false;
		} else if (xcoord >= 464) {
			dx = 0;
			xcoord = 463;
		}
		if (key == KeyEvent.VK_UP && ycoord > 0) {
			dy = -4;
		} else if (ycoord <= 0) {
			dy = 0;
			ycoord = -1;
		}
		if ((key == KeyEvent.VK_DOWN) && (ycoord < 436)) {
			dy = 4;
		} else if (ycoord >= 436) {
			dy = 0;
			ycoord = 435;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if ((key == KeyEvent.VK_X)) {
			notAttacking = true;
		}
		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
}
