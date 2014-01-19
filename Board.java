import java.awt.*;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

	// variables
	int score = 0;
	int tempX = 0;
	int tempY = 0;
	Image explosion;
	Random generator = new Random(8659);
	Image background;
	Timer timer;
	Font scoreFont = new Font("Arcadepix", Font.TRUETYPE_FONT,
			Font.ROMAN_BASELINE).deriveFont(15.0f);
	FontMetrics scoreFontMetrics = this.getFontMetrics(this.scoreFont);
	public Knight k = new Knight();
	Loot[] lootArray = new Loot[5];
	Skeleton[] skeletonArray = new Skeleton[1000];
	Life heart = new Life();
	int health;
	int wave;

	// Constructor
	public Board() {
		// starts the key listening and sets up image drawing
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);

		// initial health and wave
		health = 10;
		wave = 1;

		// makes piles of loot
		for (int x = 0; x < 3; x++) {
			lootArray[x] = new Loot();
		}

		// makes the initial skeleton array(adds a new skeleton in each spot)
		for (int x = 0; x < skeletonArray.length; x++) {
			skeletonArray[x] = new Skeleton();
		}

		// makes the first two skeletons visible
		skeletonArray[0].makeVisible();
		skeletonArray[1].makeVisible();

		// sets up the timer
		timer = new Timer(10, this);
		timer.start();

		// loads the background
		loadBackgroundImage();
	}

	// graphics method(basically has all our coordinate tracking)
	public void paint(Graphics g) {

		// sets up the graphics
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		// only does all of this if the health is greater than 0
		if (health > 0) {
			k.check();
			// rectangle for knight for collision detection
			Rectangle knightRect = new Rectangle(k.getX(), k.getY(), 48, 48);

			// sets the wave
			wave = score / 10;

			// makes the appropriate number of skeletons visible
			if ((Math.pow(wave, 2)/4) < 999) {
				for (int x = 0; x < (Math.pow(wave, 2)/4); x++) {
					skeletonArray[x].makeVisible();
				}
			}

			// creates the right amount of loot piles
			for (int x = 0; x < 3; x++) {
				if (lootArray[x].checkknightCollisions(knightRect)) {
					score += lootArray[x].value;
				}
			}
			
			if (heart.checkknightCollisions(knightRect)) {
				health += heart.value;
			}
			
			// goes through and handles skeleton collisions(if they are visible)
			for (int x = 0; x < skeletonArray.length; x++) {
				if (skeletonArray[x].visible) {
					if (skeletonArray[x].checkCollisions(knightRect,
							k.notAttacking)) {
						score++;
					} else {
						if (k.notAttacking) {
							if ((skeletonArray[x]
									.checkknightCollisions(knightRect))) {
								health--;
								skeletonArray[x].xcoord -= 100;
							}
						}
					}
				}
			}

			// draws the knight
			g2d.drawImage(k.getImage(), k.getX(), k.getY(), this);

			// draws loots

			for (int x = 0; x < 3; x++) {
				g2d.drawImage(lootArray[x].getImage(), lootArray[x].getX(),
						lootArray[x].getY(), this);
			}
			
			g2d.drawImage(heart.getImage(), heart.getX(), heart.getY(), this);

			// draws every skeleton(if visible)
			for (int x = 0; x < skeletonArray.length; x++) {
				if (skeletonArray[x].visible) {
					g2d.drawImage(skeletonArray[x].getImage(),
							skeletonArray[x].getX(), skeletonArray[x].getY(),
							this);
					skeletonArray[x].move(k.getX(), k.getY());
				}
			}

			// prints the score, health, and wave
			g2d.setColor(Color.WHITE);
			g2d.setFont(this.scoreFont);
			g2d.drawString(
					String.format("  Score: %d", this.score),
					-20,
					this.scoreFontMetrics.getHeight()
							- this.scoreFontMetrics.getMaxAscent()
							+ this.scoreFontMetrics.getAscent());
			g2d.drawString(
					String.format("  Health: %d", this.health),
					-20,
					2 * (this.scoreFontMetrics.getHeight()
							- this.scoreFontMetrics.getMaxAscent() + this.scoreFontMetrics
							.getAscent()));
			g2d.drawString(
					String.format("  Wave: %d", this.wave + 1),
					-20,
					3 * (this.scoreFontMetrics.getHeight()
							- this.scoreFontMetrics.getMaxAscent() + this.scoreFontMetrics
							.getAscent()));
		} else {
			// prints game over if health < 0
			Image gameOver;
			g2d.setColor(Color.WHITE);
			g2d.setFont(this.scoreFont);
			g2d.drawString(
					String.format("  Score: %d", this.score),
					-20,
					this.scoreFontMetrics.getHeight()
							- this.scoreFontMetrics.getMaxAscent()
							+ this.scoreFontMetrics.getAscent());
			ImageIcon gO = new ImageIcon(this.getClass().getResource(
					"Game Over.png"));
			gameOver = gO.getImage();
			g2d.drawImage(gameOver, 0, 0, null);
		}

		// From zetcode.net java games tutorial(probably refreshes everything),
		// makes things work
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	// paints the background
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int imageWidth = background.getWidth(this);
		int imageHeight = background.getHeight(this);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		g.drawImage(background, x, y, this);

		// background is black when game is over
		if (health <= 0) {
			g.setColor(new Color(0x000000));
			g.fillRect(0, 0, width, height);
		}
	}

	// loads the background image(dirt floor)
	public void loadBackgroundImage() {
		ImageIcon image = new ImageIcon(this.getClass().getResource(
				"Background.png"));
		background = image.getImage();
	}

	// if an action is preformed, knight is moved
	public void actionPerformed(ActionEvent e) {
		k.move();
		repaint();
	}

	// from zetcode.net java games tutorial (if the key is released or the key
	// is pressed, runs knights key released and key pressed methods)
	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			k.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			k.keyPressed(e);
		}
	}
}
