package goldrush.Maze;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Overlay {

	GameMode mode;
	private int score;

	int xPix = 1280;
	int yPix = 720;

	String gameOver = "Game Over!";
	String nForNew = "Press \"n\" to start a new game.";

	public Overlay() {
		mode = GameMode.STANDBY;
		score = 0;
	}

	public void render(Graphics g) {

		switch (mode) {
		case STANDBY:
			break;
		case GAMEOVER:
			g.setColor(Color.RED);
			drawText(g, gameOver, 640, 360);
			drawText(g, "Your final score was: " + String.valueOf(score), 640, 400);
			drawText(g, nForNew, 640, 440);
			break;
		case GAMEWON:
			drawBoldText(g, "You've found the Arkenstone!", 640, 350, 1);
			drawBoldText(g, "Final Score: " + String.valueOf(score), 640, 400, 1);
			drawBoldText(g, nForNew, 640, 450, 1);

			break;
		}
	}

	private void drawText(Graphics g, String text, int centerX, int centerY) {
		int finalX, finalY, width, height;
		FontMetrics fm = g.getFontMetrics();
		width = fm.stringWidth(text);
		height = fm.getAscent() + fm.getDescent() + fm.getLeading();
		finalX = centerX - width / 2;
		finalY = centerY - height / 2 - fm.getDescent() - fm.getLeading();
		g.drawString(text, finalX, finalY);
	}

	private void drawBoldBackText(Graphics g, String text, int centerX, int centerY, int b) {
		drawText(g, text, centerX + b, centerY);
		drawText(g, text, centerX - b, centerY);
		drawText(g, text, centerX, centerY + b);
		drawText(g, text, centerX, centerY - b);
	}

	private void drawBoldText(Graphics g, String text, int centerX, int centerY, int b) {
		g.setColor(Color.BLACK);
		drawBoldBackText(g, text, centerX, centerY, b);
		g.setColor(Color.WHITE);
		drawText(g, text, centerX, centerY);
	}

	public void updateScore(int s) {
		score = s;
	}

	public void updateMode(GameMode m) {
		mode = m;
	}

}
