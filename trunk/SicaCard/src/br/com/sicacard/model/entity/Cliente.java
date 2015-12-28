package br.com.sicacard.model.entity;

import java.util.List;

/**
 * Cliente.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
public class Cliente implements GenericEntity{

	private static final long serialVersionUID = -2908915722311271177L;
	private Long codCliente;
	private String endereco;
	private String telefone;
	private DadosBancario dadosBancario;
	private List<Dependente> dependentes;


	/**
	 * Default constructor.
	 */
	public Cliente() {
		super();
	}

	/**
	 * Get the codCliente
	 * @return <code>Long</code>
	 */
	public final Long getCodCliente() {
		return codCliente;
	}

	/**
	 * Set the codCliente
	 * @param codCliente the <code>Long</code>
	 */
	public final void setCodCliente(Long codCliente) {
		this.codCliente = codCliente;
	}

	/**
	 * Get the endereco
	 * @return <code>String</code>
	 */
	public final String getEndereco() {
		return endereco;
	}

	/**
	 * Set the endereco
	 * @param endereco the <code>String</code>
	 */
	public final void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * Get the telefone
	 * @return <code>String</code>
	 */
	public final String getTelefone() {
		return telefone;
	}

	/**
	 * Set the telefone
	 * @param telefone the <code>String</code>
	 */
	public final void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Get the dadosBancario
	 * @return <code>DadosBancario</code>
	 */
	public final DadosBancario getDadosBancario() {
		return dadosBancario;
	}

	/**
	 * Set the dadosBancario
	 * @param dadosBancario the <code>DadosBancario</code>
	 */
	public final void setDadosBancario(DadosBancario dadosBancario) {
		this.dadosBancario = dadosBancario;
	}

	/**
	 * Get the dependentes
	 * @return <code>List<Dependente></code>
	 */
	public final List<Dependente> getDependentes() {
		return dependentes;
	}

	/**
	 * Set the dependentes
	 * @param dependentes the <code>List<Dependente></code>
	 */
	public final void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}
	
	/**
	 * Método responsável por clonar os dados de um cliente
	 * 
	 * @param cliente {@link Cliente}
	 */
	public void clonar(Cliente cliente) {
		this.codCliente = cliente.getCodCliente();
		this.dadosBancario = cliente.getDadosBancario();
		this.dependentes = cliente.getDependentes();
		this.endereco = cliente.getEndereco();
		this.telefone = cliente.getTelefone();
	}

}
