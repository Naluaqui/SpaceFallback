package br.com.fiap.spacefallback.app;

import java.util.Scanner;

import br.com.fiap.spacefallback.model.MissaoEspacial;

/**
 * Classe principal da aplicação SpaceFallback Data Intelligence.
 * 
 * Executa o sistema no console, permitindo cadastrar, listar, buscar, alterar
 * status e gerar resumo de missões espaciais sustentáveis ligadas à
 * confiabilidade de dados espaciais.
 * 
 * @author Ana Luiza Tibiriçá da Paixão
 * RM: 562098
 */
public class Main {

	// Constantes que definem regras fixas do sistema
	private static final int LIMITE_MISSOES = 50;
	private static final int INTERACOES_PARA_DESBLOQUEAR = 4;

	// Vetor e contadores usados para armazenar e controlar as missões cadastradas
	private static MissaoEspacial[] missoes = new MissaoEspacial[LIMITE_MISSOES];
	private static int totalMissoes = 0;
	private static int interacoesRealizadas = 0;

	// Dados do usuário e objeto Scanner para entrada de informações pelo console
	private static String nomeUsuario = "";
	private static Scanner entrada = new Scanner(System.in);

	
	// Método principal: inicia a aplicação, exibe a introdução e controla o menu interativo
	public static void main(String[] args) {
		int opcao;

		exibirIntroducao();

		// Estrutura de repetição responsável por manter o sistema funcionando até o usuário escolher sair
		do {
			separarSecao();
			exibirMenu();
			opcao = lerInteiro("Escolha uma opção: ");

			// Estrutura de decisão que executa a funcionalidade escolhida no menu
			switch (opcao) {
			case 1:
				cadastrarMissao();
				registrarInteracao();
				break;
			case 2:
				listarMissoes();
				registrarInteracao();
				break;
			case 3:
				buscarPorNome();
				registrarInteracao();
				break;
			case 4:
				buscarPorAreaImpacto();
				registrarInteracao();
				break;
			case 5:
				alterarStatus();
				registrarInteracao();
				break;
			case 6:
				if (menusEspeciaisDesbloqueados()) {
					cadastrarExemplos();
				} else {
					exibirMensagemMenuBloqueado("Cadastrar exemplos do SpaceFallback");
				}
				break;
			case 7:
				if (menusEspeciaisDesbloqueados()) {
					exibirResumoFinal();
				} else {
					exibirMensagemMenuBloqueado("Exibir resumo final");
				}
				break;
			case 0:
				System.out.println();
				System.out.println("Sistema encerrado.");
				System.out.println("Obrigada por participar da missão SpaceFallback, " + nomeUsuario + ".");
				break;
			default:
				System.out.println("Opção inválida.");
			}

			if (opcao != 0) {
				pausar();
			}

		} while (opcao != 0);

		// Fecha o Scanner ao final da aplicação
		entrada.close();
	}

