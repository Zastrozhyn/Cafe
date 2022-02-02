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
import by.zastr.cafe.model.dao.DishDao;
import by.zastr.cafe.model.entity.Dish;

/**
 * class DishDaoImpl.
 *
 * @author A.Zastrozhyn
 */
public class DishDaoImpl extends AbstractDao<Dish> implements DishDao{
	private static final String SQL_FIND_ALL_DISH = "SELECT menu_id, type, name, description, price, weight, archive FROM menu "
			+ "WHERE archive=? ORDER BY type";
	private static final String SQL_FIND_DISH_BY_ID = "SELECT menu_id, type, name, description, price, weight, archive "
			+ "FROM menu WHERE menu_id=?";
	private static final String SQL_FIND_DISH_BY_NAME = "SELECT menu_id, type, name, description, price, weight, archive"
			+ " FROM menu WHERE name = ?";
	private static final String SQL_FIND_DISH_BY_TYPE = "SELECT menu_id, type, name, description, price, weight, archive"
			+ " FROM menu WHERE type = ? AND archive=?";
	private static final String SQL_CREATE_DISH = "INSERT INTO menu (menu_id, type, name, description, price, weight)"
			+ " VALUES (NULL,?,?,?,?,?)";
	private static final String SQL_UPDATE_DISH = "UPDATE menu SET  type=?, name=?, description=?, price=?, weight=?"
			+ " WHERE menu_id=?";
	private static final String SQL_DELETE_DISH = "UPDATE menu SET archive=? WHERE menu_id = ?";
	
	/**
	 * Instantiates a new dish dao impl.
	 */
	public DishDaoImpl() {
	}
	

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Dish> findAll() throws DaoException {
		List<Dish> dishList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_DISH)){
			statement.setString(1, "false");
			try (ResultSet result = statement.executeQuery()){ 
	            while (result.next()) {
	                Dish dish = buildDish(result);
	                dishList.add(dish);
	            }
			}
		} catch (SQLException e) {
			logger.error("\"Find all dish\" query has been failed", e);
            throw new DaoException("\"Find all dish\" query has been failed", e);
		}
		return dishList;
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 * @throws DaoException the dao exception
	 */
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
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
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
	
	/**
	 * Find by type.
	 *
	 * @param type the type
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Dish> findByType(String type) throws DaoException {
		List<Dish> dishList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_DISH_BY_TYPE)) {
			statement.setString(1, type);
			statement.setString(2, "false");
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
	

	/**
	 * Creates the Dish.
	 *
	 * @param dish the dish
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
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

	/**
	 * Update Dish.
	 *
	 * @param dish the dish
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
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
	
	/**
	 * Delete Dish.
	 *
	 * @param dish the dish
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean delete(Dish dish) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DISH)) {
            statement.setString(1, "true");
            statement.setInt(2, dish.getId());
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("\"Delete dish\" query has been failed", e);
            throw new DaoException("\"Delete dish\" query has been failed", e);
        }
		return (result > 0);
	}

	/**
	 * Delete Dish.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean delete(int id) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DISH)) {
            statement.setString(1, "true");
            statement.setInt(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("\"Delete dish\" query has been failed", e);
            throw new DaoException("\"Delete dish\" query has been failed", e);
        }
		return (result > 0);
	}
	
	/**
	 * Find deleted.
	 *
	 * @return the list
	 * @throws DaoException the dao exception
	 */
	@Override
	public List<Dish> findDeleted() throws DaoException {
		List<Dish> dishList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_DISH)){
			statement.setString(1, "true");
			try (ResultSet result = statement.executeQuery()){ 
	            while (result.next()) {
	                Dish dish = buildDish(result);
	                dishList.add(dish);
	            }
			}
		} catch (SQLException e) {
			logger.error("\"Find deleted dish\" query has been failed", e);
            throw new DaoException("\"Find dweleted dish\" query has been failed", e);
		}
		return dishList;
	}
	
	/**
	 * Restore Dish.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	@Override
	public boolean restore(int id) throws DaoException {
		int result;
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_DISH)) {
            statement.setString(1, "false");
            statement.setInt(2, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.info("\"Delete dish\" query has been failed", e);
            throw new DaoException("\"Delete dish\" query has been failed", e);
        }
		return (result > 0);
	}
	
     /**
      * Builds the dish.
      *
      * @param result the result
      * @return the dish
      * @throws SQLException the SQL exception
      */
     static Dish buildDish(ResultSet result) throws SQLException {
    	Dish dish = new Dish();
    	dish.setId(result.getInt(DISH_ID));
    	dish.setName(result.getString(DISH_NAME));
    	dish.setDescription(result.getString(DISH_DESCRIPTION));
    	dish.setPrice(BigDecimal.valueOf(result.getDouble(DISH_PRICE)));
    	dish.setType(result.getString(DISH_TYPE));
    	dish.setWeight(result.getInt(DISH_WEIGHT));
    	dish.setArchive(Boolean.parseBoolean(result.getString(ARCHIVE)));
    	return dish;
    }
}
