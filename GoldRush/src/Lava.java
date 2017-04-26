import javagames.util.*;
import java.awt.*;
import java.util.*;

public class Lava extends Sprite{
	//public Sprite shadow;
	//public String hType;
	//public boolean greenBorder = true;
	
	public int damage;
	
	public int spriteWidth = 1280;
	public int spriteHeight = 480;
	
	public Lava(){
		//hType = type;
		//greenBorder = true;
		//circle = true;
		
		//switch(hType){
		//case "Rock":
			fn = "Images/LavaTemp.png";
		
			//break;
		//default:
		//	break;
		//}
		
		damage = 200;
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(0, 0);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
	}
}