package App;

import estruturas.ListaDocumentos;
import estruturas.MapaDispersao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Indexador {

    private MapaDispersao<ListaDocumentos> indice;

    public Indexador(MapaDispersao<ListaDocumentos> indice) {
        this.indice = indice;
    }

    public void indexarDiretorio(String caminho) {
        caminho = caminho.trim().replace("\\", "/");
        File diretorio = new File(caminho);

        if (!diretorio.exists() || !diretorio.isDirectory()) {
            System.out.println("Diretório inválido: " + caminho);
            return;
        }

        processarDiretorio(diretorio);
    }

    private void processarDiretorio(File diretorio) {
        File[] arquivos = diretorio.listFiles();

        if (arquivos == null) return;

        for (File arquivo : arquivos) {
            if (arquivo.isDirectory()) {
                // Recursão: entra nos subdiretórios
                processarDiretorio(arquivo);
            } else if (arquivo.getName().endsWith(".txt")) {
                indexarArquivo(arquivo);
            }
        }
    }

    private void indexarArquivo(File arquivo) {
        System.out.println("Indexando: " + arquivo.getPath());

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] tokens = linha.split("[^a-zA-ZÀ-ÿ]+");
                for (String token : tokens) {
                    String palavra = token.toLowerCase().trim();
                    if (deveIndexar(palavra)) {
                        adicionarNoIndice(palavra, arquivo.getPath());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + arquivo.getPath());
        }
    }

    private boolean deveIndexar(String palavra) {
        if (palavra.length() < 3) return false;
        // Rejeita palavras compostas só de dígitos ou pontos
        if (palavra.matches("[0-9.]+")) return false;
        return true;
    }

    private void adicionarNoIndice(String palavra, String caminhoArquivo) {
        ListaDocumentos lista = indice.buscar(palavra);

        if (lista == null) {
            lista = new ListaDocumentos();
            lista.adicionar(caminhoArquivo);
            indice.inserir(palavra, lista);
        } else {
            lista.adicionar(caminhoArquivo);
        }
    }
}