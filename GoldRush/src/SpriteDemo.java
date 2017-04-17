import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

import javagames.util.WindowFramework;

//BLAH BLAH
public class SpriteDemo extends WindowFramework {
	
	Random r = new Random();
	
	MarioSprite mario;
	Background background;
	HealthBar healthBar;
	Score score;
	//WarpTile s1;
	///arpTile s2;
	
	int cRoom = 0;
	
	RoomData[] roomData;
	
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
	
	/*Initializes the various sprites w/ their coordinates and sets their bounding boxes.*/
	@Override
	protected void initialize() {
		super.initialize();
		
		background = new Background(0f, 0f);
		background.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		background.setSubBox();
		background.setSprite("thing");
		//sprites.add(new Floor(-1.0f, -4.4f));
		score = new Score();
		
		mario = new MarioSprite();
		mario.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		mario.setSubBox();
		
		//Room 0 Tile 0
		WarpTile s1 = new WarpTile(1, 0, -3f, 0, 4.2f);
		Collectible c1 = new Collectible(7f, 3f, "Gold", 100);
		c1.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		//Room 1 Tile 0
		WarpTile s2 = new WarpTile(0, 0, 3f, 0, -4.2f);
		WarpTile s3 = new WarpTile(2, 7f, 0f, -7.8f, 0);
		Collectible c2 = new Collectible(0f, 0f, "Oxygen", 50);
		Collectible c3 = new Collectible(-5f, 3f, "Gold", 100);
		c2.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		c3.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		
		//Room 3 Tile 0
		WarpTile s4 = new WarpTile(1, -7f, 0, 7.7f, 0f);
		
		
		roomData = new RoomData[]{new RoomData("Images/Room-0.png"), new RoomData("Images/Room-1.png"),
				new RoomData("Images/Room-0.png")};
		roomData[0].addWarpTile(s1);
		roomData[0].addCollectible(c1);
		
		roomData[1].addWarpTile(s2);
		roomData[1].addWarpTile(s3);
		roomData[1].addCollectible(c2);
		roomData[1].addCollectible(c3);
		
		roomData[2].addWarpTile(s4);
		
		
		//for(int x = 0; x < rd[cRoom].items.size(); x++){
		//	rd[cRoom].items.get(x).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		//}
		
		healthBar = new HealthBar();
		//System.out.println(rd.length);
		//System.out.println();
		
		
	}

	/*Processes the keyboard input for the various game controls*/
	@Override
	protected void processInput(float delta) {
		super.processInput(delta);
		if(keyboard.keyDown(KeyEvent.VK_W)){
			mario.setVY(2f);
		}else if(keyboard.keyDown(KeyEvent.VK_S)){
			mario.setVY(-2f);	
		}else{
			mario.setVY(0);
		}
		
		if(keyboard.keyDown(KeyEvent.VK_A)){
			mario.setVX(-2f);
		}else if(keyboard.keyDown(KeyEvent.VK_D)){
			mario.setVX(2f);
		}else{
			mario.setVX(0);
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_B)){
			mario.greenBorder = !mario.greenBorder;
			background.greenBorder = !background.greenBorder;
		}

		if(keyboard.keyDownOnce(KeyEvent.VK_1)){
			healthBar.doDamage(10);
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_2)){
			healthBar.addHealth(50);
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_3)){
			healthBar.drainOxygen(10);
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_4)){
			healthBar.addOxygen(50);
		}
	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		//System.out.printf("%f, %f\n", mario.positions.x, mario.positions.y);
		//System.out.println(getWorldMousePosition());
		background.updateObjects(delta);
		mario.updateObjects(delta);
		//s1.updateObjects(delta);
		
		if(mario.rectRectIntersection(background.subBox.get(0).getVWorld(), mario.mainBox.getVWorld())){
			mario.positions.y = -3.3f;
		}else if(mario.rectRectIntersection(background.subBox.get(1).getVWorld(), mario.mainBox.getVWorld())){
			mario.positions.y = 3.3f;
		}else if(mario.rectRectIntersection(background.subBox.get(2).getVWorld(), mario.mainBox.getVWorld())){
			mario.positions.x = -7.2f;
		}else if(mario.rectRectIntersection(background.subBox.get(3).getVWorld(), mario.mainBox.getVWorld())){
			//b.setSprite("Thing");
			//cRoom = rd[cRoom].wt.get(0).getWarpCoord();
			mario.positions.x = 7.2f;
		}
		
		roomData[cRoom].updateRoomData(delta);
		//This is a test
		for(int x = 0; x < roomData[cRoom].items.size(); x++){
			//rd[cRoom].items.get(x).updateObjects(delta);
			if(mario.rRI(roomData[cRoom].items.get(x).mainBox)){
				switch(roomData[cRoom].items.get(x).getType()){
				case "Oxygen":
					healthBar.addOxygen(roomData[cRoom].items.get(x).getIncrease());
					break;
				case "Gold":
					score.increaseScore(roomData[cRoom].items.get(x).getIncrease());
					break;
				default:
					System.out.print("Unknown Thing");
					break;
				//x--;
				}
				roomData[cRoom].items.remove(x);
			}
		}
		
		//rd[cRoom].updateRoomData(delta);
		for(int x = 0; x < roomData[cRoom].wt.size(); x++){
			//rd[cRoom].wt.get(x).updateObjects(delta);
			if(mario.rRI(roomData[cRoom].wt.get(x).tile)){
				//System.out.println("Warped");
				mario.positions.x = roomData[cRoom].wt.get(x).getWarpToX();
				mario.positions.y = roomData[cRoom].wt.get(x).getWarpToY();
				cRoom = roomData[cRoom].wt.get(x).getWarpMap();
				//System.out.println(cRoom);
				//b.pos = cRoom;
				//b.setSprite("thing");
				//b.changeSprite(rd[cRoom].getBG());
				background.currentSprite = roomData[cRoom].getBG();
				//rd[cRoom].wt.get(x).updateObjects(delta);
				roomData[cRoom].updateRoomData(delta);
				break;
			}
		}
		
		healthBar.update(delta);
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		background.render(g, getViewportTransform());
		mario.render(g, getViewportTransform());
		//s1.render(g, getViewportTransform());
		for(int x = 0; x < roomData[cRoom].wt.size(); x++){
			roomData[cRoom].wt.get(x).render(g, getViewportTransform());
		}
		for(int x = 0; x < roomData[cRoom].items.size(); x++){
			roomData[cRoom].items.get(x).render(g, getViewportTransform());
		}
		healthBar.render(g, getViewportTransform());
		score.render(g);
	}
	
	@Override
	protected void terminate() {
		super.terminate();
	}

	public static void main(String[] args) {
		launchApp(new SpriteDemo());
	}
}