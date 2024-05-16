package br.com.stoom.store.model.request;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class CategoryRequest {


    @NotNull
    @NotBlank(message = "Name can't be empty")
    private String name;

    @NotNull
    @NotBlank(message = "Description can't be empty")
    private String description;

    @NotNull
    @NotBlank(message = "CategoryId can't be empty")
    private String categoryId;

}
