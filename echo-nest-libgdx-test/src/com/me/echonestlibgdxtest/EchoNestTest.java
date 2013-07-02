package com.me.echonestlibgdxtest;

import java.util.List;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.echonest.api.v4.Artist;
import com.echonest.api.v4.Audio;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.News;
import com.echonest.api.v4.Song;
import com.echonest.api.v4.Video;


public class EchoNestTest implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private SpriteBatch fontBatch;
	private Texture texture;
	private Sprite sprite;
	private EchoNestAPI echoNest;
	private List<Artist> artists;;
	private BitmapFont myFont;
	private String output = "";
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		echoNest = new EchoNestAPI("PQCOTT9Z5LTEYXMQW"); //load from file or something		
		
		try {
			artists = echoNest.searchArtists("Foo fighters");
			if(!artists.isEmpty())
			{				
				Artist fooFighters = artists.get(0);
				
				output += "Artist: " + fooFighters.getName() + "\n";
				
				for (Song song : fooFighters.getSongs()) {
					output += "title: " + song.getTitle() + "    tempo: " + song.getTempo() + "\n";					
				}
				
				System.out.println(output);				
			}
		} catch (EchoNestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myFont = new BitmapFont(Gdx.files.internal("data/myFont3.fnt"), false);
//		myFont.setScale(1);
		myFont.setColor(Color.YELLOW);
		
		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
		fontBatch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/libgdx.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
//		TextureRegion region = new TextureRegion(texture, 0, 0, 512, 275);
		
//		sprite = new Sprite(region);
//		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
//		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
//		sprite.setPosition(-sprite.getWidth()/2, -sprite.getHeight()/2);
	}

	@Override
	public void dispose() {
		batch.dispose();
		texture.dispose();
		myFont.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
//		sprite.draw(batch);		
		batch.end();
		
		fontBatch.begin();
		myFont.drawMultiLine(fontBatch, output, 200, Gdx.graphics.getHeight() - 50);
		fontBatch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
