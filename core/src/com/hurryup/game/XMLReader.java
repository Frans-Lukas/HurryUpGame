package com.hurryup.game;

import com.badlogic.gdx.math.Vector2;
import com.hurryup.objects.tiles.Button;
import com.hurryup.objects.tiles.Door;
import com.hurryup.objects.tiles.NormalGround;
import com.hurryup.objects.tiles.Tile;
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

    static public void readMap(String fileName, String level){
        try{
            File fxmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fxmlFile);

            doc.getDocumentElement().normalize();

            Node maps = doc.getElementsByTagName("map").item(0);
            System.out.println();

            if(maps.getNodeType() == Node.ELEMENT_NODE){
                Element eMaps = (Element) maps;
                stringToList(eMaps.getElementsByTagName(level).item(0).getTextContent());
            } else {
                System.out.println("map is not an element.");
            }



        } catch(Exception e){

        }
    }
    static private void stringToList(String sMap){
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

                width++;
            }
            height++;
        }

    }

    private void addLogic(){

    }


}
