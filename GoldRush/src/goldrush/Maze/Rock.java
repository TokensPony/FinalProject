package goldrush.Maze;

import goldrush.Util.*;

public class Rock extends Sprite {
	public Sprite shadow;

	private int spriteWidth = 120;
	private int spriteHeight = 120;

	AePlayWave hit = new AePlayWave("Sounds/PlayerDamage.wav");
	AePlayWave rumble = new AePlayWave("Sounds/Rumble.wav");

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