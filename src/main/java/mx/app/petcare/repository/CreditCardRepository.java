package mx.app.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.CreditCard;
import mx.app.petcare.entity.Person;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{

	public List<CreditCard> findByPerson(Person person);
}
