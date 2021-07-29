package ru.reeson2003.jaegerplaygroung;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FullNameService {

    private final FirstnameServiceClient firstnameServiceClient;

    private final LastnameServiceClient lastnameServiceClient;

    private final FullnamePublisher fullnamePublisher;

    private final ExecutorService executorService;

    public FullNameService(FirstnameServiceClient firstnameServiceClient,
                           LastnameServiceClient lastnameServiceClient,
                           FullnamePublisher fullnamePublisher,
                           TracingExecutorServiceWrapper tracingExecutorServiceWrapper) {
        this.firstnameServiceClient = firstnameServiceClient;
        this.lastnameServiceClient = lastnameServiceClient;
        this.fullnamePublisher = fullnamePublisher;
        this.executorService = tracingExecutorServiceWrapper.enableTracing(Executors.newFixedThreadPool(32));
    }

    public Fullname generate()
            throws Exception {
        var firstname = executorService.submit(() -> firstnameServiceClient.getFirstname().getData());
        var lastname = executorService.submit(() -> lastnameServiceClient.getLastname().getData());
        while (!firstname.isDone() && lastname.isDone()) {
            Thread.yield();
        }
        var fullname = new Fullname(firstname.get(), lastname.get());
        fullnamePublisher.publish(fullname);
        return fullname;
    }

}
