package me.vadyalex.rill.collector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
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

    public static <K, V, T extends Map.Entry<K,V>> Collector<T, ImmutableMap.Builder<K,V>, ImmutableMap<K,V>> toImmutableMap() {
        return Collector.of(
                ImmutableMap::builder,
                ImmutableMap.Builder::put,
                (b1, b2) -> b1.putAll(b2.build()),
                ImmutableMap.Builder::build
        );
    }

}
