package com.hurryup.views;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.game.TextureManager;
import com.hurryup.game.XMLReader;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.entities.Player;
import com.hurryup.objects.helper.VisualConnection;
import com.hurryup.objects.tiles.*;

import java.util.ArrayList;

/**
 * Created by frasse on 2016-09-20.
 */
public class MasterLevel implements IView{

    protected ArrayList<Tile> tiles = new ArrayList<Tile>();
    public MasterLevel(){
        loadTiles();
    }
    protected ArrayList<MasterClass> entities = new ArrayList<MasterClass>();

    protected Player localPlayer;
    protected Player remotePlayer;

    @Override
    public void dispose() {

    }

    //load map from textfile.
    protected void loadTiles(){
        tiles = XMLReader.getMap();
        localPlayer = XMLReader.getLocalPlayer();
        remotePlayer = XMLReader.getRemotePlayer();
        entities.add(localPlayer);
        entities.add(remotePlayer);
    }

    public void buildConnections(){

        for (Tile t: tiles) {
            if(t instanceof LogicTile){
                VisualConnection.addPair((((LogicTile) t).getConnectionPair(false)));
                VisualConnection.addPair((((LogicTile) t).getConnectionPair(true)));
                VisualConnection.addNoGo(t.getPosition());
            }
        }
        VisualConnection.build();
    }

    @Override
    public void draw(SpriteBatch batch, long deltaTime){

    }


    @Override
    public void update(long deltaTime) {

    }

    public LogicTile getTileById(int id){
        for (Tile tile: tiles) {
            if(tile instanceof LogicTile)
                if(((LogicTile)tile).getId() == id)
                    return (LogicTile)tile;
        }
        return null;
    }

    public Player getLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(Player localPlayer) {
        this.localPlayer = localPlayer;
    }

    public Player getRemotePlayer() {
        return remotePlayer;
    }

    public void setRemotePlayer(Player remotePlayer) {
        this.remotePlayer = remotePlayer;
    }

    public void clearEnteties(){
        entities.clear();
    }
    public void clearTiles(){
        tiles.clear();
    }
}
