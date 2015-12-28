package br.com.sicacard.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * HistoricoAbastecimento.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
@Entity
@Table(name="historico_abastecimento")
public class HistoricoAbastecimento {

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cnpj_posto")
	private Posto posto;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_cliente")
	private Cliente cliente;

	@Column(name="valor")
	private Double valor;


	/**
	 * Default constructor.
	 */
	public HistoricoAbastecimento() {
		super();
	}


	/**
	 * Get the posto
	 * @return <code>Posto</code>
	 */
	public final Posto getPosto() {
		return posto;
	}


	/**
	 * Set the posto
	 * @param posto the <code>Posto</code>
	 */
	public final void setPosto(Posto posto) {
		this.posto = posto;
	}


	/**
	 * Get the cliente
	 * @return <code>Cliente</code>
	 */
	public final Cliente getCliente() {
		return cliente;
	}


	/**
	 * Set the cliente
	 * @param cliente the <code>Cliente</code>
	 */
	public final void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	/**
	 * Get the valor
	 * @return <code>Double</code>
	 */
	public final Double getValor() {
		return valor;
	}


	/**
	 * Set the valor
	 * @param valor the <code>Double</code>
	 */
	public final void setValor(Double valor) {
		this.valor = valor;
	}

}
