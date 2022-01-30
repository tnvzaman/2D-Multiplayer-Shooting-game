package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameOver {

	public Rectangle menuButton = new Rectangle(450,350,100,50);
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		Font fnt = new Font("Haettenschweiler", Font.BOLD, 70);
		g.setFont(fnt);
		g.setColor(Color.red);
		g.drawString("GAME OVER", Game.WIDTH/2, 240);
	
		Font fntp = new Font("Haettenschweiler", Font.BOLD, 30);
		g.setFont(fntp);
		g.drawString("Menu", menuButton.x+20, menuButton.y+30);
		
		g2d.draw(menuButton);
	}
}
