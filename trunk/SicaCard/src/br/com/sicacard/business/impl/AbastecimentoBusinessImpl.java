/**
 *
 */
package br.com.sicacard.business.impl;

import java.util.List;

import br.com.sicacard.business.AbastecimentoBusiness;
import br.com.sicacard.model.dao.GenericDAO;
import br.com.sicacard.model.dao.impl.HistoricoAbastecimentoDaoImpl;
import br.com.sicacard.model.entity.HistoricoAbastecimento;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.util.ApplicationConstants;

/**
 * AbastecimentoBusinessImpl.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 06/05/2012
 * @version 1.0
 */
public class AbastecimentoBusinessImpl implements AbastecimentoBusiness {
	
	private GenericDAO<HistoricoAbastecimento> historicoDao;
	
	

	/**
	 * Default constructor.
	 */
	public AbastecimentoBusinessImpl() {
		super();
		historicoDao = new HistoricoAbastecimentoDaoImpl(ApplicationConstants.JNDI);
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.business.AbastecimentoBusiness#realizarAbastecimento(br.com.sicacard.model.entity.HistoricoAbastecimento)
	 */
	@Override
	public void realizarAbastecimento(
			HistoricoAbastecimento historicoAbastecimento)
			throws BusinessException {
		historicoDao.insert(historicoAbastecimento);

	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.business.AbastecimentoBusiness#listarAbastecimentos()
	 */
	@Override
	public List<HistoricoAbastecimento> listarAbastecimentos()
			throws BusinessException {
		return historicoDao.selectAll();
	}

}
