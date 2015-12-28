/**
 *
 */
package br.com.sicacard.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sicacard.model.dao.GenericDAO;
import br.com.sicacard.model.entity.Cliente;
import br.com.sicacard.model.entity.ClientePj;
import br.com.sicacard.model.entity.exception.PersistenceException;
import br.com.sicacard.util.DAOUtil;
import br.com.sicacard.util.SQLUtil;

/**
 * ClienteDaoPjImpl.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 22/04/2012
 * @version 1.0
 */
public class ClientePjDaoImpl extends GenericDaoImpl<ClientePj> {

	private static final Logger LOGGER = Logger.getLogger(ClientePjDaoImpl.class);
	private GenericDAO<Cliente> clienteDAO;


	/**
	 * Default constructor.
	 *
	 * @param jndi String
	 */
	public ClientePjDaoImpl(String jndi) {
		super(jndi);
		clienteDAO = new ClienteDaoImpl(jndi);
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getLogger()
	 */
	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQuerySelectAll()
	 */
	@Override
	public String getQuerySelectAll() {
		return SQLUtil.getQuery("CLIENTE_PJ_SELECT_ALL");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQuerySelect()
	 */
	@Override
	public String getQuerySelect() {
		return SQLUtil.getQuery("CLIENTE_PJ_SELECT");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryInsert()
	 */
	@Override
	public String getQueryInsert() {
		return SQLUtil.getQuery("CLIENTE_PJ_INSERT");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryUpdate()
	 */
	@Override
	public String getQueryUpdate() {
		return SQLUtil.getQuery("CLIENTE_PJ_UPDATE");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryDelete()
	 */
	@Override
	public String getQueryDelete() {
		return SQLUtil.getQuery("CLIENTE_PJ_DELETE");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectAllEntity(java.util.List, java.sql.ResultSet)
	 */
	@Override
	public void selectAllEntity(List<ClientePj> lista, ResultSet rs)
			throws PersistenceException {
		try {
			ClientePj clientePj = new ClientePj();
			clientePj.setCnpj(Long.valueOf(rs.getString("CNPJ")));
			clientePj.setCodCliente(rs.getLong("COD_CLIENTE"));
		 	Cliente c = clienteDAO.select(clientePj);
		 	clientePj.clonar(c);
		 	clientePj.setNomeFantasia(rs.getString("NOME_FANTASIA"));
		 	clientePj.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
			lista.add(clientePj);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectEntity(java.sql.ResultSet)
	 */
	@Override
	public ClientePj selectEntity(ResultSet rs) throws PersistenceException {
		ClientePj clientePj = null;
		try {
			clientePj = new ClientePj();
			clientePj.setCnpj(Long.valueOf(rs.getString("CNPJ")));
			clientePj.setCodCliente(rs.getLong("COD_CLIENTE"));
		 	Cliente c = clienteDAO.select(clientePj);
		 	clientePj.clonar(c);
		 	clientePj.setNomeFantasia(rs.getString("NOME_FANTASIA"));
		 	clientePj.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
		return clientePj;
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#insertEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void insertEntity(ClientePj entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			DAOUtil.beginTransaction(getConn());
			stm.setLong(1, entidade.getCnpj());
			clienteDAO.insert(entidade, getConn());
			stm.setLong(2, entidade.getCodCliente());
			stm.setString(3, entidade.getNomeFantasia());
			stm.setString(4, entidade.getRazaoSocial());
			stm.executeUpdate();
			DAOUtil.commitTransaction(getConn());
		} catch (Exception e) {
			LOGGER.error(e);
			DAOUtil.rollbackTransaction(getConn());
			throw new PersistenceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#updateEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void updateEntity(ClientePj entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			DAOUtil.beginTransaction(getConn());
			stm.setLong(1, entidade.getCnpj());
			clienteDAO.insert(entidade, getConn());
			stm.setLong(2, entidade.getCodCliente());
			stm.setString(3, entidade.getNomeFantasia());
			stm.setString(4, entidade.getRazaoSocial());
			stm.executeUpdate();
			DAOUtil.commitTransaction(getConn());
		} catch (Exception e) {
			LOGGER.error(e);
			DAOUtil.rollbackTransaction(getConn());
			throw new PersistenceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#deleteEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void deleteEntity(ClientePj entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			DAOUtil.beginTransaction(getConn());
			stm.setLong(1, entidade.getCnpj());
			clienteDAO.delete(entidade, getConn());
			stm.executeUpdate();
			DAOUtil.commitTransaction(getConn());
		} catch (Exception e) {
			LOGGER.error(e);
			DAOUtil.rollbackTransaction(getConn());
			throw new PersistenceException(e);
		}
	}

	@Override
	public void setParametros(PreparedStatement stm, ClientePj entidade)
			throws PersistenceException {
		try {
			stm.setLong(1, Long.valueOf(entidade.getCnpj()));
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

}
