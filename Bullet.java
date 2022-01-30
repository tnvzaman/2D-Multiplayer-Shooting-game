package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;

public class Bullet extends GameObject implements EntityA {
	
	
	
	private Textures tex;
	
	public Bullet(double x, double y, Textures tex)
	{
		super(x, y);
		this.tex=tex;
		
		
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void tick()
	{
		x+=10;
		
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(tex.missile, (int)x, (int)y, null);
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
		
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		
	}

}
