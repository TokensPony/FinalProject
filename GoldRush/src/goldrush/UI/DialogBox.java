package goldrush.UI;
import java.awt.*;
import java.util.*;

import goldrush.Util.*;

public class DialogBox extends Sprite {
	String title;
	String message;

	String splitMessage[];

	String type;

	public String fn = "Images/DialogueBox.png";
	public final int spriteWidth = 640;
	public final int spriteHeight = 480;

	public DialogBox(String type) {
		loadFile(fn, spriteWidth, spriteHeight);
		positions = new Vector2f(0.0f, 0.0f);
		velocity = new Vector2f(0.0f, 0f);
		angle = (float) Math.toRadians(0);
		rotation = 0f;
		switch (type) {

		// NO MORE THAN 35 CHARS PER LINE (not counting \n)
		case "FallingRocks":
			title = "Watch your head!";
			message = "Looks like rocks are going to be\n" + "falling here! No turning back now. \n"
					+ "Collect all the gold and get out\n" + "while you can! I hope this risk\n" 
					+ "was worth it!\n"
					+ "\n\nPress \"E\" to continue...";
			break;
		case "Success":
			title = "Decisions, decisions....";
			message = 	"Looks like you got what you came\n" +
						"for! Now you must decide. If you\n" +
						"continue on, you may find as many\n" +
						"dangers as there are treasures.\n" +
						"Or you can call play it safe and\n" +
						"leave while you can. Just know that\n" + 
						"once you make your choice, there's\n" +
						"no turning back...\n\n";
			break;
		case "QTE":
			title = "You're in trouble NOW!";
			message = 	"As we speak, the room is about to cave\n" +
						"in and failing to escape means\n" +
						"certain death...You've got to\n" +
						"choose to go for it or call it\n" +
						"quits....FAST! All keys must be\n" +
						"pressed together!\n\n"+
						"Press \"E\" to continue...";
			// "Abcdefghijklmopqrstuvwxy1234567890ABCDEFGHIJKL";
			break;
		case "GoldIntroduction":
			title = "These are pieces of gold";
			message = "Throughout the mine you will find\n" + "pieces of diamond and\n" + "gold of varying size and value.\n"
					+ "Coin = 1\n" + "Nugget = 30\n" + "Bar = 120\n" + "Diamond = 200\n\n"
					+ "Press \"E\" to continue...";
			break;
		case "OxygenIntroduction":
			title = "These are oxygen tanks";
			message = "Inside the mine you will\n" + "also find tanks of oxygen that\n" + "will refill your oxygen meter.\n"
					+ "The tanks will also vary in the\n" + "amount of oxygen you will regain\n" + "but you can never carry more\n"
					+ "than the amount of oxygen\n" + "that you started with.\n"
					+ "Press \"E\" to continue...";
			break;
		case "Room3":
			title = "Branching Paths";
			message = "For the rest of your adventure,\n" + "you will need to make choices\n" + "about which path you will take.\n"
					+ "To the east is a series of\n" + "challenge rooms that hold\n" + "a treasure should you complete them.\n"
					+ "To the north is the rest of the mine.\n" + "The choice is yours, but be careful\n" + " not to get lost in your greed.\n"
					+ "Press \"E\" to continue...";
			break;
		case "LavaRoom":
			title = "A Firey Finish";
			message = 	"Before you is the final gauntlet\n" + 
						"you must pass. Move carefully on\n" +
						"these stone logs, lest all your\n" +
						"hard work go up in flames! Plus,\n" +
						"lava wouldn't look good on your\n" +
						"skin...\n" +
						"Press \"E\" to continue...\n";
					break;
		default:
			break;
		}
		splitMessage = message.split("\\r?\\n");
	}

	@Override
	public void render(Graphics g, Matrix3x3f vp) {
		super.render(g, vp);
		g.setColor(Color.BLACK);
		int titleWidth = g.getFontMetrics().stringWidth(title);
		//
		int fontHeight = g.getFontMetrics().getHeight();
		g.drawString(title, 640 - (titleWidth / 2), 180);
		for (int i = 0; i < splitMessage.length; i++) {
			int messageWidth = g.getFontMetrics().stringWidth(splitMessage[i]);
			g.drawString(splitMessage[i], 640 - (messageWidth / 2), 260+(fontHeight+5)*i);
		}
	}
}