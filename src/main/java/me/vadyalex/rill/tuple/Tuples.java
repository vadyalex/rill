package me.vadyalex.rill.tuple;

import java.util.Objects;

/**
 * Created by vadyalex.
 */
public class Tuples {

    public static final <A> Empty<A> of() {
        return new Empty<>();
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

    public static final <A, B, C, D, E> Quintuple<A, B, C, D, E> of(A _0, B _1, C _2, D _3, E _4) {
        return new Quintuple<>(_0, _1, _2, _3, _4);
    }

    public interface Tuple {
    }

    public static final class Empty<A> implements Tuple {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Empty)) return false;
            return true;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this);
        }

    }

    public static final class Monuple<A> implements Tuple {

        public final A _0;

        private Monuple(A _0) {
            this._0 = _0;
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

    public static final class Couple<A, B> implements Tuple {

        public final A _0;
        public final B _1;

        private Couple(A _0, B _1) {
            this._0 = _0;
            this._1 = _1;
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

    public static final class Triple<A, B, C> implements Tuple {

        public final A _0;
        public final B _1;
        public final C _2;

        private Triple(A _0, B _1, C _2) {
            this._0 = _0;
            this._1 = _1;
            this._2 = _2;
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

    public static final class Quadruple<A, B, C, D> implements Tuple {

        public final A _0;
        public final B _1;
        public final C _2;
        public final D _3;

        private Quadruple(A _0, B _1, C _2, D _3) {
            this._0 = _0;
            this._1 = _1;
            this._2 = _2;
            this._3 = _3;
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

    public static final class Quintuple<A, B, C, D, E> implements Tuple {

        public final A _0;
        public final B _1;
        public final C _2;
        public final D _3;
        public final E _4;

        private Quintuple(A _0, B _1, C _2, D _3, E _4) {
            this._0 = _0;
            this._1 = _1;
            this._2 = _2;
            this._3 = _3;
            this._4 = _4;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Quintuple)) return false;
            Quintuple<?, ?, ?, ?, ?> quintuple = (Quintuple<?, ?, ?, ?, ?>) o;
            return Objects.equals(_0, quintuple._0) &&
                    Objects.equals(_1, quintuple._1) &&
                    Objects.equals(_2, quintuple._2) &&
                    Objects.equals(_3, quintuple._3) &&
                    Objects.equals(_4, quintuple._4);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_0, _1, _2, _3, _4);
        }
    }

}
