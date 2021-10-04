package mx.app.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Specie;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, Long>{

	Specie findByName(String name);
}
