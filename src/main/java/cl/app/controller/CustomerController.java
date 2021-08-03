package cl.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		return this.customerService.findAll();
	}

	@GetMapping("/customer/page/{page}")
	public Page<Customer> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return this.customerService.findAll(pageable);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {

		Customer customer = null;
		Map<String, Object> response = new HashMap<>();

		try {
			customer = this.customerService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (customer == null) {
			response.put("message", "Customer ID '".concat(id.toString().concat("' does not exist in the database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@PostMapping("/customer")
	public ResponseEntity<?> create(@Valid @RequestBody Customer customer, BindingResult result) {

		Customer newCustomer = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			newCustomer = this.customerService.save(customer);
		} catch (DataAccessException e) {
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "The customer has been successfully created");
		response.put("customer", newCustomer);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Customer customer, BindingResult result,
			@PathVariable Long id) {

		Customer currentCustomer = this.customerService.findById(id);
		Customer updatedCustomer = null;

		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "The field '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (currentCustomer == null) {
			response.put("message", "Customer ID '".concat(id.toString().concat("' does not exist in the database")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			currentCustomer.setName(customer.getName());
			currentCustomer.setLastname(customer.getLastname());
			currentCustomer.setEmail(customer.getEmail());
			currentCustomer.setCreateAt(customer.getCreateAt());
			currentCustomer.setUpdateAt(new Date());

			updatedCustomer = this.customerService.save(currentCustomer);
		} catch (DataAccessException e) {
			response.put("message", "Error when updating customer from database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "Customer has been successfully updated");
		response.put("customer", updatedCustomer);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();

		try {
			this.customerService.delete(id);
		} catch (DataAccessException e) {
			response.put("message", "Error deleting customer from database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("message", "Customer has been successfully removed");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
