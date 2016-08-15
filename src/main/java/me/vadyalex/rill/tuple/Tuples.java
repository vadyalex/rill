package me.vadyalex.rill.tuple;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by vadyalex.
 */
public class Tuples {

    private static final Empty<?> EMPTY = new Empty<>();

    @SuppressWarnings("unchecked")
    public static final <A> Empty<A> of() {
        return (Empty<A>) EMPTY;
    }

    public static final <A> Monuple<A> of(A _0) {
        return new Monuple<>(_0);
    }

    public static final <A, B> Couple<A, B> of(A _0, B _1) {
        return new Couple<>(_0, _1);
    }

    public static final <A, B, C> Triple<A, B, C> of(A _0, B _1, C _2) {
        return new Triple<>(_0, _1, _2);
    }

    public static final <A, B, C, D> Quadruple<A, B, C, D> of(A _0, B _1, C _2, D _3) {
        return new Quadruple<>(_0, _1, _2, _3);
    }

    public static final <A, B, C, D, E> Pentuple<A, B, C, D, E> of(A _0, B _1, C _2, D _3, E _4) {
        return new Pentuple<>(_0, _1, _2, _3, _4);
    }

    public static final <A, B, C, D, E, F> Hextuple<A, B, C, D, E, F> of(A _0, B _1, C _2, D _3, E _4, F _5) {
        return new Hextuple<>(_0, _1, _2, _3, _4, _5);
    }

    public interface Tuple {
    }

    public static class Empty<A> implements Tuple {
    }

    public static class Monuple<A> extends Empty<A> {

        public final Optional<A> _0;

        private Monuple(A _0) {
            this._0 = Optional.ofNullable(_0);
        }

        public Optional<A> $0() {
            return _0;
        }

        public Optional<A> first() {
            return _0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Monuple)) return false;
            Monuple<?> aMonuple = (Monuple<?>) o;
            return Objects.equals(_0, aMonuple._0);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_0);
        }

    }

    public static class Couple<A, B> extends Monuple<A> {

        public final Optional<B> _1;

        private Couple(A _0, B _1) {
            super(_0);
            this._1 = Optional.ofNullable(_1);
        }

        public Optional<B> $1() {
            return _1;
        }

        public Optional<B> second() {
            return _1;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Couple)) return false;
            Couple<?, ?> aCouple = (Couple<?, ?>) o;
            return Objects.equals(_0, aCouple._0) &&
                    Objects.equals(_1, aCouple._1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_0, _1);
        }
    }

    public static class Triple<A, B, C> extends Couple<A, B> {

        public final Optional<C> _2;

        private Triple(A _0, B _1, C _2) {
            super(_0, _1);
            this._2 = Optional.ofNullable(_2);
        }

        public Optional<C> $2() {
            return _2;
        }

        public Optional<C> third() {
            return _2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Triple)) return false;
            Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
            return Objects.equals(_0, triple._0) &&
                    Objects.equals(_1, triple._1) &&
                    Objects.equals(_2, triple._2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_0, _1, _2);
        }
    }

    public static class Quadruple<A, B, C, D> extends Triple<A, B, C> {

        public final Optional<D> _3;

        private Quadruple(A _0, B _1, C _2, D _3) {
            super(_0, _1, _2);
            this._3 = Optional.ofNullable(_3);
        }

        public Optional<D> $3() {
            return _3;
        }

        public Optional<D> fourth() {
            return _3;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Quadruple)) return false;
            Quadruple<?, ?, ?, ?> quadruple = (Quadruple<?, ?, ?, ?>) o;
            return Objects.equals(_0, quadruple._0) &&
                    Objects.equals(_1, quadruple._1) &&
                    Objects.equals(_2, quadruple._2) &&
                    Objects.equals(_3, quadruple._3);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_0, _1, _2, _3);
        }
    }

    public static class Pentuple<A, B, C, D, E> extends Quadruple<A, B, C, D> {

        public final Optional<E> _4;

        private Pentuple(A _0, B _1, C _2, D _3, E _4) {
            super(_0, _1, _2, _3);
            this._4 = Optional.ofNullable(_4);
        }

        public Optional<E> $4() {
            return _4;
        }

        public Optional<E> fifth() {
            return _4;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pentuple)) return false;
            Pentuple<?, ?, ?, ?, ?> pentuple = (Pentuple<?, ?, ?, ?, ?>) o;
            return Objects.equals(_0, pentuple._0) &&
                    Objects.equals(_1, pentuple._1) &&
                    Objects.equals(_2, pentuple._2) &&
                    Objects.equals(_3, pentuple._3) &&
                    Objects.equals(_4, pentuple._4);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_0, _1, _2, _3, _4);
        }
    }

    public static class Hextuple<A, B, C, D, E, F> extends Pentuple<A, B, C, D, E> {

        public final Optional<F> _5;

        private Hextuple(A _0, B _1, C _2, D _3, E _4, F _5) {
            super(_0, _1, _2, _3, _4);
            this._5 = Optional.ofNullable(_5);
        }


        public Optional<F> $5() {
            return _5;
        }

        public Optional<F> sixth() {
            return _5;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Hextuple)) return false;
            Hextuple<?, ?, ?, ?, ?, ?> hextuple = (Hextuple<?, ?, ?, ?, ?, ?>) o;
            return Objects.equals(_0, hextuple._0) &&
                    Objects.equals(_1, hextuple._1) &&
                    Objects.equals(_2, hextuple._2) &&
                    Objects.equals(_3, hextuple._3) &&
                    Objects.equals(_4, hextuple._4) &&
                    Objects.equals(_5, hextuple._5);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_0, _1, _2, _3, _4, _5);
        }
    }

}
