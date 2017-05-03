package goldrush.Maze;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import goldrush.Util.AePlayWave;
import goldrush.Util.Matrix3x3f;
import goldrush.Util.WindowFramework;

public class SpriteDemo extends WindowFramework {

	Random r = new Random();

	DimliSprite mario;
	Background background;
	private Map map;
	private Overlay overlay;

	private boolean gameOver;
	private boolean controlLock;
	private boolean gameWon;
	private int cRoom;

	public String finalScore = "Final Score: %d";
	public String gameOverText = "GAME OVER";

	AePlayWave bgSong;

	public SpriteDemo() {
		appBackground = Color.BLACK;
		appBorder = Color.LIGHT_GRAY;
		appFont = new Font("Consolas", Font.BOLD, 25);
		appBorderScale = .9f;
		appFPSColor = Color.BLACK;
		appWidth = 1280;
		appHeight = 720;
		appMaintainRatio = false;
		appSleep = 10L;
		appTitle = "Gold Rush v1.0";
		appWorldWidth = 16.0f;
		appWorldHeight = 9.0f;

		setResizable(false);
	}

	/*
	 * Initializes the various sprites w/ their coordinates and sets their
	 * bounding boxes.
	 */
	@Override
	protected void initialize() {
		super.initialize();

		cRoom = 0;
		gameOver = false;
		gameWon = false;
		controlLock = false;

		// Initialization of the Map, rooms, collectibles, warptiles, etc...
		map = new Map(appWidth, appHeight, appWorldWidth, appWorldHeight);

		mario = new DimliSprite();
		mario.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		mario.setSubBox();

		bgSong = new AePlayWave("Sounds/Spooky Graveyard Song.wav");
		bgSong.loop();

		overlay = new Overlay();
	}

	/* Processes the keyboard input for the various game controls */
	@Override
	protected void processInput(float delta) {
		super.processInput(delta);

		if (gameOver || gameWon) {
			if (keyboard.keyDown(KeyEvent.VK_N)) {
				initialize();
				return;
			}

		}

		int running = 2;
		float base = 2.4f;

		if (!controlLock) {
			// Running. Change running to 2 for final release.
			// I found it a reasonable value if we want to have it.,,,
			if (keyboard.keyDown(KeyEvent.VK_SHIFT))
				running = 3;

			if (keyboard.keyDown(KeyEvent.VK_W) || keyboard.keyDown(KeyEvent.VK_UP)) {
				mario.flip = false;
				mario.setSprite("Up");
				mario.direction = "Up";
				mario.setVY(base * running);
			} else if (keyboard.keyDown(KeyEvent.VK_S) || keyboard.keyDown(KeyEvent.VK_DOWN)) {
				mario.flip = false;
				mario.setSprite("Down");
				mario.direction = "Down";
				mario.setVY(-base * running);
			} else {
				mario.setVY(0);
				// mario.setSprite("");
			}

			if (keyboard.keyDown(KeyEvent.VK_A) || keyboard.keyDown(KeyEvent.VK_LEFT)) {
				mario.flip = true;
				mario.setSprite("Left");
				mario.direction = "Left";
				mario.setVX(-base * running);
			} else if (keyboard.keyDown(KeyEvent.VK_D) || keyboard.keyDown(KeyEvent.VK_RIGHT)) {
				mario.flip = false;
				mario.setSprite("Right");
				mario.direction = "Right";
				mario.setVX(base * running);
			} else {
				// mario.setSprite("");
				mario.setVX(0);
			}
			mario.velocity.x += map.roomData[cRoom].onLog(mario);
			if (!keyboard.keyDown(KeyEvent.VK_RIGHT) && !keyboard.keyDown(KeyEvent.VK_D)
					&& !keyboard.keyDown(KeyEvent.VK_UP) && !keyboard.keyDown(KeyEvent.VK_W)
					&& !keyboard.keyDown(KeyEvent.VK_DOWN) && !keyboard.keyDown(KeyEvent.VK_S)
					&& !keyboard.keyDown(KeyEvent.VK_LEFT) && !keyboard.keyDown(KeyEvent.VK_A)) {
				mario.setSprite("");
			}

		} else {
			// mario.setSprite("");
			mario.setVX(0);
			mario.setVY(0);
		}

		if (keyboard.keyDownOnce(KeyEvent.VK_E)) {
			map.roomData[cRoom].showDB = false;
			controlLock = false;
			map.roomData[cRoom].challengeActive = true;
		}

		if (keyboard.keyDownOnce(KeyEvent.VK_B)) {
			mario.greenBorder = !mario.greenBorder;
			map.background.greenBorder = !map.background.greenBorder;
			// for (int i = 0; i < map.roomData.length; i++) {
			// map.roomData[cRoom].showStuff();
			// }
		}

		switch (map.roomData[cRoom].passKeyboard(keyboard)) {
		case "Succeeded":
			for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
				map.roomData[cRoom].wt.get(x).activateTile();
				System.out.printf("Activated: %b\n", map.roomData[cRoom].wt.get(x).isActive());
			}
			break;
		case "Escaped":
			if (cRoom == 6) {
				cRoom = 3;
			} else if (cRoom == 11) {
				cRoom = 10;
			}
			map.background.currentSprite = map.roomData[cRoom].getBG();
			break;
		case "Failed":
			gameOverText = "TOO SLOW";
			mario.healthBar.doDamage(200);
			break;
		default:
			break;
		}

