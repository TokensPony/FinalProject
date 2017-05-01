import java.awt.Color;
import java.awt.Graphics;

import javagames.util.*;

public class WarpTile extends Sprite{

	public VectorObject tile;
	public boolean active = true;
	public boolean challengeEntrance = false;
	public int warpX;
	public float warpToX;
	public float warpToY;
	
	public int spriteWidth = 62;
	public int spriteHeight = 50;

	//Default constructor that sets a default .4x.4 Bounding box as well as it's characteristics
	//For what tile the room leads to, where the player appears on the screen, and where the tile
	//Itself physically resides.
	public WarpTile(int destination, float charPosX, float charPosY, float tilePosX, float tilePosY, boolean status, int degrees, String type) {
		fn = "Images/Door-Final.png";
		loadFile(fn,spriteWidth, spriteHeight);
		positions = new Vector2f(tilePosX, tilePosY);
		velocity = new Vector2f(0f, 0f);
		angle = (float) Math.toRadians(degrees);
		rotation = 0f;
		tile = new VectorObject(Color.RED, new Vector2f[] { new Vector2f(-.4f, .4f), new Vector2f(.4f, .4f),
				new Vector2f(.4f, -.4f), new Vector2f(-.4f, -.4f), });
		warpX = destination;
		warpToX = charPosX;
		warpToY = charPosY;
		tile.setTX(tilePosX);
		tile.setTY(tilePosY);
		active = status;
	}
	
	//Overstuffed method which allows for a custom tile size
	public WarpTile(VectorObject t, int destination, float charPosX, float charPosY, float tilePosX, float tilePosY) {
		tile = t;
		warpX = destination;
		warpToX = charPosX;
		warpToY = charPosY;
		tile.setTX(tilePosX);
		tile.setTY(tilePosY);
	}

	//Gets vworld of the tile
	public Vector2f[] getVWorld() {
		return tile.getVWorld();
	}

	//update
	@Override
	public void updateObjects(float delta) {
		super.updateObjects(delta);
		tile.updateWorld();
	}

	//Render
	@Override
	public void render(Graphics g, Matrix3x3f vp) {
		super.render(g, vp);
		//tile.render(vp, g);
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