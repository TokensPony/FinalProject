import javagames.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class QTERoom extends RoomData{
	//public char easy = 't';
	//public char[] hard = {'r', 'p', 'n'};
	public float timer = 5;
	public float timeUp = 0;
	
	public int timerPosX = 640;
	public int timerPosY = 100;
	
	public String timerText = "%.2f";
	public String buttonText = "Escape: \'T\'             Continue: \'R\'+\'P\'+\'N\'";
	
	public int easy = KeyEvent.VK_T;
	public int[] hard = {KeyEvent.VK_R, KeyEvent.VK_P, KeyEvent.VK_N};
	
	
	public QTERoom(String filename, String dbType){
		super(filename, dbType);
	}
	
	@Override
	public void updateRoomData(float delta){
		super.updateRoomData(delta);
		if(challengeActive && timer > 0){
			timer -= delta;
		}else if (challengeActive && timer <= 0){
			timer = 0f;
		}
	}
	
	@Override
	public String passKeyboard(KeyboardInput k){
		if(challengeActive){
			if(k.keyDown(hard[0]) && k.keyDown(hard[1]) && k.keyDown(hard[2])){
				challengeActive = false;
				System.out.println("Succeeded");
				return "Succeeded";
			}
			if(k.keyDown(easy)){
				challengeActive = false;
				System.out.println("Escaped");
				return "Escaped";
			}
			if(timer == 0f){
				challengeActive = false;
				System.out.println("Failed");
				return "Failed";
			}
		}
		
		return "";
	}
	
	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp){
		super.renderRoom(g, vp);
		String temp = "";
		temp = temp.format(timerText, timer);
		int sWidth = g.getFontMetrics().stringWidth(buttonText);
		if(challengeActive){
			g.setColor(Color.BLACK);
			g.drawString(temp, timerPosX, timerPosY-1);
			g.drawString(temp, timerPosX+1, timerPosY);
			g.drawString(temp, timerPosX, timerPosY+1);
			g.drawString(temp, timerPosX-1, timerPosY);
			g.setColor(Color.WHITE);
			g.drawString(temp, timerPosX, timerPosY);
			
			g.setColor(Color.BLACK);
			g.drawString(buttonText, 640-(sWidth/2), 200-1);
			g.drawString(buttonText, 640-(sWidth/2)+1, 200);
			g.drawString(buttonText, 640-(sWidth/2), 200+1);
			g.drawString(buttonText, 640-(sWidth/2)-1, 200);
			g.setColor(Color.WHITE);
			g.drawString(buttonText, 640-(sWidth/2), 200);
		}
	}
}