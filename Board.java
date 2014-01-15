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
	int score = 0;
	int tempX = 0;
	int tempY = 0;
	Image explosion;
	Random generator = new Random(8659);
	Image background;
	Timer timer;
	Font scoreFont = new Font("Comic Sans MS", Font.PLAIN, Font.ROMAN_BASELINE).deriveFont(20.0f);
	FontMetrics scoreFontMetrics = this.getFontMetrics(this.scoreFont);
	public Knight k = new Knight();
	Skeleton[] skeletonArray = new Skeleton[10000];
	int health;
	int wave;

	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		health = 10;
		wave = 1;
		for(int x = 0; x < skeletonArray.length; x++){
			skeletonArray[x] = new Skeleton();
		}

		skeletonArray[0].makeVisible();
		timer = new Timer(8, this);
		timer.start();
		loadImage();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if(health > 0){
		
		Rectangle knightRect = new Rectangle(k.getX(), k.getY(), 48, 48);
		
		wave = score/10;
		
		for(int x = 0; x < (wave^2); x++){
			skeletonArray[x].makeVisible();
		}

		for(int x = 0; x < skeletonArray.length; x++){
			if(skeletonArray[x].visible){
				if(skeletonArray[x].checkCollisions(knightRect, k.notAttacking)){
					score++;
				}else{
					if(k.notAttacking)
						if((skeletonArray[x].checkknightCollisions(knightRect))){
							health--;
							skeletonArray[x].xcoord -= 100;
						}
				}
			}
		}
		
		g2d.drawImage(k.getImage(), k.getX(), k.getY(), this);
		
		for(int x = 0; x < skeletonArray.length; x++){
			if(skeletonArray[x].visible){
				g2d.drawImage(skeletonArray[x].getImage(), skeletonArray[x].getX(), skeletonArray[x].getY(), this);
				skeletonArray[x].move(k.getX(), k.getY());
			}
		}
		
		g2d.setColor(Color.ORANGE);
		g2d.setFont(this.scoreFont);
		g2d.drawString(String.format("Score: %d", this.score), 0, this.scoreFontMetrics.getHeight() - this.scoreFontMetrics.getMaxAscent() + this.scoreFontMetrics.getAscent());
		g2d.drawString(String.format("Health: %d", this.health), 0, 2 * (this.scoreFontMetrics.getHeight() - this.scoreFontMetrics.getMaxAscent() + this.scoreFontMetrics.getAscent()));
		g2d.drawString(String.format("Wave: %d", this.wave), 0, 3 * (this.scoreFontMetrics.getHeight() - this.scoreFontMetrics.getMaxAscent() + this.scoreFontMetrics.getAscent()));
		}else{
			Font gameOverFont = this.scoreFont.deriveFont(100.0f);
			FontMetrics gameOverFontMetrics = this.getFontMetrics(gameOverFont);
			g2d.setFont(gameOverFont);
			g2d.setColor(new Color((0x100 - 1) << 16));
			g2d.drawString("Game Over", 0, gameOverFontMetrics.getHeight() - gameOverFontMetrics.getMaxAscent() + gameOverFontMetrics.getAscent());
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		int imageWidth = background.getWidth(this);
		int imageHeight = background.getHeight(this);
		int x = (width - imageWidth) / 2;
		int y = (height - imageHeight) / 2;
		g.drawImage(background, x, y, this);
		if(health <= 0){
		g.setColor(new Color(0x000000));
		g.fillRect(0, 0, width, height);
		}
	}

	public void loadImage() {
		ImageIcon image = new ImageIcon(this.getClass().getResource(
				"Background.png"));
		background = image.getImage();
	}

	public void actionPerformed(ActionEvent e) {
		k.move();
		repaint();
	}

	private class TAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			k.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			k.keyPressed(e);
		}
	}
}
