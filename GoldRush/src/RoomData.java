import javagames.util.*;
import java.awt.*;
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
	
	public RoomData(String filename){
		fn = filename;
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
	
	public BufferedImage getBG(){
		return bg;
	}
}