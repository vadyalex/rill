package me.vadyalex.rill;

import me.vadyalex.rill.collector.FluentCollector;
import me.vadyalex.rill.tuple.Tuples;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by vadyalex.
 */
public interface FluentStream<T> extends Stream<T> {

    @Override
    FluentStream<T> filter(Predicate<? super T> predicate);

    @Override
    <R> FluentStream<R> map(Function<? super T, ? extends R> mapper);

    @Override
    <R> FluentStream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);

    @Override
    FluentStream<T> distinct();

    @Override
    FluentStream<T> sorted();

    @Override
    FluentStream<T> sorted(Comparator<? super T> comparator);

    @Override
    FluentStream<T> peek(Consumer<? super T> action);

    @Override
    FluentStream<T> limit(long maxSize);

    @Override
    FluentStream<T> skip(long n);

    FluentCollector<T> collect();

    Optional<T> findLast();

    @Override
    FluentStream<T> sequential();

    @Override
    FluentStream<T> parallel();

    @Override
    FluentStream<T> unordered();

    @Override
    FluentStream<T> onClose(Runnable closeHandler);

    <U> FluentStream<Tuples.Couple<T, U>> zip(Stream<U> stream);

    <U, V> FluentStream<Tuples.Triple<T, U, V>> zip(Stream<U> stream1, Stream<V> stream2);

    <U, V, K> FluentStream<Tuples.Quadruple<T, U, V, K>> zip(Stream<U> stream1, Stream<V> stream2, Stream<K> stream3);

    <U, V, K, L> FluentStream<Tuples.Pentuple<T, U, V, K, L>> zip(Stream<U> stream1, Stream<V> stream2, Stream<K> stream3, Stream<L> stream4);

    <U, V, K, L, M> FluentStream<Tuples.Hextuple<T, U, V, K, L, M>> zip(Stream<U> stream1, Stream<V> stream2, Stream<K> stream3, Stream<L> stream4, Stream<M> stream5);

    <U> FluentStream<Tuples.Couple<T, U>> zip(U... ts);

    <U> FluentStream<Tuples.Couple<T, U>> zip(Iterable<U> iterable);

    <U> FluentStream<Tuples.Couple<T, U>> zip(Iterator<U> iterator);

    FluentStream<T> join(Stream<? extends T> stream);

    FluentStream<T> join(Stream<? extends T>... streams);

    FluentStream<T> join(T t);

    FluentStream<T> join(T... ts);

    FluentStream<T> join(Iterable<? extends T> iterable);

    FluentStream<T> join(Iterator<? extends T> iterator);

}
