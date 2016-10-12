package com.hurryup.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.RemoteInput;
import com.badlogic.gdx.math.Vector2;
import com.hurryup.game.network.GameClient;
import com.hurryup.game.network.Server;
import com.hurryup.objects.MasterClass;
import com.hurryup.objects.tiles.LogicTile;
import com.hurryup.views.MainMenu;
import com.hurryup.views.MasterLevel;
import com.hurryup.views.TestLevel;
import com.hurryup.views.IView;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.badlogic.gdx.utils.TimeUtils.millis;

public class hurryupGame extends ApplicationAdapter {

	SpriteBatch batch;

	private static boolean hosting = false;

	public static boolean isHosting() {
		return hosting;
	}

	//cameracontrol
	static public OrthographicCamera camera;
	private IView viewToDraw;
	//stack for view handling
	private static Deque<IView> views = new ArrayDeque<IView>();

	//height and width of game screen.
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;

	//keep track of time
	long prevTime = millis();

	BitmapFont font;

	@Override
	public void create () {


		TextureManager.Load();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);

		//start with testlevel.
		views.push(new MainMenu());
		//views.push(new TestLevel());
        //XMLReader.readMap("level1.xml");
		//Load textures

	}

	@Override
	public void render(){
		//clear screen and draw it in white
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		GameClient.update();
		if(Server.isStarted()) {
			Server.update();
		}
		//TODO: camera that follows seperate players
		viewToDraw = views.peek();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		//update current view
		viewToDraw.update(millis() - prevTime);
		//draw current view.

		viewToDraw.draw(batch, millis() - prevTime);
		//keep track of current
		batch.end();
		prevTime = millis();
	}

	@Override
	public void dispose () {
		batch.dispose();
		for(IView view : views){
			view.dispose();
		}
	}

	//change view to watch
	public static void pushView(IView viewToSet){ views.push(viewToSet); }
	//remove current view and go back to previous view.
	public static IView popView(){
		return views.pop();
	}
	public static IView peekView() {
		return views.peek();
	}
	public static void updateRemotePostition(Vector2 newPos){
		((MasterLevel)peekView()).getRemotePlayer().setX((int)newPos.x);
		((MasterLevel)peekView()).getRemotePlayer().setY((int)newPos.y);
	}

	public static void startServer(int port){
        Server.start(1234);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        GameClient.configure("127.0.0.1",1234);
        GameClient.connect();
        hosting = true;
    }

    public static void startClient(String ip, int port){
        GameClient.configure("127.0.0.1", 1234);
        GameClient.connect();
    }
}


