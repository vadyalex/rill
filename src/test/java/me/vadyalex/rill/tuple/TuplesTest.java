package me.vadyalex.rill.tuple;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by vlal.
 */
public class TuplesTest {


    @Test
    public void create_some_tuples() {

        final Tuples.Empty empty0 = Tuples.of();
        final Tuples.Tuple empty1 = Tuples.of();

        Assertions.assertThat(empty0).isEqualTo(empty1);

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

        final Tuples.Quintuple<String, String, String, String, String> quintuple0 = Tuples.of("A", "B", "C", "D", "E");
        final Tuples.Tuple quintuple1 = Tuples.of("A", "B", "C", "D", "E");

        Assertions.assertThat(quintuple0).isEqualTo(quintuple1);

    }

}
