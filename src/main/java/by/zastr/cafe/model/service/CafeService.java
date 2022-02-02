package by.zastr.cafe.model.service;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.CafeEntity;

/**
 * interface CafeService.
 *
 * @author A.Zastrozhyn
 * @param <T> the generic type
 */
public interface CafeService<T extends CafeEntity> {

	/**
	 * Find all.
	 *
	 * @return List<T>
	 * @throws ServiceException the service exception
	 */
	List<T> findAll() throws ServiceException;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return Optional<T>
	 * @throws ServiceException the service exception
	 */
	Optional<T> findById(int id) throws ServiceException;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean delete(int id) throws ServiceException;



}