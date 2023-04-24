package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// Herança
public class Asset extends AssetRegister {

	// Declaração de atributos
	private String status;
	private String sistemaOperacional;
	private String usuario;
	private String departamento;
	private LocalDate dataMov;

	// Construtor vazio
	public Asset() {
		super();
	}

	// Construtor com parâmetros
	public Asset(String serviceTag, String tipo, String modelo, String perfil, double valor, int notaFiscal,
			String proprietario, String local, LocalDate dataCadastro, String status, String sistemaOperacional,
			String usuario, String departamento, LocalDate dataMov) {
		// Chamando o construtor da classe pai para inicializar atributos
		super(serviceTag, tipo, perfil, valor, notaFiscal, proprietario, local, dataCadastro);
		this.status = status;
		this.sistemaOperacional = sistemaOperacional;
		this.usuario = usuario;
		this.departamento = departamento;
		this.dataMov = dataMov;
	}

	// Getters e Setters
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSistemaOperacional() {
		return sistemaOperacional;
	}

	public void setSistemaOperacional(String sistemaOperacional) {
		this.sistemaOperacional = sistemaOperacional;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public LocalDate getDataMov() {
		return dataMov;
	}

	public void setDataMov(LocalDate dataMov) {
		this.dataMov = dataMov;
	}

	/*
	 * Atribuir um usuário a um asset e cadastrar o asset e enviar as informações
	 * para o arquivo "CadastroAsset.txt".
	 */
	public boolean atribuirUsuario(String caminhoDiretorio, String caminho, String serviceTag, String tipo,
			String perfil, double valor, int notaFiscal, String proprietario, String local, LocalDate dataCadastro,
			String status, String sistemaOperacional, String usuario, String departamento, LocalDate dataMov) {

		try {// Inicio bloco try-catch

			// Chamando método para verificar se diretório ou arquivo existem
			verificarArquivo(caminhoDiretorio, caminho);

			// Abrindo o arquivo em modo leitura
			BufferedReader br = new BufferedReader(new FileReader(caminho));

			// Abrindo o arquivo em modo escrita acrescentando informação
			BufferedWriter bw = new BufferedWriter(new FileWriter(caminho, true));

			boolean cadastrado = false;

			// Laço de repetição para saber se o vet[0] já existe no arquivo
			while (br.ready()) {// Enquanto tiver linha para ler, retorna true
				String linha = br.readLine();
				String vet[] = linha.split("#");
				if (vet.length >= 2 && vet[0].equalsIgnoreCase(serviceTag)) {
					cadastrado = true;
				}
			}
			br.close(); // Fechando o arquivo

			// Caso true, não cadastrar
			if (cadastrado) {
				System.out.println(" │");
				System.out.println(" └█ Service Tag já cadastrado!");

				// Caso false, cadastra novo ativo
			} else {
				// Override, modificando o método pai "cadastrarAsset"
				super.cadastrarAsset(caminhoDiretorio, caminho, serviceTag, tipo, perfil, valor, notaFiscal,
						proprietario, local, dataCadastro);

				dataCadastro = LocalDate.now();// Data atual
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");// Formatação data

				// Escrevendo no arquivo
				bw.write("#" + status + "#" + sistemaOperacional + "#" + usuario + "#" + departamento + "#"
						+ dataMov.format(fmt));
				bw.newLine();// Criar nova linha (pular linha)
				System.out.println(" │");
				System.out.println(" └█ Asset cadastrado com sucesso!");
				bw.close();
			}
		} catch (Exception e) {
			e.printStackTrace();// Imprime todos os erros encontrado
		}
		return false;

	}

	/*
	 * Realizar uma busca (localizar)
	 */
	public boolean localizar(String caminhoDiretorio, String caminho, String localizar) {
		try {
			verificarArquivo(caminhoDiretorio, caminho);

			if (localizar.isEmpty()) {// Retorna um boolean informando se está vazia ou não
				System.out.println(" │");
				System.out.println(" └█ Campo em branco!");
			} else {

				BufferedReader br = new BufferedReader(new FileReader(caminho));
				boolean naoLocalizado = true;

				System.out.println("\n\t\t\t\t\t\t\t\t\t◄│▒│ LISTAGEM DE ATIVOS │▒│► ");
				System.out.println(
						"╔─►─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╗");
				System.out.format(
						"%12.12s %8.8s %8.8s %8.8s %12.12s %12.12s %6.6s %14.14s %8.8s %10.10s %20.20s %15.15s %15.15s",
						"SERVICE TAG", "TIPO", "PERFIL", "VALOR", "NOTA FISCAL", "PROPRIETÁRIO", "LOCAL",
						"DATA CADASTRO", "STATUS", "SO", "USUÁRIO", "DEPARTAMENTO", "DATA MOVIMENTO");
				System.out.println();
				System.out.println(
						"╚─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────◄─╝");
				while (br.ready()) {
					String linha = br.readLine();
					String vet[] = linha.split("#");
					for (String indice : vet) {
						if (indice.contains(localizar)) {
							System.out.format(
									"%12.12s %8.8s %8.8s %8.8s %12.12s %12.12s %6.6s %14.14s %8.8s %10.10s %20.20s %15.15s %15.15s",
									vet[0], vet[1], vet[2], vet[3], vet[4], vet[5], vet[6], vet[7], vet[8], vet[9],
									vet[10], vet[11], vet[12]);
							System.out.println();
							naoLocalizado = false;
							break;
						}
					}
				}
				System.out.println(
						"╚───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╝");
				if (naoLocalizado) {
					System.out.println(" │");
					System.out.println(" └█ Não encontramos resultado para sua pesquisa. <" + localizar + ">");
				}
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Realiza a saída de um ativo do inventário
	 */
	public boolean saidaAsset(String caminhoDiretorio, String caminho, String stSaida) {
		try {
			verificarArquivo(caminhoDiretorio, caminho);
			if (stSaida.isEmpty()) {
				System.out.println(" │");
				System.out.println(" └█ Campo em branco!");
			} else {

				BufferedReader br = new BufferedReader(new FileReader(caminho));
				// Escrevendo no arquivo temporário
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(caminho.replace("\\CadastroAsset.txt", "\\tmp.txt")));

				dataMov = LocalDate.now();
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				while (br.ready()) {
					String linha = br.readLine();
					String vet[] = linha.split("#");// Tratando a linha, fazendo a separação das Strings pelo caracter #
					// Editando linha
					if (vet[0].equalsIgnoreCase(stSaida)) {
						bw.write(vet[0] + "#" + vet[1] + "#" + vet[2] + "#" + vet[3] + "#" + vet[4] + "#" + vet[5] + "#"
								+ vet[6] + "#" + vet[7] + "#" + status + "#" + sistemaOperacional + "#" + usuario + "#"
								+ departamento + "#" + dataMov.format(fmt));

						bw.newLine();
					} else {
						bw.write(linha);
						bw.newLine();
					}
				}
				System.out.println(" │");
				System.out.println(" └█ Saída realizada com sucesso!");
				br.close();
				bw.close();
			}
			// Transferindo informações do arquivo temporário para "CadastroAsset.txt"
			BufferedWriter bw2 = new BufferedWriter(new FileWriter(caminho));
			BufferedReader br2 = new BufferedReader(
					new FileReader(caminho.replace("\\CadastroAsset.txt", "\\tmp.txt")));
			while (br2.ready()) {
				String linha = br2.readLine();
				bw2.write(linha);
				bw2.newLine();
			}

			br2.close();
			bw2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Realiza a entrada de um ativo do inventário
	 */
	public boolean entradaAsset(String caminhoDiretorio, String caminho, String stEntrada) {
		try {
			verificarArquivo(caminhoDiretorio, caminho);

			if (stEntrada.isEmpty()) {
				System.out.println(" │");
				System.out.println(" └█ Campo em branco!");
			} else {

				BufferedReader br = new BufferedReader(new FileReader(caminho));
				BufferedWriter bw = new BufferedWriter(
						new FileWriter(caminho.replace("\\CadastroAsset.txt", "\\tmp.txt")));

				dataMov = LocalDate.now();
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

				while (br.ready()) {
					String linha = br.readLine();
					String vet[] = linha.split("#");
					if (vet[0].equalsIgnoreCase(stEntrada)) {
						bw.write(vet[0] + "#" + vet[1] + "#" + vet[2] + "#" + vet[3] + "#" + vet[4] + "#" + vet[5] + "#"
								+ vet[6] + "#" + vet[7] + "#" + status + "#" + sistemaOperacional + "#" + usuario + "#"
								+ departamento + "#" + dataMov.format(fmt));
						bw.newLine();
					} else {
						bw.write(linha);
						bw.newLine();
					}
				}
				System.out.println(" │");
				System.out.println(" └█ Entrada realizada com sucesso!");

				br.close();
				bw.close();
			}

			BufferedWriter bw2 = new BufferedWriter(new FileWriter(caminho));
			BufferedReader br2 = new BufferedReader(
					new FileReader(caminho.replace("\\CadastroAsset.txt", "\\tmp.txt")));
			while (br2.ready()) {
				String linha = br2.readLine();
				bw2.write(linha);
				bw2.newLine();
			}

			br2.close();
			bw2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Listar todos os ativos no console(tela)
	 */
	public boolean listarAsset(String caminhoDiretorio, String caminho) {
		try {
			verificarArquivo(caminhoDiretorio, caminho);

			BufferedReader br = new BufferedReader(new FileReader(caminho));

			System.out.println("\n\t\t\t\t\t\t\t\t\t◄│▒│ LISTAGEM DE ATIVOS │▒│► ");
			System.out.println(
					"╔─►─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╗");
			System.out.format(
					"%12.12s %8.8s %8.8s %8.8s %12.12s %12.12s %6.6s %14.14s %8.8s %10.10s %20.20s %15.15s %15.15s",
					"SERVICE TAG", "TIPO", "PERFIL", "VALOR", "NOTA FISCAL", "PROPRIETÁRIO", "LOCAL", "DATA CADASTRO",
					"STATUS", "SO", "USUÁRIO", "DEPARTAMENTO", "DATA MOVIMENTO");
			System.out.println();
			System.out.println(
					"╚─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────◄─╝");
			while (br.ready()) {
				String linha = br.readLine();
				String vet[] = linha.split("#");

				System.out.format(
						"%12.12s %8.8s %8.8s %8.8s %12.12s %12.12s %6.6s %14.14s %8.8s %10.10s %20.20s %15.15s %15.15s",
						vet[0], vet[1], vet[2], vet[3], vet[4], vet[5], vet[6], vet[7], vet[8], vet[9], vet[10],
						vet[11], vet[12]);
				System.out.println();
			}
			br.close();

			System.out.println(
					"╚───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╝");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Listar os ativos por parâmetro no console(tela)
	 */
	public boolean listarAsset(String caminhoDiretorio, String caminho, String localizar) {
		try {
			verificarArquivo(caminhoDiretorio, caminho);
			if (localizar.isEmpty()) {
				System.out.println(" │");
				System.out.println(" └█ Campo em branco!");
			} else {

				BufferedReader br = new BufferedReader(new FileReader(caminho));
				boolean naoLocalizado = true;

				System.out.println("\n\t\t\t\t\t\t\t\t\t◄│▒│ LISTAGEM DE ATIVOS │▒│► ");
				System.out.println(
						"╔─►─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╗");
				System.out.format(
						"%12.12s %8.8s %8.8s %8.8s %12.12s %12.12s %6.6s %14.14s %8.8s %10.10s %20.20s %15.15s %15.15s",
						"SERVICE TAG", "TIPO", "PERFIL", "VALOR", "NOTA FISCAL", "PROPRIETÁRIO", "LOCAL",
						"DATA CADASTRO", "STATUS", "SO", "USUÁRIO", "DEPARTAMENTO", "DATA MOVIMENTO");
				System.out.println();
				System.out.println(
						"╚─────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────◄─╝");
				while (br.ready()) {
					String linha = br.readLine();
					String vet[] = linha.split("#");
					for (String indice : vet) {
						if (indice.contains(localizar)) {
							System.out.format(
									"%12.12s %8.8s %8.8s %8.8s %12.12s %12.12s %6.6s %14.14s %8.8s %10.10s %20.20s %15.15s %15.15s",
									vet[0], vet[1], vet[2], vet[3], vet[4], vet[5], vet[6], vet[7], vet[8], vet[9],
									vet[10], vet[11], vet[12]);
							System.out.println();
							naoLocalizado = false;
							break;
						}
					}
				}
				System.out.println(
						"╚───────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────╝");
				if (naoLocalizado) {
					System.out.println(" │");
					System.out.println(" └█ Não encontramos resultado para sua pesquisa. <" + localizar + ">");
				}
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Exibir a quantidade de ativos por item
	 */
	public boolean quantitativoAsset(String caminhoDiretorio, String caminho) {
		try {
			verificarArquivo(caminhoDiretorio, caminho);

			ArrayList<String> listaAsset = new ArrayList<String>();// Declarando e instanciando uma ArrayList

			BufferedReader br = new BufferedReader(new FileReader(caminho));

			while (br.ready()) {
				String linha = br.readLine();
				String vet[] = linha.split("#");
				listaAsset.add(vet[1]);// Adicionando um item na ArrayList
			}

			br.close();

			// Contador
			int notebook = 0;
			int macbook = 0;
			int imac = 0;
			int desktop = 0;
			int aio = 0;
			int monitor = 0;

			// Percorrendo a lista
			for (int i = 0; i < listaAsset.size(); i++) {
				String asset = listaAsset.get(i);

				switch (asset) {
				case "NOTEBOOK":
					notebook++;
					break;
				case "MACBOOK":
					macbook++;
					break;
				case "IMAC":
					imac++;
					break;
				case "DESKTOP":
					desktop++;
					break;
				case "AIO":
					aio++;
					break;
				case "MONITOR":
					monitor++;
					break;
				default:
					break;
				}
			}

			System.out.println("╔────────────────────────╗");
			System.out.println("│ │▒│ ESTOQUE ATIVOS │▒│ │");
			System.out.println("└────────────────────────┘");
			System.out.printf("%7s %10s", "ASSET ", "  VOL.\n");
			System.out.println(" ──────────────────────── ");
			System.out.printf("%10s %4s", " Notebook: ", notebook + "\n");
			System.out.printf("%10s %4s", " Macbook:  ", macbook + "\n");
			System.out.printf("%10s %4s", " Macbook:  ", imac + "\n");
			System.out.printf("%10s %4s", " Desktop:  ", desktop + "\n");
			System.out.printf("%10s %4s", " Macbook:  ", aio + "\n");
			System.out.printf("%10s %4s", " Monitor:  ", monitor + "\n");
			System.out.println("┌────────────────────────┐");
			System.out.printf("%10s %4s", "  Total:     ", listaAsset.size() + "\n");
			System.out.println("└────────────────────────┘");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Exibir soma de valores de ativos por item
	 */
	public boolean contabilAsset(String caminhoDiretorio, String caminho) {
		try {
			verificarArquivo(caminhoDiretorio, caminho);

			BufferedReader br = new BufferedReader(new FileReader(caminho));

			ArrayList<String> listaAsset = new ArrayList<String>();// ArrayList para o item
			ArrayList<Double> listaValores = new ArrayList<Double>();// ArrayList para o valor do item

			while (br.ready()) {
				String linha = br.readLine();
				String vet[] = linha.split("#");
				listaAsset.add(vet[1]);
				listaValores.add(Double.parseDouble(vet[3]));
			}
			br.close();

			double notebook = 0;
			double macbook = 0;
			double imac = 0;
			double desktop = 0;
			double aio = 0;
			double monitor = 0;
			double total = 0;

			for (int i = 0; i < listaAsset.size(); i++) {
				String asset = listaAsset.get(i);
				double valores = listaValores.get(i);

				switch (asset) {
				case "NOTEBOOK":
					notebook += valores;
					break;
				case "MACBOOK":
					macbook += valores;
					break;
				case "IMAC":
					imac += valores;
					break;
				case "DESKTOP":
					desktop += valores;
					break;
				case "AIO":
					aio += valores;
					break;
				case "MONITOR":
					monitor += valores;
					break;
				default:
					break;
				}
				total += valores;
			}

			System.out.println("╔────────────────────────╗");
			System.out.println("│ │▒│ ESTOQUE ATIVOS │▒│ │");
			System.out.println("└────────────────────────┘");
			System.out.printf("%7s %10s", "ASSET ", "  VALOR\n");
			System.out.println(" ──────────────────────── ");
			System.out.printf("%10s %4s", " Notebook: R$ ", notebook + "\n");
			System.out.printf("%10s %4s", " Macbook:  R$ ", macbook + "\n");
			System.out.printf("%10s %4s", " Macbook:  R$ ", imac + "\n");
			System.out.printf("%10s %4s", " Desktop:  R$ ", desktop + "\n");
			System.out.printf("%10s %4s", " Macbook:  R$ ", aio + "\n");
			System.out.printf("%10s %4s", " Monitor:  R$ ", monitor + "\n");
			System.out.println("┌────────────────────────┐");
			System.out.printf("%10s %4s", "  Total:   R$ ", total + "\n");
			System.out.println("└────────────────────────┘");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * Excluir ativo com status OBSOLETO conforme busca do service tag
	 */
	public boolean excluirAsset(String caminhoDiretorio, String caminho, String excluirSt) {
		try {
			verificarArquivo(caminhoDiretorio, caminho);

			BufferedReader br = new BufferedReader(new FileReader(caminho));
			BufferedWriter bw = new BufferedWriter(
					new FileWriter(caminho.replace("\\CadastroAsset.txt", "\\Excluir.txt")));

			boolean naoLocalizado = true;
			if (excluirSt.isEmpty()) {
				System.out.println(" │");
				System.out.println(" └█ Asset não localizado!");
			} else {

				while (br.ready()) {
					String linha;
					linha = br.readLine();
					String vet[] = linha.split("#");
					if (vet[0].equalsIgnoreCase(excluirSt) && vet[8].equalsIgnoreCase("OBSOLETO")) {
						naoLocalizado = false;
						System.out.println(" │");
						System.out.println(" └█ Asset localizado e excluído!");
						continue;

					} else {
						bw.write(linha);
						bw.newLine();
					}
				}

				br.close();
				bw.close();

				BufferedWriter bw2 = new BufferedWriter(new FileWriter(caminho));
				BufferedReader br2 = new BufferedReader(
						new FileReader(caminho.replace("\\CadastroAsset.txt", "\\Excluir.txt")));

				while (br2.ready()) {
					String linha = br2.readLine();
					bw2.write(linha);
					bw2.newLine();
				}
				br2.close();
				bw2.close();

			}
			if (naoLocalizado) {
				System.out.println(" │");
				System.out.println(" └█ Não encontramos resultado para sua pesquisa. <" + excluirSt + ">");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
