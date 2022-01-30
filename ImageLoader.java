package com.game.src.main;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class ImageLoader {

	public Image getImage(String path)
	{
		Image tempImage =null;
		try
		{
			URL imageURL = ImageLoader.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		}catch(Exception e)
		{
			System.out.println("An error occured - "+e.getMessage());
		}
		return tempImage;
	}
}
