package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

public class MouseInput implements MouseListener {

	private Game game;
	
	public MouseInput(Game game)
	{
		this.game=game;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx=e.getX();
		int my=e.getY();
		/**
		 * public Rectangle playButton = new Rectangle(Game.WIDTH/2+120,150,100,50); 
	public Rectangle helpButton = new Rectangle(Game.WIDTH/2+120,250,100,50); 
	public Rectangle quitButton = new Rectangle(Game.WIDTH/2+120,350,100,50); 
		 */
		if(Game.State==Game.State.MENU)
		{
		//playButton
		if(mx >= Game.WIDTH/2+120 && mx<=Game.WIDTH/2 +220)
		{
			if(my>=150 && my<=200)
			{
				Game.State = Game.STATE.GAME;
			}
		}
		
		//fortressButton
		if(mx >= Game.WIDTH/2+120 && mx<=Game.WIDTH/2 +220)
		{
			if(my>=230 && my<=270)
			{
				Game.State = Game.STATE.FORTRESS;
			}
		}
		
		//helpButton
		if(mx>=Game.WIDTH/2+120 && mx<=Game.WIDTH/2 +220)
		{
			if(my>=310 && my<=360)
			{
				Game.State=Game.State.HELP;
			}
		}
		
		//quitButton
		if(mx >= Game.WIDTH/2+120 && mx<=Game.WIDTH/2 +220)
		{
			if(my>=390 && my<=440)
			{
				System.exit(1);
			}
		}
		}
		
		if(Game.State==Game.State.HELP)
		{
			if(mx>=450 && mx<=550)
			{
				if(my>=350 && my<=400)
				{
					Game.State=Game.State.MENU;
				}
			}
		}
		
		if(Game.State==Game.State.GAMEOVER)
		{
			if(mx>=450 && mx<=550)
			{
				if(my>=350 && my<=400)
				{
					
					Game.State=Game.State.MENU;
				}
			}
		}
		
		if(Game.State==Game.State.FORTRESS)
		{
			if(mx>=450 && mx<=550)
			{
				if(my>=350 && my<=400)
				{
					Game.State=Game.State.MENU;
				}
			}
			else if(mx>=100 && mx<=240)
			{
				if(my>=100 && my<=240)
				{
					if(game.getHighScore()>=10)
					{
						JOptionPane.showMessageDialog(null, "SENTINEL SKIN UNLOCKED");
						game.setSkinlck1(1);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You do not have Enough Score");
					}
				}
			}
			
			else if(mx>=250 && mx<=390)
			{
				if(my>=100 && my<=240)
				{
					if(game.getHighScore()>=20)
					{
						JOptionPane.showMessageDialog(null, "DARK LORD SKIN UNLOCKED");
						game.setSkinlck1(2);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "You do not have Enough Score");
					}
				}
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
