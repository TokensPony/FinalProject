package goldrush.Maze;

import java.awt.*;
import java.awt.event.KeyEvent;

import goldrush.Util.*;

public class QTERoom extends RoomData {
	// Time variables
	public float timer = 5;
	public float timeUp = 0;

	// Timer Text Positions
	public int timerPosX = 640;
	public int timerPosY = 100;

	// Timer and command strings
	public String timerText = "%.2f";
	public String buttonText = "Escape: \'T\'             Continue: \'R\'+\'P\'+\'N\'";

	// Key combinations for the easy route and the hard route
	public int easy = KeyEvent.VK_T;
	public int[] hard = { KeyEvent.VK_R, KeyEvent.VK_P, KeyEvent.VK_N };

	public QTERoom(String filename, String dbType) {
		super(filename, dbType);
	}

	@Override
	public void updateRoomData(float delta) {
		// Update the countdown timer when the challenge is active
		super.updateRoomData(delta);
		if (challengeActive && timer > 0) {
			timer -= delta;
		} else if (challengeActive && timer <= 0) {
			timer = 0f;
		}
	}

	/*
	 * Checks the input of the keyboard against the given acceptable button
	 * combinations to see if the input matches any of them, or if the player
	 * was able to do anything in time.
	 */
	@Override
	public String passKeyboard(KeyboardInput k) {
		if (challengeActive) {
			if (k.keyDown(hard[0]) && k.keyDown(hard[1]) && k.keyDown(hard[2])) {
				challengeActive = false;
				for (int x = 0; x < wt.size(); x++) {
					wt.get(x).currentSprite = wt.get(x).temp;
				}
				System.out.println("Succeeded");
				return "Succeeded";
			}
			if (k.keyDown(easy)) {
				challengeActive = false;
				System.out.println("Escaped");
				return "Escaped";
			}
			if (timer == 0f) {
				challengeActive = false;
				System.out.println("Failed");
				return "Failed";
			}
		}

		return "";
	}

	/*
	 * Renders the text for the Timer and the input commands, it prints the
	 * sprint multiple times to create a boarder effect
	 */
	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp) {
		super.renderRoom(g, vp);
		String temp = "";
		temp = String.format(timerText, timer);
		int sWidth = g.getFontMetrics().stringWidth(buttonText);
		if (challengeActive) {
			g.setColor(Color.BLACK);
			g.drawString(temp, timerPosX, timerPosY - 1);
			g.drawString(temp, timerPosX + 1, timerPosY);
			g.drawString(temp, timerPosX, timerPosY + 1);
			g.drawString(temp, timerPosX - 1, timerPosY);
			g.setColor(Color.WHITE);
			g.drawString(temp, timerPosX, timerPosY);

			g.setColor(Color.BLACK);
			g.drawString(buttonText, 640 - (sWidth / 2), 200 - 1);
			g.drawString(buttonText, 640 - (sWidth / 2) + 1, 200);
			g.drawString(buttonText, 640 - (sWidth / 2), 200 + 1);
			g.drawString(buttonText, 640 - (sWidth / 2) - 1, 200);
			g.setColor(Color.WHITE);
			g.drawString(buttonText, 640 - (sWidth / 2), 200);
		}
	}
}