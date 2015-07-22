package me.vadyalex.rill;

import com.google.common.collect.Iterators;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class StreamableTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(StreamableTest.class);

    private final class SomeStreamableIterableClass implements StreamableIterable<String> {

        public final String property1;
        public final String property2;
        public final String property3;

        private SomeStreamableIterableClass(String property1, String property2, String property3) {
            this.property1 = property1;
            this.property2 = property2;
            this.property3 = property3;
        }

        @Override
        public Iterator<String> iterator() {

            return Iterators.forArray(
                    property1,
                    property2,
                    property3
            );
        }

    }

    @Test
    public void check() {

        LOGGER.info(
                " -> {}",
                new SomeStreamableIterableClass("a", "b", "c")
                        .stream()
                        .map(
                                String::toUpperCase
                        )
                        .filter(
                                s -> s.equalsIgnoreCase("C")
                        )
                        .findFirst()
                        .orElse(
                                "NONE"
                        )
        );

    }

}
