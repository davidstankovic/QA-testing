package videoigra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OdecaTest {
    private Odeca odeca;
    private double DELTA = 1e-9;

    @BeforeAll
    void setBeforeAll() {
        odeca = Mockito.mock(Odeca.class);
    }

    @Test
    void getTezinaShouldReturnAnAdequateValue() {
        Mockito.when(odeca.getTezina())
                .thenReturn(10.);
        assertEquals(10., odeca.getTezina(), DELTA);
    }

    @Test
    void getOdbrambenaVrednostShouldReturnAnAdequateValue() {
        Mockito.when(odeca.getOdbrambenaVrednost())
                .thenReturn(10.);
        assertEquals(10., odeca.getOdbrambenaVrednost(), DELTA);
    }


}