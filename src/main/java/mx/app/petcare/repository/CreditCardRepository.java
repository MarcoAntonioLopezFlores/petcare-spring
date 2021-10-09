package mx.app.petcare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.CreditCard;
import mx.app.petcare.entity.Person;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long>{

	public Page<CreditCard> findByPerson(Person person, Pageable pageable);
}
