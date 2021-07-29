package ru.reeson2003.jaegerplaygroung;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "firstname-service-client", url = "${service.firstname.url}")
public interface FirstnameServiceClient {

    @GetMapping("/firstname/random")
    Name getFirstname();

}
