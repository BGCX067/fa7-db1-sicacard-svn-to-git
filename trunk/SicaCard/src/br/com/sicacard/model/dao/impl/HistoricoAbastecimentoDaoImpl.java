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
import br.com.sicacard.model.entity.ClientePj;
import br.com.sicacard.model.entity.HistoricoAbastecimento;
import br.com.sicacard.model.entity.Posto;
import br.com.sicacard.model.entity.TipoCliente;
import br.com.sicacard.model.entity.exception.BusinessException;
import br.com.sicacard.model.entity.exception.PersistenceException;
import br.com.sicacard.util.SQLUtil;

/**
 * HistoricoAbastecimentoDaoImpl.java
 *
 * @author Ebix
 *
 * 23/04/2012
 */
public class HistoricoAbastecimentoDaoImpl extends GenericDaoImpl<HistoricoAbastecimento> {

	private static final Logger LOGGER = Logger.getLogger(HistoricoAbastecimentoDaoImpl.class);
	private GenericDAO<Posto> postoDao;
	private GenericDAO<ClientePf> clientePfDao;
	private GenericDAO<ClientePj> clientePjDao;


	/**
	 * Default constructor.
	 *
	 * @param jndi String
	 */
	public HistoricoAbastecimentoDaoImpl(String jndi) {
		super(jndi);
		postoDao = new PostoDaoImpl(jndi);
		clientePfDao = new ClientePfDaoImpl(jndi);
		clientePjDao = new ClientePjDaoImpl(jndi);
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
		return SQLUtil.getQuery("HISTORICO_ABASTECIMENTO_SELECT_ALL");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQuerySelect()
	 */
	@Override
	public String getQuerySelect() {
		return SQLUtil.getQuery("HISTORICO_ABASTECIMENTO_SELECT");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryInsert()
	 */
	@Override
	public String getQueryInsert() {
		return SQLUtil.getQuery("HISTORICO_ABASTECIMENTO_INSERT");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryUpdate()
	 */
	@Override
	public String getQueryUpdate() {
		return SQLUtil.getQuery("HISTORICO_ABASTECIMENTO_UPDATE");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#getQueryDelete()
	 */
	@Override
	public String getQueryDelete() {
		return SQLUtil.getQuery("HISTORICO_ABASTECIMENTO_DELETE");
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectAllEntity(java.util.List, java.sql.ResultSet)
	 */
	@Override
	public void selectAllEntity(List<HistoricoAbastecimento> lista, ResultSet rs)
			throws PersistenceException {
		try {
			HistoricoAbastecimento historicoAbastecimento = new HistoricoAbastecimento();
			Posto posto = new Posto();
			posto.setCnpj(Long.valueOf(rs.getString("CNPJ_POSTO")));
			historicoAbastecimento.setPosto(postoDao.select(posto));
			TipoCliente tipoCliente = TipoCliente.valueOf(rs.getInt("COD_TIPO_CLIENTE"));
			historicoAbastecimento.setTipoCliente(tipoCliente);
			historicoAbastecimento.setNumeroDocumento(Long.valueOf(rs.getString("NUM_DOCUMENTO")));
			historicoAbastecimento.setCliente(getCliente(tipoCliente, historicoAbastecimento.getNumeroDocumento()));
			historicoAbastecimento.setValor(rs.getDouble("VALOR"));

			lista.add(historicoAbastecimento);
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	private Cliente getCliente(TipoCliente tipoCliente, long numeroDocumento) throws BusinessException {
		Cliente cliente = null;
		if(tipoCliente == TipoCliente.CLIENTE_PF) {
			ClientePf clientePf = new ClientePf();
			clientePf.setCpf(numeroDocumento);
			cliente = clientePfDao.select(clientePf);
		} else {
			ClientePj clientePj = new ClientePj();
			clientePj.setCnpj(numeroDocumento);
			cliente = clientePjDao.select(clientePj);
		}
		return cliente;
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#selectEntity(java.sql.ResultSet)
	 */
	@Override
	public HistoricoAbastecimento selectEntity(ResultSet rs)
			throws PersistenceException {
		HistoricoAbastecimento historicoAbastecimento = null;
		try {
			historicoAbastecimento = new HistoricoAbastecimento();
			Posto posto = new Posto();
			posto.setCnpj(Long.valueOf(rs.getString("CNPJ_POSTO")));
			historicoAbastecimento.setPosto(postoDao.select(posto));
			TipoCliente tipoCliente = TipoCliente.valueOf(rs
					.getInt("COD_TIPO_CLIENTE")-1);
			historicoAbastecimento.setTipoCliente(tipoCliente);
			long codCliente = rs.getLong("COD_CLIENTE");
			historicoAbastecimento.setCliente(getCliente(tipoCliente,
					codCliente));
			historicoAbastecimento.setValor(rs.getDouble("VALOR"));
			historicoAbastecimento.setNumeroDocumento(Long.valueOf(rs
					.getString("NUM_DOCUMENTO")));
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
		return historicoAbastecimento;
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#insertEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void insertEntity(HistoricoAbastecimento entidade,
			PreparedStatement stm) throws PersistenceException {
		try {
			stm.setLong(1, entidade.getPosto().getCnpj());
			stm.setLong(2, entidade.getCliente().getCodCliente());
			stm.setDouble(3, entidade.getValor());
			stm.setLong(4, entidade.getNumeroDocumento());
			stm.setLong(5, entidade.getTipoCliente().getCodigo());
			stm.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#updateEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void updateEntity(HistoricoAbastecimento entidade,
			PreparedStatement stm) throws PersistenceException {
		try {
			stm.setLong(1, entidade.getPosto().getCnpj());
			stm.setLong(2, entidade.getCliente().getCodCliente());
			stm.setDouble(3, entidade.getValor());
			stm.setLong(4, entidade.getNumeroDocumento());
			stm.setLong(5, entidade.getTipoCliente().getCodigo());
			stm.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.sicacard.model.dao.impl.GenericDAOImpl#deleteEntity(br.com.sicacard.model.entity.GenericEntity, java.sql.PreparedStatement)
	 */
	@Override
	public void deleteEntity(HistoricoAbastecimento entidade,
			PreparedStatement stm) throws PersistenceException {
		try {
			stm.setLong(1, entidade.getPosto().getCnpj());
			stm.setLong(2, entidade.getCliente().getCodCliente());
			stm.executeUpdate();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	@Override
	public void setParametros(PreparedStatement stm,
			HistoricoAbastecimento entidade) throws PersistenceException {
		try {
			stm.setLong(1, entidade.getPosto().getCnpj());
			stm.setLong(2, entidade.getCliente().getCodCliente());
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}

	}

}
