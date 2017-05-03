package goldrush.Maze;

import goldrush.Util.*;

public class Collectible extends Sprite {

	public String fn = "Images/OTankTemp.png";
	public int decrease;
	public int increase = 50;

	AePlayWave collectSFX = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");

	String cType;

	int spriteWidth = 32;
	int spriteHeight = 40;

	/*Constructor sets sprite image, size, value, and sound effects for new collectibles*/
	public Collectible(float x, float y, String type) {
		cType = type;
		switch (cType) {
		case "LargePasty":
			fn = "Images/PastyLarge.png";
			spriteWidth = 44;
			spriteHeight = 20;
			increase = 100;
			collectSFX = new AePlayWave("Sounds/Health Pickup.wav");
			break;
		case "SmallPasty":
			fn = "Images/PastySmall.png";
			spriteWidth = 30;
			spriteHeight = 13;
			increase = 50;
			collectSFX = new AePlayWave("Sounds/Health Pickup.wav");
			break;
		case "Chips":
			fn = "Images/ChipsBag.png";
			spriteWidth = 23;
			spriteHeight = 27;
			increase = 10;
			collectSFX = new AePlayWave("Sounds/Chips.wav");
			break;
		case "Oxygen100":
			fn = "Images/OxygenTank100.png";
			spriteWidth = 19;
			spriteHeight = 50;
			increase = 50;
			collectSFX = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");
			break;
		case "Oxygen80":
			fn = "Images/OxygenTank80.png";
			spriteWidth = 19;
			spriteHeight = 50;
			increase = 50;
			collectSFX = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");
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
			fn = "Images/GoldCoin.png";
			spriteWidth = 9;
			spriteHeight = 12;
			increase = 1;
			collectSFX = new AePlayWave("Sounds/coin-drop-4.wav");
			break;
		case "GoldNugget":
			fn = "Images/GoldNugget.png";
			spriteWidth = 23;
			spriteHeight = 26;
			increase = 30;
			collectSFX = new AePlayWave("Sounds/coin-drop-4.wav");
			break;
		case "GoldBar":
			fn = "Images/GoldBar.png";
			spriteWidth = 45;
			spriteHeight = 25;
			increase = 120;
			collectSFX = new AePlayWave("Sounds/GoldBar.wav");
			break;
		case "Diamond":
			fn = "Images/Diamond.png";
			spriteWidth = 35;
			spriteHeight = 30;
			increase = 200;
			collectSFX = new AePlayWave("Sounds/Ding.wav");
			break;
		case "Arkenstone":
			fn = "Images/Arkenstone.png";
			spriteWidth = 66;
			spriteHeight = 75;
			collectSFX = new AePlayWave("Sounds/Arkenstone.wav");
			increase = 100000;
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

	public int getIncrease() {
		return increase;
	}

	public String getType() {
		return cType;
	}
}