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

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Spielfeld extends JPanel {

	private int screenwidth = 1000, screenheight = 1000;
	private int x = 40, y = screenheight - 110;
	private int kollX,kollY;
	private Player player = new Player(x, y, 40, 40, screenwidth, screenheight);
	private JFrame jf1;

	public Spielfeld() {
		jf1 = new JFrame();
		jf1.setSize(screenwidth, screenheight);
		jf1.setTitle("Secret Agency");
		jf1.setLocationRelativeTo(null);
		jf1.setVisible(true);
		jf1.setResizable(false);
		jf1.addKeyListener(player);
		//paint Methode aufrufen
		jf1.add(this);
		//jede 16 Millisekunden(60 FPS) prüfen, ob eine Taste gedrückt wird
		new javax.swing.Timer(1000/60, e -> update()).start();

	}

	// Zeichnen
	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		super.paintComponents(g2);
		// Hintergrund färben
		g2.setColor(Color.BLACK);
		// Erstellen des Feldes
		g2.fillRect(0, 0, screenwidth, screenheight);
		// Erstellen des Spielers
		player.paint(g2);
		// Begrenzung einzeichnen
		readRechtecke(g2);

	}

	public void Begrenzung(Graphics g2, int posx, int posy) {
	//	System.out.println("Spalte:"+posx+" Zeile:"+posy);
//		System.out.println(kollX+" "+kollY);
		double w = getFieldWidth();
		double h = getFieldHeight();
		g2.fillRect((int)(posx * w), (int)(posy * h), 40, 40);
	

	}
	
	private double getFieldWidth(){
		return this.getWidth()/25.0;
	}
	private double getFieldHeight(){
		return this.getHeight()/25.0;
	}

	public void readRechtecke(Graphics g2) {
		try {
			g2.setColor(Color.GREEN);
			// Aus einer txt-Datei Zeichen ablesen und wenn an der Stelle ein
			// "*" erkannt wird, ensprechend Blöcke platzieren
			String line;
			int zeile = 0;

			BufferedReader bf = new BufferedReader(new FileReader(getResourceFromName("CreateLevel.txt")));

			while ((line = bf.readLine()) != null) {
				for (int j = 0; j < line.length(); j++) {
					if (line.charAt(j) == '*') {
						
						Begrenzung(g2, j, zeile);
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
	
	private File getResourceFromName(String name){
		URL url = ClassLoader.getSystemResource("src/resources/" + name);
		if(url == null){
			return new File("src/resources/"+name);
		}
		else{
			try {
				return new File(url.toURI());
			} catch (URISyntaxException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public void update() {
		player.update(kollX*40,kollY*40);
		repaint();
	}

}
