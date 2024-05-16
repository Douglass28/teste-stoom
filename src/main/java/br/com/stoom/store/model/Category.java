package br.com.stoom.store.model;

import br.com.stoom.store.model.request.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;
    private String name;
    private String description;
    private String categoryId;
    @Column
    private Boolean active = true;


    public Category(String title, String description, String categoryId){
        this.name = title;
        this.description = description;
        this.categoryId = categoryId;
    }

    public Category(CategoryRequest request){
        this.name = request.getName();
        this.description = request.getDescription();
        this.categoryId = request.getCategoryId();
    }
}