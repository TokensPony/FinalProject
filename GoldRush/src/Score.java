import java.awt.Color;
import java.awt.Graphics;

public class Score{

	//location to draw score (pixels)
	private final int scoreX = 100;
	private final int scoreY = 23;
	
	//how bold to draw the score (controls offset in render)
	private final int boldness = 2;
	
	private int score;
	
	/* Constructor method for the score/Combo */
	public Score(){
		score = 0;
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
		String s = Integer.toString(score);
		g.setColor(Color.BLACK);
		g.drawString(s, scoreX+boldness, scoreY);
		g.drawString(s, scoreX, scoreY+boldness);
		g.drawString(s, scoreX-boldness, scoreY);
		g.drawString(s, scoreX, scoreY-boldness);
		g.setColor(Color.WHITE);
		g.drawString(s, scoreX, scoreY);
	}
}
