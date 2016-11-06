package ua.ronaldo173.mypackage.lab2;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/*
 * The PolyLine class model a line made up of many points
 */
public class PolyLine {
	private List<Integer> xList; // List of x-coord
	private List<Integer> yList; // List of y-coord

	// Constructor
	public PolyLine() {
		xList = new ArrayList<Integer>();
		yList = new ArrayList<Integer>();
	}

	// Add a point to this PolyLine
	public void addPoint(int x, int y) {
		xList.add(x);
		yList.add(y);
	}

	// This PolyLine paints itself given the Graphics context
	public void draw(Graphics g) { // draw itself
		for (int i = 0; i < xList.size() - 1; ++i) {
			g.drawLine((int) xList.get(i), (int) yList.get(i), (int) xList.get(i + 1), (int) yList.get(i + 1));
		}
	}
}