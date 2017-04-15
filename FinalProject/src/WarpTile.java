import java.awt.Graphics;

import javagames.util.*;

public class WarpTile{
	
	public VectorObject tile;
	public boolean active;
	public int warpX;
	public float warpToX;
	
	public WarpTile(VectorObject t, int destination){
		tile = t;
		warpX = destination;
	}
	
	public Vector2f[] getVWorld(){
		return tile.getVWorld();
	}
	
	public void updateObjects(float delta){
		tile.updateWorld();
	}
	
	public void render(Graphics g, Matrix3x3f vp){
		tile.render(vp, g);
	}
	
	public int getWarpMap(){
		return warpX;
	}
	
	public float getWarpTo(){
		return warpToX;
	}
}