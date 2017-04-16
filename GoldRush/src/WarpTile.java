import java.awt.Color;
import java.awt.Graphics;

import javagames.util.*;

public class WarpTile {

	public VectorObject tile;
	public boolean active;
	public int warpX;
	public float warpToX;
	public float warpToY;

	/**/
	public WarpTile(int destination, float charPosX, float charPosY, float tilePosX, float tilePosY) {
		tile = new VectorObject(Color.RED, new Vector2f[] { new Vector2f(-.3f, .3f), new Vector2f(.3f, .3f),
				new Vector2f(.3f, -.3f), new Vector2f(-.3f, -.3f), });
		warpX = destination;
		warpToX = charPosX;
		warpToY = charPosY;
		tile.setTX(tilePosX);
		tile.setTY(tilePosY);
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
}