
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

	public void remove(String NIF) {
		cls[searchCliente(NIF)] = null;
	}
	
	public int searchCliente(String NIF) {
        int i;
        int cl = -1;
        for (i = 0; i < count; i++) {
            if (cls[i].getNIF().equals(NIF)) {
                cl = i;
                break;
            }
        }
        return cl;
    }
	
	public Cliente[] getDevedores() {
		Cliente[] cl = new Cliente[cls.length];
		boolean[] used = new boolean[cls.length];
		for (int i = 0; i<count; i++) {
			int saldoMax = 1;
			int j;
			for (j = 0; j <count; j++) {
				if (saldoMax >cls[j].getSaldo() && used[j] == false) {
					saldoMax = cls[j].getSaldo();
				}
			}
			cl[i] = cls[j];
			used[j] = true;
		}
		
		
		return cl;
	}
	
	public void sort() {
		for (int i = 0; i<count; i++) {
			if (cls[i].getNIF().compareToIgnoreCase(cls[i+1].getNIF()) > 0) {
				Cliente t = cls[i+1];
				cls[i+1] = cls[i];
				cls[i] = t;
			}
		}
	}

	
}
