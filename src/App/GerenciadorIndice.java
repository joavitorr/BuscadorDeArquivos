package App;

import estruturas.ListaDocumentos;
import estruturas.ListaEncadeada;
import estruturas.MapaDispersao;
import estruturas.NoMapa;

import java.io.*;

public class GerenciadorIndice{

    private static final String ARQUIVO_INDICE = "indice.txt";

    public void salvar(MapaDispersao<ListaDocumentos> indice) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_INDICE))) {
            ListaEncadeada<NoMapa<ListaDocumentos>>[] buckets = indice.getInfo();

            for (int i = 0; i < buckets.length; i++) {
                var no = buckets[i].getCabeca();
                while (no != null) {
                    NoMapa<ListaDocumentos> entrada = no.dado;
                    writer.write(entrada.getChave());
                    writer.write("|");

                    var noDoc = entrada.getValor().getCabeca();
                    while (noDoc != null) {
                        writer.write(noDoc.documento);
                        if (noDoc.proximo != null) {
                            writer.write(";");
                        }
                        noDoc = noDoc.proximo;
                    }

                    writer.newLine();
                    no = no.proximo;
                }
            }

            System.out.println("Índice salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar índice: " + e.getMessage());
        }
    }

    public MapaDispersao<ListaDocumentos> carregar() {
        File arquivo = new File(ARQUIVO_INDICE);

        if (!arquivo.exists()) {
            return null;
        }

        MapaDispersao<ListaDocumentos> indice = new MapaDispersao<>(2000);

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length < 2) continue;

                String palavra = partes[0];
                String[] documentos = partes[1].split(";");

                ListaDocumentos lista = new ListaDocumentos();
                for (String doc : documentos) {
                    lista.adicionar(doc.trim());
                }

                indice.inserir(palavra, lista);
            }

            System.out.println("Índice carregado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar índice: " + e.getMessage());
        }

        return indice;
    }

    public boolean indiceExiste() {
        return new File(ARQUIVO_INDICE).exists();
    }
}