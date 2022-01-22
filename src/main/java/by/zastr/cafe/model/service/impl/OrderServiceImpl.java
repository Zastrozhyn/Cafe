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
import by.zastr.cafe.model.dao.impl.OrderDaoImpl;
import by.zastr.cafe.model.dao.impl.UserDaoImpl;
import by.zastr.cafe.model.entity.Account;
import by.zastr.cafe.model.entity.CafeOrder;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.entity.User;
import by.zastr.cafe.model.service.CafeService;
import by.zastr.cafe.util.impl.InputValidatorImpl;

public class OrderServiceImpl implements CafeService<CafeOrder> {
	private static OrderServiceImpl instance = new OrderServiceImpl();;
	private OrderDaoImpl orderDao;
	private EntityTransaction entityTransaction;
	
	private OrderServiceImpl() {
		orderDao = new OrderDaoImpl();
		entityTransaction = new EntityTransaction();
	}
	
	public static OrderServiceImpl getInstance() {
        return instance;
	}
	
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
	
	public String confirmOrder(int userId, List<Dish> orderList, String description, String comment, LocalDate date, LocalTime time,
			String payment, BigDecimal totalCost) throws ServiceException {
		boolean paid = false;
		InputValidatorImpl validator = InputValidatorImpl.getInstance();
		if (!validator.isCorrectDescription(description)) {
			return UserMessage.WRONG_DESCRIPTION;
		}
		if (!validator.isCorrectTime(time)) {
			return UserMessage.WRONG_TIME;
		}
		
		CafeOrder.PaymentType paymentType = CafeOrder.PaymentType.valueOf(payment);
		if (paymentType.equals(CafeOrder.PaymentType.ACCOUNT)) {
			boolean b = payByAccount(userId, totalCost);
			if (!b) {
				return UserMessage.ACCOUNT_IS_BLOCK;
			}
			else {
				paid = true;
			}
		}
		CafeOrder order = new CafeOrder(userId, orderList, description, date, time, payment, paid, totalCost);
		try {
            entityTransaction.beginTransaction(orderDao);
			order = orderDao.createOrder(order);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method add order", e);
		}
		finally {
			entityTransaction.end();
		}	
		return UserMessage.CONFIRM_ORDER_SUCCESSFUL;	
	}

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
	
	public boolean payByAccount (int userId, BigDecimal totalCost) throws ServiceException {
		UserServiceImpl userService = UserServiceImpl.getInstance();
		User user = userService.findById(userId).get();
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
	
	public void setPaid (int orderId) throws ServiceException {
		CafeOrder order = findById(orderId).get();
		BigDecimal cost = order.getTotalCost();
		AccountServiceImpl accountService = AccountServiceImpl.getInstance();
		UserServiceImpl userService = UserServiceImpl.getInstance();
		User user = userService.findById(order.getUserId()).get();
		Account account = user.getAccount();
		BigDecimal orderHistory = account.getOrderHistory();
		account.setOrderHistory(orderHistory.add(cost));
		accountService.update(account);
		order.setPaid(true);
	}
}
