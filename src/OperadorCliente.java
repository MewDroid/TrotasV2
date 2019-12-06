/**
 * @author Guilherme Cipriano Carvalho.
 *
 */
public class OperadorCliente {

	private static final int FACTOR = 3;
	private static final int BASE_SIZE = 10;

	private Cliente[] cls;
	private int count;

	/**
	 * Construtor da classe.
	 */
	public OperadorCliente() {
		cls = new Cliente[BASE_SIZE];
		count = 0;
	}

	/**
	 * Devolve o cliente da lista.
	 * 
	 * @param index
	 * @return
	 */
	public Cliente getCliente(int index) {
		return cls[index];
	}


	/**
	 * Muda o tamanho da lista.
	 */
	private void resize() {
		if (count == cls.length) {
			Cliente[] array2 = new Cliente[FACTOR * cls.length];
			for (int i = 0; i < cls.length; i++) {
				array2[i] = cls[i];
			}
			cls = array2;
		}
	}

	/**
	 * Acresenta o cliente.
	 * 
	 * @param cliente
	 */
	public void append(Cliente cliente) {
		resize();
		cls[count] = cliente;
		count++;
	}

	/**
	 * Remove o cliente.
	 * @param nif
	 */
	public void remove(String nif) {
		for (int i = searchCliente(nif); i < count; i++)
			if (cls.length >i+1)
				cls[i] = cls[i + 1];
		count--;
	}

	/**
	 * Devolve o index do cliente.
	 * 
	 * @param nif
	 * @return
	 */
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

	/**
	 * Devolve o index do cliente.
	 * 
	 * @param cl
	 * @return
	 */
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

	/**
	 * Devolve os clientes devedores organizados.
	 * 
	 * @return
	 */
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

	/**
	 * Organiza os clientes com base no NIF.
	 */
	public void sort() {
		for (int i = 1; i < count; i++)
			for (int j = count - 1; j >= i; j--)
				if (cls[j] != null && cls[j - 1].getNif().compareTo((cls[j].getNif())) > 0) {
					Cliente temp = cls[j - 1];
					cls[j - 1] = cls[j];
					cls[j] = temp;
				}
	}

	/**
	 * Muda o saldo do cliente.
	 * 
	 * @param nif
	 * @param valorCentimos
	 */
	public void mudarSaldo(String nif, int valorCentimos) {
		cls[searchCliente(nif)].mudarSaldo(valorCentimos);
	}

	/**
	 * Muda o saldo do cliente.
	 * 
	 * @param cl
	 * @param valorCentimos
	 */
	public void mudarSaldo(Cliente cl, int valorCentimos) {
		cls[searchCliente(cl)].mudarSaldo(valorCentimos);
	}

	/**
	 * Muda a trotinete em uso pelo cliente.
	 * 
	 * @param nif
	 * @param trot
	 */
	public void setTrot(String nif, Trot trot) {
		cls[searchCliente(nif)].setTrot(trot);
	}

	/**
	 * Aumenta o numero de alugueres efetuados.
	 * 
	 * @param nif
	 */
	public void incrementarAlugueres(String nif) {
		cls[searchCliente(nif)].incrementarAlugueres();
	}

	/**
	 * Devolve a trotinete em uso pelo cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public Trot getTrot(String nif) {
		return cls[searchCliente(nif)].getTrot();
	}

	/**
	 * Devolve o numero total de minutos em movimento de um cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getTotalMinutos(String nif) {
		return cls[searchCliente(nif)].getTotalMinutos();
	}
	
	/**
	 * Aumenta o numero total de minutos em movimento de um cliente.
	 * @param nif
	 * @param minutos
	 */
	public void mudarTotalMinutos(String nif, int minutos) {
		cls[searchCliente(nif)].mudarTotalMinutos(minutos);
	}

	/**
	 * Devolve o numero de alugueres realizdos pelo cliente.
	 * @param nif
	 * @return
	 */
	public int getAlugueres(String nif) {
		return cls[searchCliente(nif)].getAlugueres();
	}

	/**
	 * Devolve o email do cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public String getEmail(String nif) {
		return cls[searchCliente(nif)].getEmail();
	}

	/**
	 * Devolve o numero de telefone do cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public String getTelefone(String nif) {
		return cls[searchCliente(nif)].getTelefone();
	}

	/**
	 * Devolve o nome do cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public String getNome(String nif) {
		return cls[searchCliente(nif)].getNome();
	}

	/**
	 * Devolve o saldo do cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getSaldo(String nif) {
		return cls[searchCliente(nif)].getSaldo();
	}

	/**
	 * Aumenta o numero maximo de minutos de uso do sistema pelo cliente.
	 * 
	 * @param nif
	 * @param amount
	 */
	public void setMaxMinutos(String nif, int amount) {
		cls[searchCliente(nif)].setMaxMinutos(amount);
	}

	/**
	 * Aumenta o numero total de centimos gastos pelo cliente.
	 * 
	 * @param nif
	 * @param amount
	 */
	public void mudarTotalCentimos(String nif, int amount) {
		cls[searchCliente(nif)].mudarTotalCentimos(amount);
	}

	/**
	 * Devolve o numero total de centimos gasto pelo cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getTotalCentimos(String nif) {
		return cls[searchCliente(nif)].getTotalCentimos();
	}

	/**
	 * Devolve o numero maximo de minutos de uso do sistema pelo cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getMaxMinutos(String nif) {
		return cls[searchCliente(nif)].getMaxMinutos();
	}

	/**
	 * Devolve a media dos minutos de uso do sistema pelo cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getMedMinutos(String nif) {
		return cls[searchCliente(nif)].getMedMinutos();
	}

	/**
	 * Devolve o NIF do cliente com o index dado.
	 * 
	 * @param id
	 * @return
	 */
	public String getNif(int id) {
		return cls[id].getNif();
	}

	/**
	 * Devolve o NIF do cliente com o NIF dado.
	 * @param nif
	 * @return
	 */
	public String getNif(String nif) {
		return cls[searchCliente(nif)].getNif();
	}
	
	/**
	 * Devolve o numero de clientes.
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

}
