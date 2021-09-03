package com.galeno.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilterCriteria {
    ID("id"),
    NAME("name");
    private String value;
}
