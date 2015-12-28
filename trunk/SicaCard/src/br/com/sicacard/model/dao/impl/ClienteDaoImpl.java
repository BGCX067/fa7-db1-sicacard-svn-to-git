/**
 *
 */
package br.com.sicacard.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sicacard.model.dao.GenericDAO;
import br.com.sicacard.model.entity.Cliente;
import br.com.sicacard.model.entity.DadosBancario;
import br.com.sicacard.model.entity.Dependente;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.model.entity.exception.PersistenceException;
import br.com.sicacard.util.DAOUtil;
import br.com.sicacard.util.SQLUtil;

/**
 * ClienteDaoImpl.java
 *
 * @author Ebix
 *
 * 19/04/2012
 */
public class ClienteDaoImpl extends GenericDaoImpl<Cliente>{

	private static final Logger LOGGER = Logger.getLogger(ClienteDaoImpl.class);
	private GenericDAO<DadosBancario>  dadosBancarioDao;
	private DependenteDaoImpl dependenteDAO;


	/**
	 * Default constructor.
	 *
	 * @param jndi String
	 */
	public ClienteDaoImpl(String jndi) {
		super(jndi);
		dadosBancarioDao = new DadosBancarioDaoImpl(jndi);
		dependenteDAO = new DependenteDaoImpl(jndi);
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
		return SQLUtil.getQuery("CLIENTE_SELECT_ALL");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQuerySelect()
	 */
	@Override
	public String getQuerySelect() {
		return SQLUtil.getQuery("CLIENTE_SELECT");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryInsert()
	 */
	@Override
	public String getQueryInsert() {
		return SQLUtil.getQuery("CLIENTE_INSERT");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryUpdate()
	 */
	@Override
	public String getQueryUpdate() {
		return SQLUtil.getQuery("CLIENTE_UPDATE");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryDelete()
	 */
	@Override
	public String getQueryDelete() {
		return SQLUtil.getQuery("CLIENTE_DELETE");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectAllEntity(java.util.List, java.sql.ResultSet)
	 */
	@Override
	public void selectAllEntity(List<Cliente> lista, ResultSet rs)
			throws PersistenceException {
		try {
			Cliente cliente = new Cliente();
			cliente.setCodCliente(rs.getLong(1));
			DadosBancario dadosBancario = new DadosBancario();
			dadosBancario.setCodigo(rs.getLong(2));
			dadosBancario = dadosBancarioDao.select(dadosBancario);
			cliente.setDadosBancario(dadosBancario);
			cliente.setEndereco(rs.getString(3));
			cliente.setTelefone(rs.getString(4));
			cliente.setDependentes(dependenteDAO.selectAll(cliente));
			lista.add(cliente);
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
	public Cliente selectEntity(ResultSet rs) throws PersistenceException {
		Cliente cliente = null;
		try {
			cliente = new Cliente();
			cliente.setCodCliente(rs.getLong(1));
			DadosBancario dadosBancario = new DadosBancario();
			dadosBancario.setCodigo(rs.getLong(2));
			dadosBancario = dadosBancarioDao.select(dadosBancario);
			cliente.setDadosBancario(dadosBancario);
			cliente.setEndereco(rs.getString(3));
			cliente.setTelefone(rs.getString(4));
			cliente.setDependentes(dependenteDAO.selectAll(cliente));
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
		return cliente;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#insertEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void insertEntity(Cliente entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			entidade.setCodCliente(this.getSequenceNextValue("CLIENTE_SEQ"));
			stm.setLong(1, entidade.getCodCliente());
			dadosBancarioDao.insert(entidade.getDadosBancario(), getConn());
			stm.setLong(2, entidade.getDadosBancario().getCodigo());
			stm.setString(3, entidade.getEndereco());
			stm.setString(4, entidade.getTelefone());
			stm.executeUpdate();
			for(Dependente d: entidade.getDependentes()) {
				d.setCodResponsavel(entidade.getCodCliente());
				dependenteDAO.insert(d,getConn());
			}
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#updateEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void updateEntity(Cliente entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCodCliente());
			dadosBancarioDao.update(entidade.getDadosBancario(), this.getConn());
			stm.setLong(2, entidade.getDadosBancario().getCodigo());
			stm.setString(3, entidade.getEndereco());
			stm.setString(4, entidade.getTelefone());
			for(Dependente d: entidade.getDependentes()) {
				dependenteDAO.update(d);
			}
			stm.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#deleteEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void deleteEntity(Cliente entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCodCliente());
			dadosBancarioDao.delete(entidade.getDadosBancario(), getConn());
			dependenteDAO.deleteDependentes(getConn(), entidade);
			stm.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDaoImpl#setParametros(java.sql.PreparedStatement, br.com.sicacard.model.entity.GenericEntity)
	 */
	@Override
	public void setParametros(PreparedStatement stm, Cliente entidade)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCodCliente());
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}
	
	public Cliente select(long cnhDependente) throws BusinessException {
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		Cliente c = null;
		try {
			conn = DAOUtil.getConnection(getJndi());
			stm = conn.prepareStatement(SQLUtil.getQuery("CLIENTE_SELECT_BY_DEPENDENTE"));
			stm.setLong(1, cnhDependente);
			rs = stm.executeQuery();
			if(rs.next()){
				c =selectEntity(rs);
			}
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.closeResourses(conn, stm, rs);
			conn = null;
		}
		return c;
	}


}
