import javagames.util.*;


public class Log extends Sprite{
	
	public int spriteWidth = 320;
	public int spriteHeight = 120;
	
	public Log(float posX, float posY, float direction){
		fn = "Images/Log.png";
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(posX, posY);
		velocity = new Vector2f(direction, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
	}
	
	
}