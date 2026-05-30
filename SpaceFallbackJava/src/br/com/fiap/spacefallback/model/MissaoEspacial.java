package br.com.fiap.spacefallback.model;

/**
 * Classe que representa uma missão espacial sustentável
 * ligada ao projeto SpaceFallback Data Intelligence.
 * 
 * Esta classe herda de ProjetoEspacial e implementa o método
 * exibirDados(), aplicando sobrescrita de método.
 * 
 * @author Ana Luiza Tibiriçá da Paixão
 * RM: 562098
 */
public class MissaoEspacial extends ProjetoEspacial {

	// Construtor vazio que chama o construtor da classe mãe
	public MissaoEspacial() {
		super();
	}

	// Construtor completo que repassa os dados para a classe mãe usando super
	public MissaoEspacial(
			String nome, 
			String areaImpacto, 
			String objetivo, 
			String odsRelacionado,
			String tipoTecnologia, 
			int grauPrioridade, 
			String status
			) {super(nome, areaImpacto, objetivo, odsRelacionado, tipoTecnologia, grauPrioridade, status);
	}

	// Sobrescrita do método abstrato exibirDados definido na classe ProjetoEspacial
	@Override
	public void exibirDados() {
		System.out.println("---------------------------------------------");
		System.out.println("Nome da missão: " + getNome());
		System.out.println("Área de impacto: " + getAreaImpacto());
		System.out.println("Objetivo: " + getObjetivo());
		System.out.println("ODS relacionado: " + getOdsRelacionado());
		System.out.println("Tipo de tecnologia: " + getTipoTecnologia());
		System.out.println("Grau de prioridade: " + getGrauPrioridade());
		System.out.println("Status: " + getStatus());
		System.out.println("---------------------------------------------");
	}
}