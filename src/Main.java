
import estruturas.ListaDocumentos;
import estruturas.MapaDispersao;

import java.util.Scanner;

import App.Buscador;
import App.GerenciadorIndice;
import App.Indexador;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorIndice gerenciador = new GerenciadorIndice();
        MapaDispersao<ListaDocumentos> indice;

        // Verifica se já existe um índice salvo em disco
        if (gerenciador.indiceExiste()) {
            System.out.println("Índice encontrado! Carregando...");
            indice = gerenciador.carregar();
        } else {
            System.out.println("Nenhum índice encontrado.");
            System.out.print("Informe o diretório a ser indexado: ");
            String caminho = scanner.nextLine();

            indice = new MapaDispersao<>(2000);
            Indexador indexador = new Indexador(indice);
            indexador.indexarDiretorio(caminho);
            gerenciador.salvar(indice);
        }

        Buscador buscador = new Buscador(indice);

        // Loop de busca
        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.print("Digite palavra(s) para buscar (ou 'sair' para encerrar): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("sair")) {
                System.out.println("Encerrando...");
                break;
            }

            if (entrada.isEmpty()) {
                System.out.println("Por favor, digite ao menos uma palavra.");
                continue;
            }

            String[] palavras = entrada.split("\\s+");
            buscador.buscar(palavras);
        }

        scanner.close();
    }
}