package br.com.stoom.store.model;

import br.com.stoom.store.model.request.CategoryRequest;
import br.com.stoom.store.model.request.SuplierRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "suplier")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Suplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String suplierId;

    @Column
    private Boolean active = true;


    public Suplier(SuplierRequest request){
        this.name = request.getName();
        this.address = request.getAddress();
        this.phone = request.getPhone();
        this.suplierId = request.getSuplierId();
    }

    public Suplier(String name, String address, String phone, String suplierId, Boolean active) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.suplierId = suplierId;
        this.active = active;
    }
}
