package by.zastr.cafe.model.dao;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.entity.Account;

/**
 * interface AccountDao
 * @author A.Zastrozhyn
 *
 */
public interface AccountDao {

	/**
	 * 
	 * @return List<Account>
	 * @throws DaoException
	 */
	List<Account> findAll() throws DaoException;

	/**
	 * 
	 * @param id
	 * @return Optional<Account>
	 * @throws DaoException
	 */
	Optional<Account> findById(int id) throws DaoException;

	/**
	 * 
	 * @param account
	 * @throws DaoException
	 */
	boolean create(Account account) throws DaoException;

	/**
	 * 
	 * @param account
	 * @throws DaoException
	 */
	boolean update(Account account) throws DaoException;

	/**
	 * 
	 * @param account
	 * @throws DaoException
	 */
	boolean delete(Account account) throws DaoException;

	/**
	 * 
	 * @param id
	 * @throws DaoException
	 */
	boolean delete(int id) throws DaoException;

	/**
	 * 
	 * @param active
	 * @return Optional<Account>
	 * @throws DaoException
	 */
	Optional<Account> findByActive(boolean active) throws DaoException;

	/**
	 * 
	 * @return Default Account
	 * @throws DaoException
	 */
	Account createNewDefaultAccount() throws DaoException;

}