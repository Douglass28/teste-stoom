package br.com.stoom.store.service;

import br.com.stoom.store.exception.CategoryException;
import br.com.stoom.store.exception.ProductException;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.Suplier;
import br.com.stoom.store.model.request.ProductRequest;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private SuplierService suplierService;

    public ProductService (ProductRepository productRepository, CategoryService categoryService, SuplierService suplierService){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.suplierService = suplierService;
    }

    public Product saveProduct(ProductRequest productRequest){

        Product product = new Product(productRequest);

        if (!productRequest.isActive()){
            product.setActive(false);
        }

        Category category = categoryService.findByCategory(productRequest.getCategory().getCategoryId());
        Suplier suplier = suplierService.findBySuplier(productRequest.getSuplier().getSuplierId());

        product.setCategory(category);
        product.setSuplier(suplier);


        this.productRepository.save(product);

        return product;
    }

    public Optional<Product> getProductById(String id){
        try {
            Long productId = Long.valueOf(id);
            return productRepository.findByIdAndActiveTrue(productId);
        }  catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid ID format: " + id);
        }

    }

    public List<Product> getAllProducts(){
        return productRepository.findByActiveTrue();

    }

    public List<Product> listProductsToBrand(String brand) {
        try {
            return productRepository.findByBrandAndActiveTrue(brand);
        }catch (RuntimeException e){
            throw new RuntimeException("Brand not exists: " + brand);
        }
    }

    public List<Product> listProductsToCategory(String categoryId) {
        try {
            Long category = Long.valueOf(categoryId);
            return productRepository.findByCategoryIdAndActiveTrue(category);
        }catch (ProductException e){
            throw new RuntimeException("Category the product not exists: " + categoryId);
        }
    }

    @Transactional
    public Product updateProduct(String id, ProductRequest updateProduct) {

        Product productExists = productRepository.findById(Long.valueOf(id))
                .orElseThrow(()-> new ProductException("Product not found with id: " + id));

        productExists.setName(updateProduct.getName());
        productExists.setPrice(updateProduct.getPrice());
        productExists.setDescription(updateProduct.getDescription());
        productExists.setBrand(updateProduct.getBrand());


        return productRepository.save(productExists);
    }

    public Product updateProductActive(String id, boolean active) {

        Product product = productRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ProductException("Product not found with id: " + id));

        product.setActive(active);
        return productRepository.save(product);
    }

    public void deleteProduct(String id){
        try {
            productRepository.deleteById(Long.valueOf(id));
        } catch (ProductException e){
            throw new ProductException("Id product not existis: " + id );
        }

    }


}
