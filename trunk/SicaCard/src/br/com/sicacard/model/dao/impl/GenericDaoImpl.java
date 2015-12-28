package br.com.sicacard.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sicacard.model.dao.GenericDAO;
import br.com.sicacard.model.entity.GenericEntity;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.model.entity.exception.PersistenceException;
import br.com.sicacard.util.DAOUtil;


/**
 * GenericDAOImpl.java
 *
 *@author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 * @param <E>
 */
public abstract class GenericDaoImpl<E extends GenericEntity> implements GenericDAO<E>{

	private String jndi;
	private Connection conn;

	/**
	 * Default constructor.
	 * @param jndi String
	 */
	public GenericDaoImpl(String jndi){
		this.jndi = jndi;
	}

	/**
	 * Get the jndi.
	 * @return <code>String</code>
	 */
	public String getJndi() {
		return jndi;
	}

	/**
	 * Set the jndi.
	 * @param jndi the <code>String</code>
	 */
	public void setJndi(String jndi) {
		this.jndi = jndi;
	}

	/**
	 * Get the conn
	 * @return <code>Connection</code>
	 */
	public Connection getConn() {
		return conn;
	}

	/**
	 * Set the conn
	 * @param conn the <code>Connection</code>
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	/**
	 * Método responsável por obter o valor de uma sequence.
	 *
	 * @param seguenceName String
	 * @param conn Connection
	 * @return long
	 * @throws BusinessException
	 */
	public long getSequenceNextValue(String seguenceName, Connection conn) throws BusinessException {
		long sequenceValue = 0L;
		Statement stm = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("select NEXTVAL('");
			sb.append(seguenceName);
			sb.append("')");
			stm = conn.createStatement();
			rs = stm.executeQuery(sb.toString());
			if (rs.next()) {
				sequenceValue =  rs.getLong(1);
			}
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.closeResourses(stm, rs);
		}
		return sequenceValue;
	}

	/**
	 * Método responsável por obter o valor de uma sequence.
	 *
	 * @param seguenceName String
	 * @param conn Connection
	 * @return long
	 * @throws BusinessException
	 */
	public long getSequenceNextValue(String seguenceName) throws BusinessException {
		long sequenceValue = 0L;
		Statement stm = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DAOUtil.getConnection(this.getJndi());
			StringBuilder sb = new StringBuilder();
			sb.append("select NEXTVAL('");
			sb.append(seguenceName);
			sb.append("')");
			stm = conn.createStatement();
			rs = stm.executeQuery(sb.toString());
			if (rs.next()) {
				sequenceValue =  rs.getLong(1);
			}
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.closeResourses(conn, stm, rs);
		}
		return sequenceValue;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.GenericDAO#selectAll()
	 */
	public List<E> selectAll() throws BusinessException {
		Statement stm = null;
		ResultSet rs = null;
		List<E> lista = new ArrayList<E>();

		try {
			conn = DAOUtil.getConnection(getJndi());
			stm = conn.createStatement();
			rs = stm.executeQuery(getQuerySelectAll());
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


	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.GenericDAO#select(br.com.sicacard.model.entity.GenericEntity)
	 */
	public E select(E entidade) throws BusinessException {
		PreparedStatement stm = null;
		ResultSet rs = null;
		E entity = null;

		try {
			conn = DAOUtil.getConnection(getJndi());
			stm = conn.prepareStatement(getQuerySelect());
			setParametros(stm, entidade);
			rs = stm.executeQuery();
			while(rs.next()){
				entity = selectEntity(rs);
			}
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.closeResourses(conn, stm, rs);
			conn = null;
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.GenericDAO#insert(br.com.sicacard.model.entity.GenericEntity)
	 */
	public void insert(E entidade) throws BusinessException {
		PreparedStatement stm = null;
		try {
			conn = DAOUtil.getConnection(getJndi());
			stm = conn.prepareStatement(getQueryInsert());
			insertEntity(entidade, stm);
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.closeResourses(conn, stm);
			conn = null;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.GenericDAO#update(br.com.sicacard.model.entity.GenericEntity)
	 */
	public void update(E entidade) throws BusinessException {
		PreparedStatement stm = null;
		try {
			conn = DAOUtil.getConnection(getJndi());
			stm = conn.prepareStatement(getQueryUpdate());
			updateEntity(entidade, stm);
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.closeResourses(conn, stm);
			conn = null;
		}

	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.GenericDAO#delete(br.com.sicacard.model.entity.GenericEntity)
	 */
	public void delete(E entidade) throws BusinessException {
		PreparedStatement stm = null;
		try {
			conn = DAOUtil.getConnection(getJndi());
			stm = conn.prepareStatement(getQueryDelete());
			deleteEntity(entidade, stm);
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.closeResourses(conn, stm);
			conn = null;
		}
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.GenericDAO#insert(br.com.sicacard.model.entity.GenericEntity, java.sql.Connection)
	 */
	public void insert(E entidade, Connection conn) throws BusinessException {
		PreparedStatement stm = null;
		try {
			this.conn = conn;
			stm = conn.prepareStatement(getQueryInsert());
			insertEntity(entidade, stm);
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.close(stm);
		}

	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.GenericDAO#update(br.com.sicacard.model.entity.GenericEntity, java.sql.Connection)
	 */
	public void update(E entidade, Connection conn) throws BusinessException {
		PreparedStatement stm = null;
		try {
			this.conn = conn;
			stm = conn.prepareStatement(getQueryUpdate());
			updateEntity(entidade, stm);
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.close(stm);
		}

	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.GenericDAO#delete(br.com.sicacard.model.entity.GenericEntity, java.sql.Connection)
	 */
	public void delete(E entidade, Connection conn) throws BusinessException {
		PreparedStatement stm = null;
		try {
			this.conn = conn;
			stm = conn.prepareStatement(getQueryDelete());
			deleteEntity(entidade, stm);
		} catch (Exception e) {
			getLogger().error(e);
			throw new BusinessException(e);
		} finally {
			DAOUtil.close(stm);
		}

	}

	/**
	 * Método responsável por obter o logger da classe.
	 *
	 * @return {@link Logger}
	 */
	public abstract Logger getLogger();

	/**
	 * Método responsável por obter a query que seleciona todas as entidades.
	 *
	 * @return String
	 */
	public abstract String getQuerySelectAll();

	/**
	 * Método responsável por obter a query que seleciona uma entidade.
	 *
	 * @return String
	 */
	public abstract String getQuerySelect() ;

	/**
	 * Método responsável por obter a query que insere uma entidade.
	 *
	 * @return String
	 */
	public abstract String getQueryInsert();

	/**
	 * Método responsável por obter a query que atualiza uma entidade.
	 *
	 * @return String
	 */
	public abstract String getQueryUpdate();

	/**
	 * Método responsável por obter a query que deleta uma entidade.
	 *
	 * @return String
	 */
	public abstract String getQueryDelete();

	/**
	 * Método responsável por obter uma lista de entidades a partir de
	 * um ResultSet.
	 *
	 * @param lista List<E>
	 * @param rs {@link ResultSet}
	 * @throws PersistenceException
	 */
	public abstract void selectAllEntity(List<E> lista, ResultSet rs) throws PersistenceException;

	/**
	 * Método responsável por obter uma entidade a partir de
	 * um ResultSet.
	 *
	 * @param rs {@link ResultSet}
	 * @return E
	 * @throws PersistenceException
	 */
	public abstract  E selectEntity(ResultSet rs) throws PersistenceException;

	/**
	 * Método responsável  por transferir  o estado da entidade para a
	 * query de inserção.
	 *
	 * @param entidade E
	 * @param stm {@link PreparedStatement}
	 * @throws PersistenceException
	 */
	public abstract void insertEntity(E entidade, PreparedStatement stm) throws PersistenceException;

	/**
	 * Método responsável  por transferir  o estado da entidade para a
	 * query de atualização.
	 *
	 * @param entidade E
	 * @param stm {@link PreparedStatement}
	 * @throws PersistenceException
	 */
	public abstract void updateEntity(E entidade, PreparedStatement stm) throws PersistenceException;

	/**
	 * Método responsável  por transferir  o estado da entidade para a
	 * query de Deleção.
	 *
	 * @param entidade E
	 * @param stm {@link PreparedStatement}
	 * @throws PersistenceException
	 */
	public abstract void deleteEntity(E entidade, PreparedStatement stm) throws PersistenceException;
	
	/**
	 * Método responsável por setar os parametros da consulta.
	 * 
	 * @param stm
	 * @param entidade E
	 * @throws PersistenceException
	 */
	public abstract void setParametros(PreparedStatement stm, E entidade) throws PersistenceException;

}
