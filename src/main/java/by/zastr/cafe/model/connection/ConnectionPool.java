package by.zastr.cafe.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * class ConnectionPool
 * @author A.Zastrozhyn
 *
 */
public class ConnectionPool {
	private static final Logger logger = LogManager.getLogger();
    private static final int POOL_SIZE = 4;
    private static final AtomicBoolean instanceCheck = new AtomicBoolean(false);
    private static final ReentrantLock instanceLock = new ReentrantLock(true);
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> freeConnections;
    private BlockingQueue<ProxyConnection> busyConnections;


    private ConnectionPool(){
        freeConnections = new LinkedBlockingQueue<>();
        busyConnections = new LinkedBlockingQueue<>();
        
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.offer(ConnectionBuilder.buildProxyConnection());
            } catch (SQLException e) {
                logger.log(Level.ERROR, "{} proxy connection wasn't created", i, e);
            }
        }
        if (freeConnections.isEmpty()){
            logger.log(Level.FATAL, "Connection pool is empty");
            throw new RuntimeException("Connection pool is empty");
        }
        logger.info("Connection pool created successfully, size = {}", POOL_SIZE);
    }

    /**
     * 
     * @return ConnectionPool
     */
    public static ConnectionPool getInstance(){
        if (!instanceCheck.get()){
            try {
                instanceLock.lock();
                if (instance == null){
                    instance = new ConnectionPool();
                    instanceCheck.set(true);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance;
    }

    /**
     * 
     * @return Connection
     */
    public Connection takeConnection(){
        ProxyConnection connection = null;
        try {
           connection = freeConnections.take();
           busyConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "taking connection was interrupted", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * 
     * @param connection
     */
    public boolean releaseConnection(Connection connection){
        boolean result = false;
        if (connection instanceof ProxyConnection proxyConnection){
            try {
               result = busyConnections.remove(proxyConnection);
               if (result){
                   freeConnections.put(proxyConnection);
               }
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "releasing connection was interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        return result;
    }

    /**
     * destroy Pool
     */
    public void destroyPool() {
        logger.log(Level.INFO, "Destroying pool is started");
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().realClose();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "SQL error while destroy {} connection", i, e);
                throw new RuntimeException("SQL error while destroy connection pool", e);//todo
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "destroying pool was interrupted", e);
                Thread.currentThread().interrupt();
            }
        }
        deregisterDrivers();
        instance = null;
        instanceCheck.set(false);
        logger.log(Level.INFO, "Commection pool destroyed");
    }

    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "SQL error while deregister driver", e);
            }
        });
    }
}
