

import java.awt.Graphics;

public class Score{
	private int score = 0;
	private int combo = 0;
	private static String s = "0";
	private static String c = "";
	private boolean showStreak = false;
	
	/**
	 * Constructor method for the score/Combo
	 */
	public Score(){
		
	}
	
	/**
	 * This version of increaseScore takes in a float value for the size of the
	 * ball that has just been hit, then increases the score based on which one
	 * was destroyed. Large = 100, Medium = 250, Small = 1000. It then also adds
	 * the value of the current combo streak to the score
	 * @param size
	 */
	public void increaseScore(float size){
		if(size == .75f){
			score += 100;
		}else if(size == .5f){
			score += 250;
		}else if(size == .25f){
			score += 500;
		}
		score += combo*100;
	}
	
	/**
	 * This version of increaseScore directly adds to the score what has passed
	 * to it in the x parameter
	 * @param x
	 */
	public void increaseScore(int x){
		score += x;
	}
	
	/**
	 * Each time a ball has been hit consecutively, the value for combo is
	 * increased by 1.
	 */
	public void increaseCombo(){
		combo++;
	}
	
	/**
	 * Resets combo back to 0
	 */
	public void comboBreaker(){
		//increaseScore(combo * 100);
		combo = 0;
	}
	
	/**
	 * Resets score and combo back to 0
	 */
	public void reset(){
		score = 0;
		combo = 0;
	}
	
	/**
	 * Renders the strings for score and the combo streak
	 * @param g
	 */
	public void render(Graphics g){
		s = s.format("%d", score);
		g.drawString(s, 1200, 20);
		//c = c.format("Combo Bonus %d x 100",combo);
		//g.drawString(c, 20, 60);
	}
}