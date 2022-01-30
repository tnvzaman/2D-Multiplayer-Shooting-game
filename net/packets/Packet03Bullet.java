package com.game.src.main.net.packets;

import com.game.src.main.net.GameClient;
import com.game.src.main.net.GameServer;

public class Packet03Bullet extends Packet{
	
	private String username;
	private int x,y;

	public Packet03Bullet(byte[] data) {
		super(03);
		String [] dataArray = readData(data).split(",");
		this.username=dataArray[0];
		this.x=Integer.parseInt(dataArray[1]);
		this.y=Integer.parseInt(dataArray[2]);
		
	}
	
	public Packet03Bullet(String username, int x, int y) {
		super(03);
		this.username=username;
		this.x=x;
		this.y=y;
		
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
		return ("03"+this.username+","+this.x+","+this.y).getBytes();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getUsername()
	{
		return username;
	}

}
