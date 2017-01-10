package util;

import java.io.IOException;

@FunctionalInterface
public interface ConsumerWithIOException<T> {
    void apply(T stream) throws IOException;
}
