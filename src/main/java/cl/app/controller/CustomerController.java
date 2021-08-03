package cl.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.app.model.entity.Customer;
import cl.app.service.ICustomerService;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/api")
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("/customer")
	public List<Customer> index() {
		return customerService.findAll();
	}
	
	@GetMapping("/customer/page/{page}")
	public Page<Customer> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return customerService.findAll(pageable);
	}
	
	@GetMapping("/customer/{id}")
	public Customer show(@PathVariable Long id) {
		return customerService.findById(id);
	}

}
