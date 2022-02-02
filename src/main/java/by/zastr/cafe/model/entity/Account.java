package by.zastr.cafe.model.entity;

import java.math.BigDecimal;

/**
 * Class account
 * @author A.Zastrozhyn
 *
 */
public class Account extends CafeEntity{
	private static final BigDecimal SILVER_AMOUNT = BigDecimal.valueOf(100);
	private static final BigDecimal GOLD_AMOUNT = BigDecimal.valueOf(500);
	private static final BigDecimal PLATINUM_AMOUNT = BigDecimal.valueOf(1000);
	private int id;
	private AccountStatus status;
	private BigDecimal balance;
	private BigDecimal orderHistory;
	private boolean active;
	
	/**
	 * AccountStatus
	 * @author A.Zastrozhyn
	 *
	 */
	public enum AccountStatus {
		COMMON(0),
		SILVER(5),
		GOLD(10),
		PLATINUM(20);
		private int discount;

		private AccountStatus(int discount) {
			this.discount = discount;
		}

		public int getDiscount() {
			return discount;
		}		
	}

	public Account() {
		super();
	}

	/**
	 * Instantiates a new Account
	 * @param id
	 * @param status
	 * @param money
	 * @param active
	 */
	public Account(int id, AccountStatus status, BigDecimal money, boolean active) {
		super();
		this.id = id;
		this.status = status;
		this.balance = money;
		this.active = active;
	}

	/**
	 * Instantiates a new Account
	 * @param status
	 * @param money
	 * @param active
	 */
	public Account(AccountStatus status, BigDecimal money, boolean active) {
		super();
		this.status = status;
		this.balance = money;
		this.active = active;
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
	 * @return status
	 */
	public AccountStatus getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	/**
	 * 
	 * @return balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 
	 * @param balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	/**
	 * 
	 * @return isActive
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * 
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * 
	 * @return orderHistory
	 */
	public BigDecimal getOrderHistory() {
		return orderHistory;
	}

	/**
	 * set AccountStatus
	 * @param orderHistory
	 */
	public void setOrderHistory(BigDecimal orderHistory) {
		if (orderHistory.compareTo(SILVER_AMOUNT) > 0) {
			this.status = AccountStatus.SILVER;
		}
		if (orderHistory.compareTo(GOLD_AMOUNT) > 0) {
			this.status = AccountStatus.GOLD;
		}
		if (orderHistory.compareTo(PLATINUM_AMOUNT) > 0) {
			this.status = AccountStatus.PLATINUM;
		}
		this.orderHistory = orderHistory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		result = prime * result + id;
		result = prime * result + ((orderHistory == null) ? 0 : orderHistory.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Account other = (Account) obj;
		if (active != other.active) {
			return false;
		}
		if (balance == null) {
			if (other.balance != null) {
				return false;
			}
		} else if (!balance.equals(other.balance)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (orderHistory == null) {
			if (other.orderHistory != null) {
				return false;
			}
		} else if (!orderHistory.equals(other.orderHistory)) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [id=");
		builder.append(id);
		builder.append(", status=");
		builder.append(status);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", orderHistory=");
		builder.append(orderHistory);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

}
