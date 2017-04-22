import javagames.util.*;
import java.awt.*;
import java.util.*;

public class Rock extends Sprite{
	public Sprite shadow;
	public String hType;
	
	public int damage;
	
	public int spriteWidth = 120;
	public int spriteHeight = 120;
	
	public Rock(float x, float y, String type, int value){
		hType = type;
		//switch(hType){
		//case "Rock":
			fn = "Images/Rock.png";
		
			//break;
		//default:
		//	break;
		//}
		
		damage = value;
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(x, y);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 3f;
	}
}