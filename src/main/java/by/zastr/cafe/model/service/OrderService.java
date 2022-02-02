package by.zastr.cafe.model.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.CafeOrder;
import by.zastr.cafe.model.entity.Dish;

public interface OrderService {

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<CafeOrder> findAll() throws ServiceException;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 * @throws ServiceException the service exception
	 */
	Optional<CafeOrder> findById(int id) throws ServiceException;

	/**
	 * Delete CafeOrder.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean delete(int id) throws ServiceException;

	/**
	 * Adds the comment.
	 *
	 * @param comment the comment
	 * @param orderId the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean addComment(String comment, int orderId) throws ServiceException;

	/**
	 * Find unpaid.
	 *
	 * @return List<CafeOrder>
	 * @throws ServiceException the service exception
	 */
	List<CafeOrder> findUnpaid() throws ServiceException;

	/**
	 * Find by login.
	 *
	 * @param login the login
	 * @return List<CafeOrder>
	 * @throws ServiceException the service exception
	 */
	List<CafeOrder> findByLogin(String login) throws ServiceException;

	/**
	 * Find today.
	 *
	 * @return List<CafeOrder>
	 * @throws ServiceException the service exception
	 */
	List<CafeOrder> findToday() throws ServiceException;

	/**
	 * Confirm order.
	 *
	 * @param userLogin the user login
	 * @param orderList the order list
	 * @param description the description
	 * @param comment the comment
	 * @param date the date
	 * @param time the time
	 * @param payment the payment
	 * @param totalCost the total cost
	 * @param locale the locale
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	String confirmOrder(String userLogin, List<Dish> orderList, String description, String comment, LocalDate date,
			LocalTime time, String payment, BigDecimal totalCost, String locale) throws ServiceException;

	/**
	 * Paid.
	 *
	 * @param orderId the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean paid(int orderId) throws ServiceException;

	/**
	 * Update CafeOrder.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean update(CafeOrder order) throws ServiceException;

	/**
	 * Pay by account.
	 *
	 * @param userLogin the user login
	 * @param totalCost the total cost
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean payByAccount(String userLogin, BigDecimal totalCost) throws ServiceException;

	/**
	 * Sets the paid.
	 *
	 * @param orderId the new paid
	 * @throws ServiceException the service exception
	 */
	void setPaid(int orderId) throws ServiceException;

}