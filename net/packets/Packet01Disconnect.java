package com.game.src.main.net.packets;

import com.game.src.main.net.GameClient;
import com.game.src.main.net.GameServer;

public class Packet01Disconnect extends Packet{
	
	private String username;

	public Packet01Disconnect(byte[] data) {
		super(01);
		this.username=readData(data);
		// TODO Auto-generated constructor stub
	}
	
	public Packet01Disconnect(String username) {
		super(01);
		this.username=username;
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
		return ("01"+this.username).getBytes();
	}
	
	public String getUsername()
	{
		return username;
	}

}
