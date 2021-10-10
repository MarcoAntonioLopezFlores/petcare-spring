package mx.app.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long>{

	public List<Purchase> findByPersonOrderByCreatedAtDesc(Person person);
}
