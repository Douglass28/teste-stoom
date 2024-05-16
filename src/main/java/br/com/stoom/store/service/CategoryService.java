package br.com.stoom.store.service;

import br.com.stoom.store.exception.CategoryException;
import br.com.stoom.store.exception.ProductException;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.request.CategoryRequest;
import br.com.stoom.store.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;


    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category insert(CategoryRequest request){
        Category category = new Category(request);
        this.categoryRepository.save(category);

        return category;
    }

    public List<Category> getAllCategory(){
       return this.categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(String id){
       Optional<Category> category = categoryRepository.findById(Long.valueOf(id));
        return category;
    }

    public Category updateCategory(String id, CategoryRequest request){

        Category updateCategory = this.categoryRepository.findById(Long.valueOf(id))
                .orElseThrow(()-> new CategoryException("Category not found with id: " + id));

        if (!request.getCategoryId().isEmpty()) updateCategory.setCategoryId(request.getCategoryId());
        if (!request.getDescription().isEmpty()) updateCategory.setDescription(request.getDescription());
        if (!request.getName().isEmpty()) updateCategory.setName(request.getName());

        this.categoryRepository.save(updateCategory);

        return updateCategory;

    }

    public Category updateCategoryActive(String id, boolean active) {

        Category category = categoryRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new CategoryException("Category not found with id: " + id));

        category.setActive(active);
        return categoryRepository.save(category);
    }

    public Category findByCategory(String id){
        try {
            Category category = categoryRepository.findByCategoryId(id);
            return category;
        } catch (EntityNotFoundException exception){
            throw new EntityNotFoundException("Category not found with ID: " + id);
        }

    }

    public Void deleteCategory(String id){
        Category category = this.categoryRepository.findById(Long.valueOf(id))
                .orElseThrow(()-> new CategoryException("Category not found with id: " + id));

        this.categoryRepository.delete(category);

        return null;
    }
}
