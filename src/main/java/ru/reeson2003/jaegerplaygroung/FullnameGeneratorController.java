package ru.reeson2003.jaegerplaygroung;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.cloud.sleuth.annotation.ContinueSpan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/fullname")
public class FullnameGeneratorController {

    private final FullNameService fullNameService;

    @Autowired
    private Tracer tracer;

    @ContinueSpan
    @RequestMapping("/random")
    public Fullname getRandom()
            throws Exception {
        log.debug("Got request");
        return fullNameService.generate();
    }

}
