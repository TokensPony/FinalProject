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
				new LavaRoom("Images/Room-1.png", "LavaRoom")/*Room 5*/, new QTERoom("Images/Room-0.png", "QTE")/*Room 6 QTE*/, new RoomData("Images/Room-1.png")/*Room 7 Challenge Gold Room*/, 
				new LavaRoom("Images/Room-0.png")/*Room 8*/, new RoomData("Images/Room-1.png")/*Room 9*/, new RoomData("Images/Room-0.png")/*Roomn10*/, new QTERoom("Images/Room-0.png", "QTE")/*Roomn11*/, 
				new RockChallengeRoom("Images/Room-0.png", "FallingRocks")/*Roomn12*/, new RoomData("Images/Room-0.png")/*Roomn13*/, new RockChallengeRoom("Images/Room-0.png", "FallingRocks")/*Room 14*/};
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
		WarpTile s7 = new WarpTile(10, 0, -3f, 0, 4.2f, true);
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
		Collectible h2 = new Collectible(0f, -3f, "SmallPasty");
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
		WarpTile s10 = new WarpTile(10, 0, 3f, 0, -4.2f, true);
		WarpTile s15 = new WarpTile(14, 0, -3f, 0, 4.2f, true);
		
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
		Collectible h1 = new Collectible(-6f, 0f, "LargePasty");
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
		
		//Room 8
		WarpTile s16 = new WarpTile(14, 0, 4f, 0, -4.2f, true);
		WarpTile s17 = new WarpTile(9, 0, -3f, 0, 4.2f, true);
		
		//Room 9
		WarpTile s18 = new WarpTile(8, 0, 4f, 0, -4.2f, true);
		Collectible a1 = new Collectible(0f, 3f, "Arkenstone");
		a1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		//Room 10
		WarpTile s19 = new WarpTile(3, 0, 4f, 0, -4.2f, true);
		WarpTile s20 = new WarpTile(11, 7f, 0f, -7.8f, 0, true);
		WarpTile s21 = new WarpTile(5, 0, -3f, 0, 4.2f, true);
		Collectible o9 = new Collectible(-6, 2.5f, "Oxygen50");
		Collectible o10 = new Collectible(7f, 3.5f, "Oxygen100");
		Collectible o11 = new Collectible(5f, -2.5f, "Oxygen30");
		Collectible h4 = new Collectible(0f, -3f, "SmallPasty");
		Collectible h5 = new Collectible(-3.2f, -2f, "Chips");
		o9.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o10.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o11.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		h4.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		h5.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		s20.challengeEntrance = true;
		
		//Room 11
		WarpTile s22 = new WarpTile(10, -7f, 0, 7.7f, 0f, false);
		WarpTile s23 = new WarpTile(12, 0, -3f, 0, 4.2f, false);
		
		//Room 12
		WarpTile s24 = new WarpTile(11, 0, 4f, 0, -4.2f, false);
		WarpTile s25 = new WarpTile(13, 0, -3f, 0, 4.2f, false);
		Collectible g7 = new Collectible(6f, -3f, "GoldCoin");
		Collectible g8 = new Collectible(0f, -2f, "GoldBar");
		Collectible g9 = new Collectible(-7f, 3f, "GoldCoin");
		Collectible g10 = new Collectible(7f, 3.5f, "Diamond");
		Collectible g11 = new Collectible(-7f, -3.5f, "GoldNugget");
		Collectible g12 = new Collectible(4f, 0f, "GoldBar");
		Collectible o12 = new Collectible(-5.5f, 3f, "Oxygen20");
		Collectible h6 = new Collectible(0f, -3f, "SmallPasty");
		Collectible h7 = new Collectible(-3.2f, -2f, "Chips");
		g7.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g8.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g9.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g10.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g11.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		g12.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o12.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		h6.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		h7.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		//Room 13
		WarpTile s26 = new WarpTile(10, 0, 4f, 0, -4.2f, true);
		Collectible c23 = new Collectible(2f, 2f, "GoldNugget");
		Collectible c24 = new Collectible(-2f, -2f, "GoldNugget");
		Collectible c25 = new Collectible(0f, 0f, "GoldNugget");
		Collectible c26 = new Collectible(-2f, 2f, "GoldNugget");
		Collectible c27 = new Collectible(2f, 0f, "GoldNugget");
		Collectible c28 = new Collectible(0f, -2f, "GoldNugget");
		Collectible c29 = new Collectible(0f, 2f, "GoldNugget");
		Collectible c30 = new Collectible(3f, 3f, "GoldNugget");
		Collectible c31 = new Collectible(1f, 3.5f, "GoldBar");
		Collectible c32 = new Collectible(5f, -1f, "GoldBar");
		Collectible c33 = new Collectible(2f, 3.5f, "GoldBar");
		Collectible c34 = new Collectible(0f, -3.5f, "Diamond");
		Collectible c35 = new Collectible(5f, 0f, "Diamond");
		Collectible c36 = new Collectible(4f, 3.5f, "Diamond");
		Collectible c37 = new Collectible(3f, 1f, "Diamond");
		Collectible c38 = new Collectible(-4f, -3.5f, "Diamond");
		Collectible h8 = new Collectible(-6f, 0f, "LargePasty");
		Collectible o13 = new Collectible(-6, 2.5f, "Oxygen50");
		Collectible o14 = new Collectible(7f, 3.5f, "Oxygen100");
		Collectible o15 = new Collectible(5f, -2.5f, "Oxygen30");
		c23.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c24.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c25.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c26.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c27.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c28.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c29.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c30.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c31.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c32.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c33.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c34.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c35.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c36.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c37.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c38.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		h8.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o13.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o14.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		o15.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		//Room 14
		WarpTile s27 = new WarpTile(5, 0, 4f, 0, -4.2f, true);
		WarpTile s28 = new WarpTile(8, 0, -3f, 0, 4.2f, true);

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
		roomData[5].addWarpTile(s15);
		
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
		roomData[7].addCollectible(o13);
		roomData[7].addCollectible(o14);
		roomData[7].addCollectible(o15);
		
		roomData[8].addWarpTile(s16);
		roomData[8].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[8].addWarpTile(s17);
		
		roomData[9].addWarpTile(s18);
		roomData[9].addCollectible(a1);
		
		roomData[10].addWarpTile(s19);
		roomData[10].addWarpTile(s20);
		roomData[10].addWarpTile(s21);
		roomData[10].addCollectible(o9);
		roomData[10].addCollectible(o10);
		roomData[10].addCollectible(o11);
		roomData[10].addCollectible(h4);
		roomData[10].addCollectible(h5);
		
		roomData[11].addWarpTile(s22);
		roomData[11].addWarpTile(s23);
		roomData[11].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[11].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		roomData[12].addWarpTile(s24);
		roomData[12].addWarpTile(s25);
		roomData[12].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[12].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[12].addCollectible(g7);
		roomData[12].addCollectible(g8);
		roomData[12].addCollectible(g9);
		roomData[12].addCollectible(g10);
		roomData[12].addCollectible(g11);
		roomData[12].addCollectible(g12);
		roomData[12].addCollectible(o12);
		roomData[12].addCollectible(h6);
		roomData[12].addCollectible(h7);
		
		roomData[13].addWarpTile(s26);
		roomData[13].addCollectible(c23);
		roomData[13].addCollectible(c24);
		roomData[13].addCollectible(c25);
		roomData[13].addCollectible(c26);
		roomData[13].addCollectible(c27);
		roomData[13].addCollectible(c28);
		roomData[13].addCollectible(c29);
		roomData[13].addCollectible(c30);
		roomData[13].addCollectible(c31);
		roomData[13].addCollectible(c32);
		roomData[13].addCollectible(c33);
		roomData[13].addCollectible(c34);
		roomData[13].addCollectible(c35);
		roomData[13].addCollectible(c36);
		roomData[13].addCollectible(c37);
		roomData[13].addCollectible(c38);
		roomData[13].addCollectible(h8);
		roomData[13].addCollectible(o5);
		roomData[13].addCollectible(o6);
		roomData[13].addCollectible(o7);
		
		roomData[14].addWarpTile(s27);
		roomData[14].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[14].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[14].addWarpTile(s28);
		
	}

	public void update(float delta, MarioSprite mario, int cRoom) {
		background.updateObjects(delta);
	}	

	public void updateOnObjects(float delta, MarioSprite mario, int cRoom) {
		roomData[cRoom].updateRoomData(delta);

		for (int x = 0; x < roomData[cRoom].items.size(); x++) {
			if (mario.rRI(roomData[cRoom].items.get(x).mainBox)) {
				switch (roomData[cRoom].items.get(x).getType()) {
				case "LargePasty":
					mario.healthBar.addHealth(roomData[cRoom].items.get(x).getIncrease());
					break;
				case "SmallPasty":
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
