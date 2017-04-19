import javagames.util.*;
import java.awt.*;
import java.util.*;

public class RockChallengeRoom extends RoomData{
	ArrayList<Hazard> rocks = new ArrayList<Hazard>();
	
	public RockChallengeRoom(String filename){
		super(filename);
		type = "Rock";
	}
	
	public RockChallengeRoom(String filename, String dbType){
		super(filename, dbType);
		type = "Rock";
	}
	
	@Override
	public void rockUpdater(float delta){
		System.out.println("It Worked!");
	}
}