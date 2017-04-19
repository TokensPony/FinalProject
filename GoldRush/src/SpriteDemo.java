import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import javagames.util.WindowFramework;

public class SpriteDemo extends WindowFramework {
	// >>>>>>> branch 'master' of https://github.com/TokensPony/FinalProject.git

	Random r = new Random();

	MarioSprite mario;
	Background background;
	HealthBar healthBar;
	Score score;
	// WarpTile s1;
	/// arpTile s2;
	private Map map;

	boolean gameOver = false;
	boolean controlLock = false;

	int cRoom = 0;

	//RoomData[] roomData;

	public SpriteDemo() {
		appBackground = Color.BLACK;
		appBorder = Color.LIGHT_GRAY;
		appFont = new Font("Consolas", Font.PLAIN, 14);
		appBorderScale = .9f;
		appFPSColor = Color.BLACK;
		appWidth = 1280;
		appHeight = 720;
		appMaintainRatio = false;
		appSleep = 10L;
		appTitle = "Gold Rush v1.0";
		appWorldWidth = 16.0f;
		appWorldHeight = 9.0f;

		// temporal fix. Arturo.
		setResizable(false);
	}

	/*
	 * Initializes the various sprites w/ their coordinates and sets their
	 * bounding boxes.
	 */
	@Override
	protected void initialize() {
		super.initialize();

		// Initialization of the Map, rooms, collectibles, warptiles, etc...
		map = new Map();
		map.initialize(appWidth, appHeight, appWorldWidth, appWorldHeight);

		// sprites.add(new Floor(-1.0f, -4.4f));
		score = new Score();
		healthBar = new HealthBar();

		mario = new MarioSprite();
		mario.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		mario.setSubBox();

		/*
		 * //Room 0 Tile 0 WarpTile s1 = new WarpTile(1, 0, -3f, 0, 4.2f, true);
		 * Collectible c1 = new Collectible(7f, 3f, "Gold", 100);
		 * c1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		 * 
		 * //Room 1 Tile 0 WarpTile s2 = new WarpTile(0, 0, 3f, 0, -4.2f, true);
		 * WarpTile s3 = new WarpTile(2, 7f, 0f, -7.8f, 0, true); Collectible c2
		 * = new Collectible(0f, 0f, "Oxygen", 50); Collectible c3 = new
		 * Collectible(-5f, 3f, "Gold", 100); c2.setBB(appWidth, appHeight,
		 * appWorldWidth, appWorldHeight); c3.setBB(appWidth, appHeight,
		 * appWorldWidth, appWorldHeight);
		 * 
		 * //Room 3 Tile 0 WarpTile s4 = new WarpTile(1, -7f, 0, 7.7f, 0f,
		 * false);
		 * 
		 * roomData = new RoomData[]{new RoomData("Images/Room-0.png"), new
		 * RoomData("Images/Room-1.png"), new RoomData("Images/Room-0.png",
		 * "QTE")}; roomData[0].addWarpTile(s1); roomData[0].addCollectible(c1);
		 * 
		 * roomData[1].addWarpTile(s2); roomData[1].addWarpTile(s3);
		 * roomData[1].addCollectible(c2); roomData[1].addCollectible(c3);
		 * 
		 * roomData[2].addWarpTile(s4); roomData[2].db.setBB(appWidth,
		 * appHeight, appWorldWidth, appWorldHeight); if(roomData[2].db ==
		 * null){ System.out.println("NULL"); }
		 */

		healthBar = new HealthBar();
	}

	/* Processes the keyboard input for the various game controls */
	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
		if (!controlLock) {
			if (keyboard.keyDown(KeyEvent.VK_W) || keyboard.keyDown(KeyEvent.VK_UP)) {
				mario.setVY(2f);
			} else if (keyboard.keyDown(KeyEvent.VK_S) || keyboard.keyDown(KeyEvent.VK_DOWN)) {
				mario.setVY(-2f);
			} else {
				mario.setVY(0);
			}

			if (keyboard.keyDown(KeyEvent.VK_A) || keyboard.keyDown(KeyEvent.VK_LEFT)) {
				mario.setVX(-2f);
			} else if (keyboard.keyDown(KeyEvent.VK_D) || keyboard.keyDown(KeyEvent.VK_RIGHT)) {
				mario.setVX(2f);
			} else {
				mario.setVX(0);
			}
		} else {
			mario.setVX(0);
			mario.setVY(0);
		}

		if (keyboard.keyDownOnce(KeyEvent.VK_E)) {
			map.roomData[cRoom].showDB = false;
			controlLock = false;
		}

		if (keyboard.keyDownOnce(KeyEvent.VK_B)) {
			mario.greenBorder = !mario.greenBorder;
			map.background.greenBorder = !map.background.greenBorder;
		}

		/*
		 * The following controls are for debugging and testing ONLY! These MUST
		 * be removed for final release
		 */

		/* Health bar */
		if (keyboard.keyDownOnce(KeyEvent.VK_1)) {
			healthBar.doDamage(10);
		}

