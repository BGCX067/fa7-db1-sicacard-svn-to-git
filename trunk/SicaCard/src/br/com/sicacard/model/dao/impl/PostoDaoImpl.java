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
import br.com.sicacard.model.entity.DadosBancario;
import br.com.sicacard.model.entity.Posto;
import br.com.sicacard.model.entity.exception.PersistenceException;
import br.com.sicacard.util.DAOUtil;
import br.com.sicacard.util.SQLUtil;

/**
 * PostoDaoImpl.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 22/04/2012
 * @version 1.0
 */
public class PostoDaoImpl extends GenericDaoImpl<Posto> {

	private static final Logger LOGGER = Logger.getLogger(PostoDaoImpl.class);
	private GenericDAO<DadosBancario>  dadosBancarioDao;


	/**
	 * Default constructor.
	 *
	 * @param jndi String
	 */
	public PostoDaoImpl(String jndi) {
		super(jndi);
		dadosBancarioDao = new DadosBancarioDaoImpl(jndi);
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
		return SQLUtil.getQuery("POSTO_SELECT_ALL");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQuerySelect()
	 */
	@Override
	public String getQuerySelect() {
		return SQLUtil.getQuery("POSTO_SELECT");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryInsert()
	 */
	@Override
	public String getQueryInsert() {
		return SQLUtil.getQuery("POSTO_INSERT");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryUpdate()
	 */
	@Override
	public String getQueryUpdate() {
		return SQLUtil.getQuery("POSTO_UPDATE");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryDelete()
	 */
	@Override
	public String getQueryDelete() {
		return SQLUtil.getQuery("POSTO_DELETE");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectAllEntity(java.util.List, java.sql.ResultSet)
	 */
	@Override
	public void selectAllEntity(List<Posto> lista, ResultSet rs)
			throws PersistenceException {
		try {
			Posto posto = new Posto();
			posto.setCnpj(Long.valueOf(rs.getString("CNPJ")));
			DadosBancario db = new DadosBancario();
			db.setCodigo(rs.getLong("COD_DADOS_BANCARIO"));
			posto.setDadosBancario(dadosBancarioDao.select(db));
			posto.setNomeFantasia(rs.getString("NOME_FANTASIA"));
			posto.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
			posto.setTelefone(rs.getString("TELEFONE"));
			lista.add(posto);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectEntity(java.sql.ResultSet)
	 */
	@Override
	public Posto selectEntity(ResultSet rs) throws PersistenceException {
		Posto posto = null;
		try {
			posto = new Posto();
			posto.setCnpj(Long.valueOf(rs.getString("CNPJ")));
			DadosBancario db = new DadosBancario();
			db.setCodigo(rs.getLong("COD_DADOS_BANCARIO"));
			posto.setDadosBancario(dadosBancarioDao.select(db));
			posto.setNomeFantasia(rs.getString("NOME_FANTASIA"));
			posto.setRazaoSocial(rs.getString("RAZAO_SOCIAL"));
			posto.setTelefone(rs.getString("TELEFONE"));
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
		return posto;
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#insertEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void insertEntity(Posto entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			DAOUtil.beginTransaction(getConn());
			stm.setLong(1, entidade.getCnpj());
			dadosBancarioDao.insert(entidade.getDadosBancario(), getConn());
			stm.setLong(2, entidade.getDadosBancario().getCodigo());
			stm.setString(3, entidade.getNomeFantasia());
			stm.setString(4, entidade.getRazaoSocial());
			stm.setString(5, entidade.getTelefone());
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
	public void updateEntity(Posto entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			DAOUtil.beginTransaction(getConn());
			stm.setLong(1, entidade.getCnpj());
			dadosBancarioDao.update(entidade.getDadosBancario(), getConn());
			stm.setLong(2, entidade.getDadosBancario().getCodigo());
			stm.setString(3, entidade.getNomeFantasia());
			stm.setString(4, entidade.getRazaoSocial());
			stm.setString(5, entidade.getTelefone());
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
	public void deleteEntity(Posto entidade, PreparedStatement stm)
			throws PersistenceException {
		try {
			DAOUtil.beginTransaction(getConn());
			stm.setLong(1, entidade.getCnpj());
			dadosBancarioDao.delete(entidade.getDadosBancario(), getConn());
			stm.executeUpdate();
			DAOUtil.commitTransaction(getConn());
		} catch (Exception e) {
			LOGGER.error(e);
			DAOUtil.rollbackTransaction(getConn());
			throw new PersistenceException(e);
		}
	}

	@Override
	public void setParametros(PreparedStatement stm, Posto entidade)
			throws PersistenceException {
		try {
			stm.setLong(1, entidade.getCnpj());
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

}
