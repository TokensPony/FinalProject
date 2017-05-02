import java.awt.Color;
import java.awt.Graphics;

public class Score{
	private int score = 0;

	private int scoreX = 100;
	private int scoreY = 23;
	private int boldness = 2;
	
	private static String s = "0";
	
	/* Constructor method for the score/Combo */
	public Score(){
	}
	
	/* adds x parameter to score */
	public void increaseScore(int x){
		score += x;
	}

	/* returns the score */
	public int getScore(){
		return score;
	}
	
	/* Resets score */
	public void reset(){
		score = 0;
	}
	
	/* Renders the score */
	public void render(Graphics g){
		//This Draws the same string 4 times in offset positions then draws it in
		//White to make a boarder
		s = s.format("%d", score);
		g.setColor(Color.BLACK);
		g.drawString(s, scoreX+boldness, scoreY);
		g.drawString(s, scoreX, scoreY+boldness);
		g.drawString(s, scoreX-boldness, scoreY);
		g.drawString(s, scoreX, scoreY-boldness);
		g.setColor(Color.WHITE);
		g.drawString(s, scoreX, scoreY);
	}
}
