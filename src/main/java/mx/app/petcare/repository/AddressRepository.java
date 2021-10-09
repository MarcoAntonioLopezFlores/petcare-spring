package mx.app.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.app.petcare.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
