package br.com.sicacard.model.entity;


/**
 * HistoricoAbastecimento.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
public class HistoricoAbastecimento implements GenericEntity{

	private static final long serialVersionUID = 6100444149922698498L;
	private Posto posto;
	private Cliente cliente;
	private Double valor;
	private long numeroDocumento;
	private TipoCliente tipoCliente;


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

	/**
	 * Get the numeroDocumento.
	 * @return <code>long</code>
	 */
	public long getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * Set the numeroDocumento.
	 * @param numeroDocumento the <code>long</code>
	 */
	public void setNumeroDocumento(long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * Get the tipoCliente.
	 * @return <code>TipoCliente</code>
	 */
	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * Set the tipoCliente.
	 * @param tipoCliente the <code>TipoCliente</code>
	 */
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

}
