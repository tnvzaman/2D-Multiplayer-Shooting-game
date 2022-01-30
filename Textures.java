package com.game.src.main;

import java.awt.image.BufferedImage;

public class Textures {

	public BufferedImage player, missile, enemy;
	
	private SpriteSheet ss;
	
	
	public Textures(Game game)
	{
	    this.ss= new SpriteSheet(game.getSpriteSheet());
		getTextures();
	}
	
	private void getTextures()
	{
		
			player = ss.grabImage(1, 1, 32, 32);
		missile = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(3, 1, 32, 32);
		
	}

    public void getSkin1()
    {
    	this.player = ss.grabImage(4, 1, 32, 32);
    }
    
    public void getSkin2()
    {
    	this.player = ss.grabImage(5, 1, 32, 32);
    }
    	

	
	
	
}
