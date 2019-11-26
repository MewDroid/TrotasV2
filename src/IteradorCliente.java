
public class IteradorCliente {
	
	private static final int FACTOR = 3;
	
	private Cliente[] cls;
	private int count;
	
	public IteradorCliente() {
		count = 0;
	}
	
	public Cliente getCliente(int index) {
		return cls[index];
	}
	
	public boolean hasNext() {
		return count < cls.length;
	}
	
	private void resize() {
		if (count > cls.length) {
			Cliente[] array2 = new Cliente[FACTOR * cls.length];
			for (int i = 0; i < cls.length; i++) {
				array2[i] = cls[i];
			}
			cls = array2;
		}
	}
	
	public void append(Cliente cliente) {
		resize();
		cls[count] = cliente;
		count++;
	}

	public Cliente[] getDevedores() {
		Cliente[] cl = new Cliente[cls.length];
		for (int i = 0; i<cls.length; i++) {
			int saldoMax = 1;
			for (int j = 0; j <cls.length; j++) {
				if 
			}
		}
		
		
		return null;
	}
	

}
