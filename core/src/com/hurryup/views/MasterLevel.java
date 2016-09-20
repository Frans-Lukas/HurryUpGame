package com.hurryup.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.tiles.Button;
import com.hurryup.objects.tiles.NormalGround;
import com.hurryup.objects.tiles.Tile;

import java.util.ArrayList;

/**
 * Created by frasse on 2016-09-20.
 */
public class MasterLevel implements IView{

    ArrayList<Tile> tiles = new ArrayList<Tile>();

    public MasterLevel(){
        loadTiles("map1.txt");
    }

    public void loadTiles(String fileName){

        int height = 0;
        int width = 0;
        int index = 0;

        FileHandle file = Gdx.files.internal(fileName);

        String lineArray[] = file.readString().split("\\r?\\n");

        for(String line : lineArray){
            width++;
            for(String part : line.split(",")){

                if(part.equals("1")){
                    tiles.add(index, new NormalGround(new Vector2(width * Tile.width, height * Tile.height)));
                    index++;
                } else if(part.equals("2")){
                    tiles.add(index, new Button(new Vector2(width * Tile.width, height * Tile.height)));
                    index++;
                }

                height++;
            }
        }

    }

    @Override
    public void draw(SpriteBatch batch) {

    }
    @Override
    public void update(long deltaTime) {

    }
}
