
import java.awt.Color;

import javagames.util.Sprite;
import javagames.util.Vector2f;
//import javagames.util.VectorObject;
import javagames.util.VectorObject;

public class MarioSprite extends Sprite{
	
	public String fn = "Images/PlayerSprites.png";
	final int spriteWidth = 50;
	final int spriteHeight = 70;
	
	public HealthBar healthBar;
	public Score score;
	public String direction = "Down";
	
	//public boolean flip = false;
	
	/*Two different constructors for default and more customized starting values*/
	public MarioSprite(){
		//super();
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(0.0f, 0.0f);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
		
		healthBar = new HealthBar();
		score = new Score();
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
		float temp = (float)cycle%2;
		switch(status){
		case "Down":
			//System.out.println(x);
			if((temp > 0f && temp < .2f)){
				System.out.println("One");
				currentSprite = spriteSheet.getSubimage(spriteWidth, 0, spriteWidth, spriteHeight);
			}else if((temp > .2f && temp < .4f)){
				//28
				System.out.println("Two");
				currentSprite = spriteSheet.getSubimage(spriteWidth*2, 0, spriteWidth, spriteHeight);
			}else if(temp > .4f){
				cycle = 0f;
			}
			break;
		case "Left":
		case "Right":
			//float temp = (float)cycle%2;
			//System.out.println(x);
			if((temp > 0f && temp < .2f)){
				System.out.println("One");
				currentSprite = spriteSheet.getSubimage(spriteWidth, spriteHeight, spriteWidth, spriteHeight);
			}else if((temp > .2f && temp < .4f)){
				//28
				System.out.println("Two");
				currentSprite = spriteSheet.getSubimage(spriteWidth*2, spriteHeight, spriteWidth, spriteHeight);
			}else if(temp > .4f){
				cycle = 0f;
			}
			break;
		case "Up":
			//float temp = (float)cycle%2;
			//System.out.println(x);
			if((temp > 0f && temp < .2f)){
				System.out.println("One");
				currentSprite = spriteSheet.getSubimage(spriteWidth, spriteHeight*2, spriteWidth, spriteHeight);
			}else if((temp > .2f && temp < .4f)){
				//28
				System.out.println("Two");
				currentSprite = spriteSheet.getSubimage(spriteWidth*2, spriteHeight*2, spriteWidth, spriteHeight);
			}else if(temp > .4f){
				cycle = 0f;
			}
			break;
		default:
			//cycle = 0f;
			switch(direction){
			case "Down":
				currentSprite = spriteSheet.getSubimage(0, 0, spriteWidth, spriteHeight);
				break;
			case "Left":
			case "Right":
				currentSprite = spriteSheet.getSubimage(0, spriteHeight, spriteWidth, spriteHeight);
				break;
			case "Up":
				currentSprite = spriteSheet.getSubimage(0, spriteHeight*2, spriteWidth, spriteHeight);
				break;
			}
			//System.out.println("Default");
			
			break;
		}
	}
	
	public void update(float delta, Map map) {
		updateObjects(delta);
		
		if (rectRectIntersection(map.background.subBox.get(0).getVWorld(), mainBox.getVWorld())) {
			positions.y = -3.3f;
		} else if (rectRectIntersection(map.background.subBox.get(1).getVWorld(), mainBox.getVWorld())) {
			positions.y = 3.3f;
		} else if (rectRectIntersection(map.background.subBox.get(2).getVWorld(), mainBox.getVWorld())) {
			positions.x = -7.2f;
		} else if (rectRectIntersection(map.background.subBox.get(3).getVWorld(), mainBox.getVWorld())) {
			// b.setSprite("Thing");
			// cRoom = map.roomData[cRoom].wt.get(0).getWarpCoord();
			positions.x = 7.2f;
		}
	}
}