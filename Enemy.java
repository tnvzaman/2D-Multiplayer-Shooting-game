package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

public class Enemy extends GameObject implements EntityB {
	
	
	
	private Textures tex;
	
	private Random r = new Random();
	private Game game;
	private Controller c;
	private Sound snd;
	private int speed = r.nextInt(5)+1;
	
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game)
	{
		super(x, y);
		this.tex=tex;
		this.c=c;
		this.game=game;
		snd = new Sound();
	}
	
	public void tick()
	{
		x -=speed;
		if(x<=0)
		{
			x=640;
			y=r.nextInt(Game.HEIGHT*Game.SCALE);
			game.HEALTH-=0;
			if(game.HEALTH<=0)
			{
				game.State=game.State.GAMEOVER;
			}
		}
		
		for(int i=0; i<game.ea.size();i++)
		{
			EntityA tempEnt = game.ea.get(i);
			
		
		
		if(Physx.Collision(this, tempEnt))
		{
			snd.setFile("D:\\Class\\Workspace\\ShootingGame\\res\\enemydie.wav");
			snd.play();
			c.removeEntity(tempEnt);
			c.removeEntity(this);
			game.CalScore();
			game.setEnemy_killed(game.getEnemy_killed()+1);
		}
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(tex.enemy, (int)x, (int)y, null);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	public void setY(double y)
	{
		this.y=y;
	}
	public void setX(double x)
	{
		this.x=x;
	}
	

}
