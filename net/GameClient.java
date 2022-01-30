package com.game.src.main.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.game.src.main.Bullet;
import com.game.src.main.Game;
import com.game.src.main.PlayerMP;
import com.game.src.main.net.packets.Packet;
import com.game.src.main.net.packets.Packet00Login;
import com.game.src.main.net.packets.Packet01Disconnect;
import com.game.src.main.net.packets.Packet02Move;
import com.game.src.main.net.packets.Packet03Bullet;
import com.game.src.main.net.packets.Packet.PacketTypes;

public class GameClient extends Thread {
	
	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	
	public GameClient(Game game, String ipAddress)
	{
		this.game= game;
		try {
			this.socket= new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		while (true)
		{
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
//			System.out.println("SERVER > "+new String(packet.getData()));
		}
	}
	
	private void parsePacket(byte[] data, InetAddress address, int port) {
		// TODO Auto-generated method stub
		String message = new String (data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0,2));
		Packet packet=null;
		switch(type)
		{
		default:
		case INVALID:
			break;
		case LOGIN:
		    packet=new Packet00Login(data);
		    handleLogin(((Packet00Login)packet),address,port);
			
		
			break;
		case DISCONNECT:
			packet=new Packet01Disconnect(data);
			System.out.println("["+address.getHostAddress()+":"+port+"]"+((Packet01Disconnect)packet).getUsername()+"  has left the game.....");
			game.getC().removePlayerMP(((Packet01Disconnect)packet).getUsername());
			break;
		case MOVE:
			packet = new Packet02Move(data);
			handleMove((Packet02Move)packet);
			break;
		case BULLET:
			packet = new Packet03Bullet(data);
			handleBullet((Packet03Bullet)packet);
			
		}
	}
	
	private void handleBullet(Packet03Bullet packet) {
		// TODO Auto-generated method stub
		this.game.getC().addEntity(new Bullet(packet.getX(),packet.getY(),this.game.getTex()));
	}

	private void handleLogin(Packet00Login packet, InetAddress address, int port) {
		System.out.println("["+address.getHostAddress()+":"+port+"]"+((Packet00Login)packet).getUsername()+"  has joined the game.....");
		
		PlayerMP player = new PlayerMP(packet.getX(),packet.getY(),game.getTex(),((Packet00Login)packet).getUsername(),game,game.getC(),address,port);
		game.getC().addEntity(player);
		
	}

	public void sendData(byte[] data)
	{
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleMove(Packet02Move packet) {
		this.game.getC().movePlayerMP(packet.getUsername(), packet.getX(), packet.getY());
		
	}

}
