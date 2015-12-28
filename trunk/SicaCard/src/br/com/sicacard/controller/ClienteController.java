/**
 *
 */
package br.com.sicacard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.sicacard.business.ClienteBusiness;
import br.com.sicacard.business.impl.ClienteBusinessImpl;
import br.com.sicacard.model.entity.ClientePf;
import br.com.sicacard.model.entity.ClientePj;
import br.com.sicacard.model.entity.DadosBancario;
import br.com.sicacard.model.entity.Dependente;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.util.ApplicationConstants;

/**
 * ClienteController.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 06/05/2012
 * @version 1.0
 */
@ManagedBean(name = "clienteController")
@ViewScoped
public class ClienteController extends GenericController {

	private static final long serialVersionUID = 5430508218669617224L;
	private static final Logger LOGGER = Logger.getLogger(ClienteController.class);
	private ClienteBusiness business;
	private ClientePf clientePf;
	private ClientePj clientePj;
	private Dependente dependente;
	private DadosBancario dadosBancario;
	private boolean flgPessoaFisica;
	
	
	/**
	 * Default constructor.
	 */
	public ClienteController() {
		super();
		business = new ClienteBusinessImpl();
		flgPessoaFisica = true;
	}
	
	/**
	 * Troca o tipo de pessoa.
	 */
	public void trocaTipoPessoa(){
		flgPessoaFisica = !flgPessoaFisica;
	}
	
	/**
	 * Método responsável inicializar o objeto.
	 */
	private void initClientePf(){
		clientePf = new ClientePf();
		clientePf.setDadosBancario(new DadosBancario());
		clientePf.setDependentes(new ArrayList<Dependente>());
	}
	
	/**
	 * Método responsável inicializar o objeto.
	 */
	private void initClientePJ(){
		clientePj = new ClientePj();
		clientePj.setDadosBancario(new DadosBancario());
		clientePj.setDependentes(new ArrayList<Dependente>());
	}

	/**
	 * Get the clientePf
	 * @return <code>ClientePf</code>
	 */
	public ClientePf getClientePf() {
		if(clientePf == null) {
			initClientePf();
		}
		return clientePf;
	}

	/**
	 * Set the clientePf
	 * @param clientePf the <code>ClientePf</code>
	 */
	public void setClientePf(ClientePf clientePf) {
		this.clientePf = clientePf;
	}

	/**
	 * Get the clientePj
	 * @return <code>ClientePj</code>
	 */
	public ClientePj getClientePj() {
		if(clientePj == null) {
			initClientePJ();
		}
		return clientePj;
	}

	/**
	 * Set the clientePj
	 * @param clientePj the <code>ClientePj</code>
	 */
	public void setClientePj(ClientePj clientePj) {
		this.clientePj = clientePj;
	}

	/**
	 * Get the dependente
	 * @return <code>Dependente</code>
	 */
	public Dependente getDependente() {
		if(dependente == null) {
			dependente = new Dependente();
		}
		return dependente;
	}

	/**
	 * Set the dependente
	 * @param dependente the <code>Dependente</code>
	 */
	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

	/**
	 * Get the flgPessoaFisica
	 * @return <code>boolean</code>
	 */
	public boolean isFlgPessoaFisica() {
		return flgPessoaFisica;
	}

	/**
	 * Set the flgPessoaFisica
	 * @param flgPessoaFisica the <code>boolean</code>
	 */
	public void setFlgPessoaFisica(boolean flgPessoaFisica) {
		this.flgPessoaFisica = flgPessoaFisica;
	}
	
	/**
	 * Método respensável por adicionar um dependente.
	 */
	public void addDependente() {
		if(flgPessoaFisica) {
			clientePf.getDependentes().add(dependente);
		} else {
			clientePj.getDependentes().add(dependente);
		}
		dependente = new Dependente();
	}
	
	/**
	 * Método responsável por remover um dependente.
	 * 
	 * @param dependente Dependente
	 */
	public void removeDependente(Dependente dependente) {
		if(flgPessoaFisica) {
			clientePf.getDependentes().remove(dependente);
		} else {
			clientePj.getDependentes().remove(dependente);
		}
	}
	
	/**
	 * Método responsável por salvar um cliente.
	 * 
	 * @return navegação
	 */
	public String salvarCliente() {
		try {
			if(flgPessoaFisica) {
				clientePf.setDadosBancario(dadosBancario);
				business.salvar(clientePf);
			} else {
				clientePj.setDadosBancario(dadosBancario);
				business.salvar(clientePj);
			}
			addInfoMessage("Cliente gravado com sucesso");
			return ApplicationConstants.SUCCESS;
		} catch (Exception e) {
			LOGGER.error(e);
			addErrorMessage("Falha ao salvar o cliente");
			return ApplicationConstants.ERROR;
		}
	}

	/**
	 * Get the dadosBancario
	 * @return <code>DadosBancario</code>
	 */
	public DadosBancario getDadosBancario() {
		if(dadosBancario == null) {
			dadosBancario = new DadosBancario();
		}
		return dadosBancario;
	}

	/**
	 * Set the dadosBancario
	 * @param dadosBancario the <code>DadosBancario</code>
	 */
	public void setDadosBancario(DadosBancario dadosBancario) {
		this.dadosBancario = dadosBancario;
	}
	
	/**
	 * Método responsável por listar os dependentes.
	 * 
	 * @return List<Dependente>
	 */
	public List<Dependente> getListaDependentes() {
		if(flgPessoaFisica) {
			return clientePf.getDependentes();
		} else {
			return clientePj.getDependentes();
		}
	}
	
	/**
	 * Método responsável por obter a lista de clientes.
	 * 
	 * @return List<ClientePf>
	 */
	public List<ClientePf> getListaClientePf() {
		try {
			return business.listarClientePf();
		} catch (BusinessException e) {
			addErrorMessage("Falha ao recuperar a lista de clientes");
			LOGGER.error(e);
			return null;
		}
	}
	
	/**
	 * Método responsável por obter a lista de clientes.
	 * 
	 * @return List<ClientePj>
	 */
	public List<ClientePj> getListaClientePj() {
		try {
			return business.listarClientePJ();
		} catch (BusinessException e) {
			addErrorMessage("Falha ao recuperar a lista de clientes");
			LOGGER.error(e);
			return null;
		}
	}
	

}
