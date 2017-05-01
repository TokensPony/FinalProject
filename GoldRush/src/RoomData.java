import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javagames.util.*;

import javax.imageio.ImageIO;

import javagames.util.Matrix3x3f;

public class RoomData{
	
	ArrayList<WarpTile> wt = new ArrayList<WarpTile>();
	ArrayList<Collectible> items = new ArrayList<Collectible>();
	public String fn;
	public BufferedImage bg;
	ArrayList<DialogBox> db;
	public boolean showDB = false;
	public int currentDB = 0;
	public boolean challengeActive = false;
	
	public int appWidth;
	public int appHeight;
	public float appWorldWidth;
	public float appWorldHeight;
	
	public String type;
	
	public RoomData(String filename){
		fn = filename;
		type = "Normal";
		try{
			bg = ImageIO.read(new File(filename));
		}catch (IOException e){
			e.printStackTrace();
			bg = null;
		}
	}
	
	public RoomData(String filename, String dbType){
		fn = filename;
		type = "Normal";
		showDB = true;
		db = new ArrayList<DialogBox>();
		db.add(new DialogBox(dbType));
		try{
			bg = ImageIO.read(new File(filename));
		}catch (IOException e){
			e.printStackTrace();
			bg = null;
		}
	}
	
	public void addWarpTile(WarpTile w){
		w.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		wt.add(w);
	}
	
	public void addCollectible(Collectible c){
		items.add(c);
	}
	
	public void setStuff(int aW, int aH, float aWW, float aWH){
		appWidth = aW;
		appHeight = aH;
		appWorldWidth = aWW;
		appWorldHeight = aWH;
	}
	
	public void updateRoomData(float delta){
		if(db != null){
			db.get(currentDB).updateObjects(delta);
		}
		for(int x = 0; x < wt.size(); x++){
			wt.get(x).updateObjects(delta);
		}
		
		for(int x = 0; x < items.size(); x++){
			items.get(x).updateObjects(delta);
		}
		/*SpriteDemo.test++;
		System.out.println(SpriteDemo.test);*/
		
	}
	
	public void renderRoom(Graphics g, Matrix3x3f vp){
		if (showDB) {
			db.get(currentDB).render(g, vp);
		}
	}
	
	public boolean doDamage(){
		return false;
	}
	
	public BufferedImage getBG(){
		return bg;
	}
	
	public void showStuff(){
		
	}
	
	public boolean hazardHit(MarioSprite m){
		return false;
	}
	
	public float onLog(MarioSprite m){
		return 0f;
	}
	
	public void rockUpdater(float delta){
		//System.out.println("Normal Room");
	}
	
	public String passKeyboard(KeyboardInput k){
		return "";
	}
	
	public void showNextDB(){
		currentDB++;
		showDB = true;
	}
}