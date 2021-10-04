package mx.app.petcare.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Person;
import mx.app.petcare.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

	public Page<Pet> findByOwner(Person owner, Pageable pageable);
}
