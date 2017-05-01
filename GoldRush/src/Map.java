import java.util.Random;

import javagames.util.*;

public class Map {

	public Background background;
	public WarpTile warpTile;
	public Collectible collectible;
	
	private int appWidth, appHeight;
	private float appWorldWidth, appWorldHeight;

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
	

	public void addItems(int room, int numItems, String[] choices)
	{
		Random r = new Random();
		
		for (int i = 0; i < numItems; i++)
		{
			float posx = r.nextFloat() * 14 - 7;
			float posy = r.nextFloat() * 8 - 4;
			int choice = r.nextInt(choices.length);
			Collectible c = new Collectible(posx, posy, choices[choice]);
			c.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
			roomData[room].addCollectible(c);
		}
	}
	
	//overloaded to handle a single string
	public void addItems(int room, int numItems, String item)
	{
		String[] choices = {item};
		addItems(room, numItems, choices);
	}
	
	public void setAppVals(int aW, int aH, float aWW, float aWH)
	{
		this.appWidth = aW;
		this.appHeight = aH;
		this.appWorldHeight = aWH;
		this.appWorldWidth = aWW;
	}
	
	//temporary fix
	public void initialize(int appWidth, int appHeight, float appWorldWidth, float appWorldHeight) 
	{
		setAppVals(appWidth, appHeight, appWorldWidth, appWorldHeight);
		initialize();
		
	}
	
	public void initialize() 
	{
		String[] allOxygen = {"Oxygen10", "Oxygen30", "Oxygen50", "Oxygen80", "Oxygen100"};
		String[] allEdibles = {"SmallPasty", "LargePasty", "Chips"};
		String[] allGold = {"GoldCoin", "GoldNugget", "GoldBar"};
				

		background.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		background.setSubBox();
		background.setSprite("thing");
		

		/*Room 0*/
		roomData[0].addWarpTile(new WarpTile(10, 7f, 0f, -7.8f, 0, false));//this is for the exit
		roomData[0].addWarpTile(new WarpTile(1, 0, -3f, 0, 4.2f, true));


		/* Room 1 (Gold Demo Room)*/
		roomData[1].addWarpTile(new WarpTile(0, 0, 3f, 0, -4.2f, true));
		roomData[1].addWarpTile(new WarpTile(2, -7f, 0, 7.7f, 0f, true));
		roomData[1].db.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		addItems(1, 50, "GoldCoin");//put 50 GoldCoins in room 1 randomly
		addItems(1, 3, "GoldNugget");//put 3 GoldNuggets in room 1
		addItems(1, 2, "GoldBar");//add 2 GoldBars
		addItems(1, 1, "Diamond");//add 1 Diamond
		
		
		/* Room 2 Oxygen Demo */
		roomData[2].addWarpTile(new WarpTile(1, 7f, 0f, -7.8f, 0, true));
		roomData[2].addWarpTile(new WarpTile(3, -7f, 0, 7.7f, 0f, true));
		roomData[2].db.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		addItems(2, 3, allOxygen); //add three items from oxygen[] randomly

		
		/* Room 3 Branching Paths*/
		roomData[3].addWarpTile(new WarpTile(2, 7f, 0f, -7.8f, 0, true));
		roomData[3].addWarpTile(new WarpTile(10, 0, -3f, 0, 4.2f, true));
		WarpTile s8 = new WarpTile(4, -7f, 0, 7.7f, 0f, true);
		s8.challengeEntrance = true;
		roomData[3].addWarpTile(s8);
		roomData[3].db.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);


