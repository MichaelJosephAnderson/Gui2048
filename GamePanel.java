package gui2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener {

	public static int moveCount;
	private static final Font titleFont = new Font("Comic Sans", Font.BOLD, 100);
	private static final Font finalFont = new Font("Comic Sans", Font.BOLD, 90);
	
	// have the colors for different value tiles, blank, 2, 4, 8, 16...
	public static final Color[] tileColors = {
			Color.WHITE,
			new Color (255, 243, 204),
			new Color (255, 202, 153),
			new Color (255, 163, 77),
			new Color (255, 121, 77),
			new Color (255, 64, 0), 
			new Color (255, 0, 102),
			new Color (255, 204, 51),
			new Color (255, 191, 0),
			new Color (255, 255, 0),
			new Color (230, 172, 0),
			new Color (172, 0, 230),
			
	};
	
	public Color getCorrespondingColor(int value) {
		
		if (value == 0) {
			return tileColors[0];
		}
		int index = (int)Math.round(Math.log(value) / Math.log(2));
		if (index >= tileColors.length) {
			return Color.WHITE;
		}
		return tileColors[index];
	}
	
	public void drawTile(Graphics g, int row, int col, int value) {
		int screenWidth = this.getWidth();
		int screenHeight = this.getHeight();
		
		int tileX = (screenWidth / 4);
		int tileY = (screenHeight / 4);
		
		int cornerX = row * tileX;
		int cornerY = col * tileY;
		
		g.setColor(getCorrespondingColor(value));
		g.fillRect(cornerX, cornerY, tileX, tileY);
		
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		
		if (value != 0) {
			if (value < 10) {
			g.drawString(value + "", cornerX + 70, cornerY + 130);
			}else if (value < 100) {
				g.drawString(value + "", cornerX + 50, cornerY + 130);
			}else if (value < 1000) {
				g.drawString(value + "", cornerX + 17, cornerY + 130);
			}else if (value > 1000) {
				g.setFont(finalFont);
				g.drawString(value + "", cornerX + 1, cornerY + 130);
			}
		}
		
		g.setColor(Color.BLACK);
		g.drawLine(cornerX, cornerY, cornerX + tileX, cornerY);
		g.drawLine(cornerX, cornerY, cornerX, cornerY + tileY);
		
	}
	
	public void drawBoard(Graphics g) {
		int[][] board = Console2048.getBoard();
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				drawTile(g, c, r, board[r][c]);
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		drawBoard(g);
	}
	
	public static int getMoveCount() {
		return moveCount;
	}
	
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if (keyCode == KeyEvent.VK_DOWN) {
			Console2048.moveBoard('s');
		}else if (keyCode == KeyEvent.VK_UP) {
			Console2048.moveBoard('w');
		}else if (keyCode == KeyEvent.VK_LEFT) {
			Console2048.moveBoard('a');
		}else if (keyCode == KeyEvent.VK_RIGHT) {
			Console2048.moveBoard('d');
		}
		repaint();
		gameUpdate();
		moveCount++;
		
	}

	//This will be all of the other game stuff like checking for win and loose and refilling the board
	public void gameUpdate() {
		if(Console2048.update() == false) {
			System.exit(0);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
