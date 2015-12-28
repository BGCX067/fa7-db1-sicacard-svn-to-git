package br.com.sicacard.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Posto.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
@Entity
@Table(name="posto")
public class Posto {

	@Id
	@Column(name="cnpj")
	private String cnpj;

	@Column(name="nome_fantasia")
	private String nomeFantasia;

	@Column(name="razao_social")
	private String razaoSocial;

	@Column(name="telefone")
	private String telefone;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_dados_bancario")
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
	 * @return <code>String</code>
	 */
	public final String getCnpj() {
		return cnpj;
	}


	/**
	 * Set the cnpj
	 * @param cnpj the <code>String</code>
	 */
	public final void setCnpj(String cnpj) {
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
