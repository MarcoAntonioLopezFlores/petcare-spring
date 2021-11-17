package mx.app.petcare.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	public List<Pet> findByOwner(Person owner);
	List<Pet> findByOwnerAndIsForAdoptingIsTrue(Person owner);
	List<Pet> findByOwnerAndIsAdoptedIsTrue(Person owner);
	List<Pet> findByIsForAdoptingIsTrue();
	List<Pet> findByIsAdoptedIsTrue();
}
