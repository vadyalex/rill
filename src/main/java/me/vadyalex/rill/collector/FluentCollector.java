package me.vadyalex.rill.collector;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;
import java.util.function.Function;

/**
 * Created by vadyalex.
 */
public interface FluentCollector<T> {

    ImmutableList<T> toImmutableList();

    ImmutableSet<T> toImmutableSet();

    <K, V> ImmutableMap<K, V> toImmutableMap(Function<? super T, ? extends Map.Entry<K, V>> mapper);

    <K, V> ImmutableMap<K, V> toImmutableMap(Function<? super T, K> keyMapper, Function<? super T, V> valueMapper);

}
