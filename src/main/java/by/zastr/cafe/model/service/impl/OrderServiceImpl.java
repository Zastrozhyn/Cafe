package by.zastr.cafe.model.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.dao.EntityTransaction;
import by.zastr.cafe.model.dao.impl.AccountDaoImpl;
import by.zastr.cafe.model.dao.impl.OrderDaoImpl;
import by.zastr.cafe.model.entity.Account;
import by.zastr.cafe.model.entity.CafeOrder;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.CafeService;
import by.zastr.cafe.util.MessageManager;
import by.zastr.cafe.util.impl.InputValidatorImpl;

/**
 * class OrderServiceImpl.
 *
 * @author A.Zastrozhyn
 */
public class OrderServiceImpl implements CafeService<CafeOrder> {
	private static OrderServiceImpl instance = new OrderServiceImpl();;
	private OrderDaoImpl orderDao;
	private EntityTransaction entityTransaction;
	
	private OrderServiceImpl() {
		orderDao = new OrderDaoImpl();
		entityTransaction = new EntityTransaction();
	}
	
	/**
	 * Gets the single instance of OrderServiceImpl.
	 *
	 * @return single instance of OrderServiceImpl
	 */
	public static OrderServiceImpl getInstance() {
        return instance;
	}
	
	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	@Override
	public List<CafeOrder> findAll() throws ServiceException{
		List<CafeOrder> orderList = new ArrayList<CafeOrder>();
		entityTransaction.beginTransaction(orderDao);
		try {
			orderList = orderDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method find all order", e);
		}
		finally {
			entityTransaction.end();
		}
		return orderList;
	}
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 * @throws ServiceException the service exception
	 */
	@Override
	public Optional<CafeOrder> findById(int id) throws ServiceException{
		Optional<CafeOrder> order = Optional.empty();
		entityTransaction.beginTransaction(orderDao);
		try {
			order = orderDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method find all order", e);
		}
		finally {
			entityTransaction.end();
		}
		return order;
	}
	
	/**
	 * Delete CafeOrder.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	@Override
	public boolean delete(int id) throws ServiceException {
		boolean b = false;
		try {
			entityTransaction.beginTransaction(orderDao);
			b = orderDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method delete order", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;
	}
	
	/**
	 * Adds the comment.
	 *
	 * @param comment the comment
	 * @param orderId the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean addComment(String comment, int orderId) throws ServiceException {
		CafeOrder order = findById(orderId).get();
		order.setComment(comment);
		boolean b = false;
		entityTransaction.beginTransaction(orderDao);
		try {
			b = orderDao.update(order);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method find all order", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;
	}
	
	/**
	 * Find unpaid.
	 *
	 * @return List<CafeOrder>
	 * @throws ServiceException the service exception
	 */
	public List<CafeOrder> findUnpaid() throws ServiceException{
		List<CafeOrder> orderList = new ArrayList<CafeOrder>();
		entityTransaction.beginTransaction(orderDao);
		try {
			orderList = orderDao.findUnpaid();
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method find unpaid order", e);
		}
		finally {
			entityTransaction.end();
		}
		return orderList;
	}
	
	/**
	 * Find by login.
	 *
	 * @param login the login
	 * @return List<CafeOrder>
	 * @throws ServiceException the service exception
	 */
	public List<CafeOrder> findByLogin(String login) throws ServiceException{
		List<CafeOrder> orderList = new ArrayList<CafeOrder>();
		entityTransaction.beginTransaction(orderDao);
		try {
			orderList = orderDao.findByLogin(login);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method find unpaid order", e);
		}
		finally {
			entityTransaction.end();
		}
		return orderList;
	}
	
