import javagames.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class QTERoom extends RoomData{
	//public char easy = 't';
	//public char[] hard = {'r', 'p', 'n'};
	
	public int easy = KeyEvent.VK_T;
	public int[] hard = {KeyEvent.VK_R};
	
	
	public QTERoom(String filename, String dbType){
		super(filename, dbType);
	}
	
	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp){
		
	}
}