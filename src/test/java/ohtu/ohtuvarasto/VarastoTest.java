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
    Varasto tyhjaVarasto;
    Varasto kuormitettuVarasto;
    Varasto huonoKuormitettuVarasto;
    Varasto kuormitettuVarastoLiianTaysi;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        tyhjaVarasto = new Varasto(-22);
        kuormitettuVarasto = new Varasto(10, 5);
        huonoKuormitettuVarasto = new Varasto(-10, -5);
        kuormitettuVarastoLiianTaysi = new Varasto(5, 10);
    }

    @Test
    public void voidaanLuodaTyhjanTilavuudenVarasto() {
        assertEquals(0.0, tyhjaVarasto.getTilavuus(), vertailuTarkkuus);
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
    public void yritetaanLaittaaLiikaa() {
        varasto.lisaaVarastoon(8);

        varasto.lisaaVarastoon(5);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void yritetaanOttaaLiikaa() {
        varasto.lisaaVarastoon(5);

        assertEquals(5, varasto.otaVarastosta(22), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaNegatiivinenMaara() {
        varasto.lisaaVarastoon(8);

        assertEquals(0, varasto.otaVarastosta(-3), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonNegatiivinenMaara() {
        varasto.lisaaVarastoon(-5);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringTulostaaOikein() {
        varasto.lisaaVarastoon(8);

        String vastaus = varasto.toString();

        assertEquals("saldo = 8.0, vielä tilaa 2.0", vastaus, vertailuTarkkuus);
    }

    @Test
    public void kuormitetullaVarastollaOikeaTilavuus() {
        assertEquals(10, kuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kuormitetullaVarastollaOikeaSaldo() {
        assertEquals(5, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void josKuormitetunVarastonTilavuusNegatiivinenNiinTilavuusNolla() {
        assertEquals(0, huonoKuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void josKuormitetunVarastonSaldoNegatiivinenNiinSaldoNolla() {
        assertEquals(0, huonoKuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void josKuormitetunVarastonSaldoLiianIso() {
        assertEquals(5, kuormitettuVarastoLiianTaysi.getSaldo(), vertailuTarkkuus);
    }

}