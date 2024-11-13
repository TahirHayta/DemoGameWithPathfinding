package askerler;

import java.util.ArrayList;
import java.util.List;

import savaşAlanları.SavasAlaniMatrixi;

public abstract class Kilicli extends Asker {
	
	@Override
	public void enYakinDusmanaSaldir(ArrayList<Asker> dusmanlar,SavasAlaniMatrixi savasAlaniMatrixi) { //YAZILACAK
		// eğer arada engel yoksa direkt ona doğru ilerle.
		int[] t=enYakinDusmaninKonumu(dusmanlar); //Sadece bir alt satırda kullandım.
		Node enyakindüsmanNode=new Node((int)t[0]/savasAlaniMatrixi.getBlocksize(), (int)t[1]/savasAlaniMatrixi.getBlocksize(), null);
		Node bizimNode=new Node(this.getX()/savasAlaniMatrixi.getBlocksize(), this.getY()/savasAlaniMatrixi.getBlocksize(), null);
		List<Node> path = AStarPathfinding.findPath(savasAlaniMatrixi.getMapMatrix(), bizimNode, enyakindüsmanNode);
			
		// saldiri fonksiyonunu kullanmak kaldı. bu arada saldıracaksa ilerlememeli, atlılar hariç. 
		if (path.size()>1) {// bir bak kasma sebebi
			birAdimİlerle(path.get(1).nodeToCoordinat(savasAlaniMatrixi.getBlocksize()),savasAlaniMatrixi);
		}
	}
}
