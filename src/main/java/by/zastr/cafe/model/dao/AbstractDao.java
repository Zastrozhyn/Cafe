package by.zastr.cafe.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.connection.ConnectionPool;
import by.zastr.cafe.model.entity.CafeEntity;

public abstract  class AbstractDao<T extends CafeEntity> {
	protected static final Logger logger = LogManager.getLogger();
	protected Connection connection;
	
	public abstract List<T> findAll() throws DaoException;
	public abstract Optional<T> findById(int id) throws DaoException;
	public abstract boolean create(T entity) throws DaoException;
	public abstract boolean update(T entity) throws DaoException;
	public abstract boolean delete(T entity) throws DaoException;
	public abstract boolean delete(int id) throws DaoException;
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"Error with closing statement", e);
        }
    }
    public void closeConnection() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.releaseConnection(this.connection);
        connection = null;
    }

}
