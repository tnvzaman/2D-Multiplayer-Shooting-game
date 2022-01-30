package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fortress {

	public Rectangle skin1 = new Rectangle(100,100,140,140); 
	public Rectangle skin2 = new Rectangle(250,100,140,140); 
	public Rectangle backButton = new Rectangle(450,350,100,50);
	
	private BufferedImage img1=null;
	private BufferedImage img2=null;
	
	public void render(Graphics g, Game game)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
		img1= loader.loadImage("/doomskin1.png");
		img2=loader.loadImage("/doomskin2.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(img1, 110, 110,null);
		g.drawImage(img2, 260, 110,null);
		Font fnt = new Font("Haettenschweiler", Font.BOLD, 20);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("SENTINEL : 10 S", 120, 260);
		g.drawString("-7 Damage", 120, 280);
		g.drawString("DARK LORD : 20 S", 270, 260);
		g.drawString("-15 Damage", 270, 280);
		Font fnts = new Font("Haettenschweiler", Font.BOLD, 30);
		g.setColor(Color.yellow);
		g.drawString(game.getHighScore()+" S", 500, 50);
		

		g.setColor(Color.black);
		Font fntp = new Font("Haettenschweiler", Font.BOLD, 30);
		g.setFont(fntp);
		g.drawString("Back", backButton.x+20, backButton.y+30);
		
		g2d.draw(skin1);
		g2d.draw(skin2);
		g2d.draw(backButton);
	}
}
