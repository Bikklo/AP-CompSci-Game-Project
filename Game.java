import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame {
	// constructor makes the window
	public Game() {
		add(new Board());
		setTitle("Skeleton Horde");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(512, 512);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
	// creates a new game
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Game g = new Game();
	}
}
