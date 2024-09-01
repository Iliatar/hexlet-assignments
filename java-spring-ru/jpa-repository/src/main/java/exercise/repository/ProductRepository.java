package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceLessThan(Integer price, Sort sort);
    List<Product> findByPriceGreaterThan(Integer price, Sort sort);
    List<Product> findByPriceBetween(Integer startPrice, Integer endPrice, Sort sort);
    // END
}
