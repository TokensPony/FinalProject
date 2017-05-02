import javagames.util.*;
import java.awt.*;
import java.util.*;

public class Lava extends Sprite{

	public final int damage = 200;
	
	public final int spriteWidth = 1280;
	public final int spriteHeight = 480;
	
	public Lava(){
		fn = "Images/LavaTemp.png";
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(0, 0);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
	}
}
