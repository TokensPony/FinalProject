package goldrush.BoundingShapes;

import java.awt.Color;
import java.awt.Graphics2D;

import goldrush.Util.Matrix3x3f;
import goldrush.Util.Vector2f;

public abstract class BoundingShape {
	
	//color to draw the bounds in
	public Color boundsColor = Color.green;

	public abstract boolean collidesWith(BoundingShape bs);
	public abstract boolean collidesWith(AABB bb);
	public abstract boolean collidesWith(BoundingCircle bc);
	public abstract boolean pointInside(Vector2f point);
	
	public abstract void render(Graphics2D g, Matrix3x3f view);
}
