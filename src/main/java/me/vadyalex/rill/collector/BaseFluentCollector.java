package me.vadyalex.rill.collector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by vadyalex.
 */
public class BaseFluentCollector<T> implements FluentCollector<T> {

    private final Stream<T> internal;

    public BaseFluentCollector(Stream<T> stream) {
        this.internal = Objects.requireNonNull(stream);
    }

    public ImmutableList<T> toImmutableList() {
        return this
                .internal
                .collect(
                        ImmutableCollectors.toImmutableList()
                );
    }

    public ImmutableSet<T> toImmutableSet() {
        return this
                .internal
                .collect(
                        ImmutableCollectors.toImmutableSet()
                );
    }

    public <K, V> ImmutableMap<K, V> toImmutableMap(Function<? super T, ? extends Map.Entry<K, V>> mapper) {
        return this
                .internal
                .map(
                        Objects.requireNonNull(mapper)
                ).collect(
                        ImmutableCollectors.toImmutableMap()
                );
    }

    public <K, V> ImmutableMap<K, V> toImmutableMap(Function<? super T, K> keyMapper, Function<? super T, V> valueMapper) {
        return this
                .internal
                .map(
                        t -> Maps.immutableEntry(
                                Objects.requireNonNull(keyMapper).apply(t),
                                Objects.requireNonNull(valueMapper).apply(t)
                        )
                ).collect(
                        ImmutableCollectors.toImmutableMap()
                );

    }

}
