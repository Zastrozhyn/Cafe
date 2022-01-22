package by.zastr.cafe.model.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.dao.EntityTransaction;
import by.zastr.cafe.model.dao.impl.AccountDaoImpl;
import by.zastr.cafe.model.entity.Account;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.service.CafeService;


public class AccountServiceImpl implements CafeService<Account> {
	private static AccountServiceImpl instance = new AccountServiceImpl();;
	private AccountDaoImpl accountDao;
	private EntityTransaction entityTransaction;	
	private AccountServiceImpl() {
		accountDao = new AccountDaoImpl();
		entityTransaction = new EntityTransaction();
	}

	public static AccountServiceImpl getInstance() {
        return instance;
	}
		
	public Account CreateNewDefaultAccount() throws ServiceException {
		Account account = new Account();
		try {
			entityTransaction.beginTransaction(accountDao);
			account = accountDao.createNewDefaultAccount();
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method get id of new default account", e);
		}
		finally {
			entityTransaction.end();
		} 
		return account;
	}
	
	@Override
	public Optional<Account> findById(int id) throws ServiceException{
		Optional<Account> account = Optional.empty();
		try {
			entityTransaction.beginTransaction(accountDao);
			account = accountDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding account by id", e);
		}
		finally {
			entityTransaction.end();
		}
		return account;
	}

	@Override
	public List<Account> findAll() throws ServiceException {
		List<Account> accountList = new ArrayList<Account>();
		entityTransaction.beginTransaction(accountDao);
		try {
			accountList = accountDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method find all account", e);
		}
		finally {
			entityTransaction.end();
		}
		return accountList;
	}

	@Override
	public boolean delete(int id) throws ServiceException {
		boolean b = false;
		try {
			entityTransaction.beginTransaction(accountDao);
			b = accountDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method delete order", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;
	}
	
	public boolean update(Account account) throws ServiceException {
		boolean b = false;
		try {
			entityTransaction.beginTransaction(accountDao);
			b = accountDao.update(account);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method update account", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;	
	}

}
