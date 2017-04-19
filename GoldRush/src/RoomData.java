import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class RoomData{
	
	ArrayList<WarpTile> wt = new ArrayList<WarpTile>();
	ArrayList<Collectible> items = new ArrayList<Collectible>();
	public String fn;
	public BufferedImage bg;
	public DialogBox db;
	public boolean showDB = false;
	
	public RoomData(String filename){
		fn = filename;
		try{
			bg = ImageIO.read(new File(filename));
		}catch (IOException e){
			e.printStackTrace();
			bg = null;
		}
	}
	
	public RoomData(String filename, String dbType){
		fn = filename;
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
	
	public boolean doDamage(){
		return false;
	}
	
	public BufferedImage getBG(){
		return bg;
	}
}