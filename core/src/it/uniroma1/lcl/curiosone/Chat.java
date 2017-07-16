package it.uniroma1.lcl.curiosone;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import it.uniroma1.lcl.curiosone.states.FinestraChat;
import it.uniroma1.lcl.curiosone.states.GameStateManager;

public class Chat extends ApplicationAdapter {
	SpriteBatch batch;
	public static  int WIDTH=480;
	public static  int HEIGHT=800;
	public static final String TITLE="Curiosone";

	private ArrayList<Label> storico;
	
	private GameStateManager gsm;
	
	
	@Override
	public void create () {
		//resize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		storico=new ArrayList<Label>();
		batch = new SpriteBatch();
		gsm=new GameStateManager();



		gsm.push(new FinestraChat(gsm,storico));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		batch.begin();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void resize(int width,int height)
	{
		WIDTH=width;
		HEIGHT=height;
	}
}
