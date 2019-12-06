import java.util.Locale;
import java.util.Scanner;

/**
 * @author Guilherme Cipriano Carvalho.
 *
 */

public class Main {
	// Comandos possiveis de utilizar no programa
	public static final String ADICIONAR_CLIENTE = "ADCLIENTE";
	public static final String REMOVER_CLIENTE = "REMCLIENTE";
	public static final String ADICIONAR_TROT = "ADTROT";
	public static final String DADOS_CLIENTE = "DADOSCLIENTE";
	public static final String INFORMACAO_TROT = "TROT";
	public static final String DADOS_TROT = "DADOSTROT";
	public static final String UTILIZADOR_TROT = "CLIENTE";
	public static final String ADICIONAR_SALDO = "CARRSALDO";
	public static final String ALUGAR = "ALUGAR";
	public static final String LIBERTAR = "LIBERTAR";
	public static final String LIBLOCAL = "LIBLOC";
	public static final String LOCTROT = "LOCTROT";
	public static final String DADOS_SISTEMA = "ESTADOSISTEMA";
	public static final String DESATIVAR = "DESTROT";
	public static final String REACTIVAR = "REACTROT";
	public static final String LIST_TROT = "LISTTROT";
	public static final String LIST_CL = "LISTCLIENTE";
	public static final String LIST_DEV = "LISTDEV";
	public static final String SAIR = "SAIR";

	public static final String[] ERROS = { "Cliente existente.", "Cliente inexistente.", "Cliente em movimento.",
			"Cliente sem trotinete.", "Cliente sem saldo suficiente.", "Cliente iniciou novo aluguer.",
			"Trotinete existente.", "Trotinete inexistente.", "Trotinete nao alugada.",
			"Trotinete nao pode ser alugada.", "Trotinete em movimento.", "Trotinete reactivada.",
			"Nao existem trotinetes localizadas.", "Trotinete nao inactiva.", "Valor invalido.", "Comando invalido.",
			"Localizacao invalida." };
	public static final String[] SUCESSOS = { "Insercao de cliente com sucesso.", "Cliente removido com sucesso.",
			"Insercao de trotinete com sucesso.", "Carregamento efectuado.", "Aluguer efectuado com sucesso.",
			"Aluguer terminado.", "Saindo...", "Trotinete desactivada.", "Trotinete reactivada." };

	/**
	 * Inicio do programa.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		String cmd = "";
		TrotSystem sys = new TrotSystem();
		Scanner in = new Scanner(System.in);
		while (!cmd.toUpperCase().equals(SAIR)) {
			cmd = in.next();
			processCommand(sys, in, cmd);
		}
		in.close();
	}

	/**
	 * Processa o comando introduzido.
	 * 
	 * @param sys
	 * @param in
	 * @param cmd
	 */
	private static void processCommand(TrotSystem sys, Scanner in, String cmd) {
		String nif;
		String email;
		String telefone;
		String nome;
		String idTrot;
		String matricula;
		int valorCentimos;
		int minutos;
		double longitude;
		double latitude;
		switch (cmd.toUpperCase()) {
		case ADICIONAR_CLIENTE:
			nif = in.next();
			email = in.next();
			telefone = in.next();
			nome = in.nextLine().trim();
			adicionarCliente(nif, email, telefone, nome, sys);
			break;
		case REMOVER_CLIENTE:
			nif = in.next();
			in.nextLine();
			removerCliente(nif, sys);
			break;
		case ADICIONAR_TROT:
			idTrot = in.next();
			matricula = in.next();
			in.nextLine();
			adicionarTrot(idTrot, matricula, sys);
			break;
		case DADOS_CLIENTE:
			nif = in.next();
			in.nextLine();
			dadosDeUtilizador(nif, sys);
			break;
		case INFORMACAO_TROT:
			nif = in.next();
			in.nextLine();
			trotDeUtilizador(nif, sys);
			break;
		case DADOS_TROT:
			idTrot = in.next();
			in.nextLine();
			dadosTrot(idTrot, sys);
			break;
		case UTILIZADOR_TROT:
			idTrot = in.next();
			in.nextLine();
			utilizadorDeTrot(idTrot, sys);
			break;
		case ADICIONAR_SALDO:
			nif = in.next();
			valorCentimos = in.nextInt();
			in.nextLine();
			adicionarSaldo(nif, valorCentimos, sys);
			break;
		case ALUGAR:
			nif = in.next();
			idTrot = in.next();
			in.nextLine();
			alugarTrot(nif, idTrot, sys);
			break;
		case LIBERTAR:
			idTrot = in.next();
			minutos = in.nextInt();
			in.nextLine();
			libertarTrot(idTrot, minutos, sys);
			break;
		case LIBLOCAL:
			idTrot = in.next();
			minutos = in.nextInt();
			latitude = in.nextDouble();
			longitude = in.nextDouble();
			in.hasNextLine();
			libertarTrotLoc(idTrot, minutos, latitude, longitude, sys);
			break;
		case LOCTROT:
			latitude = in.nextDouble();
			longitude = in.nextDouble();
			localizarTrot(latitude, longitude, sys);
			in.nextLine();
			break;
		case DADOS_SISTEMA:
			in.nextLine();
			dadosSistema(sys);
			break;
		case DESATIVAR:
			idTrot = in.next();
			in.nextLine();
			desativarTrot(idTrot, sys);
			break;
		case REACTIVAR:
			idTrot = in.next();
			in.nextLine();
			reactivatTrot(idTrot, sys);
			break;
		case LIST_TROT:
			listarTrotinetes(sys);
			break;
		case LIST_CL:
			listarClientes(sys);
			break;
		case LIST_DEV:
			listarDevedores(sys);
			break;
		case SAIR:
			in.nextLine();
			System.out.println(SUCESSOS[6]);
			dadosSistema(sys);
			break;
		default:
			in.nextLine();
			System.out.println(ERROS[15]);
		}

	}

