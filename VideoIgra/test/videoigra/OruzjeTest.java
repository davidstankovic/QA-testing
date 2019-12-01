package videoigra;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OruzjeTest {
    private Oruzje oruzje;
    private double DELTA = 1e-9;

    @BeforeAll
    void setBeforeAll() {
        oruzje = Mockito.mock(Oruzje.class);
    }

    @Test
    void getStetaShouldReturnAnAdequateStetaValue() {
        Mockito.when(oruzje.getSteta())
                .thenReturn(10.);
        assertEquals(10., oruzje.getSteta(), DELTA);
    }

    @Test
    void getTezinaShouldReturnAnAdequateTezinaValue() {
        Mockito.when(oruzje.getTezina())
                .thenReturn(10.);
        assertEquals(10., oruzje.getTezina(), DELTA);
    }

    @Test
    void getPotrebnaSnagaShouldReturnAnAdequatePotrebnaSnagaValue() {
        Mockito.when(oruzje.getPotrebnaSnaga())
                .thenReturn(10.);
        assertEquals(10., oruzje.getPotrebnaSnaga(), DELTA);
    }
}

//org.junit.platform.commons.JUnitException: @BeforeAll method 'void videoigra.OruzjeTest.setUp()'
//                                  must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS)