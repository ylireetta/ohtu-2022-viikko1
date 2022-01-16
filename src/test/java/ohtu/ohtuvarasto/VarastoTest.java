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
    public void negatiivinenTilavuusAsettaaNollan() {
        Varasto rikkinainen = new Varasto(-10.0);
        assertEquals(0.0, rikkinainen.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void kahdenParamterinKonstruktoriToimii() {
        Varasto kaksiParametria = new Varasto(10.0, 5.0);
        
        assertEquals(10.0, kaksiParametria.getTilavuus(), vertailuTarkkuus);
        assertEquals(5.0, kaksiParametria.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kahdenParamterinKonstruktoriKunAlkusaldoTilavuuttaSuurempi() {
        Varasto kaksiParametria = new Varasto(10.0, 15.0);
        
        assertEquals(10.0, kaksiParametria.getTilavuus(), vertailuTarkkuus);
        assertEquals(10.0, kaksiParametria.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void kahdenParamterinKonstruktoriNegatiivisillaArvoilla() {
        Varasto kaksiParametria = new Varasto(-10.0, -5.0);
        
        assertEquals(0.0, kaksiParametria.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, kaksiParametria.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ylilisaysTayttaaVaraston() {
        varasto.lisaaVarastoon(15.0);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiVaikuta() {
        varasto.lisaaVarastoon(-10.0);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void yliottoTyhjentaaVaraston() {
        varasto.lisaaVarastoon(10.0);
        
        assertEquals(10.0, varasto.otaVarastosta(15.0), vertailuTarkkuus);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoPalauttaaNollan() {
        assertEquals(0.0, varasto.otaVarastosta(-10.0), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoesitysToimii() {
        varasto.lisaaVarastoon(10.0);
        String jono = "saldo = 10.0, vielä tilaa 0.0";
        String toinenJono = "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu();
        
        assertEquals(jono, varasto.toString());
        assertEquals(toinenJono, varasto.toString());
    }

}