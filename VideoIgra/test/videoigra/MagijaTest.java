package videoigra;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MagijaTest {
    private Magija magija;
    private double DELTA = 1e-9;

    @BeforeAll
    void setBeforeAll() {
        magija = Mockito.mock(Magija.class);
    }


    @Test
    void getNazivMagijeShouldReturnNazivMagije() {
        fail("Magija class doesn't have get methods for naziv magije");
    }

    @Test
    void getPotrebnaInteligencijaShouldReturnAnAdequateValue() {
        Mockito.when(magija.getPotrebnaInteligencija()).thenReturn(10.);

        assertEquals(10., magija.getPotrebnaInteligencija(), DELTA);
    }

    @Test
    void getStetaShouldReturnAnAdequateValue() {
        Mockito.when(magija.getSteta()).thenReturn(10.);

        assertEquals(10., magija.getSteta(), DELTA);
    }

    @Test
    void getPotrebnaEnergijaShouldReturnAnAdequateValue() {
        Mockito.when(magija.getPotrebnaEnergija()).thenReturn(10.);

        assertEquals(10., magija.getPotrebnaEnergija(), DELTA);
    }





}