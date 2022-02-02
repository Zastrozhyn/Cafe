package by.zastr.cafe.model.service;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.CafeEntity;

/**
 * interface CafeService
 * @author A.Zastrozhyn
 * @param <T extends CafeEntity>
 */
public interface CafeService<T extends CafeEntity> {

	/**
	 * 
	 * @return List<T>
	 * @throws ServiceException
	 */
	List<T> findAll() throws ServiceException;

	/**
	 * 
	 * @param id
	 * @return Optional<T> 
	 * @throws ServiceException
	 */
	Optional<T> findById(int id) throws ServiceException;

	/**
	 * 
	 * @param id
	 * @throws ServiceException
	 */
	boolean delete(int id) throws ServiceException;



}