package br.com.sicacard.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import br.com.sicacard.business.AbastecimentoBusiness;
import br.com.sicacard.business.ClienteBusiness;
import br.com.sicacard.business.PostoBusiness;
import br.com.sicacard.business.impl.AbastecimentoBusinessImpl;
import br.com.sicacard.business.impl.ClienteBusinessImpl;
import br.com.sicacard.business.impl.PostoBusinessImpl;
import br.com.sicacard.model.entity.Cliente;
import br.com.sicacard.model.entity.HistoricoAbastecimento;
import br.com.sicacard.model.entity.Posto;
import br.com.sicacard.model.entity.TipoCliente;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.util.ApplicationConstants;

/**
 * 
 * AbastecimentoController.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 06/05/2012
 * @version 1.0
 */
@ManagedBean(name = "abastecimentoController")
@ViewScoped
public class AbastecimentoController extends GenericController {

	private static final long serialVersionUID = 3765512612527298196L;
	private static final Logger LOGGER = Logger.getLogger(AbastecimentoController.class);
	private AbastecimentoBusiness business;
	private PostoBusiness postoBusiness;
	private ClienteBusiness clienteBusiness;
	private HistoricoAbastecimento historico;
	private int tipoCliente = 1;
	
	
	
	
	/**
	 * Default constructor.
	 */
	public AbastecimentoController() {
		super();
		business = new AbastecimentoBusinessImpl();
		postoBusiness = new PostoBusinessImpl();
		clienteBusiness = new ClienteBusinessImpl();
	}

	/**
	 * Get the historico
	 * @return <code>HistoricoAbastecimento</code>
	 */
	public HistoricoAbastecimento getHistorico() {
		if(historico == null) {
			historico = new HistoricoAbastecimento();
			historico.setCliente(new Cliente());
			historico.setPosto(new Posto());
		}
		return historico;
	}
	
	/**
	 * Set the historico
	 * @param historico the <code>HistoricoAbastecimento</code>
	 */
	public void setHistorico(HistoricoAbastecimento historico) {
		this.historico = historico;
	}
	
	/**
	 * Get the tipoCliente
	 * @return <code>int</code>
	 */
	public int getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * Set the tipoCliente
	 * @param tipoCliente the <code>int</code>
	 */
	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String registar() {
		try {
			historico.setTipoCliente(TipoCliente.valueOf(tipoCliente));
			historico.setCliente(clienteBusiness.recuperarCliente(Long.valueOf(historico.getNumeroDocumento()), TipoCliente.valueOf(tipoCliente)));
			business.realizarAbastecimento(historico);
			addInfoMessage("Abastecimento registrado com sucesso");
			return ApplicationConstants.SUCCESS;
		} catch (Exception e) {
			LOGGER.error(e);
			addErrorMessage("Falha ao registrar o abastecimento");
			return ApplicationConstants.ERROR;
		}
	}
	
	public List<HistoricoAbastecimento> getLista() {
		try {
			return business.listarAbastecimentos();
		} catch (Exception e) {
			LOGGER.error(e);
			addErrorMessage("Falha ao listar os abastecimentos");
			return null;
		}
	}
	
	public List<SelectItem> getPostos() {  
	    List<SelectItem> items = new ArrayList<SelectItem>(); 
	    try {
			List<Posto> postos = postoBusiness.listar();
			for (Posto p : postos) {  
			    items.add(new SelectItem(p.getCnpj(), p.getNomeFantasia()));  
			}
		} catch (BusinessException e) {
			LOGGER.error(e);
		}  
	    return items;  
	} 

}
