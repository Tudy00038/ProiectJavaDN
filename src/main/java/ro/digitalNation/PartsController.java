package  ro.digitalNation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
@Controller
public class PartsController {
	private Order order1;
	private Utilizator user1;
	@Autowired
	private CarPartRepository carPartsRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	
	
	@PostConstruct
	public void init() {
		Utilizator user1 = new Utilizator("Tudy00038","tudy2000038@gmail.com");
		userRepo.save(user1);
		
		//User user2 = new User("Denis00038","denis2000038@gmail.com");
		List<OrderItem> items = new ArrayList<OrderItem>();
		CarPart carPart1 = new CarPart("Oglinda retrovizoare","Oglinda retrovizoare BMW",200.99,10,"Accesori");
		CarPart carPart2 = new CarPart("Oglinda dreapta","Oglinda dreapta BMW",100.99,30,"Accesori");
		CarPart carPart3 = new CarPart("Oglinda stanga","Oglinda stanga BMW",300.99,20,"Accesori");
		carPartsRepo.save(carPart1);
		carPartsRepo.save(carPart2);
		carPartsRepo.save(carPart3);
		OrderItem item1 = new OrderItem(carPart1,2,carPart1.getPrice());
		OrderItem item2 = new OrderItem(carPart2,5,carPart2.getPrice());
		OrderItem item3 = new OrderItem(carPart3,3,carPart3.getPrice());
		items.add(item1);
		items.add(item2);
		items.add(item3);
		orderItemRepo.saveAll(items);
		Double totalAmmount= carPart1.getPrice()+carPart2.getPrice()+carPart3.getPrice();
		
		this.order1 = new Order(user1,items,totalAmmount,"PENDING");
		
		
	}
	
	@GetMapping("/orders")
	public String listOrders(Model model) {
	    Iterable<Order> orders = orderRepo.findAll(); // Fetch all orders
	    model.addAttribute("orders", orders);
	    return "ordersList"; // Name of the Thymeleaf template
	}
	
	@GetMapping("/orders/new")
	public String showNewOrderForm(Model model) {
	    Iterable<Utilizator> users = userRepo.findAll(); 
	    Iterable<OrderItem> orderItems = orderItemRepo.findAll(); 
	    model.addAttribute("users", users);
	    model.addAttribute("orderItems", orderItems);
	    model.addAttribute("order", new Order());
	    return "newOrder"; 
	}

	@PostMapping("/orders/new")
	public String saveNewOrder(
	        @RequestParam Long userId, 
	        @RequestParam List<Long> orderItemIds, 
	        @RequestParam String status) {
	    Utilizator user = userRepo.findById(userId).orElse(null);
	    Iterable<OrderItem> selectedItems = orderItemRepo.findAllById(orderItemIds);

	    if (user != null && selectedItems != null) {
	        Double totalAmount = ((Collection<OrderItem>) selectedItems).stream()
	                                           .mapToDouble(OrderItem::getPrice)
	                                           .sum();
	        Order order = new Order(user, selectedItems, totalAmount, status);
	        orderRepo.save(order);
	    }
	    return "redirect:/orders"; // Redirect to the list of orders
	}


	
	
	@GetMapping("/users")
    public String listUsers(Model model) {
        Iterable<Utilizator> users = userRepo.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        Utilizator user = userRepo.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "userDetails";
    }

    @PostMapping("/users")
    public String addUser(@RequestParam String username, @RequestParam String email) {
        Utilizator newUser = new Utilizator(username, email);
        userRepo.save(newUser);
        return "redirect:/users";
    }

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @RequestParam String username, @RequestParam String email) {
        Utilizator user = userRepo.findById(id).orElse(null);
        if (user != null) {
            user.setUtilizatorname(username);
            user.setEmail(email);
            userRepo.save(user);
        }
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/users/new")
    public String showNewUserForm(Model model) {
        model.addAttribute("user", new Utilizator());
        return "newUser";
    }

    @PostMapping("/users/new")
    public String saveNewUser(@ModelAttribute Utilizator user) {
        userRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/parts")
    public String listCarParts(Model model) {
        Iterable<CarPart> carParts = carPartsRepo.findAll();
        model.addAttribute("carParts", carParts);
        return "carPartsList";
    }

	
    @GetMapping("/parts/new")
    public String showNewCarPartForm(Model model) {
        model.addAttribute("carPart", new CarPart());
        return "newCarPart";
    }

    @PostMapping("/parts/new")
    public String saveNewCarPart(@ModelAttribute CarPart carPart) {
        carPartsRepo.save(carPart);
        return "redirect:/parts";
    }
	
	
	
	
	@PostMapping("/addCarPart")
	public String adaugaCarPart(
			@RequestParam String name,
			@RequestParam String descriere,
			@RequestParam Double price,
			@RequestParam Integer stock,
			@RequestParam String category
			) {
		
		return "redirect:/order";
	}
	
	@GetMapping("/orderItems/new")
	public String showNewOrderItemForm(Model model) {
	    Iterable<CarPart> carParts = carPartsRepo.findAll(); // Fetch all available car parts
	    model.addAttribute("carParts", carParts);
	    model.addAttribute("orderItem", new OrderItem());
	    return "newOrderItem";
	}

	@PostMapping("/orderItems/new")
	public String saveNewOrderItem(
	        @RequestParam Long carPartId, 
	        @RequestParam Integer quantity, 
	        @RequestParam Double price) {
	    CarPart carPart = carPartsRepo.findById(carPartId).orElse(null);
	    if (carPart != null) {
	        OrderItem orderItem = new OrderItem(carPart, quantity, price);
	        orderItemRepo.save(orderItem);
	    }
	    return "redirect:/orderItems"; // Redirect to a list of order items (optional)
	}
	
	@GetMapping("/orderItems")
	public String listOrderItems(Model model) {
	    Iterable<OrderItem> orderItems = orderItemRepo.findAll();
	    model.addAttribute("orderItems", orderItems);
	    return "orderItemsList";
	}

	
		
}
