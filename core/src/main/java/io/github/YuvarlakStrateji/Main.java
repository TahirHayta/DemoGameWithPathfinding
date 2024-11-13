package io.github.YuvarlakStrateji;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import askerler.Asker;
import askerler.BasitOkcu;
import askerler.BasitPiyade;
import savaşAlanları.SavasAlaniMatrixi;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Asker asker1;
    private Asker asker2;
    private Texture backgroundImage;
    private ArrayList<Asker> askerler=new ArrayList<>();
    private SavasAlaniMatrixi savasAlaniMatrixi;

    @Override
    public void create() {
        batch = new SpriteBatch();
        askerleriOlustur();
        backgroundImage = new Texture("Pictures/SampleMap.jpg");
        this.savasAlaniMatrixi=new SavasAlaniMatrixi("Pictures/SampleMap.jpg");
    }

    @Override
    public void render() {
        ScreenUtils.clear(1f, 1f, 1f, 1f);
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        askerleriCiz();
        hareketEtmek();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        askerleriSerbestBirak();
        backgroundImage.dispose();
    }
    
    
    private void askerleriOlustur() {
    	asker1=new BasitPiyade();
        asker2=new BasitOkcu();
        this.askerler.add(asker1);
        this.askerler.add(asker2);
        asker1.setX(140);
        asker1.setY(210);
        asker2.setX(800);
        asker2.setY(210);
    }
    
    private void askerleriCiz() {
    	asker1.getSprite().setPosition(asker1.getX(), asker1.getY());
    	asker1.getSprite().draw(batch);
    	asker2.getSprite().setPosition(asker2.getX(), asker2.getY());
    	asker2.getSprite().draw(batch);
    	asker1.enYakinDusmanaSaldir(this.askerler, savasAlaniMatrixi);
    }
    
    private void askerleriSerbestBirak() {
        asker1.dispose();
        asker2.dispose();
    }
    private void hareketEtmek() {
    	boolean x=true;
    	int t=1000;
    	if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
    		asker2.setX((int)(asker2.getX()-t * Gdx.graphics.getDeltaTime()));
    		x=false;
    	}
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	asker2.setX((int)(asker2.getX()+t * Gdx.graphics.getDeltaTime()));
        	x=false;
        }
        if (x) {
        	if(Gdx.input.isKeyPressed(Input.Keys.UP)) asker2.setY((int)(asker2.getY()+t * Gdx.graphics.getDeltaTime()));
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) asker2.setY((int)(asker2.getY()-t * Gdx.graphics.getDeltaTime()));

		}
        
    }
    
}
