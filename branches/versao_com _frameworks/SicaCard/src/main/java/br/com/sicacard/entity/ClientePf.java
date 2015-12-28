package br.com.sicacard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * ClientePf.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
@Entity
@Table(name="cliente_pf")
@PrimaryKeyJoinColumn(name="cod_cliente")
public class ClientePf extends Cliente {

	@Id
	@Column(name="cpf")
	private String cnpj;

	@Column(name="nome_fantasia")
	private String nomeFantasia;

	@Column(name="razao_social")
	private String razao_social;


	/**
	 * Default constructor.
	 */
	public ClientePf() {
		super();
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
	 * Get the razao_social
	 * @return <code>String</code>
	 */
	public final String getRazao_social() {
		return razao_social;
	}


	/**
	 * Set the razao_social
	 * @param razao_social the <code>String</code>
	 */
	public final void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

}
