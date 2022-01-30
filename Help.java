package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Help {
	public Rectangle backButton = new Rectangle(450,350,100,50);
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		Font fnt = new Font("Haettenschweiler", Font.BOLD, 20);
		g.setFont(fnt);
		g.setColor(Color.red);
		g.drawString("You are playing as the great DOOM SLAYER on his last stand against the demons of hell.", 10, 100);
		g.drawString(" The only barrier between earth and hell is YOU.", Game.WIDTH/2, 100+20);
		g.drawString("Press arrow keys to move and spacebar to shoot.", Game.WIDTH/2, 100+40);
		g.drawString("You will lose health if you collide with the demons", Game.WIDTH/2, 100+60);
		g.drawString("You will lose health if demons get past you and reach earth", Game.WIDTH/2, 100+80);

		
		g.drawString("In the Fortress, you can buy skins with your S (Highscore points)", 100, 200+20);
		

		
		Font fntp = new Font("Haettenschweiler", Font.BOLD, 30);
		g.setFont(fntp);
		g.drawString("Back", backButton.x+20, backButton.y+30);
		
		g2d.draw(backButton);
	}

}