	// Exibe a introdução narrativa do projeto, contextualizando o problema e a solução SpaceFallback
	private static void exibirIntroducao() {
		System.out.println("=====================================================");
		System.out.println("        BEM-VINDA AO SPACEFALLBACK DATA INTELLIGENCE");
		System.out.println("=====================================================");
		System.out.println();
		System.out.println("Antes de iniciar a missão, precisamos identificar a tripulação.");
		nomeUsuario = lerTextoObrigatorio("Digite seu nome: ");

		separarSecao();
		System.out.println("Se o mundo depende de dados espaciais,");
		System.out.println("cada dado precisa ser confiável, rastreável e acionável");
		System.out.println("antes que ele afete pessoas.");
		pausar();

		separarSecao();
		System.out.println("=====================================================");
		System.out.println("CONTEXTO DA MISSÃO");
		System.out.println("=====================================================");
		System.out.println();
		System.out.println(nomeUsuario + ", estamos em uma missão espacial porque muitos serviços");
		System.out.println("da Terra dependem de dados gerados, enviados ou interpretados por satélites.");
		System.out.println();
		System.out.println("GPS, comunicação, clima, telemetria, telecomando, payload e rastreio");
		System.out.println("podem impactar entregas, ambulâncias, agricultura, cidades, empresas");
		System.out.println("e operações espaciais.");
		System.out.println();
		System.out.println("O problema é que esses dados podem chegar atrasados, incompletos,");
		System.out.println("imprecisos, divergentes ou sem rastreabilidade.");
		pausar();

		separarSecao();
		System.out.println("=====================================================");
		System.out.println("PRIMEIRO PASSO DA MISSÃO");
		System.out.println("=====================================================");
		System.out.println();
		System.out.println("Para resolver esse problema, primeiro precisamos organizar nossas missões.");
		System.out.println();
		System.out.println("Cada missão representa uma parte da solução SpaceFallback:");
		System.out.println("- entender a confiabilidade do dado;");
		System.out.println("- registrar sua origem;");
		System.out.println("- avaliar impacto na Terra;");
		System.out.println("- recomendar uma ação de contingência.");
		pausar();

		separarSecao();
		System.out.println("=====================================================");
		System.out.println("O QUE É O SPACEFALLBACK DATA INTELLIGENCE?");
		System.out.println("=====================================================");
		System.out.println();
		System.out.println("O SpaceFallback Data Intelligence é uma plataforma de transparência");
		System.out.println("e confiabilidade para dados espaciais.");
		System.out.println();
		System.out.println("Ela analisa dados como GPS, telemetria, telecomando, payload,");
		System.out.println("rastreio orbital e dados derivados de satélites.");
		System.out.println();
		System.out.println("O objetivo é calcular confiança, mostrar impacto e recomendar");
		System.out.println("ações quando esses dados deixam de ser seguros para decisão.");
		System.out.println();
		System.out.println("Essa solução apoia pessoas, empresas, equipes pequenas, cidades,");
		System.out.println("operações críticas e serviços que dependem de infraestrutura espacial.");
		pausar();

		separarSecao();
		System.out.println("=====================================================");
		System.out.println("MENUS ESPECIAIS BLOQUEADOS");
		System.out.println("=====================================================");
		System.out.println();
		System.out.println(nomeUsuario + ", para descobrir as missões oficiais do SpaceFallback");
		System.out.println("e acessar o resumo final, desbloqueie os menus 6 e 7.");
		System.out.println();
		System.out.println("Interaja com o sistema, cadastre, liste, busque ou altere missões.");
		System.out.println("Depois de " + INTERACOES_PARA_DESBLOQUEAR + " interações, os menus especiais serão liberados.");
		pausar();
	}

	// Cria um pequeno espaçamento visual entre as seções do console
	private static void separarSecao() {
		System.out.println();
		System.out.println();
	}

	// Pausa a execução até o usuário pressionar ENTER
	private static void pausar() {
		System.out.println();
		System.out.print("Pressione ENTER para continuar...");
		entrada.nextLine();
	}

	// Registra uma interação realizada pelo usuário para liberar os menus especiais
	private static void registrarInteracao() {
		interacoesRealizadas++;
	}

	// Verifica se o usuário já realizou interações suficientes para acessar os menus especiais
	private static boolean menusEspeciaisDesbloqueados() {
		return interacoesRealizadas >= INTERACOES_PARA_DESBLOQUEAR;
	}

	// Calcula quantas interações ainda faltam para desbloquear os menus especiais
	private static int calcularInteracoesRestantes() {
		int faltam = INTERACOES_PARA_DESBLOQUEAR - interacoesRealizadas;

		if (faltam < 0) {
			return 0;
		}

		return faltam;
	}

	// Exibe uma mensagem quando o usuário tenta acessar uma funcionalidade ainda bloqueada
	private static void exibirMensagemMenuBloqueado(String nomeMenu) {
		System.out.println();
		System.out.println("O menu \"" + nomeMenu + "\" ainda está bloqueado.");
		System.out.println(nomeUsuario + ", continue interagindo com o sistema para desbloquear.");
		System.out.println("Faltam " + calcularInteracoesRestantes() + " interação(ões).");
	}

