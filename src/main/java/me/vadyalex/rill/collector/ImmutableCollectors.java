package me.vadyalex.rill.collector;

import com.google.common.collect.*;

import java.util.Comparator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collector;

/**
 * Created by vadyalex.
 */
public class ImmutableCollectors {

    public static <T> Collector<T, ImmutableList.Builder<T>, ImmutableList<T>> toImmutableList() {
        return Collector.of(
                ImmutableList.Builder::new,
                ImmutableList.Builder::add,
                (b1, b2) -> b1.addAll(b2.build()),
                ImmutableList.Builder::build
        );
    }

    public static <T> Collector<T, ImmutableSet.Builder<T>, ImmutableSet<T>> toImmutableSet() {
        return Collector.of(
                ImmutableSet.Builder::new,
                ImmutableSet.Builder::add,
                (b1, b2) -> b1.addAll(b2.build()),
                ImmutableSet.Builder::build
        );
    }

    public static <T extends Comparable<T>> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> toImmutableSortedSet() {
        return Collector.of(
                ImmutableSortedSet::naturalOrder,
                (BiConsumer<ImmutableSortedSet.Builder<T>, T>) ImmutableSortedSet.Builder::add,
                (b1, b2) -> b1.addAll(b1.build()),
                ImmutableSortedSet.Builder::build
        );
    }

    public static <T> Collector<T, ImmutableSortedSet.Builder<T>, ImmutableSortedSet<T>> toImmutableSortedSet(Comparator<T> comparator) {
        return Collector.of(
                () -> ImmutableSortedSet.orderedBy(comparator),
                (BiConsumer<ImmutableSortedSet.Builder<T>, T>) ImmutableSortedSet.Builder::add,
                (b1, b2) -> b1.addAll(b1.build()),
                ImmutableSortedSet.Builder::build
        );
    }

    public static <K, V, T extends Map.Entry<K,V>> Collector<T, ImmutableMap.Builder<K,V>, ImmutableMap<K,V>> toImmutableMap() {
        return Collector.of(
                ImmutableMap::builder,
                ImmutableMap.Builder::put,
                (b1, b2) -> b1.putAll(b2.build()),
                ImmutableMap.Builder::build
        );
    }

    public static <K, V, T extends Map.Entry<K,V>> Collector<T, ImmutableBiMap.Builder<K,V>, ImmutableBiMap<K,V>> toImmutableBiMap() {
        return Collector.of(
                ImmutableBiMap::builder,
                ImmutableBiMap.Builder::put,
                (b1, b2) -> b1.putAll(b2.build()),
                ImmutableBiMap.Builder::build
        );
    }

    public static <K extends Comparable<K>, V, T extends Map.Entry<K,V>> Collector<T, ImmutableSortedMap.Builder<K,V>, ImmutableSortedMap<K,V>> toImmutableSortedMap() {
        return Collector.of(
                ImmutableSortedMap::naturalOrder,
                (BiConsumer<ImmutableSortedMap.Builder<K, V>, T>) ImmutableSortedMap.Builder::put,
                (b1, b2) -> b1.putAll(b2.build()),
                ImmutableSortedMap.Builder::build
        );
    }

    public static <K, V, T extends Map.Entry<K,V>> Collector<T, ImmutableSortedMap.Builder<K,V>, ImmutableSortedMap<K,V>> toImmutableSortedMap(Comparator<K> keyComparator) {
        return Collector.of(
                () -> ImmutableSortedMap.orderedBy(keyComparator),
                (BiConsumer<ImmutableSortedMap.Builder<K, V>, T>) ImmutableSortedMap.Builder::put,
                (b1, b2) -> b1.putAll(b2.build()),
                ImmutableSortedMap.Builder::build
        );
    }

}
