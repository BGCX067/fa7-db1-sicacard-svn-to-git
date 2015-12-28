/**
 *
 */
package br.com.sicacard.business.impl;

import java.util.List;

import br.com.sicacard.business.PostoBusiness;
import br.com.sicacard.model.dao.GenericDAO;
import br.com.sicacard.model.dao.impl.PostoDaoImpl;
import br.com.sicacard.model.entity.Posto;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.util.ApplicationConstants;

/**
 * PostoBusinessImpl.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 06/05/2012
 * @version 1.0
 */
public class PostoBusinessImpl implements PostoBusiness{
	
	private GenericDAO<Posto> postoDao;
	
	

	/**
	 * Default constructor.
	 */
	public PostoBusinessImpl() {
		super();
		postoDao = new PostoDaoImpl(ApplicationConstants.JNDI);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.business.PostoBusiness#salvar(br.com.sicacard.model.entity.Posto)
	 */
	@Override
	public void salvar(Posto posto) throws BusinessException {
		postoDao.insert(posto);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.business.PostoBusiness#deletar(br.com.sicacard.model.entity.Posto)
	 */
	@Override
	public void deletar(Posto posto) throws BusinessException {
		postoDao.delete(posto);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.business.PostoBusiness#listar()
	 */
	@Override
	public List<Posto> listar() throws BusinessException {
		return postoDao.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.business.PostoBusiness#recuperar(br.com.sicacard.model.entity.Posto)
	 */
	@Override
	public Posto recuperar(Posto posto) throws BusinessException {
		return postoDao.select(posto);
	} 

}
