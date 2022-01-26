package by.zastr.cafe.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.dao.EntityTransaction;
import by.zastr.cafe.model.dao.impl.UserDaoImpl;
import by.zastr.cafe.model.entity.Account;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.CafeService;
import by.zastr.cafe.util.InputValidator;
import by.zastr.cafe.util.PasswordEncryptor;
import by.zastr.cafe.util.impl.InputValidatorImpl;

public class UserServiceImpl implements CafeService<User> {
	private static UserServiceImpl instance = new UserServiceImpl();;
	private UserDaoImpl userDao;
	private EntityTransaction entityTransaction;
	private static final User.Role DEFAULT_USER_ROLE = User.Role.CLIENT; 
	
	private UserServiceImpl() {
		entityTransaction = new EntityTransaction();
		userDao = new UserDaoImpl();
	}

	public static UserServiceImpl getInstance() {
        return instance;
	}
	
	@Override
	public List<User> findAll() throws ServiceException{
		List<User> userList = new ArrayList<User>();
		entityTransaction.beginTransaction(userDao);
		try {
			userList = userDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding users", e);
		}
		finally {
			entityTransaction.end();
		}
		return userList;
	}
	
	@Override
	public Optional<User> findById(int id) throws ServiceException{
		Optional<User> user = Optional.empty();
		try {
			entityTransaction.beginTransaction(userDao);
			user = userDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding users by id", e);
		}
		finally {
			entityTransaction.end();
		}
		return user;
	}
	
	public Optional<User> findByLogin(String login) throws ServiceException{
		Optional<User> user = Optional.empty();
		try {
			entityTransaction.beginTransaction(userDao);
			user = userDao.findByLogin(login);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding users by login", e);
		}
		finally {
			entityTransaction.end();
		}
		return user;
	}
	
	public List<User> findByName(String name) throws ServiceException{
		List<User> userList = new ArrayList<User>();
		try {
			entityTransaction.beginTransaction(userDao);
			userList = userDao.findByName(name);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding users", e);
		}
		finally {
			entityTransaction.end();
		}
		return userList;
	}
	
	public List<User> findByLastName(String name) throws ServiceException{
		List<User> userList = new ArrayList<User>();
		try {
			entityTransaction.beginTransaction(userDao);
			userList = userDao.findByLastName(name);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding users", e);
		}
		finally {
			entityTransaction.end();
		}
		return userList;
	}
	
	public List<User> findByRole(String role) throws ServiceException{
		List<User> userList = new ArrayList<User>();
		try {
			entityTransaction.beginTransaction(userDao);
			userList = userDao.findByRole(role);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding users", e);
		}
		finally {
			entityTransaction.end();
		}
		return userList;
	}
	
