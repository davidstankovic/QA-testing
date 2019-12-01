package videoigra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class IgracTest {
    private final double DELTA = 1e-9;
    private Igrac napadac;
    private Igrac meta;
    private Magija magija = Mockito.mock(Magija.class);
    private Oruzje oruzje = Mockito.mock(Oruzje.class);
    private Odeca odeca = Mockito.mock(Odeca.class);

    @BeforeEach
    //initialisation
    void startBefore() {
        ArrayList<Oruzje> oruzja = new ArrayList<>();
        ArrayList<Magija> magije = new ArrayList<>();
        ArrayList<Odeca> odece = new ArrayList<>();
        magije.add(magija);
        oruzja.add(oruzje);
        odece.add(odeca);

        napadac = new Igrac("napadac", 70, 70, 70, 70, Igrac.Stanje.AGRESIVNO, oruzja, odece, magije);
        meta= new Igrac("meta", 80, 70, 80, 80, Igrac.Stanje.DEFANZIVNO, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    }

// getters and setters
    @Test
    void getNazivShouldReturnNazivIfIgracIsCreated() {

        assertEquals("napadac", napadac.getNaziv());
    }

    @Test
    void setNazivShouldSetNaziv() {
        napadac.setNaziv("napadac2");
        String actual = napadac.getNaziv();

        assertEquals("napadac2", actual);
    }

    @Test
    void getZdravljeShouldReturnZdravljeIfIgracIsCreated() {
        double actual = napadac.getZdravlje();

        assertEquals(70., actual, DELTA);
    }

    @Test
    void setZdravljeShouldSetZdravlje() {
        napadac.setZdravlje(100.);
        double actual = napadac.getZdravlje();

        assertEquals(100., actual,DELTA);
    }

    @Test
    void setEnergijaShouldSetEnergija() {
        napadac.setEnergija(100.);
        double actual = napadac.getEnergija();

        assertEquals(100., actual,DELTA);
    }

    @Test
    void getEnergijaShouldReturnEnergijaIfIgracIsCreated() {
        double actual = napadac.getEnergija();

        assertEquals(70., actual, DELTA);
    }

    @Test
    void setSnagaShouldSetSnaga() {
        napadac.setSnaga(100);
        int actual = napadac.getSnaga();

        assertEquals(100, actual);
    }

    @Test
    void getSnagaShouldReturnSnagaIfIgracIsCreated() {
        int actual = napadac.getSnaga();

        assertEquals(70, actual);
    }

    @Test
    void setInteligencijaShouldSetInteligencija() {

        napadac.setInteligencija(100);
        int actual = napadac.getInteligencija();

        assertEquals(100, actual);
    }

    @Test
    void getInteligencijaShouldReturnInteligencijaIfIgracIsCreated() {
        int actual = napadac.getInteligencija();

        assertEquals(70, actual);
    }

    @Test
    void setStanjeShouldSetStanje() {
        napadac.setStanje(Igrac.Stanje.PASIVNO);

        assertEquals( Igrac.Stanje.PASIVNO, napadac.getStanje());
    }

    @Test
    void getStanjeShouldReturnStanjeDEFAZIVNO() {
        napadac.setStanje(Igrac.Stanje.DEFANZIVNO);

        assertEquals(Igrac.Stanje.DEFANZIVNO, napadac.getStanje());
    }

    @Test
    void getStanjeShouldReturnStanjePASIVNO() {
        napadac.setStanje(Igrac.Stanje.PASIVNO);

        assertEquals(Igrac.Stanje.PASIVNO, napadac.getStanje());
    }

    @Test
    void getStanjeShouldReturnStanjeAGRESINVOIfIgracIsCreatedWithAgresivnoStanje() {

        assertEquals(Igrac.Stanje.AGRESIVNO, napadac.getStanje());
    }

    @Test
    void setOruzjaShouldSetAnArrayListOfOruzja() {
        ArrayList<Oruzje>  expected = new ArrayList<>();
        expected.add(oruzje);
        expected.add(Mockito.mock(Oruzje.class));
        napadac.setOruzja(expected);

        assertEquals(expected, napadac.getOruzja());
    }

    @Test
    void getOruzjaShouldReturnAListOfOruzjaIfIgracIsCreated() {
        ArrayList<Oruzje> expected = new ArrayList<>();
        expected.add(oruzje);

        assertEquals(expected, napadac.getOruzja());
    }

    @Test
    void setOdecaShouldSetAnArrayListOfOdeca() {
        ArrayList<Odeca> expected = new ArrayList<>();
        expected.add(odeca);
        expected.add(Mockito.mock(Odeca.class));
        napadac.setOdeca(expected);

        assertEquals(expected, napadac.getOdeca());
    }

    @Test
    void getOdecaShouldReturnAnArrayListOfOdecaIfIgracIsCreated() {
        ArrayList<Odeca> expected = new ArrayList<>();
        expected.add(odeca);

        assertEquals(expected, napadac.getOdeca());
    }

    @Test
    void setMagijeShouldSetAnArrayListOfMagija() {
        ArrayList<Magija> expected = new ArrayList<>();
        expected.add(Mockito.mock(Magija.class));
        expected.add(magija);
        napadac.setMagije(expected);

        assertEquals(expected, napadac.getMagije());
    }

    @Test
    void getMagijeShouldReturnAListOfMagijeIfIgracIsCreated() {
        ArrayList<Magija> expected = new ArrayList<>();
        expected.add(magija);

        assertEquals(expected, napadac.getMagije());
    }



    //toString method
    @Test
    void toStringShouldReturnAnAdequateStringIfIgracIsCreated(){

        assertEquals("(napadac, 70.0/70.0, STR:70, INT:70)", napadac.toString());
    }

    //napadniIgraca
    @Test
    void napadniIgracaShouldReturnZeroIfNapadacHasEnergyThatEqualsTwenty() {
        napadac.setEnergija(20.);
        napadac.setSnaga(99);
        Mockito.when(napadac.getOruzja().get(0).getPotrebnaSnaga()).thenReturn(100.);
        Mockito.when(napadac.getOruzja().get(0).getSteta()).thenReturn(100.);

        double expected = 0;
        double actual = napadac.napadniIgraca(0, meta);

        assertEquals(expected, actual,DELTA);
    }

    // If igrac has less than 20 energy, steta is being set to 0.
    @Test
    void  napadniIgracaShouldReturnZeroIfNapadacHasEnergyLessThenTwenty(){
        napadac.setEnergija(19.);
        napadac.setSnaga(99);
        Mockito.when(napadac.getOruzja().get(0).getPotrebnaSnaga()).thenReturn(90.);
        Mockito.when(napadac.getOruzja().get(0).getSteta()).thenReturn(90.);

        double expected = 0;
        double actual = napadac.napadniIgraca(0, meta);

        assertEquals(expected, actual, DELTA);

    }

    //null pointer exception and index out of bound
    // props to Jelena Krstović
    @Test
    void napadniIgracaShouldThrowAnIllegalArgumentExceptionIfAtLeastOneOruzjeIsNull() { //program accepts IllegalArgumentException
        napadac.getOruzja().add(null);

        assertThrows(IllegalArgumentException.class,()->{
            napadac.napadniIgraca(napadac.getOruzja().size()-1,meta);
        });
    }

    @Test
    void napadniIgracaShouldThrowAnIllegalArgumentExceptionIfIndexOfArrayListOruzjeDoesNotExist() { //program accepts IllegalArgumentException
        napadac.getOruzja().add(null);

        assertThrows(IllegalArgumentException.class,()->{
            napadac.napadniIgraca(napadac.getOruzja().size(),meta);
        });
    }


    // If a player doesn't have enough power, weapons damage is halved.
    // in case that player has more than 20 energy, use 21.
    // Defensive stance
    @Test
    void napadniIgracaShouldReturnStetaHalfStetaOruzjaIfNapadacHasEnergyThatIsGreaterThanTwentyAndStrengthIsLessThanNeededWhenStanjeDefanzivno() {
        napadac.setEnergija(21.);
        napadac.setSnaga(99);
        napadac.setStanje(Igrac.Stanje.DEFANZIVNO);
        Mockito.when(napadac.getOruzja().get(0).getPotrebnaSnaga()).thenReturn(100.);
        Mockito.when(napadac.getOruzja().get(0).getSteta()).thenReturn(100.);

        double expected = (napadac.getOruzja().get(0).getSteta() / 2 + (napadac.getSnaga() * 2)) * 0.8;
        double actual = napadac.napadniIgraca(0, meta);

        assertEquals(expected, actual, DELTA);
    }
    
    
    @Test
    void napadniIgracaShouldReturnStetaHalfStetaOruzjaIfNapadacHasEnergyThatIsGreaterThanTwentyAndStrengthLessThanNeededWhenStanjePasivno() {
        napadac.setEnergija(21.);
        napadac.setSnaga(99);
        napadac.setStanje(Igrac.Stanje.PASIVNO);
        Mockito.when(napadac.getOruzja().get(0).getPotrebnaSnaga()).thenReturn(100.);
        Mockito.when(napadac.getOruzja().get(0).getSteta()).thenReturn(100.);

        double expected = (napadac.getOruzja().get(0).getSteta() / 2 + (napadac.getSnaga() * 2));
        double actual = napadac.napadniIgraca(0, meta);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    void napadniIgracaShouldReturnStetaHalfStetaOruzjaIfNapadacHasEnergyThatIsGreaterThanTwentyAndStrengthLessThanNeededWhenStanjeAgresivno() {
        napadac.setEnergija(21.);
        napadac.setSnaga(49);
        Mockito.when(napadac.getOruzja().get(0).getPotrebnaSnaga()).thenReturn(100.);
        Mockito.when(napadac.getOruzja().get(0).getSteta()).thenReturn(100.);

        double expected = (napadac.getOruzja().get(0).getSteta() / 2 + (napadac.getSnaga() * 2))*1.2;
        double actual = napadac.napadniIgraca(0, meta);

        assertEquals(expected, actual, DELTA);
    }

    //similar to
    //          napadniIgracaShouldReturnStetaHalfStetaOruzjaIfNapadacHasEnergyThatIsGreaterThanTwentyAndStrengthIsLessThanNeededWhenStanjeDefanzivno
    //if energy is greater than 20, but in this case power is greater than power needed.
    // Defensive stance
    @Test
    void napadniIgracaShouldReturnStetaIfNapadacHasEnergyThatIsGreaterThanTwentyAndStrengthGreaterThanNeededWhenStanjeDefanzivno() {
        napadac.setEnergija(21.);
        napadac.setStanje(Igrac.Stanje.DEFANZIVNO);
        Mockito.when(napadac.getOruzja().get(0).getPotrebnaSnaga()).thenReturn(60.);
        Mockito.when(napadac.getOruzja().get(0).getSteta()).thenReturn(60.);
        double expected = (napadac.getOruzja().get(0).getSteta()  + (napadac.getSnaga() * 2)) * 0.8;
        double actual = napadac.napadniIgraca(0, meta);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    void napadniIgracaShouldReturnStetaIfNapadacHasEnergyThatIsGreaterThanTwentyAndStrengthGreaterThanNeededWhenStanjePasivno() {
        napadac.setEnergija(21.);
        napadac.setStanje(Igrac.Stanje.PASIVNO);
        Mockito.when(napadac.getOruzja().get(0).getPotrebnaSnaga()).thenReturn(100.);
        Mockito.when(napadac.getOruzja().get(0).getSteta()).thenReturn(100.);

        double expected = (napadac.getOruzja().get(0).getSteta() / 2 + (napadac.getSnaga() * 2));
        double actual = napadac.napadniIgraca(0, meta);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    void napadniIgracaShouldReturnStetaIfNapadacHasEnergyThatIsGreaterThanTwentyAndStrengthGreaterThanNeededWhenStanjeAgresivno() {
        napadac.setEnergija(21.);
        napadac.setSnaga(100);
        Mockito.when(napadac.getOruzja().get(0).getPotrebnaSnaga()).thenReturn(90.);
        Mockito.when(napadac.getOruzja().get(0).getSteta()).thenReturn(90.);

        double expected = (napadac.getOruzja().get(0).getSteta()  + (napadac.getSnaga() * 2)) * 1.2;
        double actual = napadac.napadniIgraca(0, meta);

        assertEquals(expected, actual, DELTA);
    }

    //similar to
    //          napadniIgracaShouldReturnStetaHalfStetaOruzjaIfNapadacHasEnergyThatIsGreaterThanTwentyAndStrengthIsLessThanNeededWhenStanjeDefanzivno
    //if energy is greater than 20, but in this case power is equal to power needed.
    // Defensive stance

    @Test
    void napadniIgracaShouldReturnStetaHalfStetaOruzjaIfNapadacHasEnergyThatIsGreaterThanTwentyAndStrengthEqualToNeededWhenStanjePasivno() {
        napadac.setEnergija(21.);
        napadac.setSnaga(100);
        napadac.setStanje(Igrac.Stanje.PASIVNO);
        Mockito.when(napadac.getOruzja().get(0).getPotrebnaSnaga()).thenReturn(100.);
        Mockito.when(napadac.getOruzja().get(0).getSteta()).thenReturn(100.);

        double expected = (napadac.getOruzja().get(0).getSteta()  + (napadac.getSnaga() * 2));
        double actual = napadac.napadniIgraca(0, meta);

       assertEquals(expected, actual, DELTA);
    }


    //null pointer exception and index out of bound
    // props to Jelena Krstović
    @Test
    void odbraniSeShouldThrowAnIllegalArgumentExceptionIfAtLeastOneOruzjeIsNull() {
        napadac.getOruzja().add(null);

        assertThrows(IllegalArgumentException.class,()->{
            napadac.odbraniSe(10);});
    }

    @Test
    void odbraniSeShouldThrowAnIllegallDolaznaStetaExceptionIfAtLeastOneOdecaIsNull() {
        napadac.getOdeca().add(null);

        assertThrows(IllegalArgumentException.class,()->{
            napadac.odbraniSe(10);});
    }

    @Test
    void odbraniSeShouldThrowAnIllegalArgumentExceptionIfDolaznaStetaIsLessThanZero() {

        assertThrows(IllegalArgumentException.class, () -> napadac.odbraniSe(-10));
    }

    @Test
    void odbraniSeShouldThrowAnIllegalArgumentExceptionIfDolaznaStetaIsMinusOne() {

        assertThrows(IllegalArgumentException.class, () -> napadac.odbraniSe(-1));
    }

// if this method gets negative value, returns IllegalArgumentException.
    @Test
    void odbraniSeShouldThrowAnIllegalArgumentExceptionIfDolaznaStetaIsZero() {

        assertThrows(IllegalArgumentException.class, () -> napadac.odbraniSe(0));
    }


// this method can be passed 0, enough reasons to test him
    @Test
    void odbraniSeShouldReturnStetaIfDolaznaStetaIsZeroAndEquipmentWeightsLessThanMaxWeightWhenStanjeAgresivno() {
        napadac.setSnaga(10);
        Mockito.when(napadac.getOdeca().get(0).getOdbrambenaVrednost()).thenReturn(10.);
        Mockito.when(napadac.getOdeca().get(0).getTezina()).thenReturn(10.);
        Mockito.when(napadac.getOruzja().get(0).getTezina()).thenReturn(10.);

        double expected = 0;
        double actual = napadac.odbraniSe(0);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    void odbraniSeShouldReturnStetaIfDolaznaStetaIsGreaterThanZeroAndEquipmentWeightIsGreaterThanMaxWeighWhenStanjeDefanzivno() {
        napadac.setSnaga(10);
        napadac.setStanje(Igrac.Stanje.DEFANZIVNO);

        Mockito.when(napadac.getOdeca().get(0).getOdbrambenaVrednost()).thenReturn(10.);
        Mockito.when(napadac.getOdeca().get(0).getTezina()).thenReturn(10.);
        Mockito.when(napadac.getOruzja().get(0).getTezina()).thenReturn(21.);

        double expected = 20*1.5/ (0.9*napadac.getOdeca().get(0).getOdbrambenaVrednost() *0.25 );
        double actual = napadac.odbraniSe(20);

        assertEquals(expected, actual, DELTA);
    }


// incoming damage is greater than zero and equipment weight is grater than max weight
// passive stance
    @Test
    void odbraniSeShouldReturnStetaIfDolaznaStetaIsGreaterThanZeroAndEquipmentWeightIsGreaterThanMaxWeightWhenStanjePasivno() {
        napadac.setStanje(Igrac.Stanje.PASIVNO);
        napadac.setSnaga(10);
        Mockito.when(napadac.getOdeca().get(0).getOdbrambenaVrednost()).thenReturn(10.);
        Mockito.when(napadac.getOdeca().get(0).getTezina()).thenReturn(10.);
        Mockito.when(napadac.getOruzja().get(0).getTezina()).thenReturn(21.);

        double expected = 20*1.5/ (0.9*napadac.getOdeca().get(0).getOdbrambenaVrednost() * 0.15);
        double actual = napadac.odbraniSe(20);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    void odbraniSeShouldReturnStetaIfDolaznaStetaIsGreaterThanZeroAndEquipmentWeightIsGreaterThanMaxWeightWhenStanjeAgresivno() {
        napadac.setSnaga(10);
        Mockito.when(napadac.getOdeca().get(0).getOdbrambenaVrednost()).thenReturn(10.);
        Mockito.when(napadac.getOdeca().get(0).getTezina()).thenReturn(10.);
        Mockito.when(napadac.getOruzja().get(0).getTezina()).thenReturn(21.);

        double expected = 20*1.5/(0.9*napadac.getOdeca().get(0).getOdbrambenaVrednost() * 0.08);
        double actual = napadac.odbraniSe(20);

        assertEquals(expected, actual, DELTA);
    }
// incoming damage is greater than zero and equipment weight is less than max weight
// defensive stance
    @Test
    void odbraniSeShouldReturnStetaIfDolaznaStetaGreaterThanZeroAndEquipmentWeightsLessThanMaxWeightWhenStanjeDefanzivno() {
        napadac.setSnaga(10);
        napadac.setStanje(Igrac.Stanje.DEFANZIVNO);
        Mockito.when(napadac.getOdeca().get(0).getOdbrambenaVrednost()).thenReturn(10.);
        Mockito.when(napadac.getOdeca().get(0).getTezina()).thenReturn(10.);
        Mockito.when(napadac.getOruzja().get(0).getTezina()).thenReturn(10.);

        double expected = 20/(napadac.getOdeca().get(0).getOdbrambenaVrednost() * 0.3);
        double actual = napadac.odbraniSe(20);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    void odbraniSeShouldReturnStetaIfDolaznaStetaIsGreaterThanZeroAndEquipmentWeightIsLessThanMaxWeightWhenStanjePasivno() {
        napadac.setSnaga(10);
        napadac.setStanje(Igrac.Stanje.PASIVNO);
        Mockito.when(napadac.getOdeca().get(0).getOdbrambenaVrednost()).thenReturn(10.);
        Mockito.when(napadac.getOdeca().get(0).getTezina()).thenReturn(10.);
        Mockito.when(napadac.getOruzja().get(0).getTezina()).thenReturn(10.);

        double expected = 20/(napadac.getOdeca().get(0).getOdbrambenaVrednost() * 0.2);
        double actual = napadac.odbraniSe(20);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    void odbraniSeShouldReturnStetaIfDolaznaStetaGreaterThanZeroAndEquipmentWeightsLessThanMaxWeightWhenStanjeAgresivno() {
        napadac.setSnaga(10);
        Mockito.when(napadac.getOdeca().get(0).getOdbrambenaVrednost()).thenReturn(10.);
        Mockito.when(napadac.getOdeca().get(0).getTezina()).thenReturn(10.);
        Mockito.when(napadac.getOruzja().get(0).getTezina()).thenReturn(10.);

        double expected = 20/(napadac.getOdeca().get(0).getOdbrambenaVrednost() * 0.1);
        double actual = napadac.odbraniSe(20);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    void odbraniSeShouldReturnStetaIfDolaznaStetaGreaterThanZeroAndMaxWeightEqualsToEquipmentWeightWhenStanjeAgresivno() {
        napadac.setSnaga(10);
        Mockito.when(napadac.getOdeca().get(0).getOdbrambenaVrednost()).thenReturn(10.);
        Mockito.when(napadac.getOdeca().get(0).getTezina()).thenReturn(10.);
        Mockito.when(napadac.getOruzja().get(0).getTezina()).thenReturn(20.);

        double expected = 10/(napadac.getOdeca().get(0).getOdbrambenaVrednost() * 0.1);
        double actual = napadac.odbraniSe(10);

        assertEquals(expected, actual, DELTA);
    }

    @Test
    void upotrebiMagijuShouldReturnStetaIfInteligencijaIsGreaterThanMagijaInteligencijaAndEnergyIsLessThanMagijaEnergyWhenNapadacHasEnoughHealth() {
        napadac.setEnergija(10);
        napadac.setZdravlje(100.);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaInteligencija()).thenReturn(10.);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaEnergija()).thenReturn(50.);
        Mockito.when(napadac.getMagije().get(0).getSteta()).thenReturn(10.);

        double expected =napadac.getMagije().get(0).getSteta() * (napadac.getInteligencija() * 0.1 + (napadac.getInteligencija() - napadac.getMagije().get(0).getPotrebnaInteligencija()) + (napadac.getInteligencija() - meta.getInteligencija()));

        assertEquals(expected, napadac.upotrebiMagiju(0, meta), DELTA);
    }

    @Test
    void upotrebiMagijuShouldReturnStetaIfInteligencijaIsEqualToMagijaInteligencijaAndEnergyIsLessThanMagijaEnergyWhenNapadacHasEnoughHealth() {
        napadac.setEnergija(10);
        napadac.setZdravlje(100.);
        napadac.setInteligencija(100);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaInteligencija()).thenReturn(100.);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaEnergija()).thenReturn(50.);
        Mockito.when(napadac.getMagije().get(0).getSteta()).thenReturn(10.);

        double expected = napadac.getMagije().get(0).getSteta() * (napadac.getInteligencija() * 0.1 + (napadac.getInteligencija() - napadac.getMagije().get(0).getPotrebnaInteligencija()) + (napadac.getInteligencija() - meta.getInteligencija()));

        assertEquals(expected, napadac.upotrebiMagiju(0, meta), DELTA);
    }
    //null pointer exception and index out of bound
    // props to Jelena Krstović
    @Test
    void upotrebiMagijuShouldThrowAnIllegalArgumentExceptionIfAtLeastOneMagijaIsNull() {
        napadac.getMagije().add(null);
        napadac.setInteligencija(100);
        napadac.setEnergija(0);
        napadac.setZdravlje(100.);

        assertThrows(IllegalArgumentException.class, ()-> napadac.upotrebiMagiju(napadac.getMagije().size()-1,meta));
    }

    @Test
    void upotrebiMagijuShouldThrowAnIllegalArgumentExceptionIfIndexOfListMagijeDoesNotExist() {
        napadac.getMagije().add(null);
        napadac.setInteligencija(100);
        napadac.setEnergija(0);
        napadac.setZdravlje(100.);

        assertThrows(IllegalArgumentException.class, ()-> napadac.upotrebiMagiju(napadac.getMagije().size(),meta));
    }

    @Test
    void upotrebiMagijuShouldReturnZeroIfInteligencijaIsGreaterThanMagijaInteligencijaAndEnergyIsLessThanMagijaEnergyWhenNapadacDoesNotHaveEnoughHealth() {
        napadac.setEnergija(10);
        napadac.setZdravlje(10.);
        napadac.setInteligencija(100);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaInteligencija()).thenReturn(10.);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaEnergija()).thenReturn(50.);
        Mockito.when(napadac.getMagije().get(0).getSteta()).thenReturn(10.);

        assertAll(
                () -> assertEquals(napadac.getZdravlje() - magija.getPotrebnaEnergija() + napadac.getEnergija(), napadac.getZdravlje(), 1e-9,"zdravlje"),
                () -> assertEquals(0, napadac.getEnergija(), 1e-9,"energija"),
                () -> assertEquals(0, napadac.upotrebiMagiju(0, meta), 1e-9,"steta")
        );
    }

    @Test
    void upotrebiMagijuShouldReturnStetaIfInteligencijaIsGreaterThanMagijaInteligencijaAndEnergyIsGreaterThanMagijaEnergyWhenNapadacDoesNotHaveEnoughHealth() {
        double energijaNapadaca=100.;
        double zdravljeNapadaca= 10.;
        napadac.setEnergija(energijaNapadaca);
        napadac.setZdravlje(zdravljeNapadaca);
        napadac.setInteligencija(100);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaInteligencija()).thenReturn(10.);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaEnergija()).thenReturn(50.);
        Mockito.when(napadac.getMagije().get(0).getSteta()).thenReturn(10.);

        double expected = napadac.getMagije().get(0).getSteta() * (napadac.getInteligencija() * 0.1 + (napadac.getInteligencija() - napadac.getMagije().get(0).getPotrebnaInteligencija()) + (napadac.getInteligencija() - meta.getInteligencija()));

        assertAll(
                () -> assertEquals(zdravljeNapadaca, napadac.getZdravlje(), DELTA,"zdravlje"),
                () -> assertEquals(energijaNapadaca - magija.getPotrebnaEnergija(), napadac.getEnergija(),DELTA,"energija"),
                () -> assertEquals(expected, napadac.upotrebiMagiju(0, meta), DELTA)
        );
    }

    @Test
    void upotrebiMagijuShouldReturnStetaIfInteligencijaIsLessThanMagijaInteligencijaAndEnergyIsLessThanMagijaEnergyWhenNapadacHasEnoughHealth() {
        double energijaNapadaca = 10.;
        double zdravljeNapadaca = 100.;
        napadac.setEnergija(energijaNapadaca);
        napadac.setZdravlje(zdravljeNapadaca);
        napadac.setInteligencija(10);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaInteligencija()).thenReturn(100.);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaEnergija()).thenReturn(50.);
        Mockito.when(napadac.getMagije().get(0).getSteta()).thenReturn(10.);

        double expected = napadac.getMagije().get(0).getSteta() * (napadac.getInteligencija() * 0.1 + (napadac.getInteligencija() - napadac.getMagije().get(0).getPotrebnaInteligencija()) + (napadac.getInteligencija() - meta.getInteligencija()));
        double actual = napadac.upotrebiMagiju(0, meta);

        assertAll(
                () -> assertEquals((0.9 * zdravljeNapadaca) - magija.getPotrebnaEnergija() + 0, napadac.getZdravlje(), DELTA),
                () -> assertEquals(0, napadac.getEnergija(), DELTA),
                () -> assertEquals(expected, actual, DELTA)
        );
    }

    @Test
    void upotrebiMagijuShouldReturnStetaIfInteligencijaIsLessThanMagijaInteligencijaAndEnergyIsEqualToMagijaEnergyWhenNapadacHasEnoughHealth() {
        double energijaNapadaca = 50.;
        double zdravljeNapadaca = 100.;
        napadac.setEnergija(energijaNapadaca);
        napadac.setZdravlje(zdravljeNapadaca);
        napadac.setInteligencija(10);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaInteligencija()).thenReturn(100.);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaEnergija()).thenReturn(50.);
        Mockito.when(napadac.getMagije().get(0).getSteta()).thenReturn(10.);

        double expected = napadac.getMagije().get(0).getSteta() * (napadac.getInteligencija() * 0.1 + (napadac.getInteligencija() - napadac.getMagije().get(0).getPotrebnaInteligencija()) + (napadac.getInteligencija() - meta.getInteligencija()));
        double actual = napadac.upotrebiMagiju(0, meta);

        assertAll(
                () -> assertEquals((0.9 * zdravljeNapadaca) - magija.getPotrebnaEnergija() + 0, napadac.getZdravlje(), DELTA,"zdravlje"),
                () -> assertEquals(0, napadac.getEnergija(), DELTA,"energija"),
                () -> assertEquals(expected, actual, DELTA,"steta")
        );
    }


    @Test
    void upotrebiMagijuShouldReturnZeroIfInteligencijaIsLessThanMagijaInteligencijaWhenNapadacDoesNotHaveEnoughHealth() {
        double energijaNapadaca = 10.;
        double zdravljeNapadaca = 10.;
        napadac.setEnergija(energijaNapadaca);
        napadac.setZdravlje(zdravljeNapadaca);
        napadac.setInteligencija(10);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaInteligencija()).thenReturn(100.);
        Mockito.when(napadac.getMagije().get(0).getPotrebnaEnergija()).thenReturn(50.);
        Mockito.when(napadac.getMagije().get(0).getSteta()).thenReturn(10.);

        double actual = napadac.upotrebiMagiju(0, meta);

        assertAll(
                () -> assertEquals((0.9 * zdravljeNapadaca) - magija.getPotrebnaEnergija() + 0, napadac.getZdravlje(), DELTA,"zdravlje"),
                () -> assertEquals(0, napadac.getEnergija(), DELTA, "energija"),
                () -> assertEquals(0, actual, DELTA,"steta")
        );
    }

    private static Stream<Arguments> parametri() {
        return Stream.of(
                Arguments.of(75,0,75),
                Arguments.of(76,0,76),
                Arguments.of(100,0,100),
                Arguments.of(91,0,91),
                Arguments.of(0,50,50),
                Arguments.of(35,50,85),
                Arguments.of(48,50,98),
                Arguments.of(50,50,100),
                Arguments.of(51,49,100),
                Arguments.of(60,40,100),
                Arguments.of(2,50,52)
        );
    }

    @ParameterizedTest
    @MethodSource(value = "parametri")
    public void odmoriSeShouldReturnAnAdequateValue(double energija, double oporavak, double novaEnergija) {
        napadac.setEnergija(energija);
        double actual = napadac.odmoriSe();
        assertAll(
                () -> assertEquals(oporavak, actual, DELTA, "oporavak"),
                () -> assertEquals(novaEnergija, napadac.getEnergija(), DELTA, "nova energija")
        );
    }
}