package br.com.stoom.store.service;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.request.CategoryRequest;
import br.com.stoom.store.repository.CategoryRepository;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void testInsert() {

        String name = "Eletrodomestico";
        String description = "Eletrodomestico em geral";
        String categoryId = "1";

        CategoryRequest categoryRequest = CategoryRequest.builder()
                .name(name)
                .description(description)
                .categoryId(categoryId)
                .build();

        Category savedCategory = categoryService.insert(categoryRequest);

        verify(categoryRepository).save(savedCategory);

        assertEquals(name, savedCategory.getName());
        assertEquals(description, savedCategory.getDescription());
        assertEquals(categoryId, savedCategory.getCategoryId());


    }
}