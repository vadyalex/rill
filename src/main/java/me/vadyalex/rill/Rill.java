package me.vadyalex.rill;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import me.vadyalex.rill.collector.ImmutableCollectors;

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

        if (ts == null)
            return from();
        else
            return from(
                    Arrays.stream(ts)
            );
    }

    public static final <E> Δ<E> from(final E e) {
        return from(
                Stream.of(
                        Objects.requireNonNull(e)
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

    public static final <T, U, R> Δ<R> zipUneven(BiFunction<Optional<T>, Optional<U>, R> zipper, Stream<T> firstStream, Stream<U> anotherStream) {
        return from(firstStream).zipUneven(zipper, anotherStream);
    }

    public static final <T, U, R> Δ<R> zip(BiFunction<T, U, R> zipper, Stream<T> firstStream, Stream<U> anotherStream) {
        return from(firstStream).zip(zipper, anotherStream);
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

        public <U, R> Δ<R> zipUneven(BiFunction<Optional<T>, Optional<U>, R> zipper, Stream<U> stream) {

            Objects.requireNonNull(zipper);
            Objects.requireNonNull(stream);

            final Spliterator<T> internalSpliterator = this.internal.spliterator();
            final Spliterator<U> anotherSpliterator = stream.spliterator();


            final Iterator<T> internalIterator = Spliterators.iterator(internalSpliterator);
            final Iterator<U> anotherIterator = Spliterators.iterator(anotherSpliterator);

            final Iterator<R> resultIterator = new Iterator<R>() {

                public boolean hasNext() {
                    return internalIterator.hasNext() || anotherIterator.hasNext();
                }

                public R next() {
                    return zipper.apply(
                            internalIterator.hasNext() ?
                                    Optional.ofNullable(internalIterator.next())
                                    :
                                    Optional.<T>empty(),
                            anotherIterator.hasNext() ?
                                    Optional.ofNullable(anotherIterator.next())
                                    :
                                    Optional.<U>empty()
                    );
                }
            };


            int characteristics = internalSpliterator.characteristics() & anotherSpliterator.characteristics() & ~(Spliterator.DISTINCT | Spliterator.SORTED);

            long zipSize = ((characteristics & Spliterator.SIZED) != 0) ?
                    Math.min(
                            internalSpliterator.getExactSizeIfKnown(),
                            anotherSpliterator.getExactSizeIfKnown()
                    )
                    :
                    -1;

            final Spliterator<R> result = Spliterators.spliterator(resultIterator, zipSize, characteristics);

            return from(
                    (this.isParallel() || stream.isParallel()) ?
                            StreamSupport.stream(result, true)
                            :
                            StreamSupport.stream(result, false)
            );
        }

        public <U, R> Δ<R> zipUneven(BiFunction<Optional<T>, Optional<U>, R> zipper, U... ts) {
            return this.zipUneven(
                    zipper,
                    from(ts)
            );
        }

        public <U, R> Δ<R> zipUneven(BiFunction<Optional<T>, Optional<U>, R> zipper, Iterable<U> iterable) {
            return this.zipUneven(
                    zipper,
                    from(iterable)
            );
        }

        public <U, R> Δ<R> zipUneven(BiFunction<Optional<T>, Optional<U>, R> zipper, Iterator<U> iterator) {
            return this.zipUneven(
                    zipper,
                    from(iterator)
            );
        }

        @SuppressWarnings("unchecked")
        public <U, R> Δ<R> zip(BiFunction<? super T, U, R> zipper, Stream<U> stream) {

            Objects.requireNonNull(zipper);
            Objects.requireNonNull(stream);

            final Spliterator<T> internalSpliterator = this.internal.spliterator();
            final Spliterator<U> anotherSpliterator = stream.spliterator();


            final Iterator<T> internalIterator = Spliterators.iterator(internalSpliterator);
            final Iterator<U> anotherIterator = Spliterators.iterator(anotherSpliterator);

            final Iterator<R> resultIterator = new Iterator<R>() {

                public boolean hasNext() {
                    return internalIterator.hasNext() && anotherIterator.hasNext();
                }

                public R next() {
                    return zipper.apply(
                            internalIterator.next(),
                            anotherIterator.next()
                    );
                }
            };


            int characteristics = internalSpliterator.characteristics() & anotherSpliterator.characteristics() & ~(Spliterator.DISTINCT | Spliterator.SORTED);

            long zipSize = ((characteristics & Spliterator.SIZED) != 0) ?
                    Math.min(
                            internalSpliterator.getExactSizeIfKnown(),
                            anotherSpliterator.getExactSizeIfKnown()
                    )
                    :
                    -1;

            final Spliterator<R> result = Spliterators.spliterator(resultIterator, zipSize, characteristics);

            return from(
                    (this.isParallel() || stream.isParallel()) ?
                            StreamSupport.stream(result, true)
                            :
                            StreamSupport.stream(result, false)
            );
        }

        public <U, R> Δ<R> zip(BiFunction<? super T, U, R> zipper, U... ts) {
            return this.zip(
                    zipper,
                    from(ts)
            );
        }

        public <U, R> Δ<R> zip(BiFunction<? super T, U, R> zipper, Iterable<U> iterable) {
            return this.zip(
                    zipper,
                    from(iterable)
            );
        }

        public <U, R> Δ<R> zip(BiFunction<? super T, U, R> zipper, Iterator<U> iterator) {
            return this.zip(
                    zipper,
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
