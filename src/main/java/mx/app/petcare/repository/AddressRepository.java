package mx.app.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Address;
import mx.app.petcare.entity.Person;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findByPerson(Person person);
}
