package by.zastr.cafe.model.dao.impl;

import static by.zastr.cafe.model.dao.ColumnName.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.Level;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.connection.ConnectionPool;
import by.zastr.cafe.model.dao.AbstractDao;
import by.zastr.cafe.model.entity.CafeOrder;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.entity.CafeOrder.PaymentType;

public class OrderDaoImpl extends AbstractDao<CafeOrder>{
	private static final String SQL_FIND_ALL_DISH_IN_ORDER = "SELECT menu_id FROM order_dishes WHERE order_id=?";
	private static final String SQL_FIND_ALL_ORDER = "SELECT order_id, user_id, descritpion, comment, date, time,"
			+ " payment_type, paid FROM orders";
	private static final String SQL_FIND_ORDER_BY_ID = "SELECT order_id, user_id, descritpion, comment, date, time,"
			+ "payment_type, paid FROM orders WHERE order_id=?";
	private static final String SQL_CREATE_ORDER = "INSERT INTO orders (order_id, user_id, descritpion, comment, date, time,"
			+ "payment_type, paid) VALUES (NULL,?,?,?,?,?,?,?)";
	private static final String SQL_CREATE_ORDER_DISH = "INSERT INTO order_dishes (order_id, menu_id)"
			+ "VALUES (?,?)";	
	private static final String SQL_UPDATE_ORDER = "UPDATE users SET user_id=?, descritpion=?, comment=?, date=?, time=?"
			+ "payment_type=?, paid=? WHERE order_id = ?"; 
	private static final String SQL_DELETE_ORDER = "DELETE FROM orders WHERE order_id = ?";
	private static final String SQL_TOTAL_COST = "SELECT SUM(price) FROM menu WHERE menu_id IN "
			+ "(SELECT menu_id FROM order_dishes WHERE order_id =?) ";
	private static final String SQL_LAST_INSERT_ID = "SELECT LAST_INSERT_ID() AS lastid";
	
	public OrderDaoImpl() {
	}


