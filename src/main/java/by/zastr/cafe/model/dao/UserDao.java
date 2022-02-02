package by.zastr.cafe.model.dao;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.entity.User;

/**
 * The Interface UserDao.
 */
public interface UserDao {

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 * @returnList<User> 
	 */
	List<User> findAll() throws DaoException;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return Optional<User>
	 * @throws DaoException the dao exception
	 */
	Optional<User> findById(int id) throws DaoException;

	/**
	 * Find by login.
	 *
	 * @param login the login
	 * @return Optional<User>
	 * @throws DaoException the dao exception
	 */
	Optional<User> findByLogin(String login) throws DaoException;

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return List<User>
	 * @throws DaoException the dao exception
	 */
	List<User> findByName(String name) throws DaoException;

	/**
	 * Find by last name.
	 *
	 * @param name the name
	 * @return List<User>
	 * @throws DaoException the dao exception
	 */
	List<User> findByLastName(String name) throws DaoException;

	/**
	 * Find by role.
	 *
	 * @param name the name
	 * @return List<User>
	 * @throws DaoException the dao exception
	 */
	List<User> findByRole(String name) throws DaoException;

	/**
	 * Creates the.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean create(User user) throws DaoException;

	/**
	 * Creates the.
	 *
	 * @param user the user
	 * @param accountId the account id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean create(User user, int accountId) throws DaoException;

	/**
	 * Update.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean update(User user) throws DaoException;

	/**
	 * Sets the password.
	 *
	 * @param password the password
	 * @param login the login
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean setPassword(String password, String login) throws DaoException;

	/**
	 * Gets the password.
	 *
	 * @param id the id
	 * @return Password
	 * @throws DaoException the dao exception
	 */
	String getPassword(int id) throws DaoException;

	/**
	 * Delete.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean delete(User user) throws DaoException;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean delete(int id) throws DaoException;

	/**
	 * Find all deleted.
	 *
	 * @return List<User>
	 * @throws DaoException the dao exception
	 */
	List<User> findAllDeleted() throws DaoException;

}