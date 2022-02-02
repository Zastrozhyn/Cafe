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
import by.zastr.cafe.util.MessageManager;
import by.zastr.cafe.util.PasswordEncryptor;
import by.zastr.cafe.util.impl.InputValidatorImpl;

/**
 * class UserServiceImpl.
 *
 * @author A.Zastrozhyn
 */
public class UserServiceImpl implements CafeService<User> {
	private static UserServiceImpl instance = new UserServiceImpl();;
	private UserDaoImpl userDao;
	private EntityTransaction entityTransaction;
	private static final User.Role DEFAULT_USER_ROLE = User.Role.CLIENT; 
	
	private UserServiceImpl() {
		entityTransaction = new EntityTransaction();
		userDao = new UserDaoImpl();
	}

	/**
	 * Gets the single instance of UserServiceImpl.
	 *
	 * @return UserServiceImpl
	 */
	public static UserServiceImpl getInstance() {
        return instance;
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
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

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 * @throws ServiceException the service exception
	 */
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
	
	/**
	 * Delete User.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
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
	
	/**
	 * Find all deleted.
	 *
	 * @return List<User>
	 * @throws ServiceException the service exception
	 */
	public List<User> findAllDeleted() throws ServiceException{
		List<User> userList = new ArrayList<User>();
		entityTransaction.beginTransaction(userDao);
		try {
			userList = userDao.findAllDeleted();
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding users", e);
		}
		finally {
			entityTransaction.end();
		}
		return userList;
	}
	
	/**
	 * Find by login.
	 *
	 * @param login the login
	 * @return Optional<User>
	 * @throws ServiceException the service exception
	 */
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
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return List<User>
	 * @throws ServiceException the service exception
	 */
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
	
	/**
	 * Find by last name.
	 *
	 * @param name the name
	 * @return List<User>
	 * @throws ServiceException the service exception
	 */
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
	
	/**
	 * Find by role.
	 *
	 * @param role the role
	 * @return List<User>
	 * @throws ServiceException the service exception
	 */
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
	
	/**
	 * Update User.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
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
	
	
	/**
	 * Edits User.
	 *
	 * @param userId the user id
	 * @param name the name
	 * @param lastName the last name
	 * @param phone the phone
	 * @param email the email
	 * @param locale the locale
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	public String edit( int userId, String name, String lastName, String phone, String email, String locale) throws ServiceException {
		InputValidator validator = InputValidatorImpl.getInstance();
		MessageManager messageManager = MessageManager.defineLocale(locale);
		if (!validator.isCorrectName(name)) {
			return messageManager.getMessage(UserMessage.WRONG_NAME);
		}
		if (!validator.isCorrectName(lastName)) {
			return messageManager.getMessage(UserMessage.WRONG_NAME);
		}

		if (!validator.isCorrectEmail(email)) {
			return messageManager.getMessage(UserMessage.WRONG_EMAIL);
		}
		
		User user;
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
		return messageManager.getMessage(UserMessage.SUCCESSFUL);	
	}
	
	/**
	 * Registration.
	 *
	 * @param name the name
	 * @param lastName the last name
	 * @param phone the phone
	 * @param login the login
	 * @param password the password
	 * @param confirmPassword the confirm password
	 * @param email the email
	 * @param locale the locale
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	public String registration(String name, String lastName, String phone, String login, 
			String password, String confirmPassword, String email, String locale) throws ServiceException {
		InputValidator validator = InputValidatorImpl.getInstance();
		MessageManager messageManager = MessageManager.defineLocale(locale);
		if (!validator.isCorrectName(name)) {
			return messageManager.getMessage(UserMessage.WRONG_NAME);
		}
		if (!validator.isCorrectName(lastName)) {
			return messageManager.getMessage(UserMessage.WRONG_NAME);
		}
		if (!validator.isCorrectEmail(email)) {
			return messageManager.getMessage(UserMessage.WRONG_EMAIL);
		}
		if (!validator.isCorrectPassword(password)) {
			return messageManager.getMessage(UserMessage.WRONG_PASSWORD);
		}
		if (!validator.arePasswordsEqual(password,confirmPassword)) {
			return messageManager.getMessage(UserMessage.WRONG_CONFIRM_PASSWORD);
		}
		if(!isUniqueLogin(login)) {
			return messageManager.getMessage(UserMessage.NOT_UNIQUE_LOGIN);
		}
		if(!isUniquePhone(phone)) {
			return messageManager.getMessage(UserMessage.WRONG_PHONE);
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
		return messageManager.getMessage(UserMessage.REGISTRATION_SUCCESSFUL);	
	}
	
	/**
	 * Login.
	 *
	 * @param login the login
	 * @param password the password
	 * @return Optional<User>
	 * @throws ServiceException the service exception
	 */
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
	
	/**
	 * Change password.
	 *
	 * @param userId the user id
	 * @param login the login
	 * @param password the password
	 * @param newPassword the new password
	 * @param confirmPassword the confirm password
	 * @param locale the locale
	 * @return String result
	 * @throws ServiceException the service exception
	 */
	public String changePassword (int userId, String login, String password, String newPassword, String confirmPassword, String locale) throws ServiceException {
		InputValidator validator = InputValidatorImpl.getInstance();
		MessageManager messageManager = MessageManager.defineLocale(locale);
		if (!BCrypt.checkpw(password,getPassword(userId))) {
			return messageManager.getMessage(UserMessage.WRONG_PASSWORD);	
		}
		if (!validator.isCorrectPassword(newPassword)) {
			return messageManager.getMessage(UserMessage.WRONG_PASSWORD);
		}
		if (!validator.arePasswordsEqual(newPassword,confirmPassword)) {
			return messageManager.getMessage(UserMessage.WRONG_CONFIRM_PASSWORD);
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
		return messageManager.getMessage(UserMessage.SUCCESSFUL);
	}
	 
	/**
	 * Checks if is unique login.
	 *
	 * @param login the login
	 * @return boolean is Unique Login
	 * @throws ServiceException the service exception
	 */
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
    
    /**
     * Checks if is unique phone.
     *
     * @param phone the phone
     * @return boolean is Unique Phone
     * @throws ServiceException the service exception
     */
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
    
    /**
     * 
     * @param id
     * @return password
     * @throws ServiceException
     */
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
