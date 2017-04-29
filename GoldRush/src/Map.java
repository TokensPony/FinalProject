import javagames.util.*;

public class Map {

	public Background background;
	public WarpTile warpTile;
	public Collectible collectible;

	public RoomData[] roomData;
	
	//public AePlayWave goldCollect = new AePlayWave("Sounds/coin-drop-4.wav");
	//public AePlayWave oxygenCollect = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");
	
	enum type {
		Normal, Rock
	};

	public Map() {
		background = new Background(0f, 0f);
		roomData = new RoomData[] { new RoomData("Images/Room-0.png")/*Room 0*/, new RoomData("Images/Room-1.png", "GoldIntroduction")/*Room 1*/,
				new RoomData("Images/Room-0.png", "OxygenIntroduction")/*Room 2*/, new RoomData("Images/Room-1.png", "Room3")/*Room 3*/, new RockChallengeRoom("Images/Room-0.png", "FallingRocks")/*Room 4*/, 
				new LavaRoom("Images/Room-1.png", "LavaRoom")/*Room 5*/, new QTERoom("Images/Room-0.png", "QTE")/*Room 6 QTE*/, new RoomData("Images/Room-1.png")/*Room 7 Challenge Gold Room*/};
	}

	public void initialize(int appWidth, int appHeight, float appWorldWidth, float appWorldHeight) {

		background.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		background.setSubBox();
		background.setSprite("thing");

		// Room 0 Tile 0
		WarpTile s0 = new WarpTile(10, 7f, 0f, -7.8f, 0, false);//this is for the exit
		WarpTile s1 = new WarpTile(1, 0, -3f, 0, 4.2f, true);

		roomData[0].addWarpTile(s1);

		// Room 1 Tile 0
		WarpTile s2 = new WarpTile(0, 0, 3f, 0, -4.2f, true);
		WarpTile s3 = new WarpTile(2, -7f, 0, 7.7f, 0f, true);
		Collectible c1 = new Collectible(0.2f, 0.2f, "GoldCoin");
		Collectible c2 = new Collectible(-0.2f, -0.2f, "GoldCoin");
		Collectible c3 = new Collectible(0.2f, -0.2f, "GoldCoin");
		Collectible c4 = new Collectible(-0.2f, 0.2f, "GoldCoin");
		Collectible c5 = new Collectible(2f, 2f, "GoldNugget");
		Collectible c6 = new Collectible(-2f, -2f, "GoldBar");
		c1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c2.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c3.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c4.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c5.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c6.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);

