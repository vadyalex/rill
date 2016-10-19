package me.vadyalex.rill;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import me.vadyalex.rill.collector.ImmutableCollectors;
import me.vadyalex.rill.tuple.Tuples;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Rill {

    @SuppressWarnings("unchecked")
    public static final <E> Δ<E> from() {
        return from(
                Stream.<E>empty()
        );
    }

    public static final <E> Δ<E> from(final E... ts) {
        return from(
                Stream.of(
                        ts
                )
        );
    }

    public static final <E> Δ<E> from(final E e) {
        return from(
                Stream.of(
                        e
                )
        );
    }

    public static final <E> Δ<E> from(Stream<E> stream) {

        Objects.requireNonNull(stream);

        return (stream instanceof Δ) ?
                (Δ<E>) stream
                :
                new Δ<>(
                        stream
                );
    }

    public static final <E> Δ<E> from(final Collection<E> collection) {

        Objects.requireNonNull(collection);

        return from(
                collection.stream()
        );
    }

    public static final <E> Δ<E> from(final Iterable<E> iterable) {

        Objects.requireNonNull(iterable);

        return from(
                StreamSupport.stream(
                        iterable.spliterator(),
                        false
                )
        );
    }

    public static final <E> Δ<E> from(final Iterator<E> iterator) {

        Objects.requireNonNull(iterator);

        return from(
                StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED),
                        false
                )
        );
    }

    public static final <E> Δ<E> parallel(final Iterable<E> iterable) {

        Objects.requireNonNull(iterable);

        return from(
                StreamSupport.stream(
                        iterable.spliterator(),
                        true
                )
        );
    }

    public static final <E> Δ<E> sequential(final Iterable<E> iterable) {

        Objects.requireNonNull(iterable);

        return from(
                StreamSupport.stream(
                        iterable.spliterator(),
                        false
                )
        );
    }

    public static final <E, U> Δ<Tuples.Couple<E, U>> zip(Stream<E> stream, Stream<U> anotherStream) {
        return from(stream).zip(anotherStream);
    }

    public static final <E, U, K> Δ<Tuples.Triple<E, U, K>> zip(Stream<E> stream0, Stream<U> stream1, Stream<K> stream2) {
        return from(stream0).zip(stream1, stream2);
    }

    public static final <E, U, K, L> Δ<Tuples.Quadruple<E, U, K, L>> zip(Stream<E> stream0, Stream<U> stream1, Stream<K> stream2, Stream<L> stream3) {
        return from(stream0).zip(stream1, stream2, stream3);
    }

    public static final <E, U, K, L, M> Δ<Tuples.Pentuple<E, U, K, L, M>> zip(Stream<E> stream0, Stream<U> stream1, Stream<K> stream2, Stream<L> stream3, Stream<M> stream4) {
        return from(stream0).zip(stream1, stream2, stream3, stream4);
    }

    public static final <E, U, K, L, M, N> Δ<Tuples.Hextuple<E, U, K, L, M, N>> zip(Stream<E> stream0, Stream<U> stream1, Stream<K> stream2, Stream<L> stream3, Stream<M> stream4, Stream<N> stream5) {
        return from(stream0).zip(stream1, stream2, stream3, stream4, stream5);
    }

    public static final class Δ<T> implements Stream<T> {

        private final Stream<T> internal;

        private Δ(Stream<T> stream) {
            Objects.requireNonNull(stream);

            this.internal = stream;
        }

        @Override
        public Δ<T> filter(Predicate<? super T> predicate) {
            return from(
                    this.internal.filter(predicate)
            );
        }

        @Override
        public <R> Δ<R> map(Function<? super T, ? extends R> mapper) {
            return from(
                    this.internal.map(mapper)
            );
        }

        @Override
        public IntStream mapToInt(ToIntFunction<? super T> mapper) {
            return this.internal.mapToInt(mapper);
        }

        @Override
        public LongStream mapToLong(ToLongFunction<? super T> mapper) {
            return this.internal.mapToLong(mapper);
        }

        @Override
        public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
            return this.internal.mapToDouble(mapper);
        }

        @Override
        public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
            return from(
                    this.internal.flatMap(mapper)
            );
        }

        @Override
        public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
            return this.internal.flatMapToInt(mapper);
        }

        @Override
        public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
            return this.internal.flatMapToLong(mapper);
        }

        @Override
        public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
            return this.internal.flatMapToDouble(mapper);
        }

        @Override
        public Δ<T> distinct() {
            return from(
                    this.internal.distinct()
            );
        }

        @Override
        public Δ<T> sorted() {
            return from(
                    this.internal.sorted()
            );
        }

        @Override
        public Δ<T> sorted(Comparator<? super T> comparator) {
            return from(
                    this.internal.sorted(comparator)
            );
        }

        @Override
        public Δ<T> peek(Consumer<? super T> action) {
            return from(
                    this.internal.peek(action)
            );
        }

        @Override
        public Δ<T> limit(long maxSize) {
            return from(
                    this.internal.limit(maxSize)
            );
        }

        @Override
        public Δ<T> skip(long n) {
            return from(
                    this.internal.skip(n)
            );
        }

        @Override
        public void forEach(Consumer<? super T> action) {
            this.internal.forEach(action);
        }

        @Override
        public void forEachOrdered(Consumer<? super T> action) {
            this.internal.forEachOrdered(action);
        }

        @SuppressWarnings("unchecked")
        @Override
        public T[] toArray() {
            return (T[]) this.internal.toArray();
        }

        @Override
        public <A> A[] toArray(IntFunction<A[]> generator) {
            return this.internal.toArray(generator);
        }

        @Override
        public T reduce(T identity, BinaryOperator<T> accumulator) {
            return this.internal.reduce(identity, accumulator);
        }

        @Override
        public Optional<T> reduce(BinaryOperator<T> accumulator) {
            return this.internal.reduce(accumulator);
        }

        @Override
        public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
            return this.internal.reduce(identity, accumulator, combiner);
        }

        @Override
        public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
            return this.internal.collect(supplier, accumulator, combiner);
        }

        @Override
        public <R, A> R collect(Collector<? super T, A, R> collector) {
            return this.internal.collect(collector);
        }

        @Override
        public Optional<T> min(Comparator<? super T> comparator) {
            return this.internal.min(comparator);
        }

        @Override
        public Optional<T> max(Comparator<? super T> comparator) {
            return this.internal.max(comparator);
        }

        @Override
        public long count() {
            return this.internal.count();
        }

        @Override
        public boolean anyMatch(Predicate<? super T> predicate) {
            return this.internal.anyMatch(predicate);
        }

        @Override
        public boolean allMatch(Predicate<? super T> predicate) {
            return this.internal.allMatch(predicate);
        }

        @Override
        public boolean noneMatch(Predicate<? super T> predicate) {
            return this.internal.noneMatch(predicate);
        }

        @Override
        public Optional<T> findFirst() {
            return this.internal.findFirst();
        }

        @Override
        public Optional<T> findAny() {
            return this.internal.findAny();
        }

        public Optional<T> findLast() {
            return this.internal.reduce( (unused, result) -> result );
        }

        @Override
        public Iterator<T> iterator() {
            return this.internal.iterator();
        }

        @Override
        public Spliterator<T> spliterator() {
            return this.internal.spliterator();
        }

        @Override
        public boolean isParallel() {
            return this.internal.isParallel();
        }

        @Override
        public Δ<T> sequential() {
            return from(
                    this.internal.sequential()
            );
        }

        @Override
        public Δ<T> parallel() {
            return from(
                    this.internal.parallel()
            );
        }

        @Override
        public Δ<T> unordered() {
            return from(
                    this.internal.unordered()
            );
        }

        @Override
        public Δ<T> onClose(Runnable closeHandler) {
            return from(
                    this.internal.onClose(closeHandler)
            );

        }

        @Override
        public void close() {
            this.internal.close();
        }

        public <U> Δ<Tuples.Couple<T, U>> zip(Stream<U> stream) {
            Objects.requireNonNull(stream);

            final Spliterator<T> internalSpliterator = this.internal.spliterator();
            final Spliterator<U> anotherSpliterator = stream.spliterator();


            final Iterator<T> internalIterator = Spliterators.iterator(internalSpliterator);
            final Iterator<U> anotherIterator = Spliterators.iterator(anotherSpliterator);

            final Iterator<Tuples.Couple<T, U>> resultIterator = new Iterator<Tuples.Couple<T, U>>() {

                public boolean hasNext() {
                    return internalIterator.hasNext() || anotherIterator.hasNext();
                }

                public Tuples.Couple<T, U> next() {
                    return Tuples.of(
                            internalIterator.hasNext() ?
                                    internalIterator.next()
                                    :
                                    null,
                            anotherIterator.hasNext() ?
                                    anotherIterator.next()
                                    :
                                    null
                    );
                }
            };


            final int characteristics = internalSpliterator.characteristics() & anotherSpliterator.characteristics() & ~(Spliterator.DISTINCT | Spliterator.SORTED);

            final long zipSize = ((characteristics & Spliterator.SIZED) != 0) ?
                    Math.max(
                            internalSpliterator.getExactSizeIfKnown(),
                            anotherSpliterator.getExactSizeIfKnown()
                    )
                    :
                    -1;

            final Spliterator<Tuples.Couple<T, U>> result = Spliterators.spliterator(resultIterator, zipSize, characteristics);

            return from(
                    (this.isParallel() || stream.isParallel()) ?
                            StreamSupport.stream(result, true)
                            :
                            StreamSupport.stream(result, false)
            );
        }

        public <U, V> Δ<Tuples.Triple<T, U, V>> zip(Stream<U> stream1, Stream<V> stream2) {
            return this
                    .zip(
                            stream1
                    )
                    .zip(
                            stream2
                    )
                    .map(
                            couples -> Tuples.of(
                                    couples.$0().flatMap(Tuples.Couple::$0).orElse(null),
                                    couples.$0().flatMap(Tuples.Couple::$1).orElse(null),
                                    couples.$1().orElse(null)
                            )
                    );
        }

        public <U, V, K> Δ<Tuples.Quadruple<T, U, V, K>> zip(Stream<U> stream1, Stream<V> stream2, Stream<K> stream3) {
            return this
                    .zip(
                            stream1, stream2
                    )
                    .zip(
                            stream3
                    )
                    .map(
                            couples -> Tuples.of(
                                    couples.$0().flatMap(Tuples.Triple::$0).orElse(null),
                                    couples.$0().flatMap(Tuples.Triple::$1).orElse(null),
                                    couples.$0().flatMap(Tuples.Triple::$2).orElse(null),
                                    couples.$1().orElse(null)
                            )
                    );
        }

        public <U, V, K, L> Δ<Tuples.Pentuple<T, U, V, K, L>> zip(Stream<U> stream1, Stream<V> stream2, Stream<K> stream3, Stream<L> stream4) {
            return this
                    .zip(
                            stream1, stream2, stream3
                    )
                    .zip(
                            stream4
                    )
                    .map(
                            couples -> Tuples.of(
                                    couples.$0().flatMap(Tuples.Quadruple::$0).orElse(null),
                                    couples.$0().flatMap(Tuples.Quadruple::$1).orElse(null),
                                    couples.$0().flatMap(Tuples.Quadruple::$2).orElse(null),
                                    couples.$0().flatMap(Tuples.Quadruple::$3).orElse(null),
                                    couples.$1().orElse(null)
                            )
                    );
        }

        public <U, V, K, L, M> Δ<Tuples.Hextuple<T, U, V, K, L, M>> zip(Stream<U> stream1, Stream<V> stream2, Stream<K> stream3, Stream<L> stream4, Stream<M> stream5) {
            return this
                    .zip(
                            stream1, stream2, stream3, stream4
                    )
                    .zip(
                            stream5
                    )
                    .map(
                            couples -> Tuples.of(
                                    couples.$0().flatMap(Tuples.Pentuple::$0).orElse(null),
                                    couples.$0().flatMap(Tuples.Pentuple::$1).orElse(null),
                                    couples.$0().flatMap(Tuples.Pentuple::$2).orElse(null),
                                    couples.$0().flatMap(Tuples.Pentuple::$3).orElse(null),
                                    couples.$0().flatMap(Tuples.Pentuple::$4).orElse(null),
                                    couples.$1().orElse(null)
                            )
                    );
        }

        public <U> Δ<Tuples.Couple<T, U>> zip(U... ts) {
            return this.zip(
                    from(ts)
            );
        }

        public <U> Δ<Tuples.Couple<T, U>> zip(Iterable<U> iterable) {
            return this.zip(
                    from(iterable)
            );
        }

        public <U> Δ<Tuples.Couple<T, U>> zip(Iterator<U> iterator) {
            return this.zip(
                    from(iterator)
            );
        }

        public Δ<T> join(Stream<? extends T> stream) {
            return from(
                    Stream.concat(
                            this.internal,
                            Objects.requireNonNull(stream)
                    )
            );
        }

        public Δ<T> join(Stream<? extends T>... streams) {
            return from(streams)
                    .reduce(
                            this,
                            (Δ<T> result, Stream<? extends T> stream) -> result.join(stream),
                            (t1, t2) -> from(t1).join(t2)
                    );
        }

        public Δ<T> join(T t) {
            return join(
                    from(
                            t
                    )
            );
        }

        public Δ<T> join(T... ts) {
            return join(
                    from(
                            ts
                    )
            );
        }

        public Δ<T> join(Iterable<? extends T> iterable) {
            return join(
                    from(
                            iterable
                    )
            );
        }

        public Δ<T> join(Iterator<? extends T> iterator) {
            return join(
                    from(
                            iterator
                    )
            );
        }

        public ImmutableList<T> toImmutableList() {
            return collect(
                    ImmutableCollectors.toImmutableList()
            );
        }

        public ImmutableSet<T> toImmutableSet() {
            return collect(
                    ImmutableCollectors.toImmutableSet()
            );
        }

        public <K, V> ImmutableMap<K, V> toImmutableMap(Function<? super T, ? extends Map.Entry<K, V>> mapper) {
            return map(
                    Objects.requireNonNull(mapper)
            ).collect(
                    ImmutableCollectors.toImmutableMap()
            );
        }

        public <K, V> ImmutableMap<K, V> toImmutableMap(Function<? super T, K> keyMapper, Function<? super T, V> valueMapper) {
            return map(
                    t -> Maps.immutableEntry(
                            Objects.requireNonNull(keyMapper).apply(t),
                            Objects.requireNonNull(valueMapper).apply(t)
                    )
            ).collect(
                    ImmutableCollectors.toImmutableMap()
            );

        }

    }

}
