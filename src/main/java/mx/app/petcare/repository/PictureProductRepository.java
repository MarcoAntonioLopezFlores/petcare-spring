package mx.app.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.PictureProduct;
import mx.app.petcare.entity.Product;

@Repository
public interface PictureProductRepository extends JpaRepository<PictureProduct, Long> { 
	public List<PictureProduct> findByProduct(Product product);

}
