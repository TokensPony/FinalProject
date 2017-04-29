import javagames.util.*;
import java.awt.*;
import java.util.*;

public class LavaRoom extends RoomData{
	ArrayList<Log> logs = new ArrayList<Log>();
	public Lava lava = new Lava();
	
	boolean initialized = false;
	
	public LavaRoom(String filename){
		super(filename);
		logs.add(new Log(-10f,-2.3f,1.5f));
		logs.add(new Log(10f, -.8f, -2f));
		logs.add(new Log(-10f, .75f, 2.5f));
		logs.add(new Log(10f, 2.25f,-3f));
	}
	
	public LavaRoom(String filename, String dbType){
		super(filename, dbType);
		type = dbType;
		logs.add(new Log(-10f,-2.3f,1.5f));
		logs.add(new Log(10f, -.8f, -2f));
		logs.add(new Log(-10f, .75f, 2.5f));
		logs.add(new Log(10f, 2.25f,-3f));
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
			}else if(logs.get(x).velocity.x < 0 &&
					logs.get(x).positions.x < -10f){
				logs.get(x).positions.x = 10f;
			}
		}
	}
	
	@Override
	public float onLog(MarioSprite m){
		for(int x = 0; x < logs.size(); x++){
			if(initialized){
				if(logs.get(x).rRI(m.subBox.get(0))){
					//System.out.printf("On the Log: %f\n", logs.get(x).velocity.x);
					return logs.get(x).velocity.x;
				}
			}else{
				initialized = true;
				return 0f;
			}
		}
		return 0f;
	}
	
	@Override
	public boolean hazardHit(MarioSprite m){
		boolean hit = false;
		for(int x = 0; x < logs.size(); x++){
			//if (!hit) {
			//	continue;
			//}
			if(lava.rRI(m.subBox.get(0)) &&
					!logs.get(0).rRI(m.subBox.get(0)) && !logs.get(1).rRI(m.subBox.get(0))
					&& !logs.get(2).rRI(m.subBox.get(0)) && !logs.get(3).rRI(m.subBox.get(0))){
				//return true;
				hit = true;
			}
		}
		return hit;
	}
	
	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp){
		lava.render(g, vp);
		for(int x = 0; x < logs.size(); x++	){
			logs.get(x).render(g, vp);
		}
	}
}