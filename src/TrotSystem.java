/**
 * @author Guilherme Cipriano Carvalho.
 *
 */
public class TrotSystem {
	// Constantes.
	public static final int SALDO_BASE = 200;
	public static final int CUSTO_DE_ALUGUER = 100;
	public static final int PENALIZACAO = 100;
	public static final int TEMPO_LIMITE = 60;
	public static final int INTERVALOS_DE_PENALIZACAO = 30;

	// Iteradores
	private OperadorCliente itc;
	private OperadorTrot itt;

	// Dados estatisticos.
	private int alugueres;
	private int totalCentimos;
	private int atrasos;
	private static final double AreaWest = -9.209269;
	private static final double AreaEast = -9.201978;
	private static final double AreaSouth = 38.658475;
	private static final double AreaNorth = 38.663964;

	/**
	 * Construtor do sistema.
	 */
	public TrotSystem() {
		itc = new OperadorCliente();
		itt = new OperadorTrot();
		alugueres = 0;
		totalCentimos = 0;
		atrasos = 0;
	}

	/**
	 * Indica se as coordenadas dadas se encontram dentro dos limites predefinidos.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public boolean isInside(double latitude, double longitude) {
		return (longitude <= AreaEast && longitude >= AreaWest && latitude >= AreaSouth && latitude <= AreaNorth);
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
	 * 
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
	public void adicionarSaldo(String nif, int valorCentimos) {
		itc.mudarSaldo(nif, valorCentimos);
	}

	/**
	 * Alugar trotinete.
	 * 
	 * @param nif
	 * @param idTrot
	 * @return
	 */
	public void alugarTrot(String nif, String idTrot) {
		itt.removeCoords(idTrot);
		itc.setTrot(nif, itt.getTrot(itt.searchTrot(idTrot)));
	}

	/**
	 * Libertar trotinete e fazer o pagamento, se hasCoords == true, entao atribui
	 * tambem uma localizacao.
	 * 
	 * @param idTrot
	 * @param minutos
	 * @param hasCoords
	 * @param latitude
	 * @param longitude
	 */
	public void libertarTrot(String idTrot, int minutos, boolean hasCoords, double latitude, double longitude) {
		String nif = itt.getCliente(idTrot).getNif();
		itc.mudarSaldo(nif, -CUSTO_DE_ALUGUER);
		alugueres++;
		itc.incrementarAlugueres(nif);
		itc.mudarTotalMinutos(nif, minutos);

		itt.mudarTotalMinutos(idTrot, minutos);
		itt.incrementarAlugueres(idTrot);

		totalCentimos += CUSTO_DE_ALUGUER;
		itc.mudarTotalCentimos(nif, CUSTO_DE_ALUGUER);
		itc.setMaxMinutos(nif, minutos);
		if (hasCoords)
			itt.setCoords(idTrot, latitude, longitude);
		int tempoExtra = minutos - TEMPO_LIMITE;
		if (tempoExtra > 0)
			atrasos += tempoExtra;
		while (tempoExtra > 0) {

			itc.mudarSaldo(nif, -PENALIZACAO);
			totalCentimos += PENALIZACAO;
			itc.mudarTotalCentimos(nif, PENALIZACAO);
			tempoExtra -= INTERVALOS_DE_PENALIZACAO;
		}
		itc.setTrot(nif, null);
		itt.setCliente(idTrot, null);
	}

	

	/**
	 * Indica se existe alguma trotinete com coordenadas.
	 * 
	 * @param idTrot
	 * @return
	 */
	public boolean TrotWithCoords(String idTrot) {
		return itt.withCoordsExists(idTrot);
	}

	// Segue-se um conjunto de SetGets.

	/**
	 * Devolve o email de um determinado cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public String getEmail(String nif) {
		return itc.getEmail(nif);
	}

	/**
	 * Devolve o numero de telefone de um determinado cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public String getTelefone(String nif) {
		return itc.getTelefone(nif);
	}

	/**
	 * Devolve o nome de um determinado cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public String getNome(String nif) {
		return itc.getNome(nif);
	}

	/**
	 * Devolve o saldo de um determinado cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getSaldo(String nif) {
		return itc.getSaldo(nif);
	}

	/**
	 * Devolve o numero total de minutos em movimento registado por um dado cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getTotalMinutosCliente(String nif) {
		return itc.getTotalMinutos(nif);
	}

	/**
	 * Muda o numero total de minutos em movimento de um cliente.
	 * 
	 * @param nif
	 * @param minutos
	 */
	public void mudarTotalMinutosCliente(String nif, int minutos) {
		itc.mudarTotalMinutos(nif, minutos);
	}

	/**
	 * Indica o numero total de algueres
	 * 
	 * @param nif
	 * @return
	 */
	public int getAlugueresCliente(String nif) {
		return itc.getAlugueres(nif);
	}
	
	/**
	 * Devolve o numero total de centimos gasto pelo cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getTotalCentimosCliente(String nif) {
		return itc.getTotalCentimos(nif);
	}

	/**
	 * Devolve o numero maximo de minutos de uso do sistema pelo cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getMaxMinutosCliente(String nif) {
		return itc.getMaxMinutos(nif);
	}

	/**
	 * Devolve a media dos minutos de uso do sistema pelo cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public int getMedMinutosCliente(String nif) {
		return itc.getMedMinutos(nif);
	}

	/**
	 * Indica se o cliente tem uma trotinete alugada.
	 * 
	 * @param nif
	 * @return
	 */
	public boolean clienteHasTrot(String nif) {
		return itc.getTrot(nif) != null;
	}

