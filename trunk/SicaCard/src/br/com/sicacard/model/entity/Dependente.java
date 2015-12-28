package br.com.sicacard.model.entity;


/**
 * Dependente.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
public class Dependente implements GenericEntity {

	private static final long serialVersionUID = -1251289852892842562L;
	private Long cnh;
	private Long codResponsavel;
	private String nome;


	/**
	 * Default constructor.
	 */
	public Dependente() {
		super();
	}

	/**
	 * Get the cnh.
	 * @return <code>Long</code>
	 */
	public Long getCnh() {
		return cnh;
	}

	/**
	 * Set the cnh.
	 * @param cnh the <code>Long</code>
	 */
	public void setCnh(Long cnh) {
		this.cnh = cnh;
	}

	/**
	 * Get the codResponsavel.
	 * @return <code>Long</code>
	 */
	public Long getCodResponsavel() {
		return codResponsavel;
	}

	/**
	 * Set the codResponsavel.
	 * @param codResponsavel the <code>Long</code>
	 */
	public void setCodResponsavel(Long codResponsavel) {
		this.codResponsavel = codResponsavel;
	}

	/**
	 * Get the nome.
	 * @return <code>String</code>
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Set the nome.
	 * @param nome the <code>String</code>
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
