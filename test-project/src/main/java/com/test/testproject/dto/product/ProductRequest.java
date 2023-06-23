package com.test.testproject.dto.product;

import com.test.testproject.model.Store;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest
{
    @NotNull
    private Integer storeId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Integer price;

    @NotBlank
    private String producer;
}
