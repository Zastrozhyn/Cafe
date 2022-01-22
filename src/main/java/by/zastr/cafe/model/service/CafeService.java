package by.zastr.cafe.model.service;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.CafeEntity;

public interface CafeService<T extends CafeEntity> {

	List<T> findAll() throws ServiceException;

	Optional<T> findById(int id) throws ServiceException;

	boolean delete(int id) throws ServiceException;



}