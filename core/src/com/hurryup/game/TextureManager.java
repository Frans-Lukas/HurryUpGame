package com.hurryup.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.hurryup.objects.tiles.LogicTile;
import com.hurryup.views.MasterLevel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

/**
 * Created by Klas on 2016-10-09.
 */
public final class TextureManager {

    private static HashMap<String,GameTexture> textures = new HashMap<String,GameTexture>();
    private static boolean debug = true;
    public static void Load()
    {
        //init players on map and dont update input on remoteplayer.
        try{
            //init xml reader.
            File fxmlFile = new File("sprites.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fxmlFile);

            doc.getDocumentElement().normalize();

            //get connection tags
            NodeList sprites = doc.getElementsByTagName("sprite");
            Texture t = new Texture("Cable.png");

            for(int i = 0; i < sprites.getLength(); i++){
                if(sprites.item(i).getNodeType() == Node.ELEMENT_NODE){
                    Element eSprite = (Element) sprites.item(i);

                    //Load sprite data
                    Rectangle spriteRect = new Rectangle();

                    spriteRect.setX(Integer.parseInt(eSprite.getAttribute("x")));
                    spriteRect.setY(Integer.parseInt(eSprite.getAttribute("y")));
                    spriteRect.setWidth(Integer.parseInt(eSprite.getAttribute("w")));
                    spriteRect.setHeight(Integer.parseInt(eSprite.getAttribute("h")));

                    String name = eSprite.getAttribute("name");
                    if(debug)
                        System.out.println("Loaded: " + name);
                    textures.put(name,new GameTexture(t,spriteRect));
                }
            }


        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static GameTexture get(String n){
        return textures.get(n);
    }

}
