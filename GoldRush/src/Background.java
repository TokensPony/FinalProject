
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javagames.util.Matrix3x3f;
import javagames.util.Sprite;
import javagames.util.Vector2f;
import javagames.util.VectorObject;

public class Background extends Sprite{
	
	final int spriteWidth = 1280;//test test test
	final int spriteHeight = 720;
	
	int pos = 0;
	
	public String fn = "Images/Room-0.png";
	
	VectorObject floor = new VectorObject(Color.GREEN, new Vector2f[]{
			new Vector2f(-8f,-4.1f), new Vector2f(8f,-4.1f),
			new Vector2f(8f,-4.5f), new Vector2f(-8f,-4.5f),
	});
	
	VectorObject ceiling = new VectorObject(Color.GREEN, new Vector2f[]{
			new Vector2f(-8f,4.5f), new Vector2f(8f,4.5f),
			new Vector2f(8f, 4.1f), new Vector2f(-8f,4.1f),
	});
	
	VectorObject leftWall = new VectorObject(Color.GREEN, new Vector2f[]{
			new Vector2f(-8f, 4.5f), new Vector2f(-7.6f, 4.5f),
			new Vector2f(-7.6f, -4.5f), new Vector2f(-8f, -4.5f)
	});
	
	VectorObject rightWall = new VectorObject(Color.GREEN, new Vector2f[]{
			new Vector2f(7.6f, 4.5f), new Vector2f(8f, 4.5f),
			new Vector2f(8f, -4.5f), new Vector2f(7.6f, -4.5f)
	});
	
	public Background(float x, float y){
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(x, y);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
	}
	
	@Override
	public void setSubBox(){
		subBox.add(floor);
		subBox.add(ceiling);
		subBox.add(leftWall);
		subBox.add(rightWall);
	}
	
	@Override
	public void setSprite(String thing){
		currentSprite = spriteSheet.getSubimage(spriteWidth*pos, 0, spriteWidth, spriteHeight);
	}
	
	/*@Override
	public void render(Graphics g, Matrix3x3f vp){
		Graphics2D g2d = (Graphics2D) g;
		//g.setColor(Color.GREEN);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		doAffineTransform(g2d, vp);
		if(greenBorder){
			mainBox.render(vp, g2d);
			floor.render(vp, g2d);
		}
	}*/
}