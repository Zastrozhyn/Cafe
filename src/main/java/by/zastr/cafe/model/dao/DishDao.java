package by.zastr.cafe.model.dao;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.entity.Dish;

/**
 * interface DishDao.
 *
 * @author A.Zastrozhyn
 */
public interface DishDao {

	/**
	 * Find all.
	 *
	 * @return List<Dish>
	 * @throws DaoException the dao exception
	 */
	List<Dish> findAll() throws DaoException;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return Optional<Dish>
	 * @throws DaoException the dao exception
	 */
	Optional<Dish> findById(int id) throws DaoException;

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return List<Dish>
	 * @throws DaoException the dao exception
	 */
	List<Dish> findByName(String name) throws DaoException;

	/**
	 * Find by type.
	 *
	 * @param type the type
	 * @return List<Dish>
	 * @throws DaoException the dao exception
	 */
	List<Dish> findByType(String type) throws DaoException;

	/**
	 * Creates the.
	 *
	 * @param dish the dish
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean create(Dish dish) throws DaoException;

	/**
	 * Update.
	 *
	 * @param dish the dish
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean update(Dish dish) throws DaoException;

	/**
	 * Delete.
	 *
	 * @param dish the dish
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean delete(Dish dish) throws DaoException;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean delete(int id) throws DaoException;

	/**
	 * Find deleted.
	 *
	 * @return List<Dish>
	 * @throws DaoException the dao exception
	 */
	List<Dish> findDeleted() throws DaoException;

	/**
	 * Restore.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean restore(int id) throws DaoException;

}