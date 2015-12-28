package br.com.sicacard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * ClientePj.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
@Entity
@Table(name="cliente_pj")
@PrimaryKeyJoinColumn(name="cod_cliente")
public class ClientePj extends Cliente {

	@Id
	@Column(name="cpf")
	private String cpf;

	@Column(name="nome")
	private String nome;

	@Column(name="numero_cnh")
	private Long numeroCNH;


	/**
	 * Default constructor.
	 */
	public ClientePj() {
		super();
	}


	/**
	 * Get the cpf
	 * @return <code>String</code>
	 */
	public final String getCpf() {
		return cpf;
	}


	/**
	 * Set the cpf
	 * @param cpf the <code>String</code>
	 */
	public final void setCpf(String cpf) {
		this.cpf = cpf;
	}


	/**
	 * Get the nome
	 * @return <code>String</code>
	 */
	public final String getNome() {
		return nome;
	}


	/**
	 * Set the nome
	 * @param nome the <code>String</code>
	 */
	public final void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * Get the numeroCNH
	 * @return <code>Long</code>
	 */
	public final Long getNumeroCNH() {
		return numeroCNH;
	}


	/**
	 * Set the numeroCNH
	 * @param numeroCNH the <code>Long</code>
	 */
	public final void setNumeroCNH(Long numeroCNH) {
		this.numeroCNH = numeroCNH;
	}

}
