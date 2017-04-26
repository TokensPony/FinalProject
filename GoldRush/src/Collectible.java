import javagames.util.*;

public class Collectible extends Sprite{
	
	public String fn = "Images/OTankTemp.png";
	public int decrease;
	public int increase = 50;
	
	AePlayWave collectSFX = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");
	
	String cType;
	
	int spriteWidth = 32;
	int spriteHeight = 40;
	
	public Collectible(float x, float y, String type){
		cType = type;
		switch(cType){
		case "LargePastie":
			fn = "Images/PastyLarge.png";
			spriteWidth = 44;
			spriteHeight = 20;
			increase = 100;
			collectSFX = new AePlayWave("Sounds/Health Pickup.wav");
			break;
		case "SmallPastie":
			fn = "Images/HealthTemp.png";
			increase = 50;
			collectSFX = new AePlayWave("Sounds/Health Pickup.wav");
			break;
		case "Chips":
			fn = "Images/HealthTemp.png";
			increase = 10;
			collectSFX = new AePlayWave("Sounds/Health Pickup.wav");
			break;
		case "Oxygen50":
			fn = "Images/OxygenTank50.png";
			spriteWidth = 19;
			spriteHeight = 50;
			increase = 50;
			collectSFX = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");
			break;
		case "Oxygen20":
			fn = "Images/OxygenTank20.png";
			spriteWidth = 19;
			spriteHeight = 50;
			increase = 20;
			collectSFX = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");
			break;
		case "Oxygen30":
			fn = "Images/OxygenTank30.png";
			spriteWidth = 19;
			spriteHeight = 50;
			increase = 30;
			collectSFX = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");
			break;
		case "Oxygen10":
			fn = "Images/OxygenTank10.png";
			spriteWidth = 19;
			spriteHeight = 50;
			increase = 10;
			collectSFX = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");
			break;
		case "GoldCoin":
			fn = "Images/GoldTemp.png";
			increase = 1;
			collectSFX = new AePlayWave("Sounds/coin-drop-4.wav");
			break;
		case "GoldNugget":
			fn = "Images/GoldTemp.png";
			increase = 30;
			collectSFX = new AePlayWave("Sounds/coin-drop-4.wav");
			break;
		case "GoldBar":
			fn = "Images/GoldTemp.png";
			increase = 120;
			collectSFX = new AePlayWave("Sounds/coin-drop-4.wav");
			break;
		case "Diamond":
			fn = "Images/GoldTemp.png";
			increase = 200;
			collectSFX = new AePlayWave("Sounds/coin-drop-4.wav");
			break;
		case "Arkenstone":
			fn = "Images/GoldTemp.png";
			collectSFX = new AePlayWave("Sounds/coin-drop-4.wav");
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