		if (keyboard.keyDownOnce(KeyEvent.VK_2)) {
			healthBar.addHealth(50);
		}

		// Oxygen bar
		if (keyboard.keyDownOnce(KeyEvent.VK_3)) {
			healthBar.drainOxygen(10);
		}

		if (keyboard.keyDownOnce(KeyEvent.VK_4)) {
			healthBar.addOxygen(50);
		}

		/* Activates all warp tiles */
		if (keyboard.keyDownOnce(KeyEvent.VK_5)) {
			for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
				map.roomData[cRoom].wt.get(x).activateTile();
				System.out.printf("Activated: %b\n", map.roomData[cRoom].wt.get(x).isActive());
			}
		}

		/* Deactivates all warp tiles */
		if (keyboard.keyDownOnce(KeyEvent.VK_6)) {
			for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
				map.roomData[cRoom].wt.get(x).deactivateTile();
				System.out.printf("Activated: %b\n", map.roomData[cRoom].wt.get(x).isActive());
			}
		}

	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		// System.out.printf("%f, %f\n", mario.positions.x, mario.positions.y);
		// System.out.println(getWorldMousePosition());
		map.background.updateObjects(delta);
		mario.updateObjects(delta);
		// s1.updateObjects(delta);

		if (mario.rectRectIntersection(map.background.subBox.get(0).getVWorld(), mario.mainBox.getVWorld())) {
			mario.positions.y = -3.3f;
		} else if (mario.rectRectIntersection(map.background.subBox.get(1).getVWorld(), mario.mainBox.getVWorld())) {
			mario.positions.y = 3.3f;
		} else if (mario.rectRectIntersection(map.background.subBox.get(2).getVWorld(), mario.mainBox.getVWorld())) {
			mario.positions.x = -7.2f;
		} else if (mario.rectRectIntersection(map.background.subBox.get(3).getVWorld(), mario.mainBox.getVWorld())) {
			// b.setSprite("Thing");
			// cRoom = map.roomData[cRoom].wt.get(0).getWarpCoord();
			mario.positions.x = 7.2f;
		}

		if (map.roomData[cRoom].showDB) {
			controlLock = true;
		}
		map.roomData[cRoom].updateRoomData(delta);
		// This is a test
		for (int x = 0; x < map.roomData[cRoom].items.size(); x++) {
			// map.roomData[cRoom].items.get(x).updateObjects(delta);
			if (mario.rRI(map.roomData[cRoom].items.get(x).mainBox)) {
				switch (map.roomData[cRoom].items.get(x).getType()) {
				case "Oxygen":
					healthBar.addOxygen(map.roomData[cRoom].items.get(x).getIncrease());
					break;
				case "Gold":
					score.increaseScore(map.roomData[cRoom].items.get(x).getIncrease());
					break;
				default:
					System.out.print("Unknown Thing");
					break;
				// x--;
				}
				map.roomData[cRoom].items.remove(x);
			}
		}


		// map.roomData[cRoom].updateRoomData(delta);
		for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
			// map.roomData[cRoom].wt.get(x).updateObjects(delta);
			if (mario.rRI(map.roomData[cRoom].wt.get(x).tile) && map.roomData[cRoom].wt.get(x).isActive()) {
				// System.out.println("Warped");
				mario.positions.x = map.roomData[cRoom].wt.get(x).getWarpToX();
				mario.positions.y = map.roomData[cRoom].wt.get(x).getWarpToY();
				cRoom = map.roomData[cRoom].wt.get(x).getWarpMap();
				// System.out.println(cRoom);
				// b.pos = cRoom;
				// b.setSprite("thing");
				// b.changeSprite(map.roomData[cRoom].getBG());
				map.background.currentSprite = map.roomData[cRoom].getBG();
				// map.roomData[cRoom].wt.get(x).updateObjects(delta);
				map.roomData[cRoom].updateRoomData(delta);
				// >>>>>>> branch 'master' of
				// https://github.com/TokensPony/FinalProject.git
				break;
			}
		}

		if (!controlLock) {
			healthBar.update(delta);
			// System.out.println(healthBar.healthLevel);
			if (healthBar.healthLevel <= 0) {
				gameOver = true;
			}
		}
		healthBar.update(delta);

	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		if (!gameOver) {
			map.background.render(g, getViewportTransform());
			mario.render(g, getViewportTransform());
			// s1.render(g, getViewportTransform());
			for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
				map.roomData[cRoom].wt.get(x).render(g, getViewportTransform());
			}
			for (int x = 0; x < map.roomData[cRoom].items.size(); x++) {
				map.roomData[cRoom].items.get(x).render(g, getViewportTransform());
			}
			healthBar.render(g, getViewportTransform());
			score.render(g);
			if (map.roomData[cRoom].showDB) {
				map.roomData[cRoom].db.render(g, getViewportTransform());
			}
		} else {
			g.setColor(Color.RED);
			g.drawString("GAME OVER", 640, 360);
		}
	}

	@Override
	protected void terminate() {
		super.terminate();
	}

	public static void main(String[] args) {
		launchApp(new SpriteDemo());
	}
}