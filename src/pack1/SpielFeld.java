package pack1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpielFeld extends JPanel {
	private JFrame jf1;
	private int screenwidth = 1000, screenheight = 1000;
	private Spieler sp = new Spieler(60, screenheight - 200, 35, 35);
	
	private BufferedReader bf;
	private Vector<Block> bl;
	private Vector<Gegner> gegner;

	public SpielFeld() {
		bl = new Vector<>();
		gegner=new Vector<>();
		// Standarteinstellungen
		jf1 = new JFrame("SecretAgency");
		jf1.setSize(screenwidth, screenheight);
		jf1.setLocationRelativeTo(null);
		jf1.setVisible(true);
		jf1.setResizable(false);
		// Draw Klasse aufrufen
		jf1.add(this);
		// KeyListener zum JFrame hinzufügen
		jf1.addKeyListener(sp);

		new javax.swing.Timer(1000 / 60, e -> update()).start();
	}

	public void paint(Graphics g) {
		if (bl.size() == 0&&gegner.size()==0)
			readFeld();
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, screenwidth, screenheight);
		// Spieler einzeichnen
		sp.drawPlayer(g2);
		// Block einzeichnen
		for (Block b : bl)
			b.paint(g2);
		for (Gegner geg : gegner){
			geg.paint(g2);
			
		}
	}
	

	private double getFieldWidth() {
		return this.getWidth() / 25.0;
	}

	private double getFieldHeight() {
		return this.getHeight() / 25.0;
	}

	public int getScreenwidth() {
		return screenwidth;
	}

	public int getScreenheight() {
		return screenheight;
	}

	public Block Begrenzung(int x, int y) {
		double w = getFieldWidth();
		double h = getFieldHeight();
		return new Block((int) (x * w), (int) (y * h), 40, 40);
	}

	public void readFeld() {
		String line;
		int zeile = 0;

		try {
			bf = new BufferedReader(new FileReader(getResourceFromName("CreateLevel.txt")));
			while ((line = bf.readLine()) != null) {
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == '*') {
						Block b = Begrenzung(i, zeile);
						bl.add(b);
					}else if(line.charAt(i)=='_'){
						Gegner ge=new Gegner(i*40.0f, zeile*40.0f, 35, 35);
						gegner.add(ge);
					}
				
				}
				zeile++;
			}
			bf.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private File getResourceFromName(String name) {
		URL url = ClassLoader.getSystemResource("src/resources/" + name);
		if (url == null) {
			return new File("src/resources/" + name);
		} else {
			try {
				return new File(url.toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public boolean collision(Spieler sp) {
		for (Block b : bl) {
			if (b.isKollidiertSpieler(sp))
				return true;

		}
		return false;
	}

	public void update() {

		sp.updatePlayer(collision(sp));
		repaint();
	}

}
