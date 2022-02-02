package by.zastr.cafe.model.dao.impl;

import static by.zastr.cafe.model.dao.ColumnName.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.dao.AbstractDao;
import by.zastr.cafe.model.dao.AccountDao;
import by.zastr.cafe.model.entity.Account;
import by.zastr.cafe.model.entity.Account.AccountStatus;

/**
 * class AccountDaoImpl
 * @author A.Zastrozhyn
 *
 */
public class AccountDaoImpl extends AbstractDao<Account> implements AccountDao{
	private static final String SQL_FIND_ALL_ACCOUNT = "SELECT id, status, balance, active, order_history FROM accounts";
	private static final String SQL_FIND_ACCOUNT_BY_ID = "SELECT id, status, balance, active, order_history FROM accounts WHERE id=?";
	private static final String SQL_FIND_ACCOUNT_BY_ACTIVE = "SELECT id, status, balance, active, order_history FROM accounts WHERE active=?";
	private static final String SQL_CREATE_ACCOUNT = "INSERT INTO accounts (id, status, balance, active)"
			+ " VALUES (NULL,?,?,?)";
	private static final String SQL_CREATE_DEFAULT_ACCOUNT = "INSERT INTO accounts (balance)"
			+ " VALUES (?)";
	private static final String SQL_UPDATE_ACCOUNT = "UPDATE accounts SET  status=?, balance=?, active=?, order_history=? WHERE id=?";
	private static final String SQL_DELETE_ACCOUNT = "DELETE FROM accounts WHERE id = ?";
	private static final String SQL_LAST_INSERT_ID = "SELECT LAST_INSERT_ID() AS lastid";
	private static final int MONEY = 0;
	
	@Override
	public List<Account> findAll() throws DaoException {
		List<Account> accountList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ACCOUNT); 
				ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Account account = buildAccount(result);
                accountList.add(account);
            }
		} catch (SQLException e) {
			logger.log(Level.ERROR,"\"Find all account\" query has been failed", e);
            throw new DaoException("\"Find all account\" query has been failed", e);
		}
		return accountList;
	}

	@Override
	public Optional<Account> findById(int id) throws DaoException {
		Optional<Account> account = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()){
                if(result.next()) {
                    account = Optional.of(buildAccount(result));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Find account by id\" query has been failed", e);
            throw new DaoException("\"Find account by id\" query has been failed", e);
        }
		return account;
	}

	@Override
	public boolean create(Account account) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ACCOUNT)) {
			statement.setString(1, account.getStatus().toString().toLowerCase());
			statement.setBigDecimal(2, account.getBalance());
			statement.setString(3, String.valueOf(account.isActive()));
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.error("\"Create account\" query has been failed", e);
            throw new DaoException("\"Create account\" query has been failed", e);
		}	
		return result > 0;
	}

	@Override
	public boolean update(Account account) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ACCOUNT)) {
			statement.setString(1, account.getStatus().toString().toLowerCase());
			statement.setBigDecimal(2, account.getBalance());
			statement.setString(3, String.valueOf(account.isActive()));
			statement.setBigDecimal(4, account.getOrderHistory());
			statement.setInt(5, account.getId());
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.error("\"Update account\" query has been failed", e);
            throw new DaoException("\"Update account\" query has been failed", e);
		}	
		return result > 0;
	}

	@Override
	public boolean delete(Account account) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ACCOUNT)) {
            statement.setInt(1, account.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Delete account\" query has been failed", e);
            throw new DaoException("\"Delete account\" query has been failed", e);
        }
		return result > 0;
	}

	@Override
	public boolean delete(int id) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ACCOUNT)) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Delete account\" query has been failed", e);
            throw new DaoException("\"Delete account\" query has been failed", e);
        }
		return (result > 0);
	}
	
	@Override
	public Optional<Account> findByActive(boolean active) throws DaoException {
		Optional<Account> account = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ACCOUNT_BY_ACTIVE)) {
            statement.setString(1, String.valueOf(active));
            try (ResultSet result = statement.executeQuery()){
                if(result.next()) {
                    account = Optional.of(buildAccount(result));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Find account by active\" query has been failed", e);
            throw new DaoException("\"Find account by active\" query has been failed", e);
        }
		return account;
	}
	
	@Override
	public Account createNewDefaultAccount() throws DaoException {
		int id = 0;
		try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_DEFAULT_ACCOUNT)) {
	            statement.setInt(1, MONEY);
	            statement.executeUpdate();
	    } catch (SQLException e) {
            logger.error("\"Create account\" query has been failed", e);
            throw new DaoException("\"Create account\" query has been failed", e);
		}
		try (PreparedStatement statement = connection.prepareStatement(SQL_LAST_INSERT_ID);
				ResultSet result = statement.executeQuery()) {
			if(result.next()) {
				id = result.getInt(LAST_ID);
			}
		} catch (SQLException e) {
            logger.error("\"Create account\" query has been failed", e);
            throw new DaoException("\"Create account\" query has been failed", e);
		}
		Optional<Account> account = findById(id);
		if (!account.isPresent()) {
            logger.error("\"Create account\" query has been failed");
            throw new DaoException("\"Create account\" query has been failed");
		}
		return account.get();
	}
	
    private Account buildAccount(ResultSet result) throws SQLException {
       	Account account = new Account();
       	account.setId(result.getInt(ACCOUNT_ID));
       	account.setActive(Boolean.valueOf(result.getString(ACCOUNT_ACTIVE)));
       	account.setBalance(result.getBigDecimal(ACCOUNT_BALANCE));
       	account.setOrderHistory(result.getBigDecimal(ORDER_HISTORY));
       	account.setStatus(AccountStatus.valueOf(result.getString(ACCOUNT_STATUS).toUpperCase()));
       	return account;
   }

}
