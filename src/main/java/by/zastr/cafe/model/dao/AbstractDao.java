package by.zastr.cafe.model.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.connection.ConnectionPool;
import by.zastr.cafe.model.entity.CafeEntity;

/**
 * class AbstractDao.
 *
 * @author A.Zastrozhyn
 * @param <T> the generic type
 */
public abstract  class AbstractDao<T extends CafeEntity> {
	
	/** The Constant logger. */
	protected static final Logger logger = LogManager.getLogger();
	
	/** The connection. */
	protected Connection connection;
	
	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	public abstract List<T> findAll() throws DaoException;
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 * @throws DaoException the dao exception
	 */
	public abstract Optional<T> findById(int id) throws DaoException;
	
	/**
	 * Creates the.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public abstract boolean create(T entity) throws DaoException;
	
	/**
	 * Update.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public abstract boolean update(T entity) throws DaoException;
	
	/**
	 * Delete.
	 *
	 * @param entity the entity
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public abstract boolean delete(T entity) throws DaoException;
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	public abstract boolean delete(int id) throws DaoException;
	
	/**
	 * Sets the connection.
	 *
	 * @param connection the new connection
	 */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    
    /**
     * Close connection.
     */
    public void closeConnection() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.releaseConnection(this.connection);
        connection = null;
    }

}
