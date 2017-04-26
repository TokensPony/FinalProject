package javagames.util;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.File;
import java.io.IOException;

import javax.*;
import javax.imageio.ImageIO;

public class Sprite{
	public boolean transparent;
	public boolean greenBorder;
	public boolean wallCollision = true;
	public BufferedImage spriteSheet;
	public BufferedImage currentSprite;
	public Vector2f positions;
	
	public VectorObject mainBox;
	public ArrayList<VectorObject> subBox = new ArrayList<VectorObject>();
	
	public boolean circle = false;
	public float radius;
	
	public float angle;
	public Vector2f velocity;
	public float rotation;
	public float scale = 1;
	
	public float cycle;
	
	public boolean flip = false;
	public String fn = "Images/Testfloor.png";
	
	public float delta;
	
	public float widthCollide;
	public float heightCollide;
	
	public int spriteWidth = 1280;
	public int spriteHeight = 720;
	
	public int width;
	public int height;
	
	/*Default sprite constructor*/
	public Sprite(){
		//loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(0.0f, 0.0f);
		velocity = new Vector2f(0.0f, 0.0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
	}
	
	/*Sprite constructor with parameters for filename, position
	 * velocity, angle and rotation.*/
	public Sprite(String filename, Vector2f pos, Vector2f vel, float ang, float rot, float sca){
		loadFile(filename, spriteWidth, spriteHeight);
		positions = pos;
		velocity = vel;
		angle = ang;
		rotation = rot;
		scale = sca;
	}
	
	/*Loads the sprite image and gets the width and height of the sprite.*/
	public void loadFile(String fileName, int W, int H) {
		try {
			//sprite is reference to BufferedImage, can read JPG, BMP, GIF, and PNG
			//Generates and returns BufferedImage object representation of image
			spriteSheet = ImageIO.read(new File(fileName));
			currentSprite = spriteSheet.getSubimage(0, 0, W, H);
			System.out.println("LOADED: " + fileName);
			width = currentSprite.getWidth();
			height = currentSprite.getHeight();
			System.out.printf("%d, %d\n", width, height);
		//If there is some error during reading
		} catch (IOException e) {
			e.printStackTrace();
			spriteSheet = null;
		}
	}
	
	/*This method sets the bounding boxes as well as defines the values for the collision
	 * values for the width and height, which are used for collision detection processes that
	 * do not use the bounding boxes*/
	public void setBB(int aW, int aH, float aWW, float aWH){
		int pixPerFH = aH/(int)aWH;
		heightCollide = (float)height/(float)pixPerFH;
		heightCollide *= .5;
		int pixPerFW = aW/(int)aWW;
		widthCollide = (float)width/(float)pixPerFW;
		widthCollide *= .5f;
		
		if(!circle){
			mainBox = new VectorObject(Color.GREEN, new Vector2f[] { new Vector2f(-widthCollide, heightCollide),
				new Vector2f(widthCollide, heightCollide), new Vector2f(widthCollide, -heightCollide),
				new Vector2f(-widthCollide, -heightCollide), });
			//System.out.printf("%f, %f\n", widthCollide, heightCollide);
		}else{
			if(heightCollide > widthCollide){
				radius = heightCollide;
			}else{
				radius = widthCollide;
			}
		}
	}
	
	/*Generic Method for setting up optional sub Bounding boxes*/
	public void setSubBox(){
		
		
	}
	
	/*Updates the sprites characteristics as well as those of the bounding mainBox*/
	public void updateObjects(float d){
		//System.out.println("Placeholder");
		width = currentSprite.getWidth();
		height = currentSprite.getHeight();
		delta = d;
		positions = positions.add(velocity.mul(delta));
		angle += rotation * delta;
		if(!circle){
			mainBox.setTX(positions.x);
			mainBox.setTY(positions.y);
			mainBox.setRot(angle);
			//mainBox.setScale(2);
			
			mainBox.updateWorld();
		}
		if(!subBox.isEmpty()){
			for(int x = 0; x < subBox.size(); x++){
				subBox.get(x).setTX(positions.x);
				subBox.get(x).setTY(positions.y);
				subBox.get(x).setRot(angle);
				subBox.get(x).updateWorld();
			}
		}
		//System.out.printf("%f, %f\n",positions.x,positions.y);
	}
	
	/*This is render class for the sprite which uses antialiasing for rendering. It also will turn
	 * the borders on and off */
	public void render(Graphics g, Matrix3x3f vp){
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.GREEN);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		doAffineTransform(g2d, vp);
		if(greenBorder){
			if(!circle){
				mainBox.render(vp, g2d);
				if(!subBox.isEmpty()){
					for(int x = 0; x < subBox.size(); x++){
						subBox.get(x).render(vp, g2d);
					}
				}
			}else{
				drawOval(g, vp, positions, radius*scale);
			}
		}
	}
	
