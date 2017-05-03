package goldrush.Util;

import java.awt.*;
import java.util.*;

public class VectorObject implements Drawable{
	Matrix3x3f world = Matrix3x3f.identity();
	private Vector2f points[];
	private Vector2f vWorld[];
	private Color c;
	private float scale = 1, scaleStep;
	private float rot, rotStep;
	private float tx, ty;
	private float vx, vy;
	private float sx, sxStep;
	private float sy, syStep;
	
	/*The constructor method sets the color and the array of
	 * Vectors2f's as well as the World array*/
	public VectorObject(Color color, Vector2f shape[]){
		c = color;
		points = shape;
		vWorld = new Vector2f[points.length];
		//rotDelta = (float)Math.toRadians(1);
	}
	
	public VectorObject(VectorObject vo){
		this.world = vo.world;
		this.points = vo.points;
		this.vWorld = vo.vWorld;
		this.c = vo.c;
		this.scale = vo.scale;
		this.scaleStep = vo.scaleStep;
		this.rot = vo.rot;
		this.rotStep = vo.rotStep;
		this.tx = vo.tx;
		this.ty = vo.ty;
		this.vx = vo.vx;
		this.vy = vo.vy;
		this.sx = vo.sx;
		this.sxStep = vo.sxStep;
		this.sy = vo.sy;
		this.syStep = vo.syStep;
	}
	
	/*This method is used to perform the matrix calculations for attributes such
	 * as scale, shear, translation, and rotation. Each time it is called, it refreshes
	 * the world matrix, calculates all the attributes, then applies the matrix onto
	 * a copy of the array of Vector2fs for the object.*/
	public void updateWorld(){
		world = Matrix3x3f.identity();
		//world = world.mul(viewport);
		
		sx += sxStep;
		sy += syStep;
		world = world.mul(Matrix3x3f.shear(sx, sy));
		
		scale+=scaleStep;
		world = world.mul(Matrix3x3f.scale(scale, scale));
		
		rot += rotStep;
		world = world.mul(Matrix3x3f.rotate(rot));
		
		tx += vx;
		ty += vy;
		world = world.mul(Matrix3x3f.translate(tx, ty));
		
		for(int x = 0; x < vWorld.length; x++){
			vWorld[x] = world.mul(points[x]);
		}
	}
	
	/*The render method draws out the lines and sets the color for the vector object
	 * NOTE: This method has been modified from it's previous iterations to now accept
	 * the viewport as a parameter.*/
	public void render(Matrix3x3f viewport, Graphics g){
		for(int x = 0; x < vWorld.length; x++){
			vWorld[x] = viewport.mul(vWorld[x]);
		}
		g.setColor(c);
		Vector2f S = vWorld[vWorld.length - 1];
		//System.out.println(vWorld.length);
		Vector2f P = null;
		for (int i = 0; i < vWorld.length; ++i) {
			P = vWorld[i];
			//System.out.printf("%f %f %f %f\n", S.x, S.y, P.x, P.y);
			g.drawLine((int) S.x, (int) S.y, (int) P.x, (int) P.y);
			S = P;
		}
	}
	
	/*The following list of get and sets are for all of the main variables*/
	public float getTX(){
		return tx;
	}
	
	public void setTX(float x){
		tx = x;
	}
	
	public float getTY(){
		return ty;
	}
	
	public void setTY(float x){
		 ty = x;
	}
	
	public float getVX(){
		return vx;
	}
	
	public void setVX(float x){
		 vx = x;
	}
	
	public float getVY(){
		return vy;
	}
	
	public void setVY(float x){
		 vy = x;
	}
	
	public float getRot(){
		return rot;
	}
	
	public void setRot(float x){
		 rot = x;
	}
	
	public float getRotStep(){
		return rotStep;
	}
	
	public void setRotStep(float x){
		rotStep = x;
	}
	
	public float getScale(){
		return scale;
	}
	
	public void setScale(float x){
		 scale = x;
	}
	
	public float getScaleStep(){
		return scaleStep;
	}
	
	public void setScaleStep(float x){
		 scaleStep = x;
	}
	
	public float getSX(){
		return sx;
	}
	
	public void setSX(float x){
		 sx = x;
	}
	
	public float getSXStep(){
		return sxStep;
	}
	
	public void setSXStep(float x){
		 sxStep = x;
	}
	
	public float getSY(){
		return sy;
	}
	
	public void setSY(float x){
		 sy = x;
	}
	
	public float getSYStep(){
		return syStep;
	}
	
	public void setSYStep(float x){
		 syStep= x;
	}

	public Vector2f[] getVWorld() {
		return vWorld;
	}
}