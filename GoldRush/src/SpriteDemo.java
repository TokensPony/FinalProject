import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import javagames.util.AePlayWave;
import javagames.util.Matrix3x3f;
import javagames.util.WindowFramework;

public class SpriteDemo extends WindowFramework {

	Random r = new Random();

	MarioSprite mario;
	Background background;
	// HealthBar healthBar;
	// Score score;

	private Map map;

	boolean gameOver = false;
	boolean controlLock = false;

	int cRoom = 0;
	
	public String gameOverText = "GAME OVER";
	
	AePlayWave bgSong = new AePlayWave("Sounds/Spooky Graveyard Song.wav");

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

		// Initialization of the Map, rooms, collectibles, warptiles, etc...
		map = new Map();
		map.initialize(appWidth, appHeight, appWorldWidth, appWorldHeight);

		mario = new MarioSprite();
		mario.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		mario.setSubBox();
		
		bgSong.loop();
	}

	/* Processes the keyboard input for the various game controls */
	@Override
	protected void processInput(float delta) {
		super.processInput(delta);

		int running = 1;

		if (!controlLock) {
			// Running. Change running to 2 for final release.
			// I found it a reasonable value if we want to have it.,,,
			if (keyboard.keyDown(KeyEvent.VK_SHIFT))
				running = 4;

			if (keyboard.keyDown(KeyEvent.VK_W) || keyboard.keyDown(KeyEvent.VK_UP)) {
				mario.flip = false;
				mario.setSprite("Up");
				mario.direction = "Up";
				mario.setVY(2.4f * running);
			} else if (keyboard.keyDown(KeyEvent.VK_S) || keyboard.keyDown(KeyEvent.VK_DOWN)) {
				mario.flip = false;
				mario.setSprite("Down");
				mario.direction = "Down";
				mario.setVY(-2.4f * running);
			} else {
				mario.setVY(0);
				//mario.setSprite("");
			}

			if (keyboard.keyDown(KeyEvent.VK_A) || keyboard.keyDown(KeyEvent.VK_LEFT)) {
				mario.flip = true;
				mario.setSprite("Left");
				mario.direction = "Left";
				mario.setVX(-2.4f * running);
			} else if (keyboard.keyDown(KeyEvent.VK_D) || keyboard.keyDown(KeyEvent.VK_RIGHT)) {
				mario.flip = false;
				mario.setSprite("Right");
				mario.direction = "Right";
				mario.setVX(2.4f * running);
			} else {
				//mario.setSprite("");
				mario.setVX(0);
			}
			mario.velocity.x += map.roomData[cRoom].onLog(mario);
			if(!keyboard.keyDown(KeyEvent.VK_RIGHT) && !keyboard.keyDown(KeyEvent.VK_D) &&
					!keyboard.keyDown(KeyEvent.VK_UP) && !keyboard.keyDown(KeyEvent.VK_W) &&
					!keyboard.keyDown(KeyEvent.VK_DOWN) && !keyboard.keyDown(KeyEvent.VK_S)&&
					!keyboard.keyDown(KeyEvent.VK_LEFT)&& !keyboard.keyDown(KeyEvent.VK_A)){
				mario.setSprite("");
			}
			
		} else {
			//mario.setSprite("");
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
			//for (int i = 0; i < map.roomData.length; i++) {
			//	map.roomData[cRoom].showStuff();
			//}
		}
		
		switch(map.roomData[cRoom].passKeyboard(keyboard)){
		case "Succeeded":
			for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
				map.roomData[cRoom].wt.get(x).activateTile();
				System.out.printf("Activated: %b\n", map.roomData[cRoom].wt.get(x).isActive());
			}
			break;
		case "Escaped":
			if(cRoom == 6){
				cRoom = 3;
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
		
		/*if(keyboard.keyDown(KeyEvent.VK_N) && keyboard.keyDown(KeyEvent.VK_M)){
			System.out.println("IT WORKED");
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
		//mario.velocity.x += map.roomData[cRoom].onLog(mario);

		controlLock = map.lock(cRoom);

		map.updateOnObjects(delta, mario, cRoom);
		//mario.velocity.x += map.roomData[cRoom].onLog(mario);
		for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
			if (mario.rectRectIntersection(map.roomData[cRoom].wt.get(x).tile.getVWorld(), mario.subBox.get(0).getVWorld()) && map.roomData[cRoom].wt.get(x).isActive()) {
				mario.positions.x = map.roomData[cRoom].wt.get(x).getWarpToX();
				mario.positions.y = map.roomData[cRoom].wt.get(x).getWarpToY();
				if(map.roomData[cRoom].wt.get(x).challengeEntrance){
					map.roomData[cRoom].wt.get(x).active = false;
				}
				cRoom = map.roomData[cRoom].wt.get(x).getWarpMap();

				map.background.currentSprite = map.roomData[cRoom].getBG();
				map.roomData[cRoom].updateRoomData(delta);
				break;
			}
		}

		map.roomData[cRoom].rockUpdater(delta);
		
		if(map.roomData[cRoom].hazardHit(mario)){
			mario.healthBar.doDamage(25);
		}
		
		//mario.velocity.x += map.roomData[cRoom].onLog(mario);
		//System.out.printf("Mario Velocity After:%f\n ", mario.velocity.x);
		
		
		lock(controlLock, delta);
	}

	// Locks the player if it needs to be locked.
	// If not, decreases the Health bar.
	private void lock(boolean controlLock, float delta) {
		if (!controlLock) {
			mario.healthBar.update(delta);
			if (mario.healthBar.healthLevel <= 0) {
				gameOver = true;
			}
		}
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);

		Matrix3x3f vp = getViewportTransform();

		if (!gameOver) {
			map.background.render(g, vp);
			if(cRoom == 5 || cRoom == 8){
				map.roomData[cRoom].renderRoom(g, vp);
			}
			mario.render(g, vp);
			// s1.render(g, getViewportTransform());
			for (int x = 0; x < map.roomData[cRoom].wt.size(); x++) {
				map.roomData[cRoom].wt.get(x).render(g, vp);
			}
			for (int x = 0; x < map.roomData[cRoom].items.size(); x++) {
				map.roomData[cRoom].items.get(x).render(g, vp);
			}
			mario.healthBar.render(g, vp);
			mario.score.render(g);
			if (map.roomData[cRoom].showDB) {
				map.roomData[cRoom].db.render(g, vp);
			}
			if(cRoom != 5 && cRoom != 8){
				map.roomData[cRoom].renderRoom(g, vp);
			}
		} else {
			g.setColor(Color.RED);
			g.drawString(gameOverText, 640, 360);
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