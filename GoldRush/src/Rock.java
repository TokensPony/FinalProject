import javagames.util.Sprite;
import javagames.util.Vector2f;

public class Rock extends Sprite {
	public Sprite shadow;

	private int spriteWidth = 120;
	private int spriteHeight = 120;

	public Rock(float x, float y) {
		circle = true;

		fn = "Images/Rock.png";
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(x, y);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 3f;
	}
}