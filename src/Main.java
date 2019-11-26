import java.util.Scanner;

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
	public static final String DADOS_SISTEMA = "ESTADOSISTEMA";
	public static final String PROMO = "PROMOCAO";
	public static final String DESATIVAR = "DESTROT";
	public static final String REACTIVAR = "REACTROT";
	public static final String SAIR = "SAIR";

	public static final String[] ERROS = { "Cliente existente.", "Cliente inexistente.", "Cliente em movimento.",
			"Cliente sem trotinete.", "Cliente sem saldo suficiente.", "Cliente iniciou novo aluguer.",
			"Trotinete existente.", "Trotinete inexistente.", "Trotinete nao alugada.",
			"Trotinete nao pode ser alugada.", "Trotinete em movimento.", "Trotinete reactivada.",
			"Trotinete nao inactiva.", "Valor invalido.", "Promocao ja aplicada.", "Comando invalido." };
	public static final String[] SUCESSOS = { "Insercao de cliente com sucesso.", "Cliente removido com sucesso.",
			"Insercao de trotinete com sucesso.", "Carregamento efectuado.", "Aluguer efectuado com sucesso.",
			"Aluguer terminado.", "Saindo...", "Promocao aplicada.", "Trotinete desactivada.",
			"Trotinete reactivada." };

	/**
	 * Inicio do programa.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String cmd = "";
		TrotSystem sys = new TrotSystem();
		Scanner in = new Scanner(System.in);
		while (!cmd.toUpperCase().equals(SAIR)) {
			cmd = readString(in);
			processCommand(sys, in, cmd);
		}
		in.close();
	}

	/**
	 * Le a string seguinte introduzida.
	 * 
	 * @param in
	 * @return
	 */
	private static String readString(Scanner in) {
		return in.next();
	}

	/**
	 * Le o nome do cliente.
	 * 
	 * @param in
	 * @return
	 */
	private static String readName(Scanner in) {
		return in.nextLine();
	}

	/**
	 * Le o inteiro seguinte introduzido.
	 * 
	 * @param in
	 * @return
	 */
	private static int readInt(Scanner in) {
		return in.nextInt();
	}

	/**
	 * Processa o comando introduzido.
	 * 
	 * @param sys
	 * @param in
	 * @param cmd
	 */
	private static void processCommand(TrotSystem sys, Scanner in, String cmd) {
		String NIF;
		String email;
		String telefone;
		String nome;
		String idTrot;
		String matricula;
		int valorCentimos;
		int minutos;
		switch (cmd.toUpperCase()) {
		case ADICIONAR_CLIENTE:
			NIF = readString(in);
			email = readString(in);
			telefone = readString(in);
			nome = readName(in).trim();
			adicionarCliente(NIF, email, telefone, nome, sys);
			break;
		case REMOVER_CLIENTE:
			NIF = readString(in);
			in.nextLine();
			removerCliente(NIF, sys);
			break;
		case ADICIONAR_TROT:
			idTrot = readString(in);
			matricula = readString(in);
			in.nextLine();
			adicionarTrot(idTrot, matricula, sys);
			break;
		case DADOS_CLIENTE:
			NIF = readString(in);
			in.nextLine();
			dadosDeUtilizador(NIF, sys);
			break;
		case INFORMACAO_TROT:
			NIF = readString(in);
			in.nextLine();
			trotDeUtilizador(NIF, sys);
			break;
		case DADOS_TROT:
			idTrot = readString(in);
			in.nextLine();
			dadosTrot(idTrot, sys);
			break;
		case UTILIZADOR_TROT:
			idTrot = readString(in);
			in.nextLine();
			utilizadorDeTrot(idTrot, sys);
			break;
		case ADICIONAR_SALDO:
			NIF = readString(in);
			valorCentimos = readInt(in);
			in.nextLine();
			adicionarSaldo(NIF, valorCentimos, sys);
			break;
		case ALUGAR:
			NIF = readString(in);
			idTrot = readString(in);
			in.nextLine();
			alugarTrot(NIF, idTrot, sys);
			break;
		case LIBERTAR:
			idTrot = readString(in);
			minutos = readInt(in);
			in.nextLine();
			libertarTrot(idTrot, minutos, sys);
			break;
		case DADOS_SISTEMA:
			in.nextLine();
			dadosSistema(sys);
			break;
		case PROMO:
			NIF = in.next();
			in.nextLine();
			promo(NIF, sys);
			break;
		case DESATIVAR:
			idTrot = readString(in);
			in.nextLine();
			desativarTrot(idTrot, sys);
			break;
		case REACTIVAR:
			idTrot = readString(in);
			in.nextLine();
			reactivatTrot(idTrot, sys);
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
	 * @param NIF
	 * @param email
	 * @param telefone
	 * @param nome
	 * @param sys
	 */
	public static void adicionarCliente(String NIF, String email, String telefone, String nome, TrotSystem sys) {

		if (!sys.hasCliente(NIF)) {
			sys.adicionarCliente(NIF, email, telefone, nome);
			System.out.println(SUCESSOS[0]);
		} else {
			System.out.println(ERROS[0]);
		}

	}

	/**
	 * Remove um cliente existente.
	 * 
	 * @param NIF
	 * @param sys
	 */
	public static void removerCliente(String NIF, TrotSystem sys) {
		if (sys.hasCliente() && sys.getNIF().equalsIgnoreCase(NIF)) {
			if (sys.getTrotDeUtilizador() == null) {
				sys.removerCliente();
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

		if (!sys.hasTrot() || !sys.getId().equalsIgnoreCase(idTrot)) {
			System.out.println(SUCESSOS[2]);
			sys.adicionarTrot(idTrot, matricula);
		} else {
			System.out.println(ERROS[6]);
		}

	}

	/**
	 * Carrega o saldo do cliente.
	 * 
	 * @param NIF
	 * @param valorCentimos
	 * @param sys
	 */
	public static void adicionarSaldo(String NIF, int valorCentimos, TrotSystem sys) {

		if (valorCentimos > 0) {
			if (sys.hasCliente() && sys.getNIF().equalsIgnoreCase(NIF)) {
				sys.adicionarSaldo(valorCentimos);
				System.out.println(SUCESSOS[3]);
			} else {
				System.out.println(ERROS[1]);
			}
		} else {
			System.out.println(ERROS[13]);
		}

	}

	/**
	 * Aluga uma trotinete.
	 * 
	 * @param NIF
	 * @param idTrot
	 * @param sys
	 */
	public static void alugarTrot(String NIF, String idTrot, TrotSystem sys) {
		if (sys.hasCliente() && sys.getNIF().equalsIgnoreCase(NIF)) {
			if (sys.hasTrot() && sys.getId().equalsIgnoreCase(idTrot)) {
				if (sys.trotIsLivre() && !sys.isInativa()) {
					if (sys.getSaldo() >= TrotSystem.CUSTO_DE_ALUGUER) {
						sys.alugarTrot();
						System.out.println(SUCESSOS[4]);
					} else {
						System.out.println(ERROS[4]);
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
	 * Acaba um alugamento de uma trotinete.
	 * 
	 * @param idTrot
	 * @param minutos
	 * @param sys
	 */
	public static void libertarTrot(String idTrot, int minutos, TrotSystem sys) {

		if (minutos > 0) {
			if (sys.hasTrot() && sys.getId().equalsIgnoreCase(idTrot)) {
				if (sys.getUtilizadorDeTrot() != null && !sys.isInativa()) {
					System.out.println(SUCESSOS[5]);
					sys.libertarTrot(minutos);

				} else {
					System.out.println(ERROS[8]);
				}
			} else {
				System.out.println(ERROS[7]);
			}
		} else {
			System.out.println(ERROS[13]);
		}

	}

	/**
	 * Ativa uma promocao.
	 * 
	 * @param NIF
	 * @param sys
	 */
	public static void promo(String NIF, TrotSystem sys) {

		if (sys.hasCliente() && sys.getNIF().equalsIgnoreCase(NIF)) {
			if (sys.getTrotDeUtilizador() == null) {
				if (!sys.isPromoAplicada()) {
					sys.promo();
					System.out.println(SUCESSOS[7]);
				} else {
					System.out.println(ERROS[14]);
				}
			} else {
				System.out.println(ERROS[5]);
			}
		} else {
			System.out.println(ERROS[1]);
		}

	}

	/**
	 * Desativa uma trotinete, esta acao proibe o uso da mesma para alugar.
	 * 
	 * @param idTrot
	 * @param sys
	 */
	public static void desativarTrot(String idTrot, TrotSystem sys) {

		if (sys.hasTrot() && sys.getId().equalsIgnoreCase(idTrot)) {
			if (sys.getUtilizadorDeTrot() == null) {
				System.out.println(SUCESSOS[8]);
				sys.setInativa(true);
			} else {
				System.out.println(ERROS[8]);
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

		if (sys.hasTrot() && sys.getId().equalsIgnoreCase(idTrot)) {
			if (sys.isInativa()) {
				System.out.println(SUCESSOS[9]);
				sys.setInativa(false);
			} else {
				System.out.println(ERROS[12]);
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
	 * @param NIF
	 * @param sys
	 */
	public static void dadosDeUtilizador(String NIF, TrotSystem sys) {
		if (sys.hasCliente() && sys.getNIF().equalsIgnoreCase(NIF)) {
			System.out.println(sys.getNome() + ": " + sys.getNIF() + ", " + sys.getEmail() + ", " + sys.getTelefone()
					+ ", " + sys.getSaldo() + ", " + sys.getTotalMinutosCliente() + ", " + sys.getAlugueresCliente()
					+ ", " + sys.getMaxMinutosCliente() + ", " + sys.getMedMinutosCliente() + ", "
					+ sys.getTotalCentimosCliente());
		} else {
			System.out.println(ERROS[1]);
		}

	}

	/**
	 * Ver trotinete que esta em uso pelo cliente.
	 * 
	 * @param NIF
	 * @return
	 */

	public static void trotDeUtilizador(String NIF, TrotSystem sys) {
		if (sys.getNIF().equalsIgnoreCase(NIF)) {
			if (sys.clienteHasTrot()) {
				System.out.println(sys.getId() + ", " + sys.getMatricula());
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

		if (sys.hasTrot() && sys.getId().equalsIgnoreCase(idTrot)) {
			System.out.println(sys.getMatricula() + ": " + sys.estadoTrot() + ", " + sys.getAlugueresTrot() + ", "
					+ sys.getTotalMinutosTrot());
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

		if (sys.hasTrot() && sys.getId().equalsIgnoreCase(idTrot)) {
			if (sys.trotHasCliente()) {
				System.out.println(sys.getNIF() + ", " + sys.getNome());
			} else {
				System.out.println(ERROS[8]);
			}
		} else {
			System.out.println(ERROS[7]);
		}

	}
}
