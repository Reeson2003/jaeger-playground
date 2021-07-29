package ru.reeson2003.jaegerplaygroung;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
public class TracingExecutorServiceWrapper {

    private final Tracer tracer;

    public ExecutorService enableTracing(ExecutorService origin) {
        return new TracingExecutorService(origin);
    }

    private interface Submit {

        <T> Future<T> submit(Callable<T> task);

        <T> Future<T> submit(Runnable task, T result);

        Future<?> submit(Runnable task);

    }

    @RequiredArgsConstructor
    private class TracingExecutorService
            implements ExecutorService {

        @Delegate(excludes = Submit.class)
        private final ExecutorService origin;

        @Override
        public <T> Future<T> submit(@NotNull Callable<T> task) {
            return origin.submit(wrapTracing(task));
        }

        @Override
        public <T> Future<T> submit(@NotNull Runnable task, T result) {
            return origin.submit(wrapTracing(task), result);
        }

        @Override
        public Future<?> submit(@NotNull Runnable task) {
            return origin.submit(wrapTracing(task));
        }

        private Runnable wrapTracing(Runnable command) {
            var currentSpan = tracer.currentSpan();
            return () -> {
                tracer.withSpan(currentSpan);
                command.run();
            };
        }

        private <T> Callable<T> wrapTracing(Callable<T> command) {
            var currentSpan = tracer.currentSpan();
            return () -> {
                tracer.withSpan(currentSpan);
                return command.call();
            };
        }

    }

}
