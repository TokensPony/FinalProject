import javagames.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class QTERoom extends RoomData{
	//public char easy = 't';
	//public char[] hard = {'r', 'p', 'n'};
	public float timer = 3;
	public float timeUp = 0;
	
	public String timerText = "%.2f";
	
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
				return "Suceeded";
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
		String temp = "";
		temp = temp.format(timerText, timer);
		if(challengeActive){
			g.drawString(temp, 640, 100);
		}
	}
}