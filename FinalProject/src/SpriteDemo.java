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
		mario = new MarioSprite();
		mario.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		mario.setSubBox();
		
		WarpTile s1 = new WarpTile(new VectorObject(Color.RED, new Vector2f[]{
			new Vector2f(-.3f,.3f), new Vector2f(.3f,.3f),
			new Vector2f(.3f,-.3f), new Vector2f(-.3f,-.3f),
		}), 1);
		s1.tile.setTX(7.8f);
		s1.warpToX = -7f;
		
		WarpTile s2 = new WarpTile(new VectorObject(Color.RED, new Vector2f[]{
				new Vector2f(-.3f,.3f), new Vector2f(.3f,.3f),
				new Vector2f(.3f,-.3f), new Vector2f(-.3f,-.3f),
			}), 0);
		s2.tile.setTX(-7.8f);
		s2.warpToX = 7f;
		
		rd = new RoomData[]{new RoomData(), new RoomData()};
		rd[0].addWarpTile(s1);
		rd[1].addWarpTile(s2);
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

	}

	@Override
	protected void updateObjects(float delta) {
		super.updateObjects(delta);
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
		
		for(int x = 0; x < rd[cRoom].wt.size(); x++){
			rd[cRoom].wt.get(x).updateObjects(delta);
			if(mario.rRI(rd[cRoom].wt.get(x).tile)){
				System.out.println("Warped");
				mario.positions.x = rd[cRoom].wt.get(x).getWarpTo();
				cRoom = rd[cRoom].wt.get(x).getWarpMap();
				System.out.println(cRoom);
				b.pos = cRoom;
				b.setSprite("thing");
				rd[cRoom].wt.get(0).updateObjects(delta);
				break;
			}
		}
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
	}
	
	@Override
	protected void terminate() {
		super.terminate();
	}

	public static void main(String[] args) {
		launchApp(new SpriteDemo());
	}
}