	@Override
	public List<CafeOrder> findAll() throws DaoException {
		List<CafeOrder> orderList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDER);
				ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                CafeOrder order = buildOrder(result);
                orderList.add(order);
            }
		} catch (SQLException e) {
			logger.log(Level.ERROR,"\"Find all order\" query has been failed", e);
            throw new DaoException("\"Find all order\" query has been failed", e);
		}
		return orderList;
	}

	@Override
	public Optional<CafeOrder> findById(int id) throws DaoException {
		Optional<CafeOrder> order = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()){
                if(result.next()) {
                    order = Optional.of(buildOrder(result));
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Find order by id\" query has been failed", e);
            throw new DaoException("\"Find order by id\" query has been failed", e);
        }
		return order;
	}

	@Override
	public boolean create(CafeOrder order) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER)) {
			statement.setInt(1, order.getUserId());
			statement.setString(2, order.getDescription());
			statement.setString(3, order.getComment());
			statement.setString(4, order.getDate().toString());
			statement.setString(5, order.getTime().toString());
			statement.setString(6, order.getPayment().toString().toLowerCase());
			statement.setString(7, String.valueOf(order.isPaid()));
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.log(Level.ERROR,"\"Create order\" query has been failed", e);
            throw new DaoException("\"Create order\" query has been failed", e);
		}	
		return (result > 0);
	}
	
	public CafeOrder createOrder(CafeOrder order) throws DaoException {
		int id = 0;
		try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER)) {
			statement.setInt(1, order.getUserId());
			statement.setString(2, order.getDescription());
			statement.setString(3, order.getComment());
			statement.setString(4, order.getDate().toString());
			statement.setString(5, order.getTime().toString());
			statement.setString(6, order.getPayment().toString().toLowerCase());
			statement.setString(7, String.valueOf(order.isPaid()));
			statement.executeUpdate();
		} catch (SQLException e) {
            logger.log(Level.ERROR,"\"Create order\" query has been failed", e);
            throw new DaoException("\"Create order\" query has been failed", e);
		}	
		try (PreparedStatement statement = connection.prepareStatement(SQL_LAST_INSERT_ID);
				ResultSet result = statement.executeQuery()) {
			if(result.next()) {
				id = result.getInt(LAST_ID);
			}
		}catch (SQLException e) {
            logger.log(Level.ERROR,"\"Create order\" query has been failed", e);
            throw new DaoException("\"Create order\" query has been failed", e);
		}
		for (Dish dish : order.getOrderList()) {
			createOrderDish(id, dish.getId());
		}
		Optional<CafeOrder> optionaOrder = findById(id);
		if (optionaOrder.isPresent()) {
			order = optionaOrder.get();
		}
		return order;
	}

	@Override
	public boolean update(CafeOrder order) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER)) {
			statement.setInt(1, order.getUserId());
			statement.setString(2, order.getDescription());
			statement.setString(3, order.getComment());
			statement.setString(4, order.getDate().toString());
			statement.setString(5, order.getTime().toString());
			statement.setString(6, order.getPayment().toString().toLowerCase());
			statement.setString(7, String.valueOf(order.isPaid()));
			statement.setInt(8, order.getId());
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.log(Level.ERROR,"\"Update order\" query has been failed", e);
            throw new DaoException("\"Update order\" query has been failed", e);
		}	
		return (result > 0);
	}

	@Override
	public boolean delete(CafeOrder order) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)) {
            statement.setInt(1, order.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Delete order\" query has been failed", e);
            throw new DaoException("\"Delete order\" query has been failed", e);
        }
		return (result > 0);
	}

	@Override
	public boolean delete(int id) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER)) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR,"\"Delete order\" query has been failed", e);
            throw new DaoException("\"Delete order\" query has been failed", e);
        }
		return (result > 0);
	}
	
	public BigDecimal totalCost(int orderId) throws DaoException{
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		try (PreparedStatement statement = connection.prepareStatement(SQL_TOTAL_COST)) {
			statement.setInt(1, orderId);
            try (ResultSet result = statement.executeQuery()){
                if(result.next()) {
                    totalPrice = result.getBigDecimal(1);
                }
            }
		} catch (SQLException e) {
			logger.log(Level.ERROR,"\"Find total price\" query has been failed", e);
            throw new DaoException("\"Find total price\" query has been failed", e);
		}
		return totalPrice;
	}
	public boolean createOrderDish(int orderId, int dishId) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER_DISH)) {
			statement.setInt(1, orderId);
			statement.setInt(2, dishId);
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.log(Level.ERROR,"\"Create order_dish\" query has been failed", e);
            throw new DaoException("\"Create order_dish\" query has been failed", e);
		}	
		return (result > 0);
	}
    private CafeOrder buildOrder(ResultSet result) throws SQLException, DaoException {
    	CafeOrder order = new CafeOrder();
    	order.setId(result.getInt(ORDER_ID));
    	order.setUserId(result.getInt(USER_ID));
    	order.setComment(result.getString(ORDER_COMMENT));
    	order.setPaid(Boolean.parseBoolean(result.getString(ORDER_PAID)));
    	order.setPayment(PaymentType.valueOf(result.getString(ORDER_PAYMENT_TYPE).toUpperCase()));
    	order.setDate(LocalDate.parse(result.getString(ORDER_DATE)));
    	order.setTime(LocalTime.parse(result.getString(ORDER_TIME)));
    	order.setOrderList(getAllDishInOrder(result.getInt(ORDER_ID)));
    	order.setDescription(ORDER_DESCRIPTION);
    	order.setTotalCost(totalCost(result.getInt(ORDER_ID)));
        return order;
    }
    
    private List<Dish> getAllDishInOrder (int id) throws SQLException, DaoException{
    	DishDaoImpl dishDao = new DishDaoImpl();
    	dishDao.setConnection(ConnectionPool.getInstance().takeConnection());
		List<Dish> dishList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_DISH_IN_ORDER)) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()){
	            while (result.next()) {
	                Dish dish = dishDao.findById(result.getInt(1)).get();
	                dishList.add(dish);
	            }
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR,"\"Find all dish in order\" query has been failed", e);
            throw new SQLException("\"Find all dish in order\" query has been failed", e);
		}
		finally {
			dishDao.closeConnection();
		}
		return dishList;
    }
    
}
