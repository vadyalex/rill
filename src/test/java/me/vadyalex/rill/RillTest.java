package me.vadyalex.rill;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import me.vadyalex.rill.collector.ImmutableCollectors;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

public class RillTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(RillTest.class);

    @Test
    public void motivation_1_standard_concatenation() {

        final Stream<Integer> stream1 = Stream.of(1);
        final Stream<Integer> stream2 = Stream.of(2);
        final Stream<Integer> stream3 = Stream.of(3);
        final Stream<Integer> stream4 = Stream.of(4);

        final Stream<Integer> result = Stream.concat(
                stream1,
                Stream.concat(
                        stream2,
                        Stream.concat(
                                stream3,
                                stream4
                        )
                )
        );

        final ImmutableList<Integer> list = result.collect(ImmutableCollectors.toImmutableList());

        LOGGER.info(
                " -> {}", list
        );

        Assertions
                .assertThat(list)
                .hasSize(4)
                .containsSequence(1, 2, 3, 4);
    }

    @Test
    public void motivation_1_rill_concatenation() {

        final Stream<Integer> stream1 = Stream.of(1);
        final Stream<Integer> stream2 = Stream.of(2);
        final Stream<Integer> stream3 = Stream.of(3);
        final Stream<Integer> stream4 = Stream.of(4);

        final Stream<Integer> result = Rill
                .from(stream1)
                .join(stream2, stream3, stream4);

        final ImmutableList<Integer> list = result.collect(ImmutableCollectors.toImmutableList());

        LOGGER.info(
                " -> {}", list
        );

        Assertions
                .assertThat(list)
                .hasSize(4)
                .containsSequence(1, 2, 3, 4);
    }

    @Test
    public void motivation_1_standard_concatenation_with_filters() {

        final Stream<Integer> stream1 = Stream.of(-1, 1);
        final Stream<Integer> stream2 = Stream.of(2, 5);
        final Stream<Integer> stream3 = Stream.of(3, 0);
        final Stream<Integer> stream4 = Stream.of(4, 5);

        final Stream<Integer> result = Stream.concat(
                Stream
                        .concat(
                                Stream
                                        .concat(
                                                stream1,
                                                stream2
                                        )
                                        .filter(
                                                i -> i != 5
                                        ),
                                stream3
                        )
                        .filter(
                                i -> i != 0
                        ),
                stream4
        );

        final ImmutableList<Integer> list = result.collect(ImmutableCollectors.toImmutableList());

        LOGGER.info(
                " -> {}", list
        );

        Assertions
                .assertThat(list)
                .hasSize(6)
                .containsSequence(-1, 1, 2, 3, 4, 5);
    }

    @Test
    public void motivation_1_rill_concatenation_with_filters() {

        final Stream<Integer> stream1 = Stream.of(-1, 1);
        final Stream<Integer> stream2 = Stream.of(2, 5);
        final Stream<Integer> stream3 = Stream.of(3, 0);
        final Stream<Integer> stream4 = Stream.of(4, 5);

        final Stream<Integer> result = Rill
                .<Integer>from()
                .join(stream1, stream2)
                .filter(
                        i -> i != 5
                )
                .join(stream3)
                .filter(
                        i -> i != 0
                )
                .join(stream4);

        final ImmutableList<Integer> list = result.collect(ImmutableCollectors.toImmutableList());

        LOGGER.info(
                " -> {}", list
        );

        Assertions
                .assertThat(list)
                .hasSize(6)
                .containsSequence(-1, 1, 2, 3, 4, 5);
    }

    @Test
    public void basic_usage() {

        final ImmutableList<String> result = Rill
                .from(1)
                .join(2)
                .join(-1, 0)
                .filter(
                        i -> i > 0
                )
                .join(3)
                .join(0)
                .zip(
                        (a, b) -> a + " -> " + b,
                        1, 2, 3, 4
                )
                .toImmutableList();

        LOGGER.info(" => {}", result);

        Assertions
                .assertThat(result)
                .hasSize(4)
                .contains(
                        "1 -> 1",
                        "2 -> 2",
                        "3 -> 3",
                        "0 -> 4"
                );
    }

    @Test
    public void zip_even() {

        final ImmutableList<String> result = Rill
                .from(1, 2, 3, 4)
                .zip(
                        (i, s) -> i + " -> " + s,
                        "A", "B", "C"
                )
                .toImmutableList();

        LOGGER.info(" => {}", result);

        Assertions
                .assertThat(result)
                .hasSize(3)
                .contains(
                        "1 -> A",
                        "2 -> B",
                        "3 -> C"
                );

    }

    @Test
    public void zip_uneven() {

        final ImmutableList<String> result = Rill
                .from(0, 1, 2, 3)
                .zipUneven(
                        (i, s) -> i.orElse(-1) + " -> " + s.orElse(""),
                        Rill.from(
                                "A", "B", "C"
                        )
                )
                .toImmutableList();

        LOGGER.info(" => {}", result);

        Assertions
                .assertThat(result)
                .hasSize(4)
                .contains(
                        "0 -> A",
                        "1 -> B",
                        "2 -> C",
                        "3 -> "
                );
    }

    @Test
    public void zip_even_nulls() {

        final ImmutableList<String> result = Rill
                .from(
                        1, 2, 3, 4
                )
                .zip(
                        (i, s) -> i + " -> " + s,
                        null, null, null
                )
                .toImmutableList();

        LOGGER.info(" => {}", result);

        Assertions
                .assertThat(result)
                .hasSize(3)
                .contains(
                        "1 -> null",
                        "2 -> null",
                        "3 -> null"
                );
    }

    @Test
    public void zip_uneven_nulls() {

        final ImmutableList<String> result = Rill
                .from(
                        1, 2, 3, 4
                )
                .zipUneven(
                        (i, s) -> i.orElse(-1) + " -> " + s.orElse(""),
                        null, null, null
                )
                .toImmutableList();

        LOGGER.info(" => {}", result);

        Assertions
                .assertThat(result)
                .hasSize(4)
                .contains(
                        "1 -> ",
                        "2 -> ",
                        "3 -> ",
                        "4 -> "
                );
    }

    @Test
    public void basic_map_usage() {

        final ImmutableMap<String, Integer> result = Rill
                .<String>from()
                .join(
                        Rill.from("A")
                )
                .join("B")
                .join("X", "X", "X")
                .join(
                        ImmutableList.of("X", "X")
                )
                .join(
                        Iterators.forArray("X", "X")
                )
                .filter(
                        s -> !s.equals("X")
                )
                .join("C")
                .zip(
                        Maps::immutableEntry,
                        0, 1, 2
                )
                .toImmutableMap(
                        entry -> entry
                );

        LOGGER.info(" => {}", result);

        Assertions
                .assertThat(result)
                .hasSize(3)
                .containsKeys(
                        "A",
                        "B",
                        "C"
                )
                .containsValue(
                        0
                )
                .containsValue(
                        1
                )
                .containsValue(
                        2
                );

    }

    @Test
    public void check_stream_concat() {

        final Stream<Integer> stream1 = Stream.of(1);
        final Stream<Integer> stream2 = Stream.of(2);
        final Stream<Integer> stream3 = Stream.of(3, 4, 5);
        final Stream<Integer> stream4 = Stream.of(6);

        final ImmutableList<Integer> result = Rill
                .<Integer>from()
                .join(-2, -1)
                .join(0)
                .join(stream1)
                .join(stream2)
                .join(stream3)
                .join(stream4)
                .collect(
                        ImmutableCollectors.toImmutableList()
                );

        Assertions.assertThat(result).containsExactly(-2, -1, 0, 1, 2, 3, 4, 5, 6);

    }

    @Test
    public void check_stream_concat_2() {

        final Stream<Integer> stream1 = Stream.of(1);
        final Stream<Integer> stream2 = Stream.of(2);
        final Stream<Integer> stream3 = Stream.of(3, 4, 5);
        final Stream<Integer> stream4 = Stream.of(6);

        final ImmutableList<Integer> result = Rill
                .from(-2, -1, 0)
                .join(
                        stream1,
                        stream2,
                        stream3,
                        stream4
                )
                .toImmutableList();

        Assertions.assertThat(result).containsExactly(-2, -1, 0, 1, 2, 3, 4, 5, 6);

    }

    @Test
    public void check_empty_rill() {

        Assertions
                .assertThat(
                        Rill.from().count()
                )
                .isZero();

        Assertions
                .assertThat(
                        Rill.from().count()
                )
                .isZero();
    }

    @Test
    public void check_toImmutableMap() {

        final ImmutableMap<String, Integer> result = Rill
                .from(
                        1, 2, 3
                )
                .toImmutableMap(
                        Object::toString,
                        i -> i
                );

        Assertions
                .assertThat(result)
                .hasSize(3)
                .containsKeys(
                        "1",
                        "2",
                        "3"
                )
                .containsValue(
                        1
                )
                .containsValue(
                        2
                )
                .containsValue(
                        3
                );

    }

}
