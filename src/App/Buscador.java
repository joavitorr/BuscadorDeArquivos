package App;

import estruturas.ListaDocumentos;
import estruturas.MapaDispersao;

public class Buscador {

    private MapaDispersao<ListaDocumentos> indice;

    public Buscador(MapaDispersao<ListaDocumentos> indice) {
        this.indice = indice;
    }

    public void buscar(String[] palavras) {
        if (palavras.length == 1) {
            buscarUmaPalavra(palavras[0]);
        } else {
            buscarVariasPalavras(palavras);
        }
    }

    private void buscarUmaPalavra(String palavra) {
        palavra = palavra.toLowerCase().trim();
        ListaDocumentos lista = indice.buscar(palavra);

        if (lista == null || lista.getTamanho() == 0) {
            System.out.println("Nenhum documento encontrado para: " + palavra);
            return;
        }

        System.out.println("Documentos que contém \"" + palavra + "\":");
        var no = lista.getCabeca();
        int contador = 1;
        while (no != null) {
            System.out.println("  " + contador + ". " + no.documento);
            contador++;
            no = no.proximo;
        }
    }

    private void buscarVariasPalavras(String[] palavras) {
        String primeiraPalavra = palavras[0].toLowerCase().trim();
        ListaDocumentos listaBase = indice.buscar(primeiraPalavra);

        if (listaBase == null || listaBase.getTamanho() == 0) {
            System.out.println("Nenhum documento encontrado.");
            return;
        }

        
        System.out.println("Documentos que contém todas as palavras:");
        int contador = 1;
        var no = listaBase.getCabeca();

        while (no != null) {
            String documento = no.documento;
            boolean contemTodas = true;

            for (int i = 1; i < palavras.length; i++) {
                String palavra = palavras[i].toLowerCase().trim();
                ListaDocumentos lista = indice.buscar(palavra);

                if (lista == null || !lista.contem(documento)) {
                    contemTodas = false;
                    break;
                }
            }

            if (contemTodas) {
                System.out.println("  " + contador + ". " + documento);
                contador++;
            }

            no = no.proximo;
        }

        if (contador == 1) {
            System.out.println("Nenhum documento contém todas as palavras.");
        }
    }
}