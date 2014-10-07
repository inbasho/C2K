package com.postCognition.test;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;
import javax.swing.JFrame;

import com.postCognition.test.Level;
import com.postCognition.test.Sprites.SpriteSheetLoader;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 480;
	public static int HEIGHT = 320;
	public static int SCALE = 3;
	
	public static int playerX= 25;
	public static int playerY= 25;
	
	public static int xScroll = 0;
	public static int yScroll = 0;
	
	public static Dimension GAME_DIM = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public static String NAME = "Custom rendering engine";

	public BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
	public int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public SpriteSheetLoader loader;
	private Screen screen;
	public Level level;
	public InputHandler input = new InputHandler(this);
	public boolean running = false;
	Random random = new Random();

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public Game() {
	}

	public void init() {

		loader = new SpriteSheetLoader();
		screen = new Screen(WIDTH, HEIGHT);
		
		level = new Level(16,16);
		
	}

	public void run() {
		init();
		while (running) {
			tick();
			render();

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void tick() {
		if(input.right) xScroll++;
		if(input.left) xScroll--;
		if(input.down) yScroll++;
		if(input.up)yScroll--; 
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		
		level.renderBackground(xScroll, yScroll, screen);
		
		for (int y = 0; y < screen.h; y++) {
			for (int x = 0; x < screen.w; x++) {
				pixels[x + y * WIDTH] = screen.pixels[x + y * screen.w];
			}
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(GAME_DIM);
		game.setMaximumSize(GAME_DIM);
		game.setMinimumSize(GAME_DIM);

		JFrame frame = new JFrame(NAME);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		game.start();
	}
}