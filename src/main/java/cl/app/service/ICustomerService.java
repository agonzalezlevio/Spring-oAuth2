package cl.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cl.app.model.entity.Customer;

public interface ICustomerService {

	public List<Customer> findAll();
	
	public Page<Customer> findAll(Pageable pageable);
	
	public Customer findById(Long id);
	
	public Customer save(Customer customer);
	
	public void delete(Long id);

}