	/**
	 * Devolve o numero de clientes registados.
	 * 
	 * @return
	 */
	public int numeroClientes() {
		return itc.getCount();
	}

	/**
	 * Devolve o NIF do cliente com o index dado.
	 * 
	 * @param i
	 * @return
	 */
	public String getNif(int i) {
		return itc.getNif(i);
	}

	/**
	 * Devolve o NIF do cliente com o NIF dado.
	 * 
	 * @param nif
	 * @return
	 */
	public String getNif(String nif) {
		return itc.getNif(nif);
	}

	/**
	 * Devolve a matricula de uma determinada trotinete.
	 * 
	 * @return
	 */
	public String getMatricula(String idTrot) {
		return itt.getMatricula(idTrot);
	}

	/**
	 * Devolve a trotinete em uso pelo cliente.
	 * 
	 * @param nif
	 * @return
	 */
	public Trot getTrotDeUtilizador(String nif) {
		return itc.getTrot(nif);
	}

	/**
	 * Devolve o cliente que tem a trotinete alugada.
	 * 
	 * @return
	 */
	public Cliente getUtilizadorDeTrot(String idTrot) {
		return itt.getCliente(idTrot);
	}

	/**
	 * Muda o cliente que tem a trotinete alugada.
	 * 
	 * @param idTrot
	 * @param cl
	 */
	public void setUtilizadorDeTrot(String idTrot, Cliente cl) {
		itt.setCliente(idTrot, cl);
	}

	/**
	 * Indica se a trotinete esta inativa.
	 * 
	 * @param idTrot
	 * @return
	 */
	public boolean isInativa(String idTrot) {
		return itt.isInativa(idTrot);
	}
	
	/**
	 * Coloca a trotinete em estado inativa ou ativa.
	 * 
	 * @param idTrot
	 * @param i
	 * @return
	 */
	public void setInativa(String idTrot, boolean i) {
		itt.setInativa(idTrot, i);
	}

	/**
	 * Indica se existe algum cliente com o NIF dado.
	 * 
	 * @param nif
	 * @return
	 */
	public boolean hasCliente(String nif) {
		return itc.searchCliente(nif) != -1;
	}

	/**
	 * Indica se existe alguma trotinete com o id dado.
	 * 
	 * @param idTrot
	 * @return
	 */
	public boolean hasTrot(String idTrot) {
		return itt.hasTrot(idTrot);
	}

	/**
	 * Indica se a trotinete esta livre.
	 * 
	 * @return
	 */
	public boolean trotIsLivre(String idTrot) {
		return itt.livre(idTrot);
	}

	/**
	 * Devolve o numero total de alugueres realizados.
	 * 
	 * @return
	 */
	public int getAlugueres() {
		return alugueres;
	}

	/**
	 * Devolve o numero total de centimos gastos no sistema.
	 * 
	 * @return
	 */
	public int getTotalCentimos() {
		return totalCentimos;
	}

	/**
	 * Devolve o numero total de minutos de atrasos registado.
	 * 
	 * @return
	 */
	public int getAtrasos() {
		return atrasos;
	}



	/**
	 * Indica se a trotinete esta alugada.
	 * 
	 * @param idTrot
	 * @return
	 */
	public boolean trotHasCliente(String idTrot) {
		return itt.getCliente(idTrot) != null;
	}

	/**
	 * Indica o estado da trotinete.
	 *
	 * @param idTrot
	 * @return
	 */
	public String estadoTrot(String idTrot) {
		return itt.estado(idTrot);
	}

	/**
	 * Devolve o numero total de alugueres realizados na trotinete.
	 * 
	 * @param idTrot
	 * @return
	 */
	public int getAlugueresTrot(String idTrot) {
		return itt.getAlugueres(idTrot);
	}

	/**
	 * Devolve o numero total de minutos em que a trotinete esteve em alugamento.
	 * 
	 * @param idTrot
	 * @return
	 */
	public int getTotalMinutosTrot(String idTrot) {
		return itt.getTotalMinutos(idTrot);
	}

	/**
	 * Devolve os clientes com saldo negativo.
	 * 
	 * @return
	 */
	public Cliente[] getDevedores() {
		return itc.getDevedores();
	}

	/**
	 * Ordena os clientes com base no NIF.
	 */
	public void sortClienteNif() {
		itc.sort();
	}

	/**
	 * Devolve a lista ordenada de trotinetes por distancia.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public Trot[] sortDistancesTrot(double latitude, double longitude) {
		return itt.sortDistances(latitude, longitude);
	}

	
	/**
	 * Devolve o NIF do cliente com a trotinete alugada.
	 * 
	 * @param idTrot
	 * @return
	 */
	public String getNifInTrot(String idTrot) {
		return itt.getCliente(idTrot).getNif();
	}

	/**
	 * Devolve o numero de trotinetes registadas.
	 * 
	 * @return
	 */
	public int numeroTrots() {
		return itt.getCount();
	}

	/**
	 * Devolve o id da trotinete com o index dado.
	 * 
	 * @param i
	 * @return
	 */
	public String getId(int i) {
		return itt.getId(i);
	}

	/**
	 * Devolve o id da trotinete alugada pelo cliente
	 * 
	 * @param nif
	 * @return
	 */
	public String getId(String nif) {
		return itc.getTrot(nif).getIdTrot();
	}

}
