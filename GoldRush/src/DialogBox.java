import java.awt.*;
import java.util.*;
import javagames.util.*;

public class DialogBox extends Sprite{
	String title;
	String message;
	
	String type;
	
	public String fn = "Images/DialogueBox.png";
	public final int spriteWidth = 640;
	public final int spriteHeight = 480;
	
	public DialogBox(String type){
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(0.0f, 0.0f);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
		switch(type){
		
		case "QTE":
			title = "You're in trouble NOW!";
			message = /*"As we speak, the room is about to collapse\n"
					+ "and failing to escape means certain death...\n"
					+ "You've got to choose to go for it or call it\n"
					+ "quits....FAST!\n\n"
					+ */"Press \"E\" to continue...";
			break;
		default:
			break;
		}
	}
	
	@Override
	public void render(Graphics g, Matrix3x3f vp){
		super.render(g, vp);
		g.setColor(Color.BLACK);
		g.drawString(title, 640, 300);
		g.drawString(message, 640, 480);
	}
}