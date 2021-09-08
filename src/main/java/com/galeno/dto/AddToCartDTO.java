package com.galeno.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddToCartDTO {
    private Long productId;
    private Long UserId;

}
