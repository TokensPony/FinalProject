import javagames.util.*;

public class Collectible extends Sprite{
	
	public String fn = "Images/OTankTemp.png";
	public int decrease;
	public int increase = 50;
	
	String cType;
	
	final int spriteWidth = 32;
	final int spriteHeight = 40;
	
	public Collectible(float x, float y, String type, int value){
		cType = type;
		switch(cType){
		case "Oxygen":
			fn = "Images/OTankTemp.png";
			break;
		case "Gold":
			fn = "Images/GoldTemp.png";
		default:
			break;
		}
		increase = value;
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(x, y);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
	}

	public int getIncrease(){
		return increase;
	}
	
	public String getType(){
		return cType;
	}
}