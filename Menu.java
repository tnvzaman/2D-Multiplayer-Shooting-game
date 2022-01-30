package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Game.WIDTH/2+120,150,100,50); 
	public Rectangle fortressButton = new Rectangle(Game.WIDTH/2+120,230,100,50); 
	public Rectangle helpButton = new Rectangle(Game.WIDTH/2+120,310,100,50); 
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2+120,390,100,50); 

	
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		Font fnt = new Font("Haettenschweiler", Font.BOLD, 50);
		g.setFont(fnt);
		g.setColor(Color.black);
		g.drawString("DOOM \n SLAYER'S STAND", Game.WIDTH/2, 100);
		
		Font fntp = new Font("Haettenschweiler", Font.BOLD, 30);
		g.setFont(fntp);
		g.drawString("Play", playButton.x+20, playButton.y+30);
		
		Font fntf = new Font("Haettenschweiler", Font.BOLD, 30);
		g.setFont(fntp);
		g.drawString("Fortess", fortressButton.x+15, fortressButton.y+30);
		
		Font fnth = new Font("Haettenschweiler", Font.BOLD, 30);
		g.setFont(fnth);
		g.drawString("Help", helpButton.x+20, helpButton.y+30);
		
		Font fntq = new Font("Haettenschweiler", Font.BOLD, 30);
		g.setFont(fntq);
		g.drawString("Quit", quitButton.x+20, quitButton.y+30);
		
		g2d.draw(playButton);
		g2d.draw(fortressButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
	}

}
