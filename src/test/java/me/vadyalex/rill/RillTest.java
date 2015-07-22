package me.vadyalex.rill;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RillTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(RillTest.class);

    @Test
    public void basic_usage() {

        final ImmutableList<String> result = Rill
                .from(1)
                .concat(2)
                .concat(-1, 0)
                .filter(
                        i -> i > 0
                )
                .concat(3)
                .concat(0)
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
    public void basic_map_usage() {

        final ImmutableMap<String, Integer> result = Rill
                .<String>from()
                .concat(
                        Rill.from("1")
                )
                .concat("2")
                .concat("+", "+")
                .concat(
                        ImmutableList.of("+", "+")
                )
                .concat(
                        Iterators.forArray("+", "+")
                )
                .filter(
                        s -> !s.equals("+")
                )
                .concat("3")
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
                        "1",
                        "2",
                        "3"
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

}
