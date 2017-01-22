package pack1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Spieler implements KeyListener {
	private float x, y, width, height;
	private boolean booleanUp = false, booleanDown = false, booleanRight = false, booleanLeft = false;
	private boolean lbooleanUp = false, lbooleanDown = false, lbooleanRight = false, lbooleanLeft = false;
	public Spieler(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void drawPlayer(Graphics g2) {
		g2.setColor(Color.WHITE);
		g2.fillRect((int) getX(), (int) getY(), (int) width, (int) height);
	}

	public void updatePlayer(boolean collision) {
		if(collision){
			booleanUp&=!lbooleanUp;
			booleanDown&=!lbooleanDown;
			booleanRight&=!lbooleanRight;
			booleanLeft&=!lbooleanLeft;
			
		}else{
			lbooleanUp=booleanUp;
			lbooleanDown=booleanDown;
			lbooleanRight=booleanRight;
			lbooleanLeft=booleanLeft;
	
		
		}
		
		
		
		
		if (getBooleanUp())
			y -= 5;
		if (getBooleanDown())
			y += 5;
		if (getBooleanLeft())
			x -= 5;
		if (getBooleanRight())
			x += 5;
	}

	public boolean getBooleanUp() {
		return booleanUp;
	}

	public boolean getBooleanLeft() {
		return booleanLeft;
	}

	public boolean getBooleanDown() {
		return booleanDown;
	}

	public boolean getBooleanRight() {
		return booleanRight;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			booleanUp = true;
		if (e.getKeyCode() == KeyEvent.VK_S)
			booleanDown = true;
		if (e.getKeyCode() == KeyEvent.VK_A)
			booleanLeft = true;
		if (e.getKeyCode() == KeyEvent.VK_D)
			booleanRight = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W)
			booleanUp = false;
		if (e.getKeyCode() == KeyEvent.VK_S)
			booleanDown = false;
		if (e.getKeyCode() == KeyEvent.VK_A)
			booleanLeft = false;
		if (e.getKeyCode() == KeyEvent.VK_D)
			booleanRight = false;

	}

	// Unnötig
	@Override
	public void keyTyped(KeyEvent e) {
	}

}
