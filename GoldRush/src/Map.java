public class Map {

	public Background background;
	public WarpTile warpTile;
	public Collectible collectible;

	public RoomData[] roomData;

	public Map() {
		background = new Background(0f, 0f);
		roomData = new RoomData[] { new RoomData("Images/Room-0.png"), new RoomData("Images/Room-1.png"),
				new RoomData("Images/Room-0.png", "QTE") };
	}

	public void initialize(int appWidth, int appHeight, float appWorldWidth, float appWorldHeight) {

		background.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		background.setSubBox();
		background.setSprite("thing");

		// Room 0 Tile 0
		WarpTile s0 = new WarpTile(10, 7f, 0f, -7.8f, 0, false);//this is for the exit
		WarpTile s1 = new WarpTile(1, 0, -3f, 0, 4.2f, true);
		Collectible c1 = new Collectible(7f, 3f, "Gold", 100);
		c1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		roomData[0].addWarpTile(s1);
		roomData[0].addCollectible(c1);

		// Room 1 Tile 0
		WarpTile s2 = new WarpTile(0, 0, 3f, 0, -4.2f, true);
		WarpTile s3 = new WarpTile(2, -7f, 0, 7.7f, 0f, true);
		Collectible c2 = new Collectible(0f, 0f, "Oxygen", 50);
		Collectible c3 = new Collectible(-5f, 3f, "Gold", 100);
		c2.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c3.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);

		// Room 2 Tile 0
		WarpTile s4 = new WarpTile(1, 7f, 0f, -7.8f, 0, true);
		WarpTile s5 = new WarpTile(3, -7f, 0, 7.7f, 0f, true);
		
		// Room 3 Tile 0
		WarpTile s6 = new WarpTile(2, 7f, 0f, -7.8f, 0, true);
		WarpTile s7 = new WarpTile(5, 0, -3f, 0, 4.2f, true);
		WarpTile s8 = new WarpTile(4, -7f, 0, 7.7f, 0f, true);
		
		//Room 4 Tile 0
		WarpTile s9 = new WarpTile(3, 7f, 0f, -7.8f, 0, true);
		
		//Room 5 Tile 0
		WarpTile s10 = new WarpTile(3, 0, 3f, 0, -4.2f, true);
		
		roomData = new RoomData[] { new RoomData("Images/Room-0.png"), new RoomData("Images/Room-1.png"),
				new RoomData("Images/Room-0.png", "QTE"), new RoomData("Images/Room-1.png"), new RoomData("Images/Room-0.png"), 
				new RoomData("Images/Room-1.png")};
		roomData[0].addWarpTile(s0);
		roomData[0].addWarpTile(s1);
		roomData[0].addCollectible(c1);

		roomData[1].addWarpTile(s2);
		roomData[1].addWarpTile(s3);
		roomData[1].addCollectible(c2);
		roomData[1].addCollectible(c3);

		roomData[2].addWarpTile(s4);
		roomData[2].addWarpTile(s5);
		roomData[2].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		roomData[3].addWarpTile(s6);
		roomData[3].addWarpTile(s7);
		roomData[3].addWarpTile(s8);
		
		roomData[4].addWarpTile(s9);
		
		roomData[5].addWarpTile(s10);

		// Extra check?
		if (roomData[2].db == null) {
			System.out.println("NULL");
		}

	}
}
