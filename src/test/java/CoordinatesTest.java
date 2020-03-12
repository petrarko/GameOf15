import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinatesTest {

    @Test
    void testGetCoordinatesByIndex() {
        assertThat(Coordinates.getCoordinatesByIndex(4)).isEqualTo(new Coordinates(0, 1));
    }
}