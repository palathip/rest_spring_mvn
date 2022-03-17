package com.rest_spring_mvn.mapper;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface TextFileReader<T> {

    default Supplier<Stream<T>> supplier(Path path) {
        TextFileReader<T> self = this;
        return new Supplier<Stream<T>>() {
            @SneakyThrows(IOException.class)
            @Override
            public Stream<T> get() {
                return Files.lines(path, StandardCharsets.UTF_8).filter(self::preFilter)
                        .map(self::map).filter(self::postFilter);
            }
        };
    }

    boolean preFilter(String s);

    T map(String s);

    boolean postFilter(T t);
}