	// Exibe o menu principal e mostra se os menus especiais estão bloqueados ou liberados
	private static void exibirMenu() {
		System.out.println("========== SPACEFALLBACK DATA INTELLIGENCE ==========");
		System.out.println("Tripulante: " + nomeUsuario);
		System.out.println("Interações realizadas: " + interacoesRealizadas + "/" + INTERACOES_PARA_DESBLOQUEAR);

		if (menusEspeciaisDesbloqueados()) {
			System.out.println("Menus 6 e 7 desbloqueados.");
		} else {
			System.out.println("Menus 6 e 7 bloqueados. Faltam " + calcularInteracoesRestantes() + " interação(ões).");
		}

		System.out.println("=====================================================");
		System.out.println("1 - Cadastrar missão espacial");
		System.out.println("2 - Listar missões cadastradas");
		System.out.println("3 - Buscar missão por nome");
		System.out.println("4 - Buscar missão por área de impacto");
		System.out.println("5 - Alterar status da missão");

		if (menusEspeciaisDesbloqueados()) {
			System.out.println("6 - Cadastrar exemplos do SpaceFallback");
			System.out.println("7 - Exibir resumo final");
		} else {
			System.out.println("6 - Cadastrar exemplos do SpaceFallback (BLOQUEADO)");
			System.out.println("7 - Exibir resumo final (BLOQUEADO)");
		}

		System.out.println("0 - Sair");
		System.out.println("=====================================================");
	}

	// Lê e valida números inteiros digitados pelo usuário no console
	private static int lerInteiro(String mensagem) {
		int numero = 0;
		boolean valido = false;

		while (!valido) {
			System.out.print(mensagem);
			String texto = entrada.nextLine();

			try {
				numero = Integer.parseInt(texto);
				valido = true;
			} catch (NumberFormatException erro) {
				System.out.println("Digite um número inteiro válido.");
			}
		}

		return numero;
	}

	// Lê textos obrigatórios e impede que o usuário deixe campos vazios
	private static String lerTextoObrigatorio(String mensagem) {
		String texto;

		do {
			System.out.print(mensagem);
			texto = entrada.nextLine().trim();

			if (texto.length() == 0) {
				System.out.println("Campo obrigatório.");
			}

		} while (texto.length() == 0);

		return texto;
	}

	// Realiza o cadastro manual de uma missão espacial informada pelo usuário
	private static void cadastrarMissao() {
		separarSecao();

		if (totalMissoes >= LIMITE_MISSOES) {
			System.out.println("Limite de missões atingido.");
			return;
		}

		System.out.println("----- Cadastro de missão espacial -----");

		String nome = lerTextoObrigatorio("Nome da missão: ");
		String areaImpacto = lerTextoObrigatorio("Área de impacto: ");
		String objetivo = lerTextoObrigatorio("Objetivo da missão: ");
		String odsRelacionado = lerTextoObrigatorio("ODS relacionado: ");
		String tipoTecnologia = lerTextoObrigatorio("Tipo de tecnologia utilizada: ");
		int grauPrioridade = lerPrioridade();
		String status = escolherStatus();

		MissaoEspacial missao = new MissaoEspacial(nome, areaImpacto, objetivo, odsRelacionado,
				tipoTecnologia, grauPrioridade, status);

		missoes[totalMissoes] = missao;
		totalMissoes++;

		System.out.println();
		System.out.println("Missão cadastrada com sucesso.");
	}

	// Lê e valida o grau de prioridade da missão, aceitando apenas valores de 1 a 5
	private static int lerPrioridade() {
		int prioridade;

		do {
			prioridade = lerInteiro("Grau de prioridade de 1 a 5: ");

			if (prioridade < 1 || prioridade > 5) {
				System.out.println("Prioridade inválida. Digite de 1 a 5.");
			}

		} while (prioridade < 1 || prioridade > 5);

		return prioridade;
	}

