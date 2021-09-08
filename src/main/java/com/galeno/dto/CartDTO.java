package com.galeno.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO {
    private Long id;
    private List<ProductDTO> products;
    private Boolean paid;
}
