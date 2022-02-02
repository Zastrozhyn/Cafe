package by.zastr.cafe.model.connection;

import java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import by.zastr.cafe.exception.DaoException;

public class ConnectionPoolTest {
    ConnectionPool connectionPool;
    Connection connection;
	
    @BeforeClass
    public void before() throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
    }

  @Test
  public void getInstanceTest() {
      Assert.assertNotNull(connection);
      connectionPool.releaseConnection(connection);
  }
}
