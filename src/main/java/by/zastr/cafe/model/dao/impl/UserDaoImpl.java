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
import by.zastr.cafe.model.dao.UserDao;
import by.zastr.cafe.model.entity.Account;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.entity.Account.AccountStatus;

/**
 * class UserDaoImpl
 * @author A.Zastrozhyn
 *
 */
public class UserDaoImpl extends AbstractDao<User> implements UserDao{
	private static final String SQL_FIND_ALL = "SELECT archive, user_id, name, last_name, phone, email, login, account_id, id, status,"
			+ " balance, active, role, order_history FROM users INNER JOIN accounts ON account_id = accounts.id WHERE archive=?";
	private static final String SQL_FIND_BY_ID = "SELECT archive, user_id, name, last_name, phone, email, login, account_id, id, "
			+ "status, balance, active, role, order_history FROM users INNER JOIN accounts ON accounts.id = account_id WHERE user_id=?";
	private static final String SQL_FIND_BY_LOGIN ="SELECT archive, user_id, name, last_name, phone, email, login, account_id, id, "
			+ "status, balance, active, role, order_history FROM users INNER JOIN accounts ON accounts.id = account_id WHERE login=?";;
	private static final String SQL_FIND_BY_NAME = "SELECT archive, user_id, name, last_name, phone, email, login, account_id, id, "
			+ "status, balance, active, role, order_history FROM users INNER JOIN accounts ON accounts.id = account_id WHERE name=?";
	private static final String SQL_FIND_BY_LASTNAME = "SELECT archive, user_id, name, last_name, phone, email, login, account_id, id, "
			+ "status, balance, active, role, order_history FROM users INNER JOIN accounts ON accounts.id = account_id WHERE last_name=?";
	private static final String SQL_FIND_BY_ROLE = "SELECT archive, user_id, name, last_name, phone, email, login, account_id, id, "
			+ "status, balance, active, role, order_history FROM users INNER JOIN accounts ON accounts.id = account_id WHERE role=?";
	private static final String SQL_CREATE_USER = "INSERT INTO users (user_id, name, last_name, phone, email, login, account_id,"
			+ " role) VALUES (NULL,?,?,?,?,?,?,?)";
	private static final String SQL_CREATE_USER_WITHOUT_ACCOUNT = "INSERT INTO users (user_id, name, last_name, phone, "
			+ "email, login, role) VALUES (NULL,??,?,?,?,?,?)";
	private static final String SQL_UPDATE_USER = "UPDATE users SET  name=?, last_name=?, phone=?, email=?, login=?,"
			+ "role=? WHERE user_id=?";
	private static final String SQL_SET_PASSWORD = "UPDATE users SET  password=? WHERE login=?";
	private static final String SQL_GET_PASSWORD = "SELECT password FROM users WHERE user_id=?";
	private static final String SQL_DELETE_USER = "UPDATE users SET archive=? WHERE user_id = ?";
	
	public UserDaoImpl() {
	}
	
