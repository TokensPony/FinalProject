import javagames.util.*;
import java.awt.*;
import java.util.*;

public class LavaRoom extends RoomData{
	ArrayList<Sprite> logs = new ArrayList<Sprite>();
	public Lava lava = new Lava();
	
	public LavaRoom(String filename){
		super(filename);
	}
	
	@Override
	public void setStuff(int aW, int aH, float aWW, float aWH){
		super.setStuff(aW, aH, aWW, aWH);
		lava.setBB(aW, aH, aWW, aWH);
	}
	
	@Override
	public void updateRoomData(float delta){
		super.updateRoomData(delta);
		lava.updateObjects(delta);
	}
	
	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp){
		lava.render(g, vp);
	}
}