package pack1;

import java.awt.Color;
import java.awt.Graphics;

public class Block {
	private float x, y, width, height;

	public Block(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isKollidiertSpieler(Spieler p) {
		if (p.getX() < x + 40 && p.getX() + 40 > x && p.getY() < y + 40 && p.getY() + 40 > y)
			return true;
		return false;

	}

	public void paint(Graphics g) {
		g.setColor(new Color(235, 235, 230));
		g.fillRect((int)x,(int) y,(int) width,(int) height);

	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
}
