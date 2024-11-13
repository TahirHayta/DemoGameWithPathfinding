package askerler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import savaşAlanları.SavasAlaniMatrixi;

//Bu askerler savaş başlangıcında, bizim ordumuzun içeriğine bağlı oluşturulan askerler.
public abstract class Asker {


	protected int maxHealth;
	protected int defAttack; //default
	protected int defShield;
	protected int defSpeed;
	
	protected int attack;
	protected int shield;
	protected int health;
	protected int speed;
	
	protected int ID;
	protected static int forID=0;
	protected int[] position=new int[2];
	
	protected Texture texture;
	protected Sprite sprite;
	
	
	//*************************************** Önemsiz kolaylaştırıcı fonksiyonlar:
    public void dispose() {
        texture.dispose();
    }
    public void ID_ata() {
		Asker.forID++;
		this.ID=Asker.forID;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getShield() {
		return shield;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public Texture getResim() {
		return texture;
	}
	public void setResim(Texture resim) {
		this.texture = resim;
	}
	public int getX() {
	    return this.position[0];
	}

	public void setX(int x) {
		this.position[0] = x;
	}

	public int getY() {
	    return this.position[1];
	}

	public void setY(int y) {
		this.position[1] = y;
	}
	public Sprite getSprite() {
		return sprite;
	}
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	
	
	//*********************************************** İnherit edilince herkese göre değiştirilebilecek fonksiyonlar
	public void ozellikleriDefaultla() {
		this.health=this.maxHealth;
		this.shield=this.defShield;
		this.attack=this.defAttack;
		this.speed=this.defSpeed;
	}
	public void canAzalt(int azalanCan) {
		this.health=(this.health-azalanCan);
	}
	
	//************************************************Önemli kısımlar
	
	public abstract void enYakinDusmanaSaldir(ArrayList<Asker> dusmanlar,SavasAlaniMatrixi savasAlaniMatrixi);
	
	public void saldiri(Asker savunanAsker) { //DEĞİŞİKLİK YAPILACAK
		int saldiriGucu=this.attack;
		int saldiriEtkisi=0;
		
		if (saldiriGucu<=savunanAsker.getShield()) {
		}
		else {
		saldiriEtkisi=saldiriGucu-savunanAsker.getShield();	
		}
		// Buraları düzenle dengeli bir şekilde.
		savunanAsker.canAzalt(saldiriEtkisi);
	}
	
	public int[] enYakinDusmaninKonumu(ArrayList<Asker> dusmanlar) {
		int mesafe=Integer.MAX_VALUE;
		int[] askerinkonumu= new int[2];
		for (Asker asker : dusmanlar) {
			if (!asker.equals(this)) {
				if((int) Math.sqrt(Math.pow(this.getX() - asker.getX(), 2) + Math.pow(this.getY() - asker.getY(), 2))<mesafe) {
					askerinkonumu[0]= asker.getX();
					askerinkonumu[1]=asker.getY();
				}
			}
		}
		return askerinkonumu;
	}
	
	public void birAdimİlerle(int[] coordinate,SavasAlaniMatrixi savasAlaniMatrixi) {
		// çok fazla ilerlemememesi lazım. hızıyla orantılı ilerleyecek tabi. ama aşırı hızlı olmaması lazım.
		// ortam değişkenini de buraya ekleyeceğiz. şuanlık beyaz eklemek yeterli.
		// Get the current position
		int currentX = this.getX();
	    int currentY = this.getY();

	    // Calculate the differences in x and y
	    int diffX = coordinate[0] - currentX;
	    int diffY = coordinate[1] - currentY;

	    // Check if the target is horizontally, vertically, or diagonally aligned
	    float moveX = 0;
	    float moveY = 0;

	    int speedt=this.speed;
	    if (diffX != 0 && diffY == 0) {
	        // Horizontal movement
	        moveX = Math.signum(diffX) * speedt;  // Move 13 units horizontally
	    } else if (diffY != 0 && diffX == 0) {
	        // Vertical movement
	        moveY = Math.signum(diffY) * speedt;  // Move 13 units vertically
	    } else if (diffX != 0 && diffY != 0) {
	        // Diagonal movement
	        moveX = Math.signum(diffX) * (speedt/2);   // Move 6 units diagonally
	        moveY = Math.signum(diffY) * (speedt/2);
	    }

	    // Update the sprite's position
	    float newX = currentX + moveX;
	    float newY = currentY + moveY;

	    // Ensure the sprite doesn't overshoot the target
	    if (Math.abs(newX - coordinate[0]) < Math.abs(moveX)) {
	        newX = coordinate[0];
	    }
	    if (Math.abs(newY - coordinate[1]) < Math.abs(moveY)) {
	        newY = coordinate[1];
	    }

	    this.setX((int)newX);
	    this.setY((int)newY);
		
	}
	


}
