import javagames.util.*;

public class Collectible extends Sprite{
	
	public String fn = "Images/OTankTemp.png";
	public int decrease;
	public int increase = 50;
	
	String cType;
	
	final int spriteWidth = 32;
	final int spriteHeight = 40;
	
	public Collectible(float x, float y, String type){
		cType = type;
		switch(cType){
		case "Oxygen100":
			fn = "Images/OTankTemp.png";
			increase = 100;
			break;
		case "Oxygen70":
			fn = "Images/OTankTemp.png";
			increase = 80;
			break;
		case "Oxygen50":
			fn = "Images/OTankTemp.png";
			increase = 50;
			break;
		case "Oxygen20":
			fn = "Images/OTankTemp.png";
			increase = 40;
			break;
		case "Oxygen30":
			fn = "Images/OTankTemp.png";
			increase = 30;
			break;
		case "Oxygen10":
			fn = "Images/OTankTemp.png";
			increase = 10;
			break;
		case "GoldCoin":
			fn = "Images/GoldTemp.png";
			increase = 1;
			break;
		case "GoldNugget":
			fn = "Images/GoldTemp.png";
			increase = 30;
			break;
		case "GoldBar":
			fn = "Images/GoldTemp.png";
			increase = 120;
			break;
		case "Diamond":
			fn = "Images/GoldTemp.png";
			increase = 200;
			break;
		case "Arkenstone":
			fn = "Images/GoldTemp.png";
			increase = 10000;
			break;
		default:
			break;
		}
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