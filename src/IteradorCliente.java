
public class IteradorCliente {

	private static final int FACTOR = 3;
	private static final int BASE_SIZE = 10;

	private Cliente[] cls;
	private int count;

	public IteradorCliente() {
		cls = new Cliente[BASE_SIZE];
		count = 0;
	}

	public Cliente getCliente(int index) {
		return cls[index];
	}

	public boolean hasNext() {
		return count < cls.length;
	}

	private void resize() {
		if (count == cls.length) {
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

	public void remove(String nif) {
		for (int i = searchCliente(nif); i < count; i++)
			if (cls.length >i+1)
				cls[i] = cls[i + 1];
		count--;
	}

	public int searchCliente(String nif) {
		int i;
		int id = -1;
		for (i = 0; i < count; i++) {
			if (cls[i].getNif().equalsIgnoreCase(nif)) {
				id = i;
				break;
			}
		}
		return id;
	}

	public int searchCliente(Cliente cl) {
		int i;
		int id = -1;
		for (i = 0; i < count; i++) {
			if (cls[i].getNif().equalsIgnoreCase(cl.getNif())) {
				id = i;
				break;
			}
		}
		return id;
	}

	public Cliente[] getDevedores() {
		Cliente[] cl = new Cliente[cls.length];
		boolean[] used = new boolean[cls.length];
		for (int i = 0; i < count; i++) {
			int saldoMax = 0;
			int j;
			int id = -1;
			for (j = 0; j < count; j++) {
				if (saldoMax > cls[j].getSaldo() && used[j] == false) {
					saldoMax = cls[j].getSaldo();
					id = j;
				}
			}
			if (id > -1) {
				cl[i] = cls[id];
				used[id] = true;
			}
		}

		return cl;
	}

	public void sort() {
		for (int i = 1; i < count; i++)
			for (int j = count - 1; j >= i; j--)
				if (cls[j] != null && cls[j - 1].getNif().compareTo((cls[j].getNif())) > 0) {
					Cliente temp = cls[j - 1];
					cls[j - 1] = cls[j];
					cls[j] = temp;
				}
	}

	public void mudarSaldo(String nif, int valorCentimos) {
		cls[searchCliente(nif)].mudarSaldo(valorCentimos);
	}

	public void mudarSaldo(Cliente cl, int valorCentimos) {
		cls[searchCliente(cl)].mudarSaldo(valorCentimos);
	}

//	public void mudarTotalMinutos(Cliente cl, int minutos) {
//		cls[searchCliente(cl)].mudarTotalMinutos(minutos);
//	}

	public void mudarTotalMinutos(String nif, int minutos) {
		cls[searchCliente(nif)].mudarTotalMinutos(minutos);
	}

	public void setTrot(String nif, Trot trot) {
		cls[searchCliente(nif)].setTrot(trot);
	}

	public void incrementarAlugueres(String nif) {
		cls[searchCliente(nif)].incrementarAlugueres();
	}

	public Trot getTrot(String nif) {
		return cls[searchCliente(nif)].getTrot();
	}

	public int getTotalMinutos(String nif) {
		return cls[searchCliente(nif)].getTotalMinutos();
	}

	public int getAlugueres(String nif) {
		return cls[searchCliente(nif)].getAlugueres();
	}

	public String getEmail(String nif) {
		return cls[searchCliente(nif)].getEmail();
	}

	public String getTelefone(String nif) {
		return cls[searchCliente(nif)].getTelefone();
	}

	public String getNome(String nif) {
		return cls[searchCliente(nif)].getNome();

	}

	public int getSaldo(String nif) {
		return cls[searchCliente(nif)].getSaldo();

	}

	public void setMaxMinutos(String nif, int amount) {
		cls[searchCliente(nif)].setMaxMinutos(amount);
	}

	public void mudarTotalCentimos(String nif, int amount) {
		cls[searchCliente(nif)].mudarTotalCentimos(amount);
	}

	public int getTotalCentimos(String nif) {
		return cls[searchCliente(nif)].getTotalCentimos();
	}

	public int getMaxMinutos(String nif) {
		return cls[searchCliente(nif)].getMaxMinutos();
	}

	public int getMedMinutos(String nif) {
		return cls[searchCliente(nif)].getMedMinutos();
	}

	public String getNif(int id) {
		return cls[id].getNif();
	}

	public String getNif(String nif) {
		return cls[searchCliente(nif)].getNif();
	}

	public int getCount() {
		return count;
	}

}
