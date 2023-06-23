package com.test.testproject.dto.store;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO
{
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private boolean deliveryAvailability;

    @NotBlank
    private String contactNumber;

    @NotBlank
    private String owner;
}
