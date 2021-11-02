package mx.app.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{
	Brand findByName(String name);
}
