package com.hurryup.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.hurryupGame;
import com.hurryup.objects.entities.Cursor;
import com.hurryup.objects.logic.LogicColor;
import com.hurryup.objects.tiles.logicoperatortiles.LOTand;
import com.hurryup.objects.tiles.logicoperatortiles.LOTnot;
import com.hurryup.objects.tiles.logicoperatortiles.LOTor;
import com.hurryup.objects.tiles.logicoperatortiles.LOTxnor;

import static com.hurryup.objects.tiles.Tile.renderer;

/**
 * Created by frasse on 2016-10-08.
 */
public class TutorialMenu extends MasterLevel{

    private BitmapFont titleFont = new BitmapFont();
    private BitmapFont descriptionFont = new BitmapFont();

    private int ipMenuWidth = 400;
    private int menuX = hurryupGame.WIDTH / 2 ;
    private int menuY = hurryupGame.HEIGHT / 2 - 70;
    private int menuX2 = menuX + 50;
    private int firstRow = menuY + 100;
    private int colIncreaser = 200;
    private int secondRow = firstRow - 100;
    private int thirdRow = secondRow - 150;


    int normalWidth = 64;
    int normalHeight = 64;

    int btnHeight = 32;



    LOTand and = new LOTand(new Vector2(menuX2 - colIncreaser * 2, firstRow), LogicColor.None, 0, 0);
    LOTnot not = new LOTnot(new Vector2(menuX2 - colIncreaser, firstRow), LogicColor.None, 0, 0);
    LOTor or = new LOTor(new Vector2(menuX2, firstRow), LogicColor.None, 0, 0);
    LOTxnor xnor = new LOTxnor(new Vector2(menuX2 + colIncreaser, firstRow), LogicColor.None, 0, 0);

    Cursor cursor = new Cursor();

    public TutorialMenu(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("RuslanDisplay.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("RuslanDisplay.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 57;
        titleFont = generator.generateFont(parameter);
        parameter2.size = 30;
        descriptionFont = generator2.generateFont(parameter2);

        generator.dispose();
        generator2.dispose();

    }

    @Override
    public void draw(SpriteBatch batch, long deltaTime) {

        renderer.end();

        batch.begin();

        titleFont.setColor(Color.BLACK);
        titleFont.draw(batch, "HURRY UP GUIDE", menuX - 50 - ipMenuWidth / 2, menuY + 300);

        //draw names of blocks
        descriptionFont.setColor(Color.BLACK);
        descriptionFont.draw(batch, "AND", and.getPosition().x - 1 , and.getPosition().y + btnHeight * 2 + 24);
        descriptionFont.draw(batch, "NOT", not.getPosition().x + 1, not.getPosition().y + btnHeight * 2 + 24);
        descriptionFont.draw(batch, "OR", or.getPosition().x + 9, or.getPosition().y + btnHeight * 2 + 24);
        descriptionFont.draw(batch, "XNOR", xnor.getPosition().x - 7, xnor.getPosition().y + btnHeight * 2 + 24);

        //draw descriptions of blocks
        //and
        descriptionFont.draw(batch, "T", and.getPosition().x - 30 + 20, secondRow);
        descriptionFont.draw(batch, "T", and.getPosition().x + 30 + 20, secondRow);

        //not
        descriptionFont.draw(batch, "F", not.getPosition().x + 20, secondRow);

        //or
        descriptionFont.draw(batch, "T", or.getPosition().x - 30 + 20, secondRow);
        descriptionFont.draw(batch, "or", or.getPosition().x + 20, secondRow);
        descriptionFont.draw(batch, "T", or.getPosition().x + 50 + 20, secondRow);

        //xnor
        descriptionFont.draw(batch, "TT or FF", xnor.getPosition().x - 30, secondRow);

        //draw exit
        descriptionFont.draw(batch, "PRESS 'ESC' TO RETURN", menuX - ipMenuWidth / 2, thirdRow);

        batch.end();

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        //draw descripton connections

        //and
        renderer.line(and.getPosition().x - 30 + 29, secondRow - 20, and.getPosition().x, and.getPosition().y + 20);
        renderer.line(and.getPosition().x + 30 + 29, secondRow, and.getPosition().x + normalWidth, and.getPosition().y);

        //not
        renderer.line(not.getPosition().x + 29, secondRow, not.getPosition().x + normalWidth / 2, not.getPosition().y);

        //or
        renderer.line(or.getPosition().x - 30 + 29, secondRow - 20, or.getPosition().x, or.getPosition().y + 20);
        renderer.line(or.getPosition().x + 50 + 29, secondRow, or.getPosition().x + normalWidth, or.getPosition().y);

        //xnor
        renderer.line(xnor.getPosition().x - 17, secondRow, xnor.getPosition().x, xnor.getPosition().y);
        renderer.line(xnor.getPosition().x, secondRow, xnor.getPosition().x + normalWidth, xnor.getPosition().y);

        renderer.line(xnor.getPosition().x + 90, secondRow, xnor.getPosition().x, xnor.getPosition().y);
        renderer.line(xnor.getPosition().x + 100, secondRow, xnor.getPosition().x + normalWidth, xnor.getPosition().y);



        and.draw(batch);
        not.draw(batch);
        or.draw(batch);
        xnor.draw(batch);


        renderer.end();

        //hurryupGame.pushView(new TestLevel());

        renderer.begin(ShapeRenderer.ShapeType.Filled);

        cursor.draw(batch, renderer);
    }

    @Override
    public void update(long deltaTime) {
        cursor.update(deltaTime);
        getInput();
    }
    public void getInput(){
        boolean menu = Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE);

        if(menu){
            hurryupGame.popView();
        }
    }
}
