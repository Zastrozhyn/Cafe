package by.zastr.cafe.model.dao;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.entity.User;

public interface UserDao {

	/**
	 * 
	 * @returnList<User>
	 * @throws DaoException
	 */
	List<User> findAll() throws DaoException;

	/**
	 * 
	 * @param id
	 * @return Optional<User>
	 * @throws DaoException
	 */
	Optional<User> findById(int id) throws DaoException;

	/**
	 * 
	 * @param login
	 * @return Optional<User>
	 * @throws DaoException
	 */
	Optional<User> findByLogin(String login) throws DaoException;

	/**
	 * 
	 * @param name
	 * @return List<User>
	 * @throws DaoException
	 */
	List<User> findByName(String name) throws DaoException;

	/**
	 * 
	 * @param name
	 * @return List<User>
	 * @throws DaoException
	 */
	List<User> findByLastName(String name) throws DaoException;

	/**
	 * 
	 * @param name
	 * @return List<User>
	 * @throws DaoException
	 */
	List<User> findByRole(String name) throws DaoException;

	/**
	 * 
	 * @param user
	 * @throws DaoException
	 */
	boolean create(User user) throws DaoException;

	/**
	 * 
	 * @param user
	 * @param accountId
	 * @throws DaoException
	 */
	boolean create(User user, int accountId) throws DaoException;

	/**
	 * 
	 * @param user
	 * @throws DaoException
	 */
	boolean update(User user) throws DaoException;

	/**
	 * 
	 * @param password
	 * @param login
	 * @throws DaoException
	 */
	boolean setPassword(String password, String login) throws DaoException;

	/**
	 * 
	 * @param id
	 * @return Password
	 * @throws DaoException
	 */
	String getPassword(int id) throws DaoException;

	/**
	 * 
	 * @param user
	 * @throws DaoException
	 */
	boolean delete(User user) throws DaoException;

	/**
	 * 
	 * @param id
	 * @throws DaoException
	 */
	boolean delete(int id) throws DaoException;

	/**
	 * 
	 * @return List<User>
	 * @throws DaoException
	 */
	List<User> findAllDeleted() throws DaoException;

}