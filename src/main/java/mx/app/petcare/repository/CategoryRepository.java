package mx.app.petcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findByName(String name);
}
