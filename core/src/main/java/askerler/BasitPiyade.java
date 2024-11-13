package askerler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class BasitPiyade extends Kilicli {

	public BasitPiyade() {
		this.maxHealth=100;
		this.defAttack=10;
		this.defShield=4;
		this.defSpeed=10;
		this.ozellikleriDefaultla();
		this.ID_ata();
		this.texture = new Texture(Gdx.files.internal("Pictures/SwordCircle.png"));
		this.sprite=new Sprite(texture);
		sprite.setSize(60, 60);
	}
}
