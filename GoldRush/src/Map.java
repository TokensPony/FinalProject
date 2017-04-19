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
		WarpTile s1 = new WarpTile(1, 0, -3f, 0, 4.2f);
		Collectible c1 = new Collectible(7f, 3f, "Gold", 100);
		c1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		roomData[0].addWarpTile(s1);
		roomData[0].addCollectible(c1);

		// Room 1 Tile 0
		WarpTile s2 = new WarpTile(0, 0, 3f, 0, -4.2f);
		WarpTile s3 = new WarpTile(2, 7f, 0f, -7.8f, 0);
		Collectible c2 = new Collectible(0f, 0f, "Oxygen", 50);
		Collectible c3 = new Collectible(-5f, 3f, "Gold", 100);
		c2.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c3.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);

//<<<<<<< HEAD
		// Room 2 Tile 0
		WarpTile s4 = new WarpTile(1, -7f, 0, 7.7f, 0f);
		WarpTile s5 = new WarpTile(3, 7f, 0f, -7.8f, 0);
		
		// Room 3 Tile 0
		WarpTile s6 = new WarpTile(2, -7f, 0, 7.7f, 0f);
		
		roomData = new RoomData[] { new RoomData("Images/Room-0.png"), new RoomData("Images/Room-1.png"),
				new RoomData("Images/Room-0.png", "QTE"), new RoomData("Images/Room-1.png")};
		roomData[0].addWarpTile(s1);
		roomData[0].addCollectible(c1);

//=======
//>>>>>>> branch 'master' of https://github.com/TokensPony/FinalProject.git
		roomData[1].addWarpTile(s2);
		roomData[1].addWarpTile(s3);
		roomData[1].addCollectible(c2);
		roomData[1].addCollectible(c3);

		roomData[2].addWarpTile(s4);
		roomData[2].addWarpTile(s5);
		roomData[2].db.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		roomData[3].addWarpTile(s6);

		// Extra check?
		if (roomData[2].db == null) {
			System.out.println("NULL");
		}

	}
}