		/* Room 4 Falling Rock Demo*/
		roomData[4].addWarpTile(new WarpTile(3, 7f, 0f, -7.8f, 0, false));
		roomData[4].addWarpTile(new WarpTile(6, 0, -3f, 0, 4.2f, false));
		roomData[4].db.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[4].db.add(new DialogBox("Success"));
		roomData[4].db.get(1).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[4].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);	
		addItems(4, 1, "Oxygen20");
		addItems(4, 2, allEdibles);
		addItems(4, 5, allGold);
		addItems(4, 1, "Diamond");
		

		/* Room 5 First Lava Room*/
		roomData[5].addWarpTile(new WarpTile(10, 0, 3f, 0, -4.2f, true));
		roomData[5].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[5].db.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[5].addWarpTile(new WarpTile(14, 0, -3f, 0, 4.2f, true));
		
		
		/* Room 6 QTE Demo*/
		roomData[6].addWarpTile(new WarpTile(3, 0, 3f, 0, -4.2f, false));
		roomData[6].addWarpTile(new WarpTile(7, 0, -3f, 0, 4.2f, false));
		roomData[6].db.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);


		/* Room 7 Pot of Gold*/
		roomData[7].addWarpTile(new WarpTile(3, 0, 3f, 0, -4.2f, true));
		addItems(7, 8, "GoldNugget");
		addItems(7, 3, "GoldBar");
		addItems(7, 5, "Diamond");
		addItems(7, 1, "LargePasty");
		addItems(7, 3, allOxygen);

		
		//TODO: (Brogan) I need to finish going through the rooms below...
		
		//Room 8
		WarpTile s16 = new WarpTile(14, 0, 4f, 0, -4.2f, true);
		WarpTile s17 = new WarpTile(9, 0, -3f, 0, 4.2f, true);
		roomData[8].addWarpTile(s16);
		roomData[8].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[8].addWarpTile(s17);
		
		//Room 9
		WarpTile s18 = new WarpTile(8, 0, 4f, 0, -4.2f, true);
		Collectible a1 = new Collectible(0f, 3f, "Arkenstone");
		a1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[9].addWarpTile(s18);
		roomData[9].addCollectible(a1);
		
		//Room 10
		WarpTile s19 = new WarpTile(3, 0, 4f, 0, -4.2f, true);
		WarpTile s20 = new WarpTile(11, 7f, 0f, -7.8f, 0, true);
		WarpTile s21 = new WarpTile(5, 0, -3f, 0, 4.2f, true);
		addItems(10, 3, allOxygen);
		addItems(10, 2, allEdibles);
		s20.challengeEntrance = true;
		
		//Room 11
		WarpTile s22 = new WarpTile(10, -7f, 0, 7.7f, 0f, false);
		WarpTile s23 = new WarpTile(12, 0, -3f, 0, 4.2f, false);
		
		//Room 12
		WarpTile s24 = new WarpTile(11, 0, 4f, 0, -4.2f, false);
		WarpTile s25 = new WarpTile(13, 0, -3f, 0, 4.2f, false);
		addItems(12, 1, "Oxygen20");
		addItems(12, 2, allEdibles);
		addItems(12, 5, allGold);
		addItems(12, 1, "Diamond");
		
		//Room 13
		WarpTile s26 = new WarpTile(10, 0, 4f, 0, -4.2f, true);
		addItems(13, 8, "GoldNugget");
		addItems(13, 3, "GoldBar");
		addItems(13, 5, "Diamond");
		addItems(13, 1, "LargePasty");
		addItems(13, 3, allOxygen);
		
		//Room 14
		WarpTile s27 = new WarpTile(5, 0, 4f, 0, -4.2f, true);
		WarpTile s28 = new WarpTile(8, 0, -3f, 0, 4.2f, true);

		
		roomData[10].addWarpTile(s19);
		roomData[10].addWarpTile(s20);
		roomData[10].addWarpTile(s21);
		
		roomData[11].addWarpTile(s22);
		roomData[11].addWarpTile(s23);
		roomData[11].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[11].db.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		roomData[12].addWarpTile(s24);
		roomData[12].addWarpTile(s25);
		roomData[12].db.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[12].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		
		roomData[14].addWarpTile(s27);
		roomData[14].setStuff(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[14].db.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
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
