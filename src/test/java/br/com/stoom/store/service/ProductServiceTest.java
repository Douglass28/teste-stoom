package br.com.stoom.store.service;

import br.com.stoom.store.exception.ProductException;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.Suplier;
import br.com.stoom.store.model.request.ProductRequest;
import br.com.stoom.store.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private SuplierService suplierService;

    @Test
    public void testSaveProduct_shouldSaveProduct() throws Exception {

        String categoryId = "1";
        String suplierId = "2";
        String name = "Produto Teste";
        boolean isActive = true;

        Category category = new Category("tenis", "tenis para sair", categoryId);
        Suplier suplier = new Suplier("WolrdTenis", "Rua manaus 123", "1234556", suplierId, isActive);

        ProductRequest productRequest = ProductRequest.builder()
                .name(name)
                .sku("1234")
                .brand("Nike")
                .description("novo nike")
                .price(1000.98)
                .category(category)
                .suplier(suplier)
                .active(isActive)
                .build();


        when(categoryService.findByCategory(categoryId)).thenReturn(category);
        when(suplierService.findBySuplier(suplierId)).thenReturn(suplier);

        Product savedProduct = productService.saveProduct(productRequest);

        verify(productRepository).save(savedProduct);

        assertEquals(name, savedProduct.getName());
        assertEquals(category, savedProduct.getCategory());
        assertEquals(suplier, savedProduct.getSuplier());
    }

    @Test
    public void testDeleteProduct_shouldDeleteProduct() {
        String productId = "1";

        productService.deleteProduct(productId);

        verify(productRepository).deleteById(Long.valueOf(productId));
    }

}