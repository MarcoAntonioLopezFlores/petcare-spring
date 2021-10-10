package mx.app.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.AdoptionDetails;
import mx.app.petcare.entity.Person;

@Repository
public interface AdoptionDetailsRepository extends JpaRepository<AdoptionDetails, Long> {

	//public Page<AdoptionDetails> findByAdopter(Person adopter, Pageable pageable);
	//public Page<AdoptionDetails> findByOwner(Person owner, Pageable pageable);
	public List<AdoptionDetails> findByOwner(Person owner);
	public List<AdoptionDetails> findByAdopter(Person adopter);
}
