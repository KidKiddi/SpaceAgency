package pack1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.Timer;

public class Gegner {
	private float x, y, width, height;
	private int ranx = 0, rany = 0;

	public Gegner(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		new javax.swing.Timer(5000, e -> randomNummer()).start();
		new javax.swing.Timer(1000 / 60, e -> updateBewegung()).start();

	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void paint(Graphics2D g2) {
		g2.setColor(Color.YELLOW);
		g2.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	public void updateBewegung() {
		if (y > 920)
			y=920;
		if (y < 40)
			y =40;
		if (x > 920)
			x = 920;
		if (x < 40)
			x = 40;
		x -= ranx;
		y -= rany;
	}

	public void randomNummer() {
		Random rndx = new Random();
		Random rndy = new Random();

		ranx = rndx.nextInt(7) - 3;
		rany = rndy.nextInt(7) - 3;
		System.out.println("x: " +ranx + " y:" + rany);
	}

}
