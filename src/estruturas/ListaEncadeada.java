package estruturas;

public class ListaEncadeada<T> {

    public static class No<T> {
    public T dado;
    public No<T> proximo;

    No(T dado) {
        this.dado = dado;
        this.proximo = null;
    }

    public T getDado() {
        return dado;
    }

    public No<T> getProximo() {
        return proximo;
    }
}

    private No<T> cabeca;
    private int tamanho;

    public ListaEncadeada() {
        this.cabeca = null;
        this.tamanho = 0;
    }

    public void adicionar(T dado) {
        No<T> novo = new No<>(dado);
        novo.proximo = cabeca;
        cabeca = novo;
        tamanho++;
    }

    public T buscar(T dado) {
        No<T> atual = cabeca;
        while (atual != null) {
            if (atual.dado.equals(dado)) {
                return atual.dado;
            }
            atual = atual.proximo;
        }
        return null;
    }

    public boolean remover(T dado) {
        if (cabeca == null) return false;

        if (cabeca.dado.equals(dado)) {
            cabeca = cabeca.proximo;
            tamanho--;
            return true;
        }

        No<T> anterior = cabeca;
        No<T> atual = cabeca.proximo;
        while (atual != null) {
            if (atual.dado.equals(dado)) {
                anterior.proximo = atual.proximo;
                tamanho--;
                return true;
            }
            anterior = atual;
            atual = atual.proximo;
        }
        return false;
    }

    public int getTamanho() {
        return tamanho;
    }

    public No<T> getCabeca() {
        return cabeca;
    }
}