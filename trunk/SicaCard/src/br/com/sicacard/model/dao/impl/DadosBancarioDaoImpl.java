/**
 *
 */
package br.com.sicacard.model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.sicacard.model.entity.DadosBancario;
import br.com.sicacard.model.entity.exception.PersistenceException;
import br.com.sicacard.util.SQLUtil;

/**
 * DadosBancarioDAOImpl.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
public class DadosBancarioDaoImpl extends GenericDaoImpl<DadosBancario> {

	private static final Logger LOGGER = Logger.getLogger(DadosBancarioDaoImpl.class);

	/**
	 * Default constructor.
	 *
	 * @param jndi String
	 */
	public DadosBancarioDaoImpl(String jndi) {
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
		return SQLUtil.getQuery("DADOS_BANCARIO_SELECT_ALL");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQuerySelect()
	 */
	@Override
	public String getQuerySelect() {
		return SQLUtil.getQuery("DADOS_BANCARIO_SELECT");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryInsert()
	 */
	@Override
	public String getQueryInsert() {
		return SQLUtil.getQuery("DADOS_BANCARIO_INSERT");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryUpdate()
	 */
	@Override
	public String getQueryUpdate() {
		return SQLUtil.getQuery("DADOS_BANCARIO_UPDATE");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryDelete()
	 */
	@Override
	public String getQueryDelete() {
		return SQLUtil.getQuery("DADOS_BANCARIO_DELETE");
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectAllEntity(java.util.List, java.sql.ResultSet)
	 */
	@Override
	public void selectAllEntity(List<DadosBancario> lista, ResultSet rs)
			throws PersistenceException {
		try {
			DadosBancario dadosBancario = new DadosBancario();
			dadosBancario.setCodigo(rs.getLong("COD_DADOS_BANCARIO"));
			dadosBancario.setBanco(rs.getString("BANCO"));
			dadosBancario.setAgencia(rs.getString("AGENCIA"));
			dadosBancario.setConta(rs.getString("CONTA"));
			lista.add(dadosBancario);
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
	public DadosBancario selectEntity(ResultSet rs) throws PersistenceException {
		DadosBancario dadosBancario = new DadosBancario();
		try {
			dadosBancario.setCodigo(rs.getLong("COD_DADOS_BANCARIO"));
			dadosBancario.setBanco(rs.getString("BANCO"));
			dadosBancario.setAgencia(rs.getString("AGENCIA"));
			dadosBancario.setConta(rs.getString("CONTA"));
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
		return dadosBancario;
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#insertEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void insertEntity(DadosBancario entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			entidade.setCodigo(this.getSequenceNextValue("DADOS_BANCARIO_SEQ"));
			stm.setLong(1, entidade.getCodigo());
			stm.setString(2, entidade.getBanco());
			stm.setString(3, entidade.getAgencia());
			stm.setString(4, entidade.getConta());
			stm.executeUpdate();
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
	public void updateEntity(DadosBancario entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			stm.setString(1, entidade.getBanco());
			stm.setString(2, entidade.getAgencia());
			stm.setString(3, entidade.getConta());
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
	public void deleteEntity(DadosBancario entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCodigo());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDaoImpl#setParametros(java.sql.PreparedStatement, br.com.sicacard.model.entity.GenericEntity)
	 */
	@Override
	public void setParametros(PreparedStatement stm, DadosBancario entidade)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCodigo());
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
		
	}

}
