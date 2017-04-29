import javagames.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class QTERoom extends RoomData{
	//public char easy = 't';
	//public char[] hard = {'r', 'p', 'n'};
	
	public int easy = KeyEvent.VK_T;
	public int[] hard = {KeyEvent.VK_R, KeyEvent.VK_P, KeyEvent.VK_N};
	
	
	public QTERoom(String filename, String dbType){
		super(filename, dbType);
	}
	
	@Override
	public void passKeyboard(KeyboardInput k){
		if(k.keyDown(hard[0]) && k.keyDown(hard[1]) && k.keyDown(hard[2])){
			
		}
	}
	
	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp){
		
	}
}