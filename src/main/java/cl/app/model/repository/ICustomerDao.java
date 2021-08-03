package cl.app.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.app.model.entity.Customer;


public interface ICustomerDao extends JpaRepository<Customer, Long>{

}
