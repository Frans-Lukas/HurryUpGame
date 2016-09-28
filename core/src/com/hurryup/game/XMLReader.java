package com.hurryup.game;

import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.tiles.logicoperatortiles.LOTand;
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

    private XMLReader(){}

    static public ArrayList getMap(){
        return map;
    }

    static public void readMap(String fileName){
        try{
            //init xml reader.
            File fxmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fxmlFile);

            doc.getDocumentElement().normalize();

            //get map tag.
            Node maps = doc.getElementsByTagName("map").item(0);
            System.out.println();

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
                        System.out.println("");
                        connectionValue = Integer.parseInt(eConnection.getAttribute("connectionvalue"));
                    }

                    //find tiles to connect
                    LogicTile fromTile = ((MasterLevel)hurryupGame.peekView()).getTileById(fromId);
                    LogicTile toTile = (((MasterLevel)hurryupGame.peekView()).getTileById(toId));

                    //connect tile
                    fromTile.setConnectionValue(connectionValue);
                    fromTile.connect(toTile);
                }
            }


        } catch(Exception e){

        }
    }
    static private void stringmapToListmap(String sMap){
        //split map into rows
        String lineArray[] = sMap.split("\\r?\\n");

        //reverse map
        ArrayList<String> lineArray2 = new ArrayList<String>(Arrays.asList(lineArray));
        Collections.reverse(lineArray2);
        lineArray = lineArray2.toArray(new String[lineArray2.size()]);

        int height = 0;
        int index = 0;

        //read map and insert objects equal to map into tile arraylist.
        for(String line : lineArray){
            int width = 0;
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
                else if(id[0].equals("4")){
                    LOTand tmpLOTand = new LOTand(new Vector2(width * 64, height * 64), LogicColor.None, Integer.parseInt(id[1]), 0);
                    map.add(index, tmpLOTand);
                    index++;
                }

                width++;
            }
            height++;
        }

    }


}
