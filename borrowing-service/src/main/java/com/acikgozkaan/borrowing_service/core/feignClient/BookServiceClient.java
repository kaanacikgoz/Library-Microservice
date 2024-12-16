package com.acikgozkaan.borrowing_service.core.feignClient;

import com.acikgozkaan.borrowing_service.dto.response.FeignBookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "book-service")
public interface BookServiceClient {

    @GetMapping("/books/{id}")
    FeignBookResponse getBookById(@PathVariable Long id);

}
