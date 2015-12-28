package br.com.sicacard.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import br.com.sicacard.business.PostoBusiness;
import br.com.sicacard.business.impl.PostoBusinessImpl;
import br.com.sicacard.model.entity.DadosBancario;
import br.com.sicacard.model.entity.Posto;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.util.ApplicationConstants;

@ManagedBean(name = "postoController")
@ViewScoped
public class PostoController extends GenericController{
	
	private static final long serialVersionUID = 5899677146622442746L;
	private static final Logger LOGGER = Logger.getLogger(PostoController.class);
	private Posto posto;
	private PostoBusiness business;
	

	/**
	 * Default constructor.
	 */
	public PostoController() {
		super();
		business = new PostoBusinessImpl();
	}

	/**
	 * Get the posto
	 * @return <code>Posto</code>
	 */
	public Posto getPosto() {
		if(posto == null) {
			posto = new Posto();
			posto.setDadosBancario(new DadosBancario());
		}
		return posto;
	}

	/**
	 * Set the posto
	 * @param posto the <code>Posto</code>
	 */
	public void setPosto(Posto posto) {
		this.posto = posto;
	}
	
	/**
	 * Método responsável por salvar um posto.
	 * 
	 * @return navegação
	 */
	public String salvar() {
		try {
			business.salvar(posto);
			addInfoMessage("Posto gravado com sucesso");
			return ApplicationConstants.SUCCESS;
		} catch (Exception e) {
			LOGGER.error(e);
			addErrorMessage("Falha ao salvar o posto");
			return ApplicationConstants.ERROR;
		}
	}
	
	/**
	 * Método responsável por listas os postos.
	 * 
	 * @return List<Posto>
	 */
	public List<Posto> getLista() {
		try {
			return business.listar();
		} catch (BusinessException e) {
			addErrorMessage("Falha ao recuperar a lista de posto");
			LOGGER.error(e);
			return null;
		}
	}

}
