package mx.app.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Brand;
import mx.app.petcare.entity.Category;
import mx.app.petcare.entity.Partner;
import mx.app.petcare.entity.Product;
import mx.app.petcare.entity.Specie;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findByIdAndStatusIsTrue(long id);
	List<Product> findByStatusIsTrue();
	List<Product> findByPartner(Partner partner);
	List<Product> findByBrandAndStatusIsTrue(Brand brand);
	List<Product> findByCategoryAndStatusIsTrue(Category category);
	List<Product> findBySpecieAndStatusIsTrue(Specie specie);
	List<Product> findByOrderByCreatedAtDesc();
	List<Product> findByNameIgnoreCaseContaining(String name); 
}
