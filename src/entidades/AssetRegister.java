package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AssetRegister {

	// Declaração de atributos
	private String serviceTag;
	private String tipo;
	private String perfil;
	private double valor;
	private int notaFiscal;
	private String proprietario;
	private String local;
	private LocalDate dataCadastro;

	// Construtor vazio
	public AssetRegister() {
		super();
	}

	// Construtor com parâmetros
	public AssetRegister(String serviceTag, String tipo, String perfil, double valor, int notaFiscal,
			String proprietario, String local, LocalDate dataCadastro) {
		super();
		this.serviceTag = serviceTag;
		this.tipo = tipo;
		this.perfil = perfil;
		this.valor = valor;
		this.notaFiscal = notaFiscal;
		this.proprietario = proprietario;
		this.local = local;
		this.dataCadastro = dataCadastro;
	}

	// Getters e Setters
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getServiceTag() {
		return serviceTag;
	}

	public String getTipo() {
		return tipo;
	}

	public String getPerfil() {
		return perfil;
	}

	public double getValor() {
		return valor;
	}

	public int getNotaFiscal() {
		return notaFiscal;
	}

	public String getProprietario() {
		return proprietario;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	/*
	 * Verifica se os caminhos existem, caso não, cria o diretório e o arquivo
	 */
	public boolean verificarArquivo(String caminhoDiretorio, String caminho) {

		File d = new File(caminhoDiretorio);

		if (d.exists()) {
			try {
				File f = new File(caminho);
				if (f.createNewFile()) {
				}
			} catch (Exception e) {
				System.err.println("Erro ao criar o arquivo. Erro: " + e);
			}
		} else {
			if (d.mkdirs()) {
				try {
					File f = new File(caminho);
					if (f.createNewFile()) {
					}
				} catch (Exception e) {
					System.err.println("Erro ao criar o arquivo. Erro: " + e);
				}
			} else {
				System.err.println("Erro ao criar os diretórios");
			}
		}
		return false;
	}

	/*
	 * Cadastrar um novo ativo e enviar as informações para o arquivo
	 * "CadastroAsset.txt".
	 */
	public boolean cadastrarAsset(String caminhoDiretorio, String caminho, String serviceTag, String tipo,
			String perfil, double valor, int notaFiscal, String proprietario, String local, LocalDate dataCadastro) {

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
				dataCadastro = LocalDate.now();// Data atual
				DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");// Formatação data

				// Escrevendo no arquivo
				bw.write(serviceTag + "#" + tipo + "#" + perfil + "#" + valor + "#" + notaFiscal + "#" + proprietario
						+ "#" + local + "#" + dataCadastro.format(fmt));
				bw.close();
			}

		} catch (

		Exception e) {
			e.printStackTrace();// Imprime todos os erros encontrado
		}

		return false;
	}

}
