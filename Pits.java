import java.util.Hashtable;

public class Pits {

	
	Hashtable<Integer, Integer> hm = new Hashtable<Integer,Integer>();

	public Pits(Hashtable<Integer, Integer> hm) {
		hm.put(1, 0);
		hm.put(2, 6);
		hm.put(3, 6);
		hm.put(4, 6);
		hm.put(5, 6);
		hm.put(6, 6);
		hm.put(7, 6);
		hm.put(8, 0);
		hm.put(9, 6);
		hm.put(9, 6);
		hm.put(10, 6);
		hm.put(11, 6);
		hm.put(12, 6);
		hm.put(13, 6);
		hm.put(14, 6);
		this.hm = hm;
	}

	public Hashtable<Integer, Integer> getHm() {
		return hm;
	}

	public void setHm(Hashtable<Integer, Integer> hm) {
		this.hm = hm;
	}

}
