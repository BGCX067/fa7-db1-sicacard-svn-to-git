package br.com.sicacard.model.entity;


/**
 * Posto.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
public class Posto implements GenericEntity{

	private static final long serialVersionUID = 5470928451346777293L;
	private Long cnpj;
	private String nomeFantasia;
	private String razaoSocial;
	private String telefone;
	private DadosBancario dadosBancario;


	/**
	 * Default constructor.
	 */
	public Posto() {
		super();
	}

	/**
	 * Get the nomeFantasia
	 * @return <code>String</code>
	 */
	public final String getNomeFantasia() {
		return nomeFantasia;
	}

	/**
	 * Set the nomeFantasia
	 * @param nomeFantasia the <code>String</code>
	 */
	public final void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	/**
	 * Get the razaoSocial
	 * @return <code>String</code>
	 */
	public final String getRazaoSocial() {
		return razaoSocial;
	}


	/**
	 * Set the razaoSocial
	 * @param razaoSocial the <code>String</code>
	 */
	public final void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	/**
	 * Get the cnpj
	 * @return <code>Long</code>
	 */
	public final Long getCnpj() {
		return cnpj;
	}

	/**
	 * Set the cnpj
	 * @param cnpj the <code>Long</code>
	 */
	public final void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Get the telefone
	 * @return <code>String</code>
	 */
	public final String getTelefone() {
		return telefone;
	}

	/**
	 * Set the telefone
	 * @param telefone the <code>String</code>
	 */
	public final void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Get the dadosBancario
	 * @return <code>DadosBancario</code>
	 */
	public final DadosBancario getDadosBancario() {
		return dadosBancario;
	}

	/**
	 * Set the dadosBancario
	 * @param dadosBancario the <code>DadosBancario</code>
	 */
	public final void setDadosBancario(DadosBancario dadosBancario) {
		this.dadosBancario = dadosBancario;
	}

}
