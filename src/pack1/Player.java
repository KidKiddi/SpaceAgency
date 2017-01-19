package pack1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Player implements KeyListener {

	private boolean bup = false, bleft = false, bdown = false, bright = false;
	private int posx, posy, x, y;
	private int screenwidth, screenheight;

	

	public Player(int posx, int posy, int x, int y, int screenwidth, int screenheight) {
		this.posx = posx;
		this.posy = posy;
		this.x = x;
		this.y = y;
		this.screenwidth = screenwidth;
		this.screenheight = screenheight;

	}

	public boolean getBup() {
		return bup;
	}

	public boolean getBleft() {
		return bleft;
	}

	public boolean getBdown() {
		return bdown;
	}

	public boolean getBright() {
		return bright;
	}

	public void paint(Graphics g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect(getPosx(), getPosy(), getX(), getY());

	}

	public int getPosx() {
		return posx;
	}

	public int getPosy() {
		return posy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void update(int kollX,int kollY) {
		//System.out.println(kollX+" "+kollY);
		if (posx < kollX + 40 && posx + 40 > kollX && posy < kollY + 40 && posy + 40 > kollY) {
			posx = kollX + 40;
		}
		if (posy > 40 && getBup())
			posy -= 5;
		if (posy < screenheight - 110 && getBdown())
			posy += 5;
		if (posx > 40 && getBleft())
			posx -= 5;
		if (posx < screenwidth - 80 & getBright())
			posx += 5;
	}



	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			bup = true;
		if (e.getKeyCode() == KeyEvent.VK_S)
			bdown = true;
		if (e.getKeyCode() == KeyEvent.VK_A)
			bleft = true;
		if (e.getKeyCode() == KeyEvent.VK_D)
			bright = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			bup = false;
		if (e.getKeyCode() == KeyEvent.VK_S)
			bdown = false;
		if (e.getKeyCode() == KeyEvent.VK_A)
			bleft = false;
		if (e.getKeyCode() == KeyEvent.VK_D)
			bright = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
