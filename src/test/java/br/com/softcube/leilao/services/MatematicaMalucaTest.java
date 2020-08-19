package br.com.softcube.leilao.services;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MatematicaMalucaTest {

    @Test
    public void deveEntenderNumerosMenorQueDez() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        int resultadoEsperado = 10;

        Assert.assertEquals(resultadoEsperado, matematicaMaluca.contaMaluca(5));
    }

    @Test
    public void deveEntenderNumerosMaioresQueDezEMenoresQueTrinta() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        int resultadoEsperado = 66;

        Assert.assertEquals(resultadoEsperado, matematicaMaluca.contaMaluca(22));
    }

    @Test
    public void deveEntenderNumerosMaioresQueTrinta() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        int resultadoEsperado = 128;

        Assert.assertEquals(resultadoEsperado, matematicaMaluca.contaMaluca(32));
    }

}