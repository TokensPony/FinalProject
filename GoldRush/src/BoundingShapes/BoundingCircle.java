package BoundingShapes;

import java.awt.Graphics2D;

import javagames.util.Matrix3x3f;
import javagames.util.Vector2f;


public class BoundingCircle extends BoundingShape {

	float radius;
	Vector2f center;
	
	BoundingCircle(Vector2f c, float r)
	{
		radius = r;
		center = c;
	}
	
	BoundingCircle(BoundingCircle copy)
	{
		radius = copy.radius;
		center = copy.center;
	}


	public boolean collidesWith(BoundingShape bs) {
		if (bs instanceof BoundingCircle)
			return collidesWith((BoundingCircle)bs); 
		else if (bs instanceof AABB)
			return collidesWith((AABB)bs);
		else
			return false;
	}
	
	public boolean collidesWith(BoundingCircle bc)
	{
		float dx = (this.center.x - bc.center.x);
		float dy = (this.center.y - bc.center.y);
		float distsqrd = dx * dx + dy * dy;
		
		if (distsqrd > (this.radius + bc.radius)*(this.radius + bc.radius))
			return false;
		else
			return true;
	}
	
	public boolean collidesWith(AABB bb)
	{
		float dx = 0;
		if (this.center.x < bb.min.x) dx = this.center.x - bb.min.x;
		if (this.center.x > bb.max.x) dx = this.center.x - bb.max.x;
		
		float dy = 0;
		if (this.center.y < bb.min.y) dy = this.center.y - bb.min.y;
		if (this.center.y > bb.max.y) dy = this.center.y - bb.max.y;
		
		float d = dx*dx + dy*dy;
		
		return d < this.radius * this.radius;
	}


	public void render(Graphics2D g, Matrix3x3f view) {
		Vector2f topleft = new Vector2f(center.x - radius, center.y + radius);
		Vector2f bottomright = new Vector2f(center.x + radius, center.y - radius);
		topleft = view.mul(topleft);
		bottomright = view.mul(bottomright);
		Vector2f size = bottomright.sub(topleft);
		g.setColor(this.boundsColor);
		g.drawOval((int)topleft.x, (int)topleft.y, (int)size.x, (int)size.y);
		
	}


	@Override
	public boolean pointInside(Vector2f point) {
		
		float dx = center.x - point.x;
		float dy = center.y - point.y;
		
		float dsqrd = dx*dx + dy*dy;
		
		
		return dsqrd <= radius * radius;
	}
	

}
