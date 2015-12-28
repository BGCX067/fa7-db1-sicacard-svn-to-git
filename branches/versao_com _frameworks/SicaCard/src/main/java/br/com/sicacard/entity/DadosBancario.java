package br.com.sicacard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DadosBancario.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
@Entity
@Table(name="dados_bancario")
public class DadosBancario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_dados_bancario")
	private Long codigo;

	@Column(name="banco")
	private String banco;

	@Column(name="agencia")
	private String agencia;

	@Column(name="conta")
	private String conta;


	/**
	 * Default constructor.
	 */
	public DadosBancario() {
		super();
	}


	/**
	 * Get the codigo
	 * @return <code>Long</code>
	 */
	public final Long getCodigo() {
		return codigo;
	}


	/**
	 * Set the codigo
	 * @param codigo the <code>Long</code>
	 */
	public final void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	/**
	 * Get the banco
	 * @return <code>String</code>
	 */
	public final String getBanco() {
		return banco;
	}


	/**
	 * Set the banco
	 * @param banco the <code>String</code>
	 */
	public final void setBanco(String banco) {
		this.banco = banco;
	}


	/**
	 * Get the agencia
	 * @return <code>String</code>
	 */
	public final String getAgencia() {
		return agencia;
	}


	/**
	 * Set the agencia
	 * @param agencia the <code>String</code>
	 */
	public final void setAgencia(String agencia) {
		this.agencia = agencia;
	}


	/**
	 * Get the conta
	 * @return <code>String</code>
	 */
	public final String getConta() {
		return conta;
	}


	/**
	 * Set the conta
	 * @param conta the <code>String</code>
	 */
	public final void setConta(String conta) {
		this.conta = conta;
	}

}
