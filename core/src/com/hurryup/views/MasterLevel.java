package com.hurryup.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.XMLReader;
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
    private void loadTiles(String fileName){
        tiles = XMLReader.getMap();
    }

    @Override
    public void draw(SpriteBatch batch, long deltaTime) {

    }

    @Override
    public void update(long deltaTime) {

    }

    public void connectLogic(){

    }
}
