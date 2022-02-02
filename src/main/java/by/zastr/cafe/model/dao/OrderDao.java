package by.zastr.cafe.model.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.model.entity.CafeOrder;

/**
 * interface OrderDao.
 *
 * @author A.Zastrozhyn
 */
public interface OrderDao {

	/**
	 * Find all.
	 *
	 * @return List<CafeOrder>
	 * @throws DaoException the dao exception
	 */
	List<CafeOrder> findAll() throws DaoException;

	/**
	 * Find unpaid.
	 *
	 * @return List<CafeOrder>
	 * @throws DaoException the dao exception
	 */
	List<CafeOrder> findUnpaid() throws DaoException;

	/**
	 * Find by login.
	 *
	 * @param login the login
	 * @return List<CafeOrder>
	 * @throws DaoException the dao exception
	 */
	List<CafeOrder> findByLogin(String login) throws DaoException;

	/**
	 * Find today.
	 *
	 * @return List<CafeOrder>
	 * @throws DaoException the dao exception
	 */
	List<CafeOrder> findToday() throws DaoException;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return Optional<CafeOrder>
	 * @throws DaoException the dao exception
	 */
	Optional<CafeOrder> findById(int id) throws DaoException;

	/**
	 * Creates the.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean create(CafeOrder order) throws DaoException;

	/**
	 * Creates the order.
	 *
	 * @param order the order
	 * @return CafeOrder
	 * @throws DaoException the dao exception
	 */
	CafeOrder createOrder(CafeOrder order) throws DaoException;

	/**
	 * Update.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean update(CafeOrder order) throws DaoException;

	/**
	 * Delete.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean delete(CafeOrder order) throws DaoException;

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean delete(int id) throws DaoException;

	/**
	 * Total cost.
	 *
	 * @param orderId the order id
	 * @return totalCost
	 * @throws DaoException the dao exception
	 */
	BigDecimal totalCost(int orderId) throws DaoException;

	/**
	 * Creates the order dish.
	 *
	 * @param orderId the order id
	 * @param dishId the dish id
	 * @return true, if successful
	 * @throws DaoException the dao exception
	 */
	boolean createOrderDish(int orderId, int dishId) throws DaoException;

}