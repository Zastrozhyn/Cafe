package by.zastr.cafe.model.service;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.Dish;

public interface DishService {

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<Dish> findAll() throws ServiceException;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 * @throws ServiceException the service exception
	 */
	Optional<Dish> findById(int id) throws ServiceException;

	/**
	 * Delete Dish.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean delete(int id) throws ServiceException;

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return List<Dish>
	 * @throws ServiceException the service exception
	 */
	List<Dish> findByName(String name) throws ServiceException;

	/**
	 * Find by type.
	 *
	 * @param type the type
	 * @return List<Dish>
	 * @throws ServiceException the service exception
	 */
	List<Dish> findByType(String type) throws ServiceException;

	/**
	 * Find deleted.
	 *
	 * @return List<Dish>
	 * @throws ServiceException the service exception
	 */
	List<Dish> findDeleted() throws ServiceException;

	/**
	 * restore from archive.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean restore(int id) throws ServiceException;

	/**
	 * Update Dish.
	 *
	 * @param dish the dish
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean update(Dish dish) throws ServiceException;

	/**
	 * Creates the.
	 *
	 * @param name the name
	 * @param weight the weight
	 * @param price the price
	 * @param description the description
	 * @param type the type
	 * @param locale the locale
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	String create(String name, String weight, String price, String description, String type, String locale)
			throws ServiceException;

	/**
	 * Update Dish.
	 *
	 * @param id the id
	 * @param name the name
	 * @param weight the weight
	 * @param price the price
	 * @param description the description
	 * @param type the type
	 * @param locale the locale
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	String update(int id, String name, String weight, String price, String description, String type, String locale)
			throws ServiceException;

}