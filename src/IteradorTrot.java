
public class IteradorTrot {

	private static final int FACTOR = 3;
	
	private Trot[] trts;
	private int count;
	
	public IteradorTrot() {
		count = 0;
	}
	
	public Trot getTrot(int index) {
		return trts[index];
	}
	
	public boolean hasNext() {
		return count < trts.length;
	}
	
	private void resize() {
		if (count > trts.length) {
			Trot[] array2 = new Trot[FACTOR * trts.length];
			for (int i = 0; i < trts.length; i++) {
				array2[i] = trts[i];
			}
			trts = array2;
		}
	}
	
	public void append(Trot trot) {
		resize();
		trts[count] = trot;
		count++;
	}


	public boolean hasTrot(String NIF) {
	        int i;
	        boolean value = false;
	        for(i = 0; i < count; i++) {
	            if (trts[i] != null && trts[i].getIdTrot().equals(NIF)) {
	            	value = true;
	            }
	        }
	        return value;
	    }
	
}
