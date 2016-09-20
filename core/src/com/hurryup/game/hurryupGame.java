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
	private Thread serverWorker;
	private Thread clientWorker;
    private String ip = "127.0.0.1";
    private int port = 1337;
    private boolean host = true;
    private ServerLogic serverLogic;
    private ClientLogic clientLogic;

    public hurryupGame(boolean host)
    {
        this.host = host;
    }
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		views.push(new TestLevel());
        System.out.println(System.getProperty("server"));
        if(host) {

            serverLogic = new ServerLogic("127.0.0.1",1337);
            serverWorker = new Thread(serverLogic);
            serverLogic.setView(views.peek());
			serverWorker.start();

        }
        else {
            clientLogic = new ClientLogic("127.0.0.1", 1337);
			clientWorker = new Thread(clientLogic);
			clientWorker.start();
        }
	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		IView viewToDraw = views.peek();

		viewToDraw.update(millis() - prevTime);
		batch.begin();
		viewToDraw.draw(batch);
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
