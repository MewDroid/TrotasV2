
public class Trot {
	private String idTrot, matricula;
	private boolean alugada;
	private int alugueres, totalMinutos;
	private Cliente cliente;
	private boolean inativa;

	/**
	 * Inicializacao da trotinete.
	 * 
	 * @param idTrot
	 * @param matricula
	 */
	public Trot(String idTrot, String matricula) {
		this.setIdTrot(idTrot);
		this.setMatricula(matricula);
		inativa = false;
	}

	/**
	 * Criacao e restauro de backups.
	 * 
	 * @param t
	 */
	public Trot(Trot t) {
		if (t != null) {
			idTrot = t.idTrot;
			matricula = t.matricula;
			alugada = t.alugada;
			alugueres = t.alugueres;
			totalMinutos = t.totalMinutos;
			cliente = null;
			inativa = t.inativa;
		}
	}

	/**
	 * Verificacao se existe cliente a usar.
	 * 
	 * @return
	 */
	public boolean livre() {
		boolean livre;
		if (cliente == null) {
			livre = true;
		} else {
			livre = false;
		}
		return livre;
	}

	/**
	 * Verificacao do estado da trotinete.
	 * 
	 * @return
	 */
	public String estado() {
		String estado;
		if (!livre()) {
			estado = "alugada";
		} else if (inativa) {
			estado = "inactiva";
		} else {
			estado = "parada";
		}
		return estado;
	}

	/**
	 * Devolve o id da Trotinete.
	 * 
	 * @return
	 */
	public String getIdTrot() {
		return idTrot;
	}

	/**
	 * Atribuicao do id a trotinete.
	 * 
	 * @param idTrot
	 */
	public void setIdTrot(String idTrot) {
		this.idTrot = idTrot;
	}

	/**
	 * Devolve a matricula.
	 * 
	 * @return
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Atribui a matricula.
	 * 
	 * @param matricula
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Devolve o numero de alugueres efectuados a esta trotinete.
	 * 
	 * @return
	 */
	public int getAlugueres() {
		return alugueres;
	}

	/**
	 * Incrementa o numero de alugueres efectuados a esta trotinete.
	 */
	public void incrementarAlugueres() {
		alugueres++;
	}

	/**
	 * Devolve o numero total de minutos em que esta trotinete esteve em uso.
	 * 
	 * @return
	 */
	public int getTotalMinutos() {
		return totalMinutos;
	}

	/**
	 * Acrescenta o numero total de minutos em que esta trotinete esteve em uso.
	 * 
	 * @param totalMinutos
	 */
	public void mudarTotalMinutos(int totalMinutos) {
		this.totalMinutos += totalMinutos;
	}

	/**
	 * Devolve se esta alugada.
	 * 
	 * @return
	 */
	public boolean getAlugada() {
		return alugada;
	}

	/**
	 * Indica se esta alugada
	 * 
	 * @param alugada
	 */
	public void setAlugada(boolean alugada) {
		this.alugada = alugada;
	}

	/**
	 * Devolve o utilizador da trotinete.
	 * 
	 * @return
	 */
	public Cliente getUtilizador() {
		return cliente;
	}

	/**
	 * Indica o utilizador da trotinete.
	 * 
	 * @param utilizador
	 */
	public void setCliente(Cliente utilizador) {
		if (utilizador != null) {
			this.cliente = utilizador;
		} else {
			this.cliente = new Cliente(null);
			this.cliente = null;
		}
	}

	/**
	 * Devolve se esta inactiva.
	 * 
	 * @return
	 */
	public boolean isInativa() {
		return inativa;
	}

	/**
	 * Indica se esta inactiva.
	 * 
	 * @param inativa
	 */
	public void setInativa(boolean inativa) {
		this.inativa = inativa;
	}
}
