package com.game.src.main.net.packets;

import com.game.src.main.net.GameClient;
import com.game.src.main.net.GameServer;

public class Packet00Login extends Packet{
	
	private String username;
	
	private int x,y;

	public Packet00Login(byte[] data) {
		super(00);
		String [] dataArray = readData(data).split(",");
		this.username=dataArray[0];
		this.x=Integer.parseInt(dataArray[1]);
		this.y=Integer.parseInt(dataArray[2]);
		// TODO Auto-generated constructor stub
	}
	
	public Packet00Login(String username, int x, int y) {
		super(00);
		this.username=username;
		this.x=x;
		this.y=y;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void writeData(GameClient client) {
		// TODO Auto-generated method stub
		client.sendData(getData());
		
	}

	@Override
	public void writeData(GameServer server) {
		// TODO Auto-generated method stub
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData() {
		// TODO Auto-generated method stub
		return ("00"+this.username+","+getX()+","+getY()).getBytes();
	}
	
	public String getUsername()
	{
		return username;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
