package by.zastr.cafe.model.entity;

import java.math.BigDecimal;

public class Dish extends CafeEntity{
	private int id;
	private String name;
	private int weight;
	private BigDecimal price;
	private String description;
	private DishType type;
	private boolean archive;
	
	public enum DishType {
		DRINK,
		DESERT,
		SNACK;
	}

	public Dish() {
		super();
	}

	public Dish(int id, String name, int weihgt, BigDecimal price, String descritpion, DishType type) {
		super();
		this.id = id;
		this.name = name;
		this.weight = weihgt;
		this.price = price;
		this.description = descritpion;
		this.type = type;
	}


	public Dish(String name, int weihgt, BigDecimal price, String description, String type) {
		super();
		this.name = name;
		this.weight = weihgt;
		this.price = price;
		this.description = description;
		this.type = DishType.valueOf(type.toUpperCase());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descritpion) {
		this.description = descritpion;
	}

	public DishType getType() {
		return type;
	}

	public void setType(DishType type) {
		this.type = type;
	}
	
	public void setType(String type) {
		this.type = DishType.valueOf(type.toUpperCase());
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (archive ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + weight;
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
		Dish other = (Dish) obj;
		if (archive != other.archive) {
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
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		if (weight != other.weight) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dish [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", price=");
		builder.append(price);
		builder.append(", description=");
		builder.append(description);
		builder.append(", type=");
		builder.append(type);
		builder.append(", archive=");
		builder.append(archive);
		builder.append("]");
		return builder.toString();
	}
}
