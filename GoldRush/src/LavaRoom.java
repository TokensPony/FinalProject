import javagames.util.*;
import java.awt.*;
import java.util.*;

public class LavaRoom extends RoomData{
	ArrayList<Log> logs = new ArrayList<Log>();
	public Lava lava = new Lava();
	
	public LavaRoom(String filename){
		super(filename);
		logs.add(new Log(-10f,-2.3f,2f));
	}
	
	@Override
	public void setStuff(int aW, int aH, float aWW, float aWH){
		super.setStuff(aW, aH, aWW, aWH);
		lava.setBB(aW, aH, aWW, aWH);
		for(int x = 0; x < logs.size(); x++	){
			logs.get(x).setBB(aW, aH, aWW, aWH);
		}
	}
	
	@Override
	public void updateRoomData(float delta){
		super.updateRoomData(delta);
		lava.updateObjects(delta);
		for(int x = 0; x < logs.size(); x++	){
			logs.get(x).updateObjects(delta);
			if(logs.get(x).velocity.x > 0 &&
				logs.get(x).positions.x > 10f){
				logs.get(x).positions.x = -10f;
			}
		}
	}
	
	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp){
		lava.render(g, vp);
		for(int x = 0; x < logs.size(); x++	){
			logs.get(x).render(g, vp);
		}
	}
}