	/**
	 * Adiciona um novo cliente.
	 * 
	 * @param nif
	 * @param email
	 * @param telefone
	 * @param nome
	 * @param sys
	 */
	public static void adicionarCliente(String nif, String email, String telefone, String nome, TrotSystem sys) {

		if (!sys.hasCliente(nif)) {
			sys.adicionarCliente(nif, email, telefone, nome);
			System.out.println(SUCESSOS[0]);
		} else {
			System.out.println(ERROS[0]);
		}

	}

	/**
	 * Remove um cliente existente.
	 * 
	 * @param nif
	 * @param sys
	 */
	public static void removerCliente(String nif, TrotSystem sys) {
		if (sys.hasCliente(nif)) {
			if (sys.getTrotDeUtilizador(nif) == null) {
				sys.removerCliente(nif);
				System.out.println(SUCESSOS[1]);
			} else {
				System.out.println(ERROS[2]);
			}
		} else {
			System.out.println(ERROS[1]);
		}

	}

	/**
	 * Adiciona uma nova trotinete.
	 * 
	 * @param idTrot
	 * @param matricula
	 * @param sys
	 */
	public static void adicionarTrot(String idTrot, String matricula, TrotSystem sys) {

		if (!sys.hasTrot(idTrot)) {
			System.out.println(SUCESSOS[2]);
			sys.adicionarTrot(idTrot, matricula);
		} else {
			System.out.println(ERROS[6]);
		}

	}

	/**
	 * Carrega o saldo do cliente.
	 * 
	 * @param nif
	 * @param valorCentimos
	 * @param sys
	 */
	public static void adicionarSaldo(String nif, int valorCentimos, TrotSystem sys) {

		if (valorCentimos > 0) {
			if (sys.hasCliente(nif)) {
				sys.adicionarSaldo(nif, valorCentimos);
				System.out.println(SUCESSOS[3]);
			} else {
				System.out.println(ERROS[1]);
			}
		} else {
			System.out.println(ERROS[14]);
		}

	}

	/**
	 * Aluga uma trotinete.
	 * 
	 * @param nif
	 * @param idTrot
	 * @param sys
	 */
	public static void alugarTrot(String nif, String idTrot, TrotSystem sys) {
		if (sys.hasCliente(nif)) {
			if (sys.hasTrot(idTrot)) {
				if (sys.trotIsLivre(idTrot) && !sys.isInativa(idTrot)) {
					if (sys.getTrotDeUtilizador(nif) == null) {
						if (sys.getSaldo(nif) >= TrotSystem.CUSTO_DE_ALUGUER) {
							sys.alugarTrot(nif, idTrot);
							System.out.println(SUCESSOS[4]);
						} else {
							System.out.println(ERROS[4]);
						}
					} else {
						System.out.println(ERROS[2]);
					}
				} else {
					System.out.println(ERROS[9]);
				}
			} else {
				System.out.println(ERROS[7]);
			}
		} else {
			System.out.println(ERROS[1]);
		}

	}

