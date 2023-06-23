package com.test.testproject.exeption;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException
{
    private final Integer errorObjId;

    public EntityNotFoundException(Integer errorObjId, String message)
    {
        super(message);
        this.errorObjId = errorObjId;
    }
}
