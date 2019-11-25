public class TrotSystem {
	// Constantes.
	public static final int SALDO_BASE = 200;
	public static final int CUSTO_DE_ALUGUER = 100;
	public static final int PENALIZACAO = 100;
	public static final int TEMPO_LIMITE = 60;
	public static final int INTERVALOS_DE_PENALIZACAO = 30;

	// Clientes e Trotinetes.
	private Cliente cliente;
	private Trot trot;

	// Iteradores
	private IteradorCliente ItC;
	private IteradorTrot ItT;
	
	// Backups.
	private Cliente clienteBackup;
	private Trot trotBackup;
	private TrotSystem trotSystemBackup;

	// Dados estatisticos.
	private int alugueres;
	private int totalCentimos;
	private int atrasos;

	private int bAlugueres;
	private int bTotalCentimos;
	private int bAtrasos;

	/**
	 * Construtor do sistema.
	 */
	public TrotSystem() {
		alugueres = 0;
		totalCentimos = 0;
		atrasos = 0;
	}

	/**
	 * Criacao de um novo cliente.
	 * 
	 * @param NIF
	 * @param email
	 * @param telefone
	 * @param nome
	 * @return
	 */
	public void adicionarCliente(String NIF, String email, String telefone, String nome) {
		cliente = new Cliente(NIF, email, telefone, nome);

	}

	/**
	 * Remover um cliente existente.
	 * 
	 * @param NIF
	 * @return
	 */
	public void removerCliente() {
		cliente = null;

	}

	/**
	 * Registar nova trotinete.
	 * 
	 * @param idTrot
	 * @param matricula
	 * @return
	 */
	public void adicionarTrot(String idTrot, String matricula) {
		trot = new Trot(idTrot, matricula);

	}

	/**
	 * Carregamento de saldo pelo cliente.
	 * 
	 * @param NIF
	 * @param valorCentimos
	 * @return
	 */
	public void adicionarSaldo(int valorCentimos) {
		cliente.mudarSaldo(valorCentimos);
	}

	/**
	 * Alugar trotinete.
	 * 
	 * @param NIF
	 * @param idTrot
	 * @return
	 */
	public void alugarTrot() {
		trotBackup = new Trot(trot);
		clienteBackup = new Cliente(cliente);
		bAlugueres = alugueres;
		bAtrasos = atrasos;
		bTotalCentimos = totalCentimos;
		setTrotDeUtilizador(trot);
	}

	/**
	 * Libertar trotinete e fazer o pagamento.
	 * 
	 * @param idTrot
	 * @param minutos
	 * @return
	 */
	public void libertarTrot(int minutos) {

		adicionarSaldo(-CUSTO_DE_ALUGUER);
		alugueres++;
		incrementarAlugueresCliente();
		mudarTotalMinutosCliente(minutos);

		mudarTotalMinutosTrot(minutos);
		incrementarAlugueresTrot();

		totalCentimos += CUSTO_DE_ALUGUER;
		mudarTotalCentimosCliente(CUSTO_DE_ALUGUER);
		setMaxMinutosCliente(minutos);
		int tempoExtra = minutos - TEMPO_LIMITE;
		atrasos += tempoExtra;
		while (tempoExtra > 0) {

			adicionarSaldo(-PENALIZACAO);
			totalCentimos += PENALIZACAO;
			mudarTotalCentimosCliente(PENALIZACAO);
			tempoExtra -= INTERVALOS_DE_PENALIZACAO;
		}
		setTrotDeUtilizador(null);
		setUtilizadorDeTrot(null);
	}

	/**
	 * Aplicar promocao, esta restora o sistema inteiro para antes do ultimo
	 * aluguer, tambem pode ser vista como restaurar um backup.
	 * 
	 * @param NIF
	 * @return
	 */
	public void promo() {
		cliente = new Cliente(clienteBackup);
		trot = new Trot(trotBackup);
		cliente.setPromocaoAplicada(true);
		atrasos = bAtrasos;
		alugueres = bAlugueres;
		totalCentimos = bTotalCentimos;
	}

	/**
	 * Desativar trotinete, depois desta acao nao poderao ser feitos alugamentos ate
	 * revertida.
	 * 
	 * @param idTrot
	 * @return
	 */
	public void desativarTrot(String idTrot) {
		trot.setInativa(true);
	}

	/**
	 * Reativacao da trotinete.
	 * 
	 * @param idTrot
	 * @return
	 */
	public void reactivatTrot(String idTrot) {
		trot.setInativa(false);

	}

	// Segue-se um conjunto de SetGets.

	/**
	 * @return
	 */
	public TrotSystem getTrotSystemBackup() {
		return trotSystemBackup;
	}

	/**
	 * @return
	 */
	public String getNIF() {
		return cliente.getNIF();
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return cliente.getEmail();
	}

	/**
	 * @return
	 */
	public String getTelefone() {
		return cliente.getTelefone();
	}

	/**
	 * @return
	 */
	public String getNome() {
		return cliente.getNome();
	}

	/**
	 * @return
	 */
	public int getSaldo() {
		return cliente.getSaldo();
	}

	/**
	 * @return
	 */
	public String getId() {
		return trot.getIdTrot();
	}

	/**
	 * @return
	 */
	public String getMatricula() {
		return trot.getMatricula();
	}

	/**
	 * @return
	 */
	public int getTotalMinutosCliente() {
		return cliente.getTotalMinutos();
	}

	/**
	 * @param minutos
	 */
	public void mudarTotalMinutosCliente(int minutos) {
		cliente.mudarTotalMinutos(minutos);
	}

	/**
	 * @return
	 */
	public int getAlugueresCliente() {
		return cliente.getAlugueres();
	}

	/**
	 * 
	 */
	public void incrementarAlugueresCliente() {
		cliente.incrementarAlugueres();
	}

	/**
	 * @return
	 */
	public Trot getTrotDeUtilizador() {
		return cliente.getTrot();
	}

	/**
	 * 
	 */
	public void setTrotDeUtilizador() {
		cliente.setTrot(trot);
	}

	/**
	 * @return
	 */
	public Cliente getUtilizadorDeTrot() {
		return trot.getUtilizador();
	}

	/**
	 * 
	 */
	public void setUtilizadorDeTrot() {
		trot.setCliente(cliente);
	}

	/**
	 * @return
	 */
	public boolean isInativa() {
		return trot.isInativa();
	}

	/**
	 * @param i
	 */
	public void setInativa(boolean i) {
		trot.setInativa(i);
	}

	/**
	 * @return
	 */
	public boolean hasCliente(String NIF) {
		return ItC.searchNIF(NIF);
	}

	/**
	 * @return
	 */
	public boolean hasTrot(String idTrot) {
		return ItT.searchTrot(idTrot);
	}

	/**
	 * @return
	 */
	public boolean trotIsLivre() {
		return trot.livre();
	}

	/**
	 * @return
	 */
	public boolean isPromoAplicada() {
		return cliente.isPromocaoAplicada();
	}

	/**
	 * @return
	 */
	public int getAlugueres() {
		return alugueres;
	}

	/**
	 * @return
	 */
	public int getTotalCentimos() {
		return totalCentimos;
	}

	/**
	 * @return
	 */
	public int getAtrasos() {
		return atrasos;
	}

	/**
	 * @param c
	 */
	private void setUtilizadorDeTrot(Cliente c) {
		trot.setCliente(c);

	}

	/**
	 * @param t
	 */
	private void setTrotDeUtilizador(Trot t) {
		cliente.setTrot(t);

	}

	/**
	 * @param minutos
	 */
	private void setMaxMinutosCliente(int minutos) {
		cliente.setMaxMinutos(minutos);
	}

	/**
	 * @param centimos
	 */
	private void mudarTotalCentimosCliente(int centimos) {
		cliente.mudarTotalCentimos(centimos);

	}

	/**
	 * 
	 */
	private void incrementarAlugueresTrot() {
		trot.incrementarAlugueres();
	}

	/**
	 * @param minutos
	 */
	private void mudarTotalMinutosTrot(int minutos) {
		trot.mudarTotalMinutos(minutos);
	}

	/**
	 * @return
	 */
	public int getTotalCentimosCliente() {
		return cliente.getTotalCentimos();
	}

	/**
	 * @return
	 */
	public int getMaxMinutosCliente() {
		return cliente.getMaxMinutos();
	}

	/**
	 * @return
	 */
	public int getMedMinutosCliente() {
		return cliente.getMedMinutos();
	}

	/**
	 * @return
	 */
	public boolean clienteHasTrot() {
		return cliente.getTrot() != null;
	}

	/**
	 * @return
	 */
	public boolean trotHasCliente() {
		return trot.getUtilizador() != null;
	}

	/**
	 * @return
	 */
	public String estadoTrot() {
		return trot.estado();
	}

	/**
	 * @return
	 */
	public int getAlugueresTrot() {
		return trot.getAlugueres();
	}

	/**
	 * @return
	 */
	public int getTotalMinutosTrot() {
		return trot.getTotalMinutos();
	}
}