	/*This doAffineTransform method is taken from the flyingSprite class and has been unmodified from it's
	 * original version.*/
	public void doAffineTransform(Graphics2D g2d, Matrix3x3f vp) {
			AffineTransform transform = createTransform(positions, angle, vp);
			g2d.drawImage(currentSprite, transform, null);
	}
	
	/*This is an empty method that is used for sprites that have animations*/
	public void setSprite(String status){
		
	}
	
	/*This is the createTransform method seen in the flyingSprites method. This has been modified to also flip the
	 * sprite if required.*/
	public AffineTransform createTransform(Vector2f position, float angle, Matrix3x3f vp) {
		Vector2f screen = vp.mul(position);
		AffineTransform transform = AffineTransform.getTranslateInstance(
				screen.x, screen.y);
		transform.rotate(angle);
		transform.scale(scale, scale);
		transform.translate(-currentSprite.getWidth() / 2, -currentSprite.getHeight() / 2);
		if(flip){
			transform.concatenate(AffineTransform.getScaleInstance(-1, 1));
	        transform.concatenate(AffineTransform.getTranslateInstance(-currentSprite.getWidth(), 0));
		}
		//transform.scale(1.25, 1.25);
		if(!circle){
			mainBox.setScale(scale);
		}
		if(!subBox.isEmpty()){
			for(int x = 0; x < subBox.size(); x++){
				subBox.get(x).setScale(scale);
			}
		}
		return transform;
	}
	
/*This is the standard version of the rectRectIntersection method, unmodified
 * from the original version*/
public boolean rectRectIntersection(Vector2f[] A, Vector2f[] B) {
		
		// separating axis intersection algorithm
		Vector2f N0 = A[0].sub(A[1]).div(2.0f);
		Vector2f N1 = A[1].sub(A[2]).div(2.0f);
		Vector2f CA = A[0].add(A[2]).div(2.0f);
		
		float D0 = N0.len();
		float D1 = N1.len();
		N1 = N1.div(D1);
		N0 = N0.div(D0);
		
		Vector2f N2 = B[0].sub(B[1]).div(2.0f);
		Vector2f N3 = B[1].sub(B[2]).div(2.0f);
		Vector2f CB = B[0].add(B[2]).div(2.0f);
		
		float D2 = N2.len();
		float D3 = N3.len();
		N2 = N2.div(D2);
		N3 = N3.div(D3);
		
		Vector2f C = CA.sub(CB);
		
		float DA = D0;
		float DB = D2 * Math.abs(N2.dot(N0));
		DB += D3 * Math.abs(N3.dot(N0));
		
		if (DA + DB < Math.abs(C.dot(N0)))
			return false;
		
		DA = D1;
		DB = D2 * Math.abs(N2.dot(N1));
		DB += D3 * Math.abs(N3.dot(N1));
		
		if (DA + DB < Math.abs(C.dot(N1)))
			return false;
		
		DA = D2;
		DB = D0 * Math.abs(N0.dot(N2));
		DB += D1 * Math.abs(N1.dot(N2));
		
		if (DA + DB < Math.abs(C.dot(N2)))
			return false;
		
		DA = D3;
		DB = D0 * Math.abs(N0.dot(N3));
		DB += D1 * Math.abs(N1.dot(N3));
		
		if (DA + DB < Math.abs(C.dot(N3)))
			return false;
		
		return true;
	}
	
/*Rectangle Rectangle Intersection. This is a modified version of the method found in the
 * RectRectOverlap example. This version instead takes in a sprite and then extracts it's
 * vWorld array and compares the input to the values of the sprite the method was called
 * for to determine the presence of an intersection.*/
public boolean rRI(VectorObject s) {
		Vector2f A[] = mainBox.getVWorld();
		Vector2f B[] = s.getVWorld();
		// separating axis intersection algorithm
		Vector2f N0 = A[0].sub(A[1]).div(2.0f);
		Vector2f N1 = A[1].sub(A[2]).div(2.0f);
		Vector2f CA = A[0].add(A[2]).div(2.0f);
		
		float D0 = N0.len();
		float D1 = N1.len();
		N1 = N1.div(D1);
		N0 = N0.div(D0);
		
		Vector2f N2 = B[0].sub(B[1]).div(2.0f);
		Vector2f N3 = B[1].sub(B[2]).div(2.0f);
		Vector2f CB = B[0].add(B[2]).div(2.0f);
		
		float D2 = N2.len();
		float D3 = N3.len();
		N2 = N2.div(D2);
		N3 = N3.div(D3);
		
		Vector2f C = CA.sub(CB);
		
		float DA = D0;
		float DB = D2 * Math.abs(N2.dot(N0));
		DB += D3 * Math.abs(N3.dot(N0));
		
		if (DA + DB < Math.abs(C.dot(N0)))
			return false;
		
		DA = D1;
		DB = D2 * Math.abs(N2.dot(N1));
		DB += D3 * Math.abs(N3.dot(N1));
		
		if (DA + DB < Math.abs(C.dot(N1)))
			return false;
		
		DA = D2;
		DB = D0 * Math.abs(N0.dot(N2));
		DB += D1 * Math.abs(N1.dot(N2));
		
		if (DA + DB < Math.abs(C.dot(N2)))
			return false;
		
		DA = D3;
		DB = D0 * Math.abs(N0.dot(N3));
		DB += D1 * Math.abs(N1.dot(N3));
		
		if (DA + DB < Math.abs(C.dot(N3)))
			return false;
		
		return true;
	}
	

