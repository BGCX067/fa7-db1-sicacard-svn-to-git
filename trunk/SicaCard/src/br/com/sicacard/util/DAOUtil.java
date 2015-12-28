/**
 *
 */
package br.com.sicacard.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import br.com.sicacard.model.entity.exception.PersistenceException;

/**
 * DAOUtil.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
public class DAOUtil {

	private static final Logger LOGGER = Logger.getLogger(DAOUtil.class);

	/**
	 * M�todo respons�vel por obter uma conex�o.
	 *
	 * @param jndi String
	 * @return {@link Connection}
	 * @throws NamingException
	 * @throws SQLException
	 */
	public static Connection getConnection(String jndi) throws NamingException, SQLException{
		Context ctx = null;
		Context envCtx = null;
		DataSource ds = null;
		ctx = new InitialContext();
		envCtx = (Context) ctx.lookup("java:comp/env");
		ds = (DataSource) envCtx.lookup(jndi);
		return ds.getConnection();
	}

	/**
	 * M�todo respons�vel por fechar os recursos.
	 *
	 * @param conn {@link Connection}
	 * @param stm {@link Statement}
	 * @param rs {@link ResultSet}
	 */
	public static void closeResourses(Connection conn, Statement stm, ResultSet rs) {
		close(rs);
		close(stm);
		close(conn);
	}

	/**
	 * M�todo respons�vel por fechar os recursos.
	 *
	 * @param stm {@link Statement}
	 * @param rs {@link ResultSet}
	 */
	public static void closeResourses(Statement stm, ResultSet rs) {
		close(rs);
		close(stm);
	}

	/**
	 * M�todo respons�vel por fechar os recursos.
	 *
	 * @param conn {@link Connection}
	 * @param stm {@link Statement}
	 */
	public static void closeResourses(Connection conn, Statement stm) {
		close(stm);
		close(conn);
	}

	/**
	 * M�todo respons�vel por fechar uma {@link Connection}
	 *
	 * @param conn @link Connection}
	 */
	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo respons�vel por fechar um {@link Statement}.
	 *
	 * @param stm {@link Statement}
	 */
	public static void close(Statement stm) {
		try {
			if (stm != null) {
				stm.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo respons�vel por fechar um {@link ResultSet}.
	 *
	 * @param rs {@link ResultSet}
	 */
	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo respons�vel por iniciar uma transa��o.
	 *
	 * @param conn {@link Connection}
	 * @throws SQLException
	 */
	public static void beginTransaction(Connection conn) throws SQLException {
		conn.setAutoCommit(false);
	}

	/**
	 * M�todo respons�vel por terminar uma transa��o.
	 *
	 * @param conn {@link Connection}
	 * @throws SQLException
	 */
	public static void endTransaction(Connection conn) throws SQLException {
		conn.setAutoCommit(true);
	}

	/**
	 * M�todo respons�vel por reverter uma transa��o.
	 *
	 * @param conn {@link Connection}
	 * @throws PersistenceException
	 * @throws SQLException
	 */
	public static void rollbackTransaction(Connection conn) throws PersistenceException {
		try {
			conn.rollback();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new PersistenceException(e);
		}
	}

	/**
	 * M�todo respons�vel por comitar uma transa��o.
	 *
	 * @param conn {@link Connection}
	 * @throws SQLException
	 */
	public static void commitTransaction(Connection conn) throws SQLException {
		conn.commit();
	}

}
