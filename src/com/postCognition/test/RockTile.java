package com.postCognition.test;


import com.postCognition.test.Screen;
import com.postCognition.test.Sprites.Sprites;
public class RockTile extends Tile{

	
	public RockTile(int id){
		super(id);
		
		tile = Sprites.terrain[1][0];
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderSprite(x * tile.w, y * tile.h, tile);
	}

}