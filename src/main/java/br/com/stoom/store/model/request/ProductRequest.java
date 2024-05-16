package br.com.stoom.store.model.request;

import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Suplier;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductRequest {

    private String name;

    private String sku;

    private String brand;

    private String description;

    private Double price;

    private Category category;

    private Suplier suplier;

    private boolean active;






}
