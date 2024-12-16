package com.acikgozkaan.borrowing_service.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeignBookResponse {

    private Long id;

    private Integer stock;

}