	/**
	 * Liberta uma trotinete sem indicacao de localizacao.
	 * 
	 * @param idTrot
	 * @param minutos
	 * @param sys
	 */
	public static void libertarTrot(String idTrot, int minutos, TrotSystem sys) {

		if (minutos > 0) {
			if (sys.hasTrot(idTrot)) {
				if (sys.getUtilizadorDeTrot(idTrot) != null && !sys.isInativa(idTrot)) {
					System.out.println(SUCESSOS[5]);
					sys.libertarTrot(idTrot, minutos, false, 0, 0);

				} else {
					System.out.println(ERROS[8]);
				}
			} else {
				System.out.println(ERROS[7]);
			}
		} else {
			System.out.println(ERROS[14]);
		}

	}

	/**
	 * Desativa uma trotinete, esta acao proibe o uso da mesma para alugar.
	 * 
	 * @param idTrot
	 * @param sys
	 */
	public static void desativarTrot(String idTrot, TrotSystem sys) {

		if (sys.hasTrot(idTrot)) {
			if (sys.getUtilizadorDeTrot(idTrot) == null) {
				System.out.println(SUCESSOS[7]);
				sys.setInativa(idTrot, true);
			} else {
				System.out.println(ERROS[10]);
			}
		} else {
			System.out.println(ERROS[7]);
		}

	}

	/**
	 * Reactiva uma trotinete.
	 * 
	 * @param idTrot
	 * @param sys
	 */
	public static void reactivatTrot(String idTrot, TrotSystem sys) {

		if (sys.hasTrot(idTrot)) {
			if (sys.isInativa(idTrot)) {
				System.out.println(SUCESSOS[8]);
				sys.setInativa(idTrot, false);
			} else {
				System.out.println(ERROS[13]);
			}
		} else {
			System.out.println(ERROS[7]);
		}

	}

	/**
	 * Mostra os dados do sistema.
	 * 
	 * @param sys
	 */
	public static void dadosSistema(TrotSystem sys) {
		System.out.println(
				"Estado actual: " + sys.getAlugueres() + ", " + sys.getTotalCentimos() + ", " + sys.getAtrasos());
	}

	/**
	 * Mostra os dados de um utilizador.
	 * 
	 * @param nif
	 * @param sys
	 */
	public static void dadosDeUtilizador(String nif, TrotSystem sys) {
		if (sys.hasCliente(nif)) {
			System.out.println(sys.getNome(nif) + ": " + sys.getNif(nif) + ", " + sys.getEmail(nif) + ", "
					+ sys.getTelefone(nif) + ", " + sys.getSaldo(nif) + ", " + sys.getTotalMinutosCliente(nif) + ", "
					+ sys.getAlugueresCliente(nif) + ", " + sys.getMaxMinutosCliente(nif) + ", "
					+ sys.getMedMinutosCliente(nif) + ", " + sys.getTotalCentimosCliente(nif));
		} else {
			System.out.println(ERROS[1]);
		}

	}

	/**
	 * Ver trotinete que esta em uso pelo cliente.
	 * 
	 * @param nif
	 * @return
	 */

	public static void trotDeUtilizador(String nif, TrotSystem sys) {
		if (sys.hasCliente(nif)) {
			if (sys.clienteHasTrot(nif)) {
				System.out.println(sys.getId(nif) + ", " + sys.getMatricula(sys.getId(nif)));
			} else {
				System.out.println(ERROS[3]);
			}
		} else {
			System.out.println(ERROS[1]);
		}

	}

	/**
	 * Consultar dados da trotinete.
	 * 
	 * @param idTrot
	 * @return
	 */

	public static void dadosTrot(String idTrot, TrotSystem sys) {

		if (sys.hasTrot(idTrot)) {
			System.out.println(sys.getMatricula(idTrot) + ": " + sys.estadoTrot(idTrot) + ", "
					+ sys.getAlugueresTrot(idTrot) + ", " + sys.getTotalMinutosTrot(idTrot));
		} else {
			System.out.println(ERROS[7]);
		}

	}

	/**
	 * Ver que cliente esta a usar a trotinete.
	 * 
	 * @param idTrot
	 * @return
	 */

