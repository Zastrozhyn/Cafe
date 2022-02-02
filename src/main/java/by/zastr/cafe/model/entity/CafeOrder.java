package by.zastr.cafe.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Class CafeOrder
 * @author A.Zastrozhyn
 *
 */
public class CafeOrder extends CafeEntity {
	private int id;
	private String userLogin;
	private List<Dish> orderList;
	private String description;
	private String comment;
	private LocalDate date;
	private LocalTime time;
	private PaymentType payment;
	private boolean paid;
	private BigDecimal totalCost;
	
	/**
	 * PaymentType
	 * @author A.Zastrozhyn
	 *
	 */
	public enum PaymentType {
		CASH,
		ACCOUNT,
		CREDIT_CARD;
	}

	public CafeOrder() {
		super();
	}

	/**
	 * Instantiates a new Cafe order
	 * @param userLogin
	 * @param orderList
	 * @param description
	 * @param date
	 * @param time
	 * @param payment
	 * @param paid
	 * @param totalCost
	 */
	public CafeOrder(String userLogin, List<Dish> orderList, String description, LocalDate date, LocalTime time,
			String payment, boolean paid, BigDecimal totalCost) {
		super();
		this.userLogin = userLogin;
		this.orderList = orderList;
		this.description = description;
		this.date = date;
		this.time = time;
		this.payment = PaymentType.valueOf(payment);
		this.paid = paid;
		this.totalCost = totalCost;
	}


	/**
	 * Instantiates a new Cafe order
	 * @param userLogin
	 * @param orderList
	 * @param description
	 * @param comment
	 * @param date
	 * @param time
	 * @param payment
	 */
	public CafeOrder(String userLogin, List<Dish> orderList, String description, String comment, LocalDate date, LocalTime time,
			String payment) {
		super();
		this.userLogin = userLogin;
		this.orderList = orderList;
		this.description = description;
		this.comment = comment;
		this.date = date;
		this.time = time;
		this.payment = PaymentType.valueOf(payment);
	}
	
	/**
	 * Instantiates a new Cafe order
	 * @param userLogin
	 * @param orderList
	 * @param description
	 * @param date
	 * @param time
	 */
	public CafeOrder(String userLogin, List<Dish> orderList, String description, LocalDate date, LocalTime time) {
		super();
		this.userLogin = userLogin;
		this.orderList = orderList;
		this.description = description;
		this.date = date;
		this.time = time;
	}

	/**
	 * 
	 * @param Dish dish
	 */
	public void addDish (Dish dish) {
		orderList.add(dish);
	}

	/**
	 * 
	 * @param dish
	 */
	public void removeDish (Dish dish) {
		orderList.remove(dish);
	}

	/**
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return userLogin
	 */
	public String getUserLogin() {
		return userLogin;
	}

	/**
	 * 
	 * @param userLogin
	 */
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	/**
	 * 
	 * @return orderList
	 */
	public List<Dish> getOrderList() {
		List<Dish> copyOrder = List.copyOf(orderList);
		return copyOrder;
	}

	/**
	 * 
	 * @param orderList
	 */
	public void setOrderList(List<Dish> orderList) {
		this.orderList = orderList;
	}

	/**
	 * 
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * 
	 * @param comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 
	 * @return PaymentType
	 */
	public PaymentType getPayment() {
		return payment;
	}

	/**
	 * 
	 * @param payment
	 */
	public void setPayment(PaymentType payment) {
		this.payment = payment;
	}

	/**
	 * 
	 * @return paid
	 */
	public boolean isPaid() {
		return paid;
	}

	/**
	 * 
	 * @param completed
	 */
	public void setPaid(boolean completed) {
		this.paid = completed;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * 
	 * @return time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * 
	 * @param time
	 */
	public void setTime(LocalTime time) {
		this.time = time;
	}

	/**
	 * 
	 * @return totalCost
	 */
	public BigDecimal getTotalCost() {
		return totalCost;
	}

	/**
	 * 
	 * @param totalCost
	 */
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((orderList == null) ? 0 : orderList.hashCode());
		result = prime * result + (paid ? 1231 : 1237);
		result = prime * result + ((payment == null) ? 0 : payment.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
		result = prime * result + ((userLogin == null) ? 0 : userLogin.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		CafeOrder other = (CafeOrder) obj;
		if (comment == null) {
			if (other.comment != null) {
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			return false;
		}
		if (date == null) {
			if (other.date != null) {
				return false;
			}
		} else if (!date.equals(other.date)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (orderList == null) {
			if (other.orderList != null) {
				return false;
			}
		} else if (!orderList.equals(other.orderList)) {
			return false;
		}
		if (paid != other.paid) {
			return false;
		}
		if (payment != other.payment) {
			return false;
		}
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		if (totalCost == null) {
			if (other.totalCost != null) {
				return false;
			}
		} else if (!totalCost.equals(other.totalCost)) {
			return false;
		}
		if (userLogin == null) {
			if (other.userLogin != null) {
				return false;
			}
		} else if (!userLogin.equals(other.userLogin)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CafeOrder [id=");
		builder.append(id);
		builder.append(", userLogin=");
		builder.append(userLogin);
		builder.append(", orderList=");
		builder.append(orderList);
		builder.append(", description=");
		builder.append(description);
		builder.append(", comment=");
		builder.append(comment);
		builder.append(", date=");
		builder.append(date);
		builder.append(", time=");
		builder.append(time);
		builder.append(", payment=");
		builder.append(payment);
		builder.append(", paid=");
		builder.append(paid);
		builder.append(", totalCost=");
		builder.append(totalCost);
		builder.append("]");
		return builder.toString();
	}

}
