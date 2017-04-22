import javagames.util.*;
import java.awt.*;
import java.util.*;

public class RockChallengeRoom extends RoomData{
	ArrayList<Rock> rocks = new ArrayList<Rock>();
	
	public RockChallengeRoom(String filename){
		super(filename);
		type = "Rock";
	}
	
	public RockChallengeRoom(String filename, String dbType){
		super(filename, dbType);
		type = "Rock";
		rocks.add(new Rock(0, 0, "Test", 100));
		//rocks.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
	}
	
	
	//Need values for appWorld Width and height passed in so
	//That the random generation of the rocks will work efficiently
	@Override
	public void updateRoomData(float delta){
		super.updateRoomData(delta);
		rocks.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		for(int x = 0; x < rocks.size(); x++){
			rocks.get(x).updateObjects(delta);
		}
		
	}
	
	@Override
	public void rockUpdater(float delta){
		//System.out.println("It Worked!");
	}
	
	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp){
		rocks.get(0).render(g, vp);
	}
}