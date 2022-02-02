package by.zastr.cafe.model.service;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.Account;

public interface AccountService {

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 * @throws ServiceException the service exception
	 */
	Optional<Account> findById(int id) throws ServiceException;

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<Account> findAll() throws ServiceException;

	/**
	 * Delete account.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean delete(int id) throws ServiceException;

	/**
	 * Creates the new default account.
	 *
	 * @return default Account
	 * @throws ServiceException the service exception
	 */
	Account CreateNewDefaultAccount() throws ServiceException;

	/**
	 * Update account.
	 *
	 * @param account the account
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean update(Account account) throws ServiceException;

}