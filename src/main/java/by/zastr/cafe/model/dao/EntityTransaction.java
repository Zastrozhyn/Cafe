package by.zastr.cafe.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.connection.ConnectionPool;

/**
 * The Class EntityTransaction.
 *
 * @author A.Zastrozhyn
 */
public class EntityTransaction {
	private static final Logger logger = LogManager.getLogger();
	private Connection connection;
	
    /**
     * Instantiates a new entity transaction.
     */
    public EntityTransaction() {
    }
    
    /**
     * Begin transaction.
     *
     * @param daos the daos
     * @throws DaoException the dao exception
     */
	public void beginTransaction(AbstractDao... daos) throws DaoException {
        if (connection == null) {
        	ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
        	logger.info("Problems with disabling autocommit", e);
            throw new DaoException("Problems with disabling autocommit", e);
        }
        for (AbstractDao daoElement : daos) {
        	if (daoElement != null) {
        		daoElement.setConnection(connection);
        	}
        	if (daoElement == null) {
        		logger.info("Dao element = null!!!");
        	}
        }
    }
    
	/**
	 * Begin
	 *
	 * @param dao the dao
	 */
    public void begin(AbstractDao dao){
        if (connection == null) {
        	ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
        }
        	if (dao != null) {
        		dao.setConnection(connection);
        	}
        	if (dao == null) {
        		logger.info("Dao element = null!!!");
        	}
    }
    
    /**
     * end transaction.
     */
    public void end(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.releaseConnection(connection);
        connection = null;
    }
   
    /**
     * end transaction.
     */
    public void endTransaction(){
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
        	logger.info("Problems with enabling autocommit", e);
        }
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.releaseConnection(connection);
        connection = null;
    }
    
    /**
     * Commit.
     *
     * @throws DaoException the dao exception
     */
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
        	logger.info("Commit has been failed", e);
            throw new DaoException("Commit has been failed", e);
        }
    }

    /**
     * Rollback.
     *
     * @throws DaoException the dao exception
     */
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
        	logger.info("Rollback has been failed", e);
            throw new DaoException("Rollback has been failed", e);
        }
    }
}
