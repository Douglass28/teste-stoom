package br.com.stoom.store.model;

import br.com.stoom.store.model.request.ProductRequest;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.Instant;
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    @SequenceGenerator(name = "product_sequence", sequenceName = "PRODUCT_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "sku")
    private String sku;

    @Column
    private String name;

    @Column
    private String brand;

    @Column
    private String description;

    @Column
    private Double price;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "suplier_id")
    private Suplier suplier;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private Boolean active = true;


    public Product(Long id, String sku, String name, String brand, String description, Double price, Suplier suplier, Category category) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.price = price;
        this.suplier = suplier;
        this.category = category;
    }

    public Product(ProductRequest productRequest) {
        this.sku = productRequest.getSku();
        this.name = productRequest.getName();
        this.brand = productRequest.getBrand();
        this.description = productRequest.getDescription();
        this.price = productRequest.getPrice();
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("name", this.name);
        jsonObject.put("brand", this.brand);
        jsonObject.put("description", this.description);
        jsonObject.put("price", this.price);
        jsonObject.put("categoryId", category.getCategoryId());
        jsonObject.put("suplier", suplier.getSuplierId());


        return jsonObject.toString();

    }
}