	/**
	 * Find today.
	 *
	 * @return List<CafeOrder>
	 * @throws ServiceException the service exception
	 */
	public List<CafeOrder> findToday() throws ServiceException{
		List<CafeOrder> orderList = new ArrayList<CafeOrder>();
		entityTransaction.beginTransaction(orderDao);
		try {
			orderList = orderDao.findToday();
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method find unpaid order", e);
		}
		finally {
			entityTransaction.end();
		}
		return orderList;
	}
	
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
	public String confirmOrder(String userLogin, List<Dish> orderList, String description, String comment, LocalDate date, LocalTime time,
			String payment, BigDecimal totalCost, String locale) throws ServiceException {
		boolean paid = false;
		InputValidatorImpl validator = InputValidatorImpl.getInstance();
		MessageManager messageManager = MessageManager.defineLocale(locale);
		if (!validator.isCorrectDescription(description)) {
			return messageManager.getMessage(UserMessage.WRONG_DESCRIPTION);
		}
		if (!validator.isCorrectTime(time)) {
			return messageManager.getMessage(UserMessage.WRONG_TIME);
		}
		UserServiceImpl userService = UserServiceImpl.getInstance();
		User user = userService.findByLogin(userLogin).get();
		if (!user.getAccount().isActive()) {
			return messageManager.getMessage(UserMessage.ACCOUNT_BLOCK);
		}
		CafeOrder.PaymentType paymentType = CafeOrder.PaymentType.valueOf(payment);
		if (paymentType.equals(CafeOrder.PaymentType.ACCOUNT)) {
			boolean b = payByAccount(userLogin, totalCost);
			if (!b) {
				return messageManager.getMessage(UserMessage.ACCOUNT_BLOCK_OR_MONEY);
			}
			else {
				paid = true;
			}
		}
		CafeOrder order = new CafeOrder(userLogin, orderList, description, date, time, payment, paid, totalCost);
		try {
            entityTransaction.beginTransaction(orderDao);
			order = orderDao.createOrder(order);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method add order", e);
		}
		finally {
			entityTransaction.end();
		}	
		return messageManager.getMessage(UserMessage.SUCCESSFUL);	
	}
	
	/**
	 * Paid.
	 *
	 * @param orderId the order id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean paid (int orderId) throws ServiceException {
		var accountDao = new AccountDaoImpl();
		CafeOrder order = findById(orderId).get();
		if(order.isPaid()) {
			return false;
		}
		var userService = UserServiceImpl.getInstance();
		User user = userService.findByLogin(order.getUserLogin()).get();
		Account account = user.getAccount();
		order.setPaid(true);
		BigDecimal bonus = order.getTotalCost().divide(BigDecimal.TEN);
		account.setBalance(account.getBalance().add(bonus));
		account.setOrderHistory(account.getOrderHistory().add(order.getTotalCost()));
		try {
			entityTransaction.beginTransaction(orderDao, accountDao);
			orderDao.update(order);
			accountDao.update(account);
		} catch (DaoException e) {
			try {
				entityTransaction.rollback();
			} catch (DaoException e1) {
				throw new ServiceException("Service exception in method paid order", e);
			}
			throw new ServiceException("Service exception in method paid order", e);
		}
		finally {
			entityTransaction.endTransaction();
		}
		return true;
	}
	
	/**
	 * Update CafeOrder.
	 *
	 * @param order the order
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean update(CafeOrder order) throws ServiceException {
		boolean b = false;
		try {
			entityTransaction.beginTransaction(orderDao);
			b = orderDao.update(order);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method update order", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;
	}
	
	/**
	 * Pay by account.
	 *
	 * @param userLogin the user login
	 * @param totalCost the total cost
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean payByAccount (String userLogin, BigDecimal totalCost) throws ServiceException {
		UserServiceImpl userService = UserServiceImpl.getInstance();
		User user = userService.findByLogin(userLogin).get();
		BigDecimal balance = user.getAccount().getBalance();
		BigDecimal orderHistory = user.getAccount().getOrderHistory();
		if (!user.getAccount().isActive() || (totalCost.compareTo(balance) > 0)) {
			return false;
		}
		user.getAccount().setBalance(balance.subtract(totalCost));
		user.getAccount().setOrderHistory(orderHistory.add(totalCost));
		AccountServiceImpl service = AccountServiceImpl.getInstance();
		service.update(user.getAccount());
		return true;
	}
	
	/**
	 * Sets the paid.
	 *
	 * @param orderId the new paid
	 * @throws ServiceException the service exception
	 */
	public void setPaid (int orderId) throws ServiceException {
		CafeOrder order = findById(orderId).get();
		BigDecimal cost = order.getTotalCost();
		AccountServiceImpl accountService = AccountServiceImpl.getInstance();
		UserServiceImpl userService = UserServiceImpl.getInstance();
		User user = userService.findByLogin(order.getUserLogin()).get();
		Account account = user.getAccount();
		BigDecimal orderHistory = account.getOrderHistory();
		account.setOrderHistory(orderHistory.add(cost));
		accountService.update(account);
		order.setPaid(true);
	}
}
