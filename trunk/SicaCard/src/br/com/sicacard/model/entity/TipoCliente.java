package br.com.sicacard.model.entity;

/**
 * TipoCliente.java
 *
 * @author Ebix
 *
 * 27/04/2012
 */
public enum TipoCliente {

	/** Cliente pessoa fisica. */
	CLIENTE_PF(1,"CLIENTE_PF"),
	/** Cliente pessoa juridica*/
	CLIENTE_PJ(2, "CLIENTE_PJ"),
	/** Dependente*/
	DEPENDENTE(3, "DEPENDENTE");

	private int codigo;
	private String descricao;

	/**
	 * Default constructor.
	 */
	private TipoCliente(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}


	/**
	 * Get the codigo.
	 * @return <code>int</code>
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Set the codigo.
	 * @param codigo the <code>int</code>
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Get the descricao.
	 * @return <code>String</code>
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Set the descricao.
	 * @param descricao the <code>String</code>
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Método responsável por obter um {@link TipoCliente} a partir de um código.
	 *
	 * @param codigo int
	 * @return TipoCliente
	 */
	public static TipoCliente valueOf(int codigo) {
		TipoCliente tipos[] = values();
		if(tipos.length > codigo -1) {
			return tipos[codigo-1];
		}
		return null;
	}

}
