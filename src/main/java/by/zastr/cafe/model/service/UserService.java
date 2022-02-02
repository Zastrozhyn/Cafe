package by.zastr.cafe.model.service;

import java.util.List;
import java.util.Optional;

import by.zastr.cafe.exception.ServiceException;
import by.zastr.cafe.model.entity.User;

public interface UserService {

	/**
	 * Find all.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<User> findAll() throws ServiceException;

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the optional
	 * @throws ServiceException the service exception
	 */
	Optional<User> findById(int id) throws ServiceException;

	/**
	 * Delete User.
	 *
	 * @param id the id
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean delete(int id) throws ServiceException;

	/**
	 * Find all deleted.
	 *
	 * @return List<User>
	 * @throws ServiceException the service exception
	 */
	List<User> findAllDeleted() throws ServiceException;

	/**
	 * Find by login.
	 *
	 * @param login the login
	 * @return Optional<User>
	 * @throws ServiceException the service exception
	 */
	Optional<User> findByLogin(String login) throws ServiceException;

	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return List<User>
	 * @throws ServiceException the service exception
	 */
	List<User> findByName(String name) throws ServiceException;

	/**
	 * Find by last name.
	 *
	 * @param name the name
	 * @return List<User>
	 * @throws ServiceException the service exception
	 */
	List<User> findByLastName(String name) throws ServiceException;

	/**
	 * Find by role.
	 *
	 * @param role the role
	 * @return List<User>
	 * @throws ServiceException the service exception
	 */
	List<User> findByRole(String role) throws ServiceException;

	/**
	 * Update User.
	 *
	 * @param user the user
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	boolean update(User user) throws ServiceException;

	/**
	 * Edits User.
	 *
	 * @param userId the user id
	 * @param name the name
	 * @param lastName the last name
	 * @param phone the phone
	 * @param email the email
	 * @param locale the locale
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	String edit(int userId, String name, String lastName, String phone, String email, String locale)
			throws ServiceException;

	/**
	 * Registration.
	 *
	 * @param name the name
	 * @param lastName the last name
	 * @param phone the phone
	 * @param login the login
	 * @param password the password
	 * @param confirmPassword the confirm password
	 * @param email the email
	 * @param locale the locale
	 * @return the string
	 * @throws ServiceException the service exception
	 */
	String registration(String name, String lastName, String phone, String login, String password,
			String confirmPassword, String email, String locale) throws ServiceException;

	/**
	 * Login.
	 *
	 * @param login the login
	 * @param password the password
	 * @return Optional<User>
	 * @throws ServiceException the service exception
	 */
	Optional<User> login(String login, String password) throws ServiceException;

	/**
	 * Change password.
	 *
	 * @param userId the user id
	 * @param login the login
	 * @param password the password
	 * @param newPassword the new password
	 * @param confirmPassword the confirm password
	 * @param locale the locale
	 * @return String result
	 * @throws ServiceException the service exception
	 */
	String changePassword(int userId, String login, String password, String newPassword, String confirmPassword,
			String locale) throws ServiceException;

	/**
	 * Checks if is unique login.
	 *
	 * @param login the login
	 * @return boolean is Unique Login
	 * @throws ServiceException the service exception
	 */
	boolean isUniqueLogin(String login) throws ServiceException;

	/**
	 * Checks if is unique phone.
	 *
	 * @param phone the phone
	 * @return boolean is Unique Phone
	 * @throws ServiceException the service exception
	 */
	boolean isUniquePhone(String phone) throws ServiceException;

}