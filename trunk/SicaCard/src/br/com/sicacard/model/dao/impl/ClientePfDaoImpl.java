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
import br.com.sicacard.model.entity.ClientePf;
import br.com.sicacard.model.entity.exception.PersistenceException;
import br.com.sicacard.util.DAOUtil;
import br.com.sicacard.util.SQLUtil;

/**
 * ClienteDaoPfImpl.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 22/04/2012
 * @version 1.0
 */
public class ClientePfDaoImpl extends GenericDaoImpl<ClientePf> {

	private static final Logger LOGGER = Logger.getLogger(ClientePfDaoImpl.class);
	private GenericDAO<Cliente> clienteDAO ;


	/**
	 * Default constructor.
	 *
	 * @param jndi String
	 */
	public ClientePfDaoImpl(String jndi) {
		super(jndi);
		clienteDAO = new ClienteDaoImpl(jndi);
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getLogger()
	 */
	@Override
	public Logger getLogger() {
		return LOGGER;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQuerySelectAll()
	 */
	@Override
	public String getQuerySelectAll() {
		return SQLUtil.getQuery("CLIENTE_PF_SELECT_ALL");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQuerySelect()
	 */
	@Override
	public String getQuerySelect() {
		return SQLUtil.getQuery("CLIENTE_PF_SELECT");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryInsert()
	 */
	@Override
	public String getQueryInsert() {
		return SQLUtil.getQuery("CLIENTE_PF_INSERT");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryUpdate()
	 */
	@Override
	public String getQueryUpdate() {
		return SQLUtil.getQuery("CLIENTE_PF_UPDATE");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryDelete()
	 */
	@Override
	public String getQueryDelete() {
		return SQLUtil.getQuery("CLIENTE_PF_DELETE");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectAllEntity(java.util.List, java.sql.ResultSet)
	 */
	@Override
	public void selectAllEntity(List<ClientePf> lista, ResultSet rs)
			throws PersistenceException {
		try {
			ClientePf clientePf = new ClientePf();
			clientePf.setCpf(Long.valueOf(rs.getString("CPF")));
			clientePf.setCodCliente(rs.getLong("COD_CLIENTE"));
		 	Cliente c = clienteDAO.select(clientePf);
		 	clientePf.clonar(c);
		 	clientePf.setNome(rs.getString("NOME"));
		 	clientePf.setNumeroCNH(rs.getLong("NUM_CNH"));
			lista.add(clientePf);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectEntity(java.sql.ResultSet)
	 */
	@Override
	public ClientePf selectEntity(ResultSet rs) throws PersistenceException {
		ClientePf clientePf = null;
		try {
			clientePf = new ClientePf();
			clientePf.setCpf(Long.valueOf(rs.getString("CPF")));
			clientePf.setCodCliente(rs.getLong("COD_CLIENTE"));
		 	Cliente c = clienteDAO.select(clientePf);
		 	clientePf.clonar(c);
		 	clientePf.setNome(rs.getString("NOME"));
		 	clientePf.setNumeroCNH(rs.getLong("NUM_CNH"));
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
		return clientePf;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#insertEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void insertEntity(ClientePf entidade, PreparedStatement stm)
			throws PersistenceException {
			try {
				DAOUtil.beginTransaction(getConn());
				stm.setLong(1, entidade.getCpf());
				clienteDAO.insert(entidade, getConn());
				stm.setLong(2, entidade.getCodCliente());
				stm.setString(3, entidade.getNome());
				stm.setLong(4, entidade.getNumeroCNH());
				stm.executeUpdate();
				DAOUtil.commitTransaction(getConn());
			} catch (Exception e) {
				LOGGER.error(e);
				DAOUtil.rollbackTransaction(getConn());
				throw new PersistenceException(e);
			}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#updateEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void updateEntity(ClientePf entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			DAOUtil.beginTransaction(getConn());
			stm.setLong(1, entidade.getCpf());
			clienteDAO.update(entidade, getConn());
			stm.setLong(2, entidade.getCodCliente());
			stm.setString(3, entidade.getNome());
			stm.setLong(4, entidade.getNumeroCNH());
			stm.executeUpdate();
			DAOUtil.commitTransaction(getConn());
		} catch (Exception e) {
			LOGGER.error(e);
			DAOUtil.rollbackTransaction(getConn());
			throw new PersistenceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#deleteEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void deleteEntity(ClientePf entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			DAOUtil.beginTransaction(getConn());
			stm.setLong(1, entidade.getCpf());
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
	public void setParametros(PreparedStatement stm, ClientePf entidade)
			throws PersistenceException {
		try {
			stm.setLong(1, Long.valueOf(entidade.getCpf()));
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

}
