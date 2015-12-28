/**
 *
 */
package br.com.sicacard.business.impl;

import java.util.List;

import br.com.sicacard.business.ClienteBusiness;
import br.com.sicacard.model.dao.GenericDAO;
import br.com.sicacard.model.dao.impl.ClienteDaoImpl;
import br.com.sicacard.model.dao.impl.ClientePfDaoImpl;
import br.com.sicacard.model.dao.impl.ClientePjDaoImpl;
import br.com.sicacard.model.entity.Cliente;
import br.com.sicacard.model.entity.ClientePf;
import br.com.sicacard.model.entity.ClientePj;
import br.com.sicacard.model.entity.TipoCliente;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.util.ApplicationConstants;

/**
 * ClienteBusinessImpl.java
 * 
 * @author Nayalison <br />
 *         nayalison@gmail.com
 * 
 * @since 06/05/2012
 * @version 1.0
 */
public class ClienteBusinessImpl implements ClienteBusiness {

	private GenericDAO<ClientePf> clientePfDao;
	private GenericDAO<ClientePj> clientePjDao;
	private ClienteDaoImpl clienteDao;

	/**
	 * Default constructor.
	 */
	public ClienteBusinessImpl() {
		super();
		clientePfDao = new ClientePfDaoImpl(ApplicationConstants.JNDI);
		clientePjDao = new ClientePjDaoImpl(ApplicationConstants.JNDI);
		clienteDao = new ClienteDaoImpl(ApplicationConstants.JNDI);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sicacard.business.ClienteBusiness#salvar(br.com.sicacard.model
	 * .entity.ClientePj)
	 */
	@Override
	public void salvar(ClientePj clientePj) throws BusinessException {
		clientePjDao.insert(clientePj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sicacard.business.ClienteBusiness#deletar(br.com.sicacard.model
	 * .entity.ClientePj)
	 */
	@Override
	public void deletar(ClientePj clientePj) throws BusinessException {
		clientePjDao.delete(clientePj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sicacard.business.ClienteBusiness#listarClientePJ()
	 */
	@Override
	public List<ClientePj> listarClientePJ() throws BusinessException {
		return clientePjDao.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sicacard.business.ClienteBusiness#recuperar(br.com.sicacard.model
	 * .entity.ClientePj)
	 */
	@Override
	public ClientePj recuperar(ClientePj clientePj) throws BusinessException {
		return clientePjDao.select(clientePj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sicacard.business.ClienteBusiness#salvar(br.com.sicacard.model
	 * .entity.ClientePf)
	 */
	@Override
	public void salvar(ClientePf clientePf) throws BusinessException {
		clientePfDao.insert(clientePf);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sicacard.business.ClienteBusiness#deletar(br.com.sicacard.model
	 * .entity.ClientePf)
	 */
	@Override
	public void deletar(ClientePf clientePf) throws BusinessException {
		clientePfDao.delete(clientePf);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.sicacard.business.ClienteBusiness#listarClientePf()
	 */
	@Override
	public List<ClientePf> listarClientePf() throws BusinessException {
		return clientePfDao.selectAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.sicacard.business.ClienteBusiness#recuperar(br.com.sicacard.model
	 * .entity.ClientePf)
	 */
	@Override
	public ClientePf recuperar(ClientePf clientePf) throws BusinessException {
		return clientePfDao.select(clientePf);
	}

	@Override
	public Cliente recuperarCliente(long numDocumento, TipoCliente tipoCliente)
			throws BusinessException {
		Cliente cliente = null;
		switch (tipoCliente.getCodigo()) {
		case 1: {
			ClientePf cliPF = new ClientePf();
			cliPF.setCpf(numDocumento);
			cliente = clientePfDao.select(cliPF);
		}
			break;
		case 2: {
			ClientePj cliPJ = new ClientePj();
			cliPJ.setCnpj(numDocumento);
			cliente = clientePjDao.select(cliPJ);
		}
			break;
		case 3: {
			cliente = clienteDao.select(numDocumento);
		}
			break;
		}
		return cliente;
	}

}
