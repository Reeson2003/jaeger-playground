package ru.reeson2003.jaegerplaygroung;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "lastname-service-client", url = "${service.lastname.url}")
public interface LastnameServiceClient {

    @GetMapping("/lastname/random")
    Name getLastname();

}
