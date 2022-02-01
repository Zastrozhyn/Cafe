package by.zastr.cafe.model.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.zastr.cafe.controller.command.UserMessage;
import by.zastr.cafe.exception.DaoException;
import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.dao.EntityTransaction;
import by.zastr.cafe.model.dao.impl.DishDaoImpl;
import by.zastr.cafe.model.entity.Dish;
import by.zastr.cafe.model.service.CafeService;
import by.zastr.cafe.util.MessageManager;
import by.zastr.cafe.util.impl.InputValidatorImpl;


public class DishServiceImpl implements CafeService<Dish> {
	private static DishServiceImpl instance = new DishServiceImpl();
	private DishDaoImpl dishDao;
	private EntityTransaction entityTransaction;
	
	private DishServiceImpl() {
		dishDao = new DishDaoImpl();
		entityTransaction = new EntityTransaction();
	}
	
	public static DishServiceImpl  getInstance() {
        return instance;
	}
	
	@Override
	public List<Dish> findAll() throws ServiceException{
		List<Dish> dishList = new ArrayList<Dish>();
		entityTransaction.beginTransaction(dishDao);
		try {
			dishList = dishDao.findAll();
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method find all dish", e);
		}
		finally {
			entityTransaction.end();
		}
		return dishList;
	}
	
	@Override
	public Optional<Dish> findById(int id) throws ServiceException{
		Optional<Dish> dish = Optional.empty();
		try {
			entityTransaction.beginTransaction(dishDao);
			dish = dishDao.findById(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding dish by id", e);
		}
		finally {
			entityTransaction.end();
		}
		return dish;
	}
	
	public List<Dish> findByName(String name) throws ServiceException{
		List<Dish> userList = new ArrayList<Dish>();
		try {
			entityTransaction.beginTransaction(dishDao);;
			userList = dishDao.findByName(name);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding dish", e);
		}
		finally {
			entityTransaction.end();
		}
		return userList;
	}
	
	public List<Dish> findByType(String type) throws ServiceException{
		List<Dish> userList = new ArrayList<Dish>();
		try {
			entityTransaction.beginTransaction(dishDao);
			userList = dishDao.findByType(type);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding dish", e);
		}
		finally {
			entityTransaction.end();
		}
		return userList;
	}
	
	public List<Dish> findDeleted() throws ServiceException{
		List<Dish> userList = new ArrayList<Dish>();
		try {
			entityTransaction.beginTransaction(dishDao);
			userList = dishDao.findDeleted();
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method finding dish", e);
		}
		finally {
			entityTransaction.end();
		}
		return userList;
	}
	
	@Override
	public boolean delete(int id) throws ServiceException {
		boolean b = false;
		try {
			entityTransaction.beginTransaction(dishDao);
			b = dishDao.delete(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method delete dish", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;	
	}
	
	public boolean restore(int id) throws ServiceException {
		boolean b = false;
		try {
			entityTransaction.beginTransaction(dishDao);
			b = dishDao.restore(id);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method restore dish", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;	
	}
	
	public boolean update(Dish dish) throws ServiceException {
		boolean b = false;
		try {
			entityTransaction.beginTransaction(dishDao);
			b = dishDao.update(dish);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method update dish", e);
		}
		finally {
			entityTransaction.end();
		}
		return b;	
	}

	public String create(String name, String weight, String price, String description, String type, String locale) throws ServiceException {
		InputValidatorImpl validator = InputValidatorImpl.getInstance();
		MessageManager messageManager = MessageManager.defineLocale(locale);
		if (!validator.isCorrectPrice(price)) {
			return messageManager.getMessage(UserMessage.WRONG_PRICE);
		}
		if (!validator.isCorrectDescription(description)) {
			return messageManager.getMessage(UserMessage.WRONG_DESCRIPTION);
		}
		if (!validator.isCorrectWeight(weight)) {
			return messageManager.getMessage(UserMessage.WRONG_WEIGHT);
		}
		Double priceDouble = Double.parseDouble(price);
		BigDecimal cost = BigDecimal.valueOf(priceDouble);
		int weightInt = Integer.parseInt(weight);
		var dish = new Dish(name, weightInt, cost, description, type);
		try {
			entityTransaction.beginTransaction(dishDao);
			dishDao.create(dish);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method add dish", e);
		}
		finally {
			entityTransaction.end();
		}
		return messageManager.getMessage(UserMessage.SUCCESSFUL);
	}
	
	public String update(int id, String name, String weight, String price, String description, String type, String locale) throws ServiceException {
		InputValidatorImpl validator = InputValidatorImpl.getInstance();
		MessageManager messageManager = MessageManager.defineLocale(locale);
		if (!validator.isCorrectName(name)) {
			return messageManager.getMessage(UserMessage.WRONG_NAME);
		}
		if (!validator.isCorrectPrice(price)) {
			return messageManager.getMessage(UserMessage.WRONG_PRICE);
		}
		if (!validator.isCorrectDescription(description)) {
			return messageManager.getMessage(UserMessage.WRONG_DESCRIPTION);
		}
		if (!validator.isCorrectWeight(weight)) {
			return messageManager.getMessage(UserMessage.WRONG_WEIGHT);
		}
		Double priceDouble = Double.parseDouble(price);
		BigDecimal cost = BigDecimal.valueOf(priceDouble);
		int weightInt = Integer.parseInt(weight);
		var dish = new Dish(name, weightInt, cost, description, type);
		dish.setId(id);
		try {
			entityTransaction.beginTransaction(dishDao);
			dishDao.update(dish);
		} catch (DaoException e) {
			throw new ServiceException("Service exception in method add dish", e);
		}
		finally {
			entityTransaction.end();
		}
		return messageManager.getMessage(UserMessage.SUCCESSFUL);
	}
}
