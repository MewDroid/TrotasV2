
public class Cliente {
	private String NIF, email, telefone, nome;
	private int saldo, totalMinutos, alugueres, maxMinutos, totalCentimos;
	private boolean promocaoAplicada;

	private Trot trot;

	/**
	 * Inicializacao do nosso cliente.
	 * 
	 * @param NIF
	 * @param email
	 * @param telefone
	 * @param nome
	 */
	public Cliente(String NIF, String email, String telefone, String nome) {
		this.NIF = NIF;
		this.email = email;
		this.telefone = telefone;
		this.nome = nome;
		totalMinutos = 0;
		totalCentimos = 0;
		alugueres = 0;
		maxMinutos = 0;
		saldo = TrotSystem.SALDO_BASE;
		promocaoAplicada = false;
	}

	/**
	 * Criacao e restauro de backups.
	 * 
	 * @param c
	 */
	public Cliente(Cliente c) {
		if (c != null) {
			NIF = c.NIF;
			email = c.email;
			telefone = c.telefone;
			nome = c.nome;
			saldo = c.saldo;
			totalMinutos = c.totalMinutos;
			alugueres = c.alugueres;
			maxMinutos = c.maxMinutos;
			totalCentimos = c.totalCentimos;
		}
	}

	/**
	 * Devolve a trotinete em uso por este cliente.
	 * 
	 * @return
	 */
	public Trot getTrot() {
		return trot;
	}

	/**
	 * Indica a trotinete em uso por este cliente.
	 * 
	 * @param trot
	 */
	public void setTrot(Trot trot) {
		if (trot != null) {
			this.trot = trot;
			trot.setCliente(this);
		} else {
			this.trot = new Trot(null);
			this.trot = null;
		}
	}

	/**
	 * Devolve o NIF do cliente.
	 * 
	 * @return
	 */
	public String getNIF() {
		return NIF;
	}

	/**
	 * Devolve o email do cliente.
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Devolve o numero de telefone do cliente.
	 * 
	 * @return
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Devolve o nome do cliente.
	 * 
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Devolve o saldo.
	 * 
	 * @return
	 */
	public int getSaldo() {
		return saldo;
	}

	/**
	 * Mudar diretamente o saldo.
	 * 
	 * @param saldo
	 */
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	/**
	 * Usado para variar o saldo.
	 * 
	 * @param saldo
	 */
	public void mudarSaldo(int saldo) {
		this.saldo += saldo;
	}

	/**
	 * Devolve o total de minutos gasto em alugueres.
	 * 
	 * @return
	 */
	public int getTotalMinutos() {
		return totalMinutos;
	}

	/**
	 * Somar ao total de minutos.
	 * 
	 * @param minutos
	 */
	public void mudarTotalMinutos(int minutos) {
		this.totalMinutos += minutos;
	}

	/**
	 * Somar ao total de centimos gastos.
	 * 
	 * @param centimos
	 */
	public void mudarTotalCentimos(int centimos) {
		this.totalCentimos += centimos;
	}

	/**
	 * 
	 * @return
	 */
	public int getAlugueres() {
		return alugueres;
	}

	/**
	 * Incrementar o numero total de alugueres realizados.
	 */
	public void incrementarAlugueres() {
		alugueres++;
	}

	/**
	 * Devolve o maior numero de minutos gasto de seguida em alugueres.
	 * 
	 * @return
	 */
	public int getMaxMinutos() {
		return maxMinutos;
	}

	/**
	 * Indica o maior numero de minutos gasto de seguida em alugueres.
	 * 
	 * @param maxMinutos
	 */
	public void setMaxMinutos(int maxMinutos) {
		if (this.maxMinutos < maxMinutos) {
			this.maxMinutos = maxMinutos;
		}
	}

	/**
	 * Calcula a media de minutos de uso por aluguer.
	 * 
	 * @return
	 */
	public int getMedMinutos() {
		int med;
		if (alugueres == 0) {
			med = 0;
		} else {
			med = totalMinutos / alugueres;
		}
		return med;
	}

	/**
	 * Devolve o total de centimos gastos pelo utilizador.
	 * 
	 * @return
	 */
	public int getTotalCentimos() {
		return totalCentimos;
	}

	/**
	 * Devolve o estado da promocao.
	 * 
	 * @return
	 */
	public boolean isPromocaoAplicada() {
		return promocaoAplicada;
	}

	/**
	 * Muda a flag de promocao aplicada.
	 * 
	 * @param promocaoAplicada
	 */
	public void setPromocaoAplicada(boolean promocaoAplicada) {
		this.promocaoAplicada = promocaoAplicada;
	}

}
