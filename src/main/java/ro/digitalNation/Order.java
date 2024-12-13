package ro.digitalNation;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "`order`")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	private Utilizator utilizator;
	@OneToMany
	private List<OrderItem> orderItems;
    private Double totalAmount;
    private String status; // e.g., PENDING, SHIPPED, DELIVERED
    
	public Order(Long id, Utilizator Utilizator, List<OrderItem> orderItems, Double totalAmount, String status) {
		super();
		this.id = id;
		this.utilizator = Utilizator;
		this.orderItems = orderItems;
		this.totalAmount = totalAmount;
		this.status = status;
		
	}
	
	
	public Order( Utilizator utilizator, List<OrderItem> orderItems, Double totalAmount, String status) {
		super();
		this.utilizator = utilizator;
		this.orderItems = orderItems;
		this.totalAmount = totalAmount;
		this.status = status;
	}
	public Order() {}
	
	public Order(Utilizator user, Iterable<OrderItem> selectedItems, Double totalAmount2, String status2) {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Utilizator getUtilizator() {
		return utilizator;
	}
	public void setUtilizator(Utilizator Utilizator) {
		this.utilizator = Utilizator;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
    
    
}