	// Exibe as opções de status e retorna o texto correspondente à escolha do usuário
	private static String escolherStatus() {
		System.out.println("Escolha o status:");
		System.out.println("1 - Planejada");
		System.out.println("2 - Em análise");
		System.out.println("3 - Em desenvolvimento");
		System.out.println("4 - Em monitoramento");
		System.out.println("5 - Concluída");

		int opcao = lerInteiro("Status: ");

		while (opcao < 1 || opcao > 5) {
			System.out.println("Status inválido.");
			opcao = lerInteiro("Status: ");
		}

		if (opcao == 1) {
			return "Planejada";
		} else if (opcao == 2) {
			return "Em análise";
		} else if (opcao == 3) {
			return "Em desenvolvimento";
		} else if (opcao == 4) {
			return "Em monitoramento";
		} else {
			return "Concluída";
		}
	}

	// Lista todas as missões cadastradas no veto
	private static void listarMissoes() {
		separarSecao();
		System.out.println("----- Missões cadastradas -----");

		if (totalMissoes == 0) {
			System.out.println("Nenhuma missão cadastrada.");
			return;
		}

		for (int i = 0; i < totalMissoes; i++) {
			System.out.println("Código: " + i);
			missoes[i].exibirDados();
		}
	}

	// Busca missões pelo nome ou por parte do nome informado pelo usuário
	private static void buscarPorNome() {
		separarSecao();

		String termo = lerTextoObrigatorio("Digite o nome ou parte do nome da missão: ");
		boolean encontrou = false;

		for (int i = 0; i < totalMissoes; i++) {
			if (missoes[i].contemNome(termo)) {
				System.out.println("Missão encontrada. Código: " + i);
				missoes[i].exibirDados();
				encontrou = true;
			}
		}

		if (!encontrou) {
			System.out.println("Nenhuma missão encontrada com esse nome.");
		}
	}

	// Busca missões pela área de impacto ou por parte da área informada pelo usuário
	private static void buscarPorAreaImpacto() {
		separarSecao();

		String termo = lerTextoObrigatorio("Digite a área de impacto ou parte dela: ");
		boolean encontrou = false;

		for (int i = 0; i < totalMissoes; i++) {
			if (missoes[i].contemAreaImpacto(termo)) {
				System.out.println("Missão encontrada. Código: " + i);
				missoes[i].exibirDados();
				encontrou = true;
			}
		}

		if (!encontrou) {
			System.out.println("Nenhuma missão encontrada com essa área de impacto.");
		}
	}

	// Permite alterar o status de uma missão já cadastrada
	private static void alterarStatus() {
		separarSecao();
		System.out.println("----- Alteração de status -----");

		if (totalMissoes == 0) {
			System.out.println("Nenhuma missão cadastrada.");
			return;
		}

		listarCodigos();

		int codigo = lerInteiro("Digite o código da missão: ");

		if (codigo < 0 || codigo >= totalMissoes) {
			System.out.println("Código inválido.");
			return;
		}

		System.out.println("Status atual: " + missoes[codigo].getStatus());

		String novoStatus = escolherStatus();
		missoes[codigo].setStatus(novoStatus);

		System.out.println("Status alterado com sucesso.");
	}

	// Mostra os códigos das missões para facilitar a escolha na alteração de status
	private static void listarCodigos() {
		for (int i = 0; i < totalMissoes; i++) {
			System.out.println(i + " - " + missoes[i].getNome() + " | Status: " + missoes[i].getStatus());
		}
	}

