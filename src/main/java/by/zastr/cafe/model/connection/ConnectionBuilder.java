package by.zastr.cafe.model.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.zastr.cafe.util.ConfigurationManager;

/**
 * class ConnectionBuilder
 * @author A.Zastrozhyn
 *
 */
public class ConnectionBuilder {
	private static final Logger logger = LogManager.getLogger();
	private static final String DB_URL = "db.url";
	private static final String DB_USER = "db.username";
	private static final String DB_PASSWORD = "db.password";
	
	static {
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		} catch (SQLException e) {
			logger.log(Level.FATAL, "Database driver not found", e);
            throw new RuntimeException("Database driver not found ", e);
		}
	}
	
	private ConnectionBuilder() {
	}
	
	static ProxyConnection buildProxyConnection() throws SQLException {
		String url = ConfigurationManager.getProperty(DB_URL);
		String user = ConfigurationManager.getProperty(DB_USER);
		String password = ConfigurationManager.getProperty(DB_PASSWORD);
        return new ProxyConnection(DriverManager.getConnection(url, user, password)); 
	}
	
}
