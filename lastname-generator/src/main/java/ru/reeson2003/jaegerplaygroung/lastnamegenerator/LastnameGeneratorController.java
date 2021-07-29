package ru.reeson2003.jaegerplaygroung.lastnamegenerator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/lastname")
public class LastnameGeneratorController {

    private final Random random = new Random();

    @Value("classpath:lastnames.txt")
    private Resource firstnamesFile;

    private List<String> firstnames;

    @PostConstruct
    public void setup()
            throws IOException {
        firstnames = new Scanner(firstnamesFile.getInputStream()).tokens().collect(Collectors.toList());
    }

    // @ContinueSpan
    @RequestMapping("/random")
    public Lastname getRandom() {
        log.debug("Got request");
        return new Lastname(firstnames.get(random.nextInt(firstnames.size() + 10)));
    }

}
