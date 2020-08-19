package br.com.softcube.leilao.services;

import br.com.softcube.leilao.dominio.Lance;
import br.com.softcube.leilao.dominio.Usuario;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class FiltroDeLancesTest {

    @Test
    public void deveSelecionarLancesEntre1000E3000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,2000),
                new Lance(joao,1000),
                new Lance(joao,3000),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(2000, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarLancesEntre500E700() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtro = new FiltroDeLances();
        List<Lance> resultado = filtro.filtra(Arrays.asList(
                new Lance(joao,600),
                new Lance(joao,500),
                new Lance(joao,700),
                new Lance(joao, 800)));

        assertEquals(1, resultado.size());
        assertEquals(600, resultado.get(0).getValor(), 0.00001);
    }

    @Test
    public void deveSelecionarApenasLancesAcimaDe5000() {
        Usuario joao = new Usuario("Joao");

        FiltroDeLances filtroDeLances = new FiltroDeLances();
        List<Lance> resultado = filtroDeLances.filtra(Arrays.asList(
                new Lance(joao, 5000),
                new Lance(joao, 5500),
                new Lance(joao, 7000)));

        assertEquals(2, resultado.size());
        assertEquals(5500, resultado.get(0).getValor(), 0.000001);
        assertEquals(7000, resultado.get(1).getValor(), 0.000001);

    }

}
