package com.hurryup.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.hurryup.game.hurryupGame;
import com.hurryup.objects.entities.Cursor;

/**
 * Created by frasse on 2016-09-20.
 */
public class MainMenu implements IView {
    private int ipMenuWidth = 400;
    private int ipMenuHeight = 80;
    private int sgMenuWidth = 180;
    private int sgMenuHeight = 80;
    private int spacing = 100;
    private int menuY = hurryupGame.HEIGHT / 2 - 70;
    private int lowerMenuY = menuY - spacing;
    private int menuX = hurryupGame.WIDTH / 2 - ipMenuWidth / 2;
    private int rightMenuX = menuX + sgMenuWidth / 9 + ipMenuWidth / 2;

    private int blinkTime = 1000;
    private int totalTime = 0;

    private Color ipColor = Color.BLACK;
    private Color jgColor = Color.BLACK;
    private Color hgColor = Color.BLACK;
    private Color typerColor = Color.BLACK;

    private boolean setScaleOnlyOnce = false;

    private BitmapFont font = new BitmapFont();
    private Stage stage;
    private TextField textField;
    private Skin skin;
    private TextField ipTextField;
    private TextField portTextField;

    Cursor cursor;

    public MainMenu() {
        //hide mouse.
        Gdx.input.setCursorCatched(true);
        cursor = new Cursor();
        stage = new Stage();

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        ipTextField = new TextField("", skin, "default");
        ipTextField.setMessageText("Enter IP-address");
        ipTextField.setAlignment(Align.center);
        ipTextField.setPosition(menuX, menuY);
        ipTextField.setSize(ipMenuWidth, ipMenuHeight);
        ipTextField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if(c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });

        portTextField = new TextField("", skin, "default");
        portTextField.setMessageText("Enter port");
        portTextField.setAlignment(Align.center);
        portTextField.setPosition(menuX, menuY + spacing);
        portTextField.setSize(ipMenuWidth, ipMenuHeight);
        portTextField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                if(c == '\n') textField.getOnscreenKeyboard().show(false);
            }
        });


        final TextButton joinGameButton = new TextButton("JOIN GAME", skin, "default");
        joinGameButton.setSize(sgMenuWidth, sgMenuHeight);
        joinGameButton.setPosition(menuX, lowerMenuY);

        joinGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String ipAddress = ((TextField)stage.getActors().get(2)).getText();
                String port = ((TextField)stage.getActors().get(1)).getText();
                System.out.println("ip: " + ipAddress + ", port: " + port);
                try{
                    hurryupGame.startClient(ipAddress,Integer.parseInt(port));
                } catch(Exception e){
                    hurryupGame.startClient(ipAddress,1337);
                }
                hurryupGame.pushView(new TestLevel("lobbyLevel.xml"));
            }
        });

        final TextButton hostGameButton = new TextButton("HOST GAME", skin, "default");
        hostGameButton.setSize(sgMenuWidth, sgMenuHeight);
        hostGameButton.setPosition(rightMenuX, lowerMenuY);

        hostGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String ipAddress = ((TextField)stage.getActors().get(2)).getText();
                String port = ((TextField)stage.getActors().get(1)).getText();
                System.out.println("ip: " + ipAddress + ", port: " + port);
                try{
                    hurryupGame.startServer(Integer.parseInt(port));

                } catch(Exception e){
                    hurryupGame.startServer(1337);
                }
                hurryupGame.pushView(new TestLevel("lobbyLevel.xml"));
            }
        });

        stage.addActor(joinGameButton);
        stage.addActor(portTextField);
        stage.addActor(ipTextField);
        stage.addActor(hostGameButton);

        Gdx.input.setInputProcessor(stage);


        //change font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("RuslanDisplay.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 57;
        font = generator.generateFont(parameter);
        generator.dispose();


    }


    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void draw(SpriteBatch batch, long deltaTime) {

        font.setColor(Color.BLACK);
        font.draw(batch, "HURRY UP GAME", menuX - 50, menuY + 300);
        batch.end();
        stage.draw();
        batch.begin();
        cursor.draw(batch);
    }

    @Override
    public void update(long deltaTime) {
        cursor.update(deltaTime);
        totalTime += deltaTime;
        if(totalTime >= blinkTime){
            totalTime = 0;
            if(typerColor == Color.BLACK){
                typerColor = Color.WHITE;
            } else{
                typerColor = Color.BLACK;
            }
        }


    }

    boolean checkCollision(float x1, float x2, float y1, float y2, int width1, int height1, int width2, int height2){
        boolean collision = false;
        if(x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 < y2 + height2 &&
                y1 + height1 > y2){
            collision = true;
        }
        return collision;
    }
}
