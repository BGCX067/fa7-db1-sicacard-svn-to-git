package br.com.sicacard.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Cliente.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_cliente")
	private Long codCliente;

	@Column(name="endereco")
	private String endereco;

	@Column(name="telefone")
	private String telefone;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_dados_bancario")
	private DadosBancario dadosBancario;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="cod_cliente")
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

}
