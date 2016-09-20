package com.hurryup.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.objects.MasterClass;
import com.hurryup.views.MainMenu;
import com.hurryup.views.TestLevel;
import com.hurryup.views.IView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import static com.badlogic.gdx.utils.TimeUtils.millis;

public class hurryupGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private static Deque<IView> views = new ArrayDeque<IView>();

	long prevTime = millis();

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		views.push(new MainMenu());

	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		views.peek().update(millis() - prevTime);
		batch.begin();
		views.peek().draw(batch);
		batch.end();

		prevTime = millis();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public static void pushView(IView viewToSet){
		views.push(viewToSet);
	}
	public static IView popView(){
		return views.pop();
	}
}
