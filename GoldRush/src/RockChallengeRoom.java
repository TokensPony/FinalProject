import javagames.util.*;
import java.awt.*;
import java.util.*;

public class RockChallengeRoom extends RoomData{
	ArrayList<Hazard> rocks = new ArrayList<Hazard>();
	
	public RockChallengeRoom(String filename){
		super(filename);
	}
	
	public RockChallengeRoom(String filename, String dbType){
		super(filename, dbType);
	}
}