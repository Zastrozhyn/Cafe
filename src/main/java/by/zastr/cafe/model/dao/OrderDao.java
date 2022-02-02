package by.zastr.cafe.model.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.entity.CafeOrder;

/**
 * interface OrderDao
 * @author A.Zastrozhyn
 *
 */
public interface OrderDao {

	/**
	 * 
	 * @return List<CafeOrder> 
	 * @throws DaoException
	 */
	List<CafeOrder> findAll() throws DaoException;

	/**
	 * 
	 * @return List<CafeOrder> 
	 * @throws DaoException
	 */
	List<CafeOrder> findUnpaid() throws DaoException;

	/**
	 * 
	 * @param login
	 * @return List<CafeOrder> 
	 * @throws DaoException
	 */
	List<CafeOrder> findByLogin(String login) throws DaoException;

	/**
	 * 
	 * @return List<CafeOrder> 
	 * @throws DaoException
	 */
	List<CafeOrder> findToday() throws DaoException;

	/**
	 * 
	 * @param id
	 * @return Optional<CafeOrder>
	 * @throws DaoException
	 */
	Optional<CafeOrder> findById(int id) throws DaoException;

	/**
	 * 
	 * @param order
	 * @throws DaoException
	 */
	boolean create(CafeOrder order) throws DaoException;

	/**
	 * 
	 * @param order
	 * @return CafeOrder
	 * @throws DaoException
	 */
	CafeOrder createOrder(CafeOrder order) throws DaoException;

	/**
	 * 
	 * @param order
	 * @throws DaoException
	 */
	boolean update(CafeOrder order) throws DaoException;

	/**
	 * 
	 * @param order
	 * @throws DaoException
	 */
	boolean delete(CafeOrder order) throws DaoException;

	/**
	 * 
	 * @param id
	 * @throws DaoException
	 */
	boolean delete(int id) throws DaoException;

	/**
	 * 
	 * @param orderId
	 * @return totalCost
	 * @throws DaoException
	 */
	BigDecimal totalCost(int orderId) throws DaoException;

	/**
	 * 
	 * @param orderId
	 * @param dishId
	 * @throws DaoException
	 */
	boolean createOrderDish(int orderId, int dishId) throws DaoException;

}