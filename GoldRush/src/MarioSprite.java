
import java.awt.Color;

import javagames.util.Sprite;
import javagames.util.Vector2f;
//import javagames.util.VectorObject;
import javagames.util.VectorObject;

public class MarioSprite extends Sprite{
	
	public String fn = "Images/link test.png";
	final int spriteWidth = 46;
	final int spriteHeight = 65;
	//public boolean flip = false;
	
	/*Two different constructors for default and more customized starting values*/
	public MarioSprite(){
		//super();
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(0.0f, 0.0f);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
	}
	
	public MarioSprite(Vector2f pos, Vector2f vel, float ang, float rot){
		loadFile(fn, spriteWidth, spriteHeight);
		positions = pos;
		velocity = vel;
		angle = ang;
		rotation = rot;
	}
	
	/*This method establishes mario's subBox for his feet, which is used to register collission
	 * with the floor and platforms.*/
	@Override
	public void setSubBox(){
		VectorObject feet = new VectorObject(Color.GREEN, new Vector2f[] { new Vector2f(-widthCollide, -heightCollide+.05f),
				new Vector2f(widthCollide, -heightCollide+.05f), new Vector2f(widthCollide, -heightCollide),
				new Vector2f(-widthCollide, -heightCollide), });
		subBox.add(feet);
		System.out.println("Feet Set");
	}
	
	/*This method controls the various animations for Mario. It uses a cycle value that is increased
	 * by the value of delta every game loop. The walk animation cycles between 3 different images
	 * from left to right. Jumping uses only one frame. Climbing while moving uses 2 frames of animation
	 * while sitting idly on the vine uses only one. THe default sprite is Mario standing still.
	 * The temp value is calculated and used to determine which frame of animation should be used.*/
	@Override
	public void setSprite(String status){
		cycle += delta;
		switch(status){
		case "Walk":
		//if(getVX() == -.3f || getVX() == 0.3f){
			//System.out.println(cycle);
			float temp = (float)cycle%6;
			if((temp > 0f && temp < .2f)){
				currentSprite = spriteSheet.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
			}else if((temp > .2f && temp < .4f)||(temp > .6f && temp < .8f)){
				//28
				currentSprite = spriteSheet.getSubimage(spriteWidth*2, 0, spriteWidth, spriteHeight);
			}else if(temp > .4f && temp <.6f){
				currentSprite = spriteSheet.getSubimage(spriteWidth*3, 0, spriteWidth, spriteHeight);
			}else if(temp > .8f){
				cycle = 0f;
			}
			break;
		case "Jump":
			currentSprite = spriteSheet.getSubimage(spriteWidth*5, 0, spriteWidth, spriteHeight);
			break;
		case "Climbing":
			float climb = (float)cycle%4;
			System.out.println(climb);
			if((climb >= 0f && climb < .2f)){
				currentSprite = spriteSheet.getSubimage(spriteWidth*7, 0, spriteWidth, spriteHeight);
			}else if(climb >= .2f && climb <= .4f){
				currentSprite = spriteSheet.getSubimage(spriteWidth*8, 0, spriteWidth, spriteHeight);
			}else if (climb > .4f){
				cycle = 0f;
			}
			break;
		case "Grab":
			currentSprite = spriteSheet.getSubimage(spriteWidth*8, 0, spriteWidth, spriteHeight);
			break;
		//}else{
		default:
			//cycle = 0f;
			currentSprite = spriteSheet.getSubimage(0, 0, spriteWidth, spriteHeight);
			break;
		//}
		}
	}
	
}