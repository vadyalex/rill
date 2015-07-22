package me.vadyalex.rill;

/**
 * Created by vadyalex.
 */
public interface Streamable<T> {

    @SuppressWarnings("unchecked")
    public default Rill.Δ<T> stream() {

        if (this instanceof Iterable)
            return Rill.from(
                    Iterable.class.cast(this)
            );

        return Rill.EMPTY;
    }

}
