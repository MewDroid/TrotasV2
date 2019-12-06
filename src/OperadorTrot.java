/**
 * @author Guilherme Cipriano Carvalho.
 *
 */

public class OperadorTrot {

	private static final int FACTOR = 3;
	private static final int BASE_SIZE = 10;

	private Trot[] trts;
	private int count;

	/**
	 * Construtor da classe.
	 */
	public OperadorTrot() {
		trts = new Trot[BASE_SIZE];
		count = 0;
	}

	/**
	 * Devolve a trotinete com o index dado.
	 * 
	 * @param index
	 * @return
	 */
	public Trot getTrot(int index) {
		return trts[index];
	}

	/**
	 * Aumenta o tamanho da lista de trotinetes.
	 */
	private void resize() {
		if (count == trts.length) {
			Trot[] array2 = new Trot[FACTOR * trts.length];
			for (int i = 0; i < trts.length; i++) {
				array2[i] = trts[i];
			}
			trts = array2;
		}
	}

	/**
	 * Adiciona um valor a lista de trotinetes.
	 * 
	 * @param trot
	 */
	public void append(Trot trot) {
		resize();
		trts[count] = trot;
		count++;
	}

	/**
	 * Procura a trotinete com o id dado.
	 * 
	 * @param idTrot
	 * @return
	 */
	public int searchTrot(String idTrot) {
		int i;
		int trt = -1;
		for (i = 0; i < count; i++) {
			if (trts[i].getIdTrot().equalsIgnoreCase(idTrot)) {
				trt = i;
				break;
			}
		}
		return trt;
	}

	/**
	 * Indica se tem uma com o id dado.
	 * @param idTrot
	 * @return
	 */
	public boolean hasTrot(String idTrot) {
		int i;
		boolean value = false;
		for (i = 0; i < count; i++) {
			if (trts[i].getIdTrot().equalsIgnoreCase(idTrot)) {
				value = true;
			}
		}
		return value;
	}

	/**
	 * devolve o cliente com a trotinete alugada.
	 * 
	 * @param idTrot
	 * @return
	 */
	public Cliente getCliente(String idTrot) {
		return trts[searchTrot(idTrot)].getUtilizador();
	}

	/**
	 * Devolve a matricula da trotinete.
	 * 
	 * @param idTrot
	 * @return
	 */
	public String getMatricula(String idTrot) {
		return trts[searchTrot(idTrot)].getMatricula();
	}

	/**
	 * Coloca a trotinete em estado inativa ou ativa.
	 * 
	 * @param idTrot
	 * @param i
	 */
	public void setInativa(String idTrot, boolean i) {
		trts[searchTrot(idTrot)].setInativa(i);
	}

	/**
	 * Coloca as coordenadas na trotinete.
	 * 
	 * @param idTrot
	 * @param xCord
	 * @param yCord
	 */
	public void setCoords(String idTrot, double latitude, double longitude) {
		trts[searchTrot(idTrot)].setCoords(latitude, longitude);
	}

	/**
	 * Retira as coordenadas da trotinete.
	 * 
	 * @param idTrot
	 */
	public void removeCoords(String idTrot) {
		trts[searchTrot(idTrot)].removeCoords();
	}

	/**
	 * Muda o cliente que alugou a trotinete.
	 * 
	 * @param idTrot
	 * @param cliente
	 */
	public void setCliente(String idTrot, Cliente cliente) {
		trts[searchTrot(idTrot)].setCliente(cliente);
	}

	/**
	 * Indica se esta inativa.
	 * 
	 * @param idTrot
	 * @return
	 */
	public boolean isInativa(String idTrot) {
		return trts[searchTrot(idTrot)].isInativa();
	}

	/**
	 * Indica se a trotinete esta livre.
	 * 
	 * @param idTrot
	 * @return
	 */
	public boolean livre(String idTrot) {
		return trts[searchTrot(idTrot)].livre();
	}

	/**
	 * Indica se a trotinete tem coordenadas registadas.
	 * 
	 * @param idTrot
	 * @return
	 */
	public boolean withCoordsExists(String idTrot) {
		return trts[searchTrot(idTrot)].hasCoords();
	}

	/**
	 * Aumenta o numero de alugueres feitos.
	 * 
	 * @param idTrot
	 */
	public void incrementarAlugueres(String idTrot) {
		trts[searchTrot(idTrot)].incrementarAlugueres();
	}

	/**
	 * Aumenta o numero total de minutos de movimento.
	 * 
	 * @param idTrot
	 * @param amount
	 */
	public void mudarTotalMinutos(String idTrot, int amount) {
		trts[searchTrot(idTrot)].mudarTotalMinutos(amount);
	}

	/**
	 * Devolve o estado da trotinete.
	 * 
	 * @param idTrot
	 * @return
	 */
	public String estado(String idTrot) {
		return trts[searchTrot(idTrot)].estado();
	}

	/**
	 * Devolve o numero total de alugueres.
	 * 
	 * @param idTrot
	 * @return
	 */
	public int getAlugueres(String idTrot) {
		return trts[searchTrot(idTrot)].getAlugueres();
	}

	/**
	 * Devolve o numero total de minutos de andamento.
	 * 
	 * @param idTrot
	 * @return
	 */
	public int getTotalMinutos(String idTrot) {
		return trts[searchTrot(idTrot)].getTotalMinutos();
	}

	/**
	 * Devolve o numero de trotinetes.
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Devolve o id da trotinete.
	 * 
	 * @param i
	 * @return
	 */
	public String getId(int i) {
		return trts[i].getIdTrot();
	}

	/**
	 * Devolve uma lista organizada de trotinetes.
	 * 
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public Trot[] sortDistances(double latitude, double longitude) {
		Trot[] array = new Trot[trts.length];
		boolean[] used = new boolean[trts.length];
		for (int i = 0; i < count; i++) {
			double DistMax = -1;
			int j;
			int id = -1;
			for (j = 0; j < count; j++) {
				if ((DistMax > trts[j].getDistanceTo(latitude, longitude) || DistMax == -1) && trts[j].hasCoords()
						&& used[j] == false) {
					DistMax = trts[j].getDistanceTo(latitude, longitude);
					id = j;
				}
			}
			if (id > -1) {
				array[i] = trts[id];
				used[id] = true;
			}
		}
		return array;
	}
}
