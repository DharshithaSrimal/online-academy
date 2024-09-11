package com.online_academy.academy_service.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("USER-SERVICE")//Same name as in the Eureka Server
public interface AcademyInterface {
}
