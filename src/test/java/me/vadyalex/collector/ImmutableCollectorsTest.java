package me.vadyalex.collector;

import com.google.common.collect.*;
import me.vadyalex.rill.collector.ImmutableCollectors;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by vadyalex.
 */
public class ImmutableCollectorsTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(ImmutableCollectorsTest.class);

    @Test
    public void check_ImmutableList() {

        final Collection<Integer> result = Stream
                .of(1, 2, 3, 4, 5, 6)
                .collect(
                        ImmutableCollectors.toImmutableList()
                );

        Assertions
                .assertThat(result)
                .isInstanceOf(ImmutableList.class)
                .hasSize(6)
                .contains(1, 2, 3, 4, 5, 6);

    }

    @Test
    public void check_ImmutableSet() {

        final Collection<Integer> result = Stream
                .of(1, 2, 3, 4, 5, 6)
                .collect(
                        ImmutableCollectors.toImmutableSet()
                );

        Assertions
                .assertThat(result)
                .isInstanceOf(ImmutableSet.class)
                .hasSize(6)
                .contains(1, 2, 3, 4, 5, 6);

    }

    @Test
    public void check_ImmutableSortedSet() {

        final Collection<Integer> result = Stream
                .of(2, 4, 3, 1, 5, 6)
                .collect(
                        ImmutableCollectors.toImmutableSortedSet()
                );

        Assertions
                .assertThat(result)
                .isInstanceOf(ImmutableSet.class)
                .hasSize(6)
                .containsSubsequence(1, 2, 3, 4, 5, 6);

    }

    @Test
    public void check_ImmutableSortedSet_custom_comparator() {

        final Collection<Integer> result = Stream
                .of(2, 4, 3, 0, -1, 5, 6, 0, 99, -99)
                .collect(
                        ImmutableCollectors
                                .toImmutableSortedSet(
                                        (o1, o2) -> {
                                            if (o1 == 0)
                                                return -1;

                                            if (o2 == 0)
                                                return 1;

                                            if (o1 > o2)
                                                return 1;

                                            if (o1 < o2)
                                                return -1;

                                            return 0;
                                        }
                                )
                );

        Assertions
                .assertThat(result)
                .isInstanceOf(ImmutableSortedSet.class)
                .hasSize(10)
                .containsSubsequence(0, 0, -99, -1, 2, 3, 4, 5, 6, 99);

    }

    @Test
    public void check_ImmutableMap() {

        final Map<String, Integer> result = Stream
                .of(2, 4, 3, 0, -1, 5, 6, 100, 99, -99)
                .map(
                        i -> Maps.immutableEntry(" -> " + i, i)
                )
                .collect(
                        ImmutableCollectors
                                .toImmutableMap()
                );

        Assertions
                .assertThat(result)
                .isInstanceOf(ImmutableMap.class)
                .hasSize(10)
                .containsOnlyKeys(
                        " -> 2",
                        " -> 4",
                        " -> 3",
                        " -> 0",
                        " -> -1",
                        " -> 5",
                        " -> 6",
                        " -> 100",
                        " -> 99",
                        " -> -99"
                )
                .containsValue(0)
                .containsValue(-99)
                .containsValue(-1)
                .containsValue(2)
                .containsValue(3)
                .containsValue(4)
                .containsValue(5)
                .containsValue(6)
                .containsValue(99)
                .containsValue(100);

    }

    @Test
    public void check_ImmutableSortedMap() {

        final Map<String, String> result = Stream
                .of("B", "A", "C", "G", "K", "Y", "Z", "W", "F", "X")
                .map(
                        i -> Maps.immutableEntry(
                                String.valueOf(i),
                                i
                        )
                )
                .collect(
                        ImmutableCollectors
                                .toImmutableSortedMap()
                );

        Assertions
                .assertThat(result)
                .isInstanceOf(ImmutableMap.class)
                .hasSize(10);

        Assertions
                .assertThat(
                        result.keySet()
                )
                .containsSequence(
                        "A", "B", "C", "F", "G", "K", "W", "X", "Y", "Z"
                );

    }

    @Test
    public void check_ImmutableBiMap() {

        final Map<String, String> result = Stream
                .of("B", "A", "C", "G", "K", "Y", "Z", "W", "F", "X")
                .map(
                        i -> Maps.immutableEntry(
                                String.valueOf(i),
                                i
                        )
                )
                .collect(
                        ImmutableCollectors
                                .toImmutableBiMap()
                );

        Assertions
                .assertThat(result)
                .isInstanceOf(ImmutableBiMap.class)
                .hasSize(10);

        Assertions
                .assertThat(
                        result.keySet()
                )
                .containsSequence(
                        "B", "A", "C", "G", "K", "Y", "Z", "W", "F", "X"
                );

    }


}
