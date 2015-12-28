package br.com.sicacard.model.entity;


/**
 * DadosBancario.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
public class DadosBancario implements GenericEntity{


	private static final long serialVersionUID = 3963504017978651483L;
	private Long codigo;
	private String banco;
	private String agencia;
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
