## rill


[![Build Status](https://travis-ci.org/vadyalex/rill.svg?branch=develop)](https://travis-ci.org/vadyalex/rill)


This tiny library is fluent facade for Java 8 streams as well as some additional stream operation goodies, like stream zipping and stream concatenation.

### Motivation

- Join streams

 Standard approach to join two stream:
```
        final Stream<Integer> stream1 = Stream.of(1);
        final Stream<Integer> stream2 = Stream.of(2);

        final Stream<Integer> result = Stream.concat(
            stream1,
            stream2
        );
```

Easy, right? But if you want to join four streams?

Standard approach:
 ```
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

Rill way:

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