	public static void utilizadorDeTrot(String idTrot, TrotSystem sys) {

		if (sys.hasTrot(idTrot)) {
			if (sys.trotHasCliente(idTrot)) {
				System.out.println(sys.getNifInTrot(idTrot) + ", " + sys.getNome(sys.getNifInTrot(idTrot)));
			} else {
				System.out.println(ERROS[8]);
			}
		} else {
			System.out.println(ERROS[7]);
		}

	}

	/**
	 * Lista as trotinetes por ordem de insercao no sistema.
	 * 
	 * @param sys
	 */
	public static void listarTrotinetes(TrotSystem sys) {
		for (int i = 0; i < sys.numeroTrots(); i++) {
			String idTrot = sys.getId(i);
			System.out.println(sys.getMatricula(idTrot) + ": " + sys.estadoTrot(idTrot) + ", "
					+ sys.getAlugueresTrot(idTrot) + ", " + sys.getTotalMinutosTrot(idTrot));
		}
	}

	/**
	 * Lista os clientes por ordem de NIF.
	 * 
	 * @param sys
	 */
	public static void listarClientes(TrotSystem sys) {
		sys.sortClienteNif();
		for (int i = 0; i < sys.numeroClientes(); i++) {
			String nif = sys.getNif(i);
			System.out.println(sys.getNome(nif) + ": " + sys.getNif(nif) + ", " + sys.getEmail(nif) + ", "
					+ sys.getTelefone(nif) + ", " + sys.getSaldo(nif) + ", " + sys.getTotalMinutosCliente(nif) + ", "
					+ sys.getAlugueresCliente(nif) + ", " + sys.getMaxMinutosCliente(nif) + ", "
					+ sys.getMedMinutosCliente(nif) + ", " + sys.getTotalCentimosCliente(nif));
		}
	}

	/**
	 * Lista os clientes com saldo negativo por ordem crescente de saldo, em caso de
	 * conflito ordena pelo NIF.
	 * 
	 * @param sys
	 */
	public static void listarDevedores(TrotSystem sys) {
		Cliente[] cl = sys.getDevedores();
		for (int i = 0; i < sys.numeroClientes(); i++) {
			if (cl[i] != null)
				System.out.println(cl[i].getNome() + ": " + cl[i].getNif() + ", " + cl[i].getEmail() + ", "
						+ cl[i].getTelefone() + ", " + cl[i].getSaldo() + ", " + cl[i].getTotalMinutos() + ", "
						+ cl[i].getAlugueres() + ", " + cl[i].getMaxMinutos() + ", " + cl[i].getMedMinutos() + ", "
						+ cl[i].getTotalCentimos());
		}
	}

	/**
	 * Liberta uma trotinete com indicacao de localizacao.
	 * 
	 * @param idTrot
	 * @param minutos
	 * @param latitude
	 * @param longitude
	 * @param sys
	 */
	public static void libertarTrotLoc(String idTrot, int minutos, double latitude, double longitude, TrotSystem sys) {
		if (sys.isInside(latitude, longitude)) {
			if (minutos > 0) {
				if (sys.hasTrot(idTrot)) {
					if (sys.getUtilizadorDeTrot(idTrot) != null && !sys.isInativa(idTrot)) {
						System.out.println(SUCESSOS[5]);
						sys.libertarTrot(idTrot, minutos, true, latitude, longitude);
					} else {
						System.out.println(ERROS[8]);
					}
				} else {
					System.out.println(ERROS[7]);
				}
			} else {
				System.out.println(ERROS[14]);
			}
		} else {
			System.out.println(ERROS[16]);

		}
	}

	/**
	 * Mostra todas as trotinetes com dados de localizacao ordenadas por proximidade
	 * a um dado ponto.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param sys
	 */
	public static void localizarTrot(double latitude, double longitude, TrotSystem sys) {
		Trot[] trt = sys.sortDistancesTrot(latitude, longitude);
		if (trt[0] != null) {
			for (int i = 0; i < sys.numeroTrots(); i++) {
				if (trt[i] != null) {
					String lat = String.format("%.6f", trt[i].getLatitude());
					String lon = String.format("%.6f", trt[i].getLongitude());
					System.out.printf("Distancia: %.6f%n", trt[i].getDistanceTo(latitude, longitude));
					System.out.println(trt[i].getMatricula() + ": " + trt[i].estado() + ", " + trt[i].getAlugueres()
							+ ", " + trt[i].getTotalMinutos() + ", " + lat + ", " + lon);
				}
			}
		} else {
			System.out.println(ERROS[12]);
		}
	}

}
