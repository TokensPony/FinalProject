import javagames.util.*;

public class Map {

	public Background background;
	public WarpTile warpTile;
	public Collectible collectible;

	public RoomData[] roomData;
	
	public AePlayWave goldCollect = new AePlayWave("Sounds/coin-drop-4.wav");
	public AePlayWave oxygenCollect = new AePlayWave("Sounds/Oxygen Tank Pickup.wav");
	
	enum type {
		Normal, Rock
	};

	public Map() {
		background = new Background(0f, 0f);
		roomData = new RoomData[] { new RoomData("Images/Room-0.png")/*Room 0*/, new RoomData("Images/Room-1.png", "GoldIntroduction")/*Room 1*/,
				new RoomData("Images/Room-0.png", "OxygenIntroduction")/*Room 2*/, new RoomData("Images/Room-1.png", "Room3")/*Room 3*/, new RockChallengeRoom("Images/Room-0.png", "FallingRocks")/*Room 4*/, 
				new RoomData("Images/Room-1.png")/*Room 5*/, new RoomData("Images/Room-0.png", "QTE")/*Room 6 QTE*/, new RoomData("Images/Room-1.png")/*Room 7 Challenge Gold Room*/};
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

		// Room 4 Tile 0
		WarpTile s9 = new WarpTile(3, 7f, 0f, -7.8f, 0, false);
		WarpTile s11 = new WarpTile(6, 0, -3f, 0, 4.2f, true);

		// Room 5 Tile 0
		WarpTile s10 = new WarpTile(3, 0, 3f, 0, -4.2f, true);
		
		//Room 6
		WarpTile s12 = new WarpTile(4, 0, 3f, 0, -4.2f, false);
		WarpTile s13 = new WarpTile(7, 0, -3f, 0, 4.2f, false);
				
		//Room 7
		WarpTile s14 = new WarpTile(3, 0, 3f, 0, -4.2f, true);

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

		roomData[5].addWarpTile(s10);
		
		roomData[6].addWarpTile(s12);
		roomData[6].addWarpTile(s13);
		roomData[6].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		roomData[7].addWarpTile(s14);
	}

	public void update(float delta, MarioSprite mario, int cRoom) {
		background.updateObjects(delta);
	}	

	public void updateOnObjects(float delta, MarioSprite mario, int cRoom) {
		roomData[cRoom].updateRoomData(delta);

		for (int x = 0; x < roomData[cRoom].items.size(); x++) {
			if (mario.rRI(roomData[cRoom].items.get(x).mainBox)) {
				switch (roomData[cRoom].items.get(x).getType()) {
				case "Oxygen100":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					oxygenCollect.play();
					break;
				case "Oxygen80":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					oxygenCollect.play();
					break;
				case "Oxygen50":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					oxygenCollect.play();
					break;
				case "Oxygen40":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					oxygenCollect.play();
					break;
				case "Oxygen30":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					oxygenCollect.play();
					break;
				case "Oxygen10":
					mario.healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					oxygenCollect.play();
					break;
				case "GoldCoin":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					goldCollect.play();
					break;
				case "GoldNugget":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					goldCollect.play();
					break;
				case "GoldBar":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					goldCollect.play();
					break;
				case "Diamond":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					goldCollect.play();
					break;
				case "Arkenstone":
					mario.score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					goldCollect.play();
					break;
				default:
					System.out.print("Unknown Thing");
					break;
				}
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
