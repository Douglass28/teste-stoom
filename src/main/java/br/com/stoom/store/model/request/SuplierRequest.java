package br.com.stoom.store.model.request;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SuplierRequest {


    private String name;

    private String address;

    private String phone;

    private String suplierId;
}
