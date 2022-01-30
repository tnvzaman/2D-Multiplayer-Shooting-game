package com.game.src.main;

import java.net.InetAddress;

public class PlayerMP extends Player {
	
	
	public InetAddress ipAddress;
	public int port;
	public PlayerMP(double x, double y, Textures tex, String username, Game game, Controller c, KeyboardInput input, InetAddress ipAddress, int port) {
		super(x, y, tex, username, game, c, input);
		this.ipAddress = ipAddress;
		this.port =port;
		// TODO Auto-generated constructor stub
	}
	
	public PlayerMP(double x, double y, Textures tex, String username, Game game, Controller c, InetAddress ipAddress, int port) {
		super(x, y, tex, username, game, c, null);
		this.ipAddress = ipAddress;
		this.port =port;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick() {
		super.tick();
	}

}
