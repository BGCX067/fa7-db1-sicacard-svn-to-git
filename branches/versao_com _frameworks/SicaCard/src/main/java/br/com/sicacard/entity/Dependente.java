package br.com.sicacard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dependente.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
@Entity
@Table(name="dependente")
public class Dependente {

	@Id
	@Column(name="cnh")
	private Long cnh;

	@Column(name="nome")
	private String nome;


	/**
	 * Default constructor.
	 */
	public Dependente() {
		super();
	}


	/**
	 * Get the cnh
	 * @return <code>Long</code>
	 */
	public final Long getCnh() {
		return cnh;
	}


	/**
	 * Set the cnh
	 * @param cnh the <code>Long</code>
	 */
	public final void setCnh(Long cnh) {
		this.cnh = cnh;
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

}
