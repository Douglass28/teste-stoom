package br.com.stoom.store.controller;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.model.request.CategoryRequest;
import br.com.stoom.store.model.request.UpdateActiveRequest;
import br.com.stoom.store.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/category")
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> insertCategory (@RequestBody CategoryRequest categoryRequest){
         Category category =  categoryService.insert(categoryRequest);
         return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> listCategory =  this.categoryService.getAllCategory();
        return ResponseEntity.ok().body(listCategory);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable("id") String id){
        Optional<Category> category =  this.categoryService.getCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateCategory(@PathVariable("id") String id,
                                                   @RequestBody CategoryRequest categoryRequest){
        Category updateCategory = this.categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.ok().body(updateCategory);
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<Category> updateCategoryActive(@PathVariable("id") String id, @RequestBody UpdateActiveRequest updateActiveRequest) {
        Category category= categoryService.updateCategoryActive(id, updateActiveRequest.isActive());
        return ResponseEntity.ok().body(category);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") String id){
        this.categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();

    }


}
