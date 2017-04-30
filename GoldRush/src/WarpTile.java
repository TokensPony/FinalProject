import java.awt.Color;
import java.awt.Graphics;

import javagames.util.*;

public class WarpTile {

	public VectorObject tile;
	public boolean active = true;
	public boolean challengeEntrance = false;
	public int warpX;
	public float warpToX;
	public float warpToY;


	public WarpTile(int destination, float charPosX, float charPosY, float tilePosX, float tilePosY, boolean status) {
		tile = new VectorObject(Color.RED, new Vector2f[] { new Vector2f(-.4f, .4f), new Vector2f(.4f, .4f),
				new Vector2f(.4f, -.4f), new Vector2f(-.4f, -.4f), });
		warpX = destination;
		warpToX = charPosX;
		warpToY = charPosY;
		tile.setTX(tilePosX);
		tile.setTY(tilePosY);
		active = status;
	}

	public WarpTile(VectorObject t, int destination, float charPosX, float charPosY, float tilePosX, float tilePosY) {
		tile = t;
		warpX = destination;
		warpToX = charPosX;
		warpToY = charPosY;
		tile.setTX(tilePosX);
		tile.setTY(tilePosY);
	}

	public Vector2f[] getVWorld() {
		return tile.getVWorld();
	}

	public void updateObjects(float delta) {
		tile.updateWorld();
	}

	public void render(Graphics g, Matrix3x3f vp) {
		tile.render(vp, g);
	}

	public int getWarpMap() {
		return warpX;
	}

	public float getWarpToX() {
		return warpToX;
	}

	public float getWarpToY() {
		return warpToY;
	}
	
	public void activateTile(){
		active = true;
	}
	
	public void deactivateTile(){
		active = false;
	}
	
	public boolean isActive(){
		return active;
	}
}