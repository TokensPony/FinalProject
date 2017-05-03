package goldrush.BoundingShapes;

import java.awt.Graphics2D;

import goldrush.Util.Matrix3x3f;
import goldrush.Util.Vector2f;;


public class AABB extends BoundingShape {

	Vector2f min; //min x and y (bottom left coordinate)
	Vector2f max; //max x and y (top right coordinate)
	
	//creates a new AABB object from another AABB
	AABB(AABB copy)
	{
		min = new Vector2f(copy.min);
		max = new Vector2f(copy.max);
	}
	
	AABB(Vector2f minimum, Vector2f maximum)
	{
		min = new Vector2f(minimum);
		max = new Vector2f(maximum);
	}
	
	AABB(float left, float bottom, float right, float top)
	{
		min = new Vector2f(left, bottom);
		max = new Vector2f(right, top);
	}

	@Override
	public boolean collidesWith(BoundingShape bs) {
		if (bs instanceof AABB)
			return collidesWith((AABB) bs);
		else if (bs instanceof BoundingCircle)
			return collidesWith((BoundingCircle) bs);
		else
			return false;
		
	}
	
	public boolean collidesWith(AABB bb)
	{
		if (this.min.x > bb.max.x || this.max.x < bb.min.x || this.min.y > bb.max.y || this.max.y < bb.min.y) 
			return false;
		
		return true;
	}
	
	public boolean collidesWith(BoundingCircle bc)
	{
		float dx = 0;
		if (bc.center.x < this.min.x) dx = bc.center.x - this.min.x;
		if (bc.center.x > this.max.x) dx = bc.center.x - this.max.x;
		
		float dy = 0;
		if (bc.center.y < this.min.y) dy = bc.center.y - this.min.y;
		if (bc.center.y > this.max.y) dy = bc.center.y - this.max.y;
		
		float d = dx*dx + dy*dy;
		
		return d < bc.radius * bc.radius;
	}

	@Override
	public void render(Graphics2D g, Matrix3x3f viewport) {
		g.setColor(boundsColor);
		Vector2f topleft = new Vector2f(min.x, max.y);
		Vector2f bottomright = new Vector2f(max.x, min.y);
		topleft = viewport.mul(topleft);
		bottomright = viewport.mul(bottomright);
		Vector2f size = bottomright.sub(topleft);
		g.drawRect((int)topleft.x, (int)topleft.y, (int)size.x, (int)size.y);
		
	}

	@Override
	public boolean pointInside(Vector2f point) {
		
		if (point.x < min.x || point.x > max.x || point.y < min.y || point.y > max.y)
			return false;
		
		return true;
	}

}
