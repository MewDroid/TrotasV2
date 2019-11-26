public class TrotSystem {
	// Constantes.
	public static final int SALDO_BASE = 200;
	public static final int CUSTO_DE_ALUGUER = 100;
	public static final int PENALIZACAO = 100;
	public static final int TEMPO_LIMITE = 60;
	public static final int INTERVALOS_DE_PENALIZACAO = 30;

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
	 * @param nif
	 * @param email
	 * @param telefone
	 * @param nome
	 * @return
	 */
	public void adicionarCliente(String nif, String email, String telefone, String nome) {
		ItC.append(new Cliente(nif, email, telefone, nome));

	}

	/**
	 * Remover um cliente existente.
	 * @param nif 
	 * 
	 * @param nif
	 * @return
	 */
	public void removerCliente(String nif) {
		ItC.remove(nif);

	}

	/**
	 * Registar nova trotinete.
	 * 
	 * @param idTrot
	 * @param matricula
	 * @return
	 */
	public void adicionarTrot(String idTrot, String matricula) {
		ItT.append(new Trot(idTrot, matricula));

	}

	/**
	 * Carregamento de saldo pelo cliente.
	 * 
	 * @param nif
	 * @param valorCentimos
	 * @return
	 */
	public void adicionarSaldo(String nif,int valorCentimos) {
		ItC.mudarSaldo(nif,valorCentimos);
	}

	/**
	 * Alugar trotinete.
	 * 
	 * @param nif
	 * @param idTrot
	 * @return
	 */
	public void alugarTrot(String nif,String idTrot) {
		ItC.setTrot(nif,ItT.getTrot(ItT.searchTrot(idTrot)));
	}

	/**
	 * Libertar trotinete e fazer o pagamento.
	 * 
	 * @param idTrot
	 * @param minutos
	 * @return
	 */
	public void libertarTrot(String idTrot,int minutos) {
		Cliente cl = ItT.getCliente(idTrot);
		ItC.mudarSaldo(cl,-CUSTO_DE_ALUGUER);
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

			ItC.mudarSaldo(cl,-PENALIZACAO);
			totalCentimos += PENALIZACAO;
			mudarTotalCentimosCliente(PENALIZACAO);
			tempoExtra -= INTERVALOS_DE_PENALIZACAO;
		}
		setTrotDeUtilizador(null);
		setUtilizadorDeTrot(null);
	}

	/**
	 * Desativar trotinete, depois desta acao nao poderao ser feitos alugamentos ate
	 * revertida.
	 * 
	 * @param idTrot
	 * @return
	 */
	public void desativarTrot(String idTrot) {
		ItT.setInativa(idTrot,true);
	}

	/**
	 * Reativacao da trotinete.
	 * 
	 * @param idTrot
	 * @return
	 */
	public void reactivatTrot(String idTrot) {
		ItT.setInativa(idTrot,false);

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
	public String getEmail(String nif) {
		return ItC.getEmail(nif);
	}

	/**
	 * @return
	 */
	public String getTelefone(String nif) {
		return ItC.getTelefone(nif);
	}

	/**
	 * @return
	 */
	public String getNome(String nif) {
		return ItC.getNome(nif);
	}

	/**
	 * @return
	 */
	public int getSaldo(String nif) {
		return ItC.getSaldo(nif);
	}


	/**
	 * @return
	 */
	public String getMatricula(String idTrot) {
		return ItT.getMatricula();
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
	 * @param nif 
	 * @return
	 */
	public Trot getTrotDeUtilizador(String nif) {
		return cliente.getTrot();
	}

	/**
	 * @return
	 */
	public Cliente getUtilizadorDeTrot() {
		return ItT.getUtilizador();
	}

	/**
	 * 
	 */
	public void setUtilizadorDeTrot() {
		trot.setCliente(cliente);
	}

	/**
	 * @param idTrot 
	 * @return
	 */
	public boolean isInativa(String idTrot) {
		return ItC.isInativa(idTrot);
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
	public boolean hasCliente(String nif) {
		return ItC.searchCliente(nif) != -1;
	}

	/**
	 * @return
	 */
	public boolean hasTrot(String idTrot) {
		return ItT.hasTrot(idTrot);
	}

	/**
	 * @return
	 */
	public boolean trotIsLivre(String idTrot) {
		return ItT.livre();
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
		return ItT.getUtilizador() != null;
	}

	/**
	 * @return
	 */
	public String estadoTrot() {
		return ItT.estado();
	}

	/**
	 * @return
	 */
	public int getAlugueresTrot() {
		return ItT.getAlugueres();
	}

	/**
	 * @return
	 */
	public int getTotalMinutosTrot() {
		return ItT.getTotalMinutos();
	}

	public Cliente[] getDevedores() {
		return ItC.getDevedores();
	}

	public void sortClienteNif() {
		ItC.sort();
		
	}
}
