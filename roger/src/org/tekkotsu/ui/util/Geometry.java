package org.tekkotsu.ui.util;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class Geometry {
	public static Point center(Rectangle r) {
		return new Point(r.x+r.width/2,r.y+r.height/2);
	}
	
	public static Point center(Point a, Point b) {
		return new Point(a.x/2+b.x/2,a.y/2+b.y/2);
	}
}
