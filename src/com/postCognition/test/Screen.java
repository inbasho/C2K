package com.postCognition.test;


import com.postCognition.test.Sprites.Sprite;
public class Screen {


	public int w, h;
	int xOffset = 0, yOffset = 0;
	public int[] pixels;

	public Screen(int w, int h) {
		this.w = w;
		this.h = h;

		pixels = new int[w * h];
	}

	public void renderSprite(int xPos, int yPos, Sprite sprite) {
		int height = sprite.h;
		int width = sprite.w;

		xPos -= xOffset;
		yPos -= yOffset;

		for (int y = 0; y < height; y++) {
			if (y + yPos < 0 || y + yPos >= h) continue;
			for (int x = 0; x < width; x++) {
				if (x + xPos < 0 || x + xPos >= w) continue;
				int col = sprite.pixels[x + (y * width)];
				if (col != -65281 && col < 0) pixels[(x + xPos) + (y + yPos) * w] = col;
			}
		}
	}

	public void setOffs(int xoffs,int yoffs) {
		xOffset = xoffs;
		yOffset = yoffs;
	}

}