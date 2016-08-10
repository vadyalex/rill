package me.vadyalex.rill.tuple;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by vlal.
 */
public class TuplesTest {


    @Test
    public void create_some_tuples() {

        final Tuples.Monuple<String> monuple0 = Tuples.of("A");
        final Tuples.Tuple monuple1 = Tuples.of("A");
        final Tuples.Tuple monuple2 = Tuples.of(0);

        Assertions.assertThat(monuple0).isEqualTo(monuple1);
        Assertions.assertThat(monuple1).isNotEqualTo(monuple2);

        final Tuples.Couple<String, String> couple0 = Tuples.of("A", "B");
        final Tuples.Tuple couple1 = Tuples.of("A", "B");

        Assertions.assertThat(couple0).isEqualTo(couple1);

        final Tuples.Triple<String, String, String> triple0 = Tuples.of("A", "B", "C");
        final Tuples.Tuple triple1 = Tuples.of("A", "B", "C");

        Assertions.assertThat(triple0).isEqualTo(triple1);

        final Tuples.Quadruple<String, String, String, String> quadruple0 = Tuples.of("A", "B", "C", "D");
        final Tuples.Tuple quadruple1 = Tuples.of("A", "B", "C", "D");

        Assertions.assertThat(quadruple0).isEqualTo(quadruple1);

        final Tuples.Pentuple<String, String, String, String, String> pentuple0 = Tuples.of("A", "B", "C", null, "E");
        final Tuples.Tuple pentuple1 = Tuples.of("A", "B", "C", null, "E");

        Assertions.assertThat(pentuple0).isEqualTo(pentuple1);

        final Tuples.Hextuple<String, String, String, String, String, Integer> hextuple0 = Tuples.of("A", "B", "C", "D", "E", 1);
        final Tuples.Tuple hextuple1 = Tuples.of("A", "B", "C", "D", "E", 1);

        Assertions.assertThat(hextuple0).isEqualTo(hextuple1);

    }

    @Test
    public void check_empty_tuple_even_with_different_types() {

        final Tuples.Tuple empty0 = Tuples.of();
        final Tuples.Empty<String> empty1 = Tuples.of();
        final Tuples.Empty<Integer> empty2 = Tuples.of();

        Assertions.assertThat(empty1).isEqualTo(empty0);
        Assertions.assertThat(empty1).isEqualTo(empty2);
        Assertions.assertThat(empty0).isEqualTo(empty2);

    }

}
