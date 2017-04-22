import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import javagames.util.Matrix3x3f;

public class RoomData{
	
	ArrayList<WarpTile> wt = new ArrayList<WarpTile>();
	ArrayList<Collectible> items = new ArrayList<Collectible>();
	public String fn;
	public BufferedImage bg;
	public DialogBox db;
	public boolean showDB = false;
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
		db = new DialogBox(dbType);
		try{
			bg = ImageIO.read(new File(filename));
		}catch (IOException e){
			e.printStackTrace();
			bg = null;
		}
	}
	
	public void addWarpTile(WarpTile w){
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
			db.updateObjects(delta);
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
		
	}
	
	public boolean doDamage(){
		return false;
	}
	
	public BufferedImage getBG(){
		return bg;
	}
	
	public void rockUpdater(float delta){
		//System.out.println("Normal Room");
	}
}