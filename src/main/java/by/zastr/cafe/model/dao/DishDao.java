package by.zastr.cafe.model.dao;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.entity.Dish;

/**
 * interface DishDao
 * @author A.Zastrozhyn
 *
 */
public interface DishDao {

	/**
	 * 
	 * @return List<Dish>
	 * @throws DaoException
	 */
	List<Dish> findAll() throws DaoException;

	/**
	 * 
	 * @param id
	 * @return Optional<Dish>
	 * @throws DaoException
	 */
	Optional<Dish> findById(int id) throws DaoException;

	/**
	 * 
	 * @param name
	 * @return List<Dish>
	 * @throws DaoException
	 */
	List<Dish> findByName(String name) throws DaoException;

	/**
	 * 
	 * @param type
	 * @return List<Dish>
	 * @throws DaoException
	 */
	List<Dish> findByType(String type) throws DaoException;

	/**
	 * 
	 * @param dish
	 * @throws DaoException
	 */
	boolean create(Dish dish) throws DaoException;

	/**
	 * 
	 * @param dish
	 * @throws DaoException
	 */
	boolean update(Dish dish) throws DaoException;

	/**
	 * 
	 * @param dish
	 * @return
	 * @throws DaoException
	 */
	boolean delete(Dish dish) throws DaoException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	boolean delete(int id) throws DaoException;

	/**
	 * 
	 * @return List<Dish>
	 * @throws DaoException
	 */
	List<Dish> findDeleted() throws DaoException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	boolean restore(int id) throws DaoException;

}