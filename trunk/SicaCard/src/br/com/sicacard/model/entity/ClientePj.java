package br.com.sicacard.model.entity;


/**
 * ClientePf.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
public class ClientePj extends Cliente {

	private static final long serialVersionUID = -774915745159810882L;
	private Long cnpj;
	private String nomeFantasia;
	private String razaoSocial;


	/**
	 * Default constructor.
	 */
	public ClientePj() {
		super();
	}

	/**
	 * Get the cnpj
	 * @return <code>Long</code>
	 */
	public final Long getCnpj() {
		return cnpj;
	}

	/**
	 * Set the cnpj
	 * @param cnpj the <code>Long</code>
	 */
	public final void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Get the nomeFantasia
	 * @return <code>String</code>
	 */
	public final String getNomeFantasia() {
		return nomeFantasia;
	}

	/**
	 * Set the nomeFantasia
	 * @param nomeFantasia the <code>String</code>
	 */
	public final void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	/**
	 * Get the razao_social
	 * @return <code>String</code>
	 */
	public final String getRazaoSocial() {
		return razaoSocial;
	}

	/**
	 * Set the razao_social
	 * @param razaoSocial the <code>String</code>
	 */
	public final void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

}
