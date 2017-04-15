import javagames.util.*;
import java.awt.*;
import java.util.ArrayList;

public class RoomData{
	
	ArrayList<WarpTile> wt = new ArrayList<WarpTile>();
	
	public RoomData(){
		
	}
	
	public void addWarpTile(WarpTile w){
		wt.add(w);
	}
}