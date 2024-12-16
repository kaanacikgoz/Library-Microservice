package com.acikgozkaan.borrowing_service.core.feignClient;

import com.acikgozkaan.borrowing_service.dto.response.FeignUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/users/{id}")
    FeignUserResponse getUserById(@PathVariable String id);

}
