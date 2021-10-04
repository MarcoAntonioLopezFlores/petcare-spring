package mx.app.petcare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.AdoptionDetails;
import mx.app.petcare.entity.Person;

@Repository
public interface AdoptionDetailsRepository extends JpaRepository<AdoptionDetails, Long> {

	public Page<AdoptionDetails> findByAdopter(Person adopter, Pageable pageable);
	public Page<AdoptionDetails> findByOwner(Person owner, Pageable pageable);
}
