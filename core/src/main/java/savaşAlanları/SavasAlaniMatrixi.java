package savaşAlanları;

import java.awt.Color;
import java.nio.ByteBuffer;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;


public class SavasAlaniMatrixi {

	private byte[][] mapMatrix; // Engel varsa 1 yazar. 
    private int width, height;
    private int blocksize=15;
    private Texture texture;
    
    public int getBlocksize() {
		return blocksize;
	}
	public void setBlocksize(int blocksize) {
		this.blocksize = blocksize;
	}
	
	
	
	
	public SavasAlaniMatrixi(String filePath) {
		this.texture = new Texture(Gdx.files.internal(filePath));
        this.width = texture.getWidth() / blocksize;
        this.height = texture.getHeight() / blocksize;
        this.mapMatrix = new byte[height][width];
        generateMapMatrix();
    }
	
	
	
	public void generateMapMatrix() {
    	this.texture.getTextureData().prepare();
        Pixmap pixmap = this.texture.getTextureData().consumePixmap();
        
        
        for (int y = 0; y < this.height; y++) {
            for (int x = 0; x < this.width; x++) {
                boolean hasBlack = false;
                for (int py = 0; py < this.blocksize; py++) {
                    for (int px = 0; px < this.blocksize; px++) {
                        int pixelX = x * this.blocksize + px;
                        int pixelY = y * this.blocksize + py;
                        int pixel = pixmap.getPixel(pixelX, pixelY);
                        Color color = new Color(pixel);
                        
                        if (color.equals(Color.black)) {
                            hasBlack = true;
                            break;
                        }
                    }
                    if (hasBlack) {
                    	mapMatrix[y][x] =1;
                    	break;
                    }
                }
                
            }
        }
        pixmap.dispose();
        
        for (int i = 0; i < mapMatrix.length; i++) { //*****************SONRA SİL. map doğru üretilmiyor
        	for (int j = 0; j < mapMatrix[0].length; j++) {
				System.out.print(mapMatrix[i][j]);
			}
        	System.out.println();
		}
    }

    public byte[][] getMapMatrix() {
        return mapMatrix;
    }
    public boolean isGecebilir(int x, int y) {
        return this.mapMatrix[x][y] == 0;
    }
}
