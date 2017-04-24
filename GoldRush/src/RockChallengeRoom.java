import javagames.util.*;
import java.awt.*;
import java.util.*;


public class RockChallengeRoom extends RoomData{
	ArrayList<Rock> rocks = new ArrayList<Rock>();
	
	private float rampUp = .5f;
	private float rampUpFuse;
	
	Random r = new Random();
	
	public RockChallengeRoom(String filename){
		super(filename);
		type = "Rock";
	}
	
	public RockChallengeRoom(String filename, String dbType){
		super(filename, dbType);
		type = "Rock";
		//rocks.add(createRock());
		//rocks.add(new Rock(0, 0, "Test", 100));
		//rocks.get(0).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
	}
	
	public Rock createRock(){
		float startPosX = r.nextFloat()*16f - 8f;
		Rock r = new Rock(startPosX, 8, "Test", 100);
		r.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		return r;
	}
	
	//Need values for appWorld Width and height passed in so
	//That the random generation of the rocks will work efficiently
	@Override
	public void updateRoomData(float delta){
		super.updateRoomData(delta);
		if(challengeActive){
			rampUpFuse += delta;
			if(rampUpFuse > rampUp){
				System.out.println("GERONIMO");
				rocks.add(createRock());
				rampUpFuse = 0f;
			}
			
			for(int x = 0; x < rocks.size(); x++){
				rocks.get(x).velocity.y += -3f * delta;
			}
			
			for(int x = 0; x < rocks.size(); x++){
				//rocks.get(x).setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
			}
		
			for(int x = 0; x < rocks.size(); x++){
				rocks.get(x).updateObjects(delta);
			}
		}
	}
	
	@Override
	public void rockUpdater(float delta){
		//System.out.println("It Worked!");
	}
	
	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp){
		if(challengeActive){
			for(int x = 0; x < rocks.size(); x++){
				rocks.get(x).render(g, vp);
			}
		}
	}
	
	@Override
	public void showStuff(){
		for(int x = 0; x < rocks.size(); x++){
			rocks.get(x).greenBorder = !rocks.get(x).greenBorder;
		}
	}
	
	@Override
	public boolean hazardHit(MarioSprite m){
		Vector2f[] box = m.mainBox.getVWorld();
		for(int x = 0; x < rocks.size(); x++){
			if(m.intersectCircleAABB(rocks.get(x).positions, rocks.get(x).radius, box[3], box[1])){
				rocks.remove(x);
				return true;
			}
		}
		return false;
	}
}