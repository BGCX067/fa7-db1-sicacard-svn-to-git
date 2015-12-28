package br.com.sicacard.model.dao;

import java.sql.Connection;
import java.util.List;

import br.com.sicacard.model.entity.GenericEntity;
import br.com.sicacard.model.entity.exception.BusinessException;

/**
 * GenericDAO.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 * @param <E>
 */
public interface GenericDAO <E extends GenericEntity>{

	/**
	 * M�todo repons�vel por obter uma lista com todas as
	 * entidades contidas no banco.
	 *
	 * @return List<E>
	 * @throws BusinessException
	 */
	List<E> selectAll() throws BusinessException;

	/**
	 * M�todo repons�vel por obter uma entidade do banco de dados.
	 *
	 * @param entidade E
	 * @return E
	 * @throws BusinessException
	 */
	E select(E entidade) throws BusinessException;

	/**
	 * M�todo repons�vel por incluir uma entidade no banco de dados.
	 *
	 * @param entidade E
	 * @throws BusinessException
	 */
	void insert(E entidade) throws BusinessException;

	/**
	 * M�todo repons�vel por atualizar uma entidade no banco de dados.
	 *
	 * @param entidade E
	 * @throws BusinessException
	 */
	void update(E entidade) throws BusinessException;

	/**
	 * M�todo repons�vel por deletar uma entidade no banco de dados.
	 *
	 * @param entidade E
	 * @throws BusinessException
	 */
	void delete(E entidade) throws BusinessException;

	/**
	 * M�todo repons�vel por incluir uma entidade no banco de dados.
	 *
	 * @param entidade E
	 * @param conn {@link Connection}
	 * @throws BusinessException
	 */
	void insert(E entidade, Connection conn) throws BusinessException;

	/**
	 * M�todo repons�vel por atualizar uma entidade no banco de dados.
	 *
	 * @param entidade E
	 * @param conn {@link Connection}
	 * @throws BusinessException
	 */
	void update(E entidade, Connection conn) throws BusinessException;

	/**
	 * M�todo repons�vel por deletar uma entidade no banco de dados.
	 *
	 * @param entidade E
	 * @param conn {@link Connection}
	 * @throws BusinessException
	 */
	void delete(E entidade, Connection conn) throws BusinessException;

}