		// Room 2 Tile 0
		WarpTile s4 = new WarpTile(1, 7f, 0f, -7.8f, 0, true);
		WarpTile s5 = new WarpTile(3, -7f, 0, 7.7f, 0f, true);
		Collectible o1 = new Collectible(0f, 0f, "Oxygen10");
		Collectible o2 = new Collectible(-5f, 3f, "Oxygen10");
		Collectible o3 = new Collectible(6f, 2f, "Oxygen30");
		Collectible o4 = new Collectible(6f, -3f, "Oxygen10");
		o1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o2.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o3.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o4.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);

		// Room 3 Tile 0
		WarpTile s6 = new WarpTile(2, 7f, 0f, -7.8f, 0, true);
		WarpTile s7 = new WarpTile(5, 0, -3f, 0, 4.2f, true);
		WarpTile s8 = new WarpTile(4, -7f, 0, 7.7f, 0f, true);
		s8.challengeEntrance = true;

		// Room 4 Tile 0
		WarpTile s9 = new WarpTile(3, 7f, 0f, -7.8f, 0, false);
		WarpTile s11 = new WarpTile(6, 0, -3f, 0, 4.2f, false);
		Collectible g1 = new Collectible(6f, -3f, "GoldCoin");
		Collectible g2 = new Collectible(0f, -2f, "GoldBar");
		Collectible g3 = new Collectible(-7f, 3f, "GoldCoin");
		Collectible g4 = new Collectible(7f, 3.5f, "Diamond");
		Collectible g5 = new Collectible(-7f, -3.5f, "GoldNugget");
		Collectible g6 = new Collectible(4f, 0f, "GoldBar");
		Collectible o8 = new Collectible(-5.5f, 3f, "Oxygen20");
		Collectible h2 = new Collectible(0f, -3f, "SmallPastie");
		Collectible h3 = new Collectible(-3.2f, -2f, "Chips");
		g1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g2.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g3.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g4.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g5.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g6.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o8.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		h2.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		h3.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);

		// Room 5 Tile 0
		WarpTile s10 = new WarpTile(3, 0, 3f, 0, -4.2f, true);
		
		
		//Room 6
		WarpTile s12 = new WarpTile(3, 0, 3f, 0, -4.2f, false);
		WarpTile s13 = new WarpTile(7, 0, -3f, 0, 4.2f, false);
				
		//Room 7
		WarpTile s14 = new WarpTile(3, 0, 3f, 0, -4.2f, true);
		Collectible c7 = new Collectible(2f, 2f, "GoldNugget");
		Collectible c8 = new Collectible(-2f, -2f, "GoldNugget");
		Collectible c9 = new Collectible(0f, 0f, "GoldNugget");
		Collectible c10 = new Collectible(-2f, 2f, "GoldNugget");
		Collectible c11 = new Collectible(2f, 0f, "GoldNugget");
		Collectible c12 = new Collectible(0f, -2f, "GoldNugget");
		Collectible c13 = new Collectible(0f, 2f, "GoldNugget");
		Collectible c14 = new Collectible(3f, 3f, "GoldNugget");
		Collectible c15 = new Collectible(1f, 3.5f, "GoldBar");
		Collectible c16 = new Collectible(5f, -1f, "GoldBar");
		Collectible c17 = new Collectible(2f, 3.5f, "GoldBar");
		Collectible c18 = new Collectible(0f, -3.5f, "Diamond");
		Collectible c19 = new Collectible(5f, 0f, "Diamond");
		Collectible c20 = new Collectible(4f, 3.5f, "Diamond");
		Collectible c21 = new Collectible(3f, 1f, "Diamond");
		Collectible c22 = new Collectible(-4f, -3.5f, "Diamond");
		Collectible h1 = new Collectible(-6f, 0f, "LargePastie");
		Collectible o5 = new Collectible(-6, 2.5f, "Oxygen50");
		Collectible o6 = new Collectible(7f, 3.5f, "Oxygen100");
		Collectible o7 = new Collectible(5f, -2.5f, "Oxygen30");
		c7.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c8.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c9.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c10.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c11.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c12.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c13.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c14.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c15.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c16.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c17.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c18.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c19.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c20.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c21.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c22.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		h1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o5.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o6.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o7.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);

		roomData[0].addWarpTile(s0);
		roomData[0].addWarpTile(s1);

		roomData[1].addWarpTile(s2);
		roomData[1].addWarpTile(s3);
		roomData[1].addCollectible(c1);
		roomData[1].addCollectible(c2);
		roomData[1].addCollectible(c3);
		roomData[1].addCollectible(c4);
		roomData[1].addCollectible(c5);
		roomData[1].addCollectible(c6);
		roomData[1].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);

		roomData[2].addWarpTile(s4);
		roomData[2].addWarpTile(s5);
		roomData[2].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[2].addCollectible(o1);
		roomData[2].addCollectible(o2);
		roomData[2].addCollectible(o3);
		roomData[2].addCollectible(o4);

		roomData[3].addWarpTile(s6);
		roomData[3].addWarpTile(s7);
		roomData[3].addWarpTile(s8);
		roomData[3].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);

		roomData[4].addWarpTile(s9);
		roomData[4].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[4].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[4].addWarpTile(s11);
		roomData[4].addCollectible(g1);
		roomData[4].addCollectible(g2);
		roomData[4].addCollectible(g3);
		roomData[4].addCollectible(g4);
		roomData[4].addCollectible(g5);
		roomData[4].addCollectible(g6);
		roomData[4].addCollectible(o8);
		roomData[4].addCollectible(h2);
		roomData[4].addCollectible(h3);

		roomData[5].addWarpTile(s10);
		roomData[5].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[5].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		roomData[6].addWarpTile(s12);
		roomData[6].addWarpTile(s13);
		roomData[6].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		roomData[7].addWarpTile(s14);
		roomData[7].addCollectible(c7);
		roomData[7].addCollectible(c8);
		roomData[7].addCollectible(c9);
		roomData[7].addCollectible(c10);
		roomData[7].addCollectible(c11);
		roomData[7].addCollectible(c12);
		roomData[7].addCollectible(c13);
		roomData[7].addCollectible(c14);
		roomData[7].addCollectible(c15);
		roomData[7].addCollectible(c16);
		roomData[7].addCollectible(c17);
		roomData[7].addCollectible(c18);
		roomData[7].addCollectible(c19);
		roomData[7].addCollectible(c20);
		roomData[7].addCollectible(c21);
		roomData[7].addCollectible(c22);
		roomData[7].addCollectible(h1);
		roomData[7].addCollectible(o5);
		roomData[7].addCollectible(o6);
		roomData[7].addCollectible(o7);
	}

	public void update(float delta, MarioSprite mario, int cRoom) {
		background.updateObjects(delta);
	}	

	public void updateOnObjects(float delta, MarioSprite mario, int cRoom) {
		roomData[cRoom].updateRoomData(delta);

		for (int x = 0; x < roomData[cRoom].items.size(); x++) {
			if (mario.rRI(roomData[cRoom].items.get(x).mainBox)) {
				switch (roomData[cRoom].items.get(x).getType()) {
				case "LargePastie":
					mario.healthBar.addHealth(roomData[cRoom].items.get(x).getIncrease());
					break;
				case "SmallPastie":
					mario.healthBar.addHealth(roomData[cRoom].items.get(x).getIncrease());
					break;
				case "Chips":
					mario.healthBar.addHealth(roomData[cRoom].items.get(x).getIncrease());
					break;
				case "Oxygen100":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					//oxygenCollect.play();
					break;
				case "Oxygen80":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					//oxygenCollect.play();
					break;
				case "Oxygen50":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					//oxygenCollect.play();
					break;
				case "Oxygen40":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					//oxygenCollect.play();
					break;
				case "Oxygen30":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					//oxygenCollect.play();
					break;
				case "Oxygen10":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					//oxygenCollect.play();
					break;
				case "GoldCoin":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					//goldCollect.play();
					break;
				case "GoldNugget":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					//goldCollect.play();
					break;
				case "GoldBar":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					//goldCollect.play();
					break;
				case "Diamond":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					//goldCollect.play();
					break;
				case "Arkenstone":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					//goldCollect.play();
					break;
				default:
					System.out.print("Unknown Thing");
					break;
				}
				roomData[cRoom].items.get(x).collectSFX.play();
				roomData[cRoom].items.remove(x);
			}
		}
	}
	
	public boolean lock(int cRoom) {
		if (roomData[cRoom].showDB)
			return true;
		else
			return false;
	}
}
