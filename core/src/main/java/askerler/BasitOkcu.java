package askerler;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import savaşAlanları.SavasAlaniMatrixi;

public class BasitOkcu extends Okcu {
	
	public BasitOkcu() {
		this.maxHealth=100;
		this.defAttack=10;
		this.defShield=0;
		this.defSpeed=10;
		this.ozellikleriDefaultla();
		this.ID_ata();
		this.texture = new Texture(Gdx.files.internal("Pictures/ArrowCircle.png"));
		this.sprite=new Sprite(texture);
		sprite.setSize(60, 60);
	}

	@Override
	public void enYakinDusmanaSaldir(ArrayList<Asker> dusmanlar, SavasAlaniMatrixi savasAlaniMatrixi) {
		// TODO Auto-generated method stub
		
	}
}