	@Override
	public boolean delete(int id) throws ServiceException {
		boolean b = false;
		try {
			entityTransaction.beginTransaction(userDao);
			b = userDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method delete user", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;	
	}
	
	public boolean update(User user) throws ServiceException {
		boolean b = false;
		try {
			entityTransaction.beginTransaction(userDao);
			b = userDao.update(user);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method delete user", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;	
	}
	
	public String edit(int userId, String name, String lastName, String phone, String email) throws ServiceException {
		InputValidator validator = InputValidatorImpl.getInstance();
		if (!validator.isCorrectName(name)) {
			return UserMessage.WRONG_NAME;
		}
		if (!validator.isCorrectName(lastName)) {
			return UserMessage.WRONG_NAME;
		}

		if (!validator.isCorrectEmail(email)) {
			return UserMessage.WRONG_EMAIL;
		}
		if(!isUniquePhone(phone)) {
			return UserMessage.WRONG_PHONE;
		}
		 
		User user = new User();

		try {
			entityTransaction.beginTransaction(userDao);
			user = userDao.findById(userId).get();
			user.setEmail(email);
			user.setLastName(lastName);
			user.setName(name);
			user.setPhone(phone);
			userDao.update(user);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method create user", e);
		}
		finally {
			entityTransaction.end();
		}
		return UserMessage.UPDATE_SUCCESSFUL;	
	}
	
	public String registration(String name, String lastName, String phone, String login, 
			String password, String confirmPassword, String email) throws ServiceException {
		InputValidator validator = InputValidatorImpl.getInstance();
		if (!validator.isCorrectName(name)) {
			return UserMessage.WRONG_NAME;
		}
		if (!validator.isCorrectName(lastName)) {
			return UserMessage.WRONG_NAME;
		}
		if (!validator.isCorrectEmail(email)) {
			return UserMessage.WRONG_EMAIL;
		}
		if (!validator.isCorrectPassword(password)) {
			return UserMessage.WRONG_PASSWORD;
		}
		if (!validator.arePasswordsEqual(password,confirmPassword)) {
			return UserMessage.WRONG_CONFIRM_PASSWORD;
		}
		if(!isUniqueLogin(login)) {
			return UserMessage.WRONG_LOGIN;
		}
		if(!isUniquePhone(phone)) {
			return UserMessage.WRONG_PHONE;
		}
		 
		Account account = AccountServiceImpl.getInstance().CreateNewDefaultAccount(); 
		int accountId = account.getId();
		password = PasswordEncryptor.encrypt(password);
		User user = new User();
		user.setEmail(email);
		user.setLastName(lastName);
		user.setLogin(login);
		user.setName(name);
		user.setPhone(phone);
		user.setRole(DEFAULT_USER_ROLE);
		try {
			entityTransaction.beginTransaction(userDao);
			userDao.create(user, accountId);
			userDao.setPassword(password,login);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method create user", e);
		}
		finally {
			entityTransaction.end();
		}
		return UserMessage.REGISTRATION_SUCCESSFUL;	
	}
	
	public Optional<User> login (String login, String password) throws ServiceException {
		Optional<User> optionalUser = Optional.empty();
    	List<User> userList = findAll();
    	for (User user : userList) {
    		if (user.getLogin().equals(login)&&BCrypt.checkpw(password,getPassword(user.getUserId()))) {
    			optionalUser = Optional.of(user);
    		}
    	}
		return optionalUser;
	}
	
	public String changePassword (int userId, String login, String password, String newPassword, String confirmPassword) throws ServiceException {
		InputValidator validator = InputValidatorImpl.getInstance();
		if (!validator.isCorrectPassword(newPassword)) {
			return UserMessage.WRONG_PASSWORD;
		}
		if (!validator.arePasswordsEqual(newPassword,confirmPassword)) {
			return UserMessage.WRONG_CONFIRM_PASSWORD;
		}
		if (!BCrypt.checkpw(password,getPassword(userId))) {
			return UserMessage.WRONG_PASSWORD;
		}
		try {
			entityTransaction.beginTransaction(userDao);
			newPassword = PasswordEncryptor.encrypt(newPassword);
			userDao.setPassword(newPassword, login);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method change password", e);
		}
		finally {
			entityTransaction.end();
		}
		return UserMessage.SUCCESSFUL;
	}
	 
    public boolean isUniqueLogin (String login) throws ServiceException {
    	boolean b = true;
    	List<User> userList = findAll();
    	for (User user : userList) {
    		if (user.getLogin().equals(login)) {
    			b=false;
    		}
    	}
    	return b;
    }
    
    public boolean isUniquePhone (String phone) throws ServiceException {
    	boolean b = true;
    	List<User> userList = findAll();
    	for (User user : userList) {
    		if (user.getPhone().equals(phone)) {
    			b=false;
    		}
    	}
    	return b;
    }
    
    private String getPassword(int id) throws ServiceException {
    	String password;
		try {
			entityTransaction.beginTransaction(userDao);
			password = userDao.getPassword(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method create user", e);
		}
		finally {
			entityTransaction.end();
		}
		return password;
    }
}
