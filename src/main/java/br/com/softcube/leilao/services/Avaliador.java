package br.com.softcube.leilao.services;

import br.com.softcube.leilao.dominio.Lance;
import br.com.softcube.leilao.dominio.Leilao;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Avaliador {

    private double maiorValor = Double.NEGATIVE_INFINITY;
    private double menorValor = Double.POSITIVE_INFINITY;
    private double mediaLances = Double.valueOf(0);
    private List<Lance> maioresLances;

    public void avalia(Leilao leilao) {
        for (Lance lance : leilao.getLances()) {
            mediaLances += lance.getValor();

            if (lance.getValor() > maiorValor) {
                maiorValor = lance.getValor();
            }

            if (lance.getValor() < menorValor) {
                menorValor = lance.getValor();
            }
        }

        geraMediaLeilao(leilao);

        pegaOsTresMaioresDo(leilao);
    }

    private void pegaOsTresMaioresDo(Leilao leilao) {
        List<Lance> lances = leilao.getLances().stream().sorted(Comparator.comparing(Lance::getValor).reversed()).collect(Collectors.toList());
        maioresLances = lances.subList(0, lances.size() > 3 ? 3 : lances.size());
    }

    private void geraMediaLeilao(Leilao leilao) {
        mediaLances = leilao.getLances() == null || leilao.getLances().isEmpty() ? 0 : mediaLances / leilao.getLances().size();
    }

    public double getMaiorLance() {
        return maiorValor;
    }

    public double getMenorLance() {
        return menorValor;
    }

    public double getMediaLances() {
        return mediaLances;
    }

    public List<Lance> getMaioresLances() {
        return maioresLances;
    }
}
