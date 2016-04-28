import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private Ball ball;
	private Paddle player1, player2;
	private int score1, score2;
	
	public Board(Game game) {
		setBackground(Color.WHITE);
		this.game = game;
		ball = new Ball(game);
		player1 = new Paddle(game, KeyEvent.VK_UP, KeyEvent.VK_DOWN, game.getWidth()-36);
		player2 = new Paddle(game, KeyEvent.VK_W, KeyEvent.VK_S, 20);
		Timer timer = new Timer(5, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
	}
	
	public Paddle getPlayer(int player) {
		if(player == 1) return player1;
		else return player2;
	}
	
	public void increaseScore(int player) {
		if(player == 1) score1++;
		else score2++;
	}
	
	public int getScore(int player) {
		if (player == 1) return score1;
		else return score2;
	}
	
	private void update() {
		ball.update();
		player1.update();
		player2.update();
	}
	
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}
	
	public void keyPressed(KeyEvent e) {
		player1.pressed(e.getKeyCode());
		player2.pressed(e.getKeyCode());
	}
	
	public void keyReleased(KeyEvent e) {
		player1.released(e.getKeyCode());
		player2.released(e.getKeyCode());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString(game.getPanel().getScore(1)+" : "+game.getPanel().getScore(2), game.getWidth()/2, 10);
		ball.paint(g);
		player1.paint(g);
		player2.paint(g);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
