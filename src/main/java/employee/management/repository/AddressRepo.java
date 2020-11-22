package employee.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import employee.management.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{

}