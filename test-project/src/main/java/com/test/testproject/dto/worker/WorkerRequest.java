package com.test.testproject.dto.worker;

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
public class WorkerRequest
{
    @NotNull
    private Integer placeOfWorkId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Positive
    private Integer age;

    @NotNull
    private Integer salary;
}