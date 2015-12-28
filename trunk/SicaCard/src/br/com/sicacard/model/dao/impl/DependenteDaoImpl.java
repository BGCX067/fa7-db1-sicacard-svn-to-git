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

import br.com.sicacard.model.entity.Cliente;
import br.com.sicacard.model.entity.Dependente;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.model.entity.exception.PersistenceException;
import br.com.sicacard.util.DAOUtil;
import br.com.sicacard.util.SQLUtil;

/**
 * DependenteDaoImpl.java
 *
 * @author Ebix
 *
 * 19/04/2012
 */
public class DependenteDaoImpl extends GenericDaoImpl<Dependente> {

	private static final Logger LOGGER = Logger.getLogger(DependenteDaoImpl.class);


	/**
	 * Default constructor.
	 *
	 * @param jndi String
	 */
	public DependenteDaoImpl(String jndi) {
		super(jndi);
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
		return SQLUtil.getQuery("DEPENDENTE_SELECT_ALL");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQuerySelect()
	 */
	@Override
	public String getQuerySelect() {
		return SQLUtil.getQuery("DEPENDENTE_SELECT");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryInsert()
	 */
	@Override
	public String getQueryInsert() {
		return SQLUtil.getQuery("DEPENDENTE_INSERT");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryUpdate()
	 */
	@Override
	public String getQueryUpdate() {
		return SQLUtil.getQuery("DEPENDENTE_UPDATE");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryDelete()
	 */
	@Override
	public String getQueryDelete() {
		return SQLUtil.getQuery("DEPENDENTE_DELETE");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectAllEntity(java.util.List, java.sql.ResultSet)
	 */
	@Override
	public void selectAllEntity(List<Dependente> lista, ResultSet rs)
			throws PersistenceException {
		try {
			Dependente dependente = new Dependente();
			dependente.setCnh(rs.getLong(1));
			dependente.setCodResponsavel(rs.getLong(2));
			dependente.setNome(rs.getString(3));
			lista.add(dependente);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectEntity(java.sql.ResultSet)
	 */
	@Override
	public Dependente selectEntity(ResultSet rs) throws PersistenceException {
		Dependente dependente = new Dependente();
		try {
			dependente.setCnh(rs.getLong(1));
			dependente.setCodResponsavel(rs.getLong(2));
			dependente.setNome(rs.getString(3));
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
		return dependente;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#insertEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void insertEntity(Dependente entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCnh());
			stm.setLong(2, entidade.getCodResponsavel());
			stm.setString(3, entidade.getNome());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#updateEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void updateEntity(Dependente entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCnh());
			stm.setLong(2, entidade.getCodResponsavel());
			stm.setString(3, entidade.getNome());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#deleteEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void deleteEntity(Dependente entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCnh());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/**
	 * Método responsável por deletar todos os dependentes de um cliente.
	 *
	 * @param conn {@link Connection}
	 * @param cliente {@link Cliente}
	 * @throws BusinessException
	 */
	public void deleteDependentes(Connection conn, Cliente cliente) throws BusinessException {
		PreparedStatement stm = null;
		try {
			stm = conn.prepareStatement(SQLUtil.getQuery("DEPENDENTE_DELETE_ALL"));
			stm.setLong(1, cliente.getCodCliente());
			stm.executeUpdate();
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.close(stm);
		}
	}

	/**
	 * Método responsável por obter todos os dependentes de um cliente
	 *
	 * @param cliente {@link Cliente}
	 * @return List<Dependente>
	 * @throws BusinessException
	 */
	public List<Dependente> selectAll(Cliente cliente) throws BusinessException {
		PreparedStatement stm = null;
		ResultSet rs = null;
		List<Dependente> lista = new ArrayList<Dependente>();
		Connection conn = null;
		try {
			conn = DAOUtil.getConnection(getJndi());
			stm = conn.prepareStatement(SQLUtil.getQuery("DEPENDENTE_SELECT_ALL_FROM_CLIENTE"));
			stm.setLong(1, cliente.getCodCliente());
			rs = stm.executeQuery();
			while(rs.next()){
				selectAllEntity(lista, rs);
			}
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.closeResourses(conn, stm, rs);
			conn = null;
		}
		return lista;
	}

	@Override
	public void setParametros(PreparedStatement stm, Dependente entidade)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCodResponsavel());
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
		
	}

}
