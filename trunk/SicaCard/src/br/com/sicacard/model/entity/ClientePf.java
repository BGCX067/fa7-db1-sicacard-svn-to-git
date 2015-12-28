package br.com.sicacard.model.entity;


/**
 * ClientePj.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
public class ClientePf extends Cliente {

	private static final long serialVersionUID = -1458613399344374737L;
	private Long cpf;
	private String nome;
	private Long numeroCNH;


	/**
	 * Default constructor.
	 */
	public ClientePf() {
		super();
	}

	/**
	 * Get the cpf
	 * @return <code>Long</code>
	 */
	public final Long getCpf() {
		return cpf;
	}

	/**
	 * Set the cpf
	 * @param cpf the <code>Long</code>
	 */
	public final void setCpf(Long cpf) {
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
