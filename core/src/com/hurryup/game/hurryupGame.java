package com.hurryup.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.views.TestLevel;
import com.hurryup.views.IView;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.badlogic.gdx.utils.TimeUtils.millis;

public class hurryupGame extends ApplicationAdapter {

	SpriteBatch batch;

	//cameracontrol
	static public OrthographicCamera camera;

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
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);

		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		//start with testlevel.
		views.push(new TestLevel());

	}

	@Override
	public void render(){
		//clear screen and draw it in white
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		//create variable to make viewhandling easier.
		IView viewToDraw = views.peek();

		//update current view
		viewToDraw.update(millis() - prevTime);

		//draw current view.

		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		viewToDraw.draw(batch, millis() - prevTime);
		font.draw(batch, "hello", 200, 200);
		batch.end();


		//keep track of current time.
		prevTime = millis();

	}

	@Override
	public void dispose () {
		batch.dispose();
	}

	//change view to watch
	public static void pushView(IView viewToSet){ views.push(viewToSet); }
	//remove current view and go back to previous view.
	public static IView popView(){
		return views.pop();
	}
}
