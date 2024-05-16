package br.com.stoom.store.controller;

import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.request.ProductRequest;
import br.com.stoom.store.model.request.UpdateActiveRequest;
import br.com.stoom.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody ProductRequest productRequest){
        Product product = productService.saveProduct(productRequest);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Product>> find(@PathVariable("id") String id){
        Optional<Product> product = productService.getProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/find/brand/{brand}")
    public ResponseEntity<List<Product>> listProductsToBrand(@PathVariable("brand") String brand) {
        List<Product> listProductsToBrand = productService.listProductsToBrand(brand);
        return ResponseEntity.ok().body(listProductsToBrand);
    }
    @GetMapping("/find/category/{categoryId}")
    public ResponseEntity<List<Product>> listProductsToCategory(@PathVariable("categoryId") String categoryId) {
        List<Product> listProductsToCategory = productService.listProductsToCategory(categoryId);
        return ResponseEntity.ok().body(listProductsToCategory);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> listProduct = productService.getAllProducts();
        return ResponseEntity.ok().body(listProduct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductRequest productRequest){
        Product product = productService.updateProduct(id, productRequest);
        return ResponseEntity.ok().body(product);
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<Product> updateProductActive(@PathVariable("id") String id, @RequestBody UpdateActiveRequest updateActiveRequest) {
        Product product = productService.updateProductActive(id, updateActiveRequest.isActive());
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id){
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
