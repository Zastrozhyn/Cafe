package by.zastr.cafe.model.service.impl;

import org.testng.annotations.Test;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.connection.ConnectionPool;

import java.sql.Connection;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class UserServiceImplTest {
    ConnectionPool connectionPool;
    Connection connection;
    UserServiceImpl service = UserServiceImpl.getInstance();
	
    @BeforeClass
    public void before() throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
    }

    @Test
    public void isUniqueLoginTest() throws ServiceException {
    	String login = "Qwerty";
    	Assert.assertTrue(service.isUniqueLogin(login));
    }
    
    @Test
    public void isUniqueWrongLoginTest() throws ServiceException {
    	String login = "byrak";
    	Assert.assertFalse(service.isUniqueLogin(login));
    }

    @Test
    public void isUniquePhoneTest() throws ServiceException {
    	String phone = "80295552005";
    	Assert.assertTrue(service.isUniquePhone(phone));
    }
    
    @Test
    public void isUniqueWrongPhoneTest() throws ServiceException {
    	String phone = "+375292223331";
    	Assert.assertFalse(service.isUniquePhone(phone));
    }
}
