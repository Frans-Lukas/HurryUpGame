package com.hurryup.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hurryup.game.network.ClientLogic;
import com.hurryup.game.network.GameClient;
import com.hurryup.game.network.Server;
import com.hurryup.game.network.ServerLogic;
import com.hurryup.views.TestLevel;
import com.hurryup.views.IView;

import java.util.ArrayDeque;
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
    private Server gameServer;
    private GameClient gameClient;

    public hurryupGame(boolean host)
    {
        this.host = host;
    }
    public hurryupGame(){

	}
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		views.push(new TestLevel());
        System.out.println(System.getProperty("server"));
        if(host) {
            gameServer = new Server(1337);
            System.out.println("Hej");
        }
        else{
            gameClient = new GameClient("127.0.0.1", 1337);
        }


	}
    private float vv = 0;
	@Override
	public void render(){
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		IView viewToDraw = views.peek();

        if(gameServer != null)
            gameServer.Update();
        if(gameClient != null)
        {
            vv += millis() - prevTime;
            if(vv > 1000)
            {
                vv = 0;
                gameClient.sendMessage("Second passed");
            }
            gameClient.update();
        }
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
