package com.hurryup.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.tiles.Button;
import com.hurryup.objects.tiles.Door;
import com.hurryup.objects.tiles.NormalGround;
import com.hurryup.objects.tiles.Tile;
import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by frasse on 2016-09-20.
 */
public class MasterLevel implements IView{

    protected ArrayList<Tile> tiles = new ArrayList<Tile>();

    public MasterLevel(String fileName){
        loadTiles(fileName);
    }

    @Override
    public void dispose() {

    }

    //load map from textfile.
    public void loadTiles(String fileName){

        //get textfile with map.
        FileHandle file = Gdx.files.internal(fileName);

        //read entire map and split newlines to array.
        String lineArray[] = file.readString().split("\\r?\\n");

        //reverse map to get floor to get height of 0.
        ArrayList<String> lineArray2 = new ArrayList<String>(Arrays.asList(lineArray));
        Collections.reverse(lineArray2);
        lineArray = lineArray2.toArray(new String[lineArray2.size()]);

        int height = 0;
        int index = 0;

        //read map and insert objects equal to map into tile arraylist.
        for(String line : lineArray){
            int width = 0;
            for(String part : line.split(",")){

                //add normalGround to tiles arraylist.
                if(part.equals("1")){
                    tiles.add(index, new NormalGround(new Vector2(width * 64, height * 64)));
                    index++;
                }
                //add button to arraylist.
                else if(part.equals("2")){
                    tiles.add(index, new Button(new Vector2(width * 64, height * 64)));
                    index++;

                }
                //add door to arraylist
                else if(part.equals("3")){
                    tiles.add(index, new Door(new Vector2(width * 64, height * 64)));
                }
                width++;

            }
            height++;
        }

    }

    @Override
    public void draw(SpriteBatch batch, long deltaTime) {

    }
    @Override
    public void update(long deltaTime) {

    }
}
