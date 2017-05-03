package goldrush.UI;
import java.awt.Color;
import java.awt.Graphics;

import goldrush.Util.Matrix3x3f;

public class HealthBar{
	
	int Max = 200;
	private int healthLevel = 200;
	//Screen coordinates
	int healthXPos = 25;
	int healthYPos = 25;
	int healthDrain = 3;
	//When rampUp becomes > 5, healthDrain increases by 3
	float rampUp = 0;
	
	int oxygenLevel = 200;
	//Screen coordinates
	int oxygenXPos = 55;
	int oxygenYPos = 25;
	int drainValue = 3;
	
	float time = 0;
	
	private boolean immune = false;
	
	public HealthBar(){
		
	}
	
	public void update(float delta){
		time += delta;
		
		//Drain oxygen every second
		if((int)time == 1 && oxygenLevel > 0){
			time = 0f;
			drainOxygen(drainValue);
		}
		//Once oxygen depletes, start decreasing health
		if(oxygenLevel <= 0){
			rampUp += delta;
			//System.out.println(healthLevel);
			if((int)time == 1){
				doDamage(healthDrain);
				System.out.println(healthDrain);
				time = 0f;
			}
			if(rampUp >= 5){
				rampUp = 0f;
				healthDrain += 3;
				//doDamage(healthDrain);
			}
		}
	}
	
	public void render(Graphics g, Matrix3x3f vp){
		g.setColor(Color.BLACK);
		g.fillRect(25-1, 25-1, 27 , Max+2);
		g.fillRect(55-1, 25-1, 27 , Max+2);
		g.setColor(Color.RED);
		g.fillRect(healthXPos, healthYPos, 25, getHealthLevel());
		g.setColor(Color.CYAN);
		g.fillRect(oxygenXPos, oxygenYPos, 25, oxygenLevel);
	}
	
	/*Decreases the healthBar by the given value*/
	public void doDamage(int hit){
		if(getHealthLevel() > 0 && !isImmune()){
			setHealthLevel(getHealthLevel() - hit);
			healthYPos += hit;
		}
	}
	
	/*Increases health by the given value*/
	public void addHealth(int heal){
		setHealthLevel(getHealthLevel() + heal);
		healthYPos -= heal;
		if(getHealthLevel() > 200){
			setHealthLevel(200);
			healthYPos = 25;
		}
	}
	
	/*Decreases the healthBar by the given value*/
	public void drainOxygen(int drain){
		if(oxygenLevel > 0){
			oxygenLevel -= drain;
			oxygenYPos += drain;
		}
	}
	/*Increases Oxygen by a given value*/
	public void addOxygen(int heal){
		oxygenLevel += heal;
		oxygenYPos -= heal;
		//If the player gains oxygen, reset health drain to 3
		healthDrain = 3;
		if(oxygenLevel > 200){
			oxygenLevel = 200;
			oxygenYPos = 25;
		}
	}

	public boolean isImmune() {
		return immune;
	}

	public void setImmune(boolean immune) {
		this.immune = immune;
	}

	public int getHealthLevel() {
		return healthLevel;
	}

	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}
}