		/*
		 * The following controls are for debugging and testing ONLY! These MUST
		 * be removed for final release
		 */

		/* Health bar */
		if (keyboard.keyDownOnce(KeyEvent.VK_1)) {
			mario.healthBar.doDamage(10);
		}

		if (keyboard.keyDownOnce(KeyEvent.VK_2)) {
			mario.healthBar.addHealth(50);
		}

		// Oxygen bar
		if (keyboard.keyDownOnce(KeyEvent.VK_3)) {
			mario.healthBar.drainOxygen(10);
		}

		if (keyboard.keyDownOnce(KeyEvent.VK_4)) {
			mario.healthBar.addOxygen(50);
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

		map.update(delta, mario, cRoom);

		mario.update(delta, map);
		// mario.velocity.x += map.roomData[cRoom].onLog(mario);

		controlLock = map.lock(cRoom);

		map.updateOnObjects(delta, mario, cRoom);
		// mario.velocity.x += map.roomData[cRoom].onLog(mario);
		for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
			if (mario.rectRectIntersection(map.roomData[cRoom].wt.get(x).tile.getVWorld(),
					mario.subBox.get(0).getVWorld()) && map.roomData[cRoom].wt.get(x).isActive()) {
				mario.positions.x = map.roomData[cRoom].wt.get(x).getWarpToX();
				mario.positions.y = map.roomData[cRoom].wt.get(x).getWarpToY();
				if (map.roomData[cRoom].wt.get(x).challengeEntrance) {
					map.roomData[cRoom].wt.get(x).active = false;
					map.roomData[cRoom].wt.get(x).currentSprite = map.roomData[cRoom].wt.get(x).closed;
				}
				cRoom = map.roomData[cRoom].wt.get(x).getWarpMap();

				map.background.currentSprite = map.roomData[cRoom].getBG();
				map.roomData[cRoom].updateRoomData(delta);
				break;
			}
		}

		map.roomData[cRoom].rockUpdater(delta);

		if (map.roomData[cRoom].hazardHit(mario)) {
			mario.healthBar.doDamage(25);
		}

		// mario.velocity.x += map.roomData[cRoom].onLog(mario);
		// System.out.printf("Mario Velocity After:%f\n ", mario.velocity.x);

		if (map.roomData[9].items.isEmpty()) {
			gameWon = true;
			controlLock = true;
		}

		lock(controlLock, delta);

		overlay.updateScore(mario.score.getScore());
		if (gameOver) {
			overlay.updateMode(GameMode.GAMEOVER);
		} else if (gameWon) {
			overlay.updateMode(GameMode.GAMEWON);
		} else {
			overlay.updateMode(GameMode.STANDBY);
		}
	}

	// Locks the player if it needs to be locked.
	// If not, decreases the Health bar.
	private void lock(boolean controlLock, float delta) {
		if (!controlLock) {
			mario.healthBar.setImmune(false);
			mario.healthBar.update(delta);
			if (mario.healthBar.getHealthLevel() <= 0) {
				gameOver = true;
			}
		} else {
			mario.healthBar.setImmune(true);
		}
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);

		Matrix3x3f vp = getViewportTransform();

		if (!gameOver) {
			map.background.render(g, vp);
			if (cRoom == 5 || cRoom == 8) {
				map.roomData[cRoom].renderRoom(g, vp);
			}

			// s1.render(g, getViewportTransform());
			for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
				map.roomData[cRoom].wt.get(x).render(g, vp);
			}
			mario.render(g, vp);
			for (int x = 0; x < map.roomData[cRoom].items.size(); x++) {
				map.roomData[cRoom].items.get(x).render(g, vp);
			}
			mario.healthBar.render(g, vp);
			mario.score.render(g);
			/*
			 * if (map.roomData[cRoom].showDB) {
			 * map.roomData[cRoom].db.get(0).render(g, vp); }
			 */
			if (cRoom != 5 && cRoom != 8) {
				map.roomData[cRoom].renderRoom(g, vp);
			}

		}

		overlay.render(g);
	}

	@Override
	protected void terminate() {
		super.terminate();
	}

	public static void main(String[] args) {
		launchApp(new SpriteDemo());
	}
}
