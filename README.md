## rill


[![Build Status](https://travis-ci.org/vadyalex/rill.svg?branch=develop)](https://travis-ci.org/vadyalex/rill)


This tiny library is fluent facade for Java 8 streams as well as some additional stream operation goodies, like stream zipping and stream concatenation.

### Motivation

- Join streams

 Standard approach to join two streams:
 
```java
        final Stream<Integer> stream1 = Stream.of(1);
        final Stream<Integer> stream2 = Stream.of(2);

        final Stream<Integer> result = Stream.concat(
            stream1,
            stream2
        );
```
```
> [1, 2]
```

Easy, right? But if you want to join four streams?

Standard approach:

 ```java
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
```
```
> [1, 2, 3, 4]
```

Rill way:

```java
        final Stream<Integer> stream1 = Stream.of(1);
        final Stream<Integer> stream2 = Stream.of(2);
        final Stream<Integer> stream3 = Stream.of(3);
        final Stream<Integer> stream4 = Stream.of(4);

        final Stream<Integer> result = Rill
                .from(stream1)
                .join(stream2, stream3, stream4);
```
```
> [1, 2, 3, 4]
```

Filter out something in between joining?

Standard way:
```java
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
```
```
> [-1, 1, 2, 3, 4, 5]
```

Rill way:

```java
        final Stream<Integer> stream1 = Stream.of(-1, 1);
        final Stream<Integer> stream2 = Stream.of(2, 5);
        final Stream<Integer> stream3 = Stream.of(3, 0);
        final Stream<Integer> stream4 = Stream.of(4, 5);

        final Stream<Integer> result = Rill
                .from(stream1)
                .join(stream2)
                .filter(
                        i -> i != 5
                )
                .join(stream3)
                .filter(
                        i -> i != 0
                )
                .join(stream4);
```
```
> [-1, 1, 2, 3, 4, 5]
```

- Zip streams

```java
        final String<String> result = Rill
                .zip(
                        stream0,
                        stream1,
                        stream2,
                        stream3
                )
                .map(
                        quadruple -> quadruple._0.orElse("#UNKNOWN") + ": " + quadruple._1.map(value -> value + " ").orElse("") + quadruple._2.orElse("") + " -> " + quadruple._3.orElse(-1)
                );
```
```java
> [#001: Mr Joe -> 30, #002: Ms Ann -> 27, #003: Ms Olivia -> -1, #UNKNOWN: Jonas -> -1]
```

- Immutable collectors using Google Guava

Drop Google Guava to classpath and collect content of the stream into immutable collections of your choise:

```java
        ImmutableList<String> list = Rill
                .from("A", "B", "C")
                .collect(
                        ImmutableCollectors.toImmutableList()
                );
```
```
> ["A", "B", "C"]
```
```java
        ImmutableSet<String> set = Rill
                .from("A", "B", "C")
                .collect(
                        ImmutableCollectors.toImmutableSet()
                );
```
```
> ["A", "B", "C"]
```

Or using built-in method:

```java
        ImmutableList<String> list = Rill
                .from("A", "B", "C")
                .toImmutableList();
```
```
> ["A", "B", "C"]
```
```java
        ImmutableSet<String> set = Rill
                .from("A", "B", "C")
                .toImmutableSet();
```
```
> ["A", "B", "C"]
```

Make an ImmutableMap:

```java
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
                        0, 1, 2
                )
                .map(
                        couple -> Maps.immutableEntry(
                                couple.first().orElse(""),
                                couple.second().orElse(-1)
                        )
                )
                .collect(
                        ImmutableCollectors.toImmutableMap()
                );
```
```
> {A=0, B=1, C=2}
```

