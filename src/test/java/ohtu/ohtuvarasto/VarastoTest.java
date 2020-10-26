package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellistaMaaraaEiLisata() {
    	double tempSaldo = varasto.getSaldo();
    	varasto.lisaaVarastoon(-213);
    	assertEquals(tempSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisataanTayteenJosMaaraYlittyy() {
    	varasto.lisaaVarastoon(12312);
    	assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    	assertEquals(varasto.toString(), "saldo = 10.0, vielä tilaa 0.0");
    }
    
    @Test
    public void ottaminenToimiiOikein() {
    	assertEquals(varasto.otaVarastosta(-1231), 0, vertailuTarkkuus);
    	assertEquals(varasto.otaVarastosta(10), 0, vertailuTarkkuus);
    }
    
    @Test
    public void varastonLuontiToimiiOikein() {
    	Varasto testiVarasto1 = new Varasto(-31023);
    	Varasto testiVarasto2 = new Varasto(10, 10);
    	Varasto testiVarasto3 = new Varasto(-1230, -123);
    	Varasto testiVarasto4 = new Varasto(10, 1124);
    	
    	assertEquals(0, testiVarasto1.getTilavuus(), vertailuTarkkuus);

    	assertEquals(10, testiVarasto2.getTilavuus(), vertailuTarkkuus);
    	assertEquals(10, testiVarasto2.getSaldo(), vertailuTarkkuus);
    	
    	assertEquals(0, testiVarasto3.getTilavuus(), vertailuTarkkuus);
    	assertEquals(0, testiVarasto3.getSaldo(), vertailuTarkkuus);
    	
    	assertEquals(10, testiVarasto4.getSaldo(), vertailuTarkkuus);
    }

}