
public class IteradorCliente {
	
	private static final int FACTOR = 3;
	
	private Cliente[] array1;
	private int count;
	
	public IteradorCliente() {
		count = 0;
	}
	
	public Cliente getCliente(int index) {
		return array1[index];
	}
	
	public boolean hasNext() {
		return count < array1.length;
	}
	
	private void resize() {
		if (count > array1.length) {
			Cliente[] array2 = new Cliente[FACTOR * array1.length];
			for (int i = 0; i < array1.length; i++) {
				array2[i] = array1[i];
			}
			array1 = array2;
		}
	}
	
	public void append(Cliente cliente) {
		resize();
		array1[count] = cliente;
		count++;
	}
	

}
