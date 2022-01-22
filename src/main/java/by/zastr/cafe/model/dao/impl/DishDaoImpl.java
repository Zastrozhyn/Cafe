package by.zastr.cafe.model.dao.impl;

import static by.zastr.cafe.model.dao.ColumnName.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.dao.AbstractDao;
import by.zastr.cafe.model.entity.Dish;

public class DishDaoImpl extends AbstractDao<Dish>{
	private static final String SQL_FIND_ALL_DISH = "SELECT menu_id, type, name, description, price, weight FROM menu "
			+ "ORDER BY type";
	private static final String SQL_FIND_DISH_BY_ID = "SELECT menu_id, type, name, description, price, weight "
			+ "FROM menu WHERE menu_id=?";
	private static final String SQL_FIND_DISH_BY_NAME = "SELECT menu_id, type, name, description, price, weight"
			+ " FROM menu WHERE name = ?";
	private static final String SQL_FIND_DISH_BY_TYPE = "SELECT menu_id, type, name, description, price, weight"
			+ " FROM menu WHERE type = ?";
	private static final String SQL_CREATE_DISH = "INSERT INTO menu (menu_id, type, name, description, price, weight)"
			+ " VALUES (NULL,?,?,?,?,?)";
	private static final String SQL_UPDATE_DISH = "UPDATE menu SET  type=?, name=?, description=?, price=?, weight=?"
			+ " WHERE menu_id=?";
	private static final String SQL_DELETE_DISH = "DELETE FROM menu WHERE menu_id = ?";
	
	public DishDaoImpl() {
	}
	

	@Override
	public List<Dish> findAll() throws DaoException {
		List<Dish> dishList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_DISH); 
				ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                Dish dish = buildDish(result);
                dishList.add(dish);
            }
		} catch (SQLException e) {
			logger.error("\"Find all dish\" query has been failed", e);
            throw new DaoException("\"Find all dish\" query has been failed", e);
		}
		return dishList;
	}

	@Override
	public Optional<Dish> findById(int id) throws DaoException {
		Optional<Dish> dish = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_DISH_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()){
                if(result.next()) {
                    dish = Optional.of(buildDish(result));
                }
            }
        } catch (SQLException e) {
            logger.info("\"Find dish by id\" query has been failed", e);
            throw new DaoException("\"Find dish by id\" query has been failed", e);
        }
		return dish;
	}
	
	public List<Dish> findByName(String name) throws DaoException {
		List<Dish> dishList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_DISH_BY_NAME)) {
			statement.setString(1, name);
			try (ResultSet result = statement.executeQuery()){
	            while (result.next()) {
	                Dish dish = buildDish(result);
	                dishList.add(dish);
	            }
            }
		} catch (SQLException e) {
			logger.error("\"Find dish\" query has been failed", e);
            throw new DaoException("\"Find dish\" query has been failed", e);
		}
		return dishList;
	}
	public List<Dish> findByType(String type) throws DaoException {
		List<Dish> dishList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_DISH_BY_TYPE)) {
			statement.setString(1, type);
			try (ResultSet result = statement.executeQuery()){
	            while (result.next()) {
	                Dish dish = buildDish(result);
	                dishList.add(dish);
	            }
            }
		} catch (SQLException e) {
			logger.error("\"Find dish\" query has been failed", e);
            throw new DaoException("\"Find dish\" query has been failed", e);
		}
		return dishList;
	}
	

	@Override
	public boolean create(Dish dish) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_DISH)) {
			statement.setString(1, dish.getType().toString().toLowerCase());
			statement.setString(2, dish.getName());
			statement.setString(3, dish.getDescription());
			statement.setBigDecimal(4, dish.getPrice());
			statement.setInt(5, dish.getWeight());
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.error("\"Create dish\" query has been failed", e);
            throw new DaoException("\"Create dish\" query has been failed", e);
		}	
		return (result > 0);
	}

	@Override
	public boolean update(Dish dish) throws DaoException {
		int result;
		try (PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_DISH)) {
			statement.setString(1, dish.getType().toString().toLowerCase());
			statement.setString(2, dish.getName());
			statement.setString(3, dish.getDescription());
			statement.setBigDecimal(4, dish.getPrice());
			statement.setInt(5, dish.getWeight());
			statement.setInt(6, dish.getId());
			result = statement.executeUpdate();
		} catch (SQLException e) {
            logger.error("\"Update dish\" query has been failed", e);
            throw new DaoException("\"Update dish\" query has been failed", e);
		}	
		return (result > 0);
	}
	
	@Override
	public boolean delete(Dish dish) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DISH)) {
            statement.setInt(1, dish.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("\"Delete dish\" query has been failed", e);
            throw new DaoException("\"Delete dish\" query has been failed", e);
        }
		return (result > 0);
	}

	@Override
	public boolean delete(int id) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DISH)) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("\"Delete dish\" query has been failed", e);
            throw new DaoException("\"Delete dish\" query has been failed", e);
        }
		return (result > 0);
	}
	
     static Dish buildDish(ResultSet result) throws SQLException {
    	Dish dish = new Dish();
    	dish.setId(result.getInt(DISH_ID));
    	dish.setName(result.getString(DISH_NAME));
    	dish.setDescription(result.getString(DISH_DESCRIPTION));
    	dish.setPrice(BigDecimal.valueOf(result.getDouble(DISH_PRICE)));
    	dish.setType(result.getString(DISH_TYPE));
    	dish.setWeight(result.getInt(DISH_WEIGHT));
    	return dish;
    }
}
