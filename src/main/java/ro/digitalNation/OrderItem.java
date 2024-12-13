package ro.digitalNation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private CarPart carPart;
	private Integer quantity;
	private Double price;
	 
	public OrderItem(Long id, CarPart carPart, Integer quantity, Double price) {
		super();
		this.id = id;
		this.carPart = carPart;
		this.quantity = quantity;
		this.price = price;
	}
	
	
	public OrderItem( CarPart carPart, Integer quantity, Double price) {
		super();
		this.carPart = carPart;
		this.quantity = quantity;
		this.price = price;
	}
	public OrderItem() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CarPart getCarPart() {
		return carPart;
	}
	public void setCarPart(CarPart carPart) {
		this.carPart = carPart;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	 
	 
}
