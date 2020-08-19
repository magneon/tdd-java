package br.com.softcube.leilao.services;

import br.com.softcube.leilao.dominio.Lance;
import br.com.softcube.leilao.dominio.Leilao;
import br.com.softcube.leilao.dominio.Usuario;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class AvaliadorTest {

	@Test
	public void obtemMaiorLanceDoLeilao() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario rafael = new Usuario("Rafael");
		Usuario talita = new Usuario("Talita");
		Usuario thais = new Usuario("Thais");

		leilao.propoe(new Lance(rafael, 300));
		leilao.propoe(new Lance(talita, 500));
		leilao.propoe(new Lance(thais, 230));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double maiorLanceEsperado = 500;

		assertEquals(maiorLanceEsperado, avaliador.getMaiorLance(), 0.000001);
	}

	@Test
	public void obtemMenorLanceDoLeilao() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario rafael = new Usuario("Rafael");
		Usuario talita = new Usuario("Talita");
		Usuario thais = new Usuario("Thais");

		leilao.propoe(new Lance(rafael, 300));
		leilao.propoe(new Lance(talita, 500));
		leilao.propoe(new Lance(thais, 230));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double menorLanceEsperado = 230;

		assertEquals(menorLanceEsperado, avaliador.getMenorLance(), 0.000001);
	}

	@Test
	public void obtemMediaDosLances() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario rafael = new Usuario("Rafael");
		Usuario talita = new Usuario("Talita");
		Usuario thais = new Usuario("Thais");

		leilao.propoe(new Lance(rafael, 300));
		leilao.propoe(new Lance(talita, 500));
		leilao.propoe(new Lance(thais, 200));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double mediaLancesEsperado = 333.3333333333333;

		assertEquals(mediaLancesEsperado, avaliador.getMediaLances(), 0.000001);
	}

	@Test
	public void obtemMediaDosZerado() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario rafael = new Usuario("Rafael");
		Usuario talita = new Usuario("Talita");
		Usuario thais = new Usuario("Thais");

		leilao.propoe(new Lance(rafael, 0));
		leilao.propoe(new Lance(talita, 0));
		leilao.propoe(new Lance(thais, 0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double mediaLancesEsperado = 0;

		assertEquals(mediaLancesEsperado, avaliador.getMediaLances(), 0.000001);
	}

	@Test
	public void deveEntenderLeilaoComApenasUmLance() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario rafael = new Usuario("Rafael");

		leilao.propoe(new Lance(rafael, 1000.0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double maiorLanceEsperado = 1000;
		double menorLanceEsperado = 1000;
		double mediaLances = 1000;

		assertEquals(maiorLanceEsperado, avaliador.getMaiorLance(), 0.000001);
		assertEquals(menorLanceEsperado, avaliador.getMenorLance(), 0.000001);
		assertEquals(mediaLances, avaliador.getMediaLances(), 0.000001);
	}

	@Test
	public void deveEntenderLeilaoComLancesAleatorios() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario rafael = new Usuario("Rafael");
		Usuario talita = new Usuario("Talita");
		Usuario thais = new Usuario("Thais");

		leilao.propoe(new Lance(rafael, 1000.0));
		leilao.propoe(new Lance(talita, 500.0));
		leilao.propoe(new Lance(thais, 128.0));
		leilao.propoe(new Lance(talita, 1035.0));
		leilao.propoe(new Lance(thais, 1280.0));
		leilao.propoe(new Lance(rafael, 1350.0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double maiorLanceEsperado = 1350.0;
		double menorLanceEsperado = 128.0;
		double mediaLances = 882.1666666666667;

		assertEquals(maiorLanceEsperado, avaliador.getMaiorLance(), 0.000001);
		assertEquals(menorLanceEsperado, avaliador.getMenorLance(), 0.000001);
		assertEquals(mediaLances, avaliador.getMediaLances(), 0.000001);
	}

	@Test
	public void deveEntenderLeilaoComOrdemDecrescente() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario rafael = new Usuario("Rafael");
		Usuario talita = new Usuario("Talita");
		Usuario thais = new Usuario("Thais");

		leilao.propoe(new Lance(rafael, 1000.0));
		leilao.propoe(new Lance(talita, 900.0));
		leilao.propoe(new Lance(thais, 800.0));
		leilao.propoe(new Lance(talita, 500.0));
		leilao.propoe(new Lance(thais, 300.0));
		leilao.propoe(new Lance(rafael, 200.0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double maiorLanceEsperado = 1000.0;
		double menorLanceEsperado = 200.0;
		double mediaLances = 616.6666666666667;

		assertEquals(maiorLanceEsperado, avaliador.getMaiorLance(), 0.000001);
		assertEquals(menorLanceEsperado, avaliador.getMenorLance(), 0.000001);
		assertEquals(mediaLances, avaliador.getMediaLances(), 0.000001);
	}

	@Test
	public void deveEntenderLeilaoSemLances() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario rafael = new Usuario("Rafael");
		Usuario talita = new Usuario("Talita");
		Usuario thais = new Usuario("Thais");

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		double mediaLances = 0;

		assertEquals(mediaLances, avaliador.getMediaLances(), 0.000001);
	}

	@Test
	public void deveEntenderOsTresMaioresLances() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario rafael = new Usuario("Rafael");
		Usuario talita = new Usuario("Talita");
		Usuario thais = new Usuario("Thais");

		leilao.propoe(new Lance(rafael, 200.0));
		leilao.propoe(new Lance(talita, 300.0));
		leilao.propoe(new Lance(thais, 500.0));
		leilao.propoe(new Lance(talita, 800.0));
		leilao.propoe(new Lance(thais, 900.0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		int quantidadeDeLances = 3;
		double primeiroMaiorLance = 900;
		double segundoMaiorLance = 800;
		double terceiroMaiorLance = 500;

		assertEquals(quantidadeDeLances, avaliador.getMaioresLances().size());
		assertEquals(primeiroMaiorLance, avaliador.getMaioresLances().get(0).getValor(), 0.000001);
		assertEquals(segundoMaiorLance, avaliador.getMaioresLances().get(1).getValor(), 0.000001);
		assertEquals(terceiroMaiorLance, avaliador.getMaioresLances().get(2).getValor(), 0.000001);

	}

	@Test
	public void deveEntenderLeilaoComApenasDoisLances() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Usuario talita = new Usuario("Talita");
		Usuario thais = new Usuario("Thais");

		leilao.propoe(new Lance(talita, 300.0));
		leilao.propoe(new Lance(thais, 900.0));

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		int quantidadeDeLances = 2;
		double primeiroMaiorLance = 900;
		double segundoMaiorLance = 300;

		assertEquals(quantidadeDeLances, avaliador.getMaioresLances().size());
		assertEquals(primeiroMaiorLance, avaliador.getMaioresLances().get(0).getValor(), 0.000001);
		assertEquals(segundoMaiorLance, avaliador.getMaioresLances().get(1).getValor(), 0.000001);

	}

	@Test
	public void deveEntenderLeilaoSemLancesNaLista() {
		Leilao leilao = new Leilao("Monitor 29\" Ultrawide IPS");

		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);

		int quantidadeDeLances = 0;

		assertEquals(quantidadeDeLances, avaliador.getMaioresLances().size());
	}

}