	/*Checks to see if a circle and a bounding box has overlapped*/
	public boolean intersectCircleAABB(Vector2f c, float r, Vector2f min,
			Vector2f max) {
		float d = 0.0f;
		if (c.x < min.x)
			d += (c.x - min.x) * (c.x - min.x);
		if (c.x > max.x)
			d += (c.x - max.x) * (c.x - max.x);
		if (c.y < min.y)
			d += (c.y - min.y) * (c.y - min.y);
		if (c.y > max.y)
			d += (c.y - max.y) * (c.y - max.y);
		return d < r * r;
	}
	
	/*Draws oval shapes for the bounding circles*/
	private void drawOval(Graphics g, Matrix3x3f vp, Vector2f center, float radius) {
		Matrix3x3f view = vp;
		Vector2f topLeft = new Vector2f(center.x - radius, center.y + radius);
		topLeft = view.mul(topLeft);
		Vector2f bottomRight = new Vector2f(center.x + radius, center.y
				- radius);
		bottomRight = view.mul(bottomRight);
		int circleX = (int) topLeft.x;
		int circleY = (int) topLeft.y;
		int circleWidth = (int) (bottomRight.x - topLeft.x);
		int circleHeight = (int) (bottomRight.y - topLeft.y);
		g.drawOval(circleX, circleY, circleWidth, circleHeight);
	}
	
	/*Get and set methods for vx and vy*/
	public void setVX(float vx){
		velocity.x = vx;
	}
	
	public float getVX(){
		return velocity.x;
	}
	
	public void setVY(float vy){
		velocity.y = vy;
	}
	
	public float getVY(){
		return velocity.y;
	}
	
	
	/*Methods for determining if the sprite has collided with the edge of
	 * the viewable border of the canvas*/
	public boolean boarderCollideX(float size){
		if(wallCollision && positions.x > size-widthCollide){
			positions.x = size-widthCollide;
			return true;
		}else if(wallCollision && positions.x < -size+widthCollide){
			positions.x = -size+widthCollide;
			return true;
		}
		return false;
	}
	
	public boolean boarderCollideY(float size){
		if(wallCollision && (positions.y >= size - heightCollide)){
			positions.y = size-heightCollide;
			velocity.y = 0f;
			return true;
		}else if(wallCollision && positions.y <= -size+heightCollide){
			positions.y = -size+heightCollide;
			velocity.y = 0f;
			return true;
		}
		return false;
	}
}