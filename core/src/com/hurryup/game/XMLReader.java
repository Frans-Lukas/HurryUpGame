package com.hurryup.game;

import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.entities.Player;
import com.hurryup.objects.tiles.logicoperatortiles.*;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.*;
import com.hurryup.views.MasterLevel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.xml.parsers.*;

/**
 * Created by frasse on 2016-09-28.
 */
public final class XMLReader {
    static private ArrayList<Tile> map = new ArrayList<Tile>();
    static private Player localPlayer = new Player();
    static private Player remotePlayer = new Player(true);
    private static String nextLevel = "";
    private static int mapWidth = 0;
    private static int mapHeight = 0;

    public static int getMapWidth() {
        return mapWidth;
    }

    public static void setMapWidth(int mapWidth) {
        XMLReader.mapWidth = mapWidth;
    }

    public static void setMapHeight(int mapHeight) {
        XMLReader.mapHeight = mapHeight;
    }

    public static int getMapHeight() {
        return mapHeight;
    }

    private XMLReader(){}

    public static ArrayList getMap(){
        return map;
    }

    public static void setNextLevel(String nextLevel) {
        XMLReader.nextLevel = nextLevel;
    }

    public static void readMap(String fileName, MasterLevel level){
        //init players on map and dont update input on remoteplayer.
        try{
            //init xml reader.
            File fxmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fxmlFile);

            doc.getDocumentElement().normalize();

            //get map tag.
            Node maps = doc.getElementsByTagName("map").item(0);

            Element mapConnection = (Element) doc.getElementsByTagName("nextLevel").item(0);
            nextLevel = mapConnection.getAttribute("level");

            //read map
            if(maps.getNodeType() == Node.ELEMENT_NODE){
                Element eMaps = (Element) maps;
                stringmapToListmap(eMaps.getTextContent());
            } else {
                System.out.println("map is not an element.");
            }

            //get connection tags
            NodeList nlConnections = doc.getElementsByTagName("connection");

            for(int i = 0; i < nlConnections.getLength(); i++){
                if(nlConnections.item(i).getNodeType() == Node.ELEMENT_NODE){
                    Element eConnection = (Element) nlConnections.item(i);

                    //find tiles to connect.
                    int fromId = Integer.parseInt(eConnection.getAttribute("from"));
                    int toId = Integer.parseInt(eConnection.getAttribute("to"));

                    //get connection value if found, else set it to 0
                    int connectionValue = 0;
                    if(eConnection.hasAttribute("connectionvalue")) {
                        connectionValue = Integer.parseInt(eConnection.getAttribute("connectionvalue"));
                    }
                    //find tiles to connect
                    LogicTile fromTile = level.getTileById(fromId);
                    LogicTile toTile = level.getTileById(toId);
                    //connect tile
                    fromTile.setConnectionValue(connectionValue);
                    fromTile.connect(toTile);
                }
            }


        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    private static void stringmapToListmap(String sMap){
        //split map into rows
        String lineArray[] = sMap.split("\\r?\\n");

        //reverse map
        ArrayList<String> lineArray2 = new ArrayList<String>(Arrays.asList(lineArray));
        Collections.reverse(lineArray2);
        lineArray = lineArray2.toArray(new String[lineArray2.size()]);

        setMapHeight(0);
        setMapHeight(0);
        int height = 0;
        int index = 0;
        int width = 0;

        //read map and insert objects equal to map into tile arraylist.
        for(String line : lineArray){
            width = 0;
            for(String part : line.split(",")){

                String id[] = part.split(":");

                //add normalGround to tiles arraylist.
                if(id[0].equals("1")){
                    map.add(index, new NormalGround(new Vector2(width * 64, height * 64)));
                    index++;
                }
                //add button to arraylist.
                else if(id[0].equals("2")){
                    Button tmpButton = new Button(new Vector2(width * 64, height * 64));
                    tmpButton.setId(Integer.parseInt(id[1]));
                    map.add(index, tmpButton);
                    index++;
                }
                //add door to arraylist
                else if(id[0].equals("3")){
                    Door tmpDoor = new Door(new Vector2(width * 64, height * 64));
                    tmpDoor.setId(Integer.parseInt(id[1]));
                    map.add(index, tmpDoor);
                    index++;
                }
                //add LOTand to arraylist
                else if(id[0].equals("4")){
                    LOTand tmpLOTand = new LOTand(new Vector2(width * 64, height * 64), LogicColor.None, Integer.parseInt(id[1]), 0);
                    map.add(index, tmpLOTand);
                    index++;
                }
                //add LOTnot to arraylist
                else if(id[0].equals("5")){
                    LOTnot tmpLOTnot = new LOTnot(new Vector2(width * 64, height * 64), LogicColor.None, Integer.parseInt(id[1]), 2);
                    map.add(index, tmpLOTnot);
                    index++;
                }
                //add LOTor to arraylist
                else if(id[0].equals("6")){
                    LOTor tmpLOTor = new LOTor(new Vector2(width * 64, height * 64), LogicColor.None, Integer.parseInt(id[1]), 0);
                    map.add(index, tmpLOTor);
                    index++;
                }
                //add Lever to arraylist
                else if(id[0].equals("7")){
                    Lever tmpLever = new Lever(new Vector2(width * 64, height * 64));
                    tmpLever.setId(Integer.parseInt(id[1]));
                    map.add(index, tmpLever);
                    index++;
                }
                //add LOTxnor to arraylist
                else if(id[0].equals("8")){
                    LOTxnor tmpXnor = new LOTxnor(new Vector2(width * 64, height * 64), LogicColor.None, Integer.parseInt(id[1]), 0);
                    tmpXnor.setId(Integer.parseInt(id[1]));
                    map.add(index, tmpXnor);
                    index++;
                }
                //add SplitterTile to arraylist
                else if(id[0].equals("9")){
                    SplitterTile tmpTwoOut = new SplitterTile(new Vector2(width * 64, height * 64), LogicColor.None, Integer.parseInt(id[1]), 0);
                    tmpTwoOut.setId(Integer.parseInt(id[1]));
                    map.add(index, tmpTwoOut);
                    index++;
                }
                //add SplitterTile to arraylist
                else if(id[0].equals("10")){
                    Hatch hatch = new Hatch(new Vector2(width * 64, height * 64));
                    hatch.setId(Integer.parseInt(id[1]));
                    map.add(index, hatch);
                    index++;
                }
                //add Zdoor
                else if(id[0].equals("11")){
                    //System.out.println("next level is: " + nextLevel);
                    ZDoor zDoor = new ZDoor(new Vector2(width * 64, height * 64), LogicColor.None, Integer.parseInt(id[1]), 0, nextLevel);
                    zDoor.setId(Integer.parseInt(id[1]));
                    map.add(index, zDoor);
                    index++;
                }
                //set position of player1
                else if(id[0].equals("21")){
                    if(hurryupGame.isHosting()){
                        localPlayer.setX(width * 64);
                        localPlayer.setY(height * 64);
                        localPlayer.player1 = true;
                    } else{
                        remotePlayer.setX(width * 64);
                        remotePlayer.setY(height * 64);
                        remotePlayer.player1 = true;
                    }
                }
                //set position of player 2.
                else if(id[0].equals("22")){
                    if(hurryupGame.isHosting()){
                        remotePlayer.setX(width * 64);
                        remotePlayer.setY(height * 64);
                    } else{
                        localPlayer.setX(width * 64);
                        localPlayer.setY(height * 64);
                    }
                }
                width++;
            }
            height++;
        }
        mapWidth = width * 64;
        mapHeight = height * 64;
    }
    public static Player getLocalPlayer(){
        return localPlayer;
    }
    public static Player getRemotePlayer(){
        return remotePlayer;
    }

}
