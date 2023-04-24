package apresentacao;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import entidades.Asset;

public class Inicio {

	/*
	 * Programa de controle de ativos de TI. O programa faz interação com o usuário
	 * por meio de um menu de opções. Para cada opção que o usuário escolher vai ser
	 * executado uma tarefa.
	 */

	public static void main(String[] args) {

		// Diretório
		File d = new File(".");
		// Obter caminho absoluto do diretório
		String caminhoDiretorio = d.getAbsolutePath();

		// Arquivo
		File f = new File("");
		// Obter caminho absoluto do arquivo
		String caminho = f.getAbsolutePath() + "\\CadastroAsset.txt";

		String op;
		// Objeto para ler dados do teclado
		Scanner ler = new Scanner(System.in);

		// Menu de opções para o usuário
		do {
			System.out.println();
			System.out.println("╔──────────────────────────────────╗");
			System.out.println("│          ◄│▒│ MENU │▒│►          │");
			System.out.println("│──────────────────────────────────│");
			System.out.println("│ [1] CADASTRAR ATIVO              │");
			System.out.println("│ [2] LOCALIZAR ATIVO              │");
			System.out.println("│ [3] SAÍDA DE ATIVO               │");
			System.out.println("│ [4] ENTRADA DE ATIVO             │");
			System.out.println("│ [5] LISTAGEM GERAL ATIVOS        │");
			System.out.println("│ [6] LISTAGEM POR PARÂMETROS      │");
			System.out.println("│ [7] RESUMO ATIVOS - QUANTITATIVO │");
			System.out.println("│ [8] RESUMO ATIVOS - CONTÁBIL     │");
			System.out.println("│ [9] EXCLUIR ATIVO OBSOLETO       │");
			System.out.println("│ [s] SAIR                         │");
			System.out.println("└──────────────────────────────────┘");
			System.out.println(" │ ");
			System.out.print(" └■ Opção: ");
			// Lê a opção escolhida pelo usuário
			op = ler.nextLine();

			switch (op) {
			/*
			 * [1] Cadastrar ativo: o programa solicita informações necessárias para o
			 * cadastro de novos ativos, cria um objeto da classe “Asset” e salva no arquivo
			 * “CadastroAsset.txt”.
			 */
			case "1": {
				try {

					// Instanciando a classe Asset
					Asset a = new Asset();

					System.out.println(" │ ");
					System.out.print(" └■ SERVICE TAG: ");
					String serviceTag = ler.nextLine().toUpperCase().trim();

					// Validação SERVICE TAG
					if (serviceTag.isEmpty()) {
						System.out.println(" │");
						System.out.println(" └█ SERVICE TAG não informado");
						break;
					}

					System.out.println(" │ ");
					System.out.println(" └■ TIPO:");
					System.out.println("  └► [1] NOTEBOOK");
					System.out.println("  └► [2] MACBOOK");
					System.out.println("  └► [3] IMAC");
					System.out.println("  └► [4] DESKTOP");
					System.out.println("  └► [5] AIO");
					System.out.println("  └► [6] MONITOR");
					System.out.print(" └■ Opção: ");
					String tipo = ler.nextLine().toUpperCase().trim();

					// Validação TIPO
					if (tipo.equals("1")) {
						tipo = "NOTEBOOK";
					} else if (tipo.equals("2")) {
						tipo = "MACBOOK";
					} else if (tipo.equals("3")) {
						tipo = "IMAC";
					} else if (tipo.equals("4")) {
						tipo = "DESKTOP";
					} else if (tipo.equals("5")) {
						tipo = "AIO";
					} else if (tipo.equals("6")) {
						tipo = "MONITOR";
					} else {
						System.out.println(" │");
						System.out.println(" └█ TIPO inválido!");
						break;// Caso else retorna o menu, não deixa proseguir o cadastro
					}

					System.out.println(" │ ");
					System.out.println(" └■ PERFIL:");
					System.out.println("  └► [1] ADM");
					System.out.println("  └► [2] DEV");
					System.out.println("  └► [3] DIR");
					System.out.print(" └■ Opção: ");
					String perfil = ler.nextLine().toUpperCase().trim();

					// Validação PERFIL
					if (perfil.equals("1")) {
						perfil = "ADM";
					} else if (perfil.equals("2")) {
						perfil = "DEV";
					} else if (perfil.equals("3")) {
						perfil = "DIR";
					} else {
						System.out.println(" │");
						System.out.println(" └█ PERFIL inválido!");
						break;
					}

					System.out.println(" │ ");
					System.out.print(" └■ VALOR (apenas números): ");
					double valor = Double.parseDouble(ler.nextLine().toUpperCase().trim());

					System.out.println(" │ ");
					System.out.print(" └■ NOTA FISCAL  (apenas números): ");
					int notaFiscal = Integer.parseInt(ler.nextLine().toUpperCase().trim());

					System.out.println(" │ ");
					System.out.println(" └■ PROPRIETÁRIO:");
					System.out.println("  └► [1] BIT");
					System.out.println("  └► [2] AMER");
					System.out.print(" └■ Opção: ");
					String proprietario = ler.nextLine().toUpperCase().trim();

					// Validação PROPRIETÁRIO
					if (proprietario.equals("1")) {
						proprietario = "BIT";
					} else if (proprietario.equals("2")) {
						proprietario = "AMER";
					} else {
						System.out.println(" │");
						System.out.println(" └█ PROPRIETÁRIO inválido!");
						break;
					}

					System.out.println(" │ ");
					System.out.println(" └■ LOCAL:");
					System.out.println("  └► [1] SEDE");
					System.out.println("  └► [2] BIT RJ");
					System.out.println("  └► [3] BIT SP");
					System.out.print(" └■ Opção: ");
					String local = ler.nextLine().toUpperCase().trim();

					// Validação LOCAL
					if (local.equals("1")) {
						local = "SEDE";
					} else if (local.equals("2")) {
						local = "BIT RJ";
					} else if (local.equals("3")) {
						local = "BIT SP";
					} else {
						System.out.println(" │");
						System.out.println(" └█ LOCAL inválido!");
						System.out.println();
						break;
					}

					// Data do cadastro apenas
					System.out.println(" │ ");
					System.out.print(" └■ DATA CADASTRO: ");
					LocalDate dataCadastro = LocalDate.now();
					DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					System.out.println(dataCadastro.format(fmt));

					System.out.println(" │ ");
					System.out.println(" └■ STATUS:");
					System.out.println("  └► [1] ESTOQUE");
					System.out.print(" └■ Opção: ");
					String status = ler.nextLine().toUpperCase().trim();

					// Validação STATUS
					if (status.equals("1")) {
						status = "ESTOQUE";
					} else {
						System.out.println(" │");
						System.out.println(" └█ STATUS inválido!");
						break;
					}

					System.out.println(" │ ");
					System.out.println(" └■ SISTEMA OPERACIONAL:");
					System.out.println("  └► [1] WINDOWNS");
					System.out.println("  └► [2] LINUX");
					System.out.println("  └► [3] MAC OS");
					System.out.println("  └► [4] SEM SO");
					System.out.print(" └■ Opção: ");
					String sistemaOperacional = ler.nextLine().toUpperCase().trim();

					// Validação SISTEMA OPERACIONAL
					if (sistemaOperacional.equals("1")) {
						sistemaOperacional = "WINDOWNS";
					} else if (sistemaOperacional.equals("2")) {
						sistemaOperacional = "LINUX";
					} else if (sistemaOperacional.equals("3")) {
						sistemaOperacional = "MAC OS";
					} else if (sistemaOperacional.equals("4")) {
						sistemaOperacional = "SEM SO";
					} else {
						System.out.println(" │");
						System.out.println(" └█ SISTEMA OPERACIONAL inválido!");
						break;
					}

					System.out.println(" │ ");
					System.out.print(" └■ USUÁRIO (proibido caracteres especias): ");
					String usuario = ler.nextLine().toUpperCase().trim();

					// Validação USUÁRIO
					if (usuario.isEmpty()) {
						System.out.println(" │");
						System.out.println(" └█ USUÁRIO não informado");
						break;
					} else if (!usuario.matches("[a-zA-Z\s]+")) {
						System.out.println(" │");
						System.out.println(" └█ USUÁRIO contém caracteres inválidos!");
						break;
					}

					System.out.println(" │ ");
					System.out.print(" └■ DEPARTAMENTO (proibido caracteres especias): ");
					String departamento = ler.nextLine().toUpperCase().trim();

					// Validação DEPARTAMENTO
					if (departamento.isEmpty()) {
						System.out.println(" │");
						System.out.println(" └█ DEPARTAMENTO não informado");
						break;
					} else if (!departamento.matches("[a-zA-Z\s]+")) {
						System.out.println(" │");
						System.out.println(" └█ DEPARTAMENTO contém caracteres inválidos!");
						break;
					}
					// Data movimento
					System.out.println(" │ ");
					System.out.print(" └■ DATA MOVIMENTO:");
					LocalDate dataMov = LocalDate.now();
					System.out.println(dataMov.format(fmt));

					// Chamando o método que faz o cadastro completo do Asset
					a.atribuirUsuario(caminhoDiretorio, caminho, serviceTag, tipo, perfil, valor, notaFiscal,
							proprietario, local, dataCadastro, status, sistemaOperacional, usuario, departamento,
							dataMov);

				} catch (NumberFormatException e) {// Informa que tentou converter uma string para um formato numérico
					System.err.println(" └■ Erro: " + e.getMessage());
				}
				break;
			}

			/*
			 * [2] Localizar ativo: o programa faz a busca no arquivo “CadastroAsset.txt”
			 * conforme o dado informado na busca. Caso encontre, exibe as informações do(s)
			 * ativo(s).
			 */
			case "2": {
				Asset a = new Asset();
				System.out.println(" │ ");
				System.out.print(" └■ LOCALIZAR:");
				String localizar = ler.nextLine().toUpperCase().trim();

				// Chamando o método que localiza
				a.localizar(caminhoDiretorio, caminho, localizar);
				break;
			}

			/*
			 * [3] Saída de ativo: realiza a saída de um ativo do estoque, sendo feito por
			 * meio de modificação do “status”, “usuário”, “departamento” e “dataMov”.
			 */
			case "3": {
				Asset a = new Asset();

				System.out.println(" │ ");
				System.out.print(" └■ LOCALIZAR:");
				String stSaida = ler.nextLine().toUpperCase().trim();

				// Validação Service Tag
				if (stSaida.isEmpty()) {
					System.out.println(" │");
					System.out.println(" └█ SERVICE TAG não informado");
					break;
				}
				System.out.println(" │ ");
				System.out.println(" └■ STATUS:");
				System.out.println("  └► [1] EM USO");
				System.out.println("  └► [2] SUPORTE TÉCNICO");
				System.out.print(" └■ Opção: ");
				a.setStatus(ler.nextLine().toUpperCase().trim());

				// Validação STATUS utilizando o get e set
				if (a.getStatus().equals("1")) {
					a.setStatus("EM USO");
				} else if (a.getStatus().equals("2")) {
					a.setStatus("SUPORTE TÉCNICO");
				} else {
					System.out.println(" │");
					System.out.println(" └█ STATUS inválido!");
					break;
				}

				System.out.println(" │ ");
				System.out.println(" └■ SISTEMA OPERACIONAL:");
				System.out.println("  └► [1] WINDOWNS");
				System.out.println("  └► [2] LINUX");
				System.out.println("  └► [3] MAC OS");
				System.out.println("  └► [4] SEM SO");
				System.out.print(" └■ Opção: ");
				a.setSistemaOperacional(ler.nextLine().toUpperCase().trim());

				// Validação SISTEMA OPERACIONAL utilizando o get e set
				if (a.getSistemaOperacional().equals("1")) {
					a.setSistemaOperacional("WINDOWNS");
				} else if (a.getSistemaOperacional().equals("2")) {
					a.setSistemaOperacional("LINUX");
				} else if (a.getSistemaOperacional().equals("3")) {
					a.setSistemaOperacional("MAC OS");
				} else if (a.getSistemaOperacional().equals("4")) {
					a.setSistemaOperacional("SEM SO");
				} else {
					System.out.println(" │");
					System.out.println(" └█ SISTEMA OPERACIONAL inválido!");
					break;
				}

				System.out.println(" │ ");
				System.out.print(" └■ USUÁRIO: ");
				a.setUsuario(ler.nextLine().toUpperCase().trim());

				// Validação USUÁRIO
				if (a.getUsuario().isEmpty()) {
					System.out.println(" │");
					System.out.println(" └█ USUÁRIO não informado");
					break;
				} else if (!a.getUsuario().matches("[a-zA-Z\s]+")) {
					System.out.println(" │");
					System.out.println(" └█ USUÁRIO contém caracteres inválidos!");
					break;
				}
				System.out.println(" │ ");
				System.out.print(" └■ DEPARTAMENTO: ");
				a.setDepartamento(ler.nextLine().toUpperCase().trim());

				// Validação DEPARTAMENTO
				if (a.getDepartamento().isEmpty()) {
					System.out.println(" │");
					System.out.println(" └█ USUÁRIO não informado");
					break;
				} else if (!a.getDepartamento().matches("[a-zA-Z\s]+")) {
					System.out.println(" │");
					System.out.println(" └█ USUÁRIO contém caracteres inválidos!");
					break;
				}

				// Modificando a data de movimento para a atual
				System.out.println(" │ ");
				System.out.print(" └■ DATA MOVIMENTO:");
				LocalDate dataMov = LocalDate.now();
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				System.out.println(dataMov.format(fmt));

				// Chamando o método que faz a saída de um Asset
				a.saidaAsset(caminhoDiretorio, caminho, stSaida);
				break;
			}

			/*
			 * [4] Entrada de ativo: realiza a entrada de um ativo do estoque, sendo feito
			 * por meio de modificação do “status”, “usuário”, “departamento” e “dataMov”.
			 */
			case "4": {
				Asset a = new Asset();

				System.out.println(" │ ");
				System.out.print(" └■ LOCALIZAR:");
				String stEntrada = ler.nextLine().toUpperCase().trim();

				if (stEntrada.isEmpty()) {
					System.out.println(" │");
					System.out.println(" └█ Service Tag não informado");
					break;
				}
				System.out.println(" │ ");
				System.out.println(" └■ STATUS:");
				System.out.println("  └► [1] ESTOQUE");
				System.out.println("  └► [2] DEFEITO");
				System.out.println("  └► [3] OBSOLETO");
				System.out.print(" └■ Opção: ");
				a.setStatus(ler.nextLine().toUpperCase().trim());

				if (a.getStatus().equals("1")) {
					a.setStatus("ESTOQUE");
				} else if (a.getStatus().equals("2")) {
					a.setStatus("DEFEITO");
				} else if (a.getStatus().equals("3")) {
					a.setStatus("OBSOLETO");
				} else {
					System.out.println(" │");
					System.out.println(" └█ STATUS inválido!");
					break;
				}

				System.out.println(" │ ");
				System.out.println(" └■ SISTEMA OPERACIONAL:");
				System.out.println("  └► [1] WINDOWNS");
				System.out.println("  └► [2] LINUX");
				System.out.println("  └► [3] MAC OS");
				System.out.println("  └► [4] SEM SO");
				System.out.print(" └■ Opção: ");
				a.setSistemaOperacional(ler.nextLine().toUpperCase().trim());

				if (a.getSistemaOperacional().equals("1")) {
					a.setSistemaOperacional("WINDOWNS");
				} else if (a.getSistemaOperacional().equals("2")) {
					a.setSistemaOperacional("LINUX");
				} else if (a.getSistemaOperacional().equals("3")) {
					a.setSistemaOperacional("MAC OS");
				} else if (a.getSistemaOperacional().equals("4")) {
					a.setSistemaOperacional("SEM SO");
				} else {
					System.out.println(" │");
					System.out.println(" └█ SISTEMA OPERACIONAL inválido!");
					break;
				}

				System.out.println(" │ ");
				System.out.print(" └■ USUÁRIO: ");
				a.setUsuario(ler.nextLine().toUpperCase().trim());

				if (a.getUsuario().isEmpty()) {
					System.out.println(" │");
					System.out.println(" └█ USUÁRIO não informado");
					break;
				} else if (!a.getUsuario().matches("[a-zA-Z\s]+")) {
					System.out.println(" │");
					System.out.println(" └█ USUÁRIO contém caracteres inválidos!");
					break;
				}
				System.out.println(" │ ");
				System.out.print(" └■ DEPARTAMENTO: ");
				a.setDepartamento(ler.nextLine().toUpperCase().trim());

				if (a.getDepartamento().isEmpty()) {
					System.out.println(" │");
					System.out.println(" └█ USUÁRIO não informado");
					break;
				} else if (!a.getDepartamento().matches("[a-zA-Z\s]+")) {
					System.out.println(" │");
					System.out.println(" └█ USUÁRIO contém caracteres inválidos!");
					break;
				}

				System.out.println(" │ ");
				System.out.print(" └■ DATA MOVIMENTO:");
				LocalDate dataMov = LocalDate.now();
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				System.out.println(dataMov.format(fmt));

				// Chamando o método que faz a entrada de um Asset
				a.entradaAsset(caminhoDiretorio, caminho, stEntrada);
				break;
			}

			/*
			 * [5] Listagem geral ativos: o programa faz a leitura do arquivo
			 * “CadastroAsset.txt” e retorna as informações de todos os ativos cadastrados.
			 */
			case "5": {
				Asset a = new Asset();
				a.listarAsset(caminhoDiretorio, caminho);
				break;
			}
			/*
			 * [6] Listagem por parâmetro: o programa faz a leitura do arquivo
			 * “CadastroAsset.txt” conforme o dado informado na busca e retorna à informação
			 * do ativo cadastrado no console(tela).
			 */
			case "6": {
				Asset a = new Asset();

				System.out.println(" │ ");
				System.out.print(" └■ LOCALIZAR: ");
				String localizar = ler.nextLine().toUpperCase().trim();

				a.listarAsset(caminhoDiretorio, caminho, localizar);
				break;
			}

			/*
			 * [7] Resumo ativos – quantitativo: o programa lê o arquivo “CadastroAsset.txt”
			 * e exibe a quantidade de ativos por tipo – Notebook, macbook, desktop etc.
			 */
			case "7": {
				Asset a = new Asset();
				a.quantitativoAsset(caminhoDiretorio, caminho);
				break;
			}

			/*
			 * [8] Resumo ativos – contábil: o programa lê o arquivo “CadastroAsset.txt” e
			 * exibe o montante dos ativos por tipo – Notebook, macbook, desktop etc.
			 */
			case "8": {
				Asset a = new Asset();
				a.contabilAsset(caminhoDiretorio, caminho);
				break;
			}

			/*
			 * [9] Excluir ativos obsoletos: o programa lê o arquivo “CadastroAsset.txt,
			 * conforme o dado informado na busca e remove o ativo com status “OBSOLETO”
			 * caso encontre.
			 */
			case "9": {
				Asset a = new Asset();

				System.out.println(" │ ");
				System.out.print(" └■ LOCALIZAR: ");
				String excluirSt = ler.nextLine().toUpperCase().trim();

				a.excluirAsset(caminhoDiretorio, caminho, excluirSt);
				break;
			}
			/*
			 * [s] Sair do programa
			 */
			case "s":
			case "S": {
				System.out.println(" │ ");
				System.out.println(" └█ SAIR... ");
				System.out.println("  │ ");
				System.out.println("  │╔─────────────────────────────────────────────╗");
				System.out.println("  └│          ■│▒│ FIM DO PROGRAMA │▒│■          │");
				System.out.println("   └─────────────────────────────────────────────┘");
				break;
			}
			default:
				break;
			}
			// Repete o menu enquanto o usuário não escolher sair
		} while (!op.equalsIgnoreCase("s"));
		ler.close();// Fechamento classe Scanner
	}

}
