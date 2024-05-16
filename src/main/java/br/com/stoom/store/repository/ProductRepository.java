package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();
    List<Product> findByBrandAndActiveTrue(String brand);
    Optional<Product> findByIdAndActiveTrue(Long id);
    List<Product> findByCategoryIdAndActiveTrue(Long categoryId);


}