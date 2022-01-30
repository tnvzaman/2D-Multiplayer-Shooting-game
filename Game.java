package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.net.GameClient;
import com.game.src.main.net.GameServer;
import com.game.src.main.net.packets.Packet00Login;


public class Game extends Canvas implements Runnable {
	public static final int WIDTH= 320;
	public static final int HEIGHT = WIDTH/ 12*9;
	public static final int SCALE = 2;
	public final String TITLE = "THE SLAYER'S STAND";
	public static JFrame frame;
	public WindowHandler wind;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	
	private boolean is_shooting = false;
	
	private int enemy_count =5;
	private int enemy_killed=0;
	
	private  int score = 0;
	private int highScore =0;
	private Font scoreFont;
	
	private String saveDataPath;
	private String fileName = "SaveData";
	
	
	
	public static enum STATE
	{
		MENU, GAME, HELP, FORTRESS, GAMEOVER
	};
	
	public static STATE State = STATE.MENU;
	private Menu menu;
	private Fortress frt;
	private Help help;
	private GameOver over;
	

	public Player p;
	private Controller c;
	private Textures tex;
	private Sound snd;
	private int skinlck1=0;
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public KeyboardInput input;
	
	
	public  LinkedList<EntityA>ea;
	public LinkedList<EntityB>eb;
	
	public static int HEALTH=100*2;
	
	private GameClient client;
	private GameServer server;
	
	
	
	public void init()
	{
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/sprite_sheet.png");
			background = loader.loadImage("/background.png");
			
			saveDataPath=Game.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			
		}catch(IOException | URISyntaxException e)
		{
			e.printStackTrace();
		}
		snd= new Sound();
		
		scoreFont=new Font("Haettenschweiler", Font.BOLD, 20);
		loadHighScore();
		
		
		input = new KeyboardInput(this);
		wind=new WindowHandler(this);
		
		tex = new Textures(this);
		
//		client.sendData("ping".getBytes());

		c=new Controller( tex,this);
       	p= new PlayerMP(100,100, tex, JOptionPane.showInputDialog(this, "Please enter Username"),this,c, input,null,-1);
      	c.addEntity(p);
      	Packet00Login loginPacket = new Packet00Login(p.getUsername(),(int)p.getX(),(int)p.getY());
      	if(server!=null)
      	{
      		server.addConnection((PlayerMP)p, loginPacket);
      	}
      	
		loginPacket.writeData(client);

		menu = new Menu();
		help= new Help();
		over = new GameOver();
		frt= new Fortress();
		
		ea=c.getEntityA();
		eb=c.getEntityB();
		this.addMouseListener(new MouseInput(this));
		
		c.createEnemy(enemy_count);
		
	}
			
	
	
	
	private synchronized void start()
	{
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
		if(JOptionPane.showConfirmDialog(this, "Do you want to run the server")==0)
		{
			server = new GameServer(this);
			server.start();
	
		}
		client = new GameClient(this, "localhost");
		client.start();
		
		
	}
	
	private synchronized void stop()
	{
		if(!running)
			return;
		
	
		running = false;
		try {
		thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	
	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >=1)
			{
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer >1000)
			{
				timer +=1000;
//				System.out.println(updates + "Ticks, FPS" +frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick()
	{
		if(State == STATE.GAME)
		{
		
		c.tick();
		}
		
		if(enemy_killed >= enemy_count)
		{
			enemy_count+=2;
			enemy_killed = 0;
			c.createEnemy(enemy_count);
		}
		if(score>highScore)
		{
		highScore =score;
		
		}
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(3);
			return;
			
		}
		Graphics g = bs.getDrawGraphics();
		
		
		
		g.drawImage(background,0,0,null);
		
		if(State==STATE.GAME)
		{
			
		if(skinlck1==1)
		{
			tex.getSkin1();
		}
		else if(skinlck1==2)
		{
			tex.getSkin2();
		}
		
		c.render(g);
		
		if(input!=null)
		{
			if (input.esc.isPressed()) {
                State=STATE.MENU;
            }
		}
		
		
		
		g.setColor(Color.red);
		g.fillRect(5, 5, 200, 50);
		
		g.setColor(Color.green);
		g.fillRect(5, 5, HEALTH, 50);
		
		g.setColor(Color.black);
		g.drawRect(5, 5, 200, 50);
		
		g.setColor(Color.black);
		g.setFont(scoreFont);
		g.drawString("Score: "+score, 300, 50);
		g.setColor(Color.red);
		g.drawString("Best: "+highScore, 400, 50);
		} else if(State==STATE.MENU)
		{
			
			menu.render(g);
			
		}
		else if(State==STATE.HELP) {
			help.render(g);
		}
		else if(State==STATE.GAMEOVER)
		{
			setHighScore();
			over.render(g);
			
			Game.HEALTH=100*2;
			score=0;
			
		}
		else if(State==STATE.FORTRESS)
		{
			frt.render(g, this);
		}
		
		
		
		g.dispose();
		bs.show();
		
	}
	
	private void createSaveData()
	{
		try
		{
			File file = new File(saveDataPath, fileName);
			
			FileWriter output = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(output);
			writer.write(""+0);
			//create fastest time
			writer.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void loadHighScore()
	{
		try
		{
			File f = new File(saveDataPath, fileName);
			if(!f.isFile())
			{
				createSaveData();
			}
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			highScore = Integer.parseInt(reader.readLine());
			reader.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void setHighScore()
	{
		FileWriter output = null;
		
		try {
			File f=new File(saveDataPath, fileName);
			output = new FileWriter(f);
			
			BufferedWriter writer = new BufferedWriter(output);
			
				writer.write(""+highScore);
				
			
			writer.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
  
	
	
	public static void main(String[] args)
	{
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
	    frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
		

	}
	
	public BufferedImage getSpriteSheet()
	{
		return spriteSheet;
	}





	public int getEnemy_count() {
		return enemy_count;
	}





	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}





	public int getEnemy_killed() {
		return enemy_killed;
	}





	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	
	
	public void CalScore()
	{
		this.score+=1;
		
	}
	
	





	public void setP(Player p) {
		this.p = p;
	}
	
	public int getHighScore() {
		return highScore;
	}
	
	public Player getP() {
		return p;
	}





	public Textures getTex() {
		return tex;
	}




	public void setSkinlck1(int skinlck1) {
		this.skinlck1 = skinlck1;
	}




	public int getSkinlck1() {
		return skinlck1;
	}




	public Controller getC() {
		return c;
	}




	public GameClient getClient() {
		return client;
	}
	
	
	
	
	
	
	
	


	

}
