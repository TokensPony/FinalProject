package goldrush.Maze;
import java.awt.*;
import java.util.*;

import goldrush.Util.*;

public class RockChallengeRoom extends RoomData {
	ArrayList<Rock> rocks = new ArrayList<Rock>();

	private float rampUp = .5f;
	private float rampUpFuse;

	Random r = new Random();

	public RockChallengeRoom(String filename) {
		super(filename);
		type = "Rock";
	}

	public RockChallengeRoom(String filename, String dbType) {
		super(filename, dbType);
		type = "Rock";
	}

	public Rock createRock() {
		float startPosX = r.nextFloat() * 16f - 8f;
		Rock r = new Rock(startPosX, 8);
		r.setBB(appWidth, appHeight, appWorldWidth, appWorldHeight);
		r.rumble.play();
		return r;
	}

	// Need values for appWorld Width and height passed in so
	// That the random generation of the rocks will work efficiently
	@Override
	public void updateRoomData(float delta) {
		super.updateRoomData(delta);
		if (challengeActive) {
			rampUpFuse += delta;
		}
		if (rampUpFuse > rampUp && challengeActive) {
			System.out.println("GERONIMO");
			rocks.add(createRock());
			rampUpFuse = 0f;
		}

		if (!rocks.isEmpty()) {
			for (int x = 0; x < rocks.size(); x++) {
				rocks.get(x).velocity.y += -3f * delta;
			}

			for (int x = 0; x < rocks.size(); x++) {
			}

			for (int x = 0; x < rocks.size(); x++) {
				rocks.get(x).updateObjects(delta);
			}
		}
		if (items.isEmpty()) {
			challengeActive = false;
			for (int x = 0; x < wt.size(); x++) {
				wt.get(x).active = true;
				wt.get(x).currentSprite = wt.get(x).temp;
			}
			if(db != null){
				if(currentDB < db.size()-1){
					showNextDB();
				}
			}
		}
	}

	@Override
	public void rockUpdater(float delta) {
	}

	@Override
	public void renderRoom(Graphics g, Matrix3x3f vp) {
		if (!rocks.isEmpty()) {
			for (int x = 0; x < rocks.size(); x++) {
				rocks.get(x).render(g, vp);
			}
		}
		super.renderRoom(g, vp);
	}

	@Override
	public void showStuff() {
		for (int x = 0; x < rocks.size(); x++) {
			rocks.get(x).greenBorder = !rocks.get(x).greenBorder;
		}
	}

	@Override
	public boolean hazardHit(MarioSprite m) {
		Vector2f[] box = m.mainBox.getVWorld();
		for (int x = 0; x < rocks.size(); x++) {
			if (m.intersectCircleAABB(rocks.get(x).positions, rocks.get(x).radius, box[3], box[1])) {
				rocks.get(x).hit.play();
				rocks.remove(x);
				return true;
			}
		}
		return false;
	}
}