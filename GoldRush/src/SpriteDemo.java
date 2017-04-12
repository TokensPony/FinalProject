import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javagames.util.*;


public class SpriteDemo extends SimpleFramework {
	
	Random r = new Random();
	
	MarioSprite mario;
	Background b;
	HealthBar h;
	Score s;
	//WarpTile s1;
	///arpTile s2;
	
	int cRoom = 0;
	
	RoomData[] rd;
	
	public SpriteDemo() {
		appBackground = Color.BLACK;
		appBorder = Color.LIGHT_GRAY;
		appFont = new Font("Courier New", Font.PLAIN, 14);
		appBorderScale = .9f;
		appFPSColor = Color.BLACK;
		appWidth = 1280;
		appHeight = 720;
		appMaintainRatio = false;
		appSleep = 10L;
		appTitle = "Sprite Demo";
		appWorldWidth = 16.0f;
		appWorldHeight = 9.0f;
	}
	
	/*Initializes the various sprites w/ their coordinates and sets their bounding boxes.*/
	@Override
	protected void initialize() {
		super.initialize();
		
		b = new Background(0f, 0f);
		b.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		b.setSubBox();
		b.setSprite("thing");
		//sprites.add(new Floor(-1.0f, -4.4f));
		s = new Score();
		
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
		
		
		rd = new RoomData[]{new RoomData("Images/Room-0.png"), new RoomData("Images/Room-1.png"),
				new RoomData("Images/Room-0.png")};
		rd[0].addWarpTile(s1);
		rd[0].addCollectible(c1);
		
		rd[1].addWarpTile(s2);
		rd[1].addWarpTile(s3);
		rd[1].addCollectible(c2);
		rd[1].addCollectible(c3);
		
		rd[2].addWarpTile(s4);
		
		
		//for(int x = 0; x < rd[cRoom].items.size(); x++){
		//	rd[cRoom].items.get(x).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		//}
		
		h = new HealthBar();
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
			b.greenBorder = !b.greenBorder;
		}

		if(keyboard.keyDownOnce(KeyEvent.VK_1)){
			h.doDamage(10);
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_2)){
			h.addHealth(50);
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_3)){
			h.drainOxygen(10);
		}
		
		if(keyboard.keyDownOnce(KeyEvent.VK_4)){
			h.addOxygen(50);
		}
	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
		//System.out.printf("%f, %f\n", mario.positions.x, mario.positions.y);
		//System.out.println(getWorldMousePosition());
		b.updateObjects(delta);
		mario.updateObjects(delta);
		//s1.updateObjects(delta);
		
		if(mario.rectRectIntersection(b.subBox.get(0).getVWorld(), mario.mainBox.getVWorld())){
			mario.positions.y = -3.3f;
		}else if(mario.rectRectIntersection(b.subBox.get(1).getVWorld(), mario.mainBox.getVWorld())){
			mario.positions.y = 3.3f;
		}else if(mario.rectRectIntersection(b.subBox.get(2).getVWorld(), mario.mainBox.getVWorld())){
			mario.positions.x = -7.2f;
		}else if(mario.rectRectIntersection(b.subBox.get(3).getVWorld(), mario.mainBox.getVWorld())){
			//b.setSprite("Thing");
			//cRoom = rd[cRoom].wt.get(0).getWarpCoord();
			mario.positions.x = 7.2f;
		}
		
		rd[cRoom].updateRoomData(delta);
		//This is a test
		for(int x = 0; x < rd[cRoom].items.size(); x++){
			//rd[cRoom].items.get(x).updateObjects(delta);
			if(mario.rRI(rd[cRoom].items.get(x).mainBox)){
				switch(rd[cRoom].items.get(x).getType()){
				case "Oxygen":
					h.addOxygen(rd[cRoom].items.get(x).getIncrease());
					break;
				case "Gold":
					s.increaseScore(rd[cRoom].items.get(x).getIncrease());
					break;
				default:
					System.out.print("Unknown Thing");
					break;
				//x--;
				}
				rd[cRoom].items.remove(x);
			}
		}
		
		//rd[cRoom].updateRoomData(delta);
		for(int x = 0; x < rd[cRoom].wt.size(); x++){
			//rd[cRoom].wt.get(x).updateObjects(delta);
			if(mario.rRI(rd[cRoom].wt.get(x).tile)){
				//System.out.println("Warped");
				mario.positions.x = rd[cRoom].wt.get(x).getWarpToX();
				mario.positions.y = rd[cRoom].wt.get(x).getWarpToY();
				cRoom = rd[cRoom].wt.get(x).getWarpMap();
				//System.out.println(cRoom);
				//b.pos = cRoom;
				//b.setSprite("thing");
				//b.changeSprite(rd[cRoom].getBG());
				b.currentSprite = rd[cRoom].getBG();
				//rd[cRoom].wt.get(x).updateObjects(delta);
				rd[cRoom].updateRoomData(delta);
				break;
			}
		}
		
		h.update(delta);
	}

	@Override
	protected void render(Graphics g) {
		super.render(g);
		b.render(g, getViewportTransform());
		mario.render(g, getViewportTransform());
		//s1.render(g, getViewportTransform());
		for(int x = 0; x < rd[cRoom].wt.size(); x++){
			rd[cRoom].wt.get(x).render(g, getViewportTransform());
		}
		for(int x = 0; x < rd[cRoom].items.size(); x++){
			rd[cRoom].items.get(x).render(g, getViewportTransform());
		}
		h.render(g, getViewportTransform());
		s.render(g);
	}
	
	@Override
	protected void terminate() {
		super.terminate();
	}

	public static void main(String[] args) {
		launchApp(new SpriteDemo());
	}
}