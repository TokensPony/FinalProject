package goldrush.Maze;

import java.awt.Color;

import goldrush.Util.Sprite;
import goldrush.Util.Vector2f;
import goldrush.Util.VectorObject;

// SourceTree test

public class Background extends Sprite {

	final int spriteWidth = 1280;
	final int spriteHeight = 720;

	int pos = 0;

	public String fn = "Images/Background-Normal.png";
	
	//Define bounding boxes
	VectorObject floor = new VectorObject(Color.GREEN, new Vector2f[] { new Vector2f(-8f, -4.1f),
			new Vector2f(8f, -4.1f), new Vector2f(8f, -4.5f), new Vector2f(-8f, -4.5f), });

	VectorObject ceiling = new VectorObject(Color.GREEN, new Vector2f[] { new Vector2f(-8f, 4.5f),
			new Vector2f(8f, 4.5f), new Vector2f(8f, 4.1f), new Vector2f(-8f, 4.1f), });

	VectorObject leftWall = new VectorObject(Color.GREEN, new Vector2f[] { new Vector2f(-8f, 4.5f),
			new Vector2f(-7.6f, 4.5f), new Vector2f(-7.6f, -4.5f), new Vector2f(-8f, -4.5f) });

	VectorObject rightWall = new VectorObject(Color.GREEN, new Vector2f[] { new Vector2f(7.6f, 4.5f),
			new Vector2f(8f, 4.5f), new Vector2f(8f, -4.5f), new Vector2f(7.6f, -4.5f) });

	public Background(float x, float y) {
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(x, y);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
	}

	@Override
	public void setSubBox() {
		subBox.add(floor);
		subBox.add(ceiling);
		subBox.add(leftWall);
		subBox.add(rightWall);
	}

	@Override
	public void setSprite(String thing) {
		currentSprite = spriteSheet.getSubimage(spriteWidth * pos, 0, spriteWidth, spriteHeight);
	}

	/*
	 * @Override public void render(Graphics g, Matrix3x3f vp){ Graphics2D g2d =
	 * (Graphics2D) g; //g.setColor(Color.GREEN);
	 * g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	 * RenderingHints.VALUE_ANTIALIAS_ON); doAffineTransform(g2d, vp);
	 * if(greenBorder){ mainBox.render(vp, g2d); floor.render(vp, g2d); } }
	 */
}
