package mx.app.petcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.app.petcare.entity.Product;
import mx.app.petcare.entity.ShoppingCart;
import mx.app.petcare.entity.ShoppingCartProduct;

@Repository
public interface ShoppingCartProductRepository extends JpaRepository<ShoppingCartProduct, Long>{

	List<ShoppingCartProduct> findByShoppingCart(ShoppingCart shoppingCart);
	ShoppingCartProduct findByShoppingCartAndProduct(ShoppingCart shoppingCart, Product product);
}
