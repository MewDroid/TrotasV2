public class TrotSystem {
	// Constantes.
	public static final int SALDO_BASE = 200;
	public static final int CUSTO_DE_ALUGUER = 100;
	public static final int PENALIZACAO = 100;
	public static final int TEMPO_LIMITE = 60;
	public static final int INTERVALOS_DE_PENALIZACAO = 30;

	// Iteradores
	private IteradorCliente itc;
	private IteradorTrot itt;
	
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
		itc.append(new Cliente(nif, email, telefone, nome));

	}

	/**
	 * Remover um cliente existente.
	 * @param nif 
	 * 
	 * @param nif
	 * @return
	 */
	public void removerCliente(String nif) {
		itc.remove(nif);

	}

	/**
	 * Registar nova trotinete.
	 * 
	 * @param idTrot
	 * @param matricula
	 * @return
	 */
	public void adicionarTrot(String idTrot, String matricula) {
		itt.append(new Trot(idTrot, matricula));

	}

	/**
	 * Carregamento de saldo pelo cliente.
	 * 
	 * @param nif
	 * @param valorCentimos
	 * @return
	 */
	public void adicionarSaldo(String nif,int valorCentimos) {
		itc.mudarSaldo(nif,valorCentimos);
	}

	/**
	 * Alugar trotinete.
	 * 
	 * @param nif
	 * @param idTrot
	 * @return
	 */
	public void alugarTrot(String nif,String idTrot) {
		itc.setTrot(nif,itt.getTrot(itt.searchTrot(idTrot)));
	}

	/**
	 * Libertar trotinete e fazer o pagamento.
	 * 
	 * @param idTrot
	 * @param minutos
	 * @return
	 */
	public void libertarTrot(String idTrot,int minutos) {
		String nif = itt.getCliente(idTrot).getNif();
		itc.mudarSaldo(nif,-CUSTO_DE_ALUGUER);
		alugueres++;
		itc.incrementarAlugueres(nif);
		itc.mudarTotalMinutos(nif,minutos);

		itt.mudarTotalMinutos(idTrot,minutos);
		itt.incrementarAlugueres(idTrot);

		totalCentimos += CUSTO_DE_ALUGUER;
		itc.mudarTotalCentimos(nif,CUSTO_DE_ALUGUER);
		itc.setMaxMinutos(nif,minutos);
		int tempoExtra = minutos - TEMPO_LIMITE;
		atrasos += tempoExtra;
		while (tempoExtra > 0) {

			itc.mudarSaldo(nif,-PENALIZACAO);
			totalCentimos += PENALIZACAO;
			itc.mudarTotalCentimos(nif,PENALIZACAO);
			tempoExtra -= INTERVALOS_DE_PENALIZACAO;
		}
		itc.setTrot(nif, null);
		itt.setCliente(idTrot,null);
	}

	/**
	 * Desativar trotinete, depois desta acao nao poderao ser feitos alugamentos ate
	 * revertida.
	 * 
	 * @param idTrot
	 * @return
	 */
	public void desativarTrot(String idTrot) {
		itt.setInativa(idTrot,true);
	}

	/**
	 * Reativacao da trotinete.
	 * 
	 * @param idTrot
	 * @return
	 */
	public void reactivatTrot(String idTrot) {
		itt.setInativa(idTrot,false);

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
		return itc.getEmail(nif);
	}

	/**
	 * @return
	 */
	public String getTelefone(String nif) {
		return itc.getTelefone(nif);
	}

	/**
	 * @return
	 */
	public String getNome(String nif) {
		return itc.getNome(nif);
	}

	/**
	 * @return
	 */
	public int getSaldo(String nif) {
		return itc.getSaldo(nif);
	}


	/**
	 * @return
	 */
	public String getMatricula(String idTrot) {
		return itt.getMatricula(idTrot);
	}

	/**
	 * @return
	 */
	public int getTotalMinutosCliente(String nif) {
		return itc.getTotalMinutos(nif);
	}

	/**
	 * @param minutos
	 */
	public void mudarTotalMinutosCliente(String nif,int minutos) {
		itc.mudarTotalMinutos(nif,minutos);
	}

	/**
	 * @return
	 */
	public int getAlugueresCliente(String nif) {
		return itc.getAlugueres(nif);
	}

	

	/**
	 * @param nif 
	 * @return
	 */
	public Trot getTrotDeUtilizador(String nif) {
		return itc.getTrot(nif);
	}

	/**
	 * @return
	 */
	public Cliente getUtilizadorDeTrot(String idTrot) {
		return itt.getUtilizador(idTrot);
	}

	/**
	 * 
	 */
	public void setUtilizadorDeTrot(String idTrot, Cliente cl) {
		itt.setCliente(idTrot,cl);
	}

	/**
	 * @param idTrot 
	 * @return
	 */
	public boolean isInativa(String idTrot) {
		return itc.isInativa(idTrot);
	}

	/**
	 * @return
	 */
	public boolean hasCliente(String nif) {
		return itc.searchCliente(nif) != -1;
	}

	/**
	 * @return
	 */
	public boolean hasTrot(String idTrot) {
		return itt.hasTrot(idTrot);
	}

	/**
	 * @return
	 */
	public boolean trotIsLivre(String idTrot) {
		return itt.livre(idTrot);
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
	private void setUtilizadorDeTrot(String idTrot,Cliente c) {
		itt.setCliente(idTrot,c);

	}

	/**
	 * @param t
	 */
	private void setTrotDeUtilizador(String nif,Trot t) {
		itc.setTrot(nif,t);

	}

	/**
	 * @param minutos
	 */
	private void setMaxMinutosCliente(String nif,int minutos) {
		itc.setMaxMinutos(nif,minutos);
	}


	/**
	 * 
	 */
	private void incrementarAlugueresTrot(String idTrot) {
		itt.incrementarAlugueres(idTrot);
	}

	/**
	 * @param minutos
	 */
	private void mudarTotalMinutosTrot(String idTrot,int minutos) {
		itt.mudarTotalMinutos(idTrot,minutos);
	}

	/**
	 * @return
	 */
	public int getTotalCentimosCliente(String nif) {
		return itc.getTotalCentimos(nif);
	}

	/**
	 * @return
	 */
	public int getMaxMinutosCliente(String nif) {
		return itc.getMaxMinutos(nif);
	}

	/**
	 * @return
	 */
	public int getMedMinutosCliente(String nif) {
		return itc.getMedMinutos(nif);
	}

	/**
	 * @return
	 */
	public boolean clienteHasTrot(String nif) {
		return itc.getTrot(nif) != -1;
	}

	/**
	 * @return
	 */
	public boolean trotHasCliente(idTrot) {
		return itt.getCliente(idTrot) != -1;
	}

	/**
	 * @return
	 */
	public String estadoTrot(String idTrot) {
		return itt.estado(idTrot);
	}

	/**
	 * @return
	 */
	public int getAlugueresTrot(String idTrot) {
		return itt.getAlugueres(idTrot);
	}

	/**
	 * @return
	 */
	public int getTotalMinutosTrot(String idTrot) {
		return itt.getTotalMinutos(idTrot);
	}

	public Cliente[] getDevedores() {
		return itc.getDevedores();
	}

	public void sortClienteNif() {
		itc.sort();
		
	}
}
