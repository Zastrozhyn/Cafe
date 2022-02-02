package by.zastr.cafe.model.dao;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.entity.Account;

/**
 * interface AccountDao.
 *
 * @author A.Zastrozhyn
 */
public interface AccountDao {

	/**
	 * Find all.
	 *
	 * @return List<Account>
	 * @throws DaoException the dao exception
	 */
	List<Account> findAll() throws DaoException;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return Optional<Account>
	 * @throws DaoException the dao exception
	 */
	Optional<Account> findById(int id) throws DaoException;

	/**
	 * Creates the.
	 *
	 * @param account the account
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean create(Account account) throws DaoException;

	/**
	 * Update.
	 *
	 * @param account the account
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean update(Account account) throws DaoException;

	/**
	 * Delete.
	 *
	 * @param account the account
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean delete(Account account) throws DaoException;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean delete(int id) throws DaoException;

	/**
	 * Find by active.
	 *
	 * @param active the active
	 * @return Optional<Account>
	 * @throws DaoException the dao exception
	 */
	Optional<Account> findByActive(boolean active) throws DaoException;

	/**
	 * Creates the new default account.
	 *
	 * @return Default Account
	 * @throws DaoException the dao exception
	 */
	Account createNewDefaultAccount() throws DaoException;

}