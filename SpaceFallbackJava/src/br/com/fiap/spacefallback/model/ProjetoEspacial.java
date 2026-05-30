package br.com.fiap.spacefallback.model;

/**
 * Classe abstrata que representa a base de um projeto espacial sustentável.
 * 
 * Ela concentra os atributos comuns de uma missão espacial ligada ao
 * SpaceFallback Data Intelligence.
 * 
 * Como é abstrata, ela serve como modelo para outras classes herdarem
 * seus atributos e comportamentos.
 * 
 * @author Ana Luiza Tibiriçá da Paixão
 * RM: 562098
 */
public abstract class ProjetoEspacial {

	// Atributos encapsulados comuns a qualquer projeto espacial
	private String nome;
	private String areaImpacto;
	private String objetivo;
	private String odsRelacionado;
	private String tipoTecnologia;
	private int grauPrioridade;
	private String status;

	// Construtor vazio utilizado na criação padrão de objetos pelas classes filhas
	public ProjetoEspacial() {
		super();
	}

	// Construtor completo usado para inicializar todos os atributos herdados
	public ProjetoEspacial(String nome, String areaImpacto, String objetivo, String odsRelacionado,
			String tipoTecnologia, int grauPrioridade, String status) {
		super();
		this.nome = nome;
		this.areaImpacto = areaImpacto;
		this.objetivo = objetivo;
		this.odsRelacionado = odsRelacionado;
		this.tipoTecnologia = tipoTecnologia;
		this.grauPrioridade = grauPrioridade;
		this.status = status;
	}

	// Métodos auxiliares para verificar se o nome ou a área de impacto contêm um termo pesquisado
	public boolean contemNome(String termo) {
		return nome != null && nome.toLowerCase().contains(termo.toLowerCase());
	}

	public boolean contemAreaImpacto(String termo) {
		return areaImpacto != null && areaImpacto.toLowerCase().contains(termo.toLowerCase());
	}

	// Método abstrato que obriga as classes filhas a definirem como os dados serão exibidos
	public abstract void exibirDados();

	
	// Métodos getters e setters responsáveis pelo encapsulamento dos atributos
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAreaImpacto() {
		return areaImpacto;
	}

	public void setAreaImpacto(String areaImpacto) {
		this.areaImpacto = areaImpacto;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getOdsRelacionado() {
		return odsRelacionado;
	}

	public void setOdsRelacionado(String odsRelacionado) {
		this.odsRelacionado = odsRelacionado;
	}

	public String getTipoTecnologia() {
		return tipoTecnologia;
	}

	public void setTipoTecnologia(String tipoTecnologia) {
		this.tipoTecnologia = tipoTecnologia;
	}

	public int getGrauPrioridade() {
		return grauPrioridade;
	}

	public void setGrauPrioridade(int grauPrioridade) {
		this.grauPrioridade = grauPrioridade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}