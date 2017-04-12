package javagames.util;

import java.awt.Graphics;

public interface Drawable {
	void updateWorld();
	void render (Matrix3x3f viewport, Graphics g);
}