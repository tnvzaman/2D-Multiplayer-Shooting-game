package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.classes.EntityC;
import com.game.src.main.net.packets.Packet02Move;
import com.game.src.main.net.packets.Packet03Bullet;

public class Player extends GameObject implements EntityC {
	
	
	
	
	
	private Textures tex;
	private String username;
	private Game game;
	private Controller c;
	private KeyboardInput input;
	private Sound snd;
	
	
	
	public Player(double x, double y, Textures tex, String username, Game game, Controller c, KeyboardInput input)
	{

        super(x,y);
		this.tex=tex;
		this.username=username;
		this.game = game;
		this.c=c;
		this.input=input;
		snd = new Sound();
		
		
		
	}
	
	public void tick()
	{
		if (input != null) {
            if (input.up.isPressed()) {
                y-=5;
            }
            if (input.down.isPressed()) {
                y+=5;
            }
            if (input.left.isPressed()) {
                x-=5;
            }
            if (input.right.isPressed()) {
                x+=5;
            }
            if(input.space.isPressed() && !input.isShooting)
            {
            	input.isShooting=true;
            	snd.setFile("D:\\Class\\Workspace\\ShootingGame\\res\\shoot.wav");
        		snd.play();
        		c.addEntity(new Bullet(this.getX(), this.getY(), tex));
        		Packet03Bullet packet = new Packet03Bullet(this.getUsername(),(int)this.x,(int)this.y);
        		packet.writeData(game.getClient());
            }
        }
		
		if(x<=0)
			x=0;
		if(x >=640-19)
			x=640-19;
		if(y<=0)
			y=0;
		if(y>=480-32)
			y=480-32;
		
		if(x!=0 || y!=0 )
		{
			Packet02Move packet = new Packet02Move(this.getUsername(),(int)this.x,(int)this.y);
			packet.writeData(game.getClient());
		}
		
		for(int i=0; i<game.eb.size();i++)
		{
			EntityB tempEnt = game.eb.get(i);
			
			if(Physx.Collision(this, tempEnt))
			{
				c.removeEntity(tempEnt);
				if(game.getSkinlck1()==1)
				{
					game.HEALTH -=0;
				}
				else if(game.getSkinlck1()==2)
				{
					game.HEALTH -=0;
				}
				else
				{
				game.HEALTH -=0;
				}
				game.setEnemy_killed(game.getEnemy_killed()+1);
				if(game.HEALTH<=0)
				{
					game.State=game.State.GAMEOVER;
				}
			}
		}
	}
	
	

	public Rectangle getBounds()
	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void render(Graphics g)
	{
		g.drawImage(tex.player, (int)x, (int)y, null);
		if(username !=null)
		{
			
			Font fntq = new Font("Haettenschweiler", Font.BOLD, 20);
			g.setFont(fntq);
			g.setColor(Color.red);
			g.drawString(username, (int)x, (int)y-10);
			
			
		}
	}
	
	public double getX()
	{
		return x;
		
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setX(double x)
	{
		this.x=x;
	}
	public void setY(double y)
	{
		this.y=y;
	}
	

	public String getUsername() {
		return this.username;
	}
	
	
	
	

}
