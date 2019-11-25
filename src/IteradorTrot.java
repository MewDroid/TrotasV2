
public class IteradorTrot {

	private static final int FACTOR = 3;
	
	private Trot[] array1;
	private int count;
	
	public IteradorTrot() {
		count = 0;
	}
	
	public Trot getTrot(int index) {
		return array1[index];
	}
	
	public boolean hasNext() {
		return count < array1.length;
	}
	
	private void resize() {
		if (count > array1.length) {
			Trot[] array2 = new Trot[FACTOR * array1.length];
			for (int i = 0; i < array1.length; i++) {
				array2[i] = array1[i];
			}
			array1 = array2;
		}
	}
	
	public void append(Trot trot) {
		resize();
		array1[count] = trot;
		count++;
	}
	
}