	@Override
	public List<User> findAll() throws DaoException {
		List<User> userList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)){
            statement.setString(1, "false");
            try(ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    User user = buildUser(result);
                    userList.add(user);
                }
            }

		} catch (SQLException e) {
			logger.log(Level.ERROR,"\"Find all clients\" query has been failed", e);
            throw new DaoException("\"Find all clients\" query has been failed", e);
		}
		return userList;
	}

	@Override
	public Optional<User> findById(int id) throws DaoException {
		Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()){
                if(result.next()) {
                    user = Optional.of(buildUser(result));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Find user by id\" query has been failed", e);
            throw new DaoException("\"Find user by id\" query has been failed", e);
        }
		return user;
	}
	
	@Override
	public Optional<User> findByLogin(String login) throws DaoException {
		Optional<User> user = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet result = statement.executeQuery()){
                if(result.next()) {
                    user = Optional.of(buildUser(result));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Find user by id\" query has been failed", e);
            throw new DaoException("\"Find user by id\" query has been failed", e);
        }
		return user;
	}
	
	@Override
	public List<User> findByName(String name) throws DaoException {
		List<User> userList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_NAME)) {
			statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()){
	            while (result.next()) {
	                User user = buildUser(result);
	                userList.add(user);
	            }
            }
		} catch (SQLException e) {
			logger.log(Level.ERROR,"\"Find user\" query has been failed", e);
            throw new DaoException("\"Find user\" query has been failed", e);
		}
		return userList;
	}
	
	@Override
	public List<User> findByLastName(String name) throws DaoException {
		List<User> userList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_LASTNAME)) {
			statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()){
	            while (result.next()) {
	                User user = buildUser(result);
	                userList.add(user);
	            }
            }
		} catch (SQLException e) {
			logger.log(Level.ERROR,"\"Find user\" query has been failed", e);
            throw new DaoException("\"Find user\" query has been failed", e);
		}
		return userList;
	}
	
	@Override
	public List<User> findByRole(String name) throws DaoException {
		List<User> userList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ROLE)) {
			statement.setString(1, name);
            try (ResultSet result = statement.executeQuery()){
	            while (result.next()) {
	                User user = buildUser(result);
	                userList.add(user);
	            }
            }
		} catch (SQLException e) {
			logger.log(Level.ERROR,"\"Find user\" query has been failed", e);
            throw new DaoException("\"Find user\" query has been failed", e);
		}
		return userList;
	}

	@Override
	public boolean create(User user) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER_WITHOUT_ACCOUNT)) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getLogin());
			statement.setString(6, user.getRole().toString().toLowerCase());
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.log(Level.ERROR,"\"Create user\" query has been failed", e);
            throw new DaoException("\"Create user\" query has been failed", e);
		}	
		return (result > 0);
	}
	
	@Override
	public boolean create(User user, int accountId) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getLogin());
			statement.setInt(6, accountId);
			statement.setString(7, user.getRole().toString().toLowerCase());
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.log(Level.ERROR,"\"Create user\" query has been failed", e);
            throw new DaoException("\"Create user\" query has been failed", e);
		}	
		return (result > 0);
	}

	@Override
	public boolean update(User user) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getLogin());
			statement.setString(6, user.getRole().toString().toLowerCase());
			statement.setInt(7, user.getUserId());
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.log(Level.ERROR,"\"Update user\" query has been failed", e);
            throw new DaoException("\"Update user\" query has been failed", e);
		}	
		return (result > 0);
	}
	
	@Override
	public boolean setPassword(String password, String login) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_SET_PASSWORD)) {
			statement.setString(1, password);
			statement.setString(2, login);
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.log(Level.ERROR,"\"Update user\" query has been failed", e);
            throw new DaoException("\"Update user\" query has been failed", e);
		}	
		return (result > 0);
	}
	
	@Override
	public String getPassword(int id) throws DaoException {
		String password = "";
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_PASSWORD)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()){
                if(result.next()) {
                    password = result.getString(USER_PASSWORD);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Find user by id\" query has been failed", e);
            throw new DaoException("\"Find user by id\" query has been failed", e);
        }
		return password;
	}

	@Override
	public boolean delete(User user) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
        	statement.setString(1, "true");
            statement.setInt(2, user.getUserId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Delete user\" query has been failed", e);
            throw new DaoException("\"Delete user\" query has been failed", e);
        }
		return (result > 0);
	}

	@Override
	public boolean delete(int id) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER)) {
        	statement.setString(1, "true");
            statement.setInt(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Delete user\" query has been failed", e);
            throw new DaoException("\"Delete user\" query has been failed", e);
        }
		return (result > 0);
	}
	
	@Override
	public List<User> findAllDeleted() throws DaoException {
		List<User> userList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL)){
            statement.setString(1, "true");
            try(ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    User user = buildUser(result);
                    userList.add(user);
                }
            }

		} catch (SQLException e) {
			logger.log(Level.ERROR,"\"Find all clients\" query has been failed", e);
            throw new DaoException("\"Find all clients\" query has been failed", e);
		}
		return userList;
	}
	
    private User buildUser(ResultSet result) throws SQLException {
    	User user = new User();
    	user.setUserId(result.getInt(USER_ID));
    	user.setName(result.getString(USER_NAME));
    	user.setLastName(result.getString(USER_LAST_NAME));
    	user.setPhone(result.getString(USER_PHONE));
    	user.setEmail(result.getString(USER_EMAIL));
    	user.setLogin(result.getString(USER_LOGIN));
    	user.setArchive(result.getBoolean(ARCHIVE));
    	Account account = new Account();
       	account.setActive(Boolean.valueOf(result.getString(ACCOUNT_ACTIVE)));
       	account.setId(result.getInt(ACCOUNT_ID));
       	account.setBalance(result.getBigDecimal(ACCOUNT_BALANCE));
       	account.setStatus(AccountStatus.valueOf(result.getString(ACCOUNT_STATUS).toUpperCase()));
       	account.setOrderHistory(result.getBigDecimal(ORDER_HISTORY));
    	user.setRole(User.Role.valueOf(result.getString(USER_ROLE).toUpperCase()));
    	user.setAccount(account);
        return user;
    }

}