	// Exibe o resumo final exigido no enunciado: total, quantidade por status e maior prioridade
	private static void exibirResumoFinal() {
		separarSecao();

		System.out.println("========== RESUMO FINAL ==========");
		System.out.println("Tripulante responsável: " + nomeUsuario);
		System.out.println("Total de missões cadastradas: " + totalMissoes);

		System.out.println();
		System.out.println("Quantidade por status:");
		System.out.println("Planejada: " + contarPorStatus("Planejada"));
		System.out.println("Em análise: " + contarPorStatus("Em análise"));
		System.out.println("Em desenvolvimento: " + contarPorStatus("Em desenvolvimento"));
		System.out.println("Em monitoramento: " + contarPorStatus("Em monitoramento"));
		System.out.println("Concluída: " + contarPorStatus("Concluída"));

		MissaoEspacial maiorPrioridade = buscarMaiorPrioridade();

		System.out.println();

		if (maiorPrioridade != null) {
			System.out.println("Missão com maior prioridade:");
			maiorPrioridade.exibirDados();
		} else {
			System.out.println("Não há missão cadastrada.");
		}
	}

	// Conta quantas missões possuem determinado status
	private static int contarPorStatus(String status) {
		int quantidade = 0;

		for (int i = 0; i < totalMissoes; i++) {
			if (missoes[i].getStatus().equalsIgnoreCase(status)) {
				quantidade++;
			}
		}

		return quantidade;
	}

	// Percorre as missões cadastradas e retorna aquela com maior grau de prioridade
	private static MissaoEspacial buscarMaiorPrioridade() {
		if (totalMissoes == 0) {
			return null;
		}

		MissaoEspacial maior = missoes[0];

		for (int i = 1; i < totalMissoes; i++) {
			if (missoes[i].getGrauPrioridade() > maior.getGrauPrioridade()) {
				maior = missoes[i];
			}
		}

		return maior;
	}

	// Cadastra automaticamente as missões oficiais do SpaceFallback Data Intelligence
	private static void cadastrarExemplos() {
		separarSecao();

		if (totalMissoes + 4 > LIMITE_MISSOES) {
			System.out.println("Não há espaço suficiente para cadastrar os exemplos.");
			return;
		}

		// Criação dos objetos de exemplo usando o construtor completo da classe MissaoEspacial
		MissaoEspacial exemplo1 = new MissaoEspacial(
				"Data Trust Score",
				"Confiabilidade de dados espaciais",
				"Calcular o nível de confiança de dados como GPS, telemetria, payload e rastreio orbital.",
				"ODS 9 - Indústria, Inovação e Infraestrutura",
				"Análise de dados espaciais",
				5,
				"Em desenvolvimento");

		MissaoEspacial exemplo2 = new MissaoEspacial(
				"Data Passport",
				"Transparência e rastreabilidade",
				"Registrar origem, horário, precisão, fonte, status e impacto provável do dado espacial.",
				"ODS 11 - Cidades e Comunidades Sustentáveis",
				"Metadados e rastreabilidade",
				5,
				"Em análise");

		MissaoEspacial exemplo3 = new MissaoEspacial(
				"Impact Layer",
				"Impacto social e operacional",
				"Identificar quem será afetado quando um dado espacial falhar ou perder confiabilidade.",
				"ODS 8 - Trabalho Decente e Crescimento Econômico",
				"Classificação de impacto",
				4,
				"Planejada");

		MissaoEspacial exemplo4 = new MissaoEspacial(
				"Fallback Action",
				"Ação de contingência",
				"Recomendar ações quando dados espaciais deixam de ser seguros para tomada de decisão.",
				"ODS 13 - Ação Contra a Mudança Global do Clima",
				"Sistema de recomendação",
				5,
				"Em monitoramento");

		// Exibe as missões oficiais antes de armazená-las no vetor
		System.out.println("Missões oficiais que serão cadastradas:");
		exemplo1.exibirDados();
		exemplo2.exibirDados();
		exemplo3.exibirDados();
		exemplo4.exibirDados();

		
		// Armazena os exemplos oficiais no vetor de missões e atualiza o contador
		missoes[totalMissoes] = exemplo1;
		totalMissoes++;

		missoes[totalMissoes] = exemplo2;
		totalMissoes++;

		missoes[totalMissoes] = exemplo3;
		totalMissoes++;

		missoes[totalMissoes] = exemplo4;
		totalMissoes++;

		System.out.println("Exemplos oficiais do SpaceFallback cadastrados com sucesso.");